package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class MassNoticeEmp implements Serializable {
	
	/** 经纪人 */
	private Employee employee;
	/** 管理员 */
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
    /** 推房->公告分发明细 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 公告ID */
    private String noticeid;

    /** 接受人(emp) */
    private String empid;

    /** 发送人(staff) */
    private String staffid;

    /** 查看状态(0=待阅，1=已阅) */
    private Byte state;

    /** 查看时间 */
    private Date receivetime;

    /**  */
    private String remark;

    /**
     * MassNoticeEmp
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->公告分发明细
     * @return ID 推房->公告分发明细
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->公告分发明细
     * @param id 推房->公告分发明细
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 公告ID
     * @return NoticeID 公告ID
     */
    public String getNoticeid() {
        return noticeid;
    }

    /**
     * 公告ID
     * @param noticeid 公告ID
     */
    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid == null ? null : noticeid.trim();
    }

    /**
     * 接受人(emp)
     * @return EmpID 接受人(emp)
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 接受人(emp)
     * @param empid 接受人(emp)
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 发送人(staff)
     * @return StaffID 发送人(staff)
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 发送人(staff)
     * @param staffid 发送人(staff)
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    /**
     * 查看状态(0=待阅，1=已阅)
     * @return State 查看状态(0=待阅，1=已阅)
     */
    public Byte getState() {
        return state;
    }

    /**
     * 查看状态(0=待阅，1=已阅)
     * @param state 查看状态(0=待阅，1=已阅)
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 查看时间
     * @return ReceiveTime 查看时间
     */
    public Date getReceivetime() {
        return receivetime;
    }

    /**
     * 查看时间
     * @param receivetime 查看时间
     */
    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    /**
     * 
     * @return Remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}