package gov.nih.nci.codegen.core.transformer;

import gov.nih.nci.codegen.core.BaseArtifact;
import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;
import gov.nih.nci.codegen.core.filter.UML13ClassifierFilter;
import gov.nih.nci.codegen.core.filter.UML13ModelElementFilter;
import gov.nih.nci.codegen.core.util.UML13Utils;
import gov.nih.nci.codegen.core.util.XMLUtils;
import gov.nih.nci.codegen.framework.FilteringException;
import gov.nih.nci.codegen.framework.TransformationException;
import gov.nih.nci.codegen.framework.Transformer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.lang.StringBuffer;
import java.util.StringTokenizer;
import javax.jmi.reflect.RefObject;
import org.apache.log4j.Logger;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.modelmanagement.Model;
import org.omg.uml.modelmanagement.UmlPackage;
import java.io.*;

/**
 * <!-- LICENSE_TEXT_START -->
* Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
* materials provided with the distribution.
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by the SAIC and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
* wherever such third-party acknowledgments normally appear.
* 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
* the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
* SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * Produces an XML file that contains object-relational mapping configuration
 * information for use by the OJB tool ( <a href="http://db.apache.org/ojb/"
 * target="_blank">http://db.apache.org/ojb/ </a>). In particular, it produces
 * class-descriptor elements from a set classes defined in a UML 1.3 model.
 * <p>
 * In order to use this transformer, the supplied UML model must contain certain
 * information, in the form of tagged values and stereotypes. This section
 * describes the control file configuration and how it relates to the code. It
 * does not describe how the UML model must be annotated (see the User's Guide
 * for that).
 * <p>
 * The content model for this transformer's configuration element is as follows:
 * <p>
 * <code>
 * <pre>
 *
 *
 *
 *    &lt;!ELEMENT transformer (param, filter)&gt;
 *    &lt;!ATTLIST transformer
 *       name CDATA #REQUIRED
 *       className CDATA #FIXED gov.nih.nci.codegen.core.transformer.OJBRepTransformer&gt;
 *    &lt;!ELEMENT param EMPTY&gt;
 *    &lt;!ATTLIST param
 *       name CDATA #FIXED packageName
 *       value CDATA #REQUIRED&gt;
 *    &lt;!ELEMENT filter ... see {@link gov.nih.nci.codegen.core.filter.UML13ClassifierFilter#configure(org.w3c.dom.Element)} ...
 *
 *
 *
 * </pre>
 * </code>
 * <p>
 * As you can see, this transformer expects a nested filter element. The reason
 * is that this transformer produces a single Artifact (an XML file) from a
 * collection of model elements.
 * <p>
 * UML13OJBRepTransformer expects to be passed an instance of
 * org.omg.uml.modelmanagement.Model. It uses UML13ModelElementFilter to obtain
 * all model elements in the model. Then it use UML13Classifier to obtain the
 * classifiers selected by the contents of the nested filter element. Then it
 * iterates through these classifiers, building the class-descriptor elements.
 * <p>
 * A Collection containing a single Artifact is returned by this transformer's
 * execute method. The name attribute of the Artifact is set to "ojb_repository"
 * and its source attribute is set to the String that represents the XML
 * document.
 * <p>
 *
 * @author caBIO Team
 * @version 1.0
 */
public class UML13WSDDTransformerForEVS implements Transformer , XMLConfigurable {

    private static Logger log = Logger.getLogger(UML13WSDDTransformerForEVS.class);

    private UML13ClassifierFilter _classifierFilt;

    private String _pkgName, _svcName, outputDir, evsFileName;
    StringBuffer cache = new StringBuffer();
    private Properties evsProperties = new Properties();
    private boolean createEVS = false;


    /**
     *
     */
    public UML13WSDDTransformerForEVS() {
        super();        
        
    }

