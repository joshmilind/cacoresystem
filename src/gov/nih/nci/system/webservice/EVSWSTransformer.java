package gov.nih.nci.system.webservice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

/** 
 * @author Shaziya Muhsin
 * 
 */

/**
 * This class transforms an object that belongs to gov.nih.nci.evs.domain package to gov.nih.nci.evs.domain.ws and vise versa.
 */
public class EVSWSTransformer {
    private static Logger log = Logger.getLogger(EVSWSTransformer.class.getName());
    private String defaultVocabulary = "NCI_Thesaurus";
    private int defaultLimit = 100;
    private Properties evsProperties = new Properties();
    private String evsFileName = "evsBeans.properties";
    private String returnClassName;
    
    /**
     * Instantiates an EVSWSTransformer instance and loads EVS properties
     */
    EVSWSTransformer()throws Exception{
        loadProperties(evsFileName);
    }
    
    /**
     * Converts an instance of a class defined in a gov.nih.nci.evs.domain package to gov.nih.nci.evs.domain.ws package instance.
     * @param evsObject - An instance of a DescLogicConcept or MetaThesaurusConcept defined in the gov.nih.nci.evs.domain package
     * @return
     * @throws Exception
     */
    public Object convertEVStoWS(Object evsObject) throws Exception {

        Object wsObject = null;
        String wsPackageName = null;
        String wsBeanName = evsObject.getClass().getName();
        if (wsBeanName.indexOf(".") > 0) {
            wsPackageName = wsBeanName
                    .substring(0, wsBeanName.lastIndexOf("."));
            wsBeanName = wsBeanName.substring(wsBeanName.lastIndexOf(".") + 1);
        }
        String wsClassName = null;
        if (wsPackageName != null) {
            wsClassName = wsPackageName + ".ws." + wsBeanName;
        }

        if (wsClassName != null) {
            wsObject = getPopulatedWSObject(evsObject, Class.forName(
                    wsClassName).newInstance());
        }

        return wsObject;
    }

    /**
     * Populates wsObject with values specified in the evsObject  
     * @param evsObject - an instance of a class defined in the gov.nih.nci.evs.domain package
     * @param wsObject - an instance of a class defined in the gov.nih.nci.evs.domain.ws package
     * @return
     * @throws Exception
     */
    private Object getPopulatedWSObject(Object evsObject, Object wsObject)
            throws Exception {
        Field[] fields = evsObject.getClass().getDeclaredFields();
        Field[] wsFields = wsObject.getClass().getDeclaredFields();

        for (int f = 0; f < wsFields.length; f++) {
            Field wsField = wsFields[f];
            if (wsField.getName().equalsIgnoreCase("vocabularyName")
                    || wsField.getName().equalsIgnoreCase("serialVersionUID")) {
                continue;
            }
            try {
                Field evsField = getFieldByName(fields, wsField.getName());
                wsField.setAccessible(true);
                evsField.setAccessible(true);
                Object evsFieldValue = null;

                if (evsField.get(evsObject) != null) {
                    Object value = null;
                    Object wsValue = null;
                    ArrayList wsList = new ArrayList();

                    if (wsField.getType().getName().endsWith("ArrayList")) {

                        for (Iterator it = ((Collection) evsField
                                .get(evsObject)).iterator(); it.hasNext();) {
                            value = it.next();
                            if (value != null) {
                                String evsValueClass = value.getClass()
                                        .getName();
                                if (evsValueClass.indexOf(".evs.") > 0) {
                                    String wsValueClass = evsValueClass
                                            .substring(0, evsValueClass
                                                    .lastIndexOf("."))
                                            + ".ws."
                                            + evsValueClass
                                                    .substring(evsValueClass
                                                            .lastIndexOf(".") + 1);
                                    wsValue = getPopulatedWSObject(value, Class
                                            .forName(wsValueClass)
                                            .newInstance());
                                    wsList.add(wsValue);
                                } else {
                                    wsList.add(value);
                                }
                            }

                        }

                        if (wsList.size() > 0) {
                            wsField.set(wsObject, wsList);
                        }
                    } else if (wsField.getType().getName().indexOf(".evs.") > 0) {
                        evsFieldValue = evsField.get(evsObject);
                        if (evsFieldValue != null) {
                            String evsValueClass = evsFieldValue.getClass()
                                    .getName();
                            String wsValueClass = evsValueClass.substring(0,
                                    evsValueClass.lastIndexOf("."))
                                    + ".ws."
                                    + evsValueClass.substring(evsValueClass
                                            .lastIndexOf(".") + 1);
                            wsValue = this.getPopulatedWSObject(evsFieldValue,
                                    Class.forName(wsValueClass).newInstance());
                            if (wsValue != null) {
                                wsField.set(wsObject, wsValue);
                            }
                        }
                    } else {
                        evsFieldValue = evsField.get(evsObject);
                        if (evsFieldValue != null) {
                            wsField.set(wsObject, evsFieldValue);
                        }

                    }

                }

            } catch (Exception ex) {
            }
        }

        return wsObject;

    }

    /**
     * Converts an object defined in the gov.nih.nci.evs.domain.ws package to an object in the gov.nih.nci.evs.domain package
     * @param wsObject an instance of a class defined in the gov.nih.nci.evs.domain.ws package
     * @return
     * @throws Exception
     */
    public Object convertWStoEVS(Object wsObject) throws Exception {

        Object evsObject = null;
        String evsBeanName = wsObject.getClass().getName();
        if (evsBeanName.indexOf(".") > 0) {
            evsBeanName = evsBeanName
                    .substring(evsBeanName.lastIndexOf(".") + 1);
        }
        String evsClassName = null;
        if (evsProperties == null) {
            loadProperties(evsFileName);
        }
        if (evsProperties != null) {
            evsClassName = this.locateClass(evsBeanName);
        }

        if (evsClassName != null) {
            evsObject = getPopulatedEVSObject(wsObject);
        }

        return evsObject;
    }

