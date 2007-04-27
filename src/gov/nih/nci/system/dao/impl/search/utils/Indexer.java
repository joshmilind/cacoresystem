package gov.nih.nci.system.dao.impl.search.utils;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/*
 * Created on Apr 11, 2007
 * Shaziya Muhsin
 *
 */
/**
 * Generates a lucene based index 
 */
 /**
  * Generates lucene indexes for the java beans
  */        
public class Indexer extends Thread{

    private FullTextSession fullTextSession;
    private EntityPersister persister;
    static int counter = 0;
    
    /**
     * Generates an index for the specified entity
     * @param session
     * @param entity
     */
    public Indexer(SessionFactory session, EntityPersister entity) {
        super();
        fullTextSession = Search.createFullTextSession(session.openSession());
        persister = entity;
        counter++;       
    }
    
    /**
     * Generates lucene documents
     */
    public void run(){
        long start = System.currentTimeMillis();
        String hqlQuery = "from "+ persister.getEntityName();          
        //ScrollableResults results = fullTextSession.createQuery(hqlQuery).setCacheMode(CacheMode.IGNORE).setReadOnly(true).scroll(ScrollMode.FORWARD_ONLY);
        ScrollableResults results = fullTextSession.createQuery(hqlQuery).setCacheMode(CacheMode.IGNORE).setReadOnly(true).scroll();
        int breakPoint = 0;
        while(results.next()){
            try{
                fullTextSession.index(results.get(0));                
            }catch(Exception ex){
                System.out.println("Error indexing: "+ex.getMessage());
            }            
            if(breakPoint = 100){                
                breakPoint = 0;
                fullTextSession.clear();                
            }
            breakPoint++;
        }
        long end = System.currentTimeMillis();
        results.last();        
        System.out.println("Total number of: "+ persister.getEntityName() +"\t"+ results.getRowNumber());
        fullTextSession.close();

    }

}