    /**
     * @see gov.nih.nci.codegen.framework.Transformer#execute(javax.jmi.reflect.RefObject,
     *      java.util.Collection)
     */
    public Collection execute(RefObject modelElement, Collection artifacts)
            throws TransformationException {
        if (modelElement == null) {
        	log.error("model element is null");
            throw new TransformationException("model element is null");
        }
        if (!(modelElement instanceof Model)) {
        	log.error("model element not instance of Model");
            throw new TransformationException(
                    "model element not instance of Model");
        }
        ArrayList newArtifacts = new ArrayList();
        UML13ModelElementFilter meFilt = new UML13ModelElementFilter();
        ArrayList umlExtentCol = new ArrayList();
        umlExtentCol.add(modelElement.refOutermostPackage());
        Collection classifiers = null;
        try {
            classifiers = _classifierFilt.execute(meFilt.execute(umlExtentCol));
        } catch (FilteringException ex) {
        	log.error("couldn't filter model elements " + ex.getMessage());
            throw new TransformationException("couldn't filter model elements",
                    ex);
        }

        String methodList = generateConfig(classifiers);


        newArtifacts.add(new BaseArtifact("ehcache", modelElement, methodList ));

        return newArtifacts;

    }

    /**
	 * @param classifiers
	 * @return
     */

