package com.xiaoyi.ssm.util;

/**  
 * @Description: 全局静态变量存储类
 * @author song  
 * @date 2018年7月7日 下午4:43:25 
 */ 
public class Global {
	
	/** APP客户端注册短信验证码获取代码 */
	public final static String api_member_register_SmsCode_ = "api_member_register_SmsCode_";
	/** APP客户端找回密码短信验证码获取代码 */
	public final static String api_member_findPassword_SmsCode_ = "api_member_findPassword_SmsCode_";
	/** APP客户端修改密码短信验证码获取代码 */
	public final static String api_member_updatePassword_SmsCode_ = "api_member_updatePassword_SmsCode_";

	/** WX小程序与端短信验证码获取代码 */
	public final static String wxapp_member_SmsCode_ = "wxapp_member_SmsCode_";

	/**  
	 * @Description: 阿里云图片服务器
	 * @author song  
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
	 * @author song  
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
	 * @author song  
	 * @date 2018年7月7日 下午4:43:25 
	 */ 
	/** redis缓存会员表名 */
	public final static String redis_member = "loginmember";
	/** reids缓存管理员表名 */
	public final static String redis_manager = "loginmanager";
	

	/** 微信小程序的登录信息 */
	public final static String REDIS_WXAPP_SESSION = "redis_wxapp_session";
	

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
	// 易租房城市
	/** 城市数据缓存 */
	public final static String REDIS_CITY_MAP = "redis_city_map";
	/** 区县数据缓存 */
	public final static String REDIS_DISTRICT_MAP = "redis_district_map";
	/** 片区数据缓存 */
	public final static String REDIS_AREA_MAP = "redis_area_map";
	
	// 易订场城市
	/** 城市数据缓存 */
	public final static String REDIS_APP_CITY_MAP = "redis_app_city_map";
	/** 区县数据缓存 */
	public final static String REDIS_APP_DISTRICT_MAP = "redis_app_district_map";
	
	
	/** 微信的access_token数据缓存map=access_token,jsapi_ticket */
	public final static String REDIS_ACCESS_TOKEN = "redis_access_token";
	
	
	/** 百度地图访问API的Key */
	public final static String Baidu_Map_KRY = "ITcG0S4URK9aokGSOhTNnSXCO9o7fK8D";
	

	/** 场地等待确认 */
	public final static String template_member1 = "9gJ4rzdwau9PdIyAWbUKUD_Gzm03jsXnDlySVOoatoI";
	/** 场地确认通知 */
	public final static String template_member2 = "iGwL_e8dlS_2bQFlmL5lgaKHlr2EgiiKQla0yz6agsg";
	/** 场地确认另行支付通知 */
	public final static String template_member3 = "aGI3n2dD0cXgin04iEF4IDshCuhQSsZRjTj1WGo_QDc";
	/** 球友订场等待确认 */
	public final static String template_manager = "WY4gdGcXVTgP55lE06boDKohEEEhGdYQoKmbAS0F5GA";
	public static Integer number;
	
}
