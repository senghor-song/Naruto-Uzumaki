package com.ruiec.web.dto;

import java.io.Serializable;

/**
 * 管理员管理查询条件DTO
 * @author 贺云<br>
 * @date 2017年12月9日 上午10:48:33
 */
public class UserUnitDTO implements Serializable{

	private static final long serialVersionUID = 2881126805933601170L;
	
	/**部门ID*/
	private Integer unitId;
	
	/**查询类型（1=身份证，2=姓名）*/
	private Integer type;
	
	/**搜索内容*/
	private String searchContent;
	/**部门ID*/
	public Integer getUnitId() {
		return unitId;
	}
	/**部门ID*/
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	/**查询类型（1=身份证，2=姓名）*/
	public Integer getType() {
		return type;
	}
	/**查询类型（1=身份证，2=姓名）*/
	public void setType(Integer type) {
		this.type = type;
	}
	/**搜索内容*/
	public String getSearchContent() {
		return searchContent;
	}
	/**搜索内容*/
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	
}
