/**
 * Created on Dec 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.common.util;

import gov.nih.nci.common.util.SearchUtils;

import java.util.*;

import javax.servlet.*;
import java.lang.reflect.*;
import org.apache.log4j.*;


/**
 * @author LeThai
 *
 */

public class JSPUtils 
{    
    private static Logger log = Logger.getLogger(JSPUtils.class.getName());
    private static JSPUtils jspUtils;
    private static Properties properties = new Properties();    
    private static List domainNames = new ArrayList();
    private static Set packages = new HashSet();
    private static final String SEMICOLON_SEPARATOR = ";";
    private static final String COMMA_SEPARATOR = ",";
    private static Set indexedFields = new HashSet();
       
   
    /**
     * Instantiate JSPUtils and read bean properties
     * @param config
     * @return JSPUtils
     */
    synchronized public static JSPUtils getJSPUtils(ServletConfig config)
    {       
        try
        {
            if(jspUtils == null)
            {                
                List fileList = new ArrayList();
                ServletContext context = config.getServletContext();
                String beanFiles = context.getInitParameter ("cacoreBeans.Properties");
                
                jspUtils = new JSPUtils();         
                fileList = getFileList(beanFiles);
                
                loadProperties(fileList);
                loadIndexedFields();
            }
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
        }
        return jspUtils;
        
    }
    synchronized public static JSPUtils getJSPUtils(ServletContext context)
    {       
        try
        {
            if(jspUtils == null)
            {                
                List fileList = new ArrayList();
                //ServletContext context = config.getServletContext();
                String beanFiles = context.getInitParameter ("cacoreBeans.Properties");
                
                jspUtils = new JSPUtils();         
                fileList = getFileList(beanFiles);
                
                loadProperties(fileList);
            }
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
        }
        return jspUtils;
        
    }    
    
    /**
     * Get all the domain names includes package information
     * @return list of domain names
     */
    public List getDomainNames()
    {      
          
       Collections.sort(domainNames);       
        return domainNames;
        
    }
    /**
     * Get all the packages
     * @return all packages
     */
    public List getPackages()
    {        
        List pkg = new ArrayList(packages);        
        Collections.sort(pkg);        
        return pkg;
    }
    
    
    /**
     * Get all the domain names includes package information for a specified package name
     * @param packageName
     * @return list of domain names
     */
    public List getClassNames(String packageName)
    {
        Collections.sort(domainNames);
        if(packageName == null || packageName.equalsIgnoreCase("All"))
        {
            return domainNames;
        }
        //filter the domainNames to get all classes in this package
        List packageClasses = new ArrayList();
        String ckassName="";
        for(int i=0; i<domainNames.size(); i++)
        {
            ckassName = (String)domainNames.get(i);
            if(ckassName.indexOf(packageName, 0) >= 0)
            {                  
                packageClasses.add(ckassName);                
            }
        }
        Collections.sort(packageClasses);
        return packageClasses;
    }
    
       
    /**
     * Get the list of all fields for the class
     * @param className
     * @return List of all fields for the given class
     */
    public List getAllFields(String className)
    {
        List  fieldNames = new ArrayList();
        
        try
        {
            Class ckass = Class.forName(className);
                        
            HTTPUtils httpUtils = new HTTPUtils();
            Field[] fields = httpUtils.getAllFields(ckass);
            String fieldType;
            for(int i=0; i< fields.length; i++)
            {
                fields[i].setAccessible(true);
                fieldType = fields[i].getType().getName();
                
                if (isSearchable(fieldType))
                {
                	fieldNames.add(fields[i].getName());    
                }
            }
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            
        }
        return fieldNames;
    
    }
    
    /**
     * Get the list of all Fields for the class
     * @param className
     * @return Field[] of all fields for the given class
     */
    public List getSearchableFields(String className)
    {
        Field[] fields = null;
        List searchableFields = new ArrayList();
        
        try
        {
            Class ckass = Class.forName(className);
                        
            HTTPUtils httpUtils = new HTTPUtils();
            fields = httpUtils.getAllFields(ckass);
            String fieldType;
            
            for(int i=0; i< fields.length; i++)
            {
                fields[i].setAccessible(true);
                fieldType = fields[i].getType().getName();
                
                if (isSearchable(fieldType))
                {
                	searchableFields.add(fields[i]);    
                }
               
            }            
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            
        }
        return searchableFields;
    
    }    
     
