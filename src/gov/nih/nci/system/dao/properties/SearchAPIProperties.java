package gov.nih.nci.system.dao.properties;
import gov.nih.nci.system.dao.impl.search.utils.Indexer;

import java.io.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

import org.apache.log4j.Logger;

/*
 * Created on May 16, 2007
 * ShaziyaMuhsin
 *
 */
public class SearchAPIProperties {
    private volatile static SearchAPIProperties properties;
    private static String ormFileName;
    private static String[] indexedFields;
    private static String pkgs;
    private static Logger log = Logger.getLogger(SearchAPIProperties.class.getName());
    private static SessionFactory sessionFactory;
    private static int counter = 0;


    private SearchAPIProperties() {}

    public static SearchAPIProperties getInstance(){
        try{
            if(properties == null){
                synchronized (SearchAPIProperties.class){
                    if(properties == null){
                        properties = new SearchAPIProperties();
                    }
                }
                loadProperties("searchapiconfig.properties");
                configureSession();
                counter++;
                System.out.println(counter + ". SearchAPIProperties instantiated <<<<<<< ");
            }
        }catch(Exception ex){
            log.error("Unable to initialize Search API Properties: "+ ex);
        }

        return properties;
    }
    private static void loadProperties(String propertiesFileName) throws Exception{
        InputStream is = null;
        String indexedPropertyFiles = null;
        Properties properties = new Properties();
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
            properties.load(is);

            for(Iterator it = properties.keySet().iterator(); it.hasNext();){
                String key = (String)it.next();
                String value = properties.getProperty(key);
                if(key.equalsIgnoreCase("orm_files")){
                    ormFileName = value;
                }else if(key.equalsIgnoreCase("index_package")){
                    pkgs = value;
                }else if(key.equalsIgnoreCase("indexed_fields")){
                    populateFields(value);
                }
            }

        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        finally{
            is.close();
        }
    }
    private static void populateFields(String indexedPropertyFiles) throws Exception{
        Set fieldList = new HashSet();
        if(indexedPropertyFiles.indexOf(";")>0){
            for(StringTokenizer st = new StringTokenizer(indexedPropertyFiles,";");st.hasMoreTokens();){
                String fieldsPropertiesFile = st.nextToken();
                if(fieldsPropertiesFile != null){
                    fieldList = getFieldNames(fieldsPropertiesFile);
                }
            }
        }else{
            fieldList = getFieldNames(indexedPropertyFiles);
        }


        if(fieldList.size()>0){
            int counter = 0;
            indexedFields = new String[fieldList.size()];
            for(Iterator it = fieldList.iterator(); it.hasNext();){
                indexedFields[counter]= (String) it.next();
                counter++;
            }
        }

    }
    private static Set getFieldNames(String fileName) throws Exception{
        Set fieldList = new HashSet();
        Properties properties = new Properties();
        InputStream is = null;
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            properties.load(is);

            for(Iterator it = properties.keySet().iterator(); it.hasNext();){
                String key = (String)it.next();
                String fields = properties.getProperty(key);

                for(StringTokenizer st = new StringTokenizer(fields,";");st.hasMoreTokens();){
                    fieldList.add(st.nextToken());
                }
            }
        }catch(Exception ex){
           log.error(ex.getMessage());
        }
        finally{
            is.close();
        }
        return fieldList;
    }
    private static void configureSession() throws Exception{
    	if(ormFileName != null){
    		sessionFactory = new AnnotationConfiguration().configure(ormFileName).buildSessionFactory();
    	}else{
    		throw new Exception("Unable to create session");
    	}
    }
    public static Session getSession(){
    	return sessionFactory.openSession();
    }
    public String[] getIndexedFields(){
        return indexedFields;
    }
    public String getOrmFileName(){
        return ormFileName;
    }
    public String getIndxedPackageNames(){
        return pkgs;
    }

}