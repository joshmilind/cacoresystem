package gov.nih.nci.system.dao.cache;

import java.io.*;
import net.sf.ehcache.*;

import org.apache.log4j.*;

/** 
 * @author Shaziya Muhsin
 * Oct 27, 2006
 */
/**
 * The EVSCacheManger is a singleton that looks for a resources named ehcache_evs.xml. 
 * The ehcache_evs.xml provideds information regarding the data that need to be cached. 
 */

public class EVSCacheManager {
	private static Logger log = Logger.getLogger(EVSCacheManager.class.getName());	
	private static EVSCacheManager evsCache = new EVSCacheManager();//Make it thread safe
	private static CacheManager evsManager;
	
	/**
	 * Constructor that instantiates an EVSCacheManager
	 */
	private EVSCacheManager() {
		try{			
			evsManager = CacheManager.create(Thread.currentThread().getContextClassLoader().getResourceAsStream("ehcache_evs.xml"));			
		}catch(Exception ex){
				log.error("Unable to read file : " + ex.getMessage());
		}			
	}	
	/**
	 * Returns an EVSCacheManager instance
	 * @return
	 * @throws Exception
	 */
	public static EVSCacheManager getInstance() throws Exception{		
		if(evsCache == null){
			evsCache = new EVSCacheManager();
		}		
		return evsCache;
	}
	/**
	 * Returns the specified cache name
	 * @param name
	 * @return
	 */
	public String getCacheName(String name){
		String cacheName = null;		
		if(evsManager.getCache(name)!=null){
			cacheName = evsManager.getCache(name).getName();;
		}
		return cacheName;
	}	
	/**
	 * Stores the specified values in the cache
	 * @param key
	 * @param values
	 * @param cacheStoreName
	 */
	public void put(String key, Object values, String cacheStoreName){		
		log.info("Upload data to CACHE ");
		evsManager.getCache(cacheStoreName).put(new Element(key, values));
	}		
	/**
	 * Returns the value from the cache for a given key
	 * @param key
	 * @param cacheStoreName
	 * @return
	 * @throws Exception
	 */
	public Object get(String key, String cacheStoreName) throws Exception{
		Object value = null;
		Cache tmpCache = evsManager.getCache(cacheStoreName);
		if(tmpCache.get(key)!=null){
			value = tmpCache.get(key).getValue();
			log.debug("Retrieve data from CACHE");
		}		
		return value;
	}

}
