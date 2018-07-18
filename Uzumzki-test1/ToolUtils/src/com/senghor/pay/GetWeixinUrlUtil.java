package com.senghor.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetWeixinUrlUtil{
	
	public static void main(String[] args) {
		System.out.println(0.01*100);
	}
	private static final Logger logger = LoggerFactory.getLogger(GetWeixinUrlUtil.class);
	
    //获取二维码url  
    public static String weixin_pay(String out_trade_no,Double price) throws Exception {  
    // 账号信息  
        String appid = WeiXinPayConfigUtil.APP_ID;  // appid  
        String mch_id = WeiXinPayConfigUtil.MCH_ID; // 商户号  
        String key = WeiXinPayConfigUtil.API_KEY; // key  
        String currTime = PayCommonUtil.getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = PayCommonUtil.buildRandom(4) + "";  
        String nonce_str = strTime + strRandom;          
	    DecimalFormat df=new DecimalFormat("0.00");
	    System.out.println(df.format((float)1/100));
        int order_price = Integer.valueOf((int)(price*100)); // 商品价格   注意：价格的单位是分  
        String body = "付费信息";   // 商品名称  
        // 获取发起电脑 ip  
        String spbill_create_ip = "127.0.0.1";  
        // 回调接口   
        String notify_url = WeiXinPayConfigUtil.NOTIFY_URL;  
        String trade_type = "NATIVE";  //交易类型          
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
        packageParams.put("appid", appid);  
        packageParams.put("mch_id", mch_id);  
        packageParams.put("nonce_str", nonce_str);  
        packageParams.put("body", body);  
        packageParams.put("out_trade_no", out_trade_no);  
        packageParams.put("total_fee", String.valueOf(order_price));  
        packageParams.put("spbill_create_ip", spbill_create_ip);  
        packageParams.put("notify_url", notify_url);  
        packageParams.put("trade_type", trade_type);  
        //生成签名
        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
        packageParams.put("sign", sign);  
          
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        logger.info(requestXML);
   
        String resXml = HttpUtil.postData(WeiXinPayConfigUtil.UFDODER_URL, requestXML);
        logger.info(resXml);
        Map map = XMLUtil.doXMLParse(resXml);  
        String urlCode = (String) map.get("code_url");  
        return urlCode;   
    } 
    
    // 特殊字符处理  
    public static String UrlEncode(String src)  throws UnsupportedEncodingException {  
        return URLEncoder.encode(src, "UTF-8").replace("+", "%20");  
    }  
}