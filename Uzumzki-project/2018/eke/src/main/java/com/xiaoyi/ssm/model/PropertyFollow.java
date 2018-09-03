package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源跟进表实体
 */
public class PropertyFollow implements Serializable {
	private Employee employee;
	
    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/** 租售->房源跟进 */
    private String id;

    /** 房源ID */
    private String propertyid;

    /** 经纪ID */
    private String empid;

    /** 跟进时间 */
    private Date followdate;

    /** 可视级(内部级仅客服系统能看) */
    private String view;

    /**  */
    private String followtype;

    /** 跟进主题 */
    private String theme;

    /** 跟进内容 */
    private String content;

    /** 逻辑删除(后台专用) */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /**
     * PropertyFollow
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->房源跟进
     * @return ID 租售->房源跟进
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->房源跟进
     * @param id 租售->房源跟进
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 房源ID
     * @return PropertyID 房源ID
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * 房源ID
     * @param propertyid 房源ID
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid == null ? null : propertyid.trim();
    }

    /**
     * 经纪ID
     * @return EmpID 经纪ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 经纪ID
     * @param empid 经纪ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 跟进时间
     * @return FollowDate 跟进时间
     */
    public Date getFollowdate() {
        return followdate;
    }

    /**
     * 跟进时间
     * @param followdate 跟进时间
     */
    public void setFollowdate(Date followdate) {
        this.followdate = followdate;
    }

    /**
     * 可视级(内部级仅客服系统能看)
     * @return View 可视级(内部级仅客服系统能看)
     */
    public String getView() {
        return view;
    }

    /**
     * 可视级(内部级仅客服系统能看)
     * @param view 可视级(内部级仅客服系统能看)
     */
    public void setView(String view) {
        this.view = view == null ? null : view.trim();
    }

    /**
     * 
     * @return FollowType 
     */
    public String getFollowtype() {
        return followtype;
    }

    /**
     * 
     * @param followtype 
     */
    public void setFollowtype(String followtype) {
        this.followtype = followtype == null ? null : followtype.trim();
    }

    /**
     * 跟进主题
     * @return Theme 跟进主题
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 跟进主题
     * @param theme 跟进主题
     */
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    /**
     * 跟进内容
     * @return Content 跟进内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 跟进内容
     * @param content 跟进内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 逻辑删除(后台专用)
     * @return FlagDeleted 逻辑删除(后台专用)
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 逻辑删除(后台专用)
     * @param flagdeleted 逻辑删除(后台专用)
     */
    public void setFlagdeleted(Byte flagdeleted) {
        this.flagdeleted = flagdeleted;
    }

    /**
     * 删除时间
     * @return DeletedTime 删除时间
     */
    public Date getDeletedtime() {
        return deletedtime;
    }

    /**
     * 删除时间
     * @param deletedtime 删除时间
     */
    public void setDeletedtime(Date deletedtime) {
        this.deletedtime = deletedtime;
    }
}