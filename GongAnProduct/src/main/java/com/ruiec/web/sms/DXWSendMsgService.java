/**
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.sms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.ruiec.web.util.SettingUtils;

/**
 * 发送短信服务类<br>
 * 		服务商: web.duanxinwang.cc<br>
 * 		传输协议: HTTP<br>
 * 		调用示例: http://web.duanxinwang.cc/asmx/smsservice.aspx?name=登录名&pwd=接口密码&mobile=手机号码&content=内容&sign=签名&stime=发送时间&type=pt&extno=自定义扩展码<br>
 * @author 熊华松<br>
 * Version: 1.0<br>
 * Date: 2016年09月09日
 */
public class DXWSendMsgService {
	
	private static final Logger LOGGER = Logger.getLogger(DXWSendMsgService.class);
	private static final String LOGGER_PREFIX = "短信发送服务: "; // 日志前缀
	
	/**
	 * 提交参数给短信服务提供商<p>
	 * 响应报文说明:			0,20130821110353234137876543,0,500,0,提交成功(响应状态,发送编号,无效号码数,成功提交数,黑名单数,消息)<br>
	 * 响应状态值说明:		0(提交成功),1(含有敏感词汇),2(余额不足),3(没有号码),4(包含sql语句)
	 * 						10(账号不存在),11(账号注销),12(账号停用),13(IP鉴权失败),14(格式错误),-1(系统异常)
	 * @param name			用户名(必填)
	 * @param pwd			密码(必填)
	 * @param sign			签名(必填)
	 * @param url			提交的链接(必填)
	 * @param mobileString	电话号码字符串，多个电话号码中间用英文逗号间隔(必填)
	 * @param contextString	短信内容字符串(必填)
	 * @param stime			追加发送时间，可为空，为空为及时发送(非必填)
	 * @param extno			扩展码，必须为数字，可为空(非必填)
	 * @return
	 * @author 熊华松
	 * Date: 2016年09月09日
	 */
	public static boolean doPost(String name, String pwd, String sign, String url, StringBuffer mobileString, StringBuffer contextString, String stime, StringBuffer extno) {
		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		boolean sendResult = false;
		try {
			StringBuffer param = new StringBuffer();
			param.append("name=" + name);
			param.append("&pwd=" + pwd);
			param.append("&mobile=").append(mobileString);
			param.append("&content=").append(URLEncoder.encode(contextString.toString(), "UTF-8"));
			param.append("&stime=" + stime);
			param.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));
			param.append("&type=pt");
			param.append("&extno=").append(extno);
			URL localURL = new URL(url + "?");
			URLConnection connection = localURL.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));
			String resultBuffer = "";
			outputStream = httpURLConnection.getOutputStream();
			outputStreamWriter = new OutputStreamWriter(outputStream);
			outputStreamWriter.write(param.toString());
			outputStreamWriter.flush();
			if (httpURLConnection.getResponseCode() != 200) {
				LOGGER.error(LOGGER_PREFIX + "HTTP响应了错误, 状态码为" + httpURLConnection.getResponseCode() + "...");
				return sendResult;
			}
			inputStream = httpURLConnection.getInputStream();
			resultBuffer = convertStreamToString(inputStream);
			LOGGER.info(LOGGER_PREFIX + mobileString + "=|=" + contextString + "=|=" + resultBuffer);
			if ("0".equals(resultBuffer.split(",")[0])) {
				sendResult = true;
			}
		} catch (IOException e) {
			LOGGER.error(LOGGER_PREFIX + "流打开错误...", e);
		} finally {
			try {
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				LOGGER.error(LOGGER_PREFIX + "流关闭错误...", e);
			}
		}
		return sendResult;
	}
	
	/**
	 * 转换输入流为UTF-8格式的字符串
	 * @param is
	 * @return
	 * @author 熊华松
	 * Date: 2016年09月09日
	 */
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		byte[] bytes = new byte[4096];
		int size = 0;
		try {
			while ((size = is.read(bytes)) > 0) {
				String str = new String(bytes, 0, size, "UTF-8");
				sb.append(str);
			}
		} catch (IOException e) {
			LOGGER.error(LOGGER_PREFIX + "输入流读取错误...", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				LOGGER.error(LOGGER_PREFIX + "输入流关闭错误...", e);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 发送短信
	 * @param mobileString			电话号码字符串，多个电话号码中间用英文逗号间隔
	 * @param contextString			短信内容字符串
	 * @return
	 * @author 熊华松
	 * Date: 2016年09月09日
	 */
	public static boolean sendMsg(String mobileString, String contextString) {
		String uid = SettingUtils.get().getSmsUid(); // 短信接口用户名
		String key = SettingUtils.get().getSmsKey(); // 短信接口密码
		String sign = SettingUtils.get().getSmsSign(); // 短信接口地址
		String url = SettingUtils.get().getSmsUrl(); // 短信标识
		return doPost(uid, key, sign, url, new StringBuffer(mobileString), new StringBuffer(contextString), "", new StringBuffer());
	}

}
