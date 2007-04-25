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
public class SearchORMUtils {

    public SearchORMUtils() {}
        private static SearchORMUtils util = new SearchORMUtils();
        private static final SessionFactory sessionFactory;

        static{
            try{
                System.out.println("building session factory");
                sessionFactory = new AnnotationConfiguration().configure("orm3.cfg.xml").buildSessionFactory();
            }catch(Throwable ex){
                throw new ExceptionInInitializerError(ex);
            }
        }
        public static Session getSession()throws HibernateException{
            return sessionFactory.openSession();
        }
        public static void endSession() throws HibernateException{
            sessionFactory.close();
        }
       
}
