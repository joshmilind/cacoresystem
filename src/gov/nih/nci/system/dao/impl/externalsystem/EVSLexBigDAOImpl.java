package gov.nih.nci.system.dao.impl.externalsystem;

import java.text.*;
import java.util.*;
import java.lang.reflect.*;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.log4j.*;

import gov.nih.nci.evs.domain.*;
import gov.nih.nci.evs.security.*;
import gov.nih.nci.evs.query.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.common.net.*;
import gov.nih.nci.system.dao.*;
import gov.nih.nci.system.dao.cache.EVSCacheManager;
import gov.nih.nci.system.dao.properties.EVSProperties;
import gov.nih.nci.system.dao.security.*;

import msso.validator.MSSOUserValidator;
import org.LexGrid.LexBIG.admin.*;
import gov.nih.nci.lexrpc.client.*;
import gov.nih.nci.lexrpc.client.EditActionDate;
import gov.nih.nci.lexrpc.server.LexAdapter;
import gov.nih.nci.evs.security.*;

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
    private static HashMap <String, LexAdapter> adapters = new HashMap<String, LexAdapter>();

    private static HashMap <Integer, String> namespaceId2VocabularyName_Map = new HashMap<Integer, String>();

    private static HashMap <String, Vocabulary>vocabularies = new HashMap<String, Vocabulary>();
    private final static String defaultVocabularyName = "NCI_Thesaurus";
    private final static String metaVocabularyName = "NCI MetaThesaurus";
    private ThreadLocal threadLocal = new ThreadLocal();

    /**
     * Instantiates an EVSLexBigDAOImpl instance
     */
   public EVSLexBigDAOImpl(){

   }

    /**
	 * Creates a connection to the EVS datasource and generates a query based on the request.
	 * The results are wrapped in a response object
	 * @param r - Request object (This object holds the evs query)
	 * @return - The results are stored in a list and returned as a response object
	 * @throws DAOException
	 * @throws Exception
	 */

    public Response query(Request r) throws DAOException {
        Object obj = r.getRequest();
        List resultList = new ArrayList();
        HashMap tokenCollection = new HashMap();
        gov.nih.nci.common.net.Response response = new Response();

        try {
            Hashtable configs = r.getConfig();
            EVSQueryImpl criteria = (EVSQueryImpl) obj;
            if (criteria.getSecurityTokenCollection().size() > 0
                    && criteria.getSecurityTokenCollection() != null) {
                    tokenCollection = criteria.getSecurityTokenCollection();
            }
            HashMap mapValues = new HashMap();
            if(((HashMap)criteria.getClass().getField("descLogicValues").get(criteria)).size()>0){
                mapValues = (HashMap)criteria.getClass().getField("descLogicValues").get(criteria);
            }else if(((HashMap)criteria.getClass().getField("metaThesaurusValues").get(criteria)).size()>0){
                mapValues = (HashMap)criteria.getClass().getField("metaThesaurusValues").get(criteria);
            }
            try {
                response = queryEVS(configs, tokenCollection, mapValues);
            } catch (Exception ex) {
                DAOException e = null;
                if(ex.getMessage() == null) {
                    if(threadLocal.get() == null){
                        e = new DAOException("Exception in method: "+getMethodName(mapValues) +" - unable to execute query") ;
                    }else{
                        e = (DAOException)threadLocal.get();
                    }
                } else {
                    e = new DAOException(ex.getMessage());
                }
                threadLocal.remove();
                log.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return response;
    }

    /**
     * Returns the vocabulary name specified
     * @param mapValues
     * @return vocabulary name
     */
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
    /**
     * Returns the method name specified in the hashmap
     * @param mapValues
     * @return Method name specified in the map
     */

    private String getMethodName(HashMap mapValues){
        String methodName = null;
        Iterator iter = mapValues.keySet().iterator();
        String keyMethod = (String) iter.next();
        if(keyMethod != null){
            methodName = keyMethod.indexOf("$") > 0 ? keyMethod.substring(0, keyMethod.indexOf("$")): keyMethod;
        }
        return methodName;
    }

    /**
     * Generates a cache key based on the method name and map values
     * @param methodName
     * @param mapValues
     * @return cache key
     */
    private String getCacheKey(String methodName,HashMap mapValues){
        String key = methodName;
        for(Iterator it = mapValues.keySet().iterator(); it.hasNext();){
            String name = (String)it.next();
            Object value = mapValues.get(name);
            key += "_"+String.valueOf(value);
        }
        return key;
    }

    /**
     * Returns true if the enableCache value in the config file is set to true
     * @param config
     * @return true if enableCache is set to true
     */
    private boolean enableCache(Hashtable config){
        boolean enableCache = true;
        if(config.get("enableCache")!=null){
            try{
                enableCache = Boolean.valueOf((String)config.get("enableCache")).booleanValue();
            }catch(Exception ex){}
        }
        return enableCache;
    }

    /**
     * Executes the query
     * @param config - specifies the values set in the config file
     * @param tokenCollection - specifies the vocabulary tokens
     * @param mapValues - specifies the search method and parameters
     * @return result list is returned in a Response object
     */
    private Response queryEVS(Hashtable config, HashMap tokenCollection, HashMap mapValues) throws Exception{
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
        }else{
            try{
                if(adapters.get(metaVocabularyName)!= null){
                    key += "_"+ metaVocabularyName;
                }else{
                    key += "_"+ defaultVocabularyName;
                }
            }catch(Exception ex){
                key += "_"+ defaultVocabularyName;
            }
        }
        if(security != null){
            SecurityToken securityToken = null;
            if(tokenCollection.size()>0){
                securityToken = (SecurityToken)tokenCollection.get(vocabularyName);
            }
            if(securityToken==null){
                Exception ex = new gov.nih.nci.system.applicationservice.SecurityException("Permission denied - Please set SecurityToken for "+ vocabularyName);
                log.error(ex.getMessage());
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
            if(adapters.size()<1){
                makeRemoteConnection(config);
            }
            response = (Response) method.invoke(this,new Object[] { mapValues });
            //update cache
            if(checkCache){
                evsCache.put(key, response, methodName);
            }
        }catch(Exception ex){
            log.error( methodName + " throws Exception ");
            throw new DAOException(getException(ex.getMessage()));
        }
        return response;
    }

    /**
     * Creates a connection to the EVS Servers
     * @param configs specifies the values set in the config file
     * @param vocabularyName specifies the vocabulary name
     */
    private void makeRemoteConnection(Hashtable configs)throws Exception{
        try{
            if(adapters.size()<1){
                LexAdapter adapter = null;
                Vector vocabs = new Vector();
                EVSProperties evsProperties = EVSProperties.getInstance(configs);
                String lg_config_file = null;
                if(evsProperties.getConfigFileLocation() != null){
                    lg_config_file = evsProperties.getConfigFileLocation();
                    System.setProperty("LG_CONFIG_FILE", lg_config_file);
                }
                log.debug("CONFIG FILE LOCATION: "+ lg_config_file);
                try{
                    adapter = new LexAdapter();
                    adapter.setVocabulary(defaultVocabularyName);
                    vocabs = adapter.getVocabularyNames();
                }catch(Exception ex){
                    log.error("Unable to connect to LexBig Server - check server log for details\n"+ ex.getMessage() );
                }
                for(int i=0; i<vocabs.size(); i++){
                    String vocabName = (String)vocabs.get(i);
                    LexAdapter lexAdapter = new LexAdapter();
                    try{
                        lexAdapter.setVocabulary(vocabName);
                        adapters.put(vocabName, lexAdapter);
                        Vocabulary vocabulary = new Vocabulary();
                        vocabulary.setName(vocabName);
                        vocabulary.setDescription(lexAdapter.getVocabularyDescription(vocabName));
                        vocabulary.setNamespaceId(lexAdapter.getNamespaceId(vocabName));
                        vocabularies.put(vocabName, vocabulary);
                    }catch(Exception ex){
                        log.error("Unable to connect to "+ vocabName);
                        continue;
                    }
                }
            }
        }catch(Exception ex){
            throw new DAOException(getException(ex));
        }
        }
    /**
     * Returns an instance of LexAdapter for the specified vocabulary
     * @param vocabularyName
     */
    private LexAdapter getLexAdapter(String vocabularyName) throws DAOException{
        LexAdapter lexAdapter = null;
        if(adapters.get(vocabularyName)==null){
            try{
                lexAdapter = new LexAdapter();
                lexAdapter.setVocabulary(vocabularyName);
                adapters.put(vocabularyName, lexAdapter);
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setName(vocabularyName);
                vocabulary.setDescription(lexAdapter.getVocabularyDescription(vocabularyName));
                vocabulary.setNamespaceId(lexAdapter.getNamespaceId(vocabularyName));
                vocabularies.put(vocabularyName, vocabulary);
            }catch(Exception ex){
                log.error(vocabularyName +" " + ex.getMessage());
                throw new DAOException(getException("Unable to connect to Vocabulary "+ vocabularyName));
            }
        }else{
          lexAdapter = (LexAdapter)adapters.get(vocabularyName);
        }
        return lexAdapter;
    }
    /**
     * Returns a LexAdapter instance for the MetaThesaurus
     * @return
     * @throws Exception
     */
    private LexAdapter getLexAdapterForMeta() throws Exception{
        LexAdapter adapter = null;
        try{
            if(adapters.get(metaVocabularyName)!=null){
                adapter = (LexAdapter)adapters.get(metaVocabularyName);
            }
        }catch(Exception ex){
            log.debug("Connection error "+metaVocabularyName +"\t"+ex.getMessage());
        }
            if(adapter == null){
                adapter = getLexAdapter(defaultVocabularyName);
                log.debug(metaVocabularyName +" not found - \tConnecting to "+ defaultVocabularyName);
            }
        return adapter;
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

 			LexAdapter adapter = getLexAdapter(vocabularyName);
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
                throw new SecurityException(getException("Please specify security token to access "+ vocabularyName));
            }
            try{
                valid = validateToken(security, securityToken);
            }
            catch(Exception ex){
                valid = false;
            }
            validList.add(valid);
        }else{
            throw new SecurityException(getException("Token not required to access " + vocabularyName ));
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
    /**
     * Returns the SecurityKey for the specified securityToken
     * @param security
     * @param securityToken
     * @return
     */
    private SecurityKey getSecurityKey(DAOSecurity security, SecurityToken securityToken){
        SecurityKey key = null;
        try{
            key = security.getSecurityKey(new UserCredentials(securityToken.getAccessToken()));
        }catch(Exception ex){
            throw new SecurityException(getException(ex.getMessage()));
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
     * Returns all vocabularies
     * @param map specifies the search parameters
     */
    private Response getAllVocabularies(HashMap map) throws DAOException{
        List vocabList = new ArrayList();
        for(Iterator i= vocabularies.keySet().iterator(); i.hasNext();){
        	try{
                vocabList.add(vocabularies.get(i.next()));
			}
			catch(Exception ex){}
        }
        return new Response(vocabList);
    }
    /**
     * Returns the vocabulary for the name specifed
     * @param map specifies the vocabulary name
     * @return
     * @throws Exception
     */
    private Response getVocabularyByName(HashMap map) throws Exception{
        String vocabularyName = null;
        Vocabulary vocab = new Vocabulary();
        List vocabList = new ArrayList();

        try{
            for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
            {
                String key = (String)iter.next();
                String name = key.substring(key.indexOf("$")+1, key.length());
                if(name.equalsIgnoreCase("vocabularyName"))
                    vocabularyName = (String)map.get(key);
            }
            if(vocabularyName != null){
                if(vocabularies.get(vocabularyName)!= null){
                    vocab = (Vocabulary)vocabularies.get(vocabularyName);
                    vocabList.add(vocab);
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
	private Response getDescLogicConcept(HashMap map) throws Exception	{
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
			boolean valid = true;
			DescLogicConcept dlc = new DescLogicConcept();
			Concept concept = new Concept();
			if(!inputFlag){
	       		validateConceptName(conceptName, vocabularyName);
	       	}
			else{
	       		validateDLConceptCode(conceptName, vocabularyName);
	       	}
            Vocabulary vocab = (Vocabulary)vocabularies.get(vocabularyName);
	       dlc = buildDescLogicConcept(((LexAdapter)adapters.get(vocabularyName)).getConcept(conceptName, inputFlag, 1), vocab);
	       list.add(dlc);
		}catch(Exception e){
			log.error("Error: getDescLogicConcept  "+e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}
		return new Response(list);
	}

	/**
	 * Performs a search for DescLogicConcepts in the LexBig server and returns a response
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
	        if(!StringHelper.hasValue(searchTerm)){
	        	log.warn("searchTerm cannot be null");
	        	throw new DAOException(getException(" searchTerm cannot be null"));
	        }
	        Concept[] concepts = null;
	        try{
	        	concepts = ((LexAdapter)adapters.get(vocabularyName)).searchConcepts(searchTerm, limit, matchOption, matchType,ASDIndex,"contains");
	        }catch(Exception ex){
                //System.out.println("EXCEPTION >>>>> "+ ex.getMessage());
	        	if(matchOption==0){
                    try{
                        concepts = ((LexAdapter)adapters.get(vocabularyName)).searchConcepts(searchTerm, limit,"contains");
                    }catch(Exception e){
                        if(e.getMessage() != null){
                            throw new DAOException(getException(ex));
                        }
                    }
		        }
	        	else{
	        		throw new DAOException(getException(ex));
	        	}
	        }
            if(concepts.length > 0){
                Vocabulary vocab = (Vocabulary)vocabularies.get(vocabularyName);
            	DescLogicConcept[] dlc = new DescLogicConcept[concepts.length];
    	        for(int x=0; x<concepts.length; x++){
    	            dlc[x] = new DescLogicConcept();
    	            dlc[x] = buildDescLogicConcept(concepts[x], vocab);
    	            list.add(dlc[x]);
    	        }
            }
		}catch(Exception e){
            //System.out.println("EXCEPTION -- >>>>> "+ e.getMessage());
            if(e.getMessage() != null){
                log.error(e.getMessage());
                throw new DAOException(getException(e.getMessage()));
            }
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
			throw new DAOException(getException(e.getMessage()));
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
        	throw new DAOException(" vocabularyName cannot be null");
        }

        if(!StringHelper.hasValue(rootName)){
        	log.error("Root Node cannot be null");
        	throw new DAOException(" Root Node cannot be null");
        }
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
				LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);

				if((roles != null) && (roles.size() > 0)){
				   tree = adapter.getTree(rootName, direction, depthLevel, attrib, isaFlag, roles);

				}
                if(tree == null){
                    throw new DAOException("Unable to generate Tree  - LexBIG Exception");

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
		  		throw new DAOException (getException( e.getMessage()));
			}

		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException( e.getMessage()));
        }
		return evsTree;
    }
/**
 * Generates and returns a DefaultMutableTreeNode
 * @param dlNode
 * @param dtsrpcNode
 * @return
 */
private DefaultMutableTreeNode getChildNodes(DefaultMutableTreeNode dlNode, DefaultMutableTreeNode dtsrpcNode){
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


		LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);

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
/**
 * Seaches for a concept in the specified silo
 * @param map
 * @return
 * @throws Exception
 * @deprecated Silos are not supported in the LexBIG
 */

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



        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);

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

        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);

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


		LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
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
        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
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

        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
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

        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
		ancestors = adapter.getAncestorCodes(conceptCode, flag, stringToDate(iBaseLineDate), stringToDate(fBaseLineDate));

		if(ancestors.size()> 0){
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

        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
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


        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
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
		if(!StringHelper.hasValue(conceptName))
        	throw new DAOException("conceptName cannot be null");
        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
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
		if(!StringHelper.hasValue(conceptName))
        	throw new DAOException("conceptName cannot be null");
        LexAdapter adapter = (LexAdapter)adapters.get(vocabularyName);
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
    ArrayList list = new ArrayList();
	try
	{	for(Iterator i= vocabularies.keySet().iterator(); i.hasNext();)
		{
			list.add((String)i.next());
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
		if(!StringHelper.hasValue(conceptName)){
			String msg = "conceptName cannot be null";
			log.error(msg);
			throw new DAOException(getException(msg));
		}
		conceptCode = getLexAdapter(vocabularyName).getConceptCodeByName(conceptName);

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
		host = getLexAdapter(vocabularyName).getVocabularyHost(vocabularyName);
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
		port = getLexAdapter(vocabularyName).getVocabularyPort(vocabularyName);
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
       version.add(getLexAdapter(vocabularyName).getVocabularyVersion(vocabularyName));
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
        if(editActionDate == null){
            editActions = getLexAdapter(vocabularyName).getConceptEditAction(conceptCode);
        }
        else{
            //editActions = adapter.getConceptEditAction(conceptCode, editActionDate);
            editActions = getLexAdapter(vocabularyName).getConceptEditAction(conceptCode);
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
		editActions = getLexAdapter(vocabularyName).getConceptEditActionDates(conceptCode, action);

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
			concepts = getLexAdapter(vocabularyName).getRootConcepts(allAttributes);
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
        DescLogicConcept dlc = buildDescLogicConcept(getLexAdapter(vocabularyName).findConcept(conceptName), (Vocabulary)vocabularies.get(vocabularyName));
        dlcList.add(dlc);
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
	String source = "*";
	boolean cui = true;
	boolean shortResult = false;
	boolean score = false;

	ArrayList list = new ArrayList();
	List uList = new ArrayList();
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
			    if(!(map.get(key)==null)){
                    source = (String)map.get(key);
			        }
				if (source.toLowerCase().equals("all sources")) {
					source = "*";
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
				throw new DAOException("Invalid source "+ source);
				}
				checkSource = true;
			}
		}
        LexAdapter adapter = getLexAdapterForMeta();

		if(cui)
		{
            validateMetaConceptCode(searchTerm, adapter);
            Concept concept  = adapter.findConceptByCode(searchTerm, 1);
            MetaThesaurusConcept mtc = buildMetaThesaurusConcept(concept);
            if(source.indexOf("*")>-1){
                list.add(mtc);
            }else{
                ArrayList srcCollection = mtc.getSourceCollection();
                for(int i=0; i<srcCollection.size(); i++){
                    Source s = (Source)srcCollection.get(i);
                    if(s.getAbbreviation().equalsIgnoreCase(source)){
                        list.add(mtc);
                        break;
                    }
                }
            }
		}
		else {
            if(source.indexOf("*")>-1){
                try{
                    //Vector concepts = adapter.findConceptsWithNameMatching(searchTerm, limit);
                    Concept[] concepts = adapter.searchConcepts(searchTerm, limit,0,"",1,"contains");
                    for(int x=0; x<concepts.length; x++){
                        MetaThesaurusConcept mtc = buildMetaThesaurusConcept((Concept)concepts[x]);
                        if(source.equals("*")){
                            list.add(mtc);
                        }else{
                            ArrayList srcList = mtc.getSourceCollection();
                            if(source.startsWith("*")){
                                source = source.substring(1);
                                for(int s=0; s< srcList.size(); s++){
                                    Source src = (Source)srcList.get(s);
                                    if(src.getAbbreviation().endsWith(source)){
                                        list.add(mtc);
                                        break;
                                    }
                                }
                            }else{
                                source = source.substring(0, source.indexOf("*"));
                                source = source.substring(1);
                                for(int s=0; s< srcList.size(); s++){
                                    Source src = (Source)srcList.get(s);
                                    if(src.getAbbreviation().startsWith(source)){
                                        list.add(mtc);
                                        break;
                                    }
                                }
                            }
                        }
                     }
                }catch(Exception ex){}
            }else{
                Concept[] concepts = adapter.searchConcepts(searchTerm, limit, 0, source, 1,"contains");
                for(int i=0; i<concepts.length; i++){
                    list.add(buildMetaThesaurusConcept(concepts[i]));
                 }
            }
        }

    }
	catch(Exception e)
	{
        if(e != null){
            log.error("Error: searchMetaThesaurus - "+e.getMessage());
            throw new DAOException (getException( e.getMessage()));
        }
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
        HashMap sourceMap = new HashMap();
        HashMap atoms = new HashMap();
        HashSet synonyms = new HashSet();
        HashMap semanticTypes = new HashMap();
        HashMap definitions = new HashMap();

        Vector propertyCollection = metaConcept.getPropertyCollection();
        try{
            for(int p=0; p<propertyCollection.size(); p++){
                gov.nih.nci.lexrpc.client.Property property = (gov.nih.nci.lexrpc.client.Property) propertyCollection.get(p);
                Vector qCollection = property.getQualifierCollection();
                if(property.getName().toUpperCase().equalsIgnoreCase("FULL_SYN")){
                    gov.nih.nci.evs.domain.Atom atom = new gov.nih.nci.evs.domain.Atom();
                    atom.setName(property.getValue());
                    atom.setCode(metaConcept.getCode());
                    for(int q=0; q< qCollection.size(); q++){
                        gov.nih.nci.lexrpc.client.Qualifier qualifier = (gov.nih.nci.lexrpc.client.Qualifier)qCollection.get(q);
                        gov.nih.nci.evs.domain.Source source = new gov.nih.nci.evs.domain.Source();
                        if(qualifier.getName().toLowerCase().equalsIgnoreCase("source")){
                            source.setAbbreviation(qualifier.getValue());
                        }else if(qualifier.getName().toLowerCase().equalsIgnoreCase("source-code")){
                            source.setCode(qualifier.getValue());
                        }
                        if(source.getAbbreviation()!=null){
                            sourceMap.put(source.getAbbreviation(), source);
                            atom.setSource(source);
                            atom.setOrigin(source.getAbbreviation());
                            atoms.put(atom.getName() + source.getAbbreviation(),atom);
                        }
                    }
                    synonyms.add(property.getValue());

                }else if(property.getName().toUpperCase().equalsIgnoreCase("SEMANTIC_TYPE")){
                    SemanticType sType = new SemanticType();
                    sType.setName(property.getValue());
                    semanticTypes.put(property.getValue(), sType);
                }else if(property.getName().toUpperCase().equalsIgnoreCase("DEFINITION")){
                    Definition definition = new Definition();
                    definition.setDefinition(property.getValue());
                    gov.nih.nci.evs.domain.Source source = new gov.nih.nci.evs.domain.Source();
                    for(int q=0; q< qCollection.size(); q++){
                    	gov.nih.nci.lexrpc.client.Qualifier qualifier = (gov.nih.nci.lexrpc.client.Qualifier)qCollection.get(q);
                        if(qualifier.getName().toLowerCase().equalsIgnoreCase("source")){
                            source.setAbbreviation(qualifier.getValue());
                        }else if(qualifier.getName().toLowerCase().equalsIgnoreCase("source-code")){
                            source.setCode(qualifier.getValue());
                        }
                    }
                    definition.setSource(source);
                    definitions.put(property.getValue(),definition);
                    //sourceCollection.add(source);
                    sourceMap.put(source.getAbbreviation(), source);
                }else if(property.getName().toUpperCase().equalsIgnoreCase("SOURCE")){
                    Source source = new Source();
                    source.setAbbreviation(property.getValue());
                    //sourceCollection.add(source);
                    sourceMap.put(source.getAbbreviation(), source);
                }

            }

        }catch(Exception ex){
            throw new DAOException("\nException: buildMetaConcept: "+ ex.getMessage());
        }

        for(Iterator it = semanticTypes.keySet().iterator(); it.hasNext();){
            semanticTypesCollection.add((SemanticType)semanticTypes.get(it.next()));
        }
        metaThesaurusConcept.setSemanticTypeCollection(semanticTypesCollection);
        for(Iterator it = synonyms.iterator(); it.hasNext();){
            synonymCollection.add(it.next());
        }
	    metaThesaurusConcept.setSynonymCollection((ArrayList)synonymCollection);
        for(Iterator it = definitions.keySet().iterator(); it.hasNext();){
            definitionsCollection.add((Definition)definitions.get(it.next()));
        }
        metaThesaurusConcept.setDefinitionCollection(definitionsCollection);
        for(Iterator it = sourceMap.keySet().iterator(); it.hasNext();){
            sourceCollection.add((Source)sourceMap.get(it.next()));
        }
	    metaThesaurusConcept.setSourceCollection(sourceCollection);
        for(Iterator it = atoms.keySet().iterator(); it.hasNext();){
            atomCollection.add((Atom)atoms.get(it.next()));
        }
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
	catch(Exception e)
	{
		log.error(e.getMessage());
		throw new DAOException("MetaThesaurus exception - while getting sources \n"+ e.getMessage());
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
	private Response searchSourceByAtomCode(HashMap map) throws Exception
	{
		String code = null;
		String sourceAbbr = "*";
        int limit = 100;

		ArrayList list = new ArrayList();
		try
		{

			for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			{
				String key = (String)iter.next();
				String name = key.substring(key.indexOf("$")+1, key.length());

				if(name.equalsIgnoreCase("code"))
					code = (String)map.get(key);
				else if(name.equalsIgnoreCase("sourceAbbr")){
					if(map.get(key)!=null){
						sourceAbbr = (String)map.get(key);
					}
				}else if(name.equalsIgnoreCase("limit"))
					if(map.get(key)!=null){
						try{
							limit = ((Integer)map.get(key)).intValue();
						}catch(Exception ex){}
					}
				}
            LexAdapter adapter = getLexAdapterForMeta();
            validateMetaConceptCode(code, adapter);
            Concept metaConcept = adapter.findConceptByCode(code, 1);
            MetaThesaurusConcept mtc = buildMetaThesaurusConcept((Concept)metaConcept);

			List sourceList = new ArrayList();
			if(sourceAbbr.equals("*") || sourceAbbr == null){
				list.add(mtc);
			}
			else{
				if(!validateSource(sourceAbbr)){
				    throw new DAOException ("invalid source abbreviation - "+ sourceAbbr);
				    }
                ArrayList srcCollection = mtc.getSourceCollection();
                for(int i=0; i< srcCollection.size(); i++){
                    Source src = (Source)srcCollection.get(i);
                    if(src.getAbbreviation().equalsIgnoreCase(sourceAbbr)){
                        list.add(mtc);
                        break;
                    }
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
	 * Gets all semantic types from metaphrase
	 * @param map
	 * @return Returns a response that holds a list of SemanticTypes
	 * @throws Exception
	 */
	private Response getSemanticTypes(HashMap map) throws Exception
	{
		ArrayList list = new ArrayList();
		try
		{
            LexAdapter adapter = getLexAdapterForMeta();
          if(adapter.getSemanticTypes() != null){
              HashMap types = adapter.getSemanticTypes();
            for(Iterator i = types.keySet().iterator(); i.hasNext();){
                  String key = (String) i.next();
                SemanticType semanticType = new SemanticType();
                  semanticType.setId(key);
                  semanticType.setName((String)types.get(key));
                  list.add(semanticType);
              }
          }
		}
		catch(Exception e)
		{
			log.error("Exception occured on getSemanticTypes"+ e.getMessage());
			throw new DAOException (getException(e.getMessage()));
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
				  throw new DAOException(" Invalid Source");

            if(conceptCode == null){
                throw new Exception("Please specify conceptCode");
            }
     /** check this ***/
            log.info("findConceptWithSourceCodeMatching..."+ sourceAbbr +"\t"+ conceptCode);
			metaConcepts = getLexAdapterForMeta().findConceptsWithSourceCodeMatching(sourceAbbr, conceptCode, 1);
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
				  throw new DAOException(" Invalid concept code");


			metaConcept =	getLexAdapterForMeta().findConceptByCode(conceptCode, 1);
			conceptList.add(metaConcept.getName());


		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new DAOException (getException(e.getMessage()));
		}



		return (new Response(conceptList));
	}

	private List getMetaSources() throws Exception{
		 List list = new ArrayList();
			try
			{
		 		Vector sources= getLexAdapterForMeta().getSupportedSources();
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

			return list;
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
				throw new DAOException(" Invalid code, set concept code");
			}
			if(!StringHelper.hasValue(sourceAbbr))
			{
				sourceAbbr = "*";
			}
            LexAdapter adapter = getLexAdapterForMeta();
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
		String relation = "*";

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
				  throw new DAOException(" invalid concept code ");

			if(!StringHelper.hasValue(category))
				  throw new DAOException(" Invalid Category - set value");

/**** check code ****/
            metaConcepts = getLexAdapterForMeta().searchConcepts(conceptCode, 100, 1, category, 1);


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
		inverseRole = getLexAdapter(vocabularyName).containsInverseRole(roleName, roleValue, conceptName);
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
		inverseRole = getLexAdapter(vocabularyName).containsRole(roleName, roleValue, conceptName);
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
   		lexProperties = getLexAdapter(vocabularyName).getPropertiesByConceptName(term);

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
        LexAdapter adapter = getLexAdapter(vocabularyName);
        String code = term;
        try{
            validateDLConceptCode(code, vocabularyName);
        }catch(Exception ex){
            try{
                if(validateConceptName(code, vocabularyName)){
                    code = adapter.getConceptCodeByName(term);
                }
            }catch(Exception exc){
                throw new DAOException("Invalid concept identifier "+ code);
            }
        }
        associations = adapter.fetchTermAssociations(code);
        for(int i=0; i<associations.size(); i++)
   	   	{
   	   		list.add(associations.get(i));
   	   	}
   	}
   	catch(Exception e)
   	{
   		log.error(e.getMessage());
   		throw new DAOException(getException(e.getMessage()));
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
   		associations = getLexAdapter(vocabularyName).getAllAssociationTypes();
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
   		types = getLexAdapter(vocabularyName).getAllConceptAssociationQualifierTypes();
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
      		types = getLexAdapter(vocabularyName).getAllConceptAssociationTypes();
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
     		types = getLexAdapter(vocabularyName).getAllConceptPropertyQualifierTypes();
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
     		types = getLexAdapter(vocabularyName).getAllConceptPropertyTypes();
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
     		types = getLexAdapter(vocabularyName).getAllLicenses();
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
     		types = getLexAdapter(vocabularyName).getAllQualifierTypes();
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
     		types = getLexAdapter(vocabularyName).getAllRoleNames();
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
     		types = getLexAdapter(vocabularyName).getAllSubConceptCodes(conceptCode);
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
     		types = getLexAdapter(vocabularyName).getAllSubConceptNames(conceptName);
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
	        		types = getLexAdapter(vocabularyName).getAllSynonymTypes();
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

   //private gov.nih.nci.evs.domain.Silo convertSilo(gov.nih.nci.dtsrpc.client.Silo dtsSilo){
   private gov.nih.nci.evs.domain.Silo convertSilo(gov.nih.nci.lexrpc.client.Silo dtsSilo){
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
	        		types = getLexAdapter(vocabularyName).getAllConceptAssociationQualifierTypes();
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
   		types = getLexAdapter(vocabularyName).getAllQualifierTypes();
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

   		//types = adapter.getAllPropertyTypes();
   		//gov.nih.nci.lexrpx.client.PropertyType[] properties = adapter.getAllPropertyTypes();
   		PropertyType[] properties = getLexAdapter(vocabularyName).getAllPropertyTypes();

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
   		return dlc;
   	}

   private DescLogicConcept buildDescLogicConcept(gov.nih.nci.lexrpc.client.Concept concept, Vocabulary vocab){
       DescLogicConcept dlc = buildDescLogicConcept(concept);
       dlc.setVocabulary(vocab);
       return dlc;
   }

   public Vector convertAssociations(Vector dtsrpcAssociations){
       Vector associationVector = new Vector();
       for(int i=0; i<dtsrpcAssociations.size(); i++){
           gov.nih.nci.evs.domain.Association association = new Association();
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


   private gov.nih.nci.evs.domain.TreeNode convertTreeNode(gov.nih.nci.lexrpc.client.TreeNode node){
       gov.nih.nci.evs.domain.TreeNode treeNode = new gov.nih.nci.evs.domain.TreeNode();
       //treeNode.setName(concept.getTreeNode().getName());
       //treeNode.setIsA(concept.getTreeNode().getIsA());
       //treeNode.setLinks(concept.getLinks());
       //treeNode.setTraverseDown(concept.getTraverseDown());
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
   		Vector conceptNames = new Vector();

   		Concept[] superConcepts =  null;
   		if(!inputFlag){
   		   	if(validateConceptName(conceptName, vocabularyName)){
   		   		superConcepts = getLexAdapter(vocabularyName).getDirectSups(conceptName, false, asdIndex);
   		   	    }
   		   	}
   		else{
   		    if(validateDLConceptCode(conceptName, vocabularyName)){
   		    	superConcepts = getLexAdapter(vocabularyName).getDirectSups(conceptName, false, asdIndex);
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
   		Concept[] concepts = null;
        LexAdapter adapter = getLexAdapter(vocabularyName);

   		   if(!inputFlag){
   		   		validateConceptName(conceptName, vocabularyName);
   		   		concepts = adapter.getDirectSubs(conceptName,false, asdIndex);
   		   	}
   		   	else{
				validateDLConceptCode(conceptName, vocabularyName);
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
   	   		if(validateConceptName(conceptName, vocabularyName)){
   	   			found  = getLexAdapter(vocabularyName).hasChildren(conceptName);
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
   	   		if(validateConceptName(conceptName, vocabularyName)){
   	   			found  = getLexAdapter(vocabularyName).hasParents(conceptName);
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
   private boolean validateConceptName (String conceptName, String vocabularyName)throws Exception{

   	boolean valid = true;

   	try{
   	if(conceptName == null){
   		valid = false;
   		throw new DAOException(" Concept name cannot be a null value");
   			}
   	else if(getLexAdapter(vocabularyName).getConceptCodeByName(conceptName) == null){
   			valid = false;
   			throw new DAOException(" Invalid concept name - "+conceptName);
   			}
	}
   	catch(Exception e){
   		throw new DAOException(e.getMessage());
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
   		throw new DAOException(" Invalid relation attribute "+ relation);
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
		if(conceptCode == null){
			log.error(" Concept code cannot be a null value");
			throw new DAOException (" Invalid concept code");
			}
		conceptName = getLexAdapter(vocabularyName).getConceptNameByCode(conceptCode);
		list.add(conceptName);
	}
	catch(Exception e)
	{
		log.error("Exception - getConceptNameByCode "+e.getMessage());
		throw new DAOException (getException( e.getMessage()));
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
   		PropertyType[] propertyTypes = getLexAdapter(vocabularyName).getAllPropertyTypes();
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
       DAOException ex = null;
       if(threadLocal.get() != null){
           ex = (DAOException)threadLocal.get();
       }else if(msg != null){
           ex = new DAOException(msg);
           threadLocal.set(ex);
       }
   	return ex;
   	}

   private Exception getException(Exception ex){
   	return getException(ex.getMessage());
   	}

   private boolean validateMetaConceptCode(String code, LexAdapter adapter)throws Exception {
       boolean valid = false;
       try{
           if(adapter.getConceptNameByCode(code)== null){
               throw new DAOException("Invalid concept code "+ code);
           }else{
               valid = true;
           }
       }catch(Exception ex){
           throw new DAOException(ex.getMessage());
       }
       return valid;
   }

   private boolean validateDLConceptCode(String code, String vocabularyName) throws Exception{

	boolean valid = true;

   	try{
   	if(code == null){
   		valid = false;
   		throw new DAOException (" Concept code cannot be a null value");
   			}
   	else if(getLexAdapter(vocabularyName).getConceptNameByCode(code) == null){
   			valid = false;
   			throw new DAOException (" Invalid concept code - "+ code);
   			}
	}
   	catch(Exception e){
   		throw new DAOException(e.getMessage());
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
            if(conceptCode != null){
                validateDLConceptCode(conceptCode, vocabularyName);
            }
            LexAdapter adapter = getLexAdapter(vocabularyName);
            if(conceptCode == null){
            	v = adapter.getHistoryDates();
            }
            else{
                v = adapter.getConceptEditAction(conceptCode);
                if(v!=null && v.size()>0){
                    gov.nih.nci.evs.domain.HistoryRecord hr = new gov.nih.nci.evs.domain.HistoryRecord();
                    hr.setDescLogicConceptCode(conceptCode);
                    Vector historyVector = new Vector();
                    for(int i=0; i<v.size(); i++){
                        String actionDate = (String)v.get(i);
                        StringTokenizer st = new StringTokenizer(actionDate, "|");
                        while(st.hasMoreTokens()){
                            String action = st.nextToken();
                            Date aDate = stringToDate(st.nextToken());
                            gov.nih.nci.evs.domain.History h = new gov.nih.nci.evs.domain.History();
                            h.setEditAction(action);
                            h.setEditActionDate(aDate);
                            Vector ref = new Vector();
                            if(action.equalsIgnoreCase("retire") ||action.equalsIgnoreCase("merge")){
                                ref = adapter.getCodeActionChildren(conceptCode, aDate);
                            }else if(action.equalsIgnoreCase("split")){
                            	ref = adapter.getCodeActionParents(conceptCode, aDate);
                            }
                            String refCodes = "";
                            for(int r=0; r<ref.size(); r++){
                                refCodes += (String)ref.get(r);
                            }
                            if(refCodes.length()>0){
                                h.setReferenceCode(refCodes);
                            }
                            if(initialDate != null && finalDate == null){
                                if(initialDate.equals(aDate) || aDate.after(initialDate)){
                                    historyVector.add(h);
                                }
                            }else if(initialDate == null && finalDate != null){
                                if(finalDate.equals(aDate) || aDate.before(finalDate)){
                                    historyVector.add(h);
                                }
                            }else if(initialDate != null && finalDate != null ){
                                if(initialDate.equals(aDate) ||(aDate.after(initialDate) && aDate.before(finalDate)) || aDate.equals(finalDate)){
                                    historyVector.add(h);
                                }
                            }else if(initialDate == null && finalDate == null){
                                historyVector.add(h);
                            }
                        }
                        hr.setHistoryCollection(historyVector);
                    }
                    list.add(hr);
                   }
            }



        }
        catch(Exception e){
            throw new DAOException(getException(e));

        }
 return new Response(list);

   }


   /**
    * Returns edit action string for a given code
    * @param map
    * @return
    * @throws Exception
    */
   private String getEditActionString(int code){
       String action = null;
       if(code == 0){
           action = "create";
       }else if(code == 1){
           action = "merge";
       }else if(code == 2){
           action = "modify";
       }else if(code == 3){
           action = "retire";
       }else if(code == 4){
           action = "split";
       }
       return action;
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
	   		Date startDate = getLexAdapter(vocabularyName).getHistoryStartDate();
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
	   		Date endDate = getLexAdapter(vocabularyName).getHistoryEndDate();
	   		dateList.add(endDate);
	   		}catch(Exception e){
	   			log.error(e.getMessage());
	   			throw new DAOException(getException( e.getMessage()));
			}
		return new Response(dateList);
   }
   private Response getHistoryDates(HashMap map) throws Exception {
       List dateList = new ArrayList();
       String vocabularyName = null;
       try{
            for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
            {
                String key = (String)iter.next();
                String name = key.substring(key.indexOf("$")+1, key.length());

                if(name.equalsIgnoreCase("vocabularyName"))
                    vocabularyName = (String)map.get(key);
            }
            Vector dates = getLexAdapter(vocabularyName).getHistoryDates();
            for(int i=0; i< dates.size(); i++){
                dateList.add(dates.get(i));
            }
        }catch(Exception e){
                log.error(e.getMessage());
                throw new DAOException(getException( e.getMessage()));
        }
       return new Response(dateList);
   }

   private Response getHistoryEndBaseLineDate(HashMap map) throws Exception {
       List dateList = new ArrayList();
       String vocabularyName = null;
       try{
           for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
           {
               String key = (String)iter.next();
               String name = key.substring(key.indexOf("$")+1, key.length());

               if(name.equalsIgnoreCase("vocabularyName"))
                   vocabularyName = (String)map.get(key);
           }
           Date date = getLexAdapter(vocabularyName).getHistoryEndBaselineDate();
           if(date!=null){
               dateList.add(date);
           }
       }catch(Exception e){
               log.error(e.getMessage());
               throw new DAOException(getException( e.getMessage()));
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
   /**
    * Gets all child concept codes for the specified concept based on the action.
    * @param map - Specifies the input parameters
    * @return - Returns a response that holds a list of concept codes
    * @throws Exception
    */
   private Response getCodeActionChildren(HashMap map) throws Exception
   {
   	String vocabularyName = null;
   	String conceptCode = null;
   	String action = null;
    Date baseLineDate = null;
    Vector children = new Vector();
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
            else if(name.equalsIgnoreCase("baseLineDate"))
                baseLineDate = stringToDate((String)map.get(key));
            else if(name.equalsIgnoreCase("action"))
                action = (String)map.get(key);
   		}
   		if(action == null){
   			children = getLexAdapter(vocabularyName).getCodeActionChildren(conceptCode, baseLineDate);
   		}

   		for(int i=0; i<children.size(); i++)
			{
				list.add(children.get(i));
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
    * Gets all parent concept codes for the specified concept.
    * @param map - Specifies the input parameters
    * @return - Returns a response that holds a list of concept codes
    * @throws Exception
    */
   private Response getCodeActionParents(HashMap map) throws Exception
   {
   	String vocabularyName = null;
   	String conceptCode = null;
    Date baseLineDate = null;
    Vector parents = new Vector();
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
            else if(name.equalsIgnoreCase("baseLineDate"))
                baseLineDate = stringToDate((String)map.get(key));
   		}
   		parents = getLexAdapter(vocabularyName).getCodeActionParents(conceptCode, baseLineDate);
   		for(int i=0; i<parents.size(); i++)
			{
				list.add(parents.get(i));
			}
   	}
   	catch(Exception e)
   	{
   		log.error(e.getMessage());
   		throw new DAOException (getException( e.getMessage()));
   	}
   	return (new Response(list));
   }

//==============================================================================================================================================
/*
private Response getSecurityTokenbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		String ret_obj = adapter.getSecurityTokenbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			SecurityToken evs_obj = convertSecurityToken(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getSiloCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getSiloCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertSiloCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getQualifierCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getQualifierCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertQualifierCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getDefinitionCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getDefinitionCollectionbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Vector evs_obj = convertDefinitionCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getSourceCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getSourceCollectionbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Vector evs_obj = convertSourceCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getSemanticTypeCollectionbyCui (HashMap map) throws Exception
{
	String vocabularyName = null;
	String cui = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("cui"))
				cui = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getSemanticTypeCollectionbyCui(vocabularyName, cui);
		if (ret_obj != null) {
			Vector evs_obj = convertSemanticTypeCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getAtomCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String cui = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("cui"))
				cui = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getAtomCollectionbyCode(vocabularyName, cui);
		if (ret_obj != null) {
			Vector evs_obj = convertAtomCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getHistoryCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getHistoryCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertHistoryCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getVocabularybyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		String ret_obj = adapter.getVocabularybyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Vocabulary evs_obj = convertVocabulary(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getTreeNodebyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		//gov.nih.nci.lexrpc.client.TreeNode ret_obj = adapter.getTreeNodebyCode(vocabularyName, conceptCode);
		gov.nih.nci.lexrpc.client.TreeNode ret_obj = adapter.getEdgePropertiesbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			gov.nih.nci.evs.domain.TreeNode evs_obj = convertTreeNode(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getRoleCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getRoleCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertRoleCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getEdgePropertiesbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		gov.nih.nci.lexrpc.client.TreeNode ret_obj = adapter.getEdgePropertiesbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			EdgeProperties evs_obj = convertEdgeProperties(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getAssociationCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getAssociationCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertAssociationCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getPropertyCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getPropertyCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertPropertyCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}



private Response getRoleCollectionbyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getRoleCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertRoleCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}


private Response getSourcebyCode (HashMap map) throws Exception
{
	String vocabularyName = null;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("vocabularyName"))
				vocabularyName = (String) map.get(key);
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		String ret_obj = adapter.getSourcebyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Source evs_obj = convertSource(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

========================================================================================================================== */


private Response getSecurityTokenbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		String ret_obj = adapter.getSecurityTokenbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			SecurityToken evs_obj = convertSecurityToken(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getSiloCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getSiloCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertSiloCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getQualifierCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getQualifierCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertQualifierCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getDefinitionCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getDefinitionCollectionbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Vector evs_obj = convertDefinitionCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getSourceCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getSourceCollectionbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Vector evs_obj = convertSourceCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}


private Response getSemanticTypeVectorbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String code = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("code"))
				code = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getSemanticTypeCollectionbyCode(vocabularyName, code);

		if (ret_obj != null) {
			Vector evs_obj = convertSemanticTypeCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}


private Response getSemanticTypeCollectionbyCui (HashMap map) throws Exception
{
	int namespaceId = -1;
	String cui = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("code"))
				cui = (String) map.get(key);
		}
		String vocabularyName = "NCI_MetaThesaurus";//namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getSemanticTypeCollectionbyCode(vocabularyName, cui);

		if (ret_obj != null) {
			Vector evs_obj = convertSemanticTypeCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}


private Response getSynonymCollectionbyCui (HashMap map) throws Exception
{
	return getAtomCollectionbyCui (map);
}


private Response getAtomCollectionbyCui (HashMap map) throws Exception
{
	int namespaceId = -1;
	String cui = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("cui"))
				cui = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		Vector ret_obj = adapter.getAtomCollectionbyCode(vocabularyName, cui);
		if (ret_obj != null) {
			Vector evs_obj = convertAtomCollection(ret_obj);
			for (int i=0; i<evs_obj.size(); i++)
			{
				list.add(evs_obj.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getHistoryCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getHistoryCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertHistoryCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getVocabularybyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		String ret_obj = adapter.getVocabularybyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Vocabulary evs_obj = convertVocabulary(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

// Place holder
private gov.nih.nci.lexrpc.client.TreeNode getTreeNodebyCode(String vocabularyName, String conceptCode)
{
	return new gov.nih.nci.lexrpc.client.TreeNode();
}


private Response getTreeNodebyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		//gov.nih.nci.lexrpc.client.TreeNode ret_obj = adapter.getTreeNodebyCode(vocabularyName, conceptCode);
		gov.nih.nci.lexrpc.client.TreeNode ret_obj = getTreeNodebyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			gov.nih.nci.evs.domain.TreeNode evs_obj = convertTreeNode(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getRoleCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getRoleCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertRoleCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}


private Response getInverseRoleCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getInverseRoleCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertRoleCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getInverseAssociationCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getInverseAssociationCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertRoleCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}


private Response getEdgePropertiesbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		gov.nih.nci.lexrpc.client.TreeNode ret_obj = adapter.getEdgePropertiesbyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			EdgeProperties evs_obj = convertEdgeProperties(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getAssociationCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getAssociationCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertAssociationCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getPropertyCollectionbyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	Vector ret_vec = null;
	Vector evs_vec = null;
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		ret_vec = adapter.getPropertyCollectionbyCode(vocabularyName, conceptCode);
		if (ret_vec != null) {
			evs_vec = convertPropertyCollection(ret_vec);
			for (int i=0; i<evs_vec.size(); i++)
			{
				list.add(evs_vec.elementAt(i));
			}
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}

private Response getSourcebyCode (HashMap map) throws Exception
{
	int namespaceId = -1;
	String conceptCode = null;
	ArrayList list = new ArrayList();
	try {
		for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			String name = key.substring(key.indexOf("$")+1, key.length());
			if(name.equalsIgnoreCase("namespaceId"))
				namespaceId = ((Integer)map.get(key)).intValue();
			else if(name.equalsIgnoreCase("conceptCode"))
				conceptCode = (String) map.get(key);
		}
		String vocabularyName = namespaceId2VocabularyName(namespaceId);
		LexAdapter adapter = (LexAdapter) adapters.get(vocabularyName);
		String ret_obj = adapter.getSourcebyCode(vocabularyName, conceptCode);
		if (ret_obj != null) {
			Source evs_obj = convertSource(ret_obj);
			list.add(evs_obj);
		}
	} catch(Exception e) {
		log.error(e.getMessage());
		throw new DAOException (getException(e.getMessage()));
	}
    return (new Response(list));
}



private Vocabulary convertVocabulary (String vocabulary) {
	Vocabulary vocab = new Vocabulary();
	vocab.setName(vocabulary);
	return vocab;

}


private Vector convertAssociationCollection (Vector dtsrpcAssociations) {
	return convertAssociations(dtsrpcAssociations);
}


private Vector convertPropertyCollection (Vector nciProps) {
	return convertProperties(nciProps);
}


private Vector convertSiloCollection (Vector v) {
	Vector u = new Vector();
	for (int i=0; i<v.size(); i++)
	{
		gov.nih.nci.evs.domain.Silo silo = convertSilo((gov.nih.nci.lexrpc.client.Silo) v.elementAt(i));
		u.add(silo);
	}
    return u;
}


private Vector convertQualifierCollection (Vector dtsrpcQualifiers) {
    return convertQualifiers(dtsrpcQualifiers);
}


private Vector convertDefinitionCollection (Vector v) {
	Vector u = new Vector();
	for (int i=0; i<v.size(); i++)
	{
		gov.nih.nci.lexrpc.client.Property property = (gov.nih.nci.lexrpc.client.Property) v.elementAt(i);
		Definition definition = new Definition();
		definition.setDefinition(property.getValue());
		gov.nih.nci.evs.domain.Source source = new gov.nih.nci.evs.domain.Source();
		Vector qCollection = property.getQualifierCollection();
		for(int q=0; q< qCollection.size(); q++){
			gov.nih.nci.lexrpc.client.Qualifier qualifier = (gov.nih.nci.lexrpc.client.Qualifier)qCollection.get(q);
			if(qualifier.getName().toLowerCase().equalsIgnoreCase("source")){
				source.setAbbreviation(qualifier.getValue());
			}else if(qualifier.getName().toLowerCase().equalsIgnoreCase("source-code")){
				source.setCode(qualifier.getValue());
			}
		}
		definition.setSource(source);
		u.add(definition);
	}
	return u;
 }


private Vector convertSourceCollection (Vector v) {
	Vector u = new Vector();
	for (int i=0; i<v.size(); i++)
	{
		gov.nih.nci.lexrpc.client.Property property = (gov.nih.nci.lexrpc.client.Property) v.elementAt(i);
		if(property.getName().toUpperCase().equalsIgnoreCase("SOURCE")) {
			Source source = new Source();
			source.setAbbreviation(property.getValue());
			u.add(source);
	    }
	}
	return u;
}


private Vector convertSemanticTypeCollection (Vector v) {
	Vector u = new Vector();
	for (int i=0; i<v.size(); i++)
	{
		gov.nih.nci.lexrpc.client.Property property = (gov.nih.nci.lexrpc.client.Property) v.elementAt(i);
		if(property.getName().toUpperCase().equalsIgnoreCase("SEMANTIC_TYPE")) {
			SemanticType sType = new SemanticType();
			sType.setName(property.getValue());
			u.add(sType);
	    }
	}
	return u;
}


private Vector convertAtomCollection (Vector v) {
	Vector u = new Vector();
	for (int i=0; i<v.size(); i++)
	{
		gov.nih.nci.lexrpc.client.Property property = (gov.nih.nci.lexrpc.client.Property) v.elementAt(i);
		if(property.getName().toUpperCase().equalsIgnoreCase("FULL_SYN")) {
			gov.nih.nci.evs.domain.Atom atom = new gov.nih.nci.evs.domain.Atom();
			atom.setName(property.getValue());
			//atom.setCode(metaConcept.getCode());
			Vector qCollection = property.getQualifierCollection();
			for(int q=0; q< qCollection.size(); q++){
				gov.nih.nci.lexrpc.client.Qualifier qualifier = (gov.nih.nci.lexrpc.client.Qualifier)qCollection.get(q);
				gov.nih.nci.evs.domain.Source source = new gov.nih.nci.evs.domain.Source();
				if(qualifier.getName().toLowerCase().equalsIgnoreCase("source")){
					source.setAbbreviation(qualifier.getValue());
				}else if(qualifier.getName().toLowerCase().equalsIgnoreCase("source-code")){
					source.setCode(qualifier.getValue());
				}
				if(source.getAbbreviation()!=null){
					//sourceMap.put(source.getAbbreviation(), source);
					atom.setSource(source);
					atom.setOrigin(source.getAbbreviation());
				}
			}
			u.add(atom);
	    }
	}
	return u;
}


/*
private Vector convertHistoryCollection (Vector v) {
	Vector historyVector = new Vector();
	for (int i=0; i<v.size(); i++) {
		String actionDate = (String) v.get(i);
		StringTokenizer st = new StringTokenizer(actionDate, "|");
		while(st.hasMoreTokens()){
			String action = st.nextToken();
			Date aDate = stringToDate(st.nextToken());
			gov.nih.nci.evs.domain.History h = new gov.nih.nci.evs.domain.History();
			h.setEditAction(action);
			h.setEditActionDate(aDate);
			Vector ref = new Vector();
			if(action.equalsIgnoreCase("retire") ||action.equalsIgnoreCase("merge")){
				ref = adapter.getCodeActionChildren(conceptCode, aDate);
			}else if(action.equalsIgnoreCase("split")){
				ref = adapter.getCodeActionParents(conceptCode, aDate);
			}
			String refCodes = "";
			for(int r=0; r<ref.size(); r++){
				refCodes += (String)ref.get(r);
			}
			if(refCodes.length()>0){
				h.setReferenceCode(refCodes);
			}

			if(initialDate != null && finalDate == null){
				if(initialDate.equals(aDate) || aDate.after(initialDate)){
					historyVector.add(h);
				}
			}else if(initialDate == null && finalDate != null){
				if(finalDate.equals(aDate) || aDate.before(finalDate)){
					historyVector.add(h);
				}
			}else if(initialDate != null && finalDate != null ){
				if(initialDate.equals(aDate) ||(aDate.after(initialDate) && aDate.before(finalDate)) || aDate.equals(finalDate)){
					historyVector.add(h);
				}
			}else if(initialDate == null && finalDate == null){
				historyVector.add(h);
			}

			historyVector.add(h);

		}
	}
	return historyVector;
}
*/
private Vector convertHistoryCollection (Vector v) {
	Vector historyVector = new Vector();
	return historyVector;
}


private SecurityToken convertSecurityToken (String token) {
	return new SecurityToken();
}


private Vector convertRoleCollection (Vector nciRoles) {
	return convertRoles(nciRoles);

}

private EdgeProperties convertEdgeProperties (gov.nih.nci.lexrpc.client.TreeNode node) {
    return new EdgeProperties();
}



private Source convertSource (String sab)  {
	try {
		HashMap map = new HashMap();
		List metaSources = (List)getMetaSources(map).getResponse();
		for(int i=0; i<metaSources.size(); i++) {
			Source source = (Source)metaSources.get(i);
			String sourceName = source.getAbbreviation();
			if(sourceName.equalsIgnoreCase(sab)) {
				return source;
			}
		}
    } catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


private String namespaceId2VocabularyName(int namespaceId)
{
	Integer id = new Integer(namespaceId);
	if (namespaceId2VocabularyName_Map.containsKey(id))
	{
		return (String) namespaceId2VocabularyName_Map.get(id);
	}
	try {
		String vocabularyName = getLexAdapter(defaultVocabularyName).localName2FormalName(id.toString());
		if (vocabularyName != null)
		{
			namespaceId2VocabularyName_Map.put(id, vocabularyName);
			return vocabularyName;
		}
    } catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


 }
