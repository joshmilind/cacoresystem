package gov.nih.nci.system.dao.impl.externalsystem;

import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.tree.DefaultMutableTreeNode;
//internal

import gov.nih.nci.evs.domain.*;
import gov.nih.nci.evs.domain.HistoryRecord;
import gov.nih.nci.evs.query.EVSQueryImpl;
import gov.nih.nci.evs.security.SecurityToken;


import gov.nih.nci.common.util.*;

import gov.nih.nci.common.net.*;


import msso.validator.MSSOUserValidator;

import org.LexGrid.LexBIG.admin.ListSchemes;
import org.apache.log4j.*;

//Import LexBIG classes
import gov.nih.nci.lexrpc.client.*;
import gov.nih.nci.lexrpc.client.EditActionDate;
import gov.nih.nci.lexrpc.client.Property;
import gov.nih.nci.lexrpc.client.Qualifier;
import gov.nih.nci.lexrpc.server.LexAdapter;
import gov.nih.nci.system.dao.*;


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
  * @author caBIO Team
  * @version 1.0.1
 */
/**
 * Defines and implements methods to query the Enterprise Vocabulary Service.
 * Results are returned as a Response object.
 */

public class EVSLexBigDAOImpl implements DAO
{
	private static Logger log = Logger.getLogger(EVSLexBigDAOImpl.class.getName());	
    private LexAdapter adapter;	
	private String server;
	private String port;
	private Exception pastEx = new Exception();
    private Vocabulary vocabulary = null;
    private HashMap tokenCollection = new HashMap();
    private String defaultVocabularyName = "NCI_Thesaurus";



	/**
	 * Creates a connection to the EVS datasource and generates a query based on the request.
	 * The results are wrapped in a response object
	 * @param r - Request object (This object holds the evs query)
	 * @return - The results are stored in a list and returned as a response object
	 * @throws DAOException
	 * @throws Exception
	 */
	public Response query(Request r) throws DAOException
	{
		Object obj = r.getRequest();
		List resultList  = new ArrayList();
		gov.nih.nci.common.net.Response response = null;
		Hashtable configs;
		String methodName = null;
		try{
			configs= r.getConfig();
			EVSQueryImpl criteria = (EVSQueryImpl)obj;
			if(criteria.getSecurityTokenCollection().size()>0){
	            tokenCollection = criteria.getSecurityTokenCollection();
	        }
			Field[] fields = criteria.getClass().getDeclaredFields();
			for(int i=0; i<fields.length; i++)
			{
				fields[i].setAccessible(true);
				String fieldName = fields[i].getName();
				if(fields[i].getType().equals(HashMap.class))
				{
					HashMap mapValues = (HashMap)fields[i].get(criteria);
					if((mapValues != null) && (mapValues.size() > 0))
					{
                        try{
                            adapter = new LexAdapter();
                        }catch(Exception ex){
                            throw new DAOException("Unable to connect to LexBIG - "+ ex.getMessage());
                        }
                        if(adapter == null){
                            throw new DAOException("LexBIG Exception - unable to connect to server");
                        }						
						if(fieldName.equalsIgnoreCase("metaThesaurusValues"))
						{
                            setVocabulary(defaultVocabularyName);                          
						}
						Iterator iter = mapValues.keySet().iterator();
						String key = (String)iter.next();
						methodName = key.substring(0, key.indexOf("$"));
						Method method = this.getClass().getDeclaredMethod(methodName, new Class[]{HashMap.class});
							if(method == null){
								log.error("Invalid method name");
								throw new DAOException (getException("Invalid method name"));
								}
							else{
								try{
                                    Object result = method.invoke(this, new Object[]{mapValues});
                                    response  = (Response)result;
								}
								catch(Exception ex){
									String msg = null;
									msg = "Exception in method - "+ methodName +": ";
									if(ex.getMessage()==null){
										if(pastEx.getMessage()==null){
											if(fieldName.equalsIgnoreCase("descLogicValues")){
												msg = msg + "caCORE - LexBIG Server Exception";
											}else if(fieldName.startsWith("meta")){
												msg = msg + "caCORE - Metaphrase Exception";
											}
										}else{
											msg = msg + pastEx.getMessage();
										}
									}else{
										msg = msg + ex.getMessage();
									}
									log.error(msg);
									throw new DAOException(getException(msg));
								}
							}
						}
				}
			}
		}catch(Exception e)
		{
			log.error("Exception : query - "+  e.getMessage());
			throw new DAOException (getException( e.getMessage()));
		}
		return response;
	}


     /**
 	 * Gets the descendant concept codes for the specified concept
 	 * @param map - Holds the input parameters
 	 * @return - Descendant concept codes are returned in a Response list
 	 * @throws Exception
 	 */
 	private Response getDescendants(HashMap map) throws Exception
 	{
 		String vocabularyName = null;
 		String conceptCode = null;
 		boolean flag = false;
 		String iBaseLineDate = null;
 		String fBaseLineDate = null;
 		Vector descendants = new Vector();
 		ArrayList list = new ArrayList();
 		try
 		{
 			//parse hashmap
 			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
 			{
 				String key = (String)iter.next();
 				String name = key.substring(key.indexOf("$")+1, key.length());

 				if(name.equalsIgnoreCase("vocabularyName"))
 					vocabularyName = (String)map.get(key);
 				else if(name.equalsIgnoreCase("conceptCode"))
 					conceptCode = (String)map.get(key);
 				else if(name.equalsIgnoreCase("flag"))
 					flag = ((Boolean)map.get(key)).booleanValue();
 				else if(name.equalsIgnoreCase("iBaseLineDate"))
 					iBaseLineDate = (String)map.get(key);
 				else if(name.equalsIgnoreCase("fBaseLineDate"))
 					fBaseLineDate = (String)map.get(key);
 			}

 			setVocabulary(vocabularyName);
 			descendants = adapter.getDescendantCodes(conceptCode, flag, stringToDate(iBaseLineDate), stringToDate(fBaseLineDate));
 			if(descendants == null){
 				//log.info("No descendants found");
 				}
 			else{

 		 		for(int i=0; i<descendants.size(); i++)
 		 		{
 		 			list.add(descendants.get(i));
 		 		}

 				}

 		}
 		catch(Exception e)
 		{
 			log.error(e.getMessage());
 			throw new DAOException (getException(e.getMessage()));
 		}


 		return new Response(list);

 	}

	/**
	 * Sets Vocabulary
	 * @param vocabularyName
	 * @throws Exception
	 */
	private void setVocabulary(String vocabularyName) throws Exception
	{  
        
        vocabulary = new Vocabulary();
        if(!StringHelper.hasValue(vocabularyName)){
        	log.error("vocabularyName cannot be null");
        	throw new DAOException(getException(" vocabularyName cannot be null"));
		}
		if(vocabularyName.equals("MedDRA")){
            SecurityToken accessToken = null;
            if(tokenCollection.size()>0){
                accessToken = (SecurityToken)tokenCollection.get(vocabularyName);
            }
            if(accessToken.getAccessToken() == null){
                throw new DAOException("LexBIG Exception - MedDRA token not found - Permission denied");
            }
            MSSOUserValidator validator = new MSSOUserValidator();
            String code = validator.validateID(accessToken.getAccessToken());
        }


            boolean found = false;
            try{
                found = adapter.setVocabularyName(vocabularyName);
            }catch(Exception ex){
                ex.printStackTrace();
                throw new DAOException("Vocabulary error: "+ ex.getMessage()+ vocabularyName);
            }



            if(!found){
                throw new DAOException("LexBIG Exception - unable to connect to vocabulary "+ vocabularyName);
            }
           vocabulary = populateVocabulary(vocabularyName);
	}

    private gov.nih.nci.evs.domain.Vocabulary populateVocabulary(String vocabularyName){
        gov.nih.nci.evs.domain.Vocabulary vocab = new gov.nih.nci.evs.domain.Vocabulary();        
        vocab.setName(vocabularyName);
        vocab.setDescription(adapter.getVocabularyDescription(vocabularyName));
       	try{
			vocab.setNamespaceId(adapter.getNamespaceId(vocabularyName.trim()));
		}
		catch(Exception ex){
			}

        return vocab;
    }

    private Response getAllVocabularies(HashMap map){
        List vocabList = new ArrayList();
        Vector names = adapter.getVocabularyNames();
        for(int i=0; i<names.size(); i++){
        	try{
				setVocabulary((String)names.get(i));
			}
			catch(Exception ex){}

            vocabList.add(vocabulary);
        }
        return new Response(vocabList);
    }
    private Response getVocabularyByName(HashMap map) throws Exception{
        String vocabularyName = null;
        Vocabulary vocab = new Vocabulary();
        List vocabList = new ArrayList();

        try{
            for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
            {
                String key = (String)iter.next();
                String name = key.substring(key.indexOf("$")+1, key.length());
                if(name.equalsIgnoreCase("VocabularyName"))
                    vocabularyName = (String)map.get(key);
            }
            if(vocabularyName != null){
				if(vocabularyName.indexOf("*")>-1){
					String searchString = null;
					if(vocabularyName.length() == 1){
						return getAllVocabularies(new HashMap());
						}
					else if(vocabularyName.length() > 1){
						if(vocabularyName.endsWith("*")){
							searchString = vocabularyName.substring(0,vocabularyName.indexOf("*"));
							Vector names = adapter.getVocabularyNames();
							for(int i=0; i<names.size(); i++){
								String vocabName = (String)names.get(i);
								if(vocabName.toLowerCase().startsWith(searchString.toLowerCase())){
									setVocabulary(vocabName);
									vocabList.add(vocabulary);
									}
								}
						}
						else if(vocabularyName.startsWith("*")){
							searchString = vocabularyName.substring(1);							
							Vector names = adapter.getVocabularyNames();
							for(int i=0; i<names.size(); i++){
								String vocabName = (String)names.get(i);
								if(vocabName.toLowerCase().endsWith(searchString.toLowerCase())){
									setVocabulary(vocabName);
									vocabList.add(vocabulary);
									}
								}
							}
						}

			}
			else{
				setVocabulary(vocabularyName);
				vocabList.add(vocabulary);
			}

            }

        }catch(Exception ex){
            throw new DAOException(getException(ex.getMessage()));
        }
        return new Response(vocabList);
    }

