package com.xiaoyi.ssm.util;


import org.apache.log4j.Logger;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 发送短信通知消息工具类
 * */
public class MoblieMessageUtil {

	private final static Logger logger = Logger.getLogger(MoblieMessageUtil.class);

	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";

	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static String accessKeyId = Global.aliyunSMSAccessKeyId;
	private static String accessKeySecret = Global.aliyunSMSAccessKeySecret;
	private static String templeteCode = Global.aliyunSMSTempleteCode;

	public static SendSmsResponse sendSms(String mobile, String templateParam, String templateCode, String signName)
			throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();

		// 必填:待发送手机号
		request.setPhoneNumbers(mobile);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);

		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam(templateParam);

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)7
		request.setSmsUpExtendCode("555");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("555");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		logger.info("发送提示："+sendSmsResponse.getMessage());
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			logger.info(mobile + "发送成功");
		}
		return sendSmsResponse;
	}

	/**
	 * @Description: 验证码
	 * @author 宋高俊  
	 * @param mobile 手机号
	 * @param code 验证码
	 * @return 
	 * @date 2018年11月6日 上午9:35:30 
	 */
	public static SendSmsResponse sendIdentifyingCode(String mobile, String code) {
		try {
			return sendSms(mobile, "{\"code\":\"" + code + "\"}", templeteCode, Global.aliyunSMSSignName);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description: 下单发送 
	 * @author 宋高俊  
	 * @param mobile 场馆联系方式
	 * @param name 用户昵称
	 * @param tel 用户手机号
	 * @param area 场地
	 * @param day 日期
	 * @param time 时间段
	 * @return 
	 * @date 2018年11月6日 上午9:35:48 
	 */
//	public static SendSmsResponse sendTemplateSms(String mobile, String name, String tel, String area, String day, String time) {
//		try {
//			return sendSms(mobile, "{\"name\":\"" + name + "\", \"tel\":\"" + tel + "\", \"area\":\"" + area + "\", \"day\":\"" + day + "\", \"time\":\"" + time + "\"}",
//					Global.aliyunSMSTempleteCode1, Global.aliyunSMSSignName1);
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * @Description: 支付成功发送
	 * @author 宋高俊  
	 * @param mobile 场馆联系方式
	 * @param name 用户昵称
	 * @param tel 用户手机号
	 * @param area 场地
	 * @param day 日期
	 * @param time 时间段
	 * @param code 回复内容
	 * @return 
	 * @date 2018年11月6日 上午9:35:48 
	 */
	public static SendSmsResponse sendTemplateSms2(String mobile, String name, String tel, String day, String time, double money, Integer code) {
		try {
			return sendSms(mobile, "{\"name\":\"" + name + "\", \"tel\":\"" + tel + "\", \"day\":\"" + day + "\", \"time\":\"" + time + "\", \"money\":\"" + money + "\", \"code\":\"" + code + "\"}",
					Global.aliyunSMSTempleteCode2, Global.aliyunSMSSignName1);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @Description: 场馆方：便捷确认完成
	 * @author 宋高俊  
	 * @param mobile 场馆联系方式
	 * @param name 用户昵称
	 * @param tel 用户手机号
	 * @param area 场地
	 * @param day 日期
	 * @param time 时间段
	 * @return 
	 * @date 2018年11月6日 上午9:35:48 
	 */
	/*public static SendSmsResponse sendTemplateSms4(String mobile, String name, String tel, String area, String day, String time, double money) {
		try {
			return sendSms(mobile, "{\"name\":\"" + name + "\", \"tel\":\"" + tel + "\", \"area\":\"" + area 
					+ "\", \"day\":\"" + day + "\", \"time\":\"" + time + "\", \"money\":\"" + money + "\"}",
					Global.aliyunSMSTempleteCode4, Global.aliyunSMSSignName1);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}*/

	/**
	 * @Description: 用户方：便捷确认完成
	 * @author 宋高俊  
	 * @param mobile 场馆联系方式
	 * @param name 用户昵称
	 * @param tel 用户手机号
	 * @param area 场地
	 * @param day 日期
	 * @param time 时间段
	 * @return 
	 * @date 2018年11月6日 上午9:35:48 
	 */
//	public static SendSmsResponse sendTemplateSms5(String mobile, String name, String tel, String area, String day, String time) {
//		try {
//			return sendSms(mobile, "{\"name\":\"" + name + "\", \"tel\":\"" + tel + "\", \"area\":\"" + area 
//					+ "\", \"day\":\"" + day + "\", \"time\":\"" + time + "\"}",
//					Global.aliyunSMSTempleteCode5, Global.aliyunSMSSignName1);
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * @Description: 未关注公众号发送
	 * @author 宋高俊  
	 * @param mobile 发送人手机号
	 * @param name 场馆名
	 * @param day 日期
	 * @return 
	 * @date 2018年11月6日 上午9:35:48 
	 */
	public static SendSmsResponse sendTemplateSms6(String mobile, String name, String day) {
		try {
			return sendSms(mobile, "{\"name\":\"" + name + "\", \"day\":\"" + day + "\"}",
					Global.aliyunSMSTempleteCode6, Global.aliyunSMSSignName1);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		MoblieMessageUtil.sendTemplateSms("15207108156", "小宋", "15207108156", "东边1", "2018-11-08", "12:30-13:30");
//		MoblieMessageUtil.sendTemplateSms5("14774825972", "小宋", "15207108156", "东边1", "2018-11-08", "12:30-13:30");
	}
}
