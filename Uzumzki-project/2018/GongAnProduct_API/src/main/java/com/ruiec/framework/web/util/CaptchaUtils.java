package com.ruiec.framework.web.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class CaptchaUtils {
	
	private static CacheManager cacheManager = CacheManager.create();//使用单例模式 获取实例 默认读取类路径下的ehcache.xml
	
	public static Cache getCaptchaCache(){
		Cache localCache = cacheManager.getCache("captcha");
		return localCache;
	}
}
