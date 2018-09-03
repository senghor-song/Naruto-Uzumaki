package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 经纪人实体
 */
public class Employee implements Serializable {
	
	 /** 多对一商户实体 */
    private EmpStore empStore;

    /** 多对一商户实体 */
    public EmpStore getEmpStore() {
		return empStore;
	}

    /** 多对一商户实体 */
	public void setEmpStore(EmpStore empStore) {
		this.empStore = empStore;
	}
	
	/** 用于显示剩余天数 */
	private Integer remainingDays;
	
    public Integer getRemainingDays() {
		return remainingDays;
	}

	public void setRemainingDays(Integer remainingDays) {
		this.remainingDays = remainingDays;
	}

    /** 商户->经纪人 */
    private String id;

    /** 编号 */
    private Integer empno;

    /** 经纪姓名 */
    private String emp;

    /** 密码 */
    private String password;

    /** 第三方登录专用密码（群发工具） */
    private String thirdpwd;

    /** 手机 */
    private String tel;

    /** 名片手机号码 */
    private String telcard;

    /** 其他联系方式 */
    private String contactinform;

    /** 状态 */
    private String status;

    /** 性别 */
    private String sex;

    /** 身份证号 */
    private String idcard;

    /** 注册邮箱 */
    private String email;

    /** 个性签名 */
    private String signer;

    /** 头像URL */
    private String icon;

    /** 头像修改时间 */
    private Date icontime;

    /** 微信号 */
    private String wxno;

    /** 微信OpenID */
    private String wxopenid;

    /** 所属商户ID */
    private String storeid;

    /** （辅助） */
    private String store;

    /** 是否=商户管理员 */
    private Byte storemanage;

    /** 账户实名状态 */
    private String authstate;

    /** 性格特点（下划线隔开，最多4个，每个最多5字） */
    private String feature;

    /** 经纪人性格特点修改时间 */
    private Date featuremodtime;

    /** 推房付费账户有效期 */
    private Date massvalidity;

    /** 推房账户类别 */
    private String masstype;

    /** 发布配额 */
    private Integer massquota;

    /** 最大库存 */
    private Integer massstock;

    /** 城市ID */
    private String cityid;

    /** 城市名称 */
    private String cityname;

    /** 积分 */
    private Integer score;

    /** 备注 */
    private String remark;

    /**
     * Employee
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->经纪人
     * @return ID 商户->经纪人
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->经纪人
     * @param id 商户->经纪人
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 编号
     * @return EmpNo 编号
     */
    public Integer getEmpno() {
        return empno;
    }

    /**
     * 编号
     * @param empno 编号
     */
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    /**
     * 经纪姓名
     * @return Emp 经纪姓名
     */
    public String getEmp() {
        return emp;
    }

    /**
     * 经纪姓名
     * @param emp 经纪姓名
     */
    public void setEmp(String emp) {
        this.emp = emp == null ? null : emp.trim();
    }

    /**
     * 密码
     * @return Password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 第三方登录专用密码（群发工具）
     * @return ThirdPWD 第三方登录专用密码（群发工具）
     */
    public String getThirdpwd() {
        return thirdpwd;
    }

    /**
     * 第三方登录专用密码（群发工具）
     * @param thirdpwd 第三方登录专用密码（群发工具）
     */
    public void setThirdpwd(String thirdpwd) {
        this.thirdpwd = thirdpwd == null ? null : thirdpwd.trim();
    }

    /**
     * 手机
     * @return Tel 手机
     */
    public String getTel() {
        return tel;
    }

    /**
     * 手机
     * @param tel 手机
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 名片手机号码
     * @return TelCard 名片手机号码
     */
    public String getTelcard() {
        return telcard;
    }

    /**
     * 名片手机号码
     * @param telcard 名片手机号码
     */
    public void setTelcard(String telcard) {
        this.telcard = telcard == null ? null : telcard.trim();
    }

    /**
     * 其他联系方式
     * @return ContactInform 其他联系方式
     */
    public String getContactinform() {
        return contactinform;
    }

