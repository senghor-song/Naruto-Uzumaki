package com.ruiec.web.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;

import com.ruiec.server.common.entity.SysConfig;
import com.ruiec.server.common.service.SysConfigService;
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.common.EnumConvertUtilsBean;
import com.ruiec.web.common.Setting;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 系统配置参数工具类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月21日
 */
public abstract class SettingUtils {
	
	private static CacheManager cacheManager = CacheManager.create();//使用单例模式 获取实例 默认读取类路径下的ehcache.xml
	private static BeanUtilsBean beanUtilsBean;
	
	static{
		EnumConvertUtilsBean enumConvertUtilsBean = new EnumConvertUtilsBean();//管理转换器的注册和删除
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(CommonParam.DATA_PATTERNS);//使转换器能处理不同的时间格式
		enumConvertUtilsBean.register(dateConverter, Date.class);
		beanUtilsBean = new BeanUtilsBean(enumConvertUtilsBean);//包装模式
	}
	
	public static Setting get(){
		 Cache localCache = cacheManager.getCache("setting");
		 Element settingElement = localCache.get(Setting.KEY);
		 Setting localSetting = null;
		 
		 if(settingElement!=null){
			 localSetting = (Setting)settingElement.getObjectValue();
		 }else{
			 	localSetting = new Setting();
			try {
				SysConfigService localSysConfigService = SpringUtils.getBean("sysConfigServiceImpl", SysConfigService.class);
				List<SysConfig> list = localSysConfigService.getAll();
				Iterator<SysConfig> iterator = list.iterator();
				while(iterator.hasNext()){
					SysConfig localSysConfig = iterator.next();
					String name = localSysConfig.getName();
					String value = localSysConfig.getValue();
					beanUtilsBean.setProperty(localSetting, name, value);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}finally{
			}
			localCache.put(new Element(Setting.KEY,localSetting));
		 }
		return localSetting;
	}
	
	public static void set(Setting setting){
		SysConfigService localSysConfigService = SpringUtils.getBean("sysConfigServiceImpl", SysConfigService.class);
		List<SysConfig> list = localSysConfigService.getAll();
		try {
			Iterator<SysConfig> iterator = list.iterator();
			while(iterator.hasNext()){
				SysConfig localSysConfig = iterator.next();
				String name = localSysConfig.getName();
				if("resourceType".equals(name)){
					System.out.println("==========");
				}
				String value = beanUtilsBean.getProperty(setting, name);
				localSysConfig.setValue(value);
				beanUtilsBean.setProperty(setting, name, value);
				//localSysConfigService.updateIgnore(localSysConfig, new String[]{}, null);
			}
			localSysConfigService.update(list);
			Cache localCache = cacheManager.getCache("setting");
			localCache.put(new Element(Setting.KEY,setting));
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
		}
		
	}
}
