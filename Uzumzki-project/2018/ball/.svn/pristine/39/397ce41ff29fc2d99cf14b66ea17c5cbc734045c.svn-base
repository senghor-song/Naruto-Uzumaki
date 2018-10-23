package com.xiaoyi.ssm.controller.wxapp;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainCourseService;
import com.xiaoyi.ssm.service.TrainOrderService;
import com.xiaoyi.ssm.service.TrainTeamImageService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.TrainTrialService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**
 * @Description: 教练数据接口控制器
 * @author 宋高俊
 * @date 2018年9月29日 下午7:01:57
 */
@Controller("wxappTrainCoachController")
@RequestMapping("wxapp/train/coach")
public class ApiTrainCoachController {

	@Autowired
	private TrainTeamService trainTeamService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private TrainCourseService trainCourseService;
	@Autowired
	private TrainTeamImageService trainTeamImageService;
	@Autowired
	private TrainOrderService trainOrderService;
	@Autowired
	private TrainTrialService trainTrialService;

	/**
	 * @Description: 获取我的介绍
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月8日 下午2:48:07
	 */
	@RequestMapping(value = "/manager/getMyCoach")
	@ResponseBody
	public ApiMessage getMyCoach(HttpServletRequest request) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 根据用户ID查询教练数据
		TrainCoach trainCoach = trainCoachService.selectByMemberId(member.getId());
		
		Map<String , Object> map = new LinkedHashMap<String, Object>();
		map.put("education", trainCoach.getEducation()); // 教育背景
		map.put("sex", trainCoach.getSex()); // 性别
		map.put("workingAge", trainCoach.getWorkingAge()); // 教龄
		map.put("honor", trainCoach.getHonor()); // 相关经历
		map.put("lectureStyle", trainCoach.getLectureStyle()); // 讲课风格
		map.put("vitae", trainCoach.getVitae()); // 个人简介
		map.put("headImage", trainCoach.getHeadImage()); // 头像
		map.put("teachClass", trainCoach.getTeachClass()); // 所授课程
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**
	 * @Description: 修改我的介绍
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月8日 下午2:48:07
	 */
	@RequestMapping(value = "/manager/updateMyCoach")
	@ResponseBody
	public ApiMessage updateMyCoach(HttpServletRequest request, TrainCoach trainCoach) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 根据用户ID查询教练数据
		TrainCoach oldTrainCoach = trainCoachService.selectByMemberId(member.getId());
		
		trainCoach.setId(oldTrainCoach.getId());
		trainCoach.setModifyTime(new Date());
		
		int flag = trainCoachService.updateByPrimaryKeySelective(trainCoach);
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}
}