    /**
     * 其他联系方式
     * @param contactinform 其他联系方式
     */
    public void setContactinform(String contactinform) {
        this.contactinform = contactinform == null ? null : contactinform.trim();
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
     * 性别
     * @return Sex 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 身份证号
     * @return IDCard 身份证号
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 身份证号
     * @param idcard 身份证号
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 注册邮箱
     * @return EMail 注册邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 注册邮箱
     * @param email 注册邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 个性签名
     * @return Signer 个性签名
     */
    public String getSigner() {
        return signer;
    }

    /**
     * 个性签名
     * @param signer 个性签名
     */
    public void setSigner(String signer) {
        this.signer = signer == null ? null : signer.trim();
    }

    /**
     * 头像URL
     * @return Icon 头像URL
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 头像URL
     * @param icon 头像URL
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 头像修改时间
     * @return IconTime 头像修改时间
     */
    public Date getIcontime() {
        return icontime;
    }

    /**
     * 头像修改时间
     * @param icontime 头像修改时间
     */
    public void setIcontime(Date icontime) {
        this.icontime = icontime;
    }

    /**
     * 微信号
     * @return WXNo 微信号
     */
    public String getWxno() {
        return wxno;
    }

    /**
     * 微信号
     * @param wxno 微信号
     */
    public void setWxno(String wxno) {
        this.wxno = wxno == null ? null : wxno.trim();
    }

    /**
     * 微信OpenID
     * @return WXOpenID 微信OpenID
     */
    public String getWxopenid() {
        return wxopenid;
    }

    /**
     * 微信OpenID
     * @param wxopenid 微信OpenID
     */
    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid == null ? null : wxopenid.trim();
    }

    /**
     * 所属商户ID
     * @return StoreID 所属商户ID
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * 所属商户ID
     * @param storeid 所属商户ID
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid == null ? null : storeid.trim();
    }

    /**
     * （辅助）
     * @return Store （辅助）
     */
    public String getStore() {
        return store;
    }

    /**
     * （辅助）
     * @param store （辅助）
     */
    public void setStore(String store) {
        this.store = store == null ? null : store.trim();
    }

    /**
     * 是否=商户管理员
     * @return StoreManage 是否=商户管理员
     */
    public Byte getStoremanage() {
        return storemanage;
    }

    /**
     * 是否=商户管理员
     * @param storemanage 是否=商户管理员
     */
    public void setStoremanage(Byte storemanage) {
        this.storemanage = storemanage;
    }

    /**
     * 账户实名状态
     * @return AuthState 账户实名状态
     */
    public String getAuthstate() {
        return authstate;
    }

    /**
     * 账户实名状态
     * @param authstate 账户实名状态
     */
    public void setAuthstate(String authstate) {
        this.authstate = authstate == null ? null : authstate.trim();
    }

    /**
     * 性格特点（下划线隔开，最多4个，每个最多5字）
     * @return Feature 性格特点（下划线隔开，最多4个，每个最多5字）
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 性格特点（下划线隔开，最多4个，每个最多5字）
     * @param feature 性格特点（下划线隔开，最多4个，每个最多5字）
     */
    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    /**
     * 经纪人性格特点修改时间
     * @return FeatureModTime 经纪人性格特点修改时间
     */
    public Date getFeaturemodtime() {
        return featuremodtime;
    }

    /**
     * 经纪人性格特点修改时间
     * @param featuremodtime 经纪人性格特点修改时间
     */
    public void setFeaturemodtime(Date featuremodtime) {
        this.featuremodtime = featuremodtime;
    }

    /**
     * 推房付费账户有效期
     * @return MassValidity 推房付费账户有效期
     */
    public Date getMassvalidity() {
        return massvalidity;
    }

    /**
     * 推房付费账户有效期
     * @param massvalidity 推房付费账户有效期
     */
    public void setMassvalidity(Date massvalidity) {
        this.massvalidity = massvalidity;
    }

    /**
     * 推房账户类别
     * @return MassType 推房账户类别
     */
    public String getMasstype() {
        return masstype;
    }

    /**
     * 推房账户类别
     * @param masstype 推房账户类别
     */
    public void setMasstype(String masstype) {
        this.masstype = masstype == null ? null : masstype.trim();
    }

    /**
     * 发布配额
     * @return MassQuota 发布配额
     */
    public Integer getMassquota() {
        return massquota;
    }

    /**
     * 发布配额
     * @param massquota 发布配额
     */
    public void setMassquota(Integer massquota) {
        this.massquota = massquota;
    }

    /**
     * 最大库存
     * @return MassStock 最大库存
     */
    public Integer getMassstock() {
        return massstock;
    }

    /**
     * 最大库存
     * @param massstock 最大库存
     */
    public void setMassstock(Integer massstock) {
        this.massstock = massstock;
    }

    /**
     * 城市ID
     * @return CityID 城市ID
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市ID
     * @param cityid 城市ID
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 城市名称
     * @return CityName 城市名称
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * 城市名称
     * @param cityname 城市名称
     */
    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    /**
     * 积分
     * @return Score 积分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 积分
     * @param score 积分
     */
    public void setScore(Integer score) {
        this.score = score;
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