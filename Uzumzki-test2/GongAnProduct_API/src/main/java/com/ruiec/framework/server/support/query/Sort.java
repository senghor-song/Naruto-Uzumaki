/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.query;

import java.io.Serializable;
/**
 * 排序封装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月11日
 */
public class Sort implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String property;//排序字段
	private SortType sortType;
	
	
	public Sort(String property, SortType sortType) {
		this.property = property;
		this.sortType = sortType;
	}

	public enum SortType{
		asc,desc;
	}
	
	/**
	 * 获取排序组装类
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public static SortBuilder build(){
		return new SortBuilder();
	}
	
	public static Sort asc(String property){
		return new Sort(property, SortType.asc);
	}
	public static Sort desc(String property){
		return new Sort(property, SortType.desc);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		result = prime * result
				+ ((sortType == null) ? 0 : sortType.hashCode());
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
		Sort other = (Sort) obj;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (sortType != other.sortType)
			return false;
		return true;
	}

}
