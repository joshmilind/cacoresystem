package gov.nih.nci.system.dao.impl.search.service;

import gov.nih.nci.search.SearchQuery;
import gov.nih.nci.system.dao.*;
import gov.nih.nci.system.dao.impl.search.SearchAPIDAO;
import gov.nih.nci.system.dao.impl.search.utils.SearchORMUtils;

import java.util.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.common.net.*;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.search.FullTextSession;

/*
 * Created on Apr 18, 2007
 * Shaziya Muhsin
 * 
 */

public class HibernateSearch implements Searchable{
    private static Logger log = Logger.getLogger(HibernateSearch.class.getName());
    private static SearchAPIDAO searchAPI = new SearchAPIDAO();
    private static String[] searchFields;
    private static String indexPropertyFile = "indexedFields.properties";

    public HibernateSearch() throws Exception {
        loadSearchFields();
    }

    public List query(String queryString) throws DAOException{
        List resultList = null;
        FullTextSession fullTextSession;
 
        try{
            fullTextSession = org.hibernate.search.Search.createFullTextSession(SearchORMUtils.getSession());
        }
        catch(Exception e)
        {
            log.error("Unable to open session " + e.getMessage());
            throw new DAOException("Unable to open session  " + e.getMessage());
        }
      try{
          String keyword = queryString;          
          System.out.println("Quering for "+ keyword);
          org.apache.lucene.queryParser.QueryParser parser = new MultiFieldQueryParser(searchFields, new StandardAnalyzer());//
          org.apache.lucene.search.Query luceneQuery = parser.parse( keyword );
          Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
          resultList = fullTextQuery.list();          
                
             
        }
        catch (JDBCException ex)
        {
            log.error("JDBC Exception in SearchAPIDAO ", ex);
            throw new DAOException("JDBC Exception in SearchAPIDAO ", ex);
        }
        catch(org.hibernate.HibernateException hbmEx)
        {
            log.error(hbmEx.getMessage());
            throw new DAOException("Hibernate problem ", hbmEx);
        }
        catch(Exception e)
        {
            log.error("Exception ", e);
            throw new DAOException("Exception in the SearchAPIDAO ", e);
        }
        finally
        {
            try
            {
                   fullTextSession.clear();
                   fullTextSession.close();
            }
            catch (Exception eSession)
            {
                log.error("Could not close the session - "+ eSession.getMessage());
                throw new DAOException("Could not close the session  " + eSession);
            }
        }
        return resultList;
    }

    
    private void loadSearchFields() throws Exception{
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
            searchFields = new String[fieldList.size()+1];
            int index = 0;
            for(Iterator i= fieldList.iterator(); i.hasNext();){
                searchFields[index]=(String)i.next();
                index++;
            }
            searchFields[index]="_hibernate_class";
            System.out.println("SearchFields: "+ searchFields);
        }
    }

}
