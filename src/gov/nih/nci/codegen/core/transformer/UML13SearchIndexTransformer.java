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
import gov.nih.nci.common.util.Constant;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.jmi.reflect.RefObject;

import org.apache.log4j.Logger;

import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.Dependency;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.modelmanagement.Model;
import org.omg.uml.modelmanagement.UmlPackage;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.extensionmechanisms.Stereotype;
import org.omg.uml.foundation.extensionmechanisms.TaggedValue;

/*
 * Created on Mar 29, 2007
 * ShaziyaMuhsin
 *
 */
public class UML13SearchIndexTransformer implements Transformer, XMLConfigurable{
    private static Logger log = Logger.getLogger(UML13SearchIndexTransformer.class);

    private UML13ClassifierFilter _classifierFilt;

    private String _pkgName, _omPkg, _svcName;
    String cache = "";


    /**
     *
     */
    public UML13SearchIndexTransformer() {
        super();
    }

    /**
     * @see gov.nih.nci.codegen.framework.Transformer#execute(javax.jmi.reflect.RefObject,
     *      java.util.Collection)
     */
    public Collection execute(RefObject modelElement, Collection artifacts)
            throws TransformationException {
        if (modelElement == null) {
            throw new TransformationException("model element is null");
        }
        if (!(modelElement instanceof Model)) {
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
            log.error("couldn't filter model elements" + ex.getMessage());
            throw new TransformationException("couldn't filter model elements",
                    ex);
        }

        String methodList = generateConfig(classifiers);


        newArtifacts.add(new BaseArtifact("CommonPackageUtility", modelElement, methodList ));

        return newArtifacts;

    }

    /**
     * @param classifiers
     * @return
     */

    private String generateConfig(Collection classifiers) {
        String packageName = null;
        String className = null;
        StringBuffer fileString = new StringBuffer();
        /***/


        for (Iterator i = classifiers.iterator(); i.hasNext();) {
            Classifier klass = (Classifier) i.next();
            String basePkg = _pkgName;
            boolean indexClass = false;
            TaggedValue indexClassTag = UML13Utils.getTaggedValue(klass,"index_class");
            if (indexClassTag!=null) {
                if (indexClassTag.getValue().equalsIgnoreCase("true")) {
                 indexClass=true;
                }
            }
            if(basePkg != null){
                packageName = UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass);
                }
            className = packageName+Constant.DOT+klass.getName();
            //log.info("Package - "+ packageName + "\tClass  - "+ klass.getName() );
            //Get indexed fields;

            if(indexClass){
                StringBuffer indexedFields = new StringBuffer();
                for(Iterator it = UML13Utils.getAttributes((UmlClass)klass).iterator(); it.hasNext();){

                    Attribute att = (Attribute)it.next();
                    TaggedValue indexFieldTag = UML13Utils.getTaggedValue(att,"indexed");
                    if(indexFieldTag!=null){
                        if(indexFieldTag.getValue().equalsIgnoreCase("true")){
                            indexedFields.append(att.getName()+";");
                        }
                    }
                }
                if(indexClass && className != null && indexedFields != null){
                    fileString.append(className + Constant.EQUAL + indexedFields+"\n");
                }
            }
        }
        return fileString.toString();
    }



    /**
     * @see gov.nih.nci.codegen.core.JDOMConfigurable#configure(org.jdom.Element)
     */
    public void configure(org.w3c.dom.Element config)
            throws ConfigurationException {

        org.w3c.dom.Element filterEl = XMLUtils.getChild(config, "filter");
        if (filterEl == null) {
            throw new ConfigurationException("no child filter element found");
        }

        String className = filterEl.getAttribute("className");
        if (className == null) {
            throw new ConfigurationException("no filter class name specified");
        }
        _pkgName = getParameter(config, "basePackage");
        log.debug("basePackage: " + _pkgName);

        try {
            _classifierFilt = (UML13ClassifierFilter) Class.forName(className)
                    .newInstance();
        } catch (Exception ex) {
            log.error("Couldn't instantiate : " + ex.getMessage());
            throw new ConfigurationException("Couldn't instantiate "
                    + className);
        }

        _classifierFilt.configure(filterEl);
    }

    public String capFirst(String s){
            return s.substring(0, 1).toUpperCase() + s.substring(1);
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

    private String getPackage(UmlClass klass) {
        UmlPackage pkg = null;
        if (_omPkg != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(klass), _omPkg);
        } else {
            pkg = UML13Utils.getModel(klass);
        }
        return UML13Utils.getNamespaceName(pkg, klass);

    }
    private UmlClass getTable(UmlClass klass) {
        UmlClass table = null;
        Collection clients = new ArrayList();
        for (Iterator j = klass.getSupplierDependency().iterator(); j.hasNext();) {
            Dependency d = (Dependency) j.next();
            Stereotype s = UML13Utils.getStereotype(d);
            if (s != null && "DataSource".equals(s.getName())) {
                clients.addAll(d.getClient());
            }
        }
        if (clients.size() != 1) {
            log.error(clients.size() + " data sources found for "
                    + klass.getName());
        } else {
            table = (UmlClass) clients.iterator().next();
        }
        return table;
    }
    private Attribute getColumn(UmlClass table, Attribute att) {
        String match = getPackage((UmlClass) att.getOwner()) + Constant.DOT
                + att.getOwner().getName() + Constant.DOT + att.getName();
        log.debug("Looking for " + match + " on table " + table.getName());
        Attribute theCol = null;
        search: for (Iterator i = UML13Utils.getAttributes(table).iterator(); i
                .hasNext();) {
            Attribute col = (Attribute) i.next();
            TaggedValue tv = UML13Utils
                    .getTaggedValue(col, "mapped-attributes");
            if (tv != null && tv.getValue() != null) {
                if (tv != null && tv.getValue() != null) {
                    StringTokenizer st = new StringTokenizer(tv.getValue(), ";");
                    while (st.hasMoreTokens()) {
                        String t = st.nextToken();
                        if (t.equalsIgnoreCase(match)) {
                            theCol = col;
                            break search;
                        }
                    }
                }
            }
        }
        return theCol;
    }

    private Attribute getColumn(UmlClass table, Attribute att,
            String correctPackageName) {

        String match = correctPackageName + Constant.DOT + att.getName();
        log.debug("Looking for " + match + " on table " + table.getName());
        Attribute theCol = null;
        search: for (Iterator i = UML13Utils.getAttributes(table).iterator(); i
                .hasNext();) {
            Attribute col = (Attribute) i.next();
            TaggedValue tv = UML13Utils
                    .getTaggedValue(col, "mapped-attributes");
            if (tv != null && tv.getValue() != null) {
                if (tv != null && tv.getValue() != null) {
                    StringTokenizer st = new StringTokenizer(tv.getValue(), ";");
                    while (st.hasMoreTokens()) {
                        String t = st.nextToken();
                        if (t.equalsIgnoreCase(match)) {
                            theCol = col;
                            break search;
                        }
                    }
                }
            }
        }
        return theCol;
    }


}
