/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.query;

import java.io.Serializable;

/**
 * 过滤封装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月11日
 */
public class Filter implements Serializable {

	private static final long serialVersionUID = 305554619040274295L;
	
	private String property;// 字段名 example: (price lt 100)
	private String propertyAlias;//字段别名，用于避免同一个字段多次用于过滤时（如：name = :name)赋值覆盖
	private FilterType filterType;// 过滤类型
	private Object value;// 过滤类型的取值可为字符串和对象类型
	private Boolean ignoreCase = Boolean.valueOf(false);// 不区分大小写

	public Filter() {
	}

	public Filter(String property, String propertyAlias, FilterType filterType, Object value) {
		this.property = property;
		this.propertyAlias = propertyAlias;
		this.value = value;
		this.filterType = filterType;
	}
	
	public Filter(String property, FilterType filterType, Object value) {
		this.property = property;
		this.value = value;
		this.filterType = filterType;
	}

	public Filter(String property, FilterType filterType, Object value,
			boolean ignoreCase) {
		this.property = property;
		this.filterType = filterType;
		this.value = value;
		this.ignoreCase = ignoreCase;
	}
	// ===================================枚举类
	//等于，不等于，大于，小于，大于等于，小于等于，模糊，多匹配，是否为空，是否不为空
		public enum FilterType {
			eq, ne, gt, lt, ge, le, like, in, isNull, isNotNull;
		}

	/**
	 * 获取过滤组装类
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public static FilterBuilder build(){
		return new FilterBuilder();
	}
		
	// ===================================快速获取Filter
	public static Filter eq(String property, Object value) {
		return new Filter(property, FilterType.eq, value);
	}
	
	public static Filter eq(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.eq, value);
	}

	public static Filter eq(String property, Object value, boolean ignoreCase) {
		return new Filter(property, FilterType.eq, value, ignoreCase);
	}

	public static Filter ne(String property, Object value) {
		return new Filter(property, FilterType.ne, value);
	}
	
	public static Filter ne(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.ne, value);
	}

	public static Filter ne(String property, Object value, boolean ignoreCase) {
		return new Filter(property, FilterType.ne, value, ignoreCase);
	}

	public static Filter gt(String property, Object value) {
		return new Filter(property, FilterType.gt, value);
	}
	
	public static Filter gt(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.gt, value);
	}

	public static Filter lt(String property, Object value) {
		return new Filter(property, FilterType.lt, value);
	}
	
	public static Filter lt(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.lt, value);
	}

	public static Filter ge(String property, Object value) {
		return new Filter(property, FilterType.ge, value);
	}
	public static Filter ge(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.ge, value);
	}

	public static Filter le(String property, Object value) {
		return new Filter(property, FilterType.le, value);
	}
	public static Filter le(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.le, value);
	}

	public static Filter like(String property, Object value) {
		return new Filter(property, FilterType.like, value);
	}
	public static Filter like(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.like, value);
	}

	public static Filter in(String property, Object value) {
		return new Filter(property, FilterType.in, value);
	}
	public static Filter in(String property, String propertyAlias, Object value) {
		return new Filter(property, propertyAlias, FilterType.in, value);
	}

	public static Filter isNull(String property) {
		return new Filter(property, FilterType.isNull, "");
	}
	public static Filter isNull(String property, String propertyAlias) {
		return new Filter(property, propertyAlias, FilterType.isNull, "");
	}

	public static Filter isNotNull(String property) {
		return new Filter(property, FilterType.isNotNull, null);
	}
	public static Filter isNotNull(String property, String propertyAlias) {
		return new Filter(property, propertyAlias, FilterType.isNotNull, null);
	}

	public Filter ignoreCase() {
		ignoreCase = Boolean.valueOf(true);
		return this;
	}

	// ===================================
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	/**
	 * 别名
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public String getPropertyAlias() {
		return propertyAlias;
	}

	/**
	 * 别名
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public void setPropertyAlias(String propertyAlias) {
		this.propertyAlias = propertyAlias;
	}

	public FilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(FilterType filterType) {
		this.filterType = filterType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filterType == null) ? 0 : filterType.hashCode());
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		if (filterType != other.filterType)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
