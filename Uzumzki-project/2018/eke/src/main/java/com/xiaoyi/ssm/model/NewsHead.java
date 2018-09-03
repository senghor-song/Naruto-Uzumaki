package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻表实体
 */
public class NewsHead implements Serializable {
    /** 运营->楼讯 */
    private String id;

    /** 编号 */
    private Integer newsheadno;

    /** 状态 */
    private String status;

    /** 标题 */
    private String title;

    /** 来源 */
    private String source;

    /** 登记人ID */
    private String staffid;

    /** 登记时间 */
    private Date regdate;

    /** 显示时间 */
    private Date showdate;

    /** 封面路径 */
    private String coverpath;

    /** 是否置顶(0否1是) */
    private Byte flagtop;

    /** 查看次数 */
    private Integer viewcount;

    /** 分享次数 */
    private Integer sharecount;

    /** 逻辑删除 */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /** 排序 */
    private Integer sort;

    /** 备注 */
    private String remark;

    /** 内容HTML */
    private String content;

    /**
     * NewsHead
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->楼讯
     * @return ID 运营->楼讯
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->楼讯
     * @param id 运营->楼讯
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 编号
     * @return NewsHeadNo 编号
     */
    public Integer getNewsheadno() {
        return newsheadno;
    }

    /**
     * 编号
     * @param newsheadno 编号
     */
    public void setNewsheadno(Integer newsheadno) {
        this.newsheadno = newsheadno;
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
     * 标题
     * @return Title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 来源
     * @return Source 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 来源
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 登记人ID
     * @return StaffID 登记人ID
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 登记人ID
     * @param staffid 登记人ID
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    /**
     * 登记时间
     * @return RegDate 登记时间
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 登记时间
     * @param regdate 登记时间
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * 显示时间
     * @return ShowDate 显示时间
     */
    public Date getShowdate() {
        return showdate;
    }

    /**
     * 显示时间
     * @param showdate 显示时间
     */
    public void setShowdate(Date showdate) {
        this.showdate = showdate;
    }

    /**
     * 封面路径
     * @return CoverPath 封面路径
     */
    public String getCoverpath() {
        return coverpath;
    }

    /**
     * 封面路径
     * @param coverpath 封面路径
     */
    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath == null ? null : coverpath.trim();
    }

    /**
     * 是否置顶(0否1是)
     * @return FlagTop 是否置顶(0否1是)
     */
    public Byte getFlagtop() {
        return flagtop;
    }

    /**
     * 是否置顶(0否1是)
     * @param flagtop 是否置顶(0否1是)
     */
    public void setFlagtop(Byte flagtop) {
        this.flagtop = flagtop;
    }

    /**
     * 查看次数
     * @return ViewCount 查看次数
     */
    public Integer getViewcount() {
        return viewcount;
    }

    /**
     * 查看次数
     * @param viewcount 查看次数
     */
    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    /**
     * 分享次数
     * @return ShareCount 分享次数
     */
    public Integer getSharecount() {
        return sharecount;
    }

    /**
     * 分享次数
     * @param sharecount 分享次数
     */
    public void setSharecount(Integer sharecount) {
        this.sharecount = sharecount;
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
     * 排序
     * @return Sort 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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

    /**
     * 内容HTML
     * @return Content 内容HTML
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容HTML
     * @param content 内容HTML
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}