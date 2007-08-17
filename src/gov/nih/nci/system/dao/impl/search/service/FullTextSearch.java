package gov.nih.nci.system.dao.impl.search.service;

import gov.nih.nci.search.*;
import gov.nih.nci.search.RangeFilter;
import gov.nih.nci.system.dao.*;
import gov.nih.nci.common.util.search.*;
import java.util.*;
import java.io.*;
import org.apache.log4j.*;

import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryParser.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

/*
 * Created on Apr 2, 2007
 * ShaziyaMuhsin
 *
 */
/**
 * Performes full text searches on a lucene based index.
 */
public class FullTextSearch implements Searchable {
    String indexPropertyFile = "indexedFields.properties";
    static Logger log = Logger.getLogger(FullTextSearch.class.getName());
    

    /**
     * Constructor
     */
    public FullTextSearch() {
        super();
    }
    /**
     * Performs a full text search for a given string
     */
    public List query(String searchString)throws DAOException{ 
        List results = new ArrayList();
        try{
            Hits hits = luceneSearch(searchString);
            results = getSearchResults(hits, searchString);
        }catch(Exception ex){
            throw new DAOException(ex);
        }
        return results;

    }
    public List query(String searchString, gov.nih.nci.search.Sort sort)throws DAOException{
        List results = new ArrayList();
        try{
            Hits hits = luceneSearch(searchString);
            if(sort.getSortByClassName()){                            
                results = getOrganizedSearchResults(hits, searchString);                
            }else{
                results = getSearchResults(hits, searchString);
            }            
        }catch(Exception ex){
            throw new DAOException(ex);
        }
        return results;
    }
        /**
         * Returns the field names that are indexed
         * @return
         * @throws Exception
         */
    private String[] getIndexedFields() throws Exception{ 
            SearchAPIProperties properties = SearchAPIProperties.getInstance();
            String[] idxFields = null;
            try{
            	idxFields = properties.getIndexedFields();
            }catch(Exception ex){
            	log.info(ex);
            	//debug
            	idxFields = loadFields(indexPropertyFile);
            }
            return idxFields;
        }
   
