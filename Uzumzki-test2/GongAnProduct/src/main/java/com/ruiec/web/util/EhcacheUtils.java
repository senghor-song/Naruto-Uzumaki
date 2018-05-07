package com.ruiec.web.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

public class EhcacheUtils {
	private static final Logger LOGGER = Logger.getLogger(EhcacheUtils.class);
	private static CacheManager cacheManager = CacheManager.create();//使用单例模式 获取实例 默认读取类路径下的ehcache.xml
	
	
	public static void put(String cacheName, Object key , Object value){
		 Cache localCache = cacheManager.getCache(cacheName);
		 localCache.put(new Element(key, value));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName, Object key, Class<T> clazz){
		Cache localCache = cacheManager.getCache(cacheName);
		if (localCache == null) {
			LOGGER.error("cacheName " + cacheName + "may not be configed in ehcache.xml");
		}
		Element element = localCache.get(key);
		if(element ==null){
			return null;
		}
		T object = (T) element.getObjectValue();
		return object;
	}
	
	public static void remove(String cacheName, Object key){
		Cache localCache = cacheManager.getCache(cacheName);
		if (localCache == null) {
			LOGGER.error("cacheName " + cacheName + "may not be configed in ehcache.xml");
			return;
		}
		localCache.remove(key);
	}
	
	public static void clear(String cacheName){
		Cache localCache = cacheManager.getCache(cacheName);
		localCache.removeAll();
	}
	
	public static Ehcache getEhcache(String cacheName){
		return cacheManager.getEhcache(cacheName);
	}
}