	/**
	 * Performs a search for a DescLogicConcept in the LexBIG server and returns a response
	 * @param map Specifies the search criteria values
	 * @return Returns a response that holds a list of DescLogicConcept Objects
	 * @throws Exception
	 */
	private Response getDescLogicConcept(HashMap map) throws Exception
	{


		String vocabularyName = null;
		String conceptName = null;
		boolean inputFlag =  false;

		Vector v = new Vector();
		ArrayList list = new ArrayList();

		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String)map.get(key);
				else if(name.equalsIgnoreCase("conceptName"))
					conceptName = (String)map.get(key);
				else if(name.equalsIgnoreCase("inputFlag"))
					inputFlag = ((Boolean)map.get(key)).booleanValue();

			}

			setVocabulary(vocabularyName);
			boolean valid = true;
		DescLogicConcept dlc = new DescLogicConcept();
		Concept concept = new Concept();

	       if(!inputFlag){
	       		validateConceptName(conceptName);
	       	}
	       else{
	       		validateDLConceptCode(conceptName);

	       	}
	       dlc = buildDescLogicConcept(adapter.getConcept(conceptName, inputFlag, 1));
	       //dlc = buildDescLogicConcept(concept);
	       list.add(dlc);




		}catch(Exception e){
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}


				return new Response(list);
	}

	/**
	 * Performs a search for DescLogicConcepts in the adapter server and returns a response
	 * @param map Specifies the search criteria values
	 * @return Returns a response that holds a list of DescLogicConcept Objects
	 * @throws Exception
	 */
	private Response searchDescLogicConcepts(HashMap map) throws Exception
	{
		String vocabularyName = null;
		String searchTerm = null;
		int limit = 1;
		int matchOption = 0;
		String matchType = "";
		int ASDIndex = 1;
		Vector v = new Vector();
		ArrayList list = new ArrayList();
		try
		{
			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());
				if(name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String)map.get(key);
				else if(name.equalsIgnoreCase("SearchTerm"))
					searchTerm = (String)map.get(key);
				else if(name.equalsIgnoreCase("matchLimit"))
					limit = ((Integer)map.get(key)).intValue();
				else if(name.equalsIgnoreCase("matchOption"))
					matchOption = ((Integer)map.get(key)).intValue();
				else if(name.equalsIgnoreCase("matchType"))
					matchType = (String)map.get(key);
				else if(name.equalsIgnoreCase("ASDIndex"))
					ASDIndex = ((Integer)map.get(key)).intValue();
			}
			setVocabulary(vocabularyName);
	        if(!StringHelper.hasValue(searchTerm)){
	        	log.warn("searchTerm cannot be null");
	        	throw new DAOException(getException(" searchTerm cannot be null"));
	        }
	        Concept[] concepts = null;
	    
	        try{
	        	concepts = adapter.searchConcepts(searchTerm, limit, matchOption, matchType,ASDIndex);
	        }catch(Exception ex){	        	
	        	if(matchOption==0){
	        		concepts = adapter.searchConcepts(searchTerm, limit);
		        }
	        	else{
	        		throw new Exception("Exception in method call searchDescLogicConcept "+ex.getMessage());
	        	}
	        }
	        
	        
	       
	       	
	        
            if(vocabulary == null){
                populateVocabulary(vocabularyName);
            }
	        DescLogicConcept[] dlc = new DescLogicConcept[concepts.length];
	        for(int x=0; x<concepts.length; x++){
	            dlc[x] = new DescLogicConcept();
	            dlc[x] = buildDescLogicConcept(concepts[x]);
	            list.add(dlc[x]);
	        }
		}catch(Exception e){
		    	log.error(e.getMessage());
			throw new DAOException(getException("SearchConcepts..."+e.getMessage()));
		}
		return new Response(list);
	}

	/**
	 * get a DefaultMutableTreeNode for a given DescLogicConcept
	 * @param map - Specifies the input parameters
	 * @return - Returns a Response that holds a DefaultMutableTreeNode in a list
	 * @throws Exception
	 */
	public Response getTree(HashMap map) throws Exception
	{
		List list = new ArrayList();
		String vocabularyName = null;
		String rootName = null;
		boolean direction = false;
		boolean isaFlag = false;
		String levels = null;
		Vector roles = null;
		int attributes = 1;
		DefaultMutableTreeNode tree = null;
		try
		{
			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String)map.get(key);
				else if(name.equalsIgnoreCase("RootName"))
					rootName = (String)map.get(key);
				else if(name.equalsIgnoreCase("direction"))
					direction = ((Boolean)map.get(key)).booleanValue();
				else if(name.equalsIgnoreCase("isaFlag"))
					isaFlag = ((Boolean)map.get(key)).booleanValue();
				else if(name.equalsIgnoreCase("attributes"))
					attributes = ((Integer)map.get(key)).intValue();
				else if(name.equalsIgnoreCase("levels"))
					levels = (String)map.get(key);
				else if(name.equalsIgnoreCase("roles"))
					roles = (Vector)map.get(key);
			}
			tree = getTree(vocabularyName, rootName, direction, isaFlag, attributes, levels, roles);

			if(tree != null){
				list.add(tree);
				}


		}catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException(getException(" Exception in getTree: "+e.getMessage()));
		}

		return new gov.nih.nci.common.net.Response(list);
	}

	/**
	 * Generates a DefaultMutableTreeNode for the specified values
	 * @param vocabularyName
	 * @param rootName
	 * @param direction
	 * @param isaFlag
	 * @param atributes
	 * @param levels
	 * @param roles
	 * @return Returns a DefaultMutableTreeNode object
	 * @throws Exception
	 */
private DefaultMutableTreeNode getTree(String vocabularyName, String rootName, boolean direction,
			                              boolean isaFlag, int attributes, String levels, Vector roles) throws Exception
    {
    DefaultMutableTreeNode evsTree = new DefaultMutableTreeNode();
        if(!StringHelper.hasValue(vocabularyName)){
        	log.error("vocabularyName cannot be null");
        	throw new DAOException(getException(" vocabularyName cannot be null"));
        }

        if(!StringHelper.hasValue(rootName)){
        	log.error("Root Node cannot be null");
        	throw new DAOException(getException(" Root Node cannot be null"));
        }

		setVocabulary(vocabularyName);
        int namespace = adapter.getNamespaceId(vocabularyName);
        DefaultMutableTreeNode tree = null;

        Vector v  = null;
        try
        {
        	int depthLevel = -1;
			if(levels != null)
			{
				if(levels.equalsIgnoreCase("ALL"))
					depthLevel = -1;
                else
                	depthLevel = (new Integer(levels)).intValue();
            }

            gov.nih.nci.lexrpc.client.AttributeSetDescriptor attrib = gov.nih.nci.lexrpc.client.AttributeSetDescriptor.ALL_ATTRIBUTES;

		  	if(attributes == gov.nih.nci.evs.domain.AttributeSetDescriptor.WITH_ALL_ATTRIBUTES)
		  		attrib = gov.nih.nci.lexrpc.client.AttributeSetDescriptor.ALL_ATTRIBUTES;
		  	else if(attributes == gov.nih.nci.evs.domain.AttributeSetDescriptor.WITH_ALL_PROPERTIES)
		  		attrib = gov.nih.nci.lexrpc.client.AttributeSetDescriptor.ALL_PROPERTIES;
		  	else if(attributes == gov.nih.nci.evs.domain.AttributeSetDescriptor.WITH_ALL_ROLES)
		  		attrib = gov.nih.nci.lexrpc.client.AttributeSetDescriptor.ALL_ROLES;
		  	else if(attributes == gov.nih.nci.evs.domain.AttributeSetDescriptor.WITH_NO_ATTRIBUTES)
		  		attrib = gov.nih.nci.lexrpc.client.AttributeSetDescriptor.NO_ATTRIBUTES;

		  	try
			{
				setVocabulary(vocabularyName);

				if((roles != null) && (roles.size() > 0)){
				   tree = adapter.getTree(rootName, direction, depthLevel, attrib, isaFlag, roles);

				}
				else {
					tree = adapter.getTree(rootName, direction, depthLevel, attrib);
				}

                if(tree == null){
                    throw new DAOException(getException("LexBIG Exception"));

                }

                DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getRoot();
                Concept rootConcept = (Concept)root.getUserObject();
                DescLogicConcept dlRoot = buildDescLogicConcept(rootConcept);


                DefaultMutableTreeNode dlTree = new DefaultMutableTreeNode(buildDescLogicConcept(rootConcept), true);
                int childCounter = tree.getChildCount();

                if(root.getChildCount()>0){
                    dlTree = getChildNodes(dlTree, root);
                }
                evsTree = dlTree;

			}
		  	catch(Exception e)
			{
		  		log.error(e.getMessage());
		  		throw new DAOException (getException("Exception - LexBIG call: \n" + e.getMessage()));
			}

		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException( e.getMessage()));
        }
		return evsTree;
    }

public DefaultMutableTreeNode getChildNodes(DefaultMutableTreeNode dlNode, DefaultMutableTreeNode dtsrpcNode){
    int childCounter = dtsrpcNode.getChildCount();
    for(int i=0; i<childCounter; i++){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        DefaultMutableTreeNode conceptNode = (DefaultMutableTreeNode)dtsrpcNode.getChildAt(i);
        Concept childConcept = (Concept)conceptNode.getUserObject();
        DescLogicConcept dlc = new DescLogicConcept();
        dlc = buildDescLogicConcept(childConcept);

        Vector vecProperties = childConcept.getProperties();


        node.setUserObject(dlc);
        if(conceptNode.getChildCount()>0){
            node = getChildNodes(node, conceptNode);
        }
        dlNode.add(node);

    }
    return dlNode;
}

/**
 * Gets all the concept names for a given property
 * @param map - Specifies the input parameters
 * @return Returns a Response with a list of concept names
 * @throws Exception
 */

private Response getConceptWithPropertyMatching(HashMap map) throws Exception
{
	String vocabularyName = null;
	String propertyName = null;
	String propertyValue = null;
	int limit = 1;
	Vector v = new Vector();
	ArrayList list = new ArrayList();

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("propertyName"))
				propertyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("propertyValue"))
				propertyValue = (String)map.get(key);
			else if(name.equalsIgnoreCase("matchLimit"))
				limit = ((Integer)map.get(key)).intValue();
		}


		setVocabulary(vocabularyName);

		v=adapter.findConceptsWithPropertyMatching(propertyName, propertyValue, limit);

		if(v != null){

			for(int i=0; i<v.size(); i++)
			{
				list.add(v.get(i));
			}

			}

	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}


	return (new Response(list));
}


private Response getConceptWithSiloMatching(HashMap map) throws Exception
{

	String vocabularyName = null;
	String searchTerm = null;
	String siloName = null;
	int limit = 10;
	Vector v = new Vector();
	ArrayList list = new ArrayList();

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("searchTerm"))
				searchTerm = (String)map.get(key);
			else if(name.equalsIgnoreCase("siloName"))
				siloName = (String)map.get(key);
			else if(name.equalsIgnoreCase("matchLimit"))
				limit = ((Integer)map.get(key)).intValue();
		}



		setVocabulary(vocabularyName);

		v=adapter.findConceptsWithSiloMatching(searchTerm, limit);

		if(v != null){

			for(int i=0; i<v.size(); i++)
			{
				list.add((String)v.get(i));
			}

			}

	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}


	return (new Response(list));
}


/**
 * gets the concept name for the given concept code
 * @param map
 * @return Response object that holds the concept name in a list
 * @throws Exception
 */

private Response getDescLogicConceptNameByCode(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	String conceptName = null;
	List conceptList = new ArrayList();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String)map.get(key);
		}

		setVocabulary(vocabularyName);

		conceptName = adapter.getConceptNameByCode(conceptCode);
		if(conceptName != null){
			conceptList.add(conceptName);
		}


	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}


	return (new Response(conceptList));
}


/**
 * Checks if the first concept is a subconcept of the second concept
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a Boolean value in a list
 * @throws Exception
 */
private Response isSubConcept(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptName1 = null;
	String conceptName2 = null;
	Boolean isSub = Boolean.FALSE;
	List isSubList = new ArrayList();
	try
	{

		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode1"))
				conceptName1 = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode2"))
				conceptName2 = (String)map.get(key);
		}


		setVocabulary(vocabularyName);
		this.validateConceptName(conceptName1);
        this.validateConceptName(conceptName2);
		isSub=adapter.isChild(conceptName1,conceptName2);
        if(isSub==null){
            isSub = false;
        }
		isSubList.add(isSub);
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}




	return (new Response(isSubList));

}


/**
 * Checks if the specified concept is retired or not.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a Boolean value in a list
 * @throws Exception
 */
private Response isRetired(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	Boolean retired = Boolean.FALSE;
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String)map.get(key);
		}
		setVocabulary(vocabularyName);
		retired = adapter.isRetired(conceptCode);
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
	List isList = new ArrayList();
	isList.add(retired);
	return (new Response(isList));

}


/**
 * Gets the values of a property owned by a concept
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a the property values in a list
 * @throws Exception
 */
