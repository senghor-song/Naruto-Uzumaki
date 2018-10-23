package com.xiaoyi.ssm.quartz;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;

@Service
public class CheckAppAccessTokenJob {
	
	private static Logger logger = Logger.getLogger(CheckAppAccessTokenJob.class.getName());

	/**
	 * 定时任务，每隔一小时刷新一次微信的 access_token
	 */
	@Scheduled(cron = "0 0 0/1 * * ? ")
//	@Scheduled(cron = "0/10 * * * * ? ")
	public void getWXAccess_token() {

		Map<String, Object> listMembers = RedisUtil.getRedisAll(Global.redis_member_app);
		for (String mem : listMembers.keySet()) {
			Member member = (Member) listMembers.get(mem);
			String accessTokenReturn = HttpUtils.sendGet("https://api.weixin.qq.com/sns/oauth2/refresh_token",
					"appid="+WXConfig.appid_app+"&grant_type=refresh_token&refresh_token="+member.getRefresh_token_app());

			JSONObject accessTokenJson = JSONObject.fromObject(accessTokenReturn);// 把请求成功后的结果转换成JSON对象
			logger.info("返回用户信息：accessTokenJson:" + accessTokenJson.toString());
			if (accessTokenJson == null || accessTokenJson.get("errcode") != null || accessTokenJson.getString("openid") == null) {
				logger.error(new Exception(mem+"获取用户信息回调异常"));
			}else {
				// 保存刷新凭证
				member.setAccess_token_app(accessTokenJson.getString("access_token"));
				member.setRefresh_token_app(accessTokenJson.getString("refresh_token"));
				// 保存用户登录信息
				RedisUtil.addRedis(Global.redis_member_app, mem, member);
			}
		}
	}
}
