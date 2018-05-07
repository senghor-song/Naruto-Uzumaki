package com.ruiec.web.validation;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 两个字段比比较大小验证器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年3月23日
 */
public class NumberGtValidator implements ConstraintValidator<NumberGt, Object> {

	private String theOne;
	private String theOther;
	
	@Override
	public void initialize(NumberGt numberGtAnnotation) {
		this.theOne = numberGtAnnotation.theOne();
		this.theOther = numberGtAnnotation.theOther();
		System.out.println();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String theOneValueStr = null;
		String theOtherValueStr = null;
		BigDecimal theOneValue = null;
		BigDecimal theOtherValue = null;
		try {
			theOneValueStr = BeanUtils.getProperty(value, theOne);
			theOtherValueStr = BeanUtils.getProperty(value, theOther);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(theOneValueStr) || StringUtils.isBlank(theOtherValueStr)){
			String defaultMessage = context.getDefaultConstraintMessageTemplate();
			if("".equals(defaultMessage)){
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(" field value must not be null").addConstraintViolation();
			}
			return false;
		}
		theOneValue = new BigDecimal(theOneValueStr);
		theOtherValue = new BigDecimal(theOtherValueStr);
		if(theOneValue.compareTo(theOtherValue) <= 0){
			String defaultMessage = context.getDefaultConstraintMessageTemplate();
			if("".equals(defaultMessage)){
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(" must  greater than " + theOther).addNode(theOne).addConstraintViolation();
			}
			return false;
		}
		return true;
	}

}
