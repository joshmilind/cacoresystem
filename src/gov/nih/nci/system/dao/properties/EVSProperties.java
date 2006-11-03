package gov.nih.nci.system.dao.properties;
import gov.nih.nci.dtsrpc.client.DTSRPCClient;

import java.io.*;
import java.util.*;

import COM.Lexical.Metaphrase.*;
/**
 * @author Shaziya Muhsin
 * Nov 3, 2006
 */
/**
 * Loads EVS Properties
 */
public class EVSProperties {
	private static EVSProperties evsProperties;
	private static String metaServer = null;
	private static String database = null;
	private static String userName = null;
	private static String password = null;
	private static String dtsServer = null;
	private static String port = null;
	
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
			throw new Exception("Unable to connect to the DTSRPC Server");
		}
		return dtsrpc ;
		}
	
	private static void loadProperties(Hashtable configs) throws Exception{
		String propertyFile = System.getProperty("gov.nih.nci.cacore.evsProperties");
		Properties properties = new Properties();
		System.out.println("PROPERTY FILE LOCATION: "+ propertyFile);
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
			System.out.println("KEY: "+ key +"\t - "+value);
		}
		try{
			dtsServer = properties.getProperty("EVS_DTSRCP_SERVER") == null ? (String) configs.get("dtsrpcServer"): System.getProperty("EVS_DTSRCP_SERVER");
			port = properties.getProperty("EVS_DTSRCP_PORT") == null ? (String) configs.get("port"): System.getProperty("EVS_DTSRCP_PORT");
			metaServer = properties.getProperty("EVS_META_SERVER") == null ? (String) configs.get("metaphraseServer"): System.getProperty("EVS_META_SERVER");
			database = properties.getProperty("EVS_META_DB") == null ? (String) configs.get("database"): System.getProperty("EVS_META_DB");
			userName = properties.getProperty("EVS_META_USERNAME") == null ? (String) configs.get("username"): System.getProperty("EVS_META_USERNAME");
			password = properties.getProperty("EVS_META_PASSWD") == null ? (String) configs.get("password"): System.getProperty("EVS_META_PASSWD");		
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}	
		
	} 

}
