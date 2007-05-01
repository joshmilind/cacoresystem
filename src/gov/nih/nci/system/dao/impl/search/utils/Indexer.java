package gov.nih.nci.system.dao.impl.search.utils;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.apache.log4j.*;

/*
 * Created on Apr 11, 2007
 * Shaziya Muhsin
 *
 */

 /**
  * Generates lucene indexes for the java beans
  */        
public class Indexer extends Thread{

    private FullTextSession fullTextSession;
    private EntityPersister persister;    
    private static Logger log = Logger.getLogger(Indexer.class.getName());
    /**
     * Generates an index for the specified entity
     * @param session
     * @param entity
     */
    public Indexer(SessionFactory session, EntityPersister entity) {        
        fullTextSession = Search.createFullTextSession(session.openSession());
        persister = entity;             
    }
    
    /**
     * Generates lucene documents
     */
    public void run(){
        long timeLag = 0;
        try{
        	long start = System.currentTimeMillis();
        	String hqlQuery = "from "+ persister.getEntityName();
            ScrollableResults results = fullTextSession.createQuery(hqlQuery).setCacheMode(CacheMode.IGNORE).setReadOnly(true).scroll();
            int breakPoint = 0;
            while(results.next()){
                try{
                    fullTextSession.index(results.get(0));                
                }catch(Exception ex){
                    System.out.println("Error indexing: "+ex.getMessage());
                }            
                if(breakPoint == 100){                
                    breakPoint = 0;
                    fullTextSession.clear();                
                }
                breakPoint++;
            }
            long end = System.currentTimeMillis();
            timeLag = end - start;
            results.last();        
            log.info("Indexed:  "+ persister.getEntityName() +"\t"+ results.getRowNumber());            
        }catch(Exception ex){
        	log.error("Error occured while indexing "+ persister.getEntityName() + ex);
        }finally{
        	log.info("Time taken to index "+ persister.getEntityName()+ "\t"+ timeLag);
        	fullTextSession.close();
        	System.exit(1);
        }   
    }

}