    private boolean isSearchable(String fieldType){
    	boolean isSearchable=false;
    	
    	if(fieldType.equals("java.lang.Long") || 
        		fieldType.equals("java.lang.String") || 
        		fieldType.equals("java.lang.Integer") || 
        		fieldType.equals("java.lang.Float") || 
        		fieldType.equals("java.lang.Double") || 
        		fieldType.equals("java.lang.Boolean") || 
        		fieldType.equals("java.util.Date")
        		){
    		isSearchable = true;
    	}
    	
    	return isSearchable;
    }
   
    private static void loadProperties(List fileList) throws Exception
    {     
        if(properties.size() <1)
        {  String fileName="";
            for(int i=0; i<fileList.size(); i++)
            {
                try
                {
                    fileName = (String)fileList.get(i);
                    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));                    
                }catch(Exception ex)
                {
                    log.warn("Cannot locate the file: " + fileName);
                    continue;
                    //throw new Exception("Cannot locate file - "+ fileName);
                }
            }              
                
        }
        
        if(properties != null)
        {
            String value = null;
            for(Iterator i= properties.keySet().iterator(); i.hasNext();){
                String key = (String)i.next();    
                if(key.indexOf("nih.nci.evs.")>0){
                    if(!validateEVSClass(key)){
                        continue;
                    }
                }
                domainNames.add(key);                
                value = (String)properties.get(key);                
                packages.add(value);
                              
            }
        }
        
    }
    static List getFileList(String files){
        List fileList = new ArrayList();
        
        if(files.indexOf(SEMICOLON_SEPARATOR)>0){
            StringTokenizer st = new StringTokenizer(files, SEMICOLON_SEPARATOR);
            while(st.hasMoreTokens()){
                fileList.add((st.nextToken()).trim());
            }            
        }
        else if(files.indexOf(COMMA_SEPARATOR)>0){
            StringTokenizer st = new StringTokenizer(files, COMMA_SEPARATOR);
            while(st.hasMoreTokens()){
                fileList.add((st.nextToken()).trim());
            }
        }
        else{
            fileList.add(files.trim());
        }
        
        return fileList;
    }
    
   
@SuppressWarnings("unchecked")
public ArrayList getAssociations(String className) throws Exception{
       String qualifiedName = null;
       String packageName = null;
       if(className.indexOf(".")<1){
           for(Iterator i=properties.keySet().iterator(); i.hasNext();){
               String key = (String)i.next();
               if(key.endsWith(className)){
                   qualifiedName = key;
                   packageName = properties.getProperty(key);
                   break;
               }
           }
       }else{
           qualifiedName = className;
           packageName = className.substring(0, className.lastIndexOf("."));
       }
       Field[] fields = Class.forName(qualifiedName).getDeclaredFields();
       HashSet<java.lang.String> roleNames = new HashSet();
       roleNames.add(qualifiedName);
       for(int i =0; i< fields.length; i++){
           fields[i].setAccessible(true);
           Field field = fields[i];
           String type = field.getType().getName();
           String fieldName = field.getName();
           if(packageName.startsWith("gov.nih.nci.evs")){
               if(fieldName.toLowerCase().endsWith("edgeproperties") || fieldName.toLowerCase().endsWith("treenode")){
                   continue;
               }  
               if(className.endsWith("DescLogicConcept")){
                   roleNames.add("gov.nih.nci.evs.domain.HistoryRecord");
               }
               if(className.endsWith("Association") || className.endsWith("Property") ){
                   continue;
               }
           }
           if(!field.getType().isPrimitive()){
               if(fieldName.endsWith("Collection") || (type.startsWith("java") && type.endsWith("Collection"))){
                   String roleClassName = null;
                   String beanName = null;
                   SearchUtils searchUtils = new SearchUtils();
                   if(searchUtils.getTargetClassName(qualifiedName,fieldName)!=null){
                       roleClassName = searchUtils.getTargetClassName(qualifiedName,fieldName);                              
                   }else{
                       if(fieldName.endsWith("Collection")){
                           beanName = fieldName.substring(0, fieldName.lastIndexOf("Collection"));
                       }else{
                           beanName = fieldName;
                       }                   
                       roleClassName = locateClass(beanName, packageName);
                   }                                      
                   if(roleClassName != null){
                       roleNames.add(roleClassName);
                   }                   
               }else if(locateClass(type)!= null){
                   roleNames.add(type);                                    
               }
           }
       }
       if(!(Class.forName(qualifiedName).getSuperclass().getName().equalsIgnoreCase("java.lang.Object"))){
           String superClassName = Class.forName(qualifiedName).getSuperclass().getName();
           List associations = getAssociations(superClassName);
           for(int i=0; i<associations.size(); i++){
               if(!(superClassName.equals((String)associations.get(i)))){
                   roleNames.add((String)associations.get(i));
               }               
           }           
       }
       ArrayList<String>roles = new ArrayList();
       for(Iterator i = roleNames.iterator(); i.hasNext();){
           roles.add((String)i.next());
       } 
       Collections.sort(roles);
       return roles;
   }
private String locateClass(String className){
    String qualifiedClassName = null;   
    for(Iterator i = properties.keySet().iterator(); i.hasNext();){
        String key = (String)i.next();
        if(key.toLowerCase().equals(className.toLowerCase())){
            qualifiedClassName = key;
            break;
        }
    }      
    return qualifiedClassName;
}
private String locateClass(String beanName, String packageName){
   String className = packageName +"."+ beanName.substring(0).toUpperCase() + beanName.substring(1);
   return locateClass(className); 
}

   private static boolean validateEVSClass(String className){
       boolean valid = true;
       ArrayList<String>inValidClasses = new ArrayList();
       inValidClasses.add("TreeNode");
       inValidClasses.add("EdgeProperties");
       inValidClasses.add("SecurityToken");
       inValidClasses.add("AttributeSetDescriptor");
       inValidClasses.add("EditActionDate");
       inValidClasses.add("Definition");
       for(int i=0; i<inValidClasses.size(); i++){
           String name = (String)inValidClasses.get(i);
           if(className.endsWith(name)){
               valid = false;
           }
       }
       return valid;       
   }
   private static void loadIndexedFields() throws Exception{       
       Properties fieldNames = new Properties();
       fieldNames.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("indexedFields.properties"));
       for(Iterator i= fieldNames.keySet().iterator(); i.hasNext();){
           String className = (String)i.next();
           String fields = fieldNames.getProperty(className);
           StringTokenizer st = new StringTokenizer(fields,";");
           while(st.hasMoreTokens()){
               indexedFields.add(st.nextToken());
           }          
       }
   }
   public static Set getIndexedFields(){
       return indexedFields;
   }
   public String getKeyDescription(String keywords, String doc){
       String keyDescription = null;
       StringTokenizer st = new StringTokenizer(keywords, " ");
       Set keys = new HashSet();
       while(st.hasMoreTokens()){
           keys.add(st.nextToken());
       }
       for(StringTokenizer lines = new StringTokenizer(doc, ".");lines.hasMoreTokens();){
           String sentence = lines.nextToken();
           String start = "";   
           for(Iterator it = keys.iterator(); it.hasNext();){
               String key = (String)it.next();   
               if(sentence.indexOf(key)>-1){
                   //tokenize sentence
                   if(keyDescription == null){
                       keyDescription = sentence;
                   }
                   String newSentence = "";
                   for(StringTokenizer tokens = new StringTokenizer(keyDescription, " ");tokens.hasMoreTokens();){
                       String token = tokens.nextToken();
                       if(key.equalsIgnoreCase(token)){
                           newSentence += " <b> " + token +" </b> ";
                       }else{
                           newSentence += token +" ";
                       }
                   }
                   keyDescription = newSentence;
                   /**
                   if(keyDescription.indexOf(key)==0){
                       keyDescription = " <b> "+ key +" </b> " + keyDescription.substring(key.length());
                       
                   }else{
                       start = keyDescription.substring(0, keyDescription.indexOf(key));
                       String end = keyDescription.substring(start.length()+ key.length());
                       keyDescription = start +" <b> " + key + " </b> " + end;                                             
                   }  
                   */ 
               }
           }           
       }
       if(keyDescription != null){
           System.out.println(doc + " Key: "+ keyDescription);
       }
       
     return keyDescription;  
   }
    
}
