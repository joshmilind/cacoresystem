package gov.nih.nci.system.dao.impl.search ;
import gov.nih.nci.system.dao.*;
import gov.nih.nci.system.dao.impl.search.service.Searchable;
import gov.nih.nci.search.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.common.net.*;
import org.apache.log4j.Logger;
import java.util.*;

/*
 * Created on Mar 29, 2007
 * Shaziya Muhsin
 *
 */
/**
 * The SearchAPIDAO provides functionality to perform searches on a Lucene based Index
 */
public class SearchAPIDAO implements DAO {
    private static Logger log = Logger.getLogger(SearchAPIDAO.class.getName());
    private static SearchAPIDAO searchAPI = new SearchAPIDAO();
    private static String[] searchFields;
    private static String indexPropertyFile = "indexedFields.properties";

       /**
        *Constructor 
        */
       public SearchAPIDAO(){       }
 
       /**
        * Performs queries against the lucene indexes
        * @param searchQuery specifies the search criteria 
        * @return 
        * @throws DAOException
        */
       public List query(SearchQuery searchQuery) throws DAOException{
           List resultList = new ArrayList();
           try{
               Searchable searchService = null;               
               if(searchQuery.getQueryType()!=null){
                   searchService = (Searchable)ObjectFactory.getObject(searchQuery.getQueryType());
                   }
               resultList = searchService.query(getQueryString(searchQuery));   
           }catch(Exception ex){
               throw new DAOException(ex.getMessage());
           }
           return resultList;
       }
       
       /**
        * Performs queries against the lucene indexes
        * @param request - specifies the search criteria
        * @return
        * @throws DAOException
        */
       public Response query(Request request) throws DAOException{
           List resultList = null;           
           Object searchObject = request.getRequest();
           try{
               SearchQuery searchQuery = null;
               if(searchObject.getClass().getName().equalsIgnoreCase("gov.nih.nci.common.util.NestedCriteria")){
                   NestedCriteria criteria = (NestedCriteria)searchObject;
                   searchQuery = (SearchQuery)criteria.getSourceObjectList().get(0);  
               }else if(searchObject.getClass().getName().equalsIgnoreCase("gov.nih.nci.search.SearchQuery")){
                   searchQuery = (SearchQuery)searchObject;
               }               
               resultList = query(searchQuery);               
           }catch(Exception ex){
               log.error("Exception in SearchAPIDAO ", ex);
               throw new DAOException("Exception in SearchAPIDAO "+ ex.getMessage());
           }
          return new Response(resultList);
       }
       /**
        * Generates the search string for a given SearchQuery
        * @param searchQuery
        * @return
        * @throws DAOException
        */
       private String getQueryString(SearchQuery searchQuery) throws DAOException{
           String keyword = "";
           if(searchQuery.getKeyword()!=null){
               keyword = searchQuery.getKeyword();               
               if(searchQuery.getFuzzySearch()){
                   keyword+="~";
               }
           }          
           if(searchQuery.getRangeFilter()!=null){
               String filter = "";
               RangeFilter rangeFilter = searchQuery.getRangeFilter();
               if(rangeFilter.getFieldName()!=null){
                   filter += rangeFilter.getFieldName()+":";
               }
               String range = "";
               if(rangeFilter.getStartRange()!=null && rangeFilter.getEndRange()!=null){
                   range += "["+ rangeFilter.getStartRange() +" TO " + rangeFilter.getEndRange() +"]";
               }else if(rangeFilter.getEndRange()!=null && rangeFilter.getEndRange()==null){
                   range += rangeFilter.getStartRange();
               }else if(rangeFilter.getEndRange()==null && rangeFilter.getEndRange()!=null){
                   range += rangeFilter.getEndRange();
               }
               if(filter != null && range != null){
                   keyword += "OR" + filter + range;
               }else if(filter !=null && range == null){
                   filter += keyword;
               }else if(range != null){
                   keyword += " AND " + range;
               }
           }           
           return keyword;
       }
}
