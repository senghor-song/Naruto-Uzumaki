package com.xiaoyi.ssm.controller.wxapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dao.MemberHabitMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.model.InviteImage;
import com.xiaoyi.ssm.model.InviteJoin;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.service.InviteImageService;
import com.xiaoyi.ssm.service.InviteJoinService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.NewsCollectService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainCourseCollectService;
import com.xiaoyi.ssm.service.TrainOrderService;
import com.xiaoyi.ssm.service.TrainTeamCollectService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.AES;

/**
 * @Description: 微信小程序公共接口
 * @author 宋高俊
 * @date 2018年9月11日 下午4:22:23
 */
@Controller("wxappMemberController")
@RequestMapping("wxapp/member")
public class ApiMemberController {

    private static Logger logger = Logger.getLogger(ApiMemberController.class.getName());
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private InviteBallService inviteBallService;
	@Autowired
	private InviteImageService inviteImageService;
	@Autowired
	private InviteJoinService inviteJoinService;
	@Autowired
	private TrainOrderService trainOrderService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private MemberHabitMapper memberHabitMapper;
	@Autowired
	private TrainTeamCollectService trainTeamCollectService;
	@Autowired
	private TrainCourseCollectService trainCourseCollectService;
	@Autowired
	private NewsCollectService newsCollectService;

