package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户表实体
 */
public class EmpStoreVerify implements Serializable {
    /** 商户->申请合作/月检 */
    private String id;

    /** 创建时间 */
    private Date verifytime;

    /** 类型 */
    private String type;

    /** 商户ID(首次申请的新商户=32个0) */
    private String empstoreid;

    /** 商户名(必填) */
    private String empstore;

    /** 位置经度 */
    private Double latitude;

    /** 位置维度 */
    private Double longitude;

    /** 位置(由经纬度生成) */
    private String address;

    /** 状态 */
    private String status;

    /** 图像路径 */
    private String path;

    /** 伙伴ID */
    private String staffid;

    /** 伙伴审核时间 */
    private Date stafftime;

    /** 伙伴审核意见 */
    private String staffnote;

    /** 备注(首次时，店长信息放这) */
    private String remark;

    /**
     * EmpStoreVerify
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->申请合作/月检
     * @return ID 商户->申请合作/月检
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->申请合作/月检
     * @param id 商户->申请合作/月检
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return VerifyTime 创建时间
     */
    public Date getVerifytime() {
        return verifytime;
    }

    /**
     * 创建时间
     * @param verifytime 创建时间
     */
    public void setVerifytime(Date verifytime) {
        this.verifytime = verifytime;
    }

    /**
     * 类型
     * @return Type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 商户ID(首次申请的新商户=32个0)
     * @return EmpStoreID 商户ID(首次申请的新商户=32个0)
     */
    public String getEmpstoreid() {
        return empstoreid;
    }

    /**
     * 商户ID(首次申请的新商户=32个0)
     * @param empstoreid 商户ID(首次申请的新商户=32个0)
     */
    public void setEmpstoreid(String empstoreid) {
        this.empstoreid = empstoreid == null ? null : empstoreid.trim();
    }

    /**
     * 商户名(必填)
     * @return EmpStore 商户名(必填)
     */
    public String getEmpstore() {
        return empstore;
    }

    /**
     * 商户名(必填)
     * @param empstore 商户名(必填)
     */
    public void setEmpstore(String empstore) {
        this.empstore = empstore == null ? null : empstore.trim();
    }

    /**
     * 位置经度
     * @return Latitude 位置经度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 位置经度
     * @param latitude 位置经度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 位置维度
     * @return Longitude 位置维度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 位置维度
     * @param longitude 位置维度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 位置(由经纬度生成)
     * @return Address 位置(由经纬度生成)
     */
    public String getAddress() {
        return address;
    }

    /**
     * 位置(由经纬度生成)
     * @param address 位置(由经纬度生成)
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 状态
     * @return Status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 图像路径
     * @return Path 图像路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 图像路径
     * @param path 图像路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 伙伴ID
     * @return StaffID 伙伴ID
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 伙伴ID
     * @param staffid 伙伴ID
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    /**
     * 伙伴审核时间
     * @return StaffTime 伙伴审核时间
     */
    public Date getStafftime() {
        return stafftime;
    }

    /**
     * 伙伴审核时间
     * @param stafftime 伙伴审核时间
     */
    public void setStafftime(Date stafftime) {
        this.stafftime = stafftime;
    }

    /**
     * 伙伴审核意见
     * @return StaffNote 伙伴审核意见
     */
    public String getStaffnote() {
        return staffnote;
    }

    /**
     * 伙伴审核意见
     * @param staffnote 伙伴审核意见
     */
    public void setStaffnote(String staffnote) {
        this.staffnote = staffnote == null ? null : staffnote.trim();
    }

    /**
     * 备注(首次时，店长信息放这)
     * @return Remark 备注(首次时，店长信息放这)
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注(首次时，店长信息放这)
     * @param remark 备注(首次时，店长信息放这)
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}