/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 分页封装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月11日
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE, getterVisibility=JsonAutoDetect.Visibility.NONE, setterVisibility=JsonAutoDetect.Visibility.NONE, isGetterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int MAX_PAGE_SIZE=200;
	
	private int pageNumber=1;//获取第几页
	private int pageSize=10;
	private Long totalCount=0L;
	
	private int pageCount=0;//获取总页数
	
	private String sortProperty;
	private Sort.SortType sortType;
	
	private String property;//查找属性名称(可用于连表查询）
	private String keyword;//查找关键字
	private List<Sort> sorts = new ArrayList<Sort>();//查询排序集
	private List<Filter> filters = new ArrayList<Filter>();//查询过滤集
	private java.util.Set<String> pathSet = new TreeSet<String>();//初始化路径集
	//不确定类型的泛型
	private List<?> list;
	
	
	/**
	 * 获取Page对象
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public static Page build(){
		return new Page();
	}
	
	/**
	 * 添加过滤
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page add(Filter filter){
		filters.add(filter);
		return this;
	}
	
	/**
	 * 添加排序
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page add(Sort sort){
		sorts.add(sort);
		return this;
	}
	
	/**
	 * 添加初始化路径
	 * @author 肖学良<br>
	 * Date: 2016年1月9日
	 */
	public Page add(String path){
		pathSet.add(path);
		return this;
	}
	
	/**
	 * 清除所有过滤
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page clearFilters(){
		filters.clear();
		sorts.clear();
		return this;
	}
	
	/**
	 * 清除所有排序
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page clearSorts(){
		sorts.clear();
		return this;
	}
	
	/**
	 * 清除：单字段排序，模糊查询，过滤集，排序集
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page clear(){
		sortProperty = null;
		sortType = null;
		property = null;
		keyword = null;
		sorts.clear();
		filters.clear();
		return this;
	}
	
	/**
	 * 获取总记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	@JsonProperty
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * 设置总记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		return this;
	}
	/**
	 * 获取第几页
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	@JsonProperty
	public int getPageNumber() {
		return pageNumber;
	}
	/**
	 * 设置第几页
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}
	/**
	 * 获取每页最大记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	@JsonProperty
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置每页最大记录数
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page setPageSize(int pageSize) {
		if(pageSize<1){
			pageSize=1;
		}else if(pageSize>MAX_PAGE_SIZE){
			pageSize=MAX_PAGE_SIZE;
		}else{
			this.pageSize = pageSize;
		}
		return this;
		
	}
	/**
	 * 获取总页数
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	@JsonProperty
	public int getPageCount() {
		pageCount=(int) (totalCount/pageSize);
		if(totalCount % pageSize>0){
			pageCount+=1;
		}
		return pageCount;
	}
	/**
	 * 获取模糊查询条件字段
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * 设置模糊查询条件字段
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * 获取模糊查询关键字
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 设置模糊查询关键字
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * 获取排序集
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public List<Sort> getSorts() {
		return sorts;
	}
	/**
	 * 获取过滤集
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public List<Filter> getFilters() {
		return filters;
	}
	
	/**
	 * 获取初始化路径
	 * @author 肖学良<br>
	 * Date: 2016年1月9日
	 */
	public java.util.Set<String> getPathSet() {
		return pathSet;
	}

	/**
	 * 获取查询结果集
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	@JsonProperty
	public List<?> getList() {
		return list;
	}
	/**
	 * 设置查询结果集
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page setList(List<?> list) {
		this.list = list;
		return this;
	}
	/**
	 * 获取排序字段
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public String getSortProperty() {
		return sortProperty;
	}
	/**
	 * 设置排序字段
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
		return this;
	}
	/**
	 * 获取排序字段排序方式
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Sort.SortType getSortType() {
		return sortType;
	}
	/**
	 * 设置排序字段排序方式
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Page setSortType(Sort.SortType sortType) {
		this.sortType = sortType;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filters == null) ? 0 : filters.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + pageNumber;
		result = prime * result + pageSize;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((sortProperty == null) ? 0 : sortProperty.hashCode());
		result = prime * result + ((sortType == null) ? 0 : sortType.hashCode());
		result = prime * result + ((sorts == null) ? 0 : sorts.hashCode());
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
		Page other = (Page) obj;
		if (filters == null) {
			if (other.filters != null)
				return false;
		} else if (!filters.equals(other.filters))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (pageNumber != other.pageNumber)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (sortProperty == null) {
			if (other.sortProperty != null)
				return false;
		} else if (!sortProperty.equals(other.sortProperty))
			return false;
		if (sortType != other.sortType)
			return false;
		if (sorts == null) {
			if (other.sorts != null)
				return false;
		} else if (!sorts.equals(other.sorts))
			return false;
		return true;
	}
	
}
