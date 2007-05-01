package gov.nih.nci.system.dao.impl.search.utils;

import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import org.apache.log4j.Logger;


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
        private static SearchORMUtils util = new SearchORMUtils();
        private static final SessionFactory sessionFactory;

        static{
            try{                
                sessionFactory = new AnnotationConfiguration().configure("orm3.cfg.xml").buildSessionFactory();
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
       
}
