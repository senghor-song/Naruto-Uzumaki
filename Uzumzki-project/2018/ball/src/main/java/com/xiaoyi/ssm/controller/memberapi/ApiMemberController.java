package com.xiaoyi.ssm.controller.memberapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.model.CombineJoin;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.service.CombineService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;

/**
 * @Description: 会员控制器
 * @author 宋高俊
 * @date 2018年8月28日 下午3:42:36
 */
@Controller
@RequestMapping("memberapi/member")
public class ApiMemberController {

	@Autowired
	private CombineService combineService;
	@Autowired
	private MemberService memberService;
	
	/**
	 * @Description: 退出登录
	 * @author 宋高俊
	 * @date 2018年8月28日 下午3:44:31
	 */
	@RequestMapping(value = "/loginout")
	@ResponseBody
	public ApiMessage loginout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		
		RedisUtil.delRedis(Global.redis_member, openid);
		return new ApiMessage(200, "退出成功");
	}
	
	/**  
	 * @Description: 加入培训接口
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月7日 上午11:59:10 
	 */ 
	@RequestMapping(value = "/addTrain")
	@ResponseBody
	public ApiMessage addTrain(String price, String content) {
		return new ApiMessage(200, "加入成功");
	}
	
	/**  
	 * @Description: 加入约球接口
	 * @author 宋高俊  
	 * @param price
	 * @param content
	 * @return 
	 * @date 2018年9月10日 下午4:52:53 
	 */ 
	@RequestMapping(value = "/addCombine")
	@ResponseBody
	public ApiMessage addCombine(String combineId, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
		
		Combine combine = combineService.selectByPrimaryKey(combineId);
		
		CombineJoin combineJoin = new CombineJoin();
		combineJoin.setId(Utils.getUUID());
		combineJoin.setCreatetimetime(new Date());
		combineJoin.setMemberid(member.getId());
		combineJoin.setCombineid(combine.getId());
		combineJoin.setType(0);// 待支付状态
		
		return new ApiMessage(200, "加入成功");
	}
	
	/**  
	 * @Description: 是否是管理员接口
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年9月12日 上午11:15:35 
	 */ 
	@RequestMapping(value = "/isManager")
	@ResponseBody
	public ApiMessage isManager(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		if (manager != null) {
			return new ApiMessage(200, "已成为管理员");
		}else {
			return new ApiMessage(400, "未成为管理员");
		}
	}
	
	/**
	 * @Description: 获取当前用户的基本信息
	 * @author 宋高俊
	 * @param request 获取session
	 * @return
	 * @date 2018年9月15日 上午11:01:37
	 */
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public ApiMessage getUserInfo(HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		Map<String, Object> access_token = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.appid);

		String userinfo = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/user/info", "access_token=" + access_token.get("access_token") + "&openid=" + openid + "&lang=zh_CN");
		if (!StringUtil.isBank(userinfo)) {
			JSONObject jsonObject = JSONObject.fromObject(userinfo);
			if ("0".equals(jsonObject.get("subscribe").toString())) {
				return new ApiMessage(400, "用户未关注公众号");
			}
			//更新用户的微信数据
			Member member = memberService.selectByOpenid(openid);
			member.setAppavatarurl(jsonObject.getString("headimgurl"));
			member.setAppgender(jsonObject.getInt("sex"));
			member.setAppnickname(jsonObject.getString("nickname"));
			memberService.updateByPrimaryKeySelective(member);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("headimgurl", jsonObject.getString("headimgurl"));
			map.put("sex", jsonObject.getInt("sex"));
			map.put("nickname", jsonObject.getString("nickname"));
			return new ApiMessage(200, "获取成功", map);
		}else {
			return new ApiMessage(400, "获取失败");
		}
	}
	
}
