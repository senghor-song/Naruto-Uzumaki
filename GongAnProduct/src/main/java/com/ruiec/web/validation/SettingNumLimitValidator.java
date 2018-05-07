package com.ruiec.web.validation;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.ruiec.web.common.Setting;
import com.ruiec.web.util.SettingUtils;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 系统参数数字范围验证器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年3月25日
 */
public class SettingNumLimitValidator implements ConstraintValidator<SettingNumLimit, Object> {

	private String min;
	private String max;
	
	@Override
	public void initialize(SettingNumLimit annotation) {
		min = annotation.min();
		max = annotation.max();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null){
			return true;
		}
		
		if("".equals(min) && "".equals(max)){
			return true;
		}
		String settingMinValueStr = null;
		String settingMaxValueStr = null;
		Setting setting = SettingUtils.get();
		try {
			if(!"".equals(min)){
				settingMinValueStr = BeanUtils.getProperty(setting, min);
				
			}
			if(!"".equals(max)){
				settingMaxValueStr = BeanUtils.getProperty(setting, max);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		String defaultMessage = context.getDefaultConstraintMessageTemplate();
		BigDecimal settingMinValue = null;
		BigDecimal settingMaxValue = null;
		BigDecimal param = new BigDecimal(value.toString());
		if(settingMinValueStr != null){
			settingMinValue = new BigDecimal(settingMinValueStr);
		}
		if(settingMaxValueStr != null){
			settingMaxValue = new BigDecimal(settingMaxValueStr);
		}
		
		if(settingMinValue != null && settingMaxValue == null){
			if (param.compareTo(settingMinValue) >= 0) {
				return true;
			}else{
				if("".equals(defaultMessage)){
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(" min value is " + settingMinValue.toString()).addConstraintViolation();
				}
				return false;
			}
		}
		
		if(settingMinValue == null && settingMaxValue != null){
			if (param.compareTo(settingMaxValue) <= 0) {
				return true;
			}else{
				if("".equals(defaultMessage)){
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(" max value is " + settingMaxValue.toString()).addConstraintViolation();
				}
				return false;
			}
		}
		
		if(param.compareTo(settingMinValue) >= 0 && param.compareTo(settingMaxValue) <= 0){
			return true;
		}else{
			if("".equals(defaultMessage)){
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(" the range is " + settingMinValue.toString() + " to " + settingMaxValueStr).addConstraintViolation();
			}
			return false;
		}
	}
}
