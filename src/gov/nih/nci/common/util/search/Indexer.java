package gov.nih.nci.common.util.search;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.*;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.apache.log4j.*;
import java.util.*;

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
    private String hqlQuery;
    private int interval = 10000;
    private int startIndex =0;
    private long endIndex =0;
    static Logger log = Logger.getLogger(Indexer.class.getName());
    /**
     * Generates an index for the specified entity
    */    
    public Indexer(Session session, EntityPersister entity, int start, int max, long end) {
        fullTextSession = Search.createFullTextSession(session);
        persister = entity;
        hqlQuery = "from "+ entity.getEntityName();
        if(max > 0){
            interval = max;
        }
        if(start> 0){
            startIndex = start;
        }
        if(end > 0){
            endIndex = end;            
        }else{
            endIndex = start + interval;
        }
        
    }

    /**
     * Generates lucene documents
     */
    public void run(){
        long timeLag = 0;
        int total=0;       
        
        Long count = 0l;
        try{
        	long start = System.currentTimeMillis();  
            org.hibernate.Query query = fullTextSession.createQuery(hqlQuery);
            System.out.print(".");
            query.setFirstResult(startIndex);
            query.setMaxResults(interval);
            log.info("\t\tStart :"+ startIndex +"\t"+ (startIndex + interval)+"\t"+ persister.getEntityName());
            System.out.println("\t\tStart :"+ startIndex +"\t"+ (startIndex + interval)+"\t"+ persister.getEntityName());
            ScrollableResults results = query.setCacheMode(CacheMode.IGNORE).setReadOnly(true).scroll();
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
                log.info("Time taken to index "+ total +"\t"+persister.getEntityName()+ "\tMS:"+ timeLag);
                //log.info("Indexed: "+ persister.getEntityName() +": " + interval);

        }catch(Exception ex){
            log.error("Error occured while indexing "+ persister.getEntityName() + ex);                    	
        }finally{
        	fullTextSession.close();
        }
    }

}
