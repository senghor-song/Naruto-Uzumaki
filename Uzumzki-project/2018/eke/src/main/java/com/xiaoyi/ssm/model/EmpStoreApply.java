package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户申请合作实体
 */
public class EmpStoreApply implements Serializable {
    /** 商户->商户申请合作 */
    private String id;

    /** 申请时间 */
    private Date apply;

    /** 申请经纪人 */
    private String empapply;

    /** （辅助，联系人及电话） */
    private String emp;

    /** 商户名 */
    private String storeapply;

    /** 城市 */
    private String cityid;

    /** 位置经度 */
    private Double latitude;

    /** 位置维度 */
    private Double longitude;

    /** 地址(由经纬度生成) */
    private String address;

    /** 店长 */
    private String leader;

    /** 店长电话 */
    private String leadertel;

    /** 逻辑删除 */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /** 备注 */
    private String remark;

    /**
     * EmpStoreApply
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->商户申请合作
     * @return ID 商户->商户申请合作
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->商户申请合作
     * @param id 商户->商户申请合作
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 申请时间
     * @return Apply 申请时间
     */
    public Date getApply() {
        return apply;
    }

    /**
     * 申请时间
     * @param apply 申请时间
     */
    public void setApply(Date apply) {
        this.apply = apply;
    }

    /**
     * 申请经纪人
     * @return EmpApply 申请经纪人
     */
    public String getEmpapply() {
        return empapply;
    }

    /**
     * 申请经纪人
     * @param empapply 申请经纪人
     */
    public void setEmpapply(String empapply) {
        this.empapply = empapply == null ? null : empapply.trim();
    }

    /**
     * （辅助，联系人及电话）
     * @return Emp （辅助，联系人及电话）
     */
    public String getEmp() {
        return emp;
    }

    /**
     * （辅助，联系人及电话）
     * @param emp （辅助，联系人及电话）
     */
    public void setEmp(String emp) {
        this.emp = emp == null ? null : emp.trim();
    }

    /**
     * 商户名
     * @return StoreApply 商户名
     */
    public String getStoreapply() {
        return storeapply;
    }

    /**
     * 商户名
     * @param storeapply 商户名
     */
    public void setStoreapply(String storeapply) {
        this.storeapply = storeapply == null ? null : storeapply.trim();
    }

    /**
     * 城市
     * @return CityID 城市
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市
     * @param cityid 城市
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
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
     * 地址(由经纬度生成)
     * @return Address 地址(由经纬度生成)
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址(由经纬度生成)
     * @param address 地址(由经纬度生成)
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 店长
     * @return Leader 店长
     */
    public String getLeader() {
        return leader;
    }

    /**
     * 店长
     * @param leader 店长
     */
    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    /**
     * 店长电话
     * @return LeaderTel 店长电话
     */
    public String getLeadertel() {
        return leadertel;
    }

    /**
     * 店长电话
     * @param leadertel 店长电话
     */
    public void setLeadertel(String leadertel) {
        this.leadertel = leadertel == null ? null : leadertel.trim();
    }

    /**
     * 逻辑删除
     * @return FlagDeleted 逻辑删除
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 逻辑删除
     * @param flagdeleted 逻辑删除
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