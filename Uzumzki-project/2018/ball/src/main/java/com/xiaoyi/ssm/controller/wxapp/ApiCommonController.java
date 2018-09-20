package com.xiaoyi.ssm.controller.wxapp;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;

/**
 * @Description: 微信小程序公共接口
 * @author 宋高俊
 * @date 2018年9月11日 下午4:22:23
 */
@Controller("wxappCommonController")
@RequestMapping("wxapp/common")
public class ApiCommonController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberService memberService;

	/**
	 * @Description: 微信小程序用code换取openid
	 * @author 宋高俊
	 * @param request
	 * @param response
	 * @param state
	 * @date 2018年9月11日 下午4:24:23
	 */
	@RequestMapping(value = "/code2accessToken", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage code2accessToken(HttpServletRequest request, HttpServletResponse response, String code) {
		logger.info("开始获取微信小程序openid");
		JSONObject getCodeResultJson = null;
		String token = "";
		try {
			logger.info("开始根据code获取微信小程序openid");
			String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code"
					.replace("APPID", WXConfig.wxAppAppid).replace("SECRET", WXConfig.wxAppApp_secret).replace("JSCODE", code);

			String requestResult = HttpUtils.sendGet(requestUrl, null);// 我们需要自己写或者在网上找一个doGet方法发送doGet请求
			getCodeResultJson = JSONObject.fromObject(requestResult);// 把请求成功后的结果转换成JSON对象

			if (getCodeResultJson == null || getCodeResultJson.get("errcode") != null || getCodeResultJson.getString("openid") == null) {
				logger.error("", new Exception("获取回调异常"));
			}

			logger.info("getCodeResultJson:" + getCodeResultJson.toString());

			Member member = memberService.selectByUnionid(getCodeResultJson.getString("unionid"));

			//将openid作为key存储登录信息
			RedisUtil.addRedis(Global.REDIS_WXAPP_SESSION, getCodeResultJson.getString("unionid"), getCodeResultJson);
			
			token = getCodeResultJson.getString("unionid");
			if (member != null) {
				RedisUtil.addRedis(Global.redis_member, getCodeResultJson.getString("unionid"), member);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return new ApiMessage(200, "登录成功", token);
	}

	/**
	 * @Description: 创建用户的接口
	 * @author 宋高俊
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @date 2018年9月12日 上午11:33:27
	 */
	@RequestMapping(value = "/createMember", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage createMember(String token,String nickName, String avatarUrl, Integer gender) {
		JSONObject jsonObject = JSONObject.fromObject(RedisUtil.getRedisOne(Global.REDIS_WXAPP_SESSION, token));
		
		Member member = memberService.selectByUnionid(jsonObject.getString("unionid"));
		
		if (member != null) {
			member.setCreatetime(new Date());
			member.setModifytime(new Date());
			member.setAppavatarurl(avatarUrl);
			member.setAppnickname(nickName);
			member.setAppgender(gender);
			memberService.updateByPrimaryKeySelective(member);
			return new ApiMessage(200, "修改成功");
		}else {
			member = new Member();
			member.setId(Utils.getUUID());
			member.setCreatetime(new Date());
			member.setModifytime(new Date());
			member.setAppavatarurl(avatarUrl);
			member.setAppnickname(nickName);
			member.setAppgender(gender);
			member.setUnionid(jsonObject.get("unionid").toString());
			member.setAppopenid(jsonObject.get("openid").toString());
			memberService.insertSelective(member);
			return new ApiMessage(200, "创建成功");
		}
	}
	
}
