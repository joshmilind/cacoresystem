package gov.nih.nci.system.webservice;

import gov.nih.nci.common.util.Constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

/*
 * Created on May 16, 2006
 * Shaziya Muhsin
 * 
 */
/**
 * Transforms a bean that belongs to a web service package (*.ws.*) to a non web service bean 
 */
public class WSTransformer {
    private static Logger log = Logger.getLogger(WSTransformer.class);  
    private Properties beanProperties = new Properties();    
    private boolean processOntology = true;
    private boolean implFlag;
    private boolean wsPackage = true;
    /**
     * Constructor that instantiates an instance of a WSTransformer
     * @param beanFileName specifies the properties file name
     * @throws Exception
     */
    public WSTransformer(String beanFileName) throws Exception{       
        try{
            if(beanFileName == null){
                beanFileName = "cacoreBeans.properties";
            }
            loadClassNames(beanFileName);            
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        
    }
    /**
     * Specify if this is a caBIO Ontology
     * @param ontology Set true for a caBIO ontology type
     */
    public void setProcessOntology (boolean ontology){
        processOntology = ontology;
    }
    
   
    /**
     * Loads the properties file
     * @param beanFileName
     * @throws Exception
     */
    private  void loadClassNames(String beanFileName) throws Exception{       
        List fileList = new ArrayList();
        if(beanFileName != null){
            if(beanFileName.indexOf(Constant.COMMA)>0){
                StringTokenizer st = new StringTokenizer(beanFileName,",");
                while(st.hasMoreTokens()){
                    fileList.add(st.nextToken());
                }
            }
            else{
                fileList.add(beanFileName);
            }
            for(int f=0; f<fileList.size(); f++){
                try{
                    String fileName = (String)fileList.get(f);
                    if(fileName !=null && fileName.length()>0){
                        beanProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
                    }
                }catch(Exception ex){
                    log.error(ex.getMessage());
                    throw new Exception("Error: "+ex.getMessage());
                }
                
            }
        }
        else{
            log.error("Error: Unable to locate property files");
            throw new Exception("Error: Unable to locate property files");
        }
    }
    
    
    
/**
 * Returns the processOntology value
 * @return
 */
    public boolean getProcessOntology(){
        return processOntology;
    }
 /**
  * Generates a Search criteria object
  * @param obj
  * @return
  * @throws Exception
  */
    
      public Object buildSearchCriteria(Object obj) throws Exception{
          Class objKlass;
          Object newObject;
          try {
                objKlass = obj.getClass();              
                String objFullName = objKlass.getName();
                String newObjectName = objFullName.replaceAll(".ws.", ".");
                if (newObjectName.endsWith("Impl"))
                {
                    newObjectName = newObjectName.substring(0, newObjectName.length()-4);
                }
                Class newObjClass = Class.forName(newObjectName); 
                newObject= newObjClass.newInstance();               
                newObject = buildCriteria(obj, newObject);                
                
        }catch (Exception e) {
            log.error("WS Error"+ e.getMessage());              
            throw new Exception (e.getMessage());
        }        
        return newObject;
    }
      
 
      /**
       * Generates a search criteria object for a given web service criteria
       * @param criteria
       * @param newObject
       * @return
       */
      private Object buildCriteria(Object criteria, Object newObject){          
            try{
                
               List fields = getAllFields(criteria.getClass());
               
                for(int i=0; i<fields.size(); i++){         
                    Field field = (Field)fields.get(i);
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    String fieldType = field.getType().getName();                    
                    if(fieldName.equalsIgnoreCase("serialVersionUID")){
                        continue;                    
                        }
                    
                    if(field.get(criteria)!=null){
                        Object value = field.get(criteria);
                        Field newField = getFieldByName(fieldName, newObject.getClass());
                        if(fieldType.endsWith("Collection")|| fieldType.endsWith("Vector")|| fieldType.endsWith("Set")|| fieldType.endsWith("ArrayList")){
                            if(((Collection)value).size()<1){
                                continue;
                            }
                            List newList = new ArrayList();
                            Set setList = new HashSet();                        
                            Vector vector = new Vector();
                            for(Iterator it = ((Collection)value).iterator(); it.hasNext();){
                                Object element = it.next();
                                if(element != null){
                                    Object newValue = null;
                                    if(element.getClass().getName().indexOf("domain.ws.")>0){
                                        newValue = buildSearchCriteria(element);                                       
                                    }else{
                                        newValue = element;
                                    }
                                    if(newValue != null){
                                        if(fieldType.endsWith("Collection")){
                                            newList.add(newValue);
                                        }
                                        else if(fieldType.endsWith("Vector")){
                                            vector.add(newValue);
                                        }
                                        else if(fieldType.endsWith("Set")){
                                            setList.add(newValue);
                                        }     
                                    }
                                }
                            }                            
                            if(newList.size()>0){
                                newField.set(newObject,newList);
                            }else if(setList.size()>0){
                                newField.set(newObject, setList);
                            }else if(vector.size()>0){
                                newField.set(newObject,vector);
                            }
                        }else if(fieldType.indexOf(".domain.ws.")>0){
                            Object newValue = buildSearchCriteria(value);                            
                            if(newValue != null){                                
                                newField.set(newObject,newValue);
                            }                            
                        }else if(fieldType.startsWith("java") || field.getType().isPrimitive()){                            
                            if(value != null){
                                newField.set(newObject, value);
                            }
                        }                        
                      }
                }
            }
            catch(Exception ex){
            	log.error("Exception: ", ex);
            }
            return newObject;
           }

      
      //==========================================================================
      /**
       * Returns a fully qualified class name for a given string
       */
      private String getClassName(String className){
          boolean found = false;
          String cName = null;
            if(beanProperties != null){
                for(Iterator i= beanProperties.keySet().iterator(); i.hasNext();){
                    String key = (String)i.next();                 
                     if(className.lastIndexOf(Constant.DOT)>1){
                        if(key.equals(className)){
                            found=true;
                            cName = key;
                            break;
                        }
                    }
                    else{
                        if(key.substring(key.lastIndexOf(Constant.DOT)+1).equals(className)){
                            found=true;
                            cName = key;
                            break;
                        }
                    }            
                }
            }
            return cName;
        }
      /**
       * Returns a field by name
       * @param fields
       * @param fieldName
       * @return
       */
      private Field findFieldByName(Field[] fields, String fieldName){          
            Field field = null;
            for(int i=0; i<fields.length; i++){             
                fields[i].setAccessible(true);              
                if(fields[i].getName().equals(fieldName)){                  
                    field = fields[i];
                    break;
                }
            }
            return field;
        }
      
      /**
       * Generates web service results
       * @param results
       * @return
       * @throws Exception
       */
      public List generateWSResults(List results) throws Exception{     
          
          List alteredResults = new ArrayList();
          if(results.size()>0){
              for(int i=0; i<results.size(); i++){                
                  Object result = results.get(i);
                 
                  String resultClassName = result.getClass().getName();
                  Object newResult = null;
                  
                  if(implFlag){
                      resultClassName = resultClassName+"Impl";                       
                  }
                  if(wsPackage){                      
                      int index = resultClassName.lastIndexOf(Constant.DOT);
                      String className = resultClassName.substring(0, index)+".ws."+ resultClassName.substring(index + 1);
                      newResult = Class.forName(className).newInstance(); 
                  }
                  else{
                      newResult = Class.forName(resultClassName).newInstance();
                  }                  
                  
                  generateWSResults(result, newResult, result.getClass(), newResult.getClass());
                  Class superClass = result.getClass().getSuperclass();
                  
                  
                  if(superClass != null && !superClass.equals(Object.class) && !superClass.isInterface()){
                      Class newSuperClass = newResult.getClass().getSuperclass();
                      generateWSResults(result, newResult, superClass, newSuperClass );
                      superClass = superClass.getSuperclass();
                      
                  }
                  alteredResults.add(newResult);
              }
          }
          else{
              return null;
          } 
         
          return alteredResults;
      }
      
      /**
       * Generates web service results
       * @param result
       * @param newResult
       * @param resultClass
       * @param newResultClass
       * @throws Exception
       */
      private void generateWSResults(Object result, Object newResult, Class resultClass, Class newResultClass) throws Exception{
          
          try{
              Field[] fields = resultClass.getDeclaredFields();
              Field[] newFields = newResultClass.getDeclaredFields();
              if(newFields.length == 0 && !newResultClass.getSuperclass().equals(Object.class)){
                  newFields = newResultClass.getSuperclass().getDeclaredFields();
              }
              
              if(newFields.length > 0){
                  for(int i=0; i< fields.length; i++){
                      fields[i].setAccessible(true);
                      String fieldName = fields[i].getName();
                      String fieldType = fields[i].getType().getName();
                      
                      if(fieldName.equalsIgnoreCase("serialVersionUID")){
                            continue;
                        }
                      Field newField = this.findFieldByName(newFields, fieldName);
                      
                      if(newField != null){
                          if(fieldName.endsWith("Collection") && fieldType.startsWith("java")){
                              String bean = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.indexOf("Collection"));
                              String beanClassName = getClassName(bean);
                              if(beanClassName != null){
                                  Collection value = new ArrayList();
                                  newField.set(newResult, value);
                              }
                          }
                          else if(!((fields[i].getType().isPrimitive() ||fieldType.startsWith("java") && !fieldType.endsWith("Collection")))){
                              newField.set(newResult, null);
                          }
                          else{
                              Object value = fields[i].get(result);
                              if(value != null){
                                  newField.set(newResult, value);
                              }
                          }
                      }                     
                  }
              }
              
          }catch(Exception ex){
              throw new Exception(ex.getMessage());
          }
      }
     
