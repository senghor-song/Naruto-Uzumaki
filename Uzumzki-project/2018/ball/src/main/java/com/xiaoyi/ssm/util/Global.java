package com.xiaoyi.ssm.util;

import java.util.HashMap;
import java.util.Map;

/**  
 * @Description: 全局静态变量存储类
 * @author song  
 * @date 2018年7月7日 下午4:43:25 
 */ 
public class Global {
	
	public static String DATE_STRING = "";
	
	/** APP客户端注册短信验证码获取代码 */
	public final static String api_member_register_SmsCode_ = "api_member_register_SmsCode_";
	/** APP客户端找回密码短信验证码获取代码 */
	public final static String api_member_findPassword_SmsCode_ = "api_member_findPassword_SmsCode_";
	/** APP客户端修改密码短信验证码获取代码 */
	public final static String api_member_updatePassword_SmsCode_ = "api_member_updatePassword_SmsCode_";

	/** WX小程序端短信验证码获取代码 */
	public final static String wxapp_member_SmsCode_ = "wxapp_member_SmsCode_";
	/** WX小程序端场馆入驻语音验证码 */
	public final static String wxapp_voice_SmsCode_ = "wxapp_voice_SmsCode_";
	

	/** WEB端注册短信验证码获取代码 */
	public final static String web_staff_register_SmsCode_ = "web_staff_register_SmsCode_";
	/** WEB端解除入驻短信验证码获取代码 */
	public final static String web_relieve_venue_SmsCode_ = "web_relieve_venue_SmsCode_";

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
	public final static String aliyunOssAccessKeyId = "LTAIohag5bNL1Rae";
	/** 阿里云OSS账户密码 */
	public final static String aliyunOssAccessKeySecret = "G9q395LG0kUfK9VJqrbU1GdBLalL5S";
	/** 阿里云OSS访问文件地址 */
	public final static String aliyunOssIpAddress = "https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/";

	/**  
	 * @Description: 阿里云接口
	 * @author song  
	 * @date 2018年7月7日 下午4:43:25 
	 */ 
	/** 阿里云账号ID */
	public final static String aliyunSMSAccessKeyId = "LTAIohag5bNL1Rae";
	/** 阿里云账户密码 */
	public final static String aliyunSMSAccessKeySecret = "G9q395LG0kUfK9VJqrbU1GdBLalL5S";
	/** 阿里云SMS签名信息---小易信息 */
	public final static String aliyunSMSSignName = "小易信息";
	/** 阿里云SMS签名信息---学训易 */
	public final static String aliyunSMSSignName1 = "学训易";
	/** 阿里云SMS短信模板代码---短信验证码 */
	public final static String aliyunSMSTempleteCode = "SMS_46390046";
	/** 阿里云SMS短信模板代码1---下单发送 */
//	public final static String aliyunSMSTempleteCode1 = "SMS_150738395";
	/** 阿里云SMS短信模板代码2---支付成功发送 */
	public final static String aliyunSMSTempleteCode2 = "SMS_152110138";
	/** 阿里云SMS短信模板代码3---超时发送 */
//	public final static String aliyunSMSTempleteCode3 = "SMS_150738378";
	/** 阿里云SMS短信模板代码4---场馆方：便捷确认完成 */
//	public final static String aliyunSMSTempleteCode4 = "SMS_150743014";
	/** 阿里云SMS短信模板代码5---用户方：便捷确认完成 */
//	public final static String aliyunSMSTempleteCode5 = "SMS_150735517";
	/** 
	 * 阿里云SMS短信模板代码6---用户方：便捷确认完成
	 * ${name}经营收入${day}转账失败。请微信关注学训易公众号-场馆合作，按照提示操作获取支付。
	 **/
	public final static String aliyunSMSTempleteCode6 = "SMS_152286078";


	/** 阿里云语音通知被叫显号 */
	public final static String aliyunCalledShowNumber = "073182705882";
	/** 阿里云语音通知Tts模板ID */
	public final static String aliyunTtsCode = "TTS_149460027";

	/**  
	 * @Description: Redis缓存的表名
	 * @author song  
	 * @date 2018年7月7日 下午4:43:25 
	 */ 
	/** redis缓存会员表名 */
	public final static String redis_member = "loginmember";

	/** 微信小程序的登录信息 */
	public final static String REDIS_WXAPP_SESSION = "redis_wxapp_session";
	/** APP登录信息 */
	public final static String redis_member_app = "redis_member_app";
	

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

	/** 短信方式的入驻数据 */
	public final static String REDIS_VENUE = "REDIS_VENUE";
	/** 手机号需要确认的订单ID */
	public final static String REDIS_ORDER = "REDIS_ORDER";
	

	/** 用户每天可退款次数 */
	public final static String REDIS_DAY_REFUND_COUNT = "redis_day_refund_count";
	/** 用户每天可获取场馆电话次数 */
	public final static String REDIS_DAY_GETPHONE_COUNT = "redis_day_getphone_count";
	
	
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
	

	/** 
	 * 预定通知模板<br>
	 * 预约时间keyword1<br>
	 * 预约地点keyword2<br>
	 * 活动地点keyword3<br>
	 * 预约状态keyword4<br>
	 **/
	public final static String template_id = "5UVB087Lv3FjlGBqbrQVSY8pXwQ-CTV4Hko3duGjj7Q";
	/** 
	 * 支付完成通知模板<br>
	 * 单号keyword1<br>
	 * 金额keyword2<br>
	 * 场地名称keyword3<br>
	 * 预约时间keyword4<br>
	 * 温馨提示keyword5<br>
	 **/
	public final static String template_id2 = "gHSarhAQp2fV5eDQazwp-TLte1XxcrhY-7Kpqg5GvqY";
	
	public static Integer number;
	
	public static Map<String, Integer> templateMap;
	public static Integer getTemplateCode(String phone){
		if (templateMap == null) {
			templateMap = new HashMap<String, Integer>();
		}
		Integer code = templateMap.get(phone);
		if (code == null) {
			templateMap.put(phone, 101);
			return 101;
		}else {
			if (code > 198) {
				code = 101;
			}else {
				code++;
			}
			templateMap.put(phone, code);
		}
		return code;
	}
}
