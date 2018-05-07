package com.ruiec.web.common;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 使用包装模式 增强ConvertUtilsBean可以处理枚举和枚举数组
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月28日
 */
public class EnumConvertUtilsBean extends ConvertUtilsBean {

	//对象转成String
	@SuppressWarnings("rawtypes")
	@Override
	public String convert(Object value) {
		if(value!=null){
			Class valueClazz = value.getClass();
			if(valueClazz.isEnum()){
				if(super.lookup(valueClazz)==null){
					super.register(new EnumConverter(valueClazz), valueClazz);
				}
				Converter converter = super.lookup(valueClazz);
				return (String) converter.convert(String.class, value);
			}else if(valueClazz.isArray()&&valueClazz.getComponentType().isEnum()){
				if(super.lookup(valueClazz)==null){
					ArrayConverter arrayConverter = new ArrayConverter(valueClazz, new EnumConverter(valueClazz.getComponentType()), 1);
					arrayConverter.setOnlyFirstToString(false);
					super.register(arrayConverter, valueClazz);//参考父类处理方式
				}
				return (String) super.lookup(valueClazz).convert(String.class, value);
			}
		}
		return super.convert(value);
	}
	//String转成对象
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object convert(String value, Class clazz) {
		if(clazz.isEnum()&&super.lookup(clazz)==null){
			super.register(new EnumConverter(clazz), clazz);//参考父类处理方式
		}
		return super.convert(value, clazz);
	}
	//String数组转成对象数组
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(String[] values, Class clazz) {
		if(clazz.isArray()&&clazz.getComponentType().isEnum()&&super.lookup(clazz)==null){
			super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());//父类使用循环单个转换，所以只注册单个的类型转换器
		}
		return super.convert(values, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Object value, Class targetType) {
		if(targetType.isEnum()&&super.lookup(targetType)==null){
			super.register(new EnumConverter(targetType), targetType);
		}else if(targetType.isArray()&&targetType.getComponentType().isEnum()&&super.lookup(targetType)==null){
			ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 1);
			arrayConverter.setOnlyFirstToString(false);
			super.register(arrayConverter, targetType);//参考父类处理方式
		}
		return super.convert(value, targetType);
	}
	
}
