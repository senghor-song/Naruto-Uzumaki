package com.ruiec.web.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 字段拷贝工具
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月23日
 */
abstract public class RuiecBeanUtils {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyProperties(Object source, Object dest) {
		Assert.notNull(source, "source must not be null");
		Assert.notNull(dest, "dest must not be null");
		PropertyDescriptor[] destPropertyDescriptors = BeanUtils.getPropertyDescriptors(dest.getClass());
		for (PropertyDescriptor destPropertyDescriptor : destPropertyDescriptors) {
			if ((destPropertyDescriptor.getWriteMethod() == null)){
				continue;
			}
			PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), destPropertyDescriptor.getName());
			if ((sourcePropertyDescriptor == null) || (sourcePropertyDescriptor.getReadMethod() == null)){
				continue;
			}
			try {
				Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
				if (!Modifier.isPublic(sourceMethod.getDeclaringClass().getModifiers())){
					sourceMethod.setAccessible(true);
				}
				Object sourceValue = sourceMethod.invoke(source, new Object[0]);
				Object destValue = sourceMethod.invoke(dest, new Object[0]);
				Object temp;
				if ((sourceValue != null) && (destValue != null) && ((destValue instanceof Collection))) {
					temp = destValue;
					((Collection) temp).clear();
					((Collection) temp).addAll((Collection) sourceValue);
				} else if((sourceValue != null) && (destValue != null) && ((destValue instanceof Map))){
					temp = destValue;
					((Map) temp).clear();
					((Map) temp).putAll((Map) sourceValue);
				} else {
					temp = destPropertyDescriptor.getWriteMethod();
					if (!Modifier.isPublic(((Method) temp).getDeclaringClass().getModifiers())){
						((Method) temp).setAccessible(true);
					}
					((Method) temp).invoke(dest, new Object[] { sourceValue });
				}
			} catch (Throwable localThrowable) {
				throw new FatalBeanException("Could not copy properties from source to dest", localThrowable);
			}
		}
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyPropertiesIgnore(Object source, Object dest, String[] ignoreProperties) {
		Assert.notNull(source, "source must not be null");
		Assert.notNull(dest, "dest must not be null");
		PropertyDescriptor[] destPropertyDescriptors = BeanUtils.getPropertyDescriptors(dest.getClass());
		List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
		for (PropertyDescriptor destPropertyDescriptor : destPropertyDescriptors) {
			if ((destPropertyDescriptor.getWriteMethod() == null) || ((ignoreList != null) && (ignoreList.contains(destPropertyDescriptor.getName())))){
				continue;
			}
			PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), destPropertyDescriptor.getName());
			if ((sourcePropertyDescriptor == null) || (sourcePropertyDescriptor.getReadMethod() == null)){
				continue;
			}
			try {
				Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
				if (!Modifier.isPublic(sourceMethod.getDeclaringClass().getModifiers())){
					sourceMethod.setAccessible(true);
				}
				Object sourceValue = sourceMethod.invoke(source, new Object[0]);
				Object destValue = sourceMethod.invoke(dest, new Object[0]);
				Object temp;
				if ((sourceValue != null) && (destValue != null) && ((destValue instanceof Collection))) {
					temp = destValue;
					((Collection) temp).clear();
					((Collection) temp).addAll((Collection) sourceValue);
				} else if((sourceValue != null) && (destValue != null) && ((destValue instanceof Map))){
					temp = destValue;
					((Map) temp).clear();
					((Map) temp).putAll((Map) sourceValue);
				} else {
					temp = destPropertyDescriptor.getWriteMethod();
					if (!Modifier.isPublic(((Method) temp).getDeclaringClass().getModifiers())){
						((Method) temp).setAccessible(true);
					}
					((Method) temp).invoke(dest, new Object[] { sourceValue });
				}
			} catch (Throwable localThrowable) {
				throw new FatalBeanException("Could not copy properties from source to dest", localThrowable);
			}
		}

	}
	
	/**
	 * 拷贝source中的指定属性到dest
	 * @param source 被复制对象
	 * @param dest 目标对象
	 * @param includeProperties 要拷贝的字段
	 * @author 肖学良<br>
	 * Date: 2015年3月30日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyPropertiesInclude(Object source, Object dest, String[] includeProperties){
		Assert.notNull(source, "source must not be null");
		Assert.notNull(dest, "dest must not be null");
		PropertyDescriptor[] destPropertyDescriptors = BeanUtils.getPropertyDescriptors(dest.getClass());
		List<String> includeList = includeProperties != null ? Arrays.asList(includeProperties) : null;
		for (PropertyDescriptor destPropertyDescriptor : destPropertyDescriptors) {
			if ((destPropertyDescriptor.getWriteMethod() == null) || ((includeList != null) && (!includeList.contains(destPropertyDescriptor.getName())))){
				continue;
			}
			PropertyDescriptor sourcePropertyDescriptor = BeanUtils.getPropertyDescriptor(source.getClass(), destPropertyDescriptor.getName());
			if ((sourcePropertyDescriptor == null) || (sourcePropertyDescriptor.getReadMethod() == null)){
				continue;
			}
			try {
				Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
				if (!Modifier.isPublic(sourceMethod.getDeclaringClass().getModifiers())){
					sourceMethod.setAccessible(true);
				}
				Object sourceValue = sourceMethod.invoke(source, new Object[0]);
				Object destValue = sourceMethod.invoke(dest, new Object[0]);
				Object temp;
				if ((sourceValue != null) && (destValue != null) && ((destValue instanceof Collection))){
					temp = destValue;
					((Collection) temp).clear();
					((Collection) temp).addAll((Collection) sourceValue);
				} else if((sourceValue != null) && (destValue != null) && ((destValue instanceof Map))){
					temp = destValue;
					((Map) temp).clear();
					((Map) temp).putAll((Map) sourceValue);
				} else {
					temp = destPropertyDescriptor.getWriteMethod();
					if (!Modifier.isPublic(((Method) temp).getDeclaringClass().getModifiers())){
						((Method) temp).setAccessible(true);
					}
					((Method) temp).invoke(dest, new Object[] { sourceValue });
				}
			} catch (Throwable localThrowable) {
				throw new FatalBeanException("Could not copy properties from source to dest", localThrowable);
			}
		}
		
	}
}
