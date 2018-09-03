package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class PropertyImage implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 租售->房源图https://img.ekeae.com
     */
    private String id;

    /**
     * 房源ID
     */
    private String propertyid;

    /**
     * 上传时间
     */
    private Date uploadtime;

    /**
     * 上传人ID
     */
    private String empid;

    /**
     * 功能分类
     */
    private String function;

    /**
     * 大小(KB)
     */
    private Integer size;

    /**
     * URL
     */
    private String path;

    /**
     * 展示/不展示
     */
    private Byte flagshow;

    /**
     * 批注客服ID
     */
    private String staffid;

    /**
     * 删除标志
     */
    private Byte flagdeleted;

    /**
     * 删除时间
     */
    private Date deletedtime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 租售->房源图https://img.ekeae.com
     * @return ID 租售->房源图https://img.ekeae.com
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->房源图https://img.ekeae.com
     * @param id 租售->房源图https://img.ekeae.com
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
     * 上传时间
     * @return UploadTime 上传时间
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * 上传时间
     * @param uploadtime 上传时间
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    /**
     * 上传人ID
     * @return EmpID 上传人ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 上传人ID
     * @param empid 上传人ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 功能分类
     * @return Function 功能分类
     */
    public String getFunction() {
        return function;
    }

    /**
     * 功能分类
     * @param function 功能分类
     */
    public void setFunction(String function) {
        this.function = function == null ? null : function.trim();
    }

    /**
     * 大小(KB)
     * @return Size 大小(KB)
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 大小(KB)
     * @param size 大小(KB)
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * URL
     * @return Path URL
     */
    public String getPath() {
        return path;
    }

    /**
     * URL
     * @param path URL
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 展示/不展示
     * @return FlagShow 展示/不展示
     */
    public Byte getFlagshow() {
        return flagshow;
    }

    /**
     * 展示/不展示
     * @param flagshow 展示/不展示
     */
    public void setFlagshow(Byte flagshow) {
        this.flagshow = flagshow;
    }

    /**
     * 批注客服ID
     * @return StaffID 批注客服ID
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 批注客服ID
     * @param staffid 批注客服ID
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    /**
     * 删除标志
     * @return FlagDeleted 删除标志
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 删除标志
     * @param flagdeleted 删除标志
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

    /**
     * 备注
     * @return Remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}