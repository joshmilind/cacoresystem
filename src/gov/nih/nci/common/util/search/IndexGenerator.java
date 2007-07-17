package gov.nih.nci.common.util.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.search.Search;

/*
 * Created on Apr 3, 2007
 * Shaziya Muhsin
 */



public class IndexGenerator{
    private static Logger log = Logger.getLogger(IndexGenerator.class.getName());
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
            int max = properties.getMaxRecordsPerQuery()>0 ? properties.getMaxRecordsPerQuery(): 10000;
            SessionFactory sessionFactory = new AnnotationConfiguration().configure(ormFileName).buildSessionFactory();
            log.info("Maximum records per query:  "+ properties.getMaxRecordsPerQuery());
            log.info("Indexing package: "+ properties.getIndxedPackageNames());
            log.info("Reading ORM file : "+ properties.getOrmFileName());
            Set classSet = getIndexedClasses(sessionFactory, pkgName);
            if(classSet.size()>0){ 
                long count = 0L;
                for(Iterator i = classSet.iterator(); i.hasNext();){
                    EntityPersister persister = (EntityPersister)i.next();
                    String countQuery = "Select count(*) from "+ persister.getEntityName();
                    try{
                        count = (Long)Search.createFullTextSession(sessionFactory.openSession()).iterate(countQuery).next();                
                    }catch(Exception e){
                        throw new Exception(e);
                    }                                             
                    if(input.length()> 0 && input != null ){                        
                        if(input.equals("*") || input.indexOf("*")>-1){
                            pool.execute(new Indexer(sessionFactory.openSession(),persister, 0, max, count));
                        }else if(input.equals(persister.getEntityName())){
                            if(count > max){   
                                log.info(persister.getEntityName() +" : "+ count +" records found" +"\t"+ max);
                                for(int startIndex = 0, endIndex = max + startIndex; startIndex < count; startIndex = endIndex, endIndex += max){                                
                                    pool.execute(new Indexer(sessionFactory.openSession(),persister, startIndex, max, count));
                                    //log.info("\tIndexing: "+ startIndex +" - "+ endIndex +" of "+ persister.getEntityName());
                                } 
                            }else{
                                log.info(persister.getEntityName() +" : "+ count +" records found" +"\t"+ max);
                                pool.execute(new Indexer(sessionFactory.openSession(),persister, 0, max, count));
                            }                           
                           break;
                        }
                    }else{
                        log.info(persister.getEntityName() +" : "+ count +" records found" +"\t"+ max);
                        if(count > max){                                                        
                            for(int startIndex = 0, endIndex = max + startIndex; startIndex < count; startIndex = endIndex, endIndex += max){                                
                                pool.execute(new Indexer(sessionFactory.openSession(),persister, startIndex, max, count));
                                //log.info("\tIndexing: "+ startIndex +" - "+ endIndex +" of "+ persister.getEntityName());
                            } 
                        }else{
                            //log.info(persister.getEntityName() +" : "+ count +" records found" +"\t"+ max);
                            pool.execute(new Indexer(sessionFactory.openSession(),persister, 0, max, count));
                        }
                        
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
