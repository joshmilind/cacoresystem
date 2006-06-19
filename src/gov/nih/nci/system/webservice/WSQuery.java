package gov.nih.nci.system.webservice;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.util.*;
import java.lang.reflect.*;

import org.apache.log4j.*;

/**
 * @author caBIO Team
 */

/**
 * The WSQuery class describes operations that can be used to perform Web Service queries
 */
public class WSQuery {
    private static Logger log = Logger.getLogger(WSQuery.class);
    private boolean wsPackage = true;
    private String fileName = "CORESystem.properties";
    private int maximumRecordsPerQuery = 1000;
    private int recordsPerQuery = 1000;
    private String version = "3.2";
    private boolean processOntology = true;
    private String beanFileName = "cacoreBeans.properties";
    private WSTransformer transformer = null;

    /**
     * Instantiates a WSQuery instance
     */
    public WSQuery() throws Exception{
        loadProperties();
        loadWSTransformer();
    }

   /**
    * Returns the Version number
    */
    public String getVersion(){
        return version;
    }
    /**
     * Returns the number of records per query 
     */
    public int getRecordsPerQuery(){
        return recordsPerQuery;
    }
    /**
     * Returns the maximum number of records per query 
     */
    public int getMaximumRecordsPerQuery(){
        return maximumRecordsPerQuery;
    }
    /**
     * Returns true if <i>Process caCORE Ontology</i> value has been set to true 
     */
    public boolean getProcessOntology(){
        return processOntology;
    }
    /**
     * Returns the total number of records in the database for the specified query
     * @param targetClassName - The target class name can be a fully qualified class name or 
     * a navigation path (A comma seperated list of fully qualified class names)
     * @param criteria - Search Criteria object
     * @return 
     */
    public int getTotalNumberOfRecords(String targetClassName, Object criteria) throws Exception{
        List results = new ArrayList();
        if(criteria.getClass().getPackage().getName().indexOf(".nci.evs.")>0){
            results = query(targetClassName, criteria, 0, 0);
        }
        else{
            results = getResultSet(targetClassName,criteria);
        }
        return results.size();
    }
    /**
     * Returns a List of target objects beased on the search criteria 
     * @param targetClassName The target class name can be a fully qualified class name of 
     * a navigation path (a comma seperated list of fully qualified class names)
     * @param criteria Specifies the search critera
     */
     public List queryObject(String targetClassName, Object criteria) throws Exception
     {
         return query(targetClassName,criteria,0,0);
     }


     /**
      * Returns a List of target objects based on the search criteria
      * @param targetClassName The target class name can be a fully qualified class name of 
      * a navigation path (a comma seperated list of fully qualified class names)
      * @param Specifies the search criteria 
      * @param specifies the start index of the result set
      * @param specifies the total number of records that need to be returned 
      */
      public List query(String targetClassName, Object criteria, int startIndex, int recordCounter) throws Exception
      {
          List alteredResults = new ArrayList();
          List results = new ArrayList();
          if(criteria.getClass().getPackage().getName().indexOf(".nci.evs.")>0){
              EVSWebService service = new EVSWebService();
              alteredResults = service.evsSearch(targetClassName, criteria, startIndex, recordCounter);
          }
          else{
              results = getResultSet(targetClassName, criteria);
              List resultList = new ArrayList();

              if(results.size()>= startIndex){
                  if(recordCounter <=0 || recordCounter > (startIndex + recordsPerQuery) ){
                      recordCounter = startIndex + recordsPerQuery;
                  }
                for(int i= startIndex;( i<=(recordCounter + startIndex) && i<results.size()); i++){
                    resultList.add(results.get(i));
                  }
              }
              if(resultList.size()>0){
                  alteredResults = transformer.generateWSResults(resultList);
                  }


          }
        return alteredResults;
      }

      /**
       * Queries the database and returns a List of objects specified in the target class
       * @param targetClassName The target class name can be a fully qualified class name of 
       * a navigation path (a comma seperated list of fully qualified class names)
       * @param Specifies the search criteria      
       * @return returns a list of objects
       */
      private List getResultSet(String targetClassName, Object criteria) throws Exception{

          List results = new ArrayList();

          String searchClassName = transformer.getSearchClassName(targetClassName);
          Object searchCriteria =  transformer.getSearchCriteria(criteria);

          try
          {
              if(searchClassName != null && searchCriteria != null){
                  ApplicationService app = ApplicationServiceProvider.getLocalInstance();
                  results = app.search(searchClassName, searchCriteria);
              }
              else{
                  throw new Exception("Invalid arguments passed over to the server");
              }

          }
          catch(Exception e)
          {
              log.error("WSQuery caught an exception: "+ e.getMessage());
              e.printStackTrace();
              throw new Exception(e.getMessage());
          }
          return results;
      }

      /**
       * Loads the Properties file
       */
      private  void loadProperties() throws Exception{
          try{
              java.util.Properties properties = new java.util.Properties();
              properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
              for(Iterator i= properties.keySet().iterator(); i.hasNext();){
                  String key = (String)i.next();
                  String value = (String)properties.get(key);
                  if(key.equalsIgnoreCase("RECORDSPERQUERY")){
                      try{
                          if(value.length()>0 && value != null){
                              recordsPerQuery = Integer.parseInt(value);
                          }
                      }catch(Exception ex){
                          throw new Exception(ex.getMessage());
                      }
                  }
                  else if(key.equalsIgnoreCase("MAXRECORDSPERQUERY")){
                      try{
                          if(value.length()>0 && value != null){
                              maximumRecordsPerQuery = Integer.parseInt(value);
                          }
                      }catch(Exception ex){
                          throw new Exception(ex.getMessage());
                      }
                  }
                  else if(key.equalsIgnoreCase("VERSION")){
                      version = value;
                  }
                  else if(key.equalsIgnoreCase("CACORE_ONTOLOGY")){
                      if(value.equalsIgnoreCase("TRUE")|| value.equalsIgnoreCase("false")){
                          processOntology = Boolean.valueOf(value).booleanValue();
                      }

                  }
                  else if(key.equalsIgnoreCase("PROPERTY_FILE")){
                      beanFileName = value;
                  }

              }
          }catch(Exception ex){
              log.error("Error: Unable to read file: "+ fileName);
              throw new Exception("Error: Unable to read file: "+ fileName);
          }
      }
      
      /**
       * Loads the Web Service Transformer
       */
      private void loadWSTransformer() throws Exception{
          try{
              transformer = new WSTransformer(beanFileName);
              transformer.setProcessOntology(processOntology);
          }catch(Exception ex){
              throw new Exception(ex.getMessage());
          }

      }

}
