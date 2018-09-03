package com.xiaoyi.ssm.wxPay;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 微信的配置参数
 * @author 宋高俊  
 * @date 2018年8月30日 下午7:25:22 
 */ 
public class WXConfig {

	/**
	 * 公众号id 公司提供wx3d5beb67c8455dd2
	 */
	public static final String appid = "wxa76f9c07c99468b2";
//	public static final String appid = "wx2efc8721a286a255";

	/**
	 * 公众号密钥，公司提供aae6b38f32894df280e79da3a53043e3
	 */
	public static final String appSecret = "3caf90ffa0f40292524eba00588f6e0d";
//	public static final String appSecret = "9a198b8c3497aafa8c71d0397b67d0fb";

	/**
	 * 商户号 公司提供
	 */
	public static final String mch_id = "1390967002";

	/**
	 * 商户号密钥，公司提供
	 */
	public static final String paternerKey = "ekeEKE00000000000000000000000000";

	/**
	 * 随机字符串
	 */
	public static final String nonce_str = Utils.md5Password(String.valueOf(new Random().nextInt(10000)));

	/**
	 * 时间戳
	 */
	public static final String timeStamp = Long.toString(System.currentTimeMillis() / 1000);

	/**
	 * 签名方式
	 */
	public static final String signType = "MD5";

	/**
	 * 通知地址
	 */
	public static final String notify_url = "https://ball.ekeae.com/WebBackAPI/memberapi/order/weixinNotify";

	/**
	 * 交易类型
	 */
	public static final String trade_type = "JSAPI";

	/**
	 * 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
	 */
	public static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
    /**
     * 微信退款url  微信端提供的退款接口
     */
    public static final String gaterefund_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	// 获取ip
	public static String getIP(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		return ip;
	}
	
	
	
}
