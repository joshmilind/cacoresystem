package gov.nih.nci.common.util.search;

import java.lang.annotation.Annotation;
import java.util.*;
import java.io.*;
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
            SearchAPIProperties properties = SearchAPIProperties.getInstance();
            HashMap indexFields = properties.getIndexProperties();
            System.out.println("Enter class name to index or press <<ENTER>> to index all classes... ?");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String input = stdin.readLine();
            String ormFileName = properties.getOrmFileName()!=null?properties.getOrmFileName(): "orm3.cfg.xml";
            String pkgName = properties.getIndxedPackageNames() != null ? properties.getIndxedPackageNames() : "gov.nih.nci.cabio.domain";            
            SessionFactory sessionFactory = new AnnotationConfiguration().configure(ormFileName).buildSessionFactory();
            Set classSet = getIndexedClasses(sessionFactory, pkgName);
            if(classSet.size()>0){   
                for(Iterator i = classSet.iterator(); i.hasNext();){
                    EntityPersister persister = (EntityPersister)i.next();
                    String fields = (String)indexFields.get(persister.getEntityName());
                    String hqlQuery = null;                
                    if(fields.length() > 0){
                        fields = fields.indexOf(";")>0? fields.replace(";",","): fields;
                        if(fields.endsWith(",")){
                            fields = fields.substring(0, fields.lastIndexOf(","));
                        }
                        hqlQuery = "Select "+ fields +" from "+ persister.getEntityName();
                    }else{                        
                        continue;
                    }                                      
                    if(input.length()> 0 && input != null ){
                        if(input.equals("*") || input.indexOf("*")>-1){
                            pool.execute(new Indexer(sessionFactory.openSession(),persister, hqlQuery));
                        }else if(input.equals(persister.getEntityName())){
                           pool.execute(new Indexer(sessionFactory.openSession(),persister, hqlQuery));
                           break;
                        }
                    }else{
                        pool.execute(new Indexer(sessionFactory.openSession(),persister, hqlQuery));
                    }
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
            if(pkg.indexOf("*")>0){
                pkg = pkg.substring(0, pkg.indexOf("*"));
            }            
            if(className.startsWith(pkg) || pkg.equals("*")){
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
