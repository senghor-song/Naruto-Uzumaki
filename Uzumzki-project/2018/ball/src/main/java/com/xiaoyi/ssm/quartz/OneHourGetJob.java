package com.xiaoyi.ssm.quartz;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;

@Service
public class OneHourGetJob {
	
	private static Logger logger = Logger.getLogger(OneHourGetJob.class.getName());

	/**
	 * 定时任务，每隔一小时获取一次微信的 access_token
	 */
	@Scheduled(cron = "0 0 0/1 * * ? ")
//	@Scheduled(cron = "0/10 * * * * ? ")
	public void oneHourGetJob() {
		// 获取微信公众号的access_token
		String accessTokenDate = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/token",
				"grant_type=client_credential&appid=" + WXConfig.appid + "&secret=" + WXConfig.appSecret);
		// 获取微信小程序的access_token
		String accessTokenDate1 = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/token",
				"grant_type=client_credential&appid=" + WXConfig.wxAppAppid + "&secret=" + WXConfig.wxAppApp_secret);
		try {
			JSONObject accessTokenJson = JSONObject.fromObject(accessTokenDate);
			logger.info("access_token:" + accessTokenJson.toString());
			Map<String, Object> map = new HashMap<>();
			String access_token = (String) accessTokenJson.get("access_token");
			map.put("access_token", access_token);

			// 获取微信公众号的jsapi_ticket
			String jsapiData = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket",
					"access_token=" + access_token + "&type=jsapi");
			JSONObject jsonObject = JSONObject.fromObject(jsapiData);
			logger.info("jsapi_ticket:" + jsonObject.toString());
			String jsapi_ticket = (String) jsonObject.get("ticket");
			map.put("jsapi_ticket", jsapi_ticket);

			logger.info("微信公众号的access_token和jsapi_ticket获取成功并放入缓存");
			RedisUtil.addRedis(Global.REDIS_ACCESS_TOKEN, WXConfig.appid, map);
			
			JSONObject accessTokenJson1 = JSONObject.fromObject(accessTokenDate1);
			logger.info("access_token:" + accessTokenJson1.toString());
			Map<String, Object> map1 = new HashMap<>();
			String access_token1 = (String) accessTokenJson1.get("access_token");
			map1.put("access_token", access_token1);
			
			logger.info("微信小程序的access_token获取成功并放入缓存");
			RedisUtil.addRedis(Global.REDIS_ACCESS_TOKEN, WXConfig.wxAppAppid, map1);
			
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