private Response getPropertyValues(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptName = null;
	String propertyName = null;
	Vector values = new Vector();
	ArrayList list = new ArrayList();

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);
			else if(name.equalsIgnoreCase("propertyName"))
				propertyName = (String)map.get(key);
		}

		setVocabulary(vocabularyName);

		values = adapter.getPropertyValues(conceptName, propertyName);

		if(values != null){
			for(int i=0; i<values.size(); i++)
			{
				list.add(values.get(i));
			}

			}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}



	return (new Response(list));

}


/**
 * Gets the ancestor concept codes of the specified concept.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of concept codes
 * @throws Exception
 */
private Response getAncestors(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	boolean flag = false;
	String iBaseLineDate = null;
	String fBaseLineDate = null;
	Vector ancestors = new Vector();
	ArrayList list = new ArrayList();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String)map.get(key);
			else if(name.equalsIgnoreCase("flag"))
				flag = ((Boolean)map.get(key)).booleanValue();
			else if(name.equalsIgnoreCase("iBaseLineDate"))
				iBaseLineDate = (String)map.get(key);
			else if(name.equalsIgnoreCase("fBaseLineDate"))
				fBaseLineDate = (String)map.get(key);
		}

		setVocabulary(vocabularyName);

		ancestors = adapter.getAncestorCodes(conceptCode, flag, stringToDate(iBaseLineDate), stringToDate(fBaseLineDate));

		if(ancestors != null){
			for(int i=0; i<ancestors.size(); i++)
			{
				list.add(ancestors.get(i));
			}

		}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}




	return (new Response(list));
}



/**
 * Gets the codes or the names, of all subconcepts of the specified concept
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of conceptNames or codes
 * @throws Exception
 */
private Response getSubConcepts(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptName = null;
	Boolean inputFlag = null;
	Boolean outputFlag = null;
	ArrayList list = new ArrayList();

	Vector subConcepts = new Vector();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);
			else if(name.equalsIgnoreCase("inputFlag"))
				inputFlag = (Boolean)map.get(key);
			else if(name.equalsIgnoreCase("outputFlag"))
				outputFlag = (Boolean)map.get(key);
		}

		setVocabulary(vocabularyName);

		subConcepts = adapter.getSubConcepts(conceptName, inputFlag, outputFlag);

		if(subConcepts != null){
			for(int i=0; i<subConcepts.size(); i++)
			{
				list.add(subConcepts.get(i));
			}

			}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}




	return (new Response(list));

}


/**
 * Gets the codes (or the names) of all superconcepts of the specified concept.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of super concept names/codes
 * @throws Exception
 */
private Response getSuperConcepts(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptName = null;
	Boolean inputFlag = null;
	Boolean outputFlag = null;

	Vector superConcepts = new Vector();
	ArrayList list = new ArrayList();
	try
	{

		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);
			else if(name.equalsIgnoreCase("inputFlag"))
				inputFlag = (Boolean)map.get(key);
			else if(name.equalsIgnoreCase("outputFlag"))
				outputFlag = (Boolean)map.get(key);
		}


		setVocabulary(vocabularyName);

		superConcepts = adapter.getSuperConcepts(conceptName, inputFlag, outputFlag);

		if(superConcepts.size()>0){
			for(int i=0; i<superConcepts.size(); i++)
			{
				list.add(superConcepts.get(i));
			}

		}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException( e.getMessage()));
	}




	return (new Response(list));

}


/**
 * Gets the roles owned by the specified concept.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of roles
 * @throws Exception
 */
private Response getRolesByConceptName(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptName = null;

	Vector roles = new Vector();
	ArrayList list = new ArrayList();

	try
	{

		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);
		}

		setVocabulary(vocabularyName);

		if(!StringHelper.hasValue(conceptName))
        	throw new DAOException("conceptName cannot be null");

		roles = adapter.getRolesByConceptName(conceptName);
		if(roles != null){

			for(int i=0; i<roles.size(); i++)
			{
				String elem = (String) roles.get(i);
				StringTokenizer st = new StringTokenizer(elem, "$");
				gov.nih.nci.evs.domain.Role role = new gov.nih.nci.evs.domain.Role();
				role.setName(st.nextToken());
				role.setValue(st.nextToken());
				list.add(role);
			}

			}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}


	return (new Response(list));

}

/**
 * Gets the names and values of properties owned by the specified concept.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of properties
 * @throws Exception
 */
private Response getPropertiesByConceptName(HashMap map) throws Exception
{

	String vocabularyName = null;
	String conceptName = null;
	ArrayList list = new ArrayList();

	Vector properties = new Vector();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);
		}


		setVocabulary(vocabularyName);

		if(!StringHelper.hasValue(conceptName))
        	throw new DAOException(getException("conceptName cannot be null"));


		properties = adapter.getPropertiesByConceptName(conceptName);

		if(properties == null){
			log.info("No properties found....");
			}
		else{
			for(int i=0; i<properties.size(); i++)
			{
                String propertyString = (String)properties.get(i);
                if(propertyString != null && propertyString.indexOf("$")>0){
                    gov.nih.nci.evs.domain.Property property = new gov.nih.nci.evs.domain.Property();
                    property.setName(propertyString.substring(0,propertyString.indexOf("$")));
                    property.setValue(propertyString.substring(propertyString.indexOf("$")+1));
                    list.add(property);
                }

			}

			}




	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}



	return (new Response(list));

}



/**
 * Gets vocabulary names from EVS
 * @return - Returns a response that holds a list of vocabulary names
 * @throws Exception
 */
private Response getVocabularyNames(HashMap map) throws Exception
{
	Vector vocabularies = new Vector();
	ArrayList list = new ArrayList();
	try
	{
		vocabularies = adapter.getVocabularyNames();
		for(int i=0; i<vocabularies.size(); i++)
		{
			list.add(vocabularies.get(i));
		}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
	return (new Response(list));
}



/**
 * Gets the code of the specified concept.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of codes
 * @throws Exception
 */
private Response getConceptCodeByName(HashMap map) throws Exception
{

	String vocabularyName = null;
	String conceptName = null;
	String conceptCode = null;
	List conceptList = new ArrayList();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);
		}


		setVocabulary(vocabularyName);
		if(!StringHelper.hasValue(conceptName)){
			String msg = "conceptName cannot be null";
			log.error(msg);
			throw new DAOException(msg);
		}


		conceptCode = adapter.getConceptCodeByName(conceptName);

		if(conceptCode != null){
			conceptList.add(conceptCode);

		}
		else{
			//log.info("No conceptCode found for "+conceptName);
		}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}



	return (new Response(conceptList));

}



/**
 * Gets the host of a vocabulary server
 * @param map - Specifies the input parameters
 * @return - Returns a response
 * @throws Exception
 */
private Response getVocabularyHost(HashMap map) throws Exception
{
	String vocabularyName = null;
	String host = null;

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
		}

		setVocabulary(vocabularyName);
		host = adapter.getVocabularyHost(vocabularyName);
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException( e.getMessage()));
	}

	List hostList = new ArrayList();
	hostList.add(host);
	return (new Response(hostList));

}




/**
 * Gets the port of a vocabulary server
 * @param map
 * @return returns a response that holds the vocabulary server port number
 * @throws Exception
 */
private Response getVocabularyPort(HashMap map) throws Exception
{
	String vocabularyName = null;
	String port = null;

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
		}

		setVocabulary(vocabularyName);

		port = adapter.getVocabularyPort(vocabularyName);
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException( e.getMessage()));
	}

	List portList = new ArrayList();
	portList.add(port);

	return (new Response(portList));

}
/**
 * Returns a list of versions for the given vocabulary in the knowledgebase.
 * @param vocabularyName
 */
public Response getVocabularyVersion(HashMap map) throws Exception{
    String vocabularyName = null;
    List version = new ArrayList();

    try
    {
        for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
        {
            String key = (String)iter.next();
            String name = key.substring(key.indexOf("$")+1, key.length());

            if(name.equalsIgnoreCase("vocabularyName"))
                vocabularyName = (String)map.get(key);
        }

        setVocabulary(vocabularyName);

       version.add(adapter.getVocabularyVersion(vocabularyName));
       log.info("vocabulary version = "+ version);

    }
    catch(Exception e)
    {
        log.error(e.getMessage());
        throw new DAOException (getException( e.getMessage()));
    }


    return (new Response(version));

}


/**
 * Gets all EditAction of the specified concept. A EditAction contains a edit date
 * and an editing action. The method searches for all editing actions and the corresponding
 * editing dates on the specified concept.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of edit actions and dates
 * @throws Exception
 */
private Response getConceptEditAction(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	Vector editActions = new Vector();
	ArrayList list = new ArrayList();
    Date editActionDate = null;
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String)map.get(key);
            else if(name.equalsIgnoreCase("editActionDate"))
                editActionDate = (Date)map.get(key);
		}

		setVocabulary(vocabularyName);

        if(editActionDate == null){
            editActions = adapter.getConceptEditAction(conceptCode);
        }
        else{
            //editActions = adapter.getConceptEditAction(conceptCode, editActionDate);
            editActions = adapter.getConceptEditAction(conceptCode);
        }
		
		if(editActions != null){
			for(int i=0; i<editActions.size(); i++)
			{                
                if(editActionDate != null){
                    StringTokenizer st = new StringTokenizer((String)editActions.get(i),"|");
                    String action = st.nextToken();
                    Date date = stringToDate(st.nextToken());
                    if(date.equals(editActionDate)){
                        list.add(action);
                    }
                }
                else{
                    list.add(editActions.get(i));
                }
               
			}
		}

	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException( e.getMessage()));
	}


	return (new Response(list));

}




/**
 * Gets EditActionDate that match with the given concept code and edit action.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of edit action dates
 * @throws Exception
 */
private Response getConceptEditActionDates(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	String action = null;
	Vector editActions = new Vector();
	ArrayList list = new ArrayList();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String)map.get(key);
			else if(name.equalsIgnoreCase("action"))
				action = (String)map.get(key);
		}

		setVocabulary(vocabularyName);

		editActions = adapter.getConceptEditActionDates(conceptCode, action);

		if(editActions != null){
			for(int i=0; i<editActions.size(); i++)
			{
				list.add(editActions.get(i));
			}
			}

	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
	return (new Response(list));

}


/**
 * Retrieves all root concepts.
 * @param map - Specifies the input parameters
 * @return - Returns a response that holds a list of DescLogicConcept objects
 * @throws Exception
 */
private Response getRootConcepts(HashMap map) throws Exception
{
	String vocabularyName = null;
	boolean allAttributes = true;

	Concept[] concepts = null;
	ArrayList list = new ArrayList();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("allAttributes"))
				allAttributes = ((Boolean)map.get(key)).booleanValue();
		}

		setVocabulary(vocabularyName);

			concepts = adapter.getRootConcepts(allAttributes);


		if(concepts.length >0){

			for(int i=0; i<concepts.length; i++)
			{
				list.add(buildDescLogicConcept(concepts[i]));
			}

			}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException( e.getMessage()));
	}


	return (new Response(list));
}


/**
 * Converts LexBIG property objects to evs property objects
 * @param nciProps - vector of LexBIG property object
 * @return Returns a vector of evs property objects
 */
private Vector convertProperties(Vector nciProps)
{
	Vector properties = new Vector();
	for(int i=0 ;i<nciProps.size(); i++)
	{
        gov.nih.nci.lexrpc.client.Property property = (gov.nih.nci.lexrpc.client.Property) nciProps.get(i);
		gov.nih.nci.evs.domain.Property p = new gov.nih.nci.evs.domain.Property();
		p.setName(property.getName());
		p.setValue(property.getValue());

		p.setQualifierCollection(convertQualifiers(property.getQualifiers()));
		properties.add(p);
	}
	return properties;
}