    /**
     * Populates and return an instance of a class defined in the gov.nih.evs.domain package with the values specified in the wsObject
     * @param wsObject - an instance of a class defined in the gov.nih.nci.evs.domain.ws package
     * @return
     * @throws Exception
     */
    private Object getPopulatedEVSObject(Object wsObject) throws Exception {

        String evsClassName = null;
        String wsBeanName = wsObject.getClass().getName();
        if (wsBeanName.indexOf(".") > 0) {
            wsBeanName = wsBeanName.substring(wsBeanName.lastIndexOf(".") + 1);
        }
        if (evsProperties != null) {
            evsClassName = this.locateClass(wsBeanName);
        }
        Object evsObject = Class.forName(evsClassName).newInstance();

        if (evsClassName != null) {
            Field[] fields = evsObject.getClass().getDeclaredFields();
            Field[] wsFields = wsObject.getClass().getDeclaredFields();

            for (int f = 0; f < wsFields.length; f++) {
                Field wsField = wsFields[f];
                if (wsField.getName().equalsIgnoreCase("serialVersionUID")
                        || wsField.getName().equalsIgnoreCase("vocabularyName")) {
                    continue;
                }
                Field evsField = getFieldByName(fields, wsField.getName());
                wsField.setAccessible(true);
                evsField.setAccessible(true);
                Object evsFieldValue = null;

                if (wsField.get(wsObject) != null) {
                    evsFieldValue = wsField.get(wsObject);

                    if (wsField.getType().getName().endsWith("ArrayList")) {

                        Vector evsVector = new Vector();
                        HashSet evsHashSet = new HashSet();
                        ArrayList evsList = new ArrayList();
                        ArrayList wsList = (ArrayList) wsField.get(wsObject);
                        Object value = null;
                        if (wsList.size() > 0) {

                            for (int l = 0; l < wsList.size(); l++) {
                                value = getPopulatedEVSObject(wsList.get(l));
                                if (evsField.getType().getName().endsWith(
                                        "Vector")) {
                                    evsVector.add(value);
                                } else if (evsField.getType().getName()
                                        .endsWith("HashSet")) {
                                    evsHashSet.add(value);
                                } else if (evsField.getType().getName()
                                        .endsWith("ArrayList")) {
                                    evsList.add(value);
                                }
                            }

                            if (evsField.getType().getName().endsWith("Vector")) {
                                evsField.set(evsObject, evsVector);
                            } else if (evsField.getType().getName().endsWith(
                                    "HashSet")) {
                                evsField.set(evsObject, evsHashSet);
                            } else if (evsField.getType().getName().endsWith(
                                    "ArrayList")) {
                                evsField.set(evsObject, evsList);
                            }
                        }
                    } else {
                        if (wsField.getType().getName().indexOf(".evs.") > 0) {
                            evsFieldValue = this.getPopulatedEVSObject(wsField
                                    .get(wsObject));
                        } else {
                            evsFieldValue = wsField.get(wsObject);
                        }
                        if (evsFieldValue != null) {

                            evsField.set(evsObject, evsFieldValue);
                        }
                    }
                }
            }
        }

        return evsObject;
    }
    
    /**
     * Loads properties from the given file 
     * @param fileName specify the properties file name
     * @throws Exception
     */
    private void loadProperties(String fileName) throws Exception {

        if (evsProperties.size() < 1) {
            if (fileName != null) {
                try {
                    evsProperties
                            .load(Thread.currentThread()
                                    .getContextClassLoader()
                                    .getResourceAsStream(fileName));
                } catch (Exception ex) {
                    log.error("Cannot locate EVS properties file");
                }
            }

        }
    }

    /**
     * Locates and returns a fully qualified class name from a property file 
     * @param beanName - specify the class name 
     * @return
     * @throws Exception
     */
    private String locateClass(String beanName) throws Exception {
        String className = null;
        boolean found = false;
        if (evsProperties == null) {
            loadProperties(evsFileName);
        }
        if (evsProperties != null) {
            for (Iterator i = evsProperties.keySet().iterator(); i.hasNext();) {
                String key = (String) i.next();
                if (beanName.lastIndexOf(".") > 1) {
                    if (key.equals(className)) {
                        className = key;
                        break;
                    }
                } else {
                    if (key.substring(key.lastIndexOf(".") + 1)
                            .equals(beanName)) {
                        className = key;
                        break;
                    }
                }
            }
        }

        return className;
    }

    /**
     * Returns a Field that matches the specifie fieldName
     * @param fields -  Array of Fields
     * @param fieldName - field name
     * @return
     * @throws Exception
     */
    private Field getFieldByName(Field[] fields, String fieldName)
            throws Exception {
        Field field = null;
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].getName().equalsIgnoreCase(fieldName)) {
                field = fields[i];
            }
        }
        return field;
    }
    
    /**
     * Returns the vocabularyName defined in the wsObject
     * 
     * @param wsObject -
     *            an instance of a class defined in the
     *            gov.nih.nci.evs.domain.ws package
     * @return
     * @throws Exception
     */
    
    public String getVocabularyName(Object wsObject) throws Exception {
        String vocabularyName = null;
        Field field = null;
        if (this.getFieldByName(wsObject.getClass().getDeclaredFields(),
                "vocabularyName") != null) {
            field = this.getFieldByName(
                    wsObject.getClass().getDeclaredFields(), "vocabularyName");
            if (field.get(wsObject) != null) {
                vocabularyName = (String) field.get(wsObject);
            }
        }
        return vocabularyName;

    }


  
}
