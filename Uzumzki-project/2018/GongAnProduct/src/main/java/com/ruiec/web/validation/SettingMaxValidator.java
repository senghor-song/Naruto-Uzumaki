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
 * 系统参数最小值比较器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年3月25日
 */
public class SettingMaxValidator implements ConstraintValidator<SettingMin, Object> {

	private String fieldName;
	
	@Override
	public void initialize(SettingMin settingMinAnnotation) {
		fieldName = settingMinAnnotation.fieldName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		String settingValueStr = null;
		Setting setting = SettingUtils.get();
		try {
			settingValueStr = BeanUtils.getProperty(setting, fieldName);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		BigDecimal settingValue = new BigDecimal(settingValueStr);
		if(value != null){
			BigDecimal param = new BigDecimal(value.toString());
			if(param.compareTo(settingValue) >= 0){
				return true;
			}
		}
		
		String defaultMessage = context.getDefaultConstraintMessageTemplate();
		if("".equals(defaultMessage)){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(" min value is " + settingValue.toString()).addConstraintViolation();
		}
		return false;
	}

}