private Vector convertQualifiers(Vector dtsrpcQualifiers){
 	Vector qualifierVector = new Vector();
 	for(int i=0; i<dtsrpcQualifiers.size(); i++){
 	   gov.nih.nci.evs.domain.Qualifier qualifier = new gov.nih.nci.evs.domain.Qualifier();
 	   gov.nih.nci.lexrpc.client.Qualifier dtsQualifier = (gov.nih.nci.lexrpc.client.Qualifier)dtsrpcQualifiers.get(i);
 	   qualifier.setName(dtsQualifier.getName());
 	   qualifier.setValue(dtsQualifier.getValue());
 	   qualifierVector.add(qualifier);
 	    }
 	return qualifierVector;
}
/**
 * Converts dtsrpc role objects to evs role objects
 * @param nciRoles vector of dtsrpc roles
 * @return Returns a vector of evs roles
 */
private Vector convertRoles(Vector nciRoles)
{
	Vector roles = new Vector();
	for(int i=0 ;i<nciRoles.size(); i++)
	{
		gov.nih.nci.lexrpc.client.Role dtsRole = (gov.nih.nci.lexrpc.client.Role)nciRoles.get(i);
		gov.nih.nci.evs.domain.Role role = new gov.nih.nci.evs.domain.Role();
		role.setName(dtsRole.getName());
		role.setValue(dtsRole.getValue());
		roles.add(role);
	}
	return roles;
}


/**
 * Gets DescLogicConcept object for the given conceptname
 * @param map
 * @return Returns a response that holds a DescLogicConcept in a list
 * @throws Exception
 */
private Response getConceptByName(HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptName = null;
	ArrayList list = new ArrayList();
	List dlcList = new ArrayList();
	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);

		}

		setVocabulary(vocabularyName);

		if(validateConceptName(conceptName)){
			Vector conceptVector = new Vector();
			conceptVector.add(conceptName);
			DescLogicConcept dlc = buildDescLogicConcept(adapter.findConcept(conceptName));
			dlcList.add(dlc);
			}
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}


	return (new Response(dlcList));
	}

/**
 * Searches MetaThesaurus
 * @param map - specifies the input parameters
 * @return Returns a response that holds a list of MetaThesaurusConcepts
 * @throws Exception
 */
private Response searchMetaThesaurus(HashMap map) throws Exception
{

	String searchTerm = null;
	int limit = 10;
	String source = "";
	boolean cui = true;
	boolean shortResult = false;
	boolean score = false;

	ArrayList list = new ArrayList();
	List uList = new ArrayList();
	Concept[] concepts = null;
	try
	{

		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("searchTerm"))
				searchTerm = (String)map.get(key);
			else if(name.equalsIgnoreCase("limit"))
				limit = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("source")) {
			    if(map.get(key)==null){			       
			        source = "";
			        }
				source = (String)map.get(key);
				if (source.toLowerCase().equals("all sources")) {
					source = "";
				}
			}
			else if(name.equalsIgnoreCase("cui"))
				cui = ((Boolean)map.get(key)).booleanValue();
			else if(name.equalsIgnoreCase("shortResult"))
				shortResult = ((Boolean)map.get(key)).booleanValue();
			else if(name.equalsIgnoreCase("score"))
				score = ((Boolean)map.get(key)).booleanValue();
		}
		boolean checkSource = false;

		if(!source.equals("*")){            
			if(!(source.length() == 0 || source.indexOf("*") > 0)){				
                if(!validateSource(source)){
				throw new DAOException(getException("Invalid source"));
				}
				checkSource = true;
			}
		}
        if(!checkSource){
            source = "";
        }
        
		if(cui)
		{
            concepts = adapter.searchConcepts(searchTerm, limit, 9, source, 1);            
		}
		else
		{
            concepts = adapter.searchConcepts(searchTerm, limit, 0, source, 1);		    
		}
        
        boolean match = false;
        
        for(int i=0; i<concepts.length; i++){
            list.add(buildMetaThesaurusConcept(concepts[i]));
         }	          
    }
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException( e.getMessage()));
	}

	return new Response(list);
}


/**
 *
 * @param metaConcept
 * @return
 * @throws Exception
 */
private boolean validateSource(String source) throws Exception{

		boolean valid = false;
		HashMap map = new HashMap();

		try{

			List metaSources = (List)getMetaSources(map).getResponse();
			for(int i=0; i<metaSources.size(); i++){
			String sourceName = ((Source)metaSources.get(i)).getAbbreviation();
			if(source.equalsIgnoreCase(sourceName)){
				valid = true;
				}
			}
			}catch(Exception e){
			    String msg = null;
			    if(e.getMessage() == null){
			        msg = "Invalid source - "+ source;
			        }
			    else{
			        source = e.getMessage();
			        }
			throw new DAOException(getException(msg));
			}

		return valid;

	}

/**
private Response getMetaConceptCodeForSource(HashMap map) throws Exception{
    List codeList = new ArrayList();
    String abbreviation = null;
    try{
        for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
        {
            String key = (String)iter.next();
            String name = key.substring(key.indexOf("$")+1, key.length());

            if(name.equalsIgnoreCase("sourceAbbr")){
                if(map.get(key)==null){
                    throw new DAOException(getException("Invalid source"));
                    }
                abbreviation = (String)map.get(key);
            }
        }
        if(validateSource(abbreviation)){
            COM.Lexical.Metaphrase.Source metaSource = metaphrase.source(abbreviation);
            codeList.add(getMetaConceptCodeForSource(metaSource));
        }

    }catch(Exception ex){
        throw new DAOException(getException(ex.getMessage()));
    }
    return new Response(codeList);
}

private String getMetaConceptCodeForSource(COM.Lexical.Metaphrase.Source metaSource) throws Exception{
    COM.Lexical.Metaphrase.Concept metaConcept = getMetaConceptForSource(metaSource);
    return metaConcept.conceptID();
}
private COM.Lexical.Metaphrase.Concept getMetaConceptForSource(COM.Lexical.Metaphrase.Source metaSource) throws Exception{
    COM.Lexical.Metaphrase.Partition metaPartition = metaSource.partition();
    return metaPartition.concept();
}
private Response getSourceAbbreviation(HashMap map)throws Exception{
    List sabList = new ArrayList();
    String cui = null;
    try{
        for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
        {
            String key = (String)iter.next();
            String name = key.substring(key.indexOf("$")+1, key.length());

            if(name.equalsIgnoreCase("cui")){
                cui = (String)map.get(key);
            }
            for(Enumeration e = metaphrase.getSources();e.hasMoreElements();){
                COM.Lexical.Metaphrase.Source metaSource = (COM.Lexical.Metaphrase.Source)e.nextElement();
                if(cui.equals(getMetaConceptCodeForSource(metaSource))){
                    sabList.add(metaSource.SAB());
                }
            }
        }

    }catch(Exception ex){
        throw new DAOException(getException(ex.getMessage()));
    }
    return new Response(sabList);

}
*/

/**
 * Converts gov.nih.nci.lexrpc.client.Concept to gov.nih.nci.evs.domain.MetaThesaurusConcept
 * @param metaConcept
 * @return Returns a MetaThesaurusConcept object
 */
