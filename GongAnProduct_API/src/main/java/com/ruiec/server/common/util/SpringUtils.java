package com.ruiec.server.common.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * spring获取bean工具
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月24日
 */
@Component("springUtils_common")
@Lazy(false)
public final class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static ApplicationContext getContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		return applicationContext.getBean(name, clazz);
	}

	public static <T> Map<String, T> getBean(Class<T> clazz) {
		return applicationContext.getBeansOfType(clazz);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}

}
