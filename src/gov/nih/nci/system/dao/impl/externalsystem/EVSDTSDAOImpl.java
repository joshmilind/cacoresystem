package gov.nih.nci.system.dao.impl.externalsystem;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.tree.DefaultMutableTreeNode;
//internal
import gov.nih.nci.evs.domain.*;
import gov.nih.nci.evs.domain.Property;
import gov.nih.nci.evs.query.EVSQueryImpl;
import gov.nih.nci.common.util.*;

import gov.nih.nci.common.net.*;

import gov.nih.nci.system.dao.*;
import gov.nih.nci.system.dao.cache.EVSCacheManager;
import gov.nih.nci.system.dao.security.*;
//dtsrpc jar
import gov.nih.nci.dtsrpc.client.*;
//metaphrase jar
import COM.Lexical.Metaphrase.Cooccurrence;
import COM.Lexical.Metaphrase.Match;
import COM.Lexical.Metaphrase.Metaphrase;
import COM.Lexical.Metaphrase.Partition;
import COM.Lexical.Metaphrase.RMIMetaphrase;
import COM.Lexical.Metaphrase.Relationship;
import COM.Lexical.Metaphrase.SearchSpec;
import COM.Lexical.Metaphrase.Term;
import org.apache.log4j.*;

import gov.nih.nci.evs.security.*;
import gov.nih.nci.system.dao.cache.*;
import gov.nih.nci.system.dao.properties.*;

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

public class EVSDTSDAOImpl implements DAO {
	private static Logger log = Logger.getLogger(EVSDTSDAOImpl.class.getName());

	private DTSRPCClient dtsrpc;

	private Metaphrase metaphrase;

	private Exception pastEx = new DAOException();

	private HashMap tokenCollection = new HashMap();
	
	private static HashMap vocabularyCollection = new HashMap();

