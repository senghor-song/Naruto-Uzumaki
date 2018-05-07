package com.ruiec.web.common;

import java.io.Serializable;
import java.util.List;

import com.ruiec.web.entity.User;
import com.ruiec.web.enumeration.UnitRank;

/**
 * 登录用户管理单位实体
 * 
 * @author Senghor<br>
 * @date 2017年12月18日 下午3:29:40
 */
public class LoginUserUnit implements Serializable {
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
	/** 用于存放登录用户信息 */
	private User user;

	/** 身份名称 */
	public String getUserName() {
		return userName;
	}

	/** 身份名称 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 最高单位级别 */
	public UnitRank getUnitRank() {
		return unitRank;
	}

	/** 最高单位级别 */
	public void setUnitRank(UnitRank unitRank) {
		this.unitRank = unitRank;
	}

	/** 最高单位级别 */
	public Integer getUnitId() {
		return unitId;
	}

	/** 最高单位级别 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/** 直辖单位id */
	public List<Integer> getUnitIds() {
		return unitIds;
	}

	/** 直辖单位id */
	public void setUnitIds(List<Integer> unitIds) {
		this.unitIds = unitIds;
	}

	/** 直辖单位下级所有id */
	public List<Integer> getUnitSonIds() {
		return unitSonIds;
	}

	/** 直辖单位下级所有id */
	public void setUnitSonIds(List<Integer> unitSonIds) {
		this.unitSonIds = unitSonIds;
	}

	/** 紧急研判 */
	public boolean isUrgency() {
		return urgency;
	}

	/** 紧急研判 */
	public void setUrgency(boolean urgency) {
		this.urgency = urgency;
	}

    /** 用于存放登录用户信息 */
    public User getUser() {
        return user;
    }

    /** 用于存放登录用户信息 */
    public void setUser(User user) {
        this.user = user;
    }

}
