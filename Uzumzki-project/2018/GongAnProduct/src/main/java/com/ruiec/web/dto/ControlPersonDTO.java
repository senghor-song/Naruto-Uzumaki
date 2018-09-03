package com.ruiec.web.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ruiec.web.entity.User;

/**
 * 重点人员查询条件
 * @author Senghor<br>
 * @date 2017年12月13日 下午7:39:39
 */
public class ControlPersonDTO implements Serializable{

	private static final long serialVersionUID = 2881126805933601170L;
	
	/** 单位id */
	private Integer areaUnitId;
	/** 单位id2 */
	private Integer townUnitId;
	/** 下发状态 */
	private Integer outType;
	/** 人员类别id */
	private Integer personTypeId;
	/** 警员id */
	private Integer policeUserId;
	/** 人员类别的拼接字符串 */
	private String Categories;
	/** 查询开始时间 */
	private Date startDate;
	/** 查询结束时间 */
	private Date endDate;
	/** 单位集合 */
	private List<Integer> unitIds;
	/** 当前登录用户 */
	private User user ;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Integer> getUnitIds() {
		return unitIds;
	}
	public void setUnitIds(List<Integer> unitIds) {
		this.unitIds = unitIds;
	}
	
	
	public Integer getOutType() {
		return outType;
	}
	public void setOutType(Integer outType) {
		this.outType = outType;
	}
	public Integer getPersonTypeId() {
		return personTypeId;
	}
	public void setPersonTypeId(Integer personTypeId) {
		this.personTypeId = personTypeId;
	}
	public String getCategories() {
		return Categories;
	}
	public Integer getPoliceUserId() {
		return policeUserId;
	}
	public void setPoliceUserId(Integer policeUserId) {
		this.policeUserId = policeUserId;
	}
	public void setCategories(String categories) {
		Categories = categories;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getAreaUnitId() {
		return areaUnitId;
	}
	public void setAreaUnitId(Integer areaUnitId) {
		this.areaUnitId = areaUnitId;
	}
	public Integer getTownUnitId() {
		return townUnitId;
	}
	public void setTownUnitId(Integer townUnitId) {
		this.townUnitId = townUnitId;
	}
	
	
}
