package com.ruiec.web.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 倍率验证器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年3月21日
 */
public class TimesValidator implements ConstraintValidator<Times, Object> {

	private Integer basic;
	
	@Override
	public void initialize(Times timesAnnotation) {
		basic = timesAnnotation.basic();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		if(value == null){
			return true;
		}
		
		BigDecimal param = new BigDecimal(value.toString());
		BigDecimal[] result = param.divideAndRemainder(new BigDecimal(basic));
		if(result[1].compareTo(new BigDecimal(0)) == 0){
			return true;
		}
		String defaultMessage = context.getDefaultConstraintMessageTemplate();
		if("".equals(defaultMessage)){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(" must be a multiple of " + basic).addConstraintViolation();
		}
		return false;
	}

}
