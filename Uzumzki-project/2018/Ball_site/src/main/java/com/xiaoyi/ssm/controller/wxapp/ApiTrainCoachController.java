package com.xiaoyi.ssm.controller.wxapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainCourseService;
import com.xiaoyi.ssm.service.TrainOrderService;
import com.xiaoyi.ssm.service.TrainTeamCoachService;
import com.xiaoyi.ssm.service.TrainTeamImageService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.TrainTrialService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

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
	@Autowired
	private TrainTeamCoachService trainTeamCoachService;
	
	/**  
	 * @Description: 根据场馆ID获取教练团队
	 * @author 宋高俊  
	 * @param request
	 * @param trainTeamID
	 * @return 
	 * @date 2018年10月24日 上午10:17:18 
	 */ 
	@RequestMapping(value = "/manager/getMyTrainCoach")
	@ResponseBody
	public ApiMessage getMyTrainCoach(HttpServletRequest request, String trainTeamID) {
		
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		List<TrainTeamCoach> list = trainTeamCoachService.selectByTrainTeamID(trainTeamID);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", list.get(i).getTrainCoach().getId()); // 教练ID
			map.put("name", list.get(i).getTrainCoach().getName()); // 教练名称
			map.put("type", list.get(i).getTeachType()); // 教学身份1=主教2=助教3=内勤
			map.put("manager", list.get(i).getManager()); // 教练所属机构身份0=普通教练1=创建人2=管理员
			map.put("showFlag", list.get(i).getShowFlag()); // 状态0=禁用1=正常
			listMaps.add(map);
		}
		return new ApiMessage(200, "获取成功", listMaps);
	}
	
	/**  
	 * @Description: 修改教练接口
	 * @author 宋高俊  
	 * @param request
	 * @param trainTeamID
	 * @param coachid
	 * @param manager
	 * @param type
	 * @param showFlag
	 * @return 
	 * @date 2018年11月3日 下午3:39:23 
	 */ 
	@RequestMapping(value = "/manager/updateTrainCoach")
	@ResponseBody
	public ApiMessage updateTrainCoach(HttpServletRequest request, String trainTeamId, String trainCoachId, Integer manager, Integer type, Integer showFlag, String name) {
		if (manager == 1) {
			return new ApiMessage(400, "修改错误");
		}
		
		TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByCoachTeam(trainCoachId, trainTeamId);
		trainTeamCoach.setManager(manager);
		trainTeamCoach.setTeachType(type);
		trainTeamCoach.setShowFlag(showFlag);
		int flag = trainTeamCoachService.updateByPrimaryKeySelective(trainTeamCoach);
		if (flag > 0) {
			TrainCoach trainCoach = new TrainCoach();
			trainCoach.setId(trainTeamCoach.getTrainCoachId());
			trainCoach.setName(name);
			trainCoach.setModifyTime(new Date());
			trainCoachService.updateByPrimaryKeySelective(trainCoach);
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 获取我的介绍
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月8日 下午2:48:07
	 */
	@RequestMapping(value = "/manager/getMyCoach")
	@ResponseBody
	public ApiMessage getMyCoach(HttpServletRequest request, String trainTeamId) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 根据用户ID查询教练数据
		TrainCoach trainCoach = trainCoachService.selectByMemberTeam(member.getId(), trainTeamId);
		Map<String , Object> map = new LinkedHashMap<String, Object>();
		map.put("id", trainCoach.getId()); // 教练ID
		map.put("headImage", trainCoach.getHeadImage()); // 头像
		map.put("name", trainCoach.getName()); // 名称
		map.put("sex", trainCoach.getSex()); // 性别
		map.put("education", trainCoach.getEducation()); // 教育背景
		map.put("workingAge", trainCoach.getWorkingAge()); // 教龄
		map.put("honor", trainCoach.getHonor()); // 相关经历
		map.put("lectureStyle", trainCoach.getLectureStyle()); // 讲课风格
		map.put("vitae", trainCoach.getVitae()); // 个人简介
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
		trainCoach.setModifyTime(new Date());
		int flag = trainCoachService.updateByPrimaryKeySelective(trainCoach);
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}
	

	/**  
	 * @Description: 获取我加入的培训机构列表
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月31日 下午4:11:27 
	 */ 
	@RequestMapping(value = "/getTrainTeam")
	@ResponseBody
	public ApiMessage getTrainTeam(HttpServletRequest request) {
		
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		List<TrainTeam> list = trainTeamService.selectByMember(member.getId());
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainTeam trainTeam = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeam.getId()); // ID
			map.put("headImage", trainTeam.getHeadImage()); // 封面图片
			map.put("title", trainTeam.getTitle()); // 培训机构名称
			listmap.add(map);
		}
		return new ApiMessage(200, "获取成功", listmap);
	}
	
	/**
	 * @Description: 发出邀请
	 * @author 宋高俊
	 * @param request
	 * @param name
	 * @param trainTeamId
	 * @param phone
	 * @return
	 * @date 2018年11月19日 上午11:05:03
	 */
	@RequestMapping(value = "/sendTrainTeam")
	@ResponseBody
	public ApiMessage sendTrainTeam(HttpServletRequest request, String trainTeamId, String phone, String manager) {
		RedisUtil.setRedis(trainTeamId + "_" + phone, manager, 24 * 60 * 60);
		return new ApiMessage(200, "发布成功");
	}
	
	/**
	 * @Description: 加入邀请的机构
	 * @author 宋高俊
	 * @param request
	 * @param name
	 * @param trainTeamId
	 * @param phone
	 * @return
	 * @date 2018年11月19日 上午11:05:03
	 */
	@RequestMapping(value = "/joinTrainTeam")
	@ResponseBody
	public ApiMessage joinTrainTeam(HttpServletRequest request, String name, String trainTeamId) {
		
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		String manager = RedisUtil.getRedis(trainTeamId + "_" + member.getPhone());
		if (StringUtil.isBank(manager)) {
			return new ApiMessage(400, "该邀请不存在或不是发送给您的");
		}
		
		// 验证完毕后删除缓存
		RedisUtil.delRedis(trainTeamId + "_" + member.getPhone());
		
		// 机构数据
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(trainTeamId);

		TrainCoach trainCoach = trainCoachService.selectByMember(member.getId());
		if (trainCoach == null) {
			// 新建教练数据
			trainCoach = new TrainCoach();
			trainCoach.setId(Utils.getUUID());
			trainCoach.setCreateTime(new Date());
			trainCoach.setModifyTime(new Date());
			trainCoach.setMemberId(member.getId());
			trainCoach.setName(name);
			trainCoach.setHeadImage(member.getAppavatarurl());
			trainCoach.setSex(member.getAppgender());
			trainCoachService.insertSelective(trainCoach);
		}else {
			trainCoach.setModifyTime(new Date());
			trainCoach.setName(name);
			trainCoachService.updateByPrimaryKeySelective(trainCoach);
		}
		// 添加教练身份
		TrainTeamCoach trainTeamCoach = new TrainTeamCoach();
		trainTeamCoach.setId(Utils.getUUID());
		trainTeamCoach.setManager(Integer.valueOf(manager));
		trainTeamCoach.setShowFlag(1);
		trainTeamCoach.setTeachType(1);
		trainTeamCoach.setTrainCoachId(trainCoach.getId());
		trainTeamCoach.setTrainTeamId(trainTeam.getId());
		trainTeamCoachService.insertSelective(trainTeamCoach);
		
		return new ApiMessage(200, "获取成功");
	}
}