      /**
       * Returns the search class name
       * @param targetClassName
       * @return
       * @throws Exception
       */
      
      public String getSearchClassName(String targetClassName)throws Exception{          
          String searchClassName = "";
          String className = null;
          
          if(targetClassName.indexOf(".ws.")>0){
              className = targetClassName.replaceAll(".ws.",".");      
              wsPackage = true;                          
          }
          else{
              className = targetClassName;              
              wsPackage = false;
          } 
          
          if(targetClassName.indexOf(",")>0){
              StringTokenizer st = new StringTokenizer(className, ",");
              while(st.hasMoreTokens()){                  
                  String implClassName = st.nextToken();
                  String validClassName = getClassName(implClassName);                  
                  if(validClassName == null && implClassName.endsWith("Impl")){
                      implFlag = true;
                      String cName = implClassName.substring(0,implClassName.lastIndexOf("Impl"));
                      searchClassName = getClassName(cName) + ",";
                  }else{
                      implFlag = false;
                      searchClassName += validClassName + ",";
                  }
              }
              searchClassName = searchClassName.substring(0,searchClassName.lastIndexOf(","));
          }else{
              searchClassName = getClassName(className);          
              if(searchClassName == null && className.endsWith("Impl")){
                  implFlag = true;
                  String cName = className.substring(0,className.lastIndexOf("Impl"));
                  searchClassName = getClassName(cName);              
              }   
              else{
                  implFlag = false;
              }
          }
          if(searchClassName == null){
              throw new Exception("Invalid class name: "+ targetClassName);
          }
          return searchClassName;
      }
    
