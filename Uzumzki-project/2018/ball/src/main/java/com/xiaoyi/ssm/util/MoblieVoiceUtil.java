package com.xiaoyi.ssm.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.log4j.Logger;

/**
 * 发送语音通知消息工具类
 * */
public class MoblieVoiceUtil {

	private final static Logger logger = Logger.getLogger(MoblieVoiceUtil.class);

	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dyvmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dyvmsapi.aliyuncs.com";

	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static String accessKeyId = Global.aliyunSMSAccessKeyId;
	private static String accessKeySecret = Global.aliyunSMSAccessKeySecret;

	/**
	 * @Description: 机构管理数据
	 * @author 宋高俊
	 * @param calledShowNumber被叫显号
	 * @param calledNumber被叫号码
	 * @param ttsCode Tts模板ID
	 * @param ttsParam 模板内容
	 * @return
	 * @date 2018年11月5日 下午3:52:39
	 */
	public static SingleCallByTtsResponse sendSms(String calledShowNumber, String calledNumber, String ttsCode, String ttsParam) throws ClientException {

		// 设置访问超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient 暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		SingleCallByTtsRequest request = new SingleCallByTtsRequest();
		// 必填-被叫显号,可在语音控制台中找到所购买的显号
		request.setCalledShowNumber(calledShowNumber);
		// 必填-被叫号码
		request.setCalledNumber(calledNumber);
		// 必填-Tts模板ID
		request.setTtsCode(ttsCode);
		// 可选-当模板中存在变量时需要设置此值
		request.setTtsParam(ttsParam);
		// 可选-音量 取值范围 0--200
		request.setVolume(100);
		// 可选-播放次数
		request.setPlayTimes(3);
		// 可选-外部扩展字段,此ID将在回执消息中带回给调用方
		request.setOutId("0123456789");
		try {
			// hint 此处可能会抛出异常，注意catch
			SingleCallByTtsResponse singleCallByTtsResponse = acsClient.getAcsResponse(request);
			if (singleCallByTtsResponse.getCode() != null && singleCallByTtsResponse.getCode().equals("OK")) {
				// 请求成功
				System.out.println("语音文本外呼---------------");
				System.out.println("RequestId=" + singleCallByTtsResponse.getRequestId());
				System.out.println("Code=" + singleCallByTtsResponse.getCode());
				System.out.println("Message=" + singleCallByTtsResponse.getMessage());
				System.out.println("CallId=" + singleCallByTtsResponse.getCallId());
				logger.info(calledNumber + "发送成功");
			} else {
				logger.info(calledNumber + "发送失败:" + singleCallByTtsResponse.getMessage());
			}
			return singleCallByTtsResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 验证码
	 * @author 宋高俊
	 * @param calledNumber
	 *            被叫号码
	 * @param code
	 *            验证码
	 * @return
	 * @date 2018年11月6日 上午9:35:30
	 */
	public static SingleCallByTtsResponse sendVoiceTeamplate(String calledNumber, String code) {
		System.out.println("语音文本外呼---------------");
		try {
			return sendSms(Global.aliyunCalledShowNumber, calledNumber, Global.aliyunTtsCode, "{\"product\":\"学训易\", \"code\":\"" + code + "\"}");
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println("语音文本外呼---------------");
//		 MoblieVoiceUtil.sendVoiceTeamplate("15207108150", "1234");
		MoblieVoiceUtil.sendVoiceTeamplate("0755 85257570", "1234");
	}
}