	/**
	 * Creates a connection to the EVS datasource and generates a query based on
	 * the request. The results are wrapped in a response object
	 * 
	 * @param r -
	 *            Request object (This object holds the evs query)
	 * @return - The results are stored in a list and returned as a response
	 *         object
	 * @throws DAOException
	 * @throws Exception
	 */
	public Response query(Request r) throws DAOException {
		Object obj = r.getRequest();
		List resultList = new ArrayList();
		gov.nih.nci.common.net.Response response = new Response();		

		try {
			Hashtable configs = r.getConfig();
			EVSQueryImpl criteria = (EVSQueryImpl) obj;
			if (criteria.getSecurityTokenCollection().size() > 0
					&& criteria.getSecurityTokenCollection() != null) {
				tokenCollection = criteria.getSecurityTokenCollection();
			}			
			String queryType = null;
			HashMap mapValues = new HashMap();
			if(((HashMap)criteria.getClass().getField("descLogicValues").get(criteria)).size()>0){
				queryType = "descLogicValues";
				mapValues = (HashMap)criteria.getClass().getField("descLogicValues").get(criteria);
			}else if(((HashMap)criteria.getClass().getField("metaThesaurusValues").get(criteria)).size()>0){
				queryType = "metaThesaurusValues";
				mapValues = (HashMap)criteria.getClass().getField("metaThesaurusValues").get(criteria);
			}
			try {
				response = queryEVS(configs, mapValues,  queryType);
			} catch (Exception ex) {
				String msg = " - ";
                
				if (ex.getMessage() == null) {
					if (pastEx.getMessage() == null) {
						if (queryType.equalsIgnoreCase("descLogicValues")) {
							msg = msg + "caCORE - DTSRPC Exception";
						} else if (queryType.startsWith("meta")) {
							msg = msg + "caCORE - Metaphrase Exception";
						}
					} else {
						msg = msg + pastEx.getMessage();
					}

				} else {
					msg = msg + ex.getMessage();
				}
				log.error(msg);
				throw new DAOException(msg);
			}	

		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return response;
	}
	   
    private String getVocabularyName(HashMap mapValues){
        String vocabularyName = null;
        for(Iterator it = mapValues.keySet().iterator(); it.hasNext();){                                    
            String key = (String)it.next();
            String name = key.substring(key.indexOf("$") + 1, key.length());
            Object value = mapValues.get(key);            
            if (name.equalsIgnoreCase("VocabularyName")){
                vocabularyName = (String)value;
                break;
            }
        }
        return vocabularyName;
    }
    private String getMethodName(HashMap mapValues){ 
    	String methodName = null;
        Iterator iter = mapValues.keySet().iterator();
        String keyMethod = (String) iter.next();
        if(keyMethod != null){
        	methodName = keyMethod.indexOf("$") > 0 ? keyMethod.substring(0, keyMethod.indexOf("$")): keyMethod;
        }
        return methodName; 
    }
    
    private String getCacheKey(String methodName,HashMap mapValues){
        String key = methodName;
        for(Iterator it = mapValues.keySet().iterator(); it.hasNext();){                                    
            String name = (String)it.next();
            Object value = mapValues.get(name);
            key += "_"+String.valueOf(value);            
        }
        return key;
    }
    private boolean enableCache(Hashtable config){
        boolean enableCache = true;
        if(config.get("enableCache")!=null){
            try{
                enableCache = Boolean.valueOf((String)config.get("enableCache")).booleanValue();
            }catch(Exception ex){}
        }
        return enableCache;
    }
	private Response queryEVS(Hashtable config, HashMap mapValues, String queryType) throws Exception{	
		Response response = null;		
        String vocabularyName = getVocabularyName(mapValues);
        String methodName = getMethodName(mapValues);       
    	Method method = this.getClass().getDeclaredMethod(methodName, new Class[] { HashMap.class });       
		if (method == null) {
			log.error("Invalid method name");
			throw new DAOException(getException("Invalid method name"));
		}
        String key = getCacheKey(methodName, mapValues);
        boolean checkCache = false;        
        EVSCacheManager evsCache = null;          
		//Check if a security token is required to access the vocabulary
        DAOSecurity security = null;
        if(vocabularyName != null){
            if(getSecurityAdapter(vocabularyName)!= null){
                security = getSecurityAdapter(vocabularyName);
            }
        }
        if(security != null){
            SecurityToken securityToken = null;
            if(tokenCollection.size()>0){
                securityToken = (SecurityToken)tokenCollection.get(vocabularyName);
            }  
            if(securityToken==null){
                Exception ex = new gov.nih.nci.system.applicationservice.SecurityException("Permission denied - Please set SecurityToken for "+ vocabularyName);
                throw new gov.nih.nci.system.applicationservice.SecurityException(getException(ex));
             }       
            getSecurityKey(security, securityToken);
        }else{
            //Security token is not required to access the Vocabulary
            //Check if cache needs to be enabled            
            if(enableCache(config)){
                evsCache = EVSCacheManager.getInstance();
                if(evsCache != null){ 
                    if(evsCache.isCacheNameValid(methodName)){
                        checkCache = true;
                        try{
                            //read data from cache
                            Object results = evsCache.get(key, methodName);
                            if(results != null){
                                return (Response)results;
                            }                            
                        }catch(Exception ex){}                        
                    }                       
                 }                   
            }
        }
        try{
            //Query EVS Servers
            makeRemoteConnection(config, queryType);
            response = (Response) method.invoke(this,new Object[] { mapValues });
            //update cache
            if(checkCache){
                evsCache.put(key, response, methodName);                                             
            }                               
        }catch(Exception ex){
            log.error( methodName + " throws Exception ");
            throw new DAOException(ex.getMessage());
        }         
		return response;
	}
	
	private void makeRemoteConnection(Hashtable configs, String queryType)throws Exception{
		try{
			EVSProperties evsProperties = EVSProperties.getInstance(configs);
			if (queryType.equalsIgnoreCase("descLogicValues")) {
				dtsrpc = evsProperties.getDtsrpcConnection();
			
			} else if (queryType.equalsIgnoreCase("metaThesaurusValues")) {		
				metaphrase = evsProperties.getMetaphrase();
			}			
		}catch(Exception ex){
			throw new DAOException(getException(ex));
		}
		}
	

	/**
	 * Gets the descendant concept codes for the specified concept
	 * 
	 * @param map -
	 *            Holds the input parameters
	 * @return - Descendant concept codes are returned in a Response list
	 * @throws Exception
	 */
	private Response getDescendants(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		boolean flag = false;
		String iBaseLineDate = null;
		String fBaseLineDate = null;
		Vector descendants = new Vector();
		ArrayList list = new ArrayList();
		try {
			// parse hashmap
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("flag"))
					flag = ((Boolean) map.get(key)).booleanValue();
				else if (name.equalsIgnoreCase("iBaseLineDate"))
					iBaseLineDate = (String) map.get(key);
				else if (name.equalsIgnoreCase("fBaseLineDate"))
					fBaseLineDate = (String) map.get(key);
			}

			setVocabulary(vocabularyName);
			descendants = dtsrpc.getDescendantCodes(conceptCode, flag,
					stringToDate(iBaseLineDate), stringToDate(fBaseLineDate));
			if (descendants == null) {
				// log.info("No descendants found");
			} else {

				for (int i = 0; i < descendants.size(); i++) {
					list.add(descendants.get(i));
				}

			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(list);

	}

    /**
     * Validates vocabulary token
     * @param vocabularyName
     * @param token 
     * @return response 
     */
    private Response validateToken(HashMap map)throws SecurityException{
        List validList = new ArrayList();
        String vocabularyName = null;
        SecurityToken securityToken = null;
        boolean valid = true;
        for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
        {
            String key = (String)iter.next();
            String name = key.substring(key.indexOf("$")+1, key.length());

            if(name.equalsIgnoreCase("vocabularyName"))
                vocabularyName = (String)map.get(key);
            else if(name.equalsIgnoreCase("token"))
                securityToken = (SecurityToken)map.get(key);
        }
        DAOSecurity security = getSecurityAdapter(vocabularyName);
        if(security != null){
            if(securityToken == null){
                throw new SecurityException("Please specify security token to access "+ vocabularyName);
            }
            try{
                valid = validateToken(security, securityToken);                
            }
            catch(Exception ex){
                valid = false;
            }
            validList.add(valid);
        }
        
        return new Response(valid);
    }
    
    /**
     * Validates token 
     * @param security
     * @param securityToken
     * @return valid
     */
    private boolean validateToken(DAOSecurity security, SecurityToken securityToken) throws SecurityException{                
        boolean valid = false;                
        try{
            if(getSecurityKey(security, securityToken) != null){
                valid = true;
            }            
        }catch(Exception ex){
        }       
        return valid;       
    }
    
    private SecurityKey getSecurityKey(DAOSecurity security, SecurityToken securityToken){
    	SecurityKey key = null;        
        try{
            key = security.getSecurityKey(new UserCredentials(securityToken.getAccessToken()));           
        }catch(Exception ex){            
            throw new SecurityException(getException(ex));
        }       
        return key;
    }
    
    /**
     * Returns the Security implementation class for the specified vocabulary
     */
    private DAOSecurity getSecurityAdapter(String vocabularyName){
        DAOSecurity security = null;
        try{
            security = (DAOSecurity)ObjectFactory.getObject(vocabularyName);
        }catch(Exception ex){  
            return null;
        }
        return security;
    }
    
	/**
	 * Sets Vocabulary
	 * 
	 * @param vocabularyName
	 * @throws Exception
	 */
	private void setVocabulary(String vocabularyName) throws Exception {		
		if (!StringHelper.hasValue(vocabularyName)) {
			log.error("vocabularyName cannot be null");
			throw new DAOException(
					getException(" vocabularyName cannot be null"));
		}
        boolean found = dtsrpc.setVocabulary(vocabularyName);
		if (!found) {
			throw new DAOException(
					"DTSRPC Exception - unable to connect to vocabulary "
							+ vocabularyName);
		}
		int ping = dtsrpc.PingServer();
		if (ping != 0) {
			if (ping == 1) {
				log.error("DTSRPC Server down");
				throw new DAOException("DTSRPC Server down");
			} else if (ping == 2) {
				log.error("DTS Database server down");
				throw new DAOException("DTS Database server down");
			} else if (ping == 3) {
				log
						.error("DTS XML-RPC Exception - Unable to connect to the DTSRPC Server");
				throw new DAOException(
						"DTS XML-RPC Exception - Unable to connect to the DTSRPC Server");
			} else {
				log
						.error("DTSRPC Exception - Unable to connect to the DTSRPC Server");
			}
		} 
	}

	/**
	 * getVocabulary returns the cached version of the Vocabulary object, if exists,
	 * others it returns a newly constructed on from the DTS server.
	 * 
	 * @param vocabularyName
	 * @return Vocabulary
	 */
	private Vocabulary getVocabulary(String vocabularyName) {
		Vocabulary vocab = (Vocabulary)vocabularyCollection.get(vocabularyName);
		if (vocabularyCollection.get(vocabularyName) == null) {
			vocab = new Vocabulary();
			vocab.setName(vocabularyName);
			vocab.setNamespaceId(dtsrpc.getNamespaceId(vocabularyName));
			vocab.setDescription(dtsrpc.getVocabularyDescription(vocabularyName));
			vocabularyCollection.put(vocabularyName, vocab);
		}
		return vocab;
	}

	private Response getAllVocabularies(HashMap map) {
		List vocabList = new ArrayList();
		Vector names = dtsrpc.getVocabularyNames();
		for (int i = 0; i < names.size(); i++) {
			vocabList.add(getVocabulary((String) names.get(i)));
		}
		return new Response(vocabList);
	}

	private Response getVocabularyByName(HashMap map) throws Exception {
		String vocabularyName = null;
		List vocabList = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());
				if (name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String) map.get(key);
			}
			if (vocabularyName != null) {
				setVocabulary(vocabularyName);
				vocabList.add(getVocabulary(vocabularyName));
			}

		} catch (Exception ex) {
			throw new DAOException(getException(ex.getMessage()));
		}
		return new Response(vocabList);
	}

	/**
	 * Performs a search for a DescLogicConcept in the DTSRPC server and returns
	 * a response
	 * 
	 * @param map
	 *            Specifies the search criteria values
	 * @return Returns a response that holds a list of DescLogicConcept Objects
	 * @throws Exception
	 */
	private Response getDescLogicConcept(HashMap map) throws Exception {

		String vocabularyName = null;
		String conceptName = null;
		boolean inputFlag = false;

		Vector v = new Vector();
		ArrayList list = new ArrayList();

		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
				else if (name.equalsIgnoreCase("inputFlag"))
					inputFlag = ((Boolean) map.get(key)).booleanValue();

			}

			setVocabulary(vocabularyName);
			boolean valid = true;
			DescLogicConcept dlc = new DescLogicConcept();
			Concept concept = new Concept();

			if (!inputFlag) {
				validateConceptName(conceptName);
				// concept = dtsrpc.findConcept(conceptName);
			} else {
				validateDLConceptCode(conceptName);
				// concept = dtsrpc.findConceptByCode(conceptName, true);
			}
			dlc = buildDescLogicConcept(dtsrpc.getConcept(conceptName,
					inputFlag, 1), getVocabulary(vocabularyName));
			// dlc = buildDescLogicConcept(concept);
			list.add(dlc);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(list);
	}

	/**
	 * Performs a search for DescLogicConcepts in the DTSRPC server and returns
	 * a response
	 * 
	 * @param map
	 *            Specifies the search criteria values
	 * @return Returns a response that holds a list of DescLogicConcept Objects
	 * @throws Exception
	 */
	private Response searchDescLogicConcepts(HashMap map) throws Exception {

		String vocabularyName = null;
		String searchTerm = null;
		int limit = 1;
		int matchOption = 0;
		String matchType = "";
		int ASDIndex = 1;

		Vector v = new Vector();
		ArrayList list = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("SearchTerm"))
					searchTerm = (String) map.get(key);
				else if (name.equalsIgnoreCase("matchLimit"))
					limit = ((Integer) map.get(key)).intValue();
				else if (name.equalsIgnoreCase("matchOption"))
					matchOption = ((Integer) map.get(key)).intValue();
				else if (name.equalsIgnoreCase("matchType"))
					matchType = (String) map.get(key);
				else if (name.equalsIgnoreCase("ASDIndex"))
					ASDIndex = ((Integer) map.get(key)).intValue();

			}

			setVocabulary(vocabularyName);

			if (!StringHelper.hasValue(searchTerm)) {
				log.warn("searchTerm cannot be null");
				throw new DAOException(
						getException(" searchTerm cannot be null"));
			}
			Concept[] concepts = null;
			concepts = dtsrpc.searchConcepts(searchTerm, limit, matchOption,
					matchType, ASDIndex);

			DescLogicConcept[] dlc = new DescLogicConcept[concepts.length];
			for (int x = 0; x < concepts.length; x++) {
				dlc[x] = new DescLogicConcept();
				dlc[x] = buildDescLogicConcept(concepts[x], getVocabulary(vocabularyName));
				list.add(dlc[x]);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return new Response(list);
	}

	/**
	 * get a DefaultMutableTreeNode for a given DescLogicConcept
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a Response that holds a DefaultMutableTreeNode in a
	 *         list
	 * @throws Exception
	 */
	private Response getTree(HashMap map) throws Exception {
		List list = new ArrayList();
		String vocabularyName = null;
		String rootName = null;
		boolean direction = false;
		boolean isaFlag = false;
		String levels = null;
		Vector roles = null;
		int attributes = 1;
		DefaultMutableTreeNode tree = null;
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("RootName"))
					rootName = (String) map.get(key);
				else if (name.equalsIgnoreCase("direction"))
					direction = ((Boolean) map.get(key)).booleanValue();
				else if (name.equalsIgnoreCase("isaFlag"))
					isaFlag = ((Boolean) map.get(key)).booleanValue();
				else if (name.equalsIgnoreCase("attributes"))
					attributes = ((Integer) map.get(key)).intValue();
				else if (name.equalsIgnoreCase("levels"))
					levels = (String) map.get(key);
				else if (name.equalsIgnoreCase("roles"))
					roles = (Vector) map.get(key);
			}
			tree = getTree(vocabularyName, rootName, direction, isaFlag,
					attributes, levels, roles);

			if (tree != null) {
				list.add(tree);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(" Exception in getTree: "
					+ e.getMessage()));
		}

		return new gov.nih.nci.common.net.Response(list);
	}

	/**
	 * Generates a DefaultMutableTreeNode for the specified values
	 * 
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
	private DefaultMutableTreeNode getTree(String vocabularyName,
			String rootName, boolean direction, boolean isaFlag,
			int attributes, String levels, Vector roles) throws Exception {
		DefaultMutableTreeNode evsTree = new DefaultMutableTreeNode();
		if (!StringHelper.hasValue(vocabularyName)) {
			log.error("vocabularyName cannot be null");
			throw new DAOException(
					getException(" vocabularyName cannot be null"));
		}

		if (!StringHelper.hasValue(rootName)) {
			log.error("Root Node cannot be null");
			throw new DAOException(getException(" Root Node cannot be null"));
		}

		setVocabulary(vocabularyName);
		int namespace = getVocabulary(vocabularyName).getNamespaceId();
		DefaultMutableTreeNode tree = null;

		Vector v = null;
		try {
			int depthLevel = -1;
			if (levels != null) {
				if (levels.equalsIgnoreCase("ALL"))
					depthLevel = -1;
				else
					depthLevel = (new Integer(levels)).intValue();
			}

			gov.nih.nci.dtsrpc.client.AttributeSetDescriptor attrib = gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.ALL_ATTRIBUTES;

			if (attributes == gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.WITH_ALL_ATTRIBUTES)
				attrib = gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.ALL_ATTRIBUTES;
			else if (attributes == gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.WITH_ALL_PROPERTIES)
				attrib = gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.ALL_PROPERTIES;
			else if (attributes == gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.WITH_ALL_ROLES)
				attrib = gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.ALL_ROLES;
			else if (attributes == gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.WITH_NO_ATTRIBUTES)
				attrib = gov.nih.nci.dtsrpc.client.AttributeSetDescriptor.NO_ATTRIBUTES;

			try {
				setVocabulary(vocabularyName);

				if ((roles != null) && (roles.size() > 0)) {
					tree = dtsrpc.getTree(rootName, direction, depthLevel,
							attrib, isaFlag, roles, namespace);

				} else {
					tree = dtsrpc.getTree(rootName, direction, depthLevel,
							attrib);
				}

				if (tree == null) {
					throw new DAOException(getException("DTSRPC Exception"));

				}

				DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree
						.getRoot();
				Concept rootConcept = (Concept) root.getUserObject();
				DescLogicConcept dlRoot = buildDescLogicConcept(rootConcept, getVocabulary(vocabularyName));

				DefaultMutableTreeNode dlTree = new DefaultMutableTreeNode(dlRoot, true);
				int childCounter = tree.getChildCount();

				if (root.getChildCount() > 0) {
					dlTree = getChildNodes(dlTree, root, getVocabulary(vocabularyName));
				}
				evsTree = dlTree;

			} catch (Exception e) {
				log.error(e.getMessage());
				throw new DAOException(
						getException("Exception - DTSRPC call: \n"
								+ e.getMessage()));
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return evsTree;
	}

	private DefaultMutableTreeNode getChildNodes(DefaultMutableTreeNode dlNode,
			DefaultMutableTreeNode dtsrpcNode, Vocabulary vocabulary) {
		int childCounter = dtsrpcNode.getChildCount();
		for (int i = 0; i < childCounter; i++) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode();
			DefaultMutableTreeNode conceptNode = (DefaultMutableTreeNode) dtsrpcNode
					.getChildAt(i);
			Concept childConcept = (Concept) conceptNode.getUserObject();
			DescLogicConcept dlc = buildDescLogicConcept(childConcept, vocabulary);

			Vector vecProperties = childConcept.getProperties();

			node.setUserObject(dlc);
			if (conceptNode.getChildCount() > 0) {
				node = getChildNodes(node, conceptNode, vocabulary);
			}
			dlNode.add(node);
		}
		
		return dlNode;
	}

	/**
	 * Gets all the concept names for a given property
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return Returns a Response with a list of concept names
	 * @throws Exception
	 */

	private Response getConceptWithPropertyMatching(HashMap map)
			throws Exception {
		String vocabularyName = null;
		String propertyName = null;
		String propertyValue = null;
		int limit = 1;
		Vector v = new Vector();
		ArrayList list = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("propertyName"))
					propertyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("propertyValue"))
					propertyValue = (String) map.get(key);
				else if (name.equalsIgnoreCase("matchLimit"))
					limit = ((Integer) map.get(key)).intValue();
			}

			setVocabulary(vocabularyName);

			v = dtsrpc.findConceptWithPropertyMatching(propertyName,
					propertyValue, limit);

			if (v != null) {

				for (int i = 0; i < v.size(); i++) {
					list.add(v.get(i));
				}

			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	private Response getConceptWithSiloMatching(HashMap map) throws Exception {

		String vocabularyName = null;
		String searchTerm = null;
		String siloName = null;
		int limit = 10;
		Vector v = new Vector();
		ArrayList list = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("searchTerm"))
					searchTerm = (String) map.get(key);
				else if (name.equalsIgnoreCase("siloName"))
					siloName = (String) map.get(key);
				else if (name.equalsIgnoreCase("matchLimit"))
					limit = ((Integer) map.get(key)).intValue();
			}

			setVocabulary(vocabularyName);

			if (siloName == null) {
				v = dtsrpc.findConceptsWithSiloMatching(searchTerm, limit);
			} else {
				v = dtsrpc.findConceptsWithSiloMatching(searchTerm, limit,
						siloName);
			}

			if (v != null) {

				for (int i = 0; i < v.size(); i++) {
					list.add((String) v.get(i));
				}

			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * gets the concept name for the given concept code
	 * 
	 * @param map
	 * @return Response object that holds the concept name in a list
	 * @throws Exception
	 */

	private Response getDescLogicConceptNameByCode(HashMap map)
			throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		String conceptName = null;
		List conceptList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			conceptName = dtsrpc.getConceptNameByCode(conceptCode);
			if (conceptName != null) {
				conceptList.add(conceptName);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(conceptList));
	}

	/**
	 * Checks if the first concept is a subconcept of the second concept
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a Boolean value in a list
	 * @throws Exception
	 */
	private Response isSubConcept(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName1 = null;
		String conceptName2 = null;
		Boolean isSub = Boolean.FALSE;
		List isSubList = new ArrayList();
		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode1"))
					conceptName1 = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode2"))
					conceptName2 = (String) map.get(key);
			}

			setVocabulary(vocabularyName);
			this.validateConceptName(conceptName1);
			this.validateConceptName(conceptName2);
			isSub = dtsrpc.isChild(conceptName1, conceptName2);
			if (isSub == null) {
				isSub = false;
			}
			isSubList.add(isSub);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(isSubList));

	}

	/**
	 * Checks if the specified concept is retired or not.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a Boolean value in a list
	 * @throws Exception
	 */
	private Response isRetired(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		Boolean retired = Boolean.FALSE;
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
			}
			setVocabulary(vocabularyName);
			retired = dtsrpc.isRetired(conceptCode);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		List isList = new ArrayList();
		isList.add(retired);
		return (new Response(isList));

	}

	/**
	 * Gets the values of a property owned by a concept
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a the property values in a list
	 * @throws Exception
	 */
	private Response getPropertyValues(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		String propertyName = null;
		Vector values = new Vector();
		ArrayList list = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
				else if (name.equalsIgnoreCase("propertyName"))
					propertyName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			values = dtsrpc.getPropertyValues(conceptName, propertyName);

			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					list.add(values.get(i));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));

	}

	/**
	 * Gets the ancestor concept codes of the specified concept.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of concept codes
	 * @throws Exception
	 */
	private Response getAncestors(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		boolean flag = false;
		String iBaseLineDate = null;
		String fBaseLineDate = null;
		Vector ancestors = new Vector();
		ArrayList list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("flag"))
					flag = ((Boolean) map.get(key)).booleanValue();
				else if (name.equalsIgnoreCase("iBaseLineDate"))
					iBaseLineDate = (String) map.get(key);
				else if (name.equalsIgnoreCase("fBaseLineDate"))
					fBaseLineDate = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			ancestors = dtsrpc.getAncestorCodes(conceptCode, flag,
					stringToDate(iBaseLineDate), stringToDate(fBaseLineDate));

			if (ancestors != null) {
				for (int i = 0; i < ancestors.size(); i++) {
					list.add(ancestors.get(i));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Gets the codes or the names, of all subconcepts of the specified concept
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of conceptNames or codes
	 * @throws Exception
	 */
	private Response getSubConcepts(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		Boolean inputFlag = null;
		Boolean outputFlag = null;
		ArrayList list = new ArrayList();

		Vector subConcepts = new Vector();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
				else if (name.equalsIgnoreCase("inputFlag"))
					inputFlag = (Boolean) map.get(key);
				else if (name.equalsIgnoreCase("outputFlag"))
					outputFlag = (Boolean) map.get(key);
			}

			setVocabulary(vocabularyName);

			subConcepts = dtsrpc.getSubConcepts(conceptName, inputFlag,
					outputFlag);

			if (subConcepts != null) {
				for (int i = 0; i < subConcepts.size(); i++) {
					list.add(subConcepts.get(i));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));

	}

	/**
	 * Gets the codes (or the names) of all superconcepts of the specified
	 * concept.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of super concept
	 *         names/codes
	 * @throws Exception
	 */
	private Response getSuperConcepts(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		Boolean inputFlag = null;
		Boolean outputFlag = null;

		Vector superConcepts = new Vector();
		ArrayList list = new ArrayList();
		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
				else if (name.equalsIgnoreCase("inputFlag"))
					inputFlag = (Boolean) map.get(key);
				else if (name.equalsIgnoreCase("outputFlag"))
					outputFlag = (Boolean) map.get(key);
			}

			setVocabulary(vocabularyName);

			superConcepts = dtsrpc.getSuperConcepts(conceptName, inputFlag,
					outputFlag);

			if (superConcepts.size() > 0) {
				for (int i = 0; i < superConcepts.size(); i++) {
					list.add(superConcepts.get(i));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));

	}

	/**
	 * Gets the roles owned by the specified concept.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of roles
	 * @throws Exception
	 */
	private Response getRolesByConceptName(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;

		Vector roles = new Vector();
		ArrayList list = new ArrayList();

		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			if (!StringHelper.hasValue(conceptName))
				throw new DAOException("conceptName cannot be null");

			roles = dtsrpc.getRolesByConceptName(conceptName);
			if (roles != null) {

				for (int i = 0; i < roles.size(); i++) {
					String elem = (String) roles.get(i);
					StringTokenizer st = new StringTokenizer(elem, "$");
					gov.nih.nci.evs.domain.Role role = new gov.nih.nci.evs.domain.Role();
					role.setName(st.nextToken());
					role.setValue(st.nextToken());
					list.add(role);
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));

	}

	/**
	 * Gets the names and values of properties owned by the specified concept.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of properties
	 * @throws Exception
	 */
	private Response getPropertiesByConceptName(HashMap map) throws Exception {

		String vocabularyName = null;
		String conceptName = null;
		ArrayList list = new ArrayList();

		Vector properties = new Vector();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			if (!StringHelper.hasValue(conceptName))
				throw new DAOException(
						getException("conceptName cannot be null"));

			properties = dtsrpc.getPropertiesByConceptName(conceptName);

			if (properties == null) {
				log.info("No properties found....");
			} else {
				for (int i = 0; i < properties.size(); i++) {
					String propertyString = (String) properties.get(i);
					if (propertyString != null
							&& propertyString.indexOf("$") > 0) {
						gov.nih.nci.evs.domain.Property property = new gov.nih.nci.evs.domain.Property();
						property.setName(propertyString.substring(0,
								propertyString.indexOf("$")));
						property.setValue(propertyString
								.substring(propertyString.indexOf("$") + 1));
						list.add(property);
					}

				}

			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));

	}

	/**
	 * Gets vocabulary names from EVS
	 * 
	 * @return - Returns a response that holds a list of vocabulary names
	 * @throws Exception
	 */
	private Response getVocabularyNames(HashMap map) throws Exception {
		Vector vocabularies = new Vector();
		ArrayList list = new ArrayList();
		try {
			vocabularies = dtsrpc.getVocabularyNames();
			for (int i = 0; i < vocabularies.size(); i++) {
				list.add(vocabularies.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return (new Response(list));
	}

	/**
	 * Gets the code of the specified concept.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of codes
	 * @throws Exception
	 */
	private Response getConceptCodeByName(HashMap map) throws Exception {

		String vocabularyName = null;
		String conceptName = null;
		String conceptCode = null;
		List conceptList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);
			if (!StringHelper.hasValue(conceptName)) {
				String msg = "conceptName cannot be null";
				log.error(msg);
				throw new DAOException(msg);
			}

			conceptCode = dtsrpc.getConceptCodeByName(conceptName);

			if (conceptCode != null) {
				conceptList.add(conceptCode);

			} else {
				// log.info("No conceptCode found for "+conceptName);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(conceptList));

	}

	/**
	 * Gets the host of a vocabulary server
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response
	 * @throws Exception
	 */
	private Response getVocabularyHost(HashMap map) throws Exception {
		String vocabularyName = null;
		String host = null;

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);
			host = dtsrpc.getVocabularyHost(vocabularyName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		List hostList = new ArrayList();
		hostList.add(host);
		return (new Response(hostList));

	}

	/**
	 * Gets the port of a vocabulary server
	 * 
	 * @param map
	 * @return returns a response that holds the vocabulary server port number
	 * @throws Exception
	 */
	private Response getVocabularyPort(HashMap map) throws Exception {
		String vocabularyName = null;
		String port = null;

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			port = dtsrpc.getVocabularyPort(vocabularyName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		List portList = new ArrayList();
		portList.add(port);

		return (new Response(portList));

	}

	/**
	 * Returns a list of versions for the given vocabulary in the knowledgebase.
	 * 
	 * @param vocabularyName
	 */
	private Response getVocabularyVersion(HashMap map) throws Exception {
		String vocabularyName = null;
		List version = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			version.add(dtsrpc.getVocabularyVersion(vocabularyName));
			// log.info("vocabulary version = "+ version);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(version));

	}

	/**
	 * Gets all EditAction of the specified concept. A EditAction contains a
	 * edit date and an editing action. The method searches for all editing
	 * actions and the corresponding editing dates on the specified concept.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of edit actions and dates
	 * @throws Exception
	 */
	private Response getConceptEditAction(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		Vector editActions = new Vector();
		Date editActionDate = null;
		ArrayList list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("editActionDate"))
					editActionDate = (Date) map.get(key);
			}

			setVocabulary(vocabularyName);

			if (editActionDate == null) {
				editActions = dtsrpc.getConceptEditAction(conceptCode);
			} else {
				editActions = dtsrpc.getConceptEditAction(conceptCode,
						editActionDate);
			}

			if (editActions != null) {                
				for (int i = 0; i < editActions.size(); i++) {
                    String action = null;
                    if(Class.forName("java.lang.Integer").isInstance(editActions.get(i))){
                        if((Integer)editActions.get(i) == 0){
                            action = "create";
                        }else if((Integer)editActions.get(i) == 1){
                            action = "merge";
                        }else if((Integer)editActions.get(i) == 2){
                            action = "modify";
                        }else if((Integer)editActions.get(i) == 3){
                            action = "retire";
                        }else if((Integer)editActions.get(i) == 4){
                            action = "split";
                        }
                    }else{
                        action = (String)editActions.get(i);
                    }
					list.add(action);
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));

	}

	/**
	 * Gets EditActionDate that match with the given concept code and edit
	 * action.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of edit action dates
	 * @throws Exception
	 */
	private Response getConceptEditActionDates(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		String action = null;
		Vector editActions = new Vector();
		ArrayList list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("action"))
					action = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			editActions = dtsrpc.getConceptEditActionDates(conceptCode, action);

			if (editActions != null) {
				for (int i = 0; i < editActions.size(); i++) {
					list.add(editActions.get(i));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return (new Response(list));

	}

	/**
	 * Retrieves all root concepts.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of DescLogicConcept
	 *         objects
	 * @throws Exception
	 */
	private Response getRootConcepts(HashMap map) throws Exception {
		String vocabularyName = null;
		boolean allAttributes = true;

		Concept[] concepts = null;
		ArrayList list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("allAttributes"))
					allAttributes = ((Boolean) map.get(key)).booleanValue();
			}

			setVocabulary(vocabularyName);

			concepts = dtsrpc.getRootConcepts(allAttributes);

			if (concepts.length > 0) {

				for (int i = 0; i < concepts.length; i++) {
					list.add(buildDescLogicConcept(concepts[i], getVocabulary(vocabularyName)));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Converts dtsrpc property objects to evs property objects
	 * 
	 * @param nciProps -
	 *            vector of dtsrpc property object
	 * @return Returns a vector of evs property objects
	 */
	private Vector convertProperties(Vector nciProps) {
		Vector properties = new Vector();
		for (int i = 0; i < nciProps.size(); i++) {
			gov.nih.nci.dtsrpc.client.Property property = (gov.nih.nci.dtsrpc.client.Property) nciProps
					.get(i);
			gov.nih.nci.evs.domain.Property p = new gov.nih.nci.evs.domain.Property();
			p.setName(property.getName());
			p.setValue(property.getValue());

			p
					.setQualifierCollection(convertQualifiers(property
							.getQualifiers()));
			properties.add(p);
		}
		return properties;
	}

	private Vector convertQualifiers(Vector dtsrpcQualifiers) {
		Vector qualifierVector = new Vector();
		for (int i = 0; i < dtsrpcQualifiers.size(); i++) {
			gov.nih.nci.evs.domain.Qualifier qualifier = new gov.nih.nci.evs.domain.Qualifier();
			gov.nih.nci.dtsrpc.client.Qualifier dtsQualifier = (gov.nih.nci.dtsrpc.client.Qualifier) dtsrpcQualifiers
					.get(i);
			qualifier.setName(dtsQualifier.getName());
			qualifier.setValue(dtsQualifier.getValue());
			qualifierVector.add(qualifier);
		}
		return qualifierVector;
	}

	/**
	 * Converts dtsrpc role objects to evs role objects
	 * 
	 * @param nciRoles
	 *            vector of dtsrpc roles
	 * @return Returns a vector of evs roles
	 */
	private Vector convertRoles(Vector nciRoles) {
		Vector roles = new Vector();
		for (int i = 0; i < nciRoles.size(); i++) {
			gov.nih.nci.dtsrpc.client.Role dtsRole = (gov.nih.nci.dtsrpc.client.Role) nciRoles
					.get(i);
			gov.nih.nci.evs.domain.Role role = new gov.nih.nci.evs.domain.Role();
			role.setName(dtsRole.getName());
			role.setValue(dtsRole.getValue());
			roles.add(role);
		}
		return roles;
	}

	/**
	 * Gets DescLogicConcept object for the given conceptname
	 * 
	 * @param map
	 * @return Returns a response that holds a DescLogicConcept in a list
	 * @throws Exception
	 */
	private Response getConceptByName(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		ArrayList list = new ArrayList();
		List dlcList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);

			}

			setVocabulary(vocabularyName);

			if (validateConceptName(conceptName)) {
				Vector conceptVector = new Vector();
				conceptVector.add(conceptName);
				DescLogicConcept dlc = buildDescLogicConcept(dtsrpc
						.findConcept(conceptName), getVocabulary(vocabularyName));
				dlcList.add(dlc);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(dlcList));
	}

	/**
	 * Searches MetaThesaurus
	 * 
	 * @param map -
	 *            specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response searchMetaThesaurus(HashMap map) throws Exception {

		String searchTerm = null;
		int limit = 10;
		String source = "*";
		boolean cui = true;
		boolean shortResult = false;
		boolean score = false;

		ArrayList list = new ArrayList();
		List uList = new ArrayList();
		COM.Lexical.Metaphrase.Concept metaConcept = null;
		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("searchTerm"))
					searchTerm = (String) map.get(key);
				else if (name.equalsIgnoreCase("limit"))
					limit = ((Integer) map.get(key)).intValue();
				else if (name.equalsIgnoreCase("source")) {
					if (map.get(key) != null) {
                        source = (String) map.get(key);
					}					
					if (source.toLowerCase().equals("all sources")) {
						source = "*";
					}
				} else if (name.equalsIgnoreCase("cui"))
					cui = ((Boolean) map.get(key)).booleanValue();
				else if (name.equalsIgnoreCase("shortResult"))
					shortResult = ((Boolean) map.get(key)).booleanValue();
				else if (name.equalsIgnoreCase("score"))
					score = ((Boolean) map.get(key)).booleanValue();
			}

			int index = 0;
			boolean checkSource = false;

			if (source != null) {
				if (!(source.length() == 0 || source.equals("*"))) {
					if (!validateSource(source)) {
						throw new DAOException(getException("Invalid source"));
					}
					checkSource = true;
				}

			}

			if (cui) {
				metaConcept = metaphrase.getConcept(searchTerm);
				COM.Lexical.Metaphrase.Source[] sources = metaConcept.sources();

				if (!(checkSource)) {
					// log.info("Searching all sources");
					try{					
						list.add(buildMetaThesaurusConcept(metaConcept));
					}catch(Exception ex){}
					
				} else {
					for (int i = 0; i < sources.length; i++) {
						String sourceSAB = sources[i].SAB().toUpperCase();
						if (sourceSAB.equalsIgnoreCase(source)) {
							try{					
								list.add(buildMetaThesaurusConcept(metaConcept));
							}catch(Exception ex){}
							break;
						}
					}

				}
			} else {

				SearchSpec spec = new SearchSpec();
				spec.setLimit(limit);

				if (source != null) {
					spec.setSource(metaphrase.source(source));
				}

				spec.setShortSearch(shortResult);
				spec.setByScore(score);
				Enumeration e = metaphrase.matches(searchTerm, spec);

				int counter = 0;
				while (e.hasMoreElements()) {
					metaConcept = ((Match) e.nextElement()).concept();
					counter++;
					if (!checkSource) {
						try{
							list.add(buildMetaThesaurusConcept(metaConcept));
						}catch(Exception ex){}						
						// log.info("Searching all sources");
					} else {
						COM.Lexical.Metaphrase.Source[] sources = metaConcept.sources();
						for (int i = 0; i < sources.length; i++) {
							String sourceSAB = sources[i].SAB().toUpperCase();
							if (sourceSAB.equalsIgnoreCase(source)) {
								try{
									list.add(buildMetaThesaurusConcept(metaConcept));
								}catch(Exception ex){}
								break;
							}
						}

					}
				}				
			}

			Hashtable hashTable = new Hashtable();

			for (int i = 0; i < list.size(); i++) {
				MetaThesaurusConcept mConcept = (MetaThesaurusConcept) list.get(i);
				hashTable.put(mConcept.getCui(), mConcept);

			}
			Iterator iterator = hashTable.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				uList.add((MetaThesaurusConcept) hashTable.get(key));
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(uList);
	}

	/**
	 * 
	 * @param metaConcept
	 * @return
	 * @throws Exception
	 */
	private boolean validateSource(String source) throws Exception {	   
		boolean valid = false;
		HashMap map = new HashMap();

		try {

			List metaSources = (List) getMetaSources(map).getResponse();
			for (int i = 0; i < metaSources.size(); i++) {
				String sourceName = ((Source) metaSources.get(i))
						.getAbbreviation();
				if (source.equalsIgnoreCase(sourceName)) {
					valid = true;
				}
			}
		} catch (Exception e) {
			String msg = null;
			if (e.getMessage() == null) {
				msg = "Invalid source - " + source;
			} else {
				msg = e.getMessage();
			}
			throw new DAOException(getException(msg));
		}

		return valid;

	}

	/**
	 * Returns MetaThesaurusConcepts for all sources
	 */
	private Response getMetaConceptsForAllSources(HashMap map) throws Exception {
		List conceptList = new ArrayList();
		try {
			for (Enumeration e = metaphrase.getSources(); e.hasMoreElements();) {
				COM.Lexical.Metaphrase.Source metaSource = (COM.Lexical.Metaphrase.Source) e
						.nextElement();
				COM.Lexical.Metaphrase.Concept metaConcept = getMetaConceptForSource(metaSource);
				try{
					conceptList.add(buildMetaThesaurusConcept(metaConcept));
				}catch(Exception ex){}				
			}
		} catch (Exception ex) {
			throw new DAOException(getException(ex.getMessage()));
		}
		return new Response(conceptList);
	}

	private Response getMetaConceptCodeForSource(HashMap map) throws Exception {
		List codeList = new ArrayList();
		String abbreviation = null;
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("sourceAbbr")) {
					if (map.get(key) == null) {
						throw new DAOException(getException("Invalid source"));
					}
					abbreviation = (String) map.get(key);
				}
			}
			if (validateSource(abbreviation)) {
				COM.Lexical.Metaphrase.Source metaSource = metaphrase
						.source(abbreviation);
				codeList.add(getMetaConceptCodeForSource(metaSource));
			}

		} catch (Exception ex) {
			throw new DAOException(getException(ex.getMessage()));
		}
		return new Response(codeList);
	}

	private String getMetaConceptCodeForSource(
			COM.Lexical.Metaphrase.Source metaSource) throws Exception {
		COM.Lexical.Metaphrase.Concept metaConcept = getMetaConceptForSource(metaSource);
		return metaConcept.conceptID();
	}

	private COM.Lexical.Metaphrase.Concept getMetaConceptForSource(           
		COM.Lexical.Metaphrase.Source metaSource) throws Exception {
		COM.Lexical.Metaphrase.Partition metaPartition = metaSource.partition();        
		return metaPartition.concept();
	}

	private Response getSourceAbbreviation(HashMap map) throws Exception {
		List sabList = new ArrayList();
		String cui = null;
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("cui")) {
					cui = (String) map.get(key);
				}
				for (Enumeration e = metaphrase.getSources(); e
						.hasMoreElements();) {
					COM.Lexical.Metaphrase.Source metaSource = (COM.Lexical.Metaphrase.Source) e
							.nextElement();
					if (cui.equals(getMetaConceptCodeForSource(metaSource))) {
						sabList.add(metaSource.SAB());
					}
				}
			}

		} catch (Exception ex) {
			throw new DAOException(getException(ex.getMessage()));
		}
		return new Response(sabList);

	}

	/**
	 * Converts COM.Lexical.Metaphrase.Concept to MetaThesaurusConcept
	 * 
	 * @param metaConcept
	 * @return Returns a MetaThesaurusConcept object
	 */
	private MetaThesaurusConcept buildMetaThesaurusConcept(
			COM.Lexical.Metaphrase.Concept metaConcept) throws Exception {
		MetaThesaurusConcept metaThesaurusConcept = new MetaThesaurusConcept();
		try {

			metaThesaurusConcept.setName(metaConcept.preferredName());
			metaThesaurusConcept.setCui(metaConcept.conceptID());
			COM.Lexical.Metaphrase.Definition[] metaDefinitions = metaConcept
					.definitions();

			ArrayList definitionsList = new ArrayList();
			for (int i = 0; i < metaDefinitions.length; ++i) {
				gov.nih.nci.evs.domain.Definition definition = new gov.nih.nci.evs.domain.Definition();
				definition.setSource(convertMetaSource(metaDefinitions[i]
						.source()));
				definition.setDefinition(metaDefinitions[i].text());
				definitionsList.add(definition);
			}

			metaThesaurusConcept.setDefinitionCollection(definitionsList);

			COM.Lexical.Metaphrase.SemanticType[] semanticTypes = metaConcept
					.semanticTypes();

			ArrayList semanticTypesList = new ArrayList();
			int j = 0;
			for (int i = 0; i < semanticTypes.length; ++i) {
				SemanticType semType = new SemanticType();
				semType.setName((String) semanticTypes[i].name());
				semType.setId((String) semanticTypes[i].TUI());
				semanticTypesList.add(semType);
			}

			ArrayList atomCollection = new ArrayList();
			COM.Lexical.Metaphrase.Atom[] atoms = metaConcept.atoms();
			// log.info("Number of atoms found = "+ atoms.length);
			for (int i = 0; i < atoms.length; i++) {
				gov.nih.nci.evs.domain.Atom atom = new Atom();
				atom.setCode(atoms[i].code());
				atom.setLui(atoms[i].LUI());
				atom.setName(atoms[i].name());
				atom.setOrigin(atoms[i].origin());
				atom.setSource(convertMetaSource(atoms[i].source()));
				atomCollection.add(atom);
			}

			metaThesaurusConcept.setSemanticTypeCollection(semanticTypesList);
			metaThesaurusConcept.setSynonymCollection(getSynonyms(metaConcept));
			metaThesaurusConcept.setSourceCollection(getSources(metaConcept));
			metaThesaurusConcept.setAtomCollection(atomCollection);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return metaThesaurusConcept;
	}

	/**
	 * Gets sources from metaphrase for the given metaphraseConcept
	 * 
	 * @param metaphraseConcept
	 * @return List of Sources
	 * @throws Exception
	 */
	private ArrayList getSources(
			COM.Lexical.Metaphrase.Concept metaphraseConcept) throws Exception {
		ArrayList sourceList = new ArrayList();
		try {
			COM.Lexical.Metaphrase.Source[] sources = metaphraseConcept
					.sources();
			for (int i = 0; i < sources.length; ++i) {
				sourceList.add(convertMetaSource(sources[i]));
			}
		} catch (COM.Lexical.Metaphrase.MetaphraseException e) {
			log.error(e.getMessage());
			throw new DAOException(
					getException("Exception from Metaphrase Server while getting sources \n"
							+ e.getMessage()));
		}
		return sourceList;
	}

	/**
	 * Gets synonyms for the given concept from metaphrase
	 * 
	 * @param metaConcept
	 * @return Returns a list of Synonyms
	 * @throws Exception
	 */
	private ArrayList getSynonyms(COM.Lexical.Metaphrase.Concept metaConcept)
			throws Exception {
		ArrayList synonyms = new ArrayList();
		try {
			Term[] terms = metaConcept.synonyms();
			for (int i = 0; i < terms.length; ++i) {
				synonyms.add(terms[i].preferredForm());
			}

		} catch (COM.Lexical.Metaphrase.MetaphraseException e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return synonyms;
	}

	/**
	 * Converts Metaphrase Source to evs source
	 * 
	 * @param metaSource
	 * @return an EVS source object
	 */
	private Source convertMetaSource(COM.Lexical.Metaphrase.Source metaSource)
			throws Exception {        
		Source source = null;
		if (metaSource != null) {
			source = new Source();
			source.setDescription(metaSource.description());
			source.setAbbreviation(metaSource.SAB());
            try{
                source.setCode(getMetaConceptCodeForSource(metaSource));
            }catch(Exception ex){}
			
		} else {
			source = null;
		}
        return source;
	}

    /** Search source by atom code
     * @param map - Specify atom code
     * @return List of MetaThesaurusConcepts
     */
    private Response searchSourceByCode(HashMap map) throws Exception{
        return searchSourceByAtomCode(map);
    }
    
	/**
	 * Search Source by atom code  
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private Response searchSourceByAtomCode(HashMap map) throws Exception {
		String code = null;
		String sourceAbbr = "*";

		ArrayList list = new ArrayList();
		COM.Lexical.Metaphrase.Concept metaConcept = null;
		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("code"))
					code = (String) map.get(key);
				else if (name.equalsIgnoreCase("sourceAbbr")){
					if(map.get(key)!=null)
                        sourceAbbr = (String) map.get(key);					
				}
					
			}
            List sourceAbbrList = new ArrayList();			
			if(sourceAbbr.equals("*") || sourceAbbr == null){
				List sourceList = getMetaSources();	
				for(int i=0; i<sourceList.size(); i++){
					gov.nih.nci.evs.domain.Source src = (gov.nih.nci.evs.domain.Source)sourceList.get(i);
					sourceAbbrList.add(src.getAbbreviation());
				}				
			}else{
				if(!validateSource(sourceAbbr)){
				    throw new DAOException ("invalid source abbreviation - "+ sourceAbbr);
				    }
				sourceAbbrList.add(sourceAbbr);
			}
			for(int s=0; s<sourceAbbrList.size(); s++){
				COM.Lexical.Metaphrase.Source source = metaphrase.source((String)sourceAbbrList.get(s));
				Partition thePartition = source.partition(code);
				if(thePartition != null)
				{
					COM.Lexical.Metaphrase.Atom[] atoms = thePartition.atoms();
					for(int i=0; i<atoms.length; i++)
					{
					  metaConcept = atoms[i].concept();
					   list.add(buildMetaThesaurusConcept(metaConcept));
					}
				}
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(list);
	}

	/**
	 * Gets all semantic types from metaphrase
	 * 
	 * @param map
	 * @return Returns a response that holds a list of SemanticTypes
	 * @throws Exception
	 */
	private Response getSemanticTypes(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		try {

			Enumeration e = metaphrase.getSemanticTypes();
			while (e.hasMoreElements()) {
				COM.Lexical.Metaphrase.SemanticType type = (COM.Lexical.Metaphrase.SemanticType) e
						.nextElement();
				SemanticType semType = new SemanticType();
				semType.setName(type.name());
				semType.setId(type.TUI());
				list.add(semType);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException("Exception occured at : \n"
					+ getClass().getName() + e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Gets MetaThesaurusConcepts for the specified source
	 * 
	 * @param map
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getConceptsBySource(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		String sourceAbbr = null;
		COM.Lexical.Metaphrase.Concept metaConcept = null;
		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String) map.get(key);
			}

			if (!StringHelper.hasValue(sourceAbbr))
				throw new DAOException(getException(" Invalid Source"));

			Enumeration e = metaphrase.getConcepts(metaphrase
					.source(sourceAbbr));
			while (e.hasMoreElements()) {
				metaConcept = (COM.Lexical.Metaphrase.Concept) e.nextElement();
				list.add(buildMetaThesaurusConcept(metaConcept));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Gets MetaThesaurusConcept name
	 * 
	 * @param map
	 * @return Returns a response that holds a MetaThesaurusConcept in a list
	 * @throws Exception
	 */
	private Response getMetaConceptNameByCode(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		COM.Lexical.Metaphrase.Concept metaConcept = null;
		String conceptName = null;
		List conceptList = new ArrayList();

		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
			}

			if (!StringHelper.hasValue(conceptCode))
				throw new DAOException(getException(" Invalid concept code"));

			metaConcept = metaphrase.getConcept(conceptCode);
			conceptName = metaConcept.preferredName();
			conceptList.add(conceptName);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
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
		return (new Response(getMetaSources()));
	}
	/**
	 * Returns all meta sources
	 */

	private List getMetaSources() throws Exception{       
		List list = new ArrayList();
		try{
	  		Enumeration e = metaphrase.getSources();            
	  		while (e.hasMoreElements())
			{
                gov.nih.nci.evs.domain.Source src = convertMetaSource((COM.Lexical.Metaphrase.Source)e.nextElement());                
				list.add(src);
			}
		}catch(Exception e){
			log.error(e.getMessage());
			throw new DAOException (getException( e.getMessage()));
		}        
		return list;
	}

	/**
	 * Gets children of the specified concept in the specified source
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getChildren(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;
		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String) map.get(key);
			}
			// list = getRelatedConcepts(conceptCode, sourceAbbr, "isa");
			list = getRelatedConcepts(conceptCode, sourceAbbr, "CHD");

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Gets Parent concepts for the specified concept in a given source
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getParent(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;
		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String) map.get(key);
			}

			// list = getRelatedConcepts(conceptCode, sourceAbbr,
			// "inverse_isa");
			list = getRelatedConcepts(conceptCode, sourceAbbr, "PAR");

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Retrievs MetaThesaurusConcepts based on the specified code and relation
	 * in a given source
	 * 
	 * @param code
	 * @param source
	 * @param relation
	 * @return
	 * @throws Exception
	 */
	private ArrayList getRelatedConcepts(String conceptCode, String sourceAbbr,
			String relation) throws Exception {
		ArrayList uniqueList = new ArrayList();
		Set list = new HashSet();
		COM.Lexical.Metaphrase.Concept metaConcept = null;
		Vector rc = new Vector();

		try {
			if (!StringHelper.hasValue(conceptCode)) {
				throw new DAOException(
						getException(" Invalid code, set concept code"));
			}
			if (!StringHelper.hasValue(sourceAbbr)) {
				sourceAbbr = "*";
			}

			metaConcept = metaphrase.getConcept(conceptCode);
			Relationship[] relationships = metaConcept.relationships();
			COM.Lexical.Metaphrase.Concept relatedConcept = null;
			Vector temp = new Vector();
			String conceptId = "";

			for (int i = 0; i < relationships.length; i++) {
				if (relation != null) {
					if (validateRelation(relation)) {
						if (relationships[i].rel().equalsIgnoreCase(relation)) {
							if (sourceAbbr != null && !sourceAbbr.equals("*")) {
								if (relationships[i].source().SAB()
										.equalsIgnoreCase(sourceAbbr)) {
									relatedConcept = relationships[i]
											.concept2();
									rc.add(relatedConcept);
								}
							} else {
								relatedConcept = relationships[i].concept2();
								rc.add(relatedConcept);
							}

						}
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		for (int i = 0; i < rc.size(); i++) {
			list
					.add(buildMetaThesaurusConcept((COM.Lexical.Metaphrase.Concept) rc
							.get(i)));
		}
		for (Iterator i = list.iterator(); i.hasNext();) {
			uniqueList.add(i.next());
		}

		return uniqueList;
	}

	/**
	 * Gets concepts more general than the specified concept
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getBroaderConcepts(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;
		Vector rb = new Vector();

		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String) map.get(key);
			}
			list = getRelatedConcepts(conceptCode, sourceAbbr, "RB");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(list);
	}

	/**
	 * Gets concepts more specific than the specified concept
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getNarrowerConcepts(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String sourceAbbr = null;
		COM.Lexical.Metaphrase.Concept metaConcept = null;
		Vector rn = new Vector();

		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String) map.get(key);

			}

			list = getRelatedConcepts(conceptCode, sourceAbbr, "RN");

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return new Response(list);
	}

	/**
	 * Gets MetaThesaurusConcepts related to the specified concept
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getRelatedConcepts(HashMap map) throws Exception {
		ArrayList uniqueList = new ArrayList();
		Set list = new HashSet();

		String conceptCode = null;
		String sourceAbbr = null;
		COM.Lexical.Metaphrase.Concept metaConcept = null;
		Vector rc = new Vector();
		String relation = null;

		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("sourceAbbr"))
					sourceAbbr = (String) map.get(key);
				else if (name.equalsIgnoreCase("relation"))
					relation = (String) map.get(key);

			}

			metaConcept = metaphrase.getConcept(conceptCode);
			Relationship[] relationships = metaConcept.relationships();
			COM.Lexical.Metaphrase.Concept relatedConcept = null;

			String conceptId = "";

			for (int i = 0; i < relationships.length; i++) {
				if (relation != null) {
					if (validateRelation(relation)) {
						if (relationships[i].rel().equalsIgnoreCase(relation)) {
							if (sourceAbbr != null && !sourceAbbr.equals("*")) {
								if (relationships[i].source().SAB()
										.equalsIgnoreCase(sourceAbbr)) {
									relatedConcept = relationships[i]
											.concept2();
									rc.add(relatedConcept);
								}
							} else {
								relatedConcept = relationships[i].concept2();
								rc.add(relatedConcept);
							}

						}
					}
				} else {
					relatedConcept = relationships[i].concept2();

					if (sourceAbbr != null && !sourceAbbr.equals("*")) {
						if (relationships[i].source().SAB().equalsIgnoreCase(
								sourceAbbr)) {
							conceptId = relatedConcept.conceptID();

							rc.add(relatedConcept);

						}
					} else {
						conceptId = relatedConcept.conceptID();
						rc.add(relatedConcept);

					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		for (int i = 0; i < rc.size(); i++) {
			list
					.add(buildMetaThesaurusConcept((COM.Lexical.Metaphrase.Concept) rc
							.get(i)));
		}
		for (Iterator i = list.iterator(); i.hasNext();) {
			uniqueList.add(i.next());
		}

		return new Response(uniqueList);
	}

	/**
	 * Gets the concepts of specified category for the given concept category
	 * can be "Medications", "Procedures", "Laboratory", "Diagnosis"
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of MetaThesaurusConcepts
	 * @throws Exception
	 */
	private Response getConceptsByCategories(HashMap map) throws Exception {
		ArrayList list = new ArrayList();
		String conceptCode = null;
		String category = null;
		COM.Lexical.Metaphrase.Concept metaConcept = null;

		try {

			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("category"))
					category = (String) map.get(key);
			}

			if (!StringHelper.hasValue(conceptCode))
				throw new DAOException(getException(" invalid concept code "));

			if (!StringHelper.hasValue(category))
				throw new DAOException(
						getException(" Invalid Category - set value"));

			metaConcept = metaphrase.getConcept(conceptCode);

			Cooccurrence[] categoryValues = null;
			if (category.equalsIgnoreCase("Medications"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.MED,
						metaphrase.defaultCooccurClassifier);
			else if (category.equalsIgnoreCase("Procedures"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.PROC,
						metaphrase.defaultCooccurClassifier);
			else if (category.equalsIgnoreCase("Laboratory"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.LAB,
						metaphrase.defaultCooccurClassifier);
			else if (category.equalsIgnoreCase("Diagnosis"))
				categoryValues = metaConcept.cooccurs(Cooccurrence.DIAG,
						metaphrase.defaultCooccurClassifier);

			for (int i = 0; i < categoryValues.length; i++) {
				metaConcept = categoryValues[i].concept2();

				list.add(buildMetaThesaurusConcept(metaConcept));
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(list);
	}

	/**
	 * Returns a Boolean value if the specified concept contains inverse roles
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a Boolean value in a list
	 * @throws Exception
	 */

	private Response containsInverseRole(HashMap map) throws Exception {

		String vocabularyName = null;
		String roleName = null;
		String roleValue = null;
		String conceptName = null;

		Boolean inverseRole = Boolean.FALSE;

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("roleName"))
					roleName = (String) map.get(key);
				else if (name.equalsIgnoreCase("roleValue"))
					roleValue = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);

			}

			setVocabulary(vocabularyName);
			inverseRole = dtsrpc.containsInverseRole(roleName, roleValue,
					conceptName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		List booleanList = new ArrayList();
		booleanList.add(inverseRole);

		return new Response(booleanList);

	}

	/**
	 * Returns a Boolean value if the specified concept contains roles
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a Boolean value in a list
	 * @throws Exception
	 */
	private Response containsRole(HashMap map) throws Exception {

		String vocabularyName = null;
		String roleName = null;
		String roleValue = null;
		String conceptName = null;

		Boolean inverseRole = Boolean.FALSE;

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("roleName"))
					roleName = (String) map.get(key);
				else if (name.equalsIgnoreCase("roleValue"))
					roleValue = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);

			}

			setVocabulary(vocabularyName);
			inverseRole = dtsrpc.containsRole(roleName, roleValue, conceptName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		List booleanList = new ArrayList();
		booleanList.add(inverseRole);

		return new Response(booleanList);

	}

	/**
	 * Fetches the properties of a given concept that belongs to the given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of properties
	 * @throws Exception
	 */
	private Response fetchDTSProperties(HashMap map) throws Exception {
		String vocabularyName = null;
		String term = null;
		Vector DTSProperties = new Vector();
		List list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("term"))
					term = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			DTSProperties = dtsrpc.fetchDTSProperties(term);

			for (int i = 0; i < DTSProperties.size(); i++) {
				list.add(DTSProperties.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Fetches term association data for the specified concept from a given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response fetchTermAssociations(HashMap map) throws Exception {
		String vocabularyName = null;
		String term = null;
		Vector associations = new Vector();
		List list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("term"))
					term = (String) map.get(key);
			}

			setVocabulary(vocabularyName);

			associations = dtsrpc.fetchTermAssociations(term);

			for (int i = 0; i < associations.size(); i++) {
				list.add(associations.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return (new Response(list));
	}

	/**
	 * Gets all association types for the specified concept from a given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllAssociationTypes(HashMap map) throws Exception {

		String vocabularyName = null;
		Vector associations = new Vector();
		List associationList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}

			setVocabulary(vocabularyName);
			associations = dtsrpc.getAllAssociationTypes();
			for (int i = 0; i < associations.size(); i++) {
				associationList.add((String) associations.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(associationList);
	}

	/**
	 * Gets all concept association qualifier types for the specified concept
	 * from a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllConceptAssociationQualifierTypes(HashMap map)
			throws Exception {

		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);

			types = dtsrpc.getAllConceptAssociationQualifierTypes();
			for (int i = 0; i < types.size(); i++) {

				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all concept association types for the specified concept from a given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllConceptAssociationTypes(HashMap map) throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllConceptAssociationTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all concept property qualifier types for the specified concept from
	 * a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllConceptPropertyQualifierTypes(HashMap map)
			throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllConceptPropertyQualifierTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all concept property types for the specified concept from a given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllConceptPropertyTypes(HashMap map) throws Exception {

		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllConceptPropertyTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all licenses for the specified concept from a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllLicenses(HashMap map) throws Exception {
		String vocabularyName = null;
		String condition = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				if (name.equalsIgnoreCase("condition"))
					condition = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllLicenses(condition);
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all qualifier types for the specified concept from a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllQualifierTypes(HashMap map) throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllQualifierTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all the Role names for the specified concept from a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllRoleNames(HashMap map) throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllRoleNames();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException("\nException occured at "
					+ getClass().getName() + e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all sub-concept codes for the specified concept from a given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllSubConceptCodes(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllSubConceptCodes(conceptCode);
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all sub-concept names for the specified concept from a given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllSubConceptNames(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllSubConceptNames(conceptName);
			for (int i = 0; i < types.size(); i++) {
				typeList.add((String) types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all synonym types for the specified concept from a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllSynonymTypes(HashMap map) throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllSynonymTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all synonym types for the specified concept from a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllSilos(HashMap map) throws Exception {

		String vocabularyName = null;
		List siloList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			gov.nih.nci.dtsrpc.client.Silo[] silos = dtsrpc.getAllSilos();
			for (int i = 0; i < silos.length; i++) {
				siloList.add(convertSilo(silos[i]));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(siloList);
	}

	private gov.nih.nci.evs.domain.Silo convertSilo(
		gov.nih.nci.dtsrpc.client.Silo dtsSilo) {
		gov.nih.nci.evs.domain.Silo silo = new gov.nih.nci.evs.domain.Silo();
		silo.setId(dtsSilo.getId());
		silo.setName(dtsSilo.getName());		
		return silo;
	}

	/**
	 * Gets all term association qualifier types for the specified concept from
	 * a given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllTermAssociationQualifierTypes(HashMap map)
			throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllTermAssociationQualifierTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all term property qualifier types for the specified concept from a
	 * given namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllTermPropertyQualifierTypes(HashMap map)
			throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllTermPropertyQualifierTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Gets all term property types for the specified concept from a given
	 * namespace
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */

	private Response getAllTermPropertyTypes(HashMap map) throws Exception {
		String vocabularyName = null;
		Vector types = new Vector();
		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);
			types = dtsrpc.getAllTermPropertyTypes();
			for (int i = 0; i < types.size(); i++) {
				typeList.add(types.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Converts a dtsrpc Concept object to a DescLogicConcept object
	 * 
	 * @param concept
	 *            dtsrpc concept object
	 * @return Returns a response
	 * @throws Exception
	 */

	private DescLogicConcept buildDescLogicConcept(
			gov.nih.nci.dtsrpc.client.Concept concept, Vocabulary vocabulary) {
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
		dlc.setAssociationCollection(convertAssociations(concept
				.getAssociationCollection()));
		dlc.setInverseAssociationCollection(convertAssociations(concept
				.getInverseAssociationCollection()));

		if (concept.getTreeNode() != null) {
			dlc.setEdgeProperties(convertEdgeProperties(concept));
			dlc.setTreeNode(convertTreeNode(concept));
		}
		dlc.setSemanticTypeVector(getDLSemanticTypes(dlc.getPropertyCollection()));
		dlc.setVocabulary(vocabulary);
		return dlc;
	}

	private Vector convertAssociations(Vector dtsrpcAssociations) {
		Vector associationVector = new Vector();		
		for (int i = 0; i < dtsrpcAssociations.size(); i++) {
			gov.nih.nci.evs.domain.Association association = new Association();
			gov.nih.nci.dtsrpc.client.ConceptAssociation dtsAssociation = (gov.nih.nci.dtsrpc.client.ConceptAssociation) dtsrpcAssociations
					.get(i);
			association.setName(dtsAssociation.getName());
			association.setValue(dtsAssociation.getValue());			
			association.setQualifierCollection(convertQualifiers(dtsAssociation
					.getQualifiers()));
			associationVector.add(association);
		}
		return associationVector;
	}

	private EdgeProperties convertEdgeProperties(
			gov.nih.nci.dtsrpc.client.Concept concept) {
		EdgeProperties edgeProperties = new EdgeProperties();
		edgeProperties.setName(concept.getTreeNode().getName());
		edgeProperties.setIsA(concept.getTreeNode().getIsA());
		edgeProperties.setLinks(concept.getLinks());
		edgeProperties.setTraverseDown(concept.getTraverseDown());
		return edgeProperties;
	}

	private gov.nih.nci.evs.domain.TreeNode convertTreeNode(
			gov.nih.nci.dtsrpc.client.Concept concept) {
		gov.nih.nci.evs.domain.TreeNode treeNode = new gov.nih.nci.evs.domain.TreeNode();
		treeNode.setName(concept.getTreeNode().getName());
		treeNode.setIsA(concept.getTreeNode().getIsA());
		treeNode.setLinks(concept.getLinks());
		treeNode.setTraverseDown(concept.getTraverseDown());
		return treeNode;
	}

	/**
	 * Retrievs all the parent concepts for a given concept
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of DescLogicConcepts
	 * @throws Exception
	 */

	private Response getParentConcepts(HashMap map) throws Exception {

		String vocabularyName = null;
		String conceptName = null;
		boolean inputFlag = false;

		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
				else if (name.equalsIgnoreCase("inputFlag"))
					inputFlag = ((Boolean) map.get(key)).booleanValue();

			}
			setVocabulary(vocabularyName);

			Vector conceptNames = new Vector();

			if (!inputFlag) {
				if (validateConceptName(conceptName)) {
					conceptNames = dtsrpc.getSuperConcepts(conceptName, false,
							false);
				}
			} else {
				if (validateDLConceptCode(conceptName)) {
					conceptNames = dtsrpc.getSuperConcepts(conceptName, true,
							false);
				}
			}
			Concept[] superConcepts = dtsrpc.getConcepts(conceptNames);

			for (int i = 0; i < superConcepts.length; i++) {
				DescLogicConcept dlc = buildDescLogicConcept(superConcepts[i], getVocabulary(vocabularyName));
				typeList.add(dlc);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Retrievs all the child DescLogicConcepts for a given conceptName
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response that holds a list of DescLogicConcepts
	 * @throws Exception
	 */

	private Response getChildConcepts(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		boolean inputFlag = false;

		List typeList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);
				else if (name.equalsIgnoreCase("inputFlag"))
					inputFlag = ((Boolean) map.get(key)).booleanValue();

			}
			setVocabulary(vocabularyName);

			Concept[] concepts = null;

			if (!inputFlag) {
				validateConceptName(conceptName);
				concepts = dtsrpc.getDirectSubs(conceptName, true);
			} else {
				validateDLConceptCode(conceptName);
				concepts = dtsrpc.getDirectSubs(conceptName, true, 1, dtsrpc
						.getNamespaceId(vocabularyName));
			}

			for (int i = 0; i < concepts.length; i++) {
				DescLogicConcept dlc = new DescLogicConcept();
				dlc = buildDescLogicConcept(concepts[i], getVocabulary(vocabularyName));
				typeList.add(dlc);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(typeList);
	}

	/**
	 * Returns a Boolean value if the specified concept has a child concept
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */
	private Response hasChildren(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		List hasList = new ArrayList();

		Boolean found = new Boolean(false);
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);

			if (validateConceptName(conceptName)) {

				found = dtsrpc.hasChildren(conceptName);
				hasList.add(found);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(hasList);

	}

	/**
	 * Returns a Boolean value if the given concept has a Parent concept
	 * 
	 * @param map
	 *            Specifies the input parameters
	 * @return Returns a response
	 * @throws Exception
	 */
	private Response hasParents(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		List hasList = new ArrayList();

		Boolean found = new Boolean(false);
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptName"))
					conceptName = (String) map.get(key);

			}
			setVocabulary(vocabularyName);

			if (validateConceptName(conceptName)) {

				found = dtsrpc.hasParents(conceptName);
				hasList.add(found);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(hasList);

	}

	/**
	 * Checks if the concept name is valid
	 * 
	 * @param conceptName
	 * @return boolean value
	 * @throws Exception
	 */
	private boolean validateConceptName(String conceptName) throws Exception {

		boolean valid = true;

		try {
			if (conceptName == null) {
				valid = false;
				throw new DAOException(
						getException("Exception : Concept name cannot be a null value"));
			} else if (dtsrpc.getConceptCodeByName(conceptName) == null) {
				valid = false;
				throw new DAOException(
						getException("Exception : Invalid concept name - "
								+ conceptName));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return valid;
	}

	/**
	 * Checks if the relationship type is valid
	 * 
	 * @param relation
	 * @return boolean value
	 * @throws Exception
	 */
	private boolean validateRelation(String relation) throws Exception {
		boolean valid = false;
		String[] relationType = { "RB", "RN", "RO", "RL", "PAR", "CHD", "SIB",
				"AQ", "QB" };

		for (int i = 0; i < relationType.length && !valid; i++) {
			if (relation.equalsIgnoreCase(relationType[i])) {
				valid = true;
			}
		}
		if (!valid) {
			log.error(" Invalid relation attribute");
			throw new DAOException(getException(" Invalid relation attribute "));
		}
		return valid;

	}

	/**
	 * Gets the concept name for the specified code
	 * 
	 * @param conceptCode -
	 *            specifies the concept code
	 * @return Returns the concept name
	 */

	private Response getConceptNameByCode(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptName = null;
		String conceptCode = null;
		List list = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);

			}

			setVocabulary(vocabularyName);

			if (conceptCode == null) {
				log.error(" Concept code cannot be a null value");
				throw new DAOException(getException(" Invalid concept code"));
			}
			conceptName = dtsrpc.getConceptNameByCode(conceptCode);
			list.add(conceptName);
		} catch (Exception e) {
			log.error("Exception - getConceptNameByCode " + e.getMessage());
			throw new DAOException(getException(" Exception - "
					+ e.getMessage()));
		}

		return (new Response(list));

	}

	private Vector getDLSemanticTypes(Vector properties) {
		Vector semanticTypes = new Vector();
		for(int i=0; i< properties.size(); i++){
			Property p = (Property) properties.get(i);
			if(p.getName().equalsIgnoreCase("Semantic_Type")){
				semanticTypes.add(p.getValue());
			}
		}		
		return semanticTypes;
	}

	private Response getAllPropertyTypes(HashMap map) throws Exception {

		String vocabularyName = null;

		List propertyList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}

			setVocabulary(vocabularyName);
			PropertyType[] propertyTypes = dtsrpc.getAllPropertyTypes();
			for (int i = 0; i < propertyTypes.length; i++) {
				propertyList.add(propertyTypes[i].getName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(propertyList);

	}

	private Exception getException(String msg) {
		Exception ex = null;
		if (msg == null) {
			ex = pastEx;
		} else {
			pastEx = new DAOException(msg);
		}
		return ex;
	}

	private Exception getException(Exception ex) {
        String msg = null;
        if(ex.getMessage() == null){
            ex = pastEx;
        }
        else{
            pastEx = ex;
        } 		
		return ex;
	}

	private boolean validateDLConceptCode(String code) throws Exception {

		boolean valid = true;

		try {
			if (code == null) {
				valid = false;
				throw new DAOException(
						getException("Exception : Concept code cannot be a null value"));
			} else if (dtsrpc.getConceptNameByCode(code) == null) {
				valid = false;
				throw new DAOException(
						getException("Exception : Invalid concept code - "
								+ code));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return valid;
	}

	private Response getHistoryRecords(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		Date initialDate = null;
		Date finalDate = null;

		Vector v = new Vector();
		List list = new ArrayList();

		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("VocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("initialDate"))
					initialDate = (Date) map.get(key);
				else if (name.equalsIgnoreCase("finalDate"))
					finalDate = (Date) map.get(key);
			}

			setVocabulary(vocabularyName);
			if (conceptCode != null) {
				validateDLConceptCode(conceptCode);
			}

			if (vocabularyName != null && initialDate != null
					&& finalDate != null && conceptCode != null) {
				v = dtsrpc.getHistoryRecords(vocabularyName, initialDate,
						finalDate, conceptCode);
			} else if (vocabularyName != null && initialDate != null
					&& finalDate != null && conceptCode == null) {
				v = dtsrpc.getHistoryRecords(vocabularyName, initialDate,
						finalDate);
			} else if (vocabularyName != null && initialDate == null
					&& finalDate == null && conceptCode != null) {
				v = dtsrpc.getHistoryRecords(vocabularyName, conceptCode);
			} else {
				throw new DAOException("Exception: Invalid arguments");
			}

			if (v != null) {
				Vector historyVector = new Vector();
				String code = ((gov.nih.nci.dtsrpc.client.HistoryRecord) v
						.get(0)).getCode();
				for (int i = 0; i < v.size(); i++) {
					gov.nih.nci.dtsrpc.client.HistoryRecord dtsHistory = (gov.nih.nci.dtsrpc.client.HistoryRecord) v
							.get(i);
					if (!((gov.nih.nci.dtsrpc.client.HistoryRecord) v.get(i))
							.getCode().equals(code)) {
						gov.nih.nci.evs.domain.HistoryRecord historyRecord = new gov.nih.nci.evs.domain.HistoryRecord();
						historyRecord.setDescLogicConceptCode(code);
						historyRecord.setHistoryCollection(historyVector);
						list.add(historyRecord);
						code = ((gov.nih.nci.dtsrpc.client.HistoryRecord) v
								.get(i)).getCode();
					}
					gov.nih.nci.evs.domain.History history = convertHistory(dtsHistory);
					historyVector.add(history);
				}
				if (code != null) {
					gov.nih.nci.evs.domain.HistoryRecord historyRecord = new gov.nih.nci.evs.domain.HistoryRecord();
					historyRecord.setDescLogicConceptCode(code);
					historyRecord.setHistoryCollection(historyVector);
					list.add(historyRecord);
				}

			}
		} catch (Exception e) {
			throw new DAOException(getException(e));

		}
		return new Response(list);

	}

	private gov.nih.nci.evs.domain.History convertHistory(
		gov.nih.nci.dtsrpc.client.HistoryRecord dtsHistory) {
		gov.nih.nci.evs.domain.History history = new gov.nih.nci.evs.domain.History();
		history.setEditAction(dtsHistory.getEditAction());
		history.setEditActionDate(dtsHistory.getEditDate());
		history.setReferenceCode(dtsHistory.getReference());
		history.setNamespaceId(dtsHistory.getNamespaceId());

		return history;
	}

	private Response getHistoryStartDate(HashMap map) throws Exception {
		String vocabularyName = null;
		List dateList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);

			}

			setVocabulary(vocabularyName);
			Date startDate = dtsrpc.getHistoryStartDate();
			dateList.add(startDate);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}

		return new Response(dateList);
	}

	private Response getHistoryEndDate(HashMap map) throws Exception {
		String vocabularyName = null;
		List dateList = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
			}

			setVocabulary(vocabularyName);
			Date endDate = dtsrpc.getHistoryEndDate();
			dateList.add(endDate);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return new Response(dateList);
	}

	private Response getHistoryDates(HashMap map) throws Exception {
		List dateList = new ArrayList();
		Vector dates = dtsrpc.getHistoryDates();
		for (int i = 0; i < dates.size(); i++) {
			dateList.add(dates.get(i));
		}
		return new Response(dateList);
	}

	private Date stringToDate(String aString) throws Exception {
		Date theDate = null;
		try {
			SimpleDateFormat sdf = null;
			if (aString.indexOf("-") > 3) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			} else if (aString.indexOf("/") > 3) {
				sdf = new SimpleDateFormat("yyyy/MM/dd");
			} else {
				sdf = new SimpleDateFormat("MM/dd/yyyy");
			}

			theDate = sdf.parse(aString);
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
			throw new Exception(getException(e.getMessage()));
		}
		return theDate;

	}

	/**
	 * Gets all child concept codes for the specified concept based on the
	 * action.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of concept codes
	 * @throws Exception
	 */
	private Response getCodeActionChildren(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		String action = null;
		Date baseLineDate = null;
		Vector children = new Vector();
		ArrayList list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("baseLineDate"))
					baseLineDate = stringToDate((String) map.get(key));
				else if (name.equalsIgnoreCase("action"))
					action = (String) map.get(key);
			}
			setVocabulary(vocabularyName);
			if (action == null) {
				children = dtsrpc.getCodeActionChildren(conceptCode,
						baseLineDate);
			} else {
				children = dtsrpc.getCodeActionChildren(conceptCode,
						baseLineDate);
			}

			for (int i = 0; i < children.size(); i++) {
				list.add(children.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return (new Response(list));

	}

	/**
	 * Gets all parent concept codes for the specified concept.
	 * 
	 * @param map -
	 *            Specifies the input parameters
	 * @return - Returns a response that holds a list of concept codes
	 * @throws Exception
	 */
	private Response getCodeActionParents(HashMap map) throws Exception {
		String vocabularyName = null;
		String conceptCode = null;
		Date baseLineDate = null;
		Vector parents = new Vector();
		ArrayList list = new ArrayList();
		try {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String name = key.substring(key.indexOf("$") + 1, key.length());

				if (name.equalsIgnoreCase("vocabularyName"))
					vocabularyName = (String) map.get(key);
				else if (name.equalsIgnoreCase("conceptCode"))
					conceptCode = (String) map.get(key);
				else if (name.equalsIgnoreCase("baseLineDate"))
					baseLineDate = stringToDate((String) map.get(key));
				;
			}
			setVocabulary(vocabularyName);
			parents = dtsrpc.getCodeActionParents(conceptCode, baseLineDate);
			for (int i = 0; i < parents.size(); i++) {
				list.add(parents.get(i));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DAOException(getException(e.getMessage()));
		}
		return (new Response(list));

	}

}