    //debug
    private String[] loadFields(String propertyFileName) throws Exception{
    	Properties properties = new Properties();
    	Set fieldList = new HashSet();
    	 try{
    		 InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFileName);
             properties.load(is);
             is.close();
             for(Iterator it = properties.keySet().iterator(); it.hasNext();){
            	 String key = (String)it.next();
                 String value = properties.getProperty(key);
                 for(StringTokenizer st = new StringTokenizer(value,";");st.hasMoreTokens();){
                     String field = st.nextToken();
                     if(field!= null){
                         fieldList.add(field);
                     }
                 }
             }
             if(fieldList.size()>0){
            	 String[] fields = new String[fieldList.size()];
            	 int counter = 0;
            	 for(Iterator i= fieldList.iterator(); i.hasNext();){
            		 String s = (String)i.next();
            		 fields[counter]= new String(s);
            		 counter++;
            	 }
            	 log.info("Field count: "+ fields.length);
            	 return fields;
             }
         }catch(Exception ex){
        	 throw new Exception("Unable to load fields " + ex);
         }
         return null;
    }
    
    /**
     * Returns a list of subdirectories located within the given root directory
     * @param indexRoot
     * @return
     * @throws Exception
     */
    private File[] getDirectoryList(String indexRoot) throws Exception{            
            File dir = new File(indexRoot);
            FileFilter fileFilter = new FileFilter(){
                public boolean accept(File file){
                    return file.isDirectory();
                }
            };
            return dir.listFiles(fileFilter);
        }
    /**
     * Returns the index location
     * @return
     * @throws Exception
     */
    private String getIndexLocation() throws Exception{
            String indexFileLocation = null;
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
            if(properties.size()>0){
                indexFileLocation = properties.getProperty("hibernate.search.default.indexBase");
            }
            log.info("Index Location: "+ indexFileLocation);
            return indexFileLocation;
        }
    /**
     * Creates a ParellelMultiSearcher for the indexes
     * @return
     * @throws Exception
     */
    private ParallelMultiSearcher getIndexSearchers() throws Exception{
            File[] files = getDirectoryList(getIndexLocation());
            IndexSearcher[] searchers = new IndexSearcher[files.length];
            for(int i=0; i< files.length; i++){                    
                searchers[i]=new IndexSearcher(FSDirectory.getDirectory(files[i],false));
            }
            log.info("Number of Searchers: "+ files.length);
            return new ParallelMultiSearcher(searchers);            
        }
    
    /**
     * Performs a search on the indexes for the given string 
     * @param searchString specifies the search criteria
     * @return
     * @throws Exception
     */
    public Hits luceneSearch(String searchString) throws Exception{
            
            ParallelMultiSearcher multiSearcher = null;
            Query query = null;
            IndexReader reader;
            Hits hits = null;
            String indexRoot = getIndexLocation();
           
            try{
                try{
                    multiSearcher = getIndexSearchers();
                }catch(Exception ex){
                    throw new Exception("Unable to get lucene searchers "+ ex);
                }                
                String searchFields[] = getIndexedFields();
                QueryParser parser = new MultiFieldQueryParser(searchFields, new StandardAnalyzer());
                query = parser.parse(searchString);                
                hits = multiSearcher.search(query);
                multiSearcher.close();
            }catch(Exception ex){
                throw new Exception("Lucene Search Error : "+ex);
            }
            return hits;
        }
        
    /**
     * Returns a SearchResult object for a given document
     * @param doc 
     * @param i
     * @param keyword
     * @return
     */  
    private SearchResult getSearchResult(Document doc, int i, String keyword){
            SearchResult result = new SearchResult();
            HashMap<String,String> properties = new HashMap<String, String>();
            for(Enumeration e = doc.fields(); e.hasMoreElements(); ){
                org.apache.lucene.document.Field field = (org.apache.lucene.document.Field)e.nextElement();        
                properties.put(field.name(), field.stringValue());
                if(field.name().equalsIgnoreCase("_hibernate_class")){
                    result.setClassName(field.stringValue());
                }
                if(field.name().equalsIgnoreCase("id")){
                    result.setId(field.stringValue());
                }
            }
            result.setProperties(properties);
            int num = i;
            result.setHit(num);
            result.setKeyword(keyword);
            return result;
        }
    /**
     * Returns a List of SearchResult objects for the specified Hits
     * @param hits
     * @param searchString
     * @return
     * @throws Exception
     */
    public List getSearchResults(Hits hits, String searchString) throws Exception{
            List<SearchResult> resultList = new ArrayList<SearchResult>();            
            try{
                for(int i=0; i< hits.length(); i++){
                        Document doc = hits.doc(i);
                        SearchResult result = getSearchResult(doc,i,searchString);
                        resultList.add(result);                        
                    }
            }catch(Exception ex){
                throw new Exception(ex);
            }
            return resultList;
        }
    /**
     * Organizes the SearchResult objects in a HashMap
     * @param hits
     * @param searchString
     * @return
     * @throws Exception
     */
    public List getOrganizedSearchResults(Hits hits, String searchString) throws Exception{
            List<HashMap> resultList = new ArrayList<HashMap>();
            HashMap<String,List> map = new HashMap<String,List>();
            try{                
                Set<String> set = new HashSet<String>();
                    for(int i=0; i< hits.length(); i++){
                        Document doc = hits.doc(i);
                        SearchResult result = getSearchResult(doc,i+1,searchString);                        
                        boolean found = false;
                        for(Iterator it = set.iterator(); it.hasNext();){
                            String key = (String)it.next();
                            if(key.equalsIgnoreCase(result.getClassName())){
                                found = true;
                            }
                        }
                        List<SearchResult> l = new ArrayList<SearchResult>();
                        if(found){                    
                            l = (List<SearchResult>)map.get(result.getClassName());
                            l.add(result);
                            map.put(result.getClassName(), l);                            
                        }else{                                                        
                            l.add(result);
                            map.put(result.getClassName(), l);
                            set.add(result.getClassName());
                        }
                    }                    
                    resultList.add(map);
            }catch(Exception ex){
                throw new Exception(ex);
            }
            return resultList;
        }


}