      /**
       * Generates a search criteria
       * @param criteria
       * @return
       * @throws Exception
       */
      public Object getSearchCriteria(Object criteria)throws Exception{
          Object searchCriteria = null;
          try{
              if(criteria.getClass().getName().indexOf(".ws.")>0){
                  searchCriteria = buildSearchCriteria(criteria);            
              }
              else{              
                  searchCriteria = criteria;                  
              }
          }catch(Exception ex){
              throw new Exception(ex.getMessage());
          }
         return searchCriteria; 
      }
     /**
      * Returns the Ontology class name
      * @param beanName
      * @return
      */
    private String getOntologyClassName(String beanName){
        String ontologyBeanName = null;
        String ontologyClassName = null;
        if(beanName.indexOf("Ontology")>0 && processOntology){
            if(beanName.startsWith("Child") && beanName.length()>5){
                ontologyBeanName = beanName.substring(5);
            }
            else if(beanName.startsWith("Parent")){
                ontologyBeanName = beanName.substring(6);
            }
            
            if(ontologyBeanName != null){
                ontologyClassName = getClassName(ontologyBeanName);
            }
        }
        
        return ontologyClassName;
    } 

/**
 * Returns all the fields
 * @param criteriaClass
 * @return
 */
    private List getAllFields(Class criteriaClass){
        List fieldList = new ArrayList();
        for(Class c = criteriaClass; !c.getName().equals("java.lang.Object") && c != null; c = c.getSuperclass()){
            Field[] fs = c.getDeclaredFields();
            for(int i=0; i<fs.length; i++){                
                fieldList.add(fs[i]);                
            }
        }
        return fieldList;
        
    }
   
  
    /**
     * Returns a field by name
     */
    private Field getFieldByName(String fieldName, Class newClass){
        Field field = null;
        List fieldList = getAllFields(newClass);
        for(int i=0; i<fieldList.size(); i++){
            Field f = (Field)fieldList.get(i);
            f.setAccessible(true);
            if(f.getName().equals(fieldName)){
                field = f;
                break;
            }
        }
        return field;
    }

}
