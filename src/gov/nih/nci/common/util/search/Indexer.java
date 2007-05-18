package gov.nih.nci.common.util.search;

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
        int total=0;
        try{
        	long start = System.currentTimeMillis();
        	String hqlQuery = "from "+ persister.getEntityName();
            ScrollableResults results = fullTextSession.createQuery(hqlQuery).setCacheMode(CacheMode.IGNORE).setReadOnly(true).scroll();
            if(results != null){
            	results.first();
                int breakPoint = 0;
                do{
                	fullTextSession.index(results.get(0));                            
                    if(breakPoint == 100){                
                        breakPoint = 0;
                        fullTextSession.clear();                
                    }
                    breakPoint++;
                }while(results.next());
                long end = System.currentTimeMillis();
                timeLag = end - start;
                results.last();        
                total = results.getRowNumber();     
            }
                   
        }catch(Exception ex){
        	log.error("Error occured while indexing "+ persister.getEntityName() + ex);
        }finally{
        	log.info("Time taken to index "+ total +"\t"+persister.getEntityName()+ "\tMS:"+ timeLag);
        	fullTextSession.close();        	
        }   
    }

}
