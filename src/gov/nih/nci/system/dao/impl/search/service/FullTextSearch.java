package gov.nih.nci.system.dao.impl.search.service;

import gov.nih.nci.search.*;
import gov.nih.nci.search.RangeFilter;
import gov.nih.nci.system.dao.*;

import java.util.*;
import java.io.*;

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
            long start = System.currentTimeMillis();
            Hits hits = luceneSearch(searchString);
            long end = System.currentTimeMillis();
            System.out.println("Time to Query MS: "+ (end - start));
            start = System.currentTimeMillis();
            results = getSearchResults(hits, searchString);
            end = System.currentTimeMillis();
            System.out.println("Time to Organize Results MS: "+ (end - start));
        }catch(Exception ex){
            throw new DAOException(ex.getMessage());
        }
        return results;

    }
        /**
         * Returns the field names that are indexed
         * @return
         * @throws Exception
         */
    private String[] getIndexedFields() throws Exception{
            Properties properties = new Properties();
            Set<String> fieldList = new HashSet<String>();
            try{
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(indexPropertyFile));
            }catch(Exception ex){
                throw new Exception(ex.getMessage());
            }
            if(properties.size()>0){
                for(Iterator it = properties.keySet().iterator(); it.hasNext();){
                    String key = (String)it.next();
                    StringTokenizer st = new StringTokenizer(properties.getProperty(key),";");
                    while(st.hasMoreTokens()){
                        fieldList.add(st.nextToken());
                    }
                }
            }
            String[] searchFields = new String[fieldList.size()];
            int index = 0;
            for(Iterator i= fieldList.iterator(); i.hasNext();){
                searchFields[index]=(String)i.next();
                index++;
            }            
            return searchFields;
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
                multiSearcher = getIndexSearchers();
                String searchFields[] = getIndexedFields();
                QueryParser parser = new MultiFieldQueryParser(searchFields, new StandardAnalyzer());
                query = parser.parse(searchString);                
                hits = multiSearcher.search(query);
            }catch(Exception ex){
                throw new Exception(ex.getMessage());
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
                        SearchResult result = getSearchResult(doc,i+1,searchString);
                        resultList.add(result);                        
                    }
            }catch(Exception ex){
                throw new Exception(ex.getMessage());
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
                throw new Exception(ex.getMessage());
            }
            return resultList;
        }


}