    private String generateConfig(Collection classifiers) {
        String evsClasses ="";
        try{
            if(createEVS){
                evsClasses = getEVSClassList() + ",";  
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        cache.append("<deployment xmlns=\"http://xml.apache.org/axis/wsdd/\"");
        cache.append("\n");
        cache.append("	xmlns:java=\"http://xml.apache.org/axis/wsdd/providers/java\">");
        cache.append("\n");
        cache.append("	<service name=\"" + _svcName + "\" style=\"wrapped\" use=\"literal\">");
        cache.append("\n");
        cache.append("	<parameter name=\"className\" value=\"gov.nih.nci.system.webservice.WSQuery\"/>");
        cache.append("\n");
        cache.append("	<parameter name=\"allowedMethods\" value=\"*\"/>");
        cache.append("\n");
    	cache.append("	<parameter name=\"extraClasses\"");
    	cache.append("\n");
    	cache.append("	value=\"gov.nih.nci.search.SearchQuery,gov.nih.nci.search.SearchResult,java.lang.Enum,java.util.HashSet,java.util.HashMap");
        if(evsClasses != null && createEVS){
        	cache.append(","+evsClasses);
        }
        

        StringBuffer nn1 = new StringBuffer();
		for (Iterator i = classifiers.iterator(); i.hasNext();) {
			Classifier klass = (Classifier) i.next();
			UmlPackage classPkg = null;
			if (_pkgName != null) {
				classPkg = UML13Utils.getPackage(UML13Utils.getModel(klass),_pkgName);
			} else {
				classPkg = UML13Utils.getModel(klass);
			}
			String name = UML13Utils.getNamespaceName(classPkg, klass);
			
			nn1.setLength(0);
			nn1.append(name);
			nn1.append(".ws.");
			nn1.append(klass.getName());
			nn1.append(",");
			
			nn1.append(name);  //UML13Utils.getNamespaceName(classPkg, klass);
			nn1.append(".ws.");
			nn1.append(klass.getName());
			nn1.append("Impl");
			if (i.hasNext()) {
				nn1.append(",");
			}

			//fill in fullyqualified object name
			cache.append(nn1);
			//cache = cache + "\n";

		}
		/*StringBuffer tmpStringBuffer = new StringBuffer(nn1);
		//System.out.println("String before: " + tmpStringBuffer.toString() + "\n");
		int length = tmpStringBuffer.lastIndexOf(",");
		String finalString = tmpStringBuffer.substring(0,length-1);
        //System.out.println("String before: " + finalString + "\n");
		*/

        cache.append("\"/>" + "\n");
        for (Iterator i = classifiers.iterator(); i.hasNext();) {
					Classifier klass = (Classifier) i.next();
					UmlPackage classPkg = null;
					if (_pkgName != null) {
						classPkg = UML13Utils.getPackage(UML13Utils.getModel(klass),_pkgName);
					} else {
						classPkg = UML13Utils.getModel(klass);
					}
					
					String tmp1_1 = "<beanMapping xmlns:myNS=\"urn:ws.";
					tmp1_1 = tmp1_1 + reversePackageName(UML13Utils.getNamespaceName(classPkg, klass)).replaceAll(":impl.",":ws.");
					tmp1_1 = tmp1_1 + "\" ";
					tmp1_1 = tmp1_1 + " qname=\"myNS:";
					tmp1_1 = tmp1_1 + klass.getName();
					tmp1_1 = tmp1_1 + "\" ";
					tmp1_1 = tmp1_1 + "languageSpecificType=\"java:";

					String nn2_1 = UML13Utils.getNamespaceName(classPkg, klass);
					nn2_1 = nn2_1 + ".ws.";
					nn2_1 = nn2_1 + klass.getName();
					tmp1_1 = tmp1_1 + nn2_1;
					tmp1_1 = tmp1_1 + "\" />";
					tmp1_1 = tmp1_1 + "\n";
					cache.append(tmp1_1);

					String tmp1 = "<beanMapping xmlns:myNS=\"urn:ws.";
					tmp1 = tmp1 + reversePackageName(UML13Utils.getNamespaceName(classPkg, klass)).replaceAll(":impl.",":ws.");
					tmp1 = tmp1 + "\" ";
					//tmp1= tmp1 + "\n";
					tmp1 = tmp1 + " qname=\"myNS:";
					tmp1 = tmp1 + klass.getName();
					tmp1 = tmp1 + "Impl";
					tmp1 = tmp1 + "\" ";
					//tmp1= tmp1 + "\n";
					tmp1 = tmp1 + "languageSpecificType=\"java:";

					String nn2 = UML13Utils.getNamespaceName(classPkg, klass);
					nn2 = nn2 + ".ws.";
					nn2 = nn2 + klass.getName();
					nn2 = nn2 + "Impl";
		            tmp1 = tmp1 + nn2;
		            tmp1 = tmp1 + "\" />";
		            tmp1= tmp1 + "\n";
					cache.append(tmp1);

		}
        try{
            if(createEVS){
            	cache.append(this.getEVSBeanMapping());  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String searchQuery = "<beanMapping xmlns:nciSearch=\"urn:search.nci.nih.gov\" qname=\"nciSearch:SearchQuery\" languageSpecificType=\"java:gov.nih.nci.search.SearchQuery\"/>\n";
            String searchResult = "<beanMapping xmlns:nciSearch=\"urn:search.nci.nih.gov\" qname=\"nciSearch:SearchResult\" languageSpecificType=\"java:gov.nih.nci.search.SearchResult\"/>\n";
            String sort = "<beanMapping xmlns:nciSearch=\"urn:search.nci.nih.gov\" qname=\"nciSearch:Sort\" languageSpecificType=\"java:gov.nih.nci.search.Sort\"/>\n";
            String rangeFilter = "<beanMapping xmlns:nciSearch=\"urn:search.nci.nih.gov\" qname=\"nciSearch:RangeFilter\" languageSpecificType=\"java:gov.nih.nci.search.RangeFilter\"/>\n";
            String map = "<beanMapping xmlns:nciSearch=\"urn:search.nci.nih.gov\" qname=\"nciSearch:HashMap\" languageSpecificType=\"java:java.util.HashMap\"/>\n";
            cache.append(searchQuery + searchResult + sort + rangeFilter + map);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        cache.append("</service>");
        cache.append("\n");
        cache.append("</deployment>");
        cache.append("\n");

		return cache.toString();
    }



    /**
     * @see gov.nih.nci.codegen.core.JDOMConfigurable#configure(org.jdom.Element)
     */
    public void configure(org.w3c.dom.Element config)
            throws ConfigurationException {
        

        org.w3c.dom.Element filterEl = XMLUtils.getChild(config, "filter");
        if (filterEl == null) {
        	log.error("no child filter element found");
            throw new ConfigurationException("no child filter element found");
        }

        String className = filterEl.getAttribute("className");
        if (className == null) {
        	log.error("no filter class name specified");
            throw new ConfigurationException("no filter class name specified");
        }
        _pkgName = getParameter(config, "basePackage");
        log.debug("basePackage: " + _pkgName);

        _svcName = getParameter(config, "webserviceName");
        evsFileName = getParameter(config, "evsFileName");
        int found = -1;
        try{
            found = Thread.currentThread().getContextClassLoader().getResourceAsStream(evsFileName).available();
            if(found != -1){
                createEVS = true;
                this.loadEVSProperties();
            }
        }
        catch (FileNotFoundException fnfex) {
        	createEVS = false;
        	log.error("Error: the file '" + evsFileName + "' could not be found", fnfex);
        }
        catch (IOException ioex) {
        	createEVS = false;
        	log.error("Error: could not read file '" + evsFileName + "'", ioex);
        }
        catch(Exception ex){
            createEVS = false;
            log.error("Error: an unknown error occurred while trying to find/access '" + evsFileName + "' - " + ex.getMessage(), ex);
        }
        
        try {
            _classifierFilt = (UML13ClassifierFilter) Class.forName(className)
                    .newInstance();
        } catch (Exception ex) {
        	log.error("Couldn't instantiate "
                    + className);
            throw new ConfigurationException("Couldn't instantiate "
                    + className);
        }

        _classifierFilt.configure(filterEl);
    }

    public String capFirst(String s){
			return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public String reversePackageName(String s) {

		StringTokenizer st = new StringTokenizer(s,".");
		Vector myVector = new Vector();
		StringBuffer myStringBuffer = new StringBuffer();
		while (st.hasMoreTokens()) {
			     String t = st.nextToken();
			     myVector.add(t);

	    }

        for (int i = myVector.size(); i>0; i--) {
			  myStringBuffer.append(myVector.elementAt(i-1));
			  myStringBuffer.append(".");

	    }
	    int length1 = myStringBuffer.length();
	    String finalString1 = myStringBuffer.substring(0,length1-1);
        return finalString1;
    }

    private String getParameter(org.w3c.dom.Element config, String paramName) {
        String param = null;

        List params = XMLUtils.getChildren(config, "param");
        for (Iterator i = params.iterator(); i.hasNext();) {
            org.w3c.dom.Element paramEl = (org.w3c.dom.Element) i.next();
            if (paramName.equals(paramEl.getAttribute("name"))) {
                param = paramEl.getAttribute("value");
                break;
            }
        }

        return param;
    }

    public void loadEVSProperties() throws Exception {       
        String fileName = evsFileName; 
        evsProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
    }
    public String getEVSClassList() throws Exception{
        StringBuffer evsClassList = new StringBuffer("java.util.HashSet");        
        if(evsProperties != null){
            Set keys = evsProperties.keySet();        
            Iterator i=keys.iterator();            
            for(;i.hasNext();){
                String key = (String)i.next();
                String value = (String)evsProperties.get(key);
                if(key.indexOf(".")>0){
                	evsClassList.append(",");
                	evsClassList.append(value);
                	evsClassList.append(".ws.");
                	evsClassList.append(key.substring(key.lastIndexOf(".")+1));
                }
            }
        }
           
        return evsClassList.toString();        
    }
    
    public String getEVSBeanMapping() throws Exception{
        String evsBeanMapping = "\n";        
        String namespace = "xmlns:nciEVS=\"urn:";
        String qname = " qname=\"nciEVS:";   
        String reversedPackageName = "";
      
        if(evsProperties != null){
            Set keys = evsProperties.keySet();        
            Iterator i=keys.iterator();            
            for(;i.hasNext();){
                String key = (String)i.next();
                String wsClassName = evsProperties.get(key)+".ws."+ key.substring(key.lastIndexOf(".")+1);
               
                List packageList = new ArrayList();
                StringTokenizer st = new StringTokenizer(key.substring(0,key.lastIndexOf(".")),".");
                
                while(st.hasMoreTokens()){
                    //packageList.add(st.nextToken());                }
                    packageList.add(st.nextToken());                
                    }

                packageList.add("ws");
                reversedPackageName = (String)packageList.get(packageList.size()-1);
                for(int x = packageList.size()-2; x>=0; x--){
                    reversedPackageName += "."+(String)packageList.get(x);
                }
                reversedPackageName += "\"";
                String beanMapping = "<beanMapping "+  namespace + reversedPackageName + qname + key.substring(key.lastIndexOf(".")+1)  + "\" languageSpecificType=\"java:"+wsClassName+"\"/>\n";
                evsBeanMapping += beanMapping;                
            }
 
        }
         evsBeanMapping += "<beanMapping "+ namespace + reversedPackageName + qname +"HashSet\""+ " languageSpecificType=\"java:java.util.HashSet\"/>\n";         
        
         

        
        //log.info("EVS Bean Mapping" + evsBeanMapping);
    return evsBeanMapping;
    }
}
