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
    public HibernateSearch() throws Exception {        
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

    
    
    private String[] getIndexedFields() throws Exception{
        return SearchAPIDAO.getIndexedFields();
    }

}
