package com.ruiec.web.common;

import java.io.Serializable;
import java.util.List;

import com.ruiec.web.enumeration.UnitRank;

/**
 * 登录用户管理单位实体
 * @author Senghor<br>
 * @date 2017年12月18日 下午3:29:40
 */
/**
 * 
 * @author Senghor<br>
 * @date 2018年1月13日 下午4:40:01
 */
public class LoginUserUnit implements Serializable{
	/**  */
	private static final long serialVersionUID = -681375021419391824L;
	/** 身份名称 */
	private String userName;
	/** 最高单位级别 */
	private UnitRank unitRank;
	/** 最高单位级别 */
	private Integer unitId;
	/** 直辖单位id */
	private List<Integer> unitIds;
	/** 直辖单位下级所有id */
	private List<Integer> unitSonIds;
	/** 紧急研判 */
	private boolean urgency = false;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UnitRank getUnitRank() {
		return unitRank;
	}
	public void setUnitRank(UnitRank unitRank) {
		this.unitRank = unitRank;
	}
	public List<Integer> getUnitIds() {
		return unitIds;
	}
	public void setUnitIds(List<Integer> unitIds) {
		this.unitIds = unitIds;
	}
	public List<Integer> getUnitSonIds() {
		return unitSonIds;
	}
	public void setUnitSonIds(List<Integer> unitSonIds) {
		this.unitSonIds = unitSonIds;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public boolean getUrgency() {
		return urgency;
	}
	public void setUrgency(boolean urgency) {
		this.urgency = urgency;
	}
	
}
