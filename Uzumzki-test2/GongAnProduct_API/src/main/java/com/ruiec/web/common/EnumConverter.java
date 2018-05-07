package com.ruiec.web.common;

import org.apache.commons.beanutils.converters.AbstractConverter;

public class EnumConverter extends AbstractConverter {

	private Class<?> valueClazz;

	public EnumConverter(Class<?> valueClazz) {
		super();
		this.valueClazz = valueClazz;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Object convertToType(Class clazz, Object value) throws Throwable {
		String localString = value.toString().trim();
		return Enum.valueOf(clazz, localString);
	}

	@Override
	protected String convertToString(Object value) throws Throwable {
		return value.toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getDefaultType() {
		return valueClazz;
	}
}
