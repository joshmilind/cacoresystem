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
	private static EVSCacheManager evsCache;
	private static CacheManager evsManager;

	/**
	 * Constructor that instantiates an EVSCacheManager
	 */
	private EVSCacheManager()throws CacheException{
		try{
			evsManager = CacheManager.create(Thread.currentThread().getContextClassLoader().getResourceAsStream("ehcache_evs.xml"));
		}catch(Exception ex){
				log.error("Unable to read file : " + ex.getMessage());
				throw new CacheException(ex.getMessage());
		}
	}
	/**
	 * Returns an EVSCacheManager instance
	 * @return
	 * @throws Exception
	 */
	public static EVSCacheManager getInstance() throws CacheException{
		if(evsCache == null){
			synchronized(EVSCacheManager.class){
//				Double check lock
				if(evsCache==null)
					evsCache = new EVSCacheManager();
			}
		}
		return evsCache;
	}
	/**
	 * Returns the specified cache name
	 * @param name
	 * @return
	 */
	public String getCacheName (String name){
		String cacheName = null;
		if(evsManager.getCache(name)!=null){
			cacheName = evsManager.getCache(name).getName();;
		}
		return cacheName;
	}
	/**
	 * Returns true if the specified cache name was found
	 * @param name
	 * @return
	 */
	public boolean isCacheNameValid(String name){
		boolean valid = false;
		if(evsManager.getCache(name)!=null){
			valid = true;
		}
		return valid;
	}
	/**
	 * Returns true if the specified key was found in the cache
	 * @param name
	 * @return
	 */
	public boolean isKeyValid(String cacheStoreName, String key) throws CacheException{
		boolean valid = false;
		if(isCacheNameValid(cacheStoreName)){
			Cache cache = evsManager.getCache(cacheStoreName);
			if(cache.get(key) != null){
				valid = true;
			}else{
				throw new CacheException("Key: "+ key +" not found in "+ cacheStoreName);
			}
		}
		return valid;
	}
	/**
	 * Stores the specified values in the cache
	 * @param key
	 * @param values
	 * @param cacheStoreName
	 */
	public void put(String key, Object values, String cacheStoreName) throws CacheException{
		log.debug("Upload data to CACHE ");
		if(isCacheNameValid(cacheStoreName)){
			evsManager.getCache(cacheStoreName).put(new Element(key, values));
		}else{
			throw new CacheException("Invalid Cache "+ cacheStoreName);
		}

	}
	/**
	 * Returns the value from the cache for a given key
	 * @param key
	 * @param cacheStoreName
	 * @return
	 * @throws Exception
	 */
	public Object get(String key, String cacheStoreName) throws CacheException{
		Object value = null;
		try{
			if(isCacheNameValid(cacheStoreName)){
				Cache cache = evsManager.getCache(cacheStoreName);
				if(cache.get(key) != null){
					value = cache.get(key).getValue();
					log.debug("Retrieve data from CACHE");
				}
			}
		}catch(Exception ex){
			throw new CacheException(ex.getMessage());
		}
		return value;
	}

}
