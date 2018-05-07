/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ruiec.web.util.HttpClientUtil;
import com.ruiec.web.util.SettingUtils;

/**
 * 中国网建短信服务接口
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2016年3月29日
 */
public class SendMsgService {
	
	private static final Logger LOGGER = Logger.getLogger(SendMsgService.class);
	
	/**
	 * 向指定手机号发送短信
	 * @param url 短信接口地址，如：http://utf8.sms.webchinese.cn/
	 * @param uid 短信平台用户名
	 * @param key 接口安全密码
	 * @param smsMob 目标手机号
	 * @param smsText 短信内容
	 * @return
	 * @author 熊华松
	 */
	public static boolean sendMsg(String url, String uid, String key, String smsMob, String smsText) {
		
		String reg = "^.*(<a.*>(.*)</a>).*$";
		Matcher matcher = Pattern.compile(reg).matcher(smsText);
		if(matcher.find()){
			String content1 = matcher.group(1);
			String content2 = matcher.group(2);
			smsText = smsText.replace(content1, content2);//去除超链接
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Uid", uid);
		map.put("Key", key);
		map.put("smsMob", smsMob);
		map.put("smsText", smsText);
		String[] result = HttpClientUtil.doPostQueryCmd(url, map);
		if(result[1] != null){
			if(Integer.valueOf(result[1]) > 0){
				LOGGER.info("短信发送成功：" + smsMob + "=|=" + result[1] + "=|=" + smsText);
				return true;
			}else{
				LOGGER.warn("短信发送失败:" + smsMob + "=|=" + result[1] + "=|=" + smsText);
				return false;
			}
		}else{
			LOGGER.warn("短信发送失败:" + smsMob + "=|=" + result[1] + "=|=" + smsText);
			return false;
		}
		
//		String requestHeader = "application/x-www-form-urlencoded;charset=utf8";
//		HttpClient client = new HttpClient();
//		PostMethod post = new PostMethod(url);
//		post.addRequestHeader("Content-Type", requestHeader);//在头文件中设置转码
//		NameValuePair[] data = { new NameValuePair("Uid", uid), new NameValuePair("Key", key),
//				new NameValuePair("smsMob", smsMob), new NameValuePair("smsText", smsText)};
//		post.setRequestBody(data);
//		
//		int statusCode = -1;
//		try {
//			client.executeMethod(post);
//			statusCode = post.getStatusCode();
//			post.releaseConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("短信发送异常");
//			return false;
//		}
//		
//		if (statusCode == 200) {// 发送成功
//			return true;
//		}
//		return false;
	}
	
	/**
	 * 向指定手机号发送短信
	 * @param smsMob 目标手机号
	 * @param smsText 短信内容
	 * @return
	 * @author 熊华松
	 */
	public static boolean sendMsg(String smsMob, String smsText) {
		String url = SettingUtils.get().getSmsUrl();
		String uid = SettingUtils.get().getSmsUid();
		String key = SettingUtils.get().getSmsKey();
		return sendMsg(url, uid, key, smsMob, smsText);
	}
	
	/**
	 * 向指定的多个手机号发送短信
	 * @param smsMobs 目标手机号
	 * @param smsText 短信内容
	 * @return
	 * @author 熊华松
	 */
	public static boolean sendMsg(List<String> smsMobs, String smsText) {
		if (smsMobs.size() > 100) {
			LOGGER.error("目标手机号不能超过100个");
			return false;
		}
		String url = SettingUtils.get().getSmsUrl();
		String uid = SettingUtils.get().getSmsUid();
		String key = SettingUtils.get().getSmsKey();
		StringBuilder sb = new StringBuilder();
		for (String mob : smsMobs) {
			sb.append(mob).append(",");
		}
		String smsMob = sb.substring(0, sb.lastIndexOf(","));
		return sendMsg(url, uid, key, smsMob, smsText);
	}
	
}
