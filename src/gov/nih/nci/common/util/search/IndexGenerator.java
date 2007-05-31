package gov.nih.nci.common.util.search;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.*;
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

	

    public static void main(String[] args)throws Exception{
        int threadCount = getThreadCount()>0 ? getThreadCount():1;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        try{
            SessionFactory sessionFactory = new AnnotationConfiguration().configure("orm3.cfg.xml").buildSessionFactory();
            Set classSet = getIndexedClasses(sessionFactory, "gov.nih.nci.cabio.domain");

            if(classSet.size()>0){               
                for(Iterator i = classSet.iterator(); i.hasNext();){
                    EntityPersister persister = (EntityPersister)i.next();
                    pool.execute(new Indexer(sessionFactory,persister));
                }
            }


        }catch(Exception ex){
            throw new Exception("Error generating index: "+ ex.getMessage());
        }finally{
            pool.shutdown();
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

    static public int getThreadCount(){
        SearchAPIProperties properties = SearchAPIProperties.getInstance();
        return properties.getThreadCount();
    }


}
