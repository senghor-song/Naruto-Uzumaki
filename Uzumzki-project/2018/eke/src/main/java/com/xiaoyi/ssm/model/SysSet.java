package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class SysSet implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 系统->
     */
    private String paramname;

    /**
     * 
     */
    private Date moddate;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private String paramdata;

    /**
     * 系统->
     * @return ParamName 系统->
     */
    public String getParamname() {
        return paramname;
    }

    /**
     * 系统->
     * @param paramname 系统->
     */
    public void setParamname(String paramname) {
        this.paramname = paramname == null ? null : paramname.trim();
    }

    /**
     * 
     * @return ModDate 
     */
    public Date getModdate() {
        return moddate;
    }

    /**
     * 
     * @param moddate 
     */
    public void setModdate(Date moddate) {
        this.moddate = moddate;
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

    /**
     * 
     * @return ParamData 
     */
    public String getParamdata() {
        return paramdata;
    }

    /**
     * 
     * @param paramdata 
     */
    public void setParamdata(String paramdata) {
        this.paramdata = paramdata == null ? null : paramdata.trim();
    }
}