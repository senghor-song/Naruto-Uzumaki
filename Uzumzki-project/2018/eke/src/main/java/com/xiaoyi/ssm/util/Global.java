package com.xiaoyi.ssm.util;

/**  
 * @Description: 全局静态变量存储类
 * @author 宋高俊  
 * @date 2018年7月7日 下午4:43:25 
 */ 
public class Global {
	
	public Integer number = 0;

	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	/** 官网短信验证码获取代码 */
	public final static String website_login_SmsCode_ = "website_login_SmsCode_";
	
	/** PC端注册短信验证码获取代码 */
	public final static String pc_register_SmsCode_ = "pc_register_SmsCode_";
	/** PC端注册短信验证码获取代码 */
	public final static String pc_updatePassword_SmsCode_ = "pc_updatePassword_SmsCode_";

	/** APP客户端登录短信验证码获取代码 */
	public final static String api_cust_login_SmsCode_ = "api_cust_login_SmsCode_";
	/** APP客户端注册短信验证码获取代码 */
	public final static String api_cust_register_SmsCode_ = "api_cust_register_SmsCode_";
	/** APP客户端注册短信验证码获取代码 */
	public final static String api_cust_updatePassword_SmsCode_ = "api_cust_updatePassword_SmsCode_";

	/** APP经济端注册短信验证码获取代码 */
	public final static String api_employee_register_SmsCode_ = "api_employee_register_SmsCode_";
	/** APP经济端注册短信验证码获取代码 */
	public final static String api_employee_updatePassword_SmsCode_ = "api_employee_updatePassword_SmsCode_";

	/** 后台注册短信验证码获取代码 */
	public final static String admin_register_SmsCode_ = "admin_register_SmsCode_";
	/** 后台注册短信验证码获取代码 */
	public final static String admin_updatePassword_SmsCode_ = "admin_updatePassword_SmsCode_";
	
	/**  
	 * @Description: 阿里云图片服务器
	 * @author 宋高俊  
	 * @date 2018年7月7日 下午4:43:25 
	 */ 
	/** 阿里云OSS域名 */
	public final static String aliyunOssEndpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	/** 阿里云OSS空间名称 */
	public final static String aliyunOssBucketName = "ekeae-image";
	/** 阿里云OSS账号ID */
	public final static String aliyunOssAccessKeyId = "LTAIWzdEloEBE07i";
	/** 阿里云OSS账户密码 */
	public final static String aliyunOssAccessKeySecret = "xan1El2kNfYWKiVYD696I8IQxbIkyS";
	/** 阿里云OSS访问文件地址 */
	public final static String aliyunOssIpAddress = "https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/";

	/**  
	 * @Description: 阿里云短信接口
	 * @author 宋高俊  
	 * @date 2018年7月7日 下午4:43:25 
	 */ 
	/** 阿里云SMS账号ID */
	public final static String aliyunSMSAccessKeyId = "LTAIWzdEloEBE07i";
	/** 阿里云SMS账户密码 */
	public final static String aliyunSMSAccessKeySecret = "xan1El2kNfYWKiVYD696I8IQxbIkyS";
	/** 阿里云SMS签名信息 */
	public final static String aliyunSMSSignName = "小易信息";
	/** 阿里云SMS短信模板代码 */
	public final static String aliyunSMSTempleteCode = "SMS_46390046";

	/**  
	 * @Description: Redis缓存的表名
	 * @author 宋高俊  
	 * @date 2018年7月7日 下午4:43:25 
	 */ 
	/** redis缓存客户表名 */
	public final static String redis_cust = "logincust";
	/** reids缓存经纪人表名 */
	public final static String redis_employee = "loginemployee";
	/** redis缓存PC端经纪人SessionID */
	public final static String redis_loginuser_SessionID = "loginuser";
	/** reids缓存经纪人表名 */
	public final static String smsCode = "smsCode";
	

	/** 常驻小区缓存 */
	public final static String REDIS_ESTATE_MAP = "redis_estate_map";
	/** 常驻网站缓存 */
	public final static String REDIS_WEB_MAP = "redis_web_map";
	/** 常驻小区均价缓存 */
	public final static String REDIS_ESTATE_AVG_MAP = "redis_estate_avg_map";
	/** 常驻顶部统计标签缓存 */
	public final static String REDIS_TOP_TAG_MAP = "redis_top_tag_map";
	/** 进度条加载数据缓存 */
	public final static String REDIS_SESSION_UPLOAD_MAP = "redis_session_upload_map";
	/** 根据城市名缓存城市数据 */
	public final static String REDIS_CITY_MAP = "redis_city_map";
	/** 根据区县名缓存区县数据 */
	public final static String REDIS_DISTRICT_MAP = "redis_district_map";
	/** 根据片区名缓存片区数据 */
	public final static String REDIS_AREA_MAP = "redis_area_map";
	
	
	/** 百度地图访问API的Key */
	public final static String Baidu_Map_KRY = "ITcG0S4URK9aokGSOhTNnSXCO9o7fK8D";
	
}