private MetaThesaurusConcept buildMetaThesaurusConcept(Concept metaConcept) throws Exception
{
	MetaThesaurusConcept metaThesaurusConcept = new MetaThesaurusConcept();
	try
	{

		metaThesaurusConcept.setName(metaConcept.getName());
		metaThesaurusConcept.setCui(metaConcept.getCode());        

        ArrayList<Definition> definitionsCollection = new ArrayList();
        ArrayList<SemanticType> semanticTypesCollection = new ArrayList();
        ArrayList<Atom> atomCollection = new ArrayList();
        ArrayList<Source> sourceCollection = new ArrayList();
        ArrayList synonymCollection = new ArrayList();
       
        Vector propertyCollection = metaConcept.getPropertyCollection();
        try{
            for(int p=0; p<propertyCollection.size(); p++){
                Property property = (Property) propertyCollection.get(p);                
                if(property.getName().toUpperCase().equalsIgnoreCase("FULL_SYN")){
                    gov.nih.nci.evs.domain.Atom atom = new gov.nih.nci.evs.domain.Atom();
                    atom.setName(property.getValue());
                    Vector qCollection = property.getQualifierCollection();
                    gov.nih.nci.evs.domain.Source source = new gov.nih.nci.evs.domain.Source();                
                    for(int q=0; q< qCollection.size(); q++){
                        Qualifier qualifier = (Qualifier)qCollection.get(q);
                        if(qualifier.getName().toLowerCase().equalsIgnoreCase("source")){
                            source.setAbbreviation(qualifier.getValue());                        
                        }else if(qualifier.getName().toLowerCase().equalsIgnoreCase("source-code")){
                            source.setCode(qualifier.getValue());                        
                        }                    
                    }
                    atom.setSource(source);
                    sourceCollection.add(source);        
                    synonymCollection.add(property.getValue());  
                    atomCollection.add(atom);
                }else if(property.getName().toUpperCase().equalsIgnoreCase("SEMANTIC_TYPE")){
                    SemanticType sType = new SemanticType();
                    sType.setName(property.getValue());
                    semanticTypesCollection.add(sType);                
                }else if(property.getName().toUpperCase().equalsIgnoreCase("DEFINITION")){
                    Definition definition = new Definition();
                    definition.setDefinition(property.getValue());
                    Vector qCollection = property.getQualifierCollection();
                    gov.nih.nci.evs.domain.Source source = new gov.nih.nci.evs.domain.Source();                
                    for(int q=0; q< qCollection.size(); q++){
                        Qualifier qualifier = (Qualifier)qCollection.get(q);
                        if(qualifier.getName().toLowerCase().equalsIgnoreCase("source")){
                            source.setAbbreviation(qualifier.getValue());                        
                        }else if(qualifier.getName().toLowerCase().equalsIgnoreCase("source-code")){
                            source.setCode(qualifier.getValue());                      
                        }                    
                    }
                    definition.setSource(source);
                    definitionsCollection.add(definition);             
                    sourceCollection.add(source);
                }else if(property.getName().toUpperCase().equalsIgnoreCase("SOURCE")){
                    Source source = new Source();  
                    source.setAbbreviation(property.getValue());
                    sourceCollection.add(source);                
                }
            }
           
            
        }catch(Exception ex){
            throw new Exception(getException("\nException: buildMetaConcept: "+ ex.getMessage()));
        }
        metaThesaurusConcept.setSemanticTypeCollection(semanticTypesCollection);        
	    metaThesaurusConcept.setSynonymCollection((ArrayList)synonymCollection);
        metaThesaurusConcept.setDefinitionCollection(definitionsCollection);
	    metaThesaurusConcept.setSourceCollection(sourceCollection);
	    metaThesaurusConcept.setAtomCollection(atomCollection);


	}catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}

	return metaThesaurusConcept;
}



  /**
   * Gets sources from metaphrase for the given metaphraseConcept
   * @param metaphraseConcept
   * @return List of Sources
   * @throws Exception
   */
  private ArrayList getSources(Concept metaphraseConcept)
  throws Exception
  {
    ArrayList sourceList = new ArrayList();
  	try
	{
       sourceList = buildMetaThesaurusConcept(metaphraseConcept).getSourceCollection();
	}
	catch(COM.Lexical.Metaphrase.MetaphraseException e)
	{
		log.error(e.getMessage());
		throw new DAOException(getException("Exception from Metaphrase Server while getting sources \n"+ e.getMessage()));
	}
	return sourceList;
  }



  /**
   * Gets synonyms for the given concept from metaphrase
   * @param metaConcept
   * @return Returns a list of Synonyms
   * @throws Exception
   */
  private ArrayList getSynonyms(Concept metaConcept) throws Exception
  {
    ArrayList synonyms = new ArrayList();
  	try
	{
       synonyms = buildMetaThesaurusConcept(metaConcept).getSynonymCollection();
	}
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException(getException( e.getMessage()));
	}
	return synonyms;
  }


	/**
	 * Search by LoincId in the specified source
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private Response searchSourceByCode(HashMap map) throws Exception
	{
		String code = null;
		String sourceAbbr = null;
        int limit = 100;

		ArrayList list = new ArrayList();
		Vector metaConcepts = new Vector();
		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("code"))
					code = (String)map.get(key);
				else if(name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String)map.get(key);
                else if(name.equalsIgnoreCase("limit"))
                    limit = ((Integer)map.get(key)).intValue();
			}


			if(!StringHelper.hasValue(code))
				  throw new DAOException(getException(" invalid acode"));

			if(!validateSource(sourceAbbr)){
			    throw new DAOException ("invalid source abbreviation - "+ sourceAbbr);
			    }


            metaConcepts = adapter.findConceptsWithSourceCodeMatching(sourceAbbr, code, limit);
            for(int i=0; i<metaConcepts.size(); i++ ){
                list.add(this.buildMetaThesaurusConcept((Concept)metaConcepts.get(i)));
            }


		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}

		return new Response(list);
	}



	/**
	 * Gets all semantic types from metaphrase
	 * @param map
	 * @return Returns a response that holds a list of SemanticTypes
	 * @throws Exception
	 */
	private Response getSemanticTypes(HashMap map) throws Exception
	{
		ArrayList list = new ArrayList();
        setVocabulary(defaultVocabularyName);
		try
		{
		    HashMap types = adapter.getSemanticTypes();
		    for(Iterator i = types.keySet().iterator(); i.hasNext();){
                String key = (String) i.next();
		        SemanticType semanticType = new SemanticType();
                semanticType.setId(key);
                semanticType.setName((String)types.get(key));
                list.add(semanticType);
            }
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException("Exception occured at : \n"+getClass().getName()+ e.getMessage()));
		}

		return (new Response(list));
	}



	/**
	 * Gets MetaThesaurusConcepts for the specified source
	 * @param map
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getConceptsBySource(HashMap map) throws Exception
	{
		ArrayList list = new ArrayList();
		String sourceAbbr = null;
        String conceptCode = null;
		Vector metaConcepts = new Vector();
		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String)map.get(key);
                if(name.equalsIgnoreCase("conceptCode"))
                    conceptCode = (String)map.get(key);
			}

			if(!StringHelper.hasValue(sourceAbbr))
				  throw new DAOException(getException(" Invalid Source"));

            if(conceptCode == null){
                throw new Exception("Please specify conceptCode");
            }
     /** check this ***/
			metaConcepts = adapter.findConceptsWithSourceCodeMatching(sourceAbbr, conceptCode, 1);
            for(int i=0; i<metaConcepts.size(); i++){
                Concept concept = (Concept)metaConcepts.get(i);
                list.add(this.buildMetaThesaurusConcept(concept));
                
            }
			
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}

		return (new Response(list));
	}


	/**
	 * Gets MetaThesaurusConcept name
	 * @param map
	 * @return Returns a response that holds a MetaThesaurusConcept in a list
	 * @throws Exception
	 */
	private Response getMetaConceptNameByCode(HashMap map) throws Exception
	{
		ArrayList list = new ArrayList();
		String conceptCode = null;
		Concept metaConcept = null;
		String conceptName = null;
		List conceptList = new ArrayList();

		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String)map.get(key);
			}

			if(!StringHelper.hasValue(conceptCode))
				  throw new DAOException(getException(" Invalid concept code"));


			metaConcept =	adapter.findConceptByCode(conceptCode, 1);
			conceptList.add(metaConcept.getName());


		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}



		return (new Response(conceptList));
	}



	/**
	 * Gets all MetaThesaurus sources
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private Response getMetaSources(HashMap map) throws Exception
	{
        ArrayList list = new ArrayList();
       // setVocabulary(defaultVocabularyName);
		try
		{            
	 		Vector sources= adapter.getSupportedSources();	  		
	  		for(int i=0; i< sources.size(); i++ )
			{                
                Source source =  new Source();
                source.setAbbreviation((String)sources.get(i));
				list.add(source);
			}

		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException( e.getMessage()));
		}

		return (new Response(list));
	}


	/**
	 * Gets children of the specified concept in the specified source
	 * @param map Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getChildren(HashMap map) throws Exception
	{
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;
		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String)map.get(key);
				else if(name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String)map.get(key);
			}
			//list = getRelatedConcepts(conceptCode, sourceAbbr, "isa");
            list = getRelatedConcepts(conceptCode, sourceAbbr, "CHD");

		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException( e.getMessage()));
		}

		return (new Response(list));
	}


	/**
	 * Gets  Parent concepts for the specified concept in a given source
	 * @param map Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getParent(HashMap map) throws Exception
	{
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;
		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String)map.get(key);
				else if(name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String)map.get(key);
			}


			//list = getRelatedConcepts(conceptCode, sourceAbbr, "inverse_isa");
            list = getRelatedConcepts(conceptCode, sourceAbbr, "PAR");

		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}

		return (new Response(list));
	}



	 /**
	  * Retrievs MetaThesaurusConcepts based on the specified code and relation in a given source
	  * @param code
	  * @param source
	  * @param relation
	  * @return
	  * @throws Exception
	  */
   private ArrayList getRelatedConcepts(String conceptCode, String sourceAbbr, String relation) throws Exception
	 {
		 ArrayList uniqueList = new ArrayList();
         Set list = new HashSet();         
         Vector rc = new Vector();


	  	try
	  	{
			if(!StringHelper.hasValue(conceptCode))
			{
				throw new DAOException(getException(" Invalid code, set concept code"));
			}
			if(!StringHelper.hasValue(sourceAbbr))
			{
				sourceAbbr = "*";
			}
            adapter.setVocabulary("NCI_Thesaurus");
            if(sourceAbbr.equals("*")){
                if(relation.equalsIgnoreCase("RN") || relation.toUpperCase().equalsIgnoreCase("PAR")){
                    rc = adapter.getRelatedConcepts(conceptCode, false, relation);
                }
                else if(relation.equalsIgnoreCase("CHD") || relation.toUpperCase().equalsIgnoreCase("RB")){
                    rc = adapter.getRelatedConcepts(conceptCode, true, relation);
                }
                else{
                    rc = adapter.getRelatedConcepts(conceptCode, true,null);
                }
            }else{
                if(relation.equalsIgnoreCase("RN") || relation.toUpperCase().equalsIgnoreCase("PAR")){
                    rc = adapter.getRelatedConceptsBySource(conceptCode, false, relation, sourceAbbr);
                }
                else if(relation.equalsIgnoreCase("CHD") || relation.toUpperCase().equalsIgnoreCase("RB")){
                    rc = adapter.getRelatedConceptsBySource(conceptCode, true, relation, sourceAbbr);
                }else{
                    rc = adapter.getRelatedConceptsBySource(conceptCode, true, null, sourceAbbr);
                }
                
            }

			
			
            for(int i=0; i< rc.size(); i++){
                list.add(this.buildMetaThesaurusConcept((Concept)rc.get(i)));
            }
	  	}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
        
       
        for(Iterator i=list.iterator(); i.hasNext();){
            uniqueList.add(i.next());
        }


	  	return uniqueList;
	  }


   /**
    * Gets concepts more general than the specified concept
    * @param map Specifies the input parameters
    * @return Returns a response that holds a list of MetaThesaurusConcepts
    * @throws Exception
    */
   private Response getBroaderConcepts(HashMap map) throws Exception
   {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;
		Vector rb = new Vector();

		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String)map.get(key);
				else if(name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String)map.get(key);
			}
             list = getRelatedConcepts(conceptCode, sourceAbbr, "RB");
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}

		return new Response(list);
   }



   /**
    * Gets concepts more specific than the specified concept
    * @param map Specifies the input parameters
	* @return Returns a response that holds a list of MetaThesaurusConcepts
    * @throws Exception
    */
   private Response getNarrowerConcepts(HashMap map) throws Exception
   {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;		
        Vector rn = new Vector();

		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String)map.get(key);
				else if(name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String)map.get(key);

			}

             list = getRelatedConcepts(conceptCode, sourceAbbr, "RN");

		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException( e.getMessage()));
		}
		return new Response(list);
   }


   /**
    * Gets MetaThesaurusConcepts related to the specified concept
    * @param map Specifies the input parameters
	* @return Returns a response that holds a list of MetaThesaurusConcepts
    * @throws Exception
    */
   private Response getRelatedConcepts(HashMap map) throws Exception
   {
		ArrayList uniqueList = new ArrayList();
        Set list = new HashSet();

		String conceptCode = null;
		String sourceAbbr = null;
		Vector rc = new Vector();
		String relation = null;

		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String)map.get(key);
				else if(name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String)map.get(key);
				else if(name.equalsIgnoreCase("relation"))
					relation = (String)map.get(key);
			}
			
			uniqueList = this.getRelatedConcepts(conceptCode, sourceAbbr, relation);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}
		return new Response(uniqueList);
   }



   /**
    * Gets the concepts of specified category  for the given concept
    * category can be "Medications", "Procedures", "Laboratory", "Diagnosis"
    * @param map Specifies the input parameters
	* @return Returns a response that holds a list of MetaThesaurusConcepts
    * @throws Exception
    */
   private Response getConceptsByCategories(HashMap map) throws Exception
   {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String category = null;
		Concept[] metaConcepts = null;

		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String)map.get(key);
				else if(name.equalsIgnoreCase("category"))
					category = (String)map.get(key);
			}


			if(!StringHelper.hasValue(conceptCode))
				  throw new DAOException(getException(" invalid concept code "));

			if(!StringHelper.hasValue(category))
				  throw new DAOException(getException(" Invalid Category - set value"));

/**** check code ****/
            metaConcepts = adapter.searchConcepts(conceptCode, 100, 1, category, 1);
			

