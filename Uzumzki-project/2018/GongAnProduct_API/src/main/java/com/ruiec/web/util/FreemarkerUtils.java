package com.ruiec.web.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ruiec.web.common.CommonParam;
import com.ruiec.web.common.EnumConvertUtilsBean;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;

public abstract class FreemarkerUtils {
	
	private static final ConvertUtilsBean convertUtilsBean;
	
	static{
		convertUtilsBean = new EnumConvertUtilsBean();
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(CommonParam.DATA_PATTERNS);
		convertUtilsBean.register(dateConverter, Date.class);
	}
	
	 public static String process(String template, Map<String, ?> model)
	  {
	    Configuration localConfiguration = null;
	    ApplicationContext localApplicationContext = SpringUtils.getContext();
	    if (localApplicationContext != null)
	    {
	      FreeMarkerConfigurer localFreeMarkerConfigurer = (FreeMarkerConfigurer)SpringUtils.getBean("freemarkerConfig", FreeMarkerConfigurer.class);
	      if (localFreeMarkerConfigurer != null)
	        localConfiguration = localFreeMarkerConfigurer.getConfiguration();
	    }
	    return process(template, model, localConfiguration);
	  }
	
	public static String process(String template, Map<String, ?> model, Configuration configuration)
	  {
	    if (template == null)
	      return null;
	    if (configuration == null)
	      configuration = new Configuration();
	    StringWriter localStringWriter = new StringWriter();
	    try
	    {
	      new Template("template", new StringReader(template), configuration).process(model, localStringWriter);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return localStringWriter.toString();
	  }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getParam(String key, Map params, Class<T> clazz) {
		Object o = null;
		try {
			TemplateModel tempModel = (TemplateModel) params.get(key);
			if (tempModel == null) {
				return null;
			}
			o = DeepUnwrap.unwrap(tempModel);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		if (o == null) {
			return null;
		}
		return (T) convertUtilsBean.convert(o, clazz);

	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(String key, Environment env, Class<T> clazz){
		T object = null;
		TemplateModel localTemplateModel = getVariable(key, env);
		if(localTemplateModel!=null){
			try {
				object = (T) DeepUnwrap.unwrap(localTemplateModel);
			} catch (TemplateModelException e) {
				e.printStackTrace();
			}
		}
		return object;
	}
	
	public static TemplateModel getVariable(String key, Environment env){
		TemplateModel localTemplateModel = null;
		try {
			localTemplateModel = env.getVariable(key);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		return localTemplateModel;
	}
	public static void setVariable(String key, Object value, Environment env) {
		try {
			if (value instanceof TemplateModel) {
				env.setVariable(key, (TemplateModel) value);
			} else {
				env.setVariable(key, ObjectWrapper.BEANS_WRAPPER.wrap(value));
			}
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
	}
	
}
