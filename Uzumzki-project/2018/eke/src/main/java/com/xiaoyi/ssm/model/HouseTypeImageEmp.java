package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class HouseTypeImageEmp implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 租售->房型图（经纪人）https://img.ekeae.com
     */
    private String id;

    /**
     * 小区ID
     */
    private String estateid;

    /**
     * 房源ID
     */
    private String houseid;

    /**
     * 户型数
     */
    private Integer countf;

    /**
     * （辅助）
     */
    private String city;

    /**
     * （辅助）
     */
    private String estate;

    /**
     * 加入时间
     */
    private Date addtime;

    /**
     * 上传人ID
     */
    private String operatorid;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 大小(KB)
     */
    private Integer size;

    /**
     * URL
     */
    private String path;

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
     * 租售->房型图（经纪人）https://img.ekeae.com
     * @return ID 租售->房型图（经纪人）https://img.ekeae.com
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->房型图（经纪人）https://img.ekeae.com
     * @param id 租售->房型图（经纪人）https://img.ekeae.com
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 小区ID
     * @return EstateID 小区ID
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 小区ID
     * @param estateid 小区ID
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
    }

    /**
     * 房源ID
     * @return HouseID 房源ID
     */
    public String getHouseid() {
        return houseid;
    }

    /**
     * 房源ID
     * @param houseid 房源ID
     */
    public void setHouseid(String houseid) {
        this.houseid = houseid == null ? null : houseid.trim();
    }

    /**
     * 户型数
     * @return CountF 户型数
     */
    public Integer getCountf() {
        return countf;
    }

    /**
     * 户型数
     * @param countf 户型数
     */
    public void setCountf(Integer countf) {
        this.countf = countf;
    }

    /**
     * （辅助）
     * @return City （辅助）
     */
    public String getCity() {
        return city;
    }

    /**
     * （辅助）
     * @param city （辅助）
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * （辅助）
     * @return Estate （辅助）
     */
    public String getEstate() {
        return estate;
    }

    /**
     * （辅助）
     * @param estate （辅助）
     */
    public void setEstate(String estate) {
        this.estate = estate == null ? null : estate.trim();
    }

    /**
     * 加入时间
     * @return AddTime 加入时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 加入时间
     * @param addtime 加入时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 上传人ID
     * @return OperatorID 上传人ID
     */
    public String getOperatorid() {
        return operatorid;
    }

    /**
     * 上传人ID
     * @param operatorid 上传人ID
     */
    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid == null ? null : operatorid.trim();
    }

    /**
     * 文件名
     * @return Filename 文件名
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 文件名
     * @param filename 文件名
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
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