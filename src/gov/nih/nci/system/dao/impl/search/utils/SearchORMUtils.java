package gov.nih.nci.system.dao.impl.search.utils;

import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.io.*;
import gov.nih.nci.common.util.*;
import org.apache.log4j.Logger;
import org.jdom.*;

import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;


/*
 * Created on Mar 29, 2007
 * Shaziya Muhsin
 *
 */
/**
 * Creates a hibernate session that provides quering and indexing capabilties
 */
public class SearchORMUtils {

    public SearchORMUtils() {}
    private static Logger log= Logger.getLogger(SearchORMUtils.class.getName());
        private static SearchORMUtils util = new SearchORMUtils();
        private static Map ormList= new HashMap();
        private static Set fieldList = new HashSet();
        private static Set indexPackage = new HashSet();
        private static final Map sessionMap= new HashMap();

        private static  SessionFactory sessionFactory;
        private static  String[] indexedFields;
        private static String propertiesFile = "searchapiconfig.properties";

        static{
            try{
                //sessionFactory = new AnnotationConfiguration().configure("orm3.cfg.xml").buildSessionFactory();
                loadProperties();
                for(Iterator it = ormList.keySet().iterator(); it.hasNext();){
                    String ormFile = (String)it.next();
                    SessionFactory sessionFactory = new AnnotationConfiguration().configure(ormFile).buildSessionFactory();
                    sessionMap.put(ormFile, sessionFactory);
                }
            }catch(Throwable ex){
                throw new ExceptionInInitializerError(ex);
            }
        }
        /**
         * Returns a session
         * @return
         * @throws HibernateException
         */
        public static Session getSession()throws HibernateException{
            return sessionFactory.openSession();
        }
        /**
         * Ends a session
         * @throws HibernateException
         */
        public static void endSession() throws HibernateException{
            sessionFactory.close();
        }

            private static void loadProperties()throws Exception{
				
                InputStream is = null;
                String indexedPropertyFiles = null;
                Properties properties = new Properties();
                try{
                    is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile);
                    properties.load(is);
                    
                    for(Iterator it = properties.keySet().iterator(); it.hasNext();){
                        String key = (String)it.next();
                        String value = properties.getProperty(key);
                        if(key.equalsIgnoreCase("orm_files")){
                            populateOrmList(value);
                        }else if(key.equalsIgnoreCase("index_package")){
                            populateIndexPackageList(value);
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
            private static void populateOrmList(String ormFiles){
				
                if(ormFiles.indexOf(";")>0){
                    StringTokenizer st = new StringTokenizer(ormFiles,";");
                    while(st.hasMoreTokens()){
                        String ormFileName = st.nextToken();
                        setOrmList(ormFileName);
                    }
                }
                else{
                    setOrmList(ormFiles);
                }


            }

            private static void setOrmList(String ormFileName){

				Set packageNames = new HashSet();
                List list = getList(ormFileName, "hibernate-configuration/session-factory/mapping");

				try{
                for(Iterator it = list.iterator(); it.hasNext();){
					Element element = (Element)it.next();
					Attribute att = element.getAttribute("resource");
                    String name = att.getValue();

                    if(name.indexOf(".")>0){
						name = name.substring(0, name.indexOf("."));
						name = name.replace("/",".");
						name = name.substring(0, name.lastIndexOf("."));
						if(name.indexOf(".impl")>0){
							name = name.substring(0,name.lastIndexOf(".impl"));
						}
                        packageNames.add(name);
                    }
                }
                ormList.put(ormFileName, packageNames);
				}catch(Exception ex){
					ex.printStackTrace();
				}
            }
            private static void populateFields(String indexedPropertyFiles) throws Exception{
				
                for(StringTokenizer st = new StringTokenizer(indexedPropertyFiles,";");st.hasMoreTokens();){
                    String fieldsPropertiesFile = st.nextToken();
                    if(fieldsPropertiesFile != null){
                        setFieldNames(fieldsPropertiesFile);
                    }
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
            private static void setFieldNames(String fileName) throws Exception{

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
            }
            private static void populateIndexPackageList(String packageNames){
                if(packageNames.indexOf(";")>0){
                    for(StringTokenizer st = new StringTokenizer(packageNames,";");st.hasMoreTokens();){
                        String packageName = st.nextToken();
                        if(packageName != null){
                            indexPackage.add(packageName);
                        }
                    }
                }else{
                    indexPackage.add(packageNames);
                }
                
            }
            public static Map getSessions(){
                return sessionMap;
            }
            public static List getSessionBySource(String pkg){
                List sessionList = new ArrayList();
                for(Iterator it = ormList.keySet().iterator(); it.hasNext();){
                    String ormFile = (String)it.next();
                    Set pkgNames = (Set)ormList.get(ormFile);
                    for(Iterator i = pkgNames.iterator(); i.hasNext();){
                        String pkgName = (String)i.next();
                        if(pkgName.equalsIgnoreCase(pkg)){
                            sessionList.add(sessionMap.get(ormFile));
                        }
                    }
                }
                return sessionList;
            }
       private static List getList(String fileName, String xPathExpression){
		   Document doc = null;
		   SAXBuilder builder = new SAXBuilder();
		   	try {
		   		 doc = builder.build(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
		   	}
		   	catch (JDOMException e) {
		   	    log.error("JDOMException: " + e.getMessage());
		   	    }
		   	catch (IOException e) {
		   	    log.error("IOException: " + e.getMessage());
	    }
		   List list=null;
		   		try
		   		{
		   			XPath x = XPath.newInstance(xPathExpression);
		   		 	list = x.selectNodes(doc);

		   		} catch (JDOMException e){
		   		    log.error("JDOMException: " + e.getMessage());
		   		}

	  	return list;
	   }
	   public static String[] getIndexedFields(){
		   return indexedFields;
		   }
}