/*
			Cooccurrence[] categoryValues = null;
			if(category.equalsIgnoreCase("Medications"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.MED, metaphrase.defaultCooccurClassifier);
			else if(category.equalsIgnoreCase("Procedures"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.PROC, metaphrase.defaultCooccurClassifier);
			else if(category.equalsIgnoreCase("Laboratory"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.LAB, metaphrase.defaultCooccurClassifier);
			else if(category.equalsIgnoreCase("Diagnosis"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.DIAG, metaphrase.defaultCooccurClassifier);
*/


			for(int i=0; i<metaConcepts.length; i++)
			{
				list.add(buildMetaThesaurusConcept(metaConcepts[i]));
			}

		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException( e.getMessage()));
		}

		return new Response(list);
   }


   /**
    * Returns a Boolean value if the specified concept contains inverse roles
    * @param map Specifies the input parameters
	* @return Returns a response that holds a Boolean value in a list
	* @throws Exception
    */


   public Response containsInverseRole(HashMap map) throws Exception{

	String vocabularyName = null;
   	String roleName = null;
   	String roleValue = null;
   	String conceptName = null;

	Boolean inverseRole = Boolean.FALSE;

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("roleName"))
				roleName = (String)map.get(key);
			else if(name.equalsIgnoreCase("roleValue"))
				roleValue = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);

		}

		setVocabulary(vocabularyName);
		inverseRole = adapter.containsInverseRole(roleName, roleValue, conceptName);
	}catch(Exception e){
		log.error(e.getMessage());
		throw new DAOException(getException(e.getMessage()));
		}
	List booleanList = new ArrayList();
	booleanList.add(inverseRole);

   	return new Response(booleanList);

   	}


   /**
    * Returns a Boolean value if the specified concept contains  roles
    * @param map Specifies the input parameters
	* @return Returns a response that holds a Boolean value in a list
	* @throws Exception
    */
   public Response containsRole(HashMap map) throws Exception{

	String vocabularyName = null;
   	String roleName = null;
   	String roleValue = null;
   	String conceptName = null;

	Boolean inverseRole = Boolean.FALSE;

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("roleName"))
				roleName = (String)map.get(key);
			else if(name.equalsIgnoreCase("roleValue"))
				roleValue = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptName"))
				conceptName = (String)map.get(key);

		}

		setVocabulary(vocabularyName);
		inverseRole = adapter.containsRole(roleName, roleValue, conceptName);
	}catch(Exception e){
		log.error(e.getMessage());
		throw new DAOException(getException(e.getMessage()));
		}
	List booleanList = new ArrayList();
	booleanList.add(inverseRole);

   	return new Response(booleanList);

   	}


   /**
    * Fetches the properties of a given concept that belongs to the given namespace
    * @param map Specifies the input parameters
	* @return Returns a response that holds a list of properties
	* @throws Exception
    */
   private Response fetchDTSProperties(HashMap map) throws Exception
   {
   	String vocabularyName = null;
   	String term = null;
   	Vector lexProperties = new Vector();
   	List list = new ArrayList();
   	try
   	{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);
   			else if(name.equalsIgnoreCase("term"))
   				term = (String)map.get(key);
   		}

   		setVocabulary(vocabularyName);

   		lexProperties = adapter.getPropertiesByConceptName(term);

   	   	for(int i=0; i<lexProperties.size(); i++)
   	   	{
   	   		list.add(lexProperties.get(i));
   	   	}
   	}
   	catch(Exception e)
   	{
   		log.error(e.getMessage());
   		throw new DAOException (getException(e.getMessage()));
   	}


   	return (new Response(list));
   }


   /**
    * Fetches term association data for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   private Response fetchTermAssociations(HashMap map) throws Exception
   {
   	String vocabularyName = null;
   	String term = null;
   	Vector associations = new Vector();
   	List list = new ArrayList();
   	try
   	{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);
   			else if(name.equalsIgnoreCase("term"))
   				term = (String)map.get(key);
   		}

   		setVocabulary(vocabularyName);

   		associations = adapter.getFetchedConceptAssociations(vocabularyName, term);

   	   	for(int i=0; i<associations.size(); i++)
   	   	{
   	   		list.add(associations.get(i));
   	   	}
   	}
   	catch(Exception e)
   	{
   		log.error(e.getMessage());
   		throw new DAOException (getException( e.getMessage()));
   	}


   	return (new Response(list));
   }


   /**
    * Gets all association types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllAssociationTypes(HashMap map) throws Exception{

   	String vocabularyName = null;
   	Vector associations = new Vector();
   	List associationList = new ArrayList();
   	try{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);

   		}

   		setVocabulary(vocabularyName);
   		associations = adapter.getAllAssociationTypes();
   		for(int i=0 ;i<associations.size(); i++)
   		{
   			associationList.add((String)associations.get(i));
   		}
   		}catch(Exception e){
   			log.error(e.getMessage());
   			throw new DAOException(getException( e.getMessage()));
		}

	return new Response(associationList);
   	}




   /**
    * Gets all concept association qualifier types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllConceptAssociationQualifierTypes(HashMap map)throws Exception{

   	String vocabularyName = null;
   	Vector types = new Vector();
   	List typeList = new ArrayList();
   	try{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);

   		}
   		setVocabulary(vocabularyName);

   		types = adapter.getAllConceptAssociationQualifierTypes();
   		for(int i=0 ;i<types.size(); i++)
   		{

   			typeList.add(types.get(i));
   		}
   		}catch(Exception e){
   			log.error(e.getMessage());
   			throw new DAOException(getException(e.getMessage()));
		}

	return new Response(typeList);
   }



   /**
    * Gets all concept association types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllConceptAssociationTypes(HashMap map)throws Exception{
      	String vocabularyName = null;
      	Vector types = new Vector();
      	List typeList = new ArrayList();
      	try{
      		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
      		{
      			String key = (String)iter.next();
      			String name = key.substring(key.indexOf("$")+1, key.length());

      			if(name.equalsIgnoreCase("vocabularyName"))
      				vocabularyName = (String)map.get(key);

      		}
      		setVocabulary(vocabularyName);
      		types = adapter.getAllConceptAssociationTypes();
      		for(int i=0 ;i<types.size(); i++)
      		{
      			typeList.add(types.get(i));
      		}
      		}catch(Exception e){
      			log.error(e.getMessage());
      			throw new DAOException(getException( e.getMessage()));
   		}

   	return new Response(typeList);
      }



   /**
    * Gets all concept property qualifier types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllConceptPropertyQualifierTypes(HashMap map)throws Exception{
     	String vocabularyName = null;
     	Vector types = new Vector();
     	List typeList = new ArrayList();
     	try{
     		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
     		{
     			String key = (String)iter.next();
     			String name = key.substring(key.indexOf("$")+1, key.length());

     			if(name.equalsIgnoreCase("vocabularyName"))
     				vocabularyName = (String)map.get(key);

     		}
     		setVocabulary(vocabularyName);
     		types = adapter.getAllConceptPropertyQualifierTypes();
     		for(int i=0 ;i<types.size(); i++)
     		{
     			typeList.add(types.get(i));
     		}
     		}catch(Exception e){
     			log.error(e.getMessage());
     			throw new DAOException(getException( e.getMessage()));
  		}

  	return new Response(typeList);
     }



   /**
    * Gets all concept property types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllConceptPropertyTypes(HashMap map)throws Exception{

     	String vocabularyName = null;
     	Vector types = new Vector();
     	List typeList = new ArrayList();
     	try{
     		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
     		{
     			String key = (String)iter.next();
     			String name = key.substring(key.indexOf("$")+1, key.length());

     			if(name.equalsIgnoreCase("vocabularyName"))
     				vocabularyName = (String)map.get(key);

     		}
     		setVocabulary(vocabularyName);
     		types = adapter.getAllConceptPropertyTypes();
     		for(int i=0 ;i<types.size(); i++)
     		{
     			typeList.add(types.get(i));
     		}
     		}catch(Exception e){
     			log.error(e.getMessage());
     			throw new DAOException(getException(e.getMessage()));
  		}

  	return new Response(typeList);
     }



   /**
    * Gets all licenses for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllLicenses(HashMap map)throws Exception{
     	String vocabularyName = null;
     	String condition = null;
     	Vector types = new Vector();
     	List typeList = new ArrayList();
     	try{
     		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
     		{
     			String key = (String)iter.next();
     			String name = key.substring(key.indexOf("$")+1, key.length());

     			if(name.equalsIgnoreCase("vocabularyName"))
     				vocabularyName = (String)map.get(key);
     			if(name.equalsIgnoreCase("condition"))
     				condition = (String)map.get(key);

     		}
     		setVocabulary(vocabularyName);
     		types = adapter.getAllLicenses();
     		for(int i=0 ;i<types.size(); i++)
     		{
     			typeList.add(types.get(i));
     		}
     		}catch(Exception e){
     			log.error(e.getMessage());
     			throw new DAOException(getException( e.getMessage()));
  		}

  	return new Response(typeList);
     }



   /**
    * Gets all qualifier types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllQualifierTypes(HashMap map)throws Exception{
     	String vocabularyName = null;
     	Vector types = new Vector();
     	List typeList = new ArrayList();
     	try{
     		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
     		{
     			String key = (String)iter.next();
     			String name = key.substring(key.indexOf("$")+1, key.length());

     			if(name.equalsIgnoreCase("vocabularyName"))
     				vocabularyName = (String)map.get(key);


     		}
     		setVocabulary(vocabularyName);
     		types = adapter.getAllQualifierTypes();
     		for(int i=0 ;i<types.size(); i++)
     		{
     			typeList.add(types.get(i));
     		}
     		}catch(Exception e){
     			log.error(e.getMessage());
     			throw new DAOException(getException(e.getMessage()));
  		}

  	return new Response(typeList);
     }




   /**
    * Gets all the Role names for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllRoleNames(HashMap map)throws Exception{
     	String vocabularyName = null;
     	Vector types = new Vector();
     	List typeList = new ArrayList();
     	try{
     		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
     		{
     			String key = (String)iter.next();
     			String name = key.substring(key.indexOf("$")+1, key.length());

     			if(name.equalsIgnoreCase("vocabularyName"))
     				vocabularyName = (String)map.get(key);


     		}
     		setVocabulary(vocabularyName);
     		types = adapter.getAllRoleNames();
     		for(int i=0 ;i<types.size(); i++)
     		{
     			typeList.add(types.get(i));
     		}
     		}catch(Exception e){
     			log.error(e.getMessage());
     			throw new DAOException(getException("\nException occured at "+ getClass().getName()+ e.getMessage()));
  		}

  	return new Response(typeList);
     }

   public Response getAllSilos(HashMap map)throws Exception{
	   List siloList = new ArrayList();
	   return new Response(siloList);
   }


   /**
    * Gets all sub-concept codes for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllSubConceptCodes(HashMap map)throws Exception{
     	String vocabularyName = null;
     	String conceptCode = null;
     	Vector types = new Vector();
     	List typeList = new ArrayList();
     	try{
     		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
     		{
     			String key = (String)iter.next();
     			String name = key.substring(key.indexOf("$")+1, key.length());

     			if(name.equalsIgnoreCase("vocabularyName"))
     				vocabularyName = (String)map.get(key);
     			if(name.equalsIgnoreCase("conceptCode"))
     				conceptCode = (String)map.get(key);


     		}
     		setVocabulary(vocabularyName);
     		types = adapter.getAllSubConceptCodes(conceptCode);
     		for(int i=0 ;i<types.size(); i++)
     		{
     			typeList.add(types.get(i));
     		}
     		}catch(Exception e){
     			log.error(e.getMessage());
     			throw new DAOException(getException( e.getMessage()));
  		}

  	return new Response(typeList);
     }




   /**
    * Gets all sub-concept names for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllSubConceptNames(HashMap map)throws Exception{
     	String vocabularyName = null;
     	String conceptName = null;
     	Vector types = new Vector();
     	List typeList = new ArrayList();
     	try{
     		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
     		{
     			String key = (String)iter.next();
     			String name = key.substring(key.indexOf("$")+1, key.length());

     			if(name.equalsIgnoreCase("vocabularyName"))
     				vocabularyName = (String)map.get(key);
     			if(name.equalsIgnoreCase("conceptName"))
     				conceptName = (String)map.get(key);


     		}
     		setVocabulary(vocabularyName);
     		types = adapter.getAllSubConceptNames(conceptName);
     		for(int i=0 ;i<types.size(); i++)
     		{
     			typeList.add((String)types.get(i));
     		}
     		}catch(Exception e){
     			log.error(e.getMessage());
     			throw new DAOException(getException( e.getMessage()));
  		}

  	return new Response(typeList);
     }




   /**
    * Gets all synonym types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllSynonymTypes(HashMap map)throws Exception{
	        	String vocabularyName = null;
	        	Vector types = new Vector();
	        	List typeList = new ArrayList();
	        	try{
	        		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
	        		{
	        			String key = (String)iter.next();
	        			String name = key.substring(key.indexOf("$")+1, key.length());

	        			if(name.equalsIgnoreCase("vocabularyName"))
	        				vocabularyName = (String)map.get(key);

	        		}
	        		setVocabulary(vocabularyName);
	        		//types = adapter.getAllSynonymTypes();
	        		for(int i=0 ;i<types.size(); i++)
	        		{
	        			typeList.add(types.get(i));
	        		}
	        		}catch(Exception e){
	        			log.error(e.getMessage());
	        			throw new DAOException(getException(e.getMessage()));
	     		}

	     	return new Response(typeList);
   }

   /**
    * Gets all synonym types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */
/*
   public Response getAllSilos(HashMap map)throws Exception{

	        	String vocabularyName = null;
	        	List siloList = new ArrayList();
	        	try{
	        		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
	        		{
	        			String key = (String)iter.next();
	        			String name = key.substring(key.indexOf("$")+1, key.length());

	        			if(name.equalsIgnoreCase("vocabularyName"))
	        				vocabularyName = (String)map.get(key);

	        		}
	        		setVocabulary(vocabularyName);
	        		gov.nih.nci.dtsrpc.client.Silo[] silos = dtsrpc.getAllSilos();
	        		for(int i=0 ;i<silos.length; i++)
	        		{
	        			siloList.add(convertSilo(silos[i]));
	        		}
	        		}catch(Exception e){
	        			log.error(e.getMessage());
	        			throw new DAOException(getException(e.getMessage()));
	     		}

	     	return new Response(siloList);
   }
   */

   private gov.nih.nci.evs.domain.Silo convertSilo(gov.nih.nci.dtsrpc.client.Silo dtsSilo){
       gov.nih.nci.evs.domain.Silo silo = new gov.nih.nci.evs.domain.Silo();
       silo.setId(dtsSilo.getId());
       silo.setName(dtsSilo.getName());
       //log.info("Silo name = "+ silo.getName() +"\t"+ silo.getId());
       return silo;
       }



   /**
    * Gets all term association qualifier types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */


   public Response getAllTermAssociationQualifierTypes(HashMap map)throws Exception{
	        	String vocabularyName = null;
	        	Vector types = new Vector();
	        	List typeList = new ArrayList();
	        	try{
	        		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
	        		{
	        			String key = (String)iter.next();
	        			String name = key.substring(key.indexOf("$")+1, key.length());

	        			if(name.equalsIgnoreCase("vocabularyName"))
	        				vocabularyName = (String)map.get(key);

	        		}
	        		setVocabulary(vocabularyName);
	        		types = adapter.getAllConceptAssociationQualifierTypes();
	        		for(int i=0 ;i<types.size(); i++)
	        		{
	        			typeList.add(types.get(i));
	        		}
	        		}catch(Exception e){
	        			log.error(e.getMessage());
	        			throw new DAOException(getException( e.getMessage()));
	     		}

	     	return new Response(typeList);
   }




   /**
    * Gets all term property qualifier types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllTermPropertyQualifierTypes(HashMap map)throws Exception{
   	String vocabularyName = null;
   	Vector types = new Vector();
   	List typeList = new ArrayList();
   	try{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);

   		}
   		setVocabulary(vocabularyName);
   		types = adapter.getAllQualifierTypes();
   		for(int i=0 ;i<types.size(); i++)
   		{
   			typeList.add(types.get(i));
   		}
   		}catch(Exception e){
   			log.error(e.getMessage());
   			throw new DAOException(getException( e.getMessage()));
		}

	return new Response(typeList);
}



   /**
    * Gets all term property types for the specified concept from a given namespace
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */

   public Response getAllTermPropertyTypes(HashMap map)throws Exception{
   	String vocabularyName = null;
   	Vector types = new Vector();
   	List typeList = new ArrayList();
   	try{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);

   		}
   		setVocabulary(vocabularyName);
   		//types = adapter.getAllPropertyTypes();
   		//gov.nih.nci.lexrpx.client.PropertyType[] properties = adapter.getAllPropertyTypes();
   		PropertyType[] properties = adapter.getAllPropertyTypes();

   		for(int i=0 ;i< properties.length; i++)
   		{
   			gov.nih.nci.evs.domain.Property pro = new gov.nih.nci.evs.domain.Property();
   			pro.setName(properties[i].getName());
   			//pro.setValue(properties[i].getValue());
   			typeList.add(pro);
   		}
   		}catch(Exception e){
   			log.error(e.getMessage());
   		throw new DAOException(getException( e.getMessage()));
		}

	return new Response(typeList);
}




   /**
    * Converts a dtsrpc Concept object to a DescLogicConcept object
    * @param concept dtsrpc concept object
	* @return Returns a response
	* @throws Exception
    */

   private DescLogicConcept buildDescLogicConcept(gov.nih.nci.lexrpc.client.Concept concept){
   		DescLogicConcept dlc = new DescLogicConcept();
   		dlc.setName(concept.getName());
   		dlc.setCode(concept.getCode());
   		dlc.setPropertyCollection(convertProperties(concept.getProperties()));
   		dlc.setRoleCollection(convertRoles(concept.getRoles()));
   		dlc.setInverseRoleCollection(convertRoles(concept.getInverseRoles()));
        dlc.setNamespaceId(concept.getNamespaceId());
   		dlc.setHasParents(concept.getHasParents());
		dlc.setHasChildren(concept.getHasChildren());
		dlc.setIsRetired(concept.getIsRetired());
		dlc.setAssociationCollection(convertAssociations(concept.getAssociationCollection()));
        dlc.setInverseAssociationCollection(convertAssociations(concept.getInverseAssociationCollection()));

		if(concept.getTreeNode()!=null){
			dlc.setEdgeProperties(convertEdgeProperties(concept));
			dlc.setTreeNode(convertTreeNode(concept));
		    }

		dlc.setSemanticTypeVector(getDLSemanticTypes(dlc));
		if(vocabulary != null){
            dlc.setVocabulary(vocabulary);
        }
   		return dlc;
   	}

   public Vector convertAssociations(Vector dtsrpcAssociations){
       Vector associationVector = new Vector();
       gov.nih.nci.evs.domain.Association association = new Association();
       for(int i=0; i<dtsrpcAssociations.size(); i++){
           gov.nih.nci.lexrpc.client.ConceptAssociation dtsAssociation = (gov.nih.nci.lexrpc.client.ConceptAssociation)dtsrpcAssociations.get(i);
           association.setName(dtsAssociation.getName());
           association.setValue(dtsAssociation.getValue());
           //log.info("Number of qualifiers = "+ dtsAssociation.getQualifiers().size());
           association.setQualifierCollection(convertQualifiers(dtsAssociation.getQualifiers()));
           associationVector.add(association);
           }
       return associationVector;
       }


   public EdgeProperties convertEdgeProperties(gov.nih.nci.lexrpc.client.Concept concept){
       EdgeProperties edgeProperties = new EdgeProperties();
       edgeProperties.setName(concept.getTreeNode().getName());
       edgeProperties.setIsA(concept.getTreeNode().getIsA());
       edgeProperties.setLinks(concept.getLinks());
       edgeProperties.setTraverseDown(concept.getTraverseDown());
       return edgeProperties;
       }

   private gov.nih.nci.evs.domain.TreeNode convertTreeNode(gov.nih.nci.lexrpc.client.Concept concept){
       gov.nih.nci.evs.domain.TreeNode treeNode = new gov.nih.nci.evs.domain.TreeNode();
       treeNode.setName(concept.getTreeNode().getName());
       treeNode.setIsA(concept.getTreeNode().getIsA());
       treeNode.setLinks(concept.getLinks());
       treeNode.setTraverseDown(concept.getTraverseDown());
       return treeNode;
       }

   /**
    * Retrievs all the parent concepts for a given concept
    * @param map Specifies the input parameters
	* @return Returns a response that holds a list of DescLogicConcepts
	* @throws Exception
    */

   private Response getParentConcepts(HashMap map)throws Exception{

   	String vocabularyName = null;
   	String conceptName = null;
   	boolean inputFlag = false;
   	int asdIndex = 1;

   	List typeList = new ArrayList();
   	try{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);
   			else if(name.equalsIgnoreCase("conceptName"))
 				conceptName = (String)map.get(key);
   			else if(name.equalsIgnoreCase("inputFlag"))
				inputFlag = ((Boolean)map.get(key)).booleanValue();
   			else if(name.equalsIgnoreCase("ASDIndex"))
				asdIndex = ((Integer)map.get(key)).intValue();

   		}
   		setVocabulary(vocabularyName);

   		Vector conceptNames = new Vector();

   		Concept[] superConcepts =  null;
   		if(!inputFlag){
   		   	if(validateConceptName(conceptName)){   		   	   
   		   		superConcepts = adapter.getDirectSups(conceptName, false, asdIndex);
   		   	    }
   		   	}
   		else{
   		    if(validateDLConceptCode(conceptName)){		   	    
   		    	superConcepts = adapter.getDirectSups(conceptName, false, asdIndex);
		   	    }
		   	}	

   	   		for(int i=0 ;i<superConcepts.length; i++)   	   		{
   	   		    DescLogicConcept dlc = buildDescLogicConcept(superConcepts[i]);
   	   			typeList.add(dlc);
   	   		}



   		}catch(Exception e){
   			log.error(e.getMessage());
   		 throw new DAOException(getException(e.getMessage()));
		}

	return new Response(typeList);
}


   /**
	* Retrievs all the child DescLogicConcepts for a given conceptName
    * @param map Specifies the input parameters
	* @return Returns a response that holds a list of DescLogicConcepts
	* @throws Exception
    */

   private Response getChildConcepts(HashMap map)throws Exception{
   	String vocabularyName = null;
   	String conceptName = null;
   	boolean inputFlag = false;
   	int asdIndex = 1;

   	List typeList = new ArrayList();
   	try{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);
   			else if(name.equalsIgnoreCase("conceptName"))
 				conceptName = (String)map.get(key);
   			else if(name.equalsIgnoreCase("inputFlag"))
				inputFlag = ((Boolean)map.get(key)).booleanValue();
   			else if(name.equalsIgnoreCase("ASDIndex"))
				asdIndex = ((Integer)map.get(key)).intValue();  			
   		 

   		}
   		setVocabulary(vocabularyName);

   		Concept[] concepts = null;

   		   if(!inputFlag){
   		   		validateConceptName(conceptName);
   		   		concepts = adapter.getDirectSubs(conceptName,false, asdIndex);
   		   	}
   		   	else{
				validateDLConceptCode(conceptName);
				concepts = adapter.getDirectSubs(conceptName,true, asdIndex);
			}

			for(int i=0; i<concepts.length; i++){
				DescLogicConcept dlc = new DescLogicConcept();
				dlc = buildDescLogicConcept(concepts[i]);
				typeList.add(dlc);
				}



   		}catch(Exception e){
   			log.error(e.getMessage());
   		throw new DAOException(getException(e.getMessage()));
		}

	return new Response(typeList);
}


   /**
    * Returns a Boolean value if the specified concept has a child concept
    * @param map Specifies the input parameters
	* @return Returns a response
	* @throws Exception
    */
   	private Response hasChildren(HashMap map)throws Exception{
   	   	String vocabularyName = null;
   	   	String conceptName = null;
   	   	List hasList = new ArrayList();

   	   	Boolean found = new Boolean(false);
   	   	try{
   	   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   	   		{
   	   			String key = (String)iter.next();
   	   			String name = key.substring(key.indexOf("$")+1, key.length());

   	   			if(name.equalsIgnoreCase("vocabularyName"))
   	   				vocabularyName = (String)map.get(key);
   	   			else if(name.equalsIgnoreCase("conceptName"))
   	 				conceptName = (String)map.get(key);

   	   		}
   	   		setVocabulary(vocabularyName);


   	   		if(validateConceptName(conceptName)){

   	   			found  = adapter.hasChildren(conceptName);
   	   			hasList.add(found);
   	   		}


   	   		}catch(Exception e){
   	   		log.error(e.getMessage());
   	   		throw new DAOException(getException(e.getMessage()));
   			}

   		return new Response(hasList);

   	}


    /**
     * Returns a Boolean value if the given concept has a Parent concept
     * @param map Specifies the input parameters
 	 * @return Returns a response
 	 * @throws Exception
     */
   	private Response hasParents(HashMap map)throws Exception{
   	   	String vocabularyName = null;
   	   	String conceptName = null;
   	   	List hasList = new ArrayList();

   	   	Boolean found = new Boolean(false);
   	   	try{
   	   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   	   		{
   	   			String key = (String)iter.next();
   	   			String name = key.substring(key.indexOf("$")+1, key.length());

   	   			if(name.equalsIgnoreCase("vocabularyName"))
   	   				vocabularyName = (String)map.get(key);
   	   			else if(name.equalsIgnoreCase("conceptName"))
   	 				conceptName = (String)map.get(key);

   	   		}
   	   		setVocabulary(vocabularyName);


   	   		if(validateConceptName(conceptName)){

   	   			found  = adapter.hasParents(conceptName);
   	   			hasList.add(found);
   	   		}


   	   		}catch(Exception e){
   	   		log.error(e.getMessage());
   	   			throw new DAOException(getException(e.getMessage()));
   			}

   		return new Response(hasList);

   	}


    /**
     * Checks if the concept name is valid
     * @param conceptName
 	 * @return boolean value
 	 * @throws Exception
     */
   private boolean validateConceptName (String conceptName)throws Exception{

   	boolean valid = true;

   	try{
   	if(conceptName == null){
   		valid = false;
   		throw new DAOException (getException("Exception : Concept name cannot be a null value"));
   			}
   	else if(adapter.getConceptCodeByName(conceptName) == null){
   			valid = false;
   			throw new DAOException (getException("Exception : Invalid concept name - "+conceptName));
   			}
	}
   	catch(Exception e){
   		log.error(e.getMessage());
   		throw new DAOException(getException(e.getMessage()));
   	}
   		return valid;
   	}




   /**
    * Checks if the relationship type is valid
    * @param relation
	* @return boolean value
	* @throws Exception
    */
   private boolean validateRelation(String relation) throws Exception{
   	boolean valid = false;
   	String[] relationType = {"RB","RN","RO","RL","PAR","CHD","SIB","AQ","QB"};

   	for(int i=0; i<relationType.length && !valid; i++){
   		if(relation.equalsIgnoreCase(relationType[i])){
   			valid = true;
   			}
   		}
   	if(!valid){
   		log.error(" Invalid relation attribute");
   		throw new DAOException(getException(" Invalid relation attribute "));
   		}
   	return valid;

   	}

   /**
    * Gets the concept name for the specified code
    * @param conceptCode - specifies the concept code
    * @return Returns the concept name
    */

   private Response getConceptNameByCode(HashMap map)throws Exception{
   	String vocabularyName = null;
	String conceptName = null;
	String conceptCode = null;
	List list = new ArrayList();

	try
	{
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());

			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String)map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String)map.get(key);

		}


		setVocabulary(vocabularyName);

		if(conceptCode == null){
			log.error(" Concept code cannot be a null value");
			throw new DAOException (getException(" Invalid concept code"));
			}
		conceptName = adapter.getConceptNameByCode(conceptCode);
		list.add(conceptName);
	}
	catch(Exception e)
	{
		log.error("Exception - getConceptNameByCode "+e.getMessage());
		throw new DAOException (getException(" Exception - "+  e.getMessage()));
	}


	return (new Response(list));

   	}
   public Vector getDLSemanticTypes(DescLogicConcept dlc){
   	Vector semanticTypes = new Vector();
   	Vector properties = new Vector();
   	if(dlc.getSemanticTypeVector() == null){
   		properties = dlc.getPropertyCollection();
   	   	for(int i=0; i< properties.size(); i++){
   	   		gov.nih.nci.evs.domain.Property p = (gov.nih.nci.evs.domain.Property)properties.get(i);
   	   		if(p.getName().equalsIgnoreCase("Semantic_Type")){
   	   			semanticTypes.add(p.getValue());
   	   			}
   	   		}
   	}
   	return semanticTypes;
   	}
   public Response getAllPropertyTypes(HashMap map)throws Exception{

   	String vocabularyName = null;

   	List propertyList = new ArrayList();
   	try{
   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
   		{
   			String key = (String)iter.next();
   			String name = key.substring(key.indexOf("$")+1, key.length());

   			if(name.equalsIgnoreCase("vocabularyName"))
   				vocabularyName = (String)map.get(key);

   		}

   		setVocabulary(vocabularyName);
   		PropertyType[] propertyTypes = adapter.getAllPropertyTypes();
   		for(int i=0 ;i<propertyTypes.length; i++)
   		{
   			propertyList.add(propertyTypes[i].getName());
   		}
   		}catch(Exception e){
   			log.error(e.getMessage());
   			throw new DAOException(getException( e.getMessage()));
		}

	return new Response(propertyList);

   	}
   private Exception getException(String msg){
   	Exception ex = new Exception(msg);
   	if(msg == null){
   		ex = new Exception(pastEx.getMessage());
   		}
   	else{
   		pastEx = new Exception(msg);
   		}
   	return ex;
   	}

   private Exception getException(Exception ex){
   	String msg = ex.getMessage();
   	return getException(msg);
   	}

   private boolean validateDLConceptCode(String code) throws Exception{

	boolean valid = true;

   	try{
   	if(code == null){
   		valid = false;
   		throw new DAOException (getException("Exception : Concept code cannot be a null value"));
   			}
   	else if(adapter.getConceptNameByCode(code) == null){
   			valid = false;
   			throw new DAOException (getException("Exception : Invalid concept code - "+ code));
   			}
	}
   	catch(Exception e){
   		log.error(e.getMessage());
   		throw new DAOException(getException(e.getMessage()));
   	}
   		return valid;
   	}

   public Response getHistoryRecords(HashMap map) throws Exception{
        String vocabularyName = null;
        String conceptCode = null;
        Date initialDate = null;
        Date finalDate = null;

        Vector v = new Vector();
        List list = new ArrayList();

        try
        {
            for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
            {
                String key = (String)iter.next();
                String name = key.substring(key.indexOf("$")+1, key.length());

                if(name.equalsIgnoreCase("VocabularyName"))
                    vocabularyName = (String)map.get(key);
                else if(name.equalsIgnoreCase("conceptCode"))
                    conceptCode = (String)map.get(key);
                else if(name.equalsIgnoreCase("initialDate"))
                    initialDate = (Date)map.get(key);
                else if(name.equalsIgnoreCase("finalDate"))
                    finalDate = (Date)map.get(key);
             }

            setVocabulary(vocabularyName);
            if(conceptCode != null){
                validateDLConceptCode(conceptCode);
            }
            int namespaceId = adapter.getNamespaceId(vocabularyName);
            if(vocabularyName != null && initialDate != null && finalDate != null && conceptCode !=null){
            	v = adapter.getHistoryRecords(vocabularyName, initialDate, finalDate, conceptCode);
                }
            else if(vocabularyName != null && initialDate != null && finalDate != null && conceptCode ==null){
                v = adapter.getHistoryRecords(vocabularyName, initialDate, finalDate);
                }
            else if(vocabularyName != null && initialDate == null && finalDate == null && conceptCode !=null){
                v = adapter.getConceptEditAction(conceptCode);        
                }
            else{
                throw new DAOException("Exception: Invalid arguments");
            }

            if(v!=null && v.size()>0){
                HistoryRecord hr = new HistoryRecord();
                hr.setDescLogicConceptCode(conceptCode);
                Vector historyVector = new Vector();
                
                for(int i=0; i<v.size(); i++){           
                    String actionDate = (String)v.get(i);                    
                    StringTokenizer st = new StringTokenizer(actionDate, "|");
                    while(st.hasMoreTokens()){
                        String action = st.nextToken();
                        Date aDate = stringToDate(st.nextToken());                        
                        History h = new History();
                        h.setEditAction(action);
                        h.setEditActionDate(aDate);
                        if(action.equalsIgnoreCase("split") ||action.equalsIgnoreCase("merge")){                            
                            Vector ref = adapter.getAncestorCodes(conceptCode, false, aDate, aDate);
                            String refCodes = "";                            
                            for(int r=0; r<ref.size(); r++){
                                refCodes += (String)ref.get(r);
                            }
                            if(refCodes.length()>0){
                                h.setReferenceCode(refCodes);
                            }                            
                        }
                        historyVector.add(h);                        
                    }
                    hr.setHistoryCollection(historyVector);                    
                }
                list.add(hr);                
               }
        }
        catch(Exception e){
            throw new DAOException(getException(e));

        }
 return new Response(list);

   }


   private Response getHistoryStartDate(HashMap map) throws Exception{
	   String vocabularyName = null;
	   	List dateList = new ArrayList();
	   	try{
	   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
	   		{
	   			String key = (String)iter.next();
	   			String name = key.substring(key.indexOf("$")+1, key.length());

	   			if(name.equalsIgnoreCase("vocabularyName"))
	   				vocabularyName = (String)map.get(key);

	   		}

	   		setVocabulary(vocabularyName);
	   		Date startDate = adapter.getHistoryStartDate();
	   		dateList.add(startDate);
	   		}catch(Exception e){
	   			log.error(e.getMessage());
	   			throw new DAOException(getException( e.getMessage()));
			}

		return new Response(dateList); 
   }
   
   private Response getHistoryEndDate(HashMap map)throws Exception{
	   String vocabularyName = null;
	   	List dateList = new ArrayList();
	   	try{
	   		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
	   		{
	   			String key = (String)iter.next();
	   			String name = key.substring(key.indexOf("$")+1, key.length());

	   			if(name.equalsIgnoreCase("vocabularyName"))
	   				vocabularyName = (String)map.get(key);
	   		}

	   		setVocabulary(vocabularyName);
	   		Date endDate = adapter.getHistoryEndDate();
	   		dateList.add(endDate);
	   		}catch(Exception e){
	   			log.error(e.getMessage());
	   			throw new DAOException(getException( e.getMessage()));
			}
		return new Response(dateList); 
   }
   private Response getHistoryDates(HashMap map) throws Exception {
       List dateList = new ArrayList();
       Vector dates = adapter.getHistoryDates();
       for(int i=0; i< dates.size(); i++){
           dateList.add(dates.get(i));
       }
       return new Response(dateList);
   }
   private Response getHistoryEndBaseLineDate(HashMap map) throws Exception {
       List dateList = new ArrayList();
       Date date = adapter.getHistoryEndBaselineDate();
       if(date!=null){
           dateList.add(date);
       }
       return new Response(dateList);
   }
   private Date stringToDate(String aString) throws Exception
   {
      Date theDate = null;
       try
       {
           SimpleDateFormat sdf = null;
           if(aString.indexOf("-")>3){
               sdf = new SimpleDateFormat("yyyy-MM-dd");
           }
           else if(aString.indexOf("/")>3){
               sdf = new SimpleDateFormat("yyyy/MM/dd");
           }
           else{
               sdf = new SimpleDateFormat("MM/dd/yyyy");
           }
                       
           theDate = sdf.parse(aString);            
       }
       catch(Exception e)
       {
           log.error("Exception: " + e.getMessage());
           throw new Exception(getException(e.getMessage()));
       }
       return theDate;

   }

 }
