package gov.nih.nci.system.dao.impl.search.utils;

import java.lang.annotation.Annotation;
import java.util.*;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/*
 * Created on Apr 3, 2007
 * Shaziya Muhsin
 */



public class IndexGenerator{

	//Create thread pool
    //Check if thread pool is empty
    //Check if thread pool is full
    //Assign operation to thread pool


    public static void main(String[] args)throws Exception{

        System.out.println("SearchIndexGenerator generating indexes");
        System.out.println("getIndexedClasses");
        try{
            SessionFactory sessionFactory = new AnnotationConfiguration().configure("orm3.cfg.xml").buildSessionFactory();            
            Set classSet = getIndexedClasses(sessionFactory, "gov.nih.nci.cabio.domain");
            System.out.println("Number of indexed classes: "+ classSet.size());

            if(classSet.size()>0){
                Thread[] t = new Thread[classSet.size()];
                int count = 0;
                for(Iterator i = classSet.iterator(); i.hasNext();){
                    EntityPersister persister = (EntityPersister)i.next();
                    t[count] = new Indexer(sessionFactory,persister);
                    t[count].start();
                    count++;
                }
            }


        }catch(Exception ex){
            throw new Exception("Error generating index: "+ ex.getMessage());
        }
    }
    /**
     * Retruns the class names based on the annotations 
     * @param sessionFactory
     * @param pkg
     * @return
     * @throws Exception
     */
    static public Set getIndexedClasses(SessionFactory sessionFactory, String pkg) throws Exception{
        Map metadata = sessionFactory.getAllClassMetadata();
        Set<EntityPersister> classSet = new HashSet<EntityPersister>();
        for(Iterator i=metadata.values().iterator(); i.hasNext();){
            EntityPersister persister = (EntityPersister)i.next();
            String className = persister.getClassMetadata().getEntityName();
            Annotation[] annotations = Class.forName(persister.getEntityName()).getAnnotations();
            if(className.startsWith(pkg)){
                for(int a=0; a<annotations.length; a++){
                    if(annotations[a].annotationType().getSimpleName().equalsIgnoreCase("Indexed")){
                        classSet.add(persister);
                    }
                }
            }
        }
        return classSet;
    }



}
