package com.xiaoyi.ssm.controller.wxapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dao.InviteJoinMapper;
import com.xiaoyi.ssm.dao.InviteLogMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.model.InviteImage;
import com.xiaoyi.ssm.model.InviteJoin;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.service.InviteImageService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**
 * @Description: 微信小程序公共接口
 * @author 宋高俊
 * @date 2018年9月11日 下午4:22:23
 */
@Controller("wxappMemberController")
@RequestMapping("wxapp/member")
public class ApiMemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private InviteBallService inviteBallService;
	@Autowired
	private InviteImageService inviteImageService;
	@Autowired
	private InviteLogMapper inviteLogMapper;
	@Autowired
	private InviteJoinMapper inviteJoinMapper;

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
			List<InviteJoin> inviteJoins = inviteJoinMapper.selectByJoinMember(list.get(i).getId());
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
			List<InviteJoin> inviteJoins = inviteJoinMapper.selectByJoinMember(list.get(i).getId());
			List<String> joinList = new ArrayList<String>();
			for (int j = 0; j < inviteJoins.size(); j++) {
				joinList.add(inviteJoins.get(j).getMember().getAppavatarurl());
			}
			map.put("joinMember", joinList);// 已加入人员头像
			listmap.add(map);
		}
		return new ApiMessage(200, "登录成功", listmap);
	}

}