	/**
	 * @Description: 查询我参与的约球
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年9月18日 下午5:41:33
	 */
	@RequestMapping(value = "/getMyJoinInviteBall", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getMyJoinInviteBall(HttpServletRequest request, PageBean pageBean) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 开始分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<InviteBall> list = inviteBallService.selectByMyInviteBall(member.getId());
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			InviteBall inviteBall = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", inviteBall.getId());// ID
			InviteImage inviteImage = inviteImageService.selectByHeadID(inviteBall.getId());
			if (inviteImage != null) {
				map.put("image", inviteImage.getUrl());// 封面图片
			} else {
				map.put("image", "");// 封面图片
			}
			map.put("title", inviteBall.getTitle());// 标题
			map.put("activeTime", DateUtil.getFormat(inviteBall.getActiveTime(),"yyyy-MM-dd HH:mm"));// 活动时间
			map.put("ballType", inviteBall.getBallType());// 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)

			Integer exit = 0;
			if (inviteBall.getExitFlag() == 1) {
				InviteJoin inviteJoin = inviteJoinService.selectByJoinMemberKey(inviteBall.getId(), member.getId());
				// 没有提出退出申请的
				if (inviteJoin.getRefundFlag() != 2) {
					long nowDate = new Date().getTime();// 当前时间
					long nowDateEnd = DateUtil.getWeeHours(inviteBall.getActiveTime(), 1).getTime();// 活动时间当天的23:59:59
					// 活动状态
					if (nowDateEnd > nowDate) {
						if (inviteBall.getExitType() == 1) {
							exit = 1;// 活动状态，当前时间未过当天23:59:59可以退出
						}else {
							exit = 2;// 活动状态，当前时间未过当天23:59:59可以退出
						}
					}
				}
			}
			map.put("exit", exit);// 是否显示退出按钮
			// 根据约球ID查询加入人员
			List<InviteJoin> inviteJoins = inviteJoinService.selectByJoinMember(inviteBall.getId());
			List<String> joinList = new ArrayList<String>();
			for (int j = 0; j < inviteJoins.size(); j++) {
				joinList.add(inviteJoins.get(j).getMember().getAppavatarurl());
			}
			map.put("joinMember", joinList);// 已加入人员头像
			listmap.add(map);
		}
		return new ApiMessage(200, "登录成功", listmap);
	}
	
	/**
	 * @Description: 查询我发起的约球
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年9月18日 下午5:41:33
	 */
	@RequestMapping(value = "/getMyApplyInviteBall", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getMyApplyInviteBall(HttpServletRequest request, PageBean pageBean) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 开始分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<InviteBall> list = inviteBallService.selectByMyApplyInviteBall(member.getId());
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());// ID
			InviteImage inviteImage = inviteImageService.selectByHeadID(list.get(i).getId());
			if (inviteImage != null) {
				map.put("image", inviteImage.getUrl());// 封面图片
			} else {
				map.put("image", "");// 封面图片
			}
			map.put("title", list.get(i).getTitle());// 标题
			map.put("activeTime", DateUtil.getFormat(list.get(i).getActiveTime(),"yyyy-MM-dd HH:mm"));// 活动时间
			map.put("ballType", list.get(i).getBallType());// 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)
			// 根据约球ID查询加入人员
			List<InviteJoin> inviteJoins = inviteJoinService.selectByJoinMember(list.get(i).getId());
			List<String> joinList = new ArrayList<String>();
			for (int j = 0; j < inviteJoins.size(); j++) {
				joinList.add(inviteJoins.get(j).getMember().getAppavatarurl());
			}
			map.put("joinMember", joinList);// 已加入人员头像
			listmap.add(map);
		}
		return new ApiMessage(200, "登录成功", listmap);
	}

	/**  
	 * @Description: 查询我加入和参与的约球统计
	 * @author 宋高俊  
	 * @param request
	 * @param pageBean
	 * @return 
	 * @date 2018年9月21日 上午9:12:12 
	 */ 
	@RequestMapping(value = "/getMyJoinCount", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getMyJoinCount(HttpServletRequest request, PageBean pageBean) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Integer myJoinBall = inviteJoinService.countByMyJoinBall(member.getId());
		Integer myJoinApplyBall = inviteBallService.countByMyApplyBall(member.getId());
		Integer myTrainOrder = trainOrderService.countByMyTrainOrder(member.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myJoinBall", myJoinBall);// 我参与的约球总数
		map.put("myJoinApplyBall", myJoinApplyBall);// 我发起的约球总数
		map.put("myTrainOrder", myTrainOrder);// 我的私教总数
		int collectSum = 0;
		collectSum += memberHabitMapper.countOften(member.getId());
		collectSum += trainTeamCollectService.countByMember(member.getId());
		collectSum += trainCourseCollectService.countByMember(member.getId());
		collectSum += newsCollectService.countByMember(member.getId());
		map.put("venue", collectSum);// 收藏总数
		
		TrainCoach trainCoach = trainCoachService.selectByMember(member.getId());
		if (trainCoach != null ) {
			map.put("coachFlag", 1);// 是教练
		}else {
			map.put("coachFlag", 0);// 不是教练
		}
		return new ApiMessage(200, "查询成功", map);
	}
	
	/**
	 * @Description: 微信解密数据接口
	 * @author 宋高俊
	 * @param request
	 * @throws Exception
	 * @date 2018年9月11日 下午4:24:23
	 */
	@RequestMapping(value = "/getPhone", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getPhone(HttpServletRequest request, String encryptedData, String iv){
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		logger.info(encryptedData + "->" + member.getSessionKey() + "->" + iv);
		String result = "";
		try {
			result = AES.decrypt(encryptedData, member.getSessionKey(), iv, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != result && result.length() > 0) {
			JSONObject json = JSONObject.fromObject(result);
			member.setPhone(json.getString("phoneNumber"));
			memberService.updateByPrimaryKeySelective(member);
			RedisUtil.addRedis(Global.redis_member, token, member);
			return new ApiMessage(200, "获取成功", json);
		}
		return new ApiMessage(400, "获取失败");
	}
	
	/**
	 * @Description: 后台注册获取验证码
	 * @author 宋高俊
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request) {
		if (Utils.getPhone(phone)) {
			Member oldmember = memberService.selectByPhone(phone);
			String smsCode = Utils.getCode();
			try {
				if (oldmember != null) {
					return new ApiMessage(400, "该手机号码已被使用");
				}
				MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
				RedisUtil.setRedis(Global.wxapp_member_SmsCode_ + phone, smsCode, 120);
				return new ApiMessage(200, "发送成功");
			} catch (Exception e) {
				return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
			}
		} else {
			return new ApiMessage(400, "请输入正确的手机号码");
		}
	}

	/**
	 * @Description: 绑定手机号
	 * @author 宋高俊
	 * @param phone
	 * @param code
	 * @param request
	 * @return
	 * @date 2018年9月22日 下午5:05:00
	 */
	@RequestMapping(value = "/bindPhone")
	@ResponseBody
	public ApiMessage bindPhone(String phone, String code, HttpServletRequest request) {
		if (StringUtil.isBank(phone, code)) {
			return new ApiMessage(400, "手机号和验证码必填");
		}

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		String flagCode = (String) RedisUtil.getRedis(Global.wxapp_member_SmsCode_ + phone);
		if (code.equals(flagCode)) {
			// 验证成功
			member.setPhone(phone);
			int flag = memberService.updateByPrimaryKeySelective(member);
			RedisUtil.addRedis(Global.redis_member, token, member);
			if (flag > 0) {
				return new ApiMessage(200, "绑定成功");
			} else {
				return new ApiMessage(400, "绑定失败");
			}
		} else {
			return new ApiMessage(400, "验证码不正确");
		}
	}
	
	/**  
	 * @Description: 个人设置数据
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月12日 上午11:30:37 
	 */ 
	@RequestMapping(value = "/getMember")
	@ResponseBody
	public ApiMessage getMember(HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberno", member.getMemberno()); //用户编号
		map.put("phone", member.getPhone()); //绑定手机号
		map.put("showphone", member.getShowphone()); //活动发起人展示手机号
		map.put("showhistory", member.getShowhistory()); //是否显示历史活动
		map.put("wechatid", member.getWechatid()); //微信号
		map.put("showwechatid", member.getShowwechatid()); //微信号-发起报名时允许查看
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**  
	 * @Description: 修改个人设置数据
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月12日 上午11:30:37 
	 */ 
	@RequestMapping(value = "/updateMember")
	@ResponseBody
	public ApiMessage updateMember(HttpServletRequest request, String phone, Integer showphone, Integer showhistory, String wechatid, Integer showwechatid) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		member.setPhone(phone); //绑定手机号
		member.setShowphone(showphone); //活动发起人展示手机号
		member.setShowhistory(showhistory); //是否显示历史活动
		member.setWechatid(wechatid); //微信号
		member.setShowwechatid(showwechatid); //微信号-发起报名时允许查看
		try {
			memberService.updateByPrimaryKeySelective(member);
			RedisUtil.addRedis(Global.redis_member, token, member);
			return new ApiMessage(200, "修改成功");
		} catch (Exception e) {
			return new ApiMessage(400, "修改失败");
		}
	}
	
}
