package gov.nih.nci.system.dao.impl.search.service;

import gov.nih.nci.search.SearchQuery;
import gov.nih.nci.system.dao.*;


import java.util.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.common.net.*;
import gov.nih.nci.common.util.search.*;

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
/**
 * HibernateSearch performs text based queries by synchronizing the document index with the database, 
 * and returns domain objects back to the user. This class implements the Hibernate search engine. 
 */

public class HibernateSearch implements Searchable{
    private static Logger log = Logger.getLogger(HibernateSearch.class.getName());
    private SearchAPIProperties properties;
    /**
     * Default constructor
     */
    public HibernateSearch() throws Exception {
    	properties = SearchAPIProperties.getInstance();
    }

    /**
     * Performs queries on the underlying database
     * @param queryString
     * @return
     * @throws DAOException
     */
    public List query(String queryString) throws DAOException{
        List resultList = null;
        FullTextSession fullTextSession;

        try{
            fullTextSession = org.hibernate.search.Search.createFullTextSession(SearchAPIProperties.getSession());
        }
        catch(Exception e)
        {
            log.error("Unable to open session " + e);
            throw new DAOException("Unable to open session  " + e);
        }
      try{
          String keyword = queryString;          
          org.apache.lucene.queryParser.QueryParser parser = new MultiFieldQueryParser(getIndexedFields(), new StandardAnalyzer());//
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
/**
 * Executes queries 
 * @param searchString
 * @param sort
 * @return returns sorted results
 * @throws DAOException
 */
    public List query(String searchString, gov.nih.nci.search.Sort sort) throws DAOException{
        List results = new ArrayList();
        if(sort != null){
            if(sort.getSortByClassName()){
                //sort results
                results = query(searchString);
            }else{
                results = query(searchString);
            }
        }
        return results;
    }
    private String[] getIndexedFields() throws Exception{
        return properties.getIndexedFields();
    }

}
