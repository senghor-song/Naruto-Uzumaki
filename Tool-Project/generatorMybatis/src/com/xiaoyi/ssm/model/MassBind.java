package com.xiaoyi.ssm.model;

public class MassBind {
    /**
     * æŽ¨æˆ¿->ç¬¬ä¸‰æ–¹è´¦æˆ·ç»‘å®šï¼ˆæ¯�ç½‘ç«™æœ€å¤šä¸‰ä¸ªè´¦æˆ·ï¼‰
     */
    private String id;

    /**
     * ç¬¬ä¸‰æ–¹ç½‘ç«™
     */
    private String platform;

    /**
     * è´¦æˆ·
     */
    private String account;

    /**
     * è´¦æˆ·å¯†ç �
     */
    private String pwd;

    /**
     * 
     */
    private String remark;

    /**
     * æŽ¨æˆ¿->ç¬¬ä¸‰æ–¹è´¦æˆ·ç»‘å®šï¼ˆæ¯�ç½‘ç«™æœ€å¤šä¸‰ä¸ªè´¦æˆ·ï¼‰
     * @return ID æŽ¨æˆ¿->ç¬¬ä¸‰æ–¹è´¦æˆ·ç»‘å®šï¼ˆæ¯�ç½‘ç«™æœ€å¤šä¸‰ä¸ªè´¦æˆ·ï¼‰
     */
    public String getId() {
        return id;
    }

    /**
     * æŽ¨æˆ¿->ç¬¬ä¸‰æ–¹è´¦æˆ·ç»‘å®šï¼ˆæ¯�ç½‘ç«™æœ€å¤šä¸‰ä¸ªè´¦æˆ·ï¼‰
     * @param id æŽ¨æˆ¿->ç¬¬ä¸‰æ–¹è´¦æˆ·ç»‘å®šï¼ˆæ¯�ç½‘ç«™æœ€å¤šä¸‰ä¸ªè´¦æˆ·ï¼‰
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * ç¬¬ä¸‰æ–¹ç½‘ç«™
     * @return Platform ç¬¬ä¸‰æ–¹ç½‘ç«™
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * ç¬¬ä¸‰æ–¹ç½‘ç«™
     * @param platform ç¬¬ä¸‰æ–¹ç½‘ç«™
     */
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    /**
     * è´¦æˆ·
     * @return Account è´¦æˆ·
     */
    public String getAccount() {
        return account;
    }

    /**
     * è´¦æˆ·
     * @param account è´¦æˆ·
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * è´¦æˆ·å¯†ç �
     * @return Pwd è´¦æˆ·å¯†ç �
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * è´¦æˆ·å¯†ç �
     * @param pwd è´¦æˆ·å¯†ç �
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
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