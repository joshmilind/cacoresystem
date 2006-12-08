package gov.nih.nci.system.dao.properties;
import gov.nih.nci.dtsrpc.client.DTSRPCClient;
import gov.nih.nci.system.dao.impl.externalsystem.EVSLexBigDAOImpl;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import COM.Lexical.Metaphrase.*;
/**
 * @author Shaziya Muhsin
 * Nov 3, 2006
 */
/**
 * Loads EVS Properties
 */
public class EVSProperties {
    private static Logger log = Logger.getLogger(EVSProperties.class.getName());
	private static EVSProperties evsProperties;
	private static String metaServer = null;
	private static String database = null;
	private static String userName = null;
	private static String password = null;
	private static String dtsServer = null;
	private static String port = null;
    private static String lexBigConfigFileLocation = null;
	
	private EVSProperties(){}
	
	public static EVSProperties getInstance(Hashtable config) throws Exception{
		if(evsProperties == null){
			synchronized(EVSProperties.class){
				if(evsProperties == null){
					evsProperties = new EVSProperties();
					loadProperties(config);
				}
			}			
		}
		return evsProperties;
		}
	
	public Metaphrase getMetaphrase() throws Exception{
		Metaphrase metaphrase = null;
		try{
			metaphrase = new RMIMetaphrase("//" + metaServer + "/RemoteMetaphrase", database, userName, password);
		}catch(Exception ex){
            log.error("Unable to connect to server: "+ metaServer +"\tdatabase: "+ database +"\tUser: "+ userName +"\tPassword: "+ password);
			throw new Exception("Unable to connecto to Metaphrase Server "+ ex.getMessage());
		}
		return metaphrase;
		}
	
	public DTSRPCClient getDtsrpcConnection() throws Exception{
		DTSRPCClient dtsrpc = null;
		if(dtsServer != null && port != null){
			dtsrpc = new DTSRPCClient(dtsServer, port);
		}
		if(dtsrpc == null){
			throw new Exception("Unable to connect to the DTSRPC Server: "+ dtsServer);
		}
		return dtsrpc ;
		}
	
    public String getConfigFileLocation() throws Exception{
        return lexBigConfigFileLocation;
    }
	private static void loadProperties(Hashtable configs) throws Exception{
		String propertyFile = System.getProperty("gov.nih.nci.cacore.evsProperties");
		Properties properties = new Properties();
		//System.out.println("PROPERTY FILE LOCATION: "+ propertyFile);
		try{
			if(propertyFile != null && propertyFile.length() > 0){
				FileInputStream fis = new FileInputStream(new File(propertyFile));
				properties.load(fis);				
			}
		}catch(Exception ex){		
		}
		for(Iterator i = properties.keySet().iterator(); i.hasNext();){
			String key = (String)i.next();
			String value  = properties.getProperty(key);
			//System.out.println("KEY: "+ key +"\t - "+value);
		}
		try{
			dtsServer = properties.getProperty("EVS_DTSRCP_SERVER") == null ? (String) configs.get("dtsrpcServer"): properties.getProperty("EVS_DTSRCP_SERVER");
			port = properties.getProperty("EVS_DTSRCP_PORT") == null ? (String) configs.get("port"): properties.getProperty("EVS_DTSRCP_PORT");
			metaServer = properties.getProperty("EVS_META_SERVER") == null ? (String) configs.get("metaphraseServer"): properties.getProperty("EVS_META_SERVER");
			database = properties.getProperty("EVS_META_DB") == null ? (String) configs.get("database"): properties.getProperty("EVS_META_DB");
			userName = properties.getProperty("EVS_META_USERNAME") == null ? (String) configs.get("username"): properties.getProperty("EVS_META_USERNAME");
			password = properties.getProperty("EVS_META_PASSWD") == null ? (String) configs.get("password"): properties.getProperty("EVS_META_PASSWD");
            lexBigConfigFileLocation = properties.getProperty("LG_CONFIG_FILE") == null ? (String) configs.get("lexGridConfigFile"): properties.getProperty("LG_CONFIG_FILE");
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}	
		
	} 

}
