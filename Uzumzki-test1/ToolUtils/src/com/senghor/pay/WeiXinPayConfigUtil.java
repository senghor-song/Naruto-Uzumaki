package com.senghor.pay;

import javax.servlet.http.HttpServletRequest;

public class WeiXinPayConfigUtil {
	public static final String APP_ID = "wx2d10e9ad3aa536ce";// 微信开发平台应用ID(公众号ID)
	public static final String MCH_ID = "1395387802";// 商户号(商户号ID)
	public static final String API_KEY = "ekeEKE00000000000000000000000000";// API key(商户号里面的)
	public static final String CREATE_IP = "127.0.0.1";// 发起支付的ip
	public static final String NOTIFY_URL = "http://song123.iask.in:18800/WebRelease/pay/WeiXinPay";// 回调地址
	public static final String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 微信统一下单接口
	public static final String APP_SECRET = "84dc497095320c8bcd147b5753677ef5";// 应用对应的凭证(在公众号里面)

	// 获取ip
	public static String getIP(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		return ip;
	}
}
