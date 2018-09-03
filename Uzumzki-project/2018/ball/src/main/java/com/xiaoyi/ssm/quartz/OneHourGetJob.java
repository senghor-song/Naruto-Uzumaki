package com.xiaoyi.ssm.quartz;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;

import net.sf.json.JSONObject;

@Service
public class OneHourGetJob {
	
	private static Logger logger = LoggerFactory.getLogger(OneHourGetJob.class);

	/**
	 * 定时任务，每隔一小时获取一次微信的 access_token
	 */
	@Scheduled(cron = "0 0 0/1 * * ? ")
//	@Scheduled(cron = "0/10 * * * * ? ")
	public void getWXAccess_token() {
		// 获取微信公众号的access_token
		String accessTokenDate = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/token",
				"grant_type=client_credential&appid=" + WXConfig.appid + "&secret=" + WXConfig.appSecret);
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

			logger.info("access_token和jsapi_ticket获取成功并放入缓存");
			RedisUtil.addRedis(Global.REDIS_ACCESS_TOKEN, WXConfig.appid, map);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
}
