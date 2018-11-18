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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainCourse;
import com.xiaoyi.ssm.model.TrainEnter;
import com.xiaoyi.ssm.model.TrainOrderComment;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.model.TrainTeamCollect;
import com.xiaoyi.ssm.model.TrainTeamFeedback;
import com.xiaoyi.ssm.model.TrainTeamImage;
import com.xiaoyi.ssm.model.TrainTeamLog;
import com.xiaoyi.ssm.model.TrainTeamPhone;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueCheck;
import com.xiaoyi.ssm.model.VenueEnter;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainCourseService;
import com.xiaoyi.ssm.service.TrainEnterService;
import com.xiaoyi.ssm.service.TrainOrderCommentService;
import com.xiaoyi.ssm.service.TrainOrderService;
import com.xiaoyi.ssm.service.TrainTeamCoachService;
import com.xiaoyi.ssm.service.TrainTeamCollectService;
import com.xiaoyi.ssm.service.TrainTeamFeedbackService;
import com.xiaoyi.ssm.service.TrainTeamImageService;
import com.xiaoyi.ssm.service.TrainTeamLogService;
import com.xiaoyi.ssm.service.TrainTeamPhoneService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.TrainTrialService;
import com.xiaoyi.ssm.service.VenueCheckService;
import com.xiaoyi.ssm.service.VenueEnterService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MapUtils;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 培训机构接口控制器
 * @author 宋高俊
 * @date 2018年9月29日 下午7:01:57
 */
@Controller("wxappTrainTeamController")
@RequestMapping("wxapp/train/team")
public class ApiTrainTeamController {

	@Autowired
	private TrainTeamService trainTeamService;
	@Autowired
	private VenueService venueService;
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
	private TrainTeamFeedbackService trainTeamFeedbackService;
	@Autowired
	private TrainTeamPhoneService trainTeamPhoneService;
	@Autowired
	private VenueCheckService venueCheckService;
	@Autowired
	private TrainEnterService trainEnterService;
	@Autowired
	private VenueEnterService venueEnterService;
	@Autowired
	private TrainOrderCommentService trainOrderCommentService;
	@Autowired
	private TrainTeamCollectService trainTeamCollectService;
	@Autowired
	private TrainTeamLogService trainTeamLogService;
	@Autowired
	private TrainTeamCoachService trainTeamCoachService;

	/**
	 * @Description: 获取培训机构主页面数据
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月8日 下午2:48:07
	 */
	@RequestMapping(value = "/manager/getMyTrainTeam/details")
	@ResponseBody
	public ApiMessage getMyCoachDetails(HttpServletRequest request, String trainTeamId) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		// 培训机构当前教练数据
        TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByMemberTeam(member.getId(), trainTeamId);
        // 培训机构数据
        TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(trainTeamId);

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", trainTeam.getId()); // 机构ID
		map.put("headImage", trainTeam.getHeadImage()); // 机构封面
		map.put("title", trainTeam.getTitle()); // 机构名称
		map.put("name", trainTeamCoach.getTrainCoach().getName()); // 当前教练姓名
		map.put("type", trainTeamCoach.getTeachType());// 当前教练身份1=主教2助教
		map.put("manager", trainTeamCoach.getManager()); // 教练所属机构身份0=普通教练1=创建人2=管理员

		return new ApiMessage(200, "获取成功", map);
	}

	/**
	 * @Description: 获取机构设置
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月8日 下午2:48:07
	 */
	@RequestMapping(value = "/manager/getMyTrainTeam")
	@ResponseBody
	public ApiMessage getMyCoach(HttpServletRequest request, String trainTeamId) {

		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(trainTeamId);

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", trainTeam.getId()); // 机构ID
		map.put("title", trainTeam.getTitle()); // 机构名称
		map.put("headImage", trainTeam.getHeadImage()); // 封面图
		map.put("address", trainTeam.getAddress()); // 地址
		map.put("longitude", trainTeam.getLongitude()); // 经度
		map.put("latitude", trainTeam.getLatitude()); // 纬度
		map.put("phone", trainTeam.getPhone()); // 机构电话
		map.put("freeClasses", trainTeam.getFreeClasses()); // 免费试课
		map.put("freeParking", trainTeam.getFreeParking()); // 免费停车
		map.put("teachClass", trainTeam.getTeachClass()); // 开课科目
		map.put("brandContent", trainTeam.getBrandContent()); // 机构介绍
		map.put("titleFlag", trainTeam.getTitleFlag()); // 是否可以修改名称
		
		map.put("ballHour1", trainTeam.getBallHour1());
		map.put("ballHour2", trainTeam.getBallHour2());
		map.put("ballHour3", trainTeam.getBallHour3());

		List<String> images = new ArrayList<String>();
		List<TrainTeamImage> trainTeamImages = trainTeamImageService.selectByTrainTeamID(trainTeam.getId());
		for (int i = 0; i < trainTeamImages.size(); i++) {
			images.add(trainTeamImages.get(i).getPath());
		}
		map.put("images", images); // 机构风采
		return new ApiMessage(200, "获取成功", map);
	}

	/**
	 * @Description: 修改机构设置
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月8日 下午2:48:07
	 */
	@RequestMapping(value = "/manager/updateMyTrainTeam")
	@ResponseBody
	public ApiMessage updateMyCoach(HttpServletRequest request, TrainTeam trainTeam, String images) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 培训机构当前教练数据
		TrainCoach trainCoach = trainCoachService.selectByMemberTeam(member.getId(), trainTeam.getId());
        TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByCoachTeam(trainCoach.getId(), trainTeam.getId());
		
		if (trainTeamCoach.getManager() != 1 && trainTeamCoach.getManager() != 2) {
			return new ApiMessage(400, "权限不足");
		}
		trainTeam.setModifyTime(new Date());

		TrainTeam lodTrainTeam = trainTeamService.selectByPrimaryKey(trainTeam.getId());

		if (!lodTrainTeam.getTitle().equals(trainTeam.getTitle())) {
			if (lodTrainTeam.getTitleFlag() == 1) {
				trainTeam.setTitleFlag(0);
			} else {
				return new ApiMessage(400, "每月名称只可修改一次");
			}
		}

		int flag = trainTeamService.updateByPrimaryKeySelective(trainTeam);
		if (flag > 0) {
			trainTeamImageService.deleteByTeamAll(trainTeam.getId());
			String[] urls = images.split(";");
			for (int i = 0; i < urls.length; i++) {
				TrainTeamImage trainTeamImage = new TrainTeamImage();
				trainTeamImage.setId(Utils.getUUID());
				trainTeamImage.setCreateTime(new Date());
				trainTeamImage.setModifyTime(new Date());
				trainTeamImage.setPath(urls[i]);
				trainTeamImage.setTrainTeamId(trainTeam.getId());
				trainTeamImageService.insertSelective(trainTeamImage);
			}
			
			TrainTeamLog trainTeamLog = new TrainTeamLog();
			trainTeamLog.setId(Utils.getUUID());
			trainTeamLog.setCreateTime(new Date());
			trainTeamLog.setTrainTeamId(trainTeam.getId());
			trainTeamLog.setContent(member.getAppnickname()+"在客户端修改培训机构数据");
			trainTeamLogService.insertSelective(trainTeamLog);
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 获取所有的教学场地
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月10日 上午11:37:26
	 */
	@RequestMapping(value = "/manager/getMyTrainVenue")
	@ResponseBody
	public ApiMessage getMyTrainVenue(HttpServletRequest request, String trainTeamId) {

		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		// 可用的状态
		List<Venue> listVenue = venueService.selectByTrainTeamID(trainTeamId);
		for (int i = 0; i < listVenue.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", listVenue.get(i).getId()); // 场地ID
			map.put("headImage", listVenue.get(i).getImage()); // 封面图
			map.put("title", listVenue.get(i).getName()); // 场地名称
			map.put("address", listVenue.get(i).getAddress()); // 场地地址
			map.put("lnt", listVenue.get(i).getLongitude()); // 经度
			map.put("lat", listVenue.get(i).getLatitude()); // 纬度
			map.put("type", "1"); // 审核状态(0=审核中1=审核通过2=审核不通过)
			listMaps.add(map);
		}
		
		// 待审的状态
		List<VenueCheck> listVenueCheck = venueCheckService.selectByTrainTeamID(trainTeamId);
		for (int i = 0; i < listVenueCheck.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", listVenueCheck.get(i).getId()); // 场地ID
			map.put("headImage", listVenueCheck.get(i).getHeadImage()); // 封面图
			map.put("title", listVenueCheck.get(i).getTitle()); // 场地名称
			map.put("address", listVenueCheck.get(i).getAddress()); // 场地地址
			map.put("lnt", listVenueCheck.get(i).getLng()); // 经度
			map.put("lat", listVenueCheck.get(i).getLat()); // 纬度
			map.put("type", listVenueCheck.get(i).getCheckFlag()); // 审核状态(0=审核中1=审核通过2=审核不通过)
			listMaps.add(map);
		}
		
		return new ApiMessage(200, "获取成功", listMaps);
	}

	/**
	 * @Description: 根据场地名和经纬度获取教学场地
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月10日 上午11:37:26
	 */
	@RequestMapping(value = "/manager/searchMyTrainVenue")
	@ResponseBody
	public ApiMessage searchMyTrainVenue(HttpServletRequest request, String name, Double longitude, Double latitude) {
		// 用户数据
//		String token = (String) request.getAttribute("token");
//		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
//		// 根据用户ID查询教练数据
//		TrainCoach trainCoach = trainCoachService.selectByMemberId(member.getId());

		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		List<Venue> list = venueService.selectTrainVenue(name, longitude, latitude);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", list.get(i).getId()); // 场地ID
			map.put("title", list.get(i).getName()); // 场地名称
			map.put("address", list.get(i).getAddress()); // 场地地址
			map.put("lnt", list.get(i).getLongitude()); // 经度
			map.put("lat", list.get(i).getLatitude()); // 纬度
			listMaps.add(map);
		}
		return new ApiMessage(200, "获取成功", listMaps);
	}
	
	/**
	 * @Description: 根据ID删除教学场地
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月10日 上午11:37:26
	 */
	@RequestMapping(value = "/manager/deleteMyTrainVenue")
	@ResponseBody
	public ApiMessage deleteMyTrainVenue(HttpServletRequest request, String id, int type, String trainTeamId) {
		if (type == 1) {
			// 根据培训机构ID删除场地
			int flag = venueService.deleteByTeamVenue(trainTeamId, id);
			if (flag > 0) {
				TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(trainTeamId);
				trainTeam.setVenueNumber(trainTeam.getVenueNumber() - 1);
				trainTeamService.updateByPrimaryKeySelective(trainTeam);
				return new ApiMessage(200, "删除成功");
			}
		}else {
			int flag = venueCheckService.deleteByPrimaryKey(id);
			if (flag > 0) {
				return new ApiMessage(200, "删除成功");
			}
		}
		return new ApiMessage(400, "删除失败");
		
	}

	/**
	 * @Description: 保存教学场地
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月10日 下午2:43:48
	 */
	@RequestMapping(value = "/manager/saveMyTrainVenue")
	@ResponseBody
	public ApiMessage saveMyTrainVenue(HttpServletRequest request, String title, String address, double longitude, 
			double latitude, String headImage, String phone, String content, Integer type, String trainTeamId) {
		// 根据用户ID查询教练数据
		VenueCheck vc = new VenueCheck();
		vc.setId(Utils.getUUID());
		vc.setCreateTime(new Date());
		vc.setModifyTime(new Date());
		vc.setTitle(title);
		vc.setBallType(type);
		vc.setPhone(phone);
		vc.setContent(content);
		vc.setLng(longitude);
		vc.setLat(latitude);
		vc.setAddress(address);
		vc.setTrainTeamId(trainTeamId);
		vc.setCheckFlag(0);
		
		int flag = venueCheckService.insertSelective(vc);
		if (flag > 0) {
//			// 增加培训机构使用场地
//			TrainTeamVenue trainTeamVenue = new TrainTeamVenue();
//			trainTeamVenue.setId(Utils.getUUID());
//			trainTeamVenue.setTrainTeamId(trainCoach.getTrainTeamId());
//			trainTeamVenue.setTrainVenueId(vc.getId());
//			venueService.saveTeamVenue(trainTeamVenue);

//			// 修改培训机构使用场地数量
//			TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(trainCoach.getTrainTeamId());
//			trainTeam.setVenueNumber(trainTeam.getVenueNumber() + 1);
//			trainTeamService.updateByPrimaryKeySelective(trainTeam);
			return new ApiMessage(200, "添加成功");
		} else {
			return new ApiMessage(400, "添加失败");
		}
	}

	/**
	 * @Description: 反馈线下报名
	 * @author 宋高俊
	 * @param request
	 * @param content
	 * @return
	 * @date 2018年10月12日 上午10:47:38
	 */
	@RequestMapping(value = "/manager/saveTrainTeamFeedback")
	@ResponseBody
	public ApiMessage saveTrainTeamFeedback(HttpServletRequest request, String content, String id) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		TrainTeamFeedback trainTeamFeedback = new TrainTeamFeedback();
		trainTeamFeedback.setId(Utils.getUUID());
		trainTeamFeedback.setContent(content);
		trainTeamFeedback.setCreateTime(new Date());
		trainTeamFeedback.setMemberId(member.getId());
		trainTeamFeedback.setTrainTeamId(id);
		
		int flag = trainTeamFeedbackService.insertSelective(trainTeamFeedback);
		if (flag > 0) {
			return new ApiMessage(200, "添加成功");
		} else {
			return new ApiMessage(400, "添加失败");
		}
	}
	
	/**  
	 * @Description: 统计拨打电话次数
	 * @author 宋高俊  
	 * @param request
	 * @param id
	 * @return 
	 * @date 2018年10月15日 上午11:35:27 
	 */ 
	@RequestMapping(value = "/manager/countTrainTeamPhone")
	@ResponseBody
	public ApiMessage countTrainTeamPhone(HttpServletRequest request, String id) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		TrainTeamPhone trainTeamPhone = new TrainTeamPhone();
		trainTeamPhone.setId(Utils.getUUID());
		trainTeamPhone.setCreateTime(new Date());
		trainTeamPhone.setMemberId(member.getId());
		trainTeamPhone.setTrainTeamId(id);

		int flag = trainTeamPhoneService.insertSelective(trainTeamPhone);
		if (flag > 0) {
			return new ApiMessage(200, "添加成功");
		} else {
			return new ApiMessage(400, "添加失败");
		}
	}
	
	/**  
	 * @Description: 培训机构入驻申请接口
	 * @author 宋高俊  
	 * @param request
	 * @param trainEnter
	 * @return 
	 * @date 2018年10月17日 下午3:36:38 
	 */ 
	@RequestMapping(value = "/addTrainEnter")
	@ResponseBody
	public ApiMessage addTrainEnter(HttpServletRequest request, TrainEnter trainEnter) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		trainEnter.setId(Utils.getUUID());
		trainEnter.setCreateTime(new Date());
		trainEnter.setMemberId(member.getId());
		trainEnter.setCheckFlag(0);

		int flag = trainEnterService.insertSelective(trainEnter);
		if (flag > 0) {
			return new ApiMessage(200, "添加成功");
		} else {
			return new ApiMessage(400, "添加失败");
		}
	}
	
	/**  
	 * @Description: 场馆入驻申请接口
	 * @author 宋高俊  
	 * @param request
	 * @return
	 * @date 2018年10月17日 下午3:36:38 
	 */ 
	@RequestMapping(value = "/addVenueEnter")
	@ResponseBody
	public ApiMessage addVenueEnter(HttpServletRequest request, VenueEnter venueEnter) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		venueEnter.setId(Utils.getUUID());
		venueEnter.setCreateTime(new Date());
		venueEnter.setMemberId(member.getId());
		venueEnter.setCheckFlag(0);

		int flag = venueEnterService.insertSelective(venueEnter);
		if (flag > 0) {
			return new ApiMessage(200, "添加成功");
		} else {
			return new ApiMessage(400, "添加失败");
		}
	}
	
	/**  
	 * @Description: 获取初始化参数
	 * @author 宋高俊  
	 * @return
	 * @date 2018年10月18日 下午2:58:34
	 */ 
	@RequestMapping(value = "/getVenueEnter")
	@ResponseBody
	public ApiMessage getVenueEnter(HttpServletRequest request) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		List<TrainTeam> list = trainTeamService.selectByMemberManager(member.getId(), 1);
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", list.get(i).getId()); // 机构ID
			map.put("title", list.get(i).getTitle()); // 机构名称
			listMaps.add(map);
		}
		return new ApiMessage(200, "获取成功", listMaps);
	}
	
	/**  
	 * @Description: 根据名称模糊查询场馆
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年10月18日 下午2:58:34 
	 */ 
	@RequestMapping(value = "/likeVenueName")
	@ResponseBody
	public ApiMessage likeVenueName(String name) {
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		List<Venue> list = venueService.selectByName(name);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", list.get(i).getId()); // 场地ID
			map.put("name", list.get(i).getName()); // 场地名称
			map.put("address", list.get(i).getAddress()); // 场地地址
			map.put("contactPhone", list.get(i).getContactPhone()); // 场地电话
			listMaps.add(map);
		}
		return new ApiMessage(200, "获取成功", listMaps);
	}
	
	/**  
	 * @Description: 根据场馆查询驻场的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月18日 下午3:49:46 
	 */ 
	@RequestMapping(value = "/enterTrainTeam")
	@ResponseBody
	public ApiMessage enterTrainTeam(String id, Double lng, Double lat) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<TrainTeam> list =trainTeamService.selectByVenue(id);
		for (int i = 0; i < list.size(); i++) {
			TrainTeam trainTeam = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeam.getId()); // ID
			map.put("headImage", trainTeam.getHeadImage()); // 封面图片
			map.put("title", trainTeam.getTitle()); // 培训机构名称
			map.put("venueNumber", trainTeam.getVenueNumber()); // 开课球馆数量
			map.put("area", (int) MapUtils.getDistance(lng, lat, trainTeam.getLongitude(), trainTeam.getLatitude())); // 距离
			map.put("freeParking", trainTeam.getFreeParking()); // 是否能免费停车
			map.put("freeClasses", trainTeam.getFreeClasses()); // 是否能免费试课
			map.put("teachClass", trainTeam.getTeachClass()); // 开设科目
			map.put("trainOrderCommentSum", trainOrderCommentService.countByTeamId(trainTeam.getId(), DateUtil.getPreTime(new Date(), 4, -2))); // 近期上课人数

			map.put("minAmount", trainTeam.getMinAmount().intValue()); // 最低价格
			listmap.add(map);
		}
		return new ApiMessage(200, "获取成功", listmap);
	}
	
	/**  
	 * @Description: 场馆附近的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 上午10:36:15 
	 */ 
	@RequestMapping(value = "/nearbyTrainTeam")
	@ResponseBody
	public ApiMessage nearbyTrainTeam(String id, int maxArea) {
		
		Venue venue = venueService.selectByPrimaryKey(id);
		double offset = 0.5;
		double lng1 = Arith.sub(venue.getLongitude(), offset);
		double lng2 = Arith.add(venue.getLongitude(), offset);
		double lat1 = Arith.sub(venue.getLatitude(), offset);
		double lat2 = Arith.add(venue.getLatitude(), offset);
		
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<TrainTeam> list =trainTeamService.selectByNearbyMapTrainTeam(lng1, lng2, lat1, lat2);
		for (int i = 0; i < list.size(); i++) {
			TrainTeam trainTeam = list.get(i);
			int area = (int) MapUtils.getDistance(venue.getLongitude(), venue.getLatitude(), trainTeam.getLongitude(), trainTeam.getLatitude());
			if (area > maxArea) {
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeam.getId()); // ID
			map.put("headImage", trainTeam.getHeadImage()); // 封面图片
			map.put("title", trainTeam.getTitle()); // 培训机构名称
			map.put("venueNumber", trainTeam.getVenueNumber()); // 开课球馆数量
			map.put("area", area); // 距离
			map.put("freeParking", trainTeam.getFreeParking()); // 是否能免费停车
			map.put("freeClasses", trainTeam.getFreeClasses()); // 是否能免费试课
			map.put("teachClass", trainTeam.getTeachClass()); // 开设科目
			
			map.put("trainOrderCommentSum", trainOrderCommentService.countByTeamId(trainTeam.getId(), DateUtil.getPreTime(new Date(), 4, -2))); // 近期上课人数
			map.put("minAmount", trainTeam.getMinAmount().intValue()); // 最低价格
			listmap.add(map);
		}
		return new ApiMessage(200, "获取成功", listmap);
	}
	
	/**  
	 * @Description: 收藏/取消收藏培训机构
	 * @author 宋高俊  
	 * @param collectFlag 1 = 收藏  0 = 取消收藏
	 * @return 
	 * @date 2018年10月23日 下午8:00:33 
	 */ 
	@RequestMapping(value = "/collectTrainTeam")
	@ResponseBody
	public ApiMessage collectTrainTeam(String id, Integer collectFlag, HttpServletRequest request) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		if (collectFlag == 1) {
			TrainTeamCollect ttc = new TrainTeamCollect();
			ttc.setId(Utils.getUUID());
			ttc.setCreateTime(new Date());
			ttc.setTrainTeamId(id);
			ttc.setMemberId(member.getId());
			trainTeamCollectService.insertSelective(ttc);
		} else {
			trainTeamCollectService.deleteByIdAndMember(id, member.getId());
		}
		return new ApiMessage(200, "操作成功");
	}
	
	/**  
	 * @Description: 查询我收藏的培训机构
	 * @author 宋高俊  
	 * @param request
	 * @return 
	 * @date 2018年10月24日 上午9:08:14 
	 */ 
	@RequestMapping(value = "/getTrainTeamCollect")
	@ResponseBody
	public ApiMessage getTrainTeamCollect(HttpServletRequest request) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<TrainTeam> list =trainTeamService.selectByCollect(member.getId());
		
		for (int i = 0; i < list.size(); i++) {
			TrainTeam trainTeam = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeam.getId()); // ID
			map.put("headImage", trainTeam.getHeadImage()); // 封面图片
			map.put("title", trainTeam.getTitle()); // 培训机构名称
			map.put("freeParking", trainTeam.getFreeParking()); // 是否能免费停车
			map.put("freeClasses", trainTeam.getFreeClasses()); // 是否能免费试课
			map.put("teachClass", trainTeam.getTeachClass()); // 开设科目
			map.put("trainOrderCommentSum", trainOrderCommentService.countByTeamId(trainTeam.getId(), DateUtil.getPreTime(new Date(), 4, -2))); // 近期上课人数
			listmap.add(map);
		}
		return new ApiMessage(200, "获取成功", listmap);
	}
	
	/**
	 * @Description: 获取培训机构课程详细数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月30日 上午11:13:14
	 */
	@RequestMapping(value = "/trainTeam/course/details")
	@ResponseBody
	public ApiMessage trainTeamcoursedetails(HttpServletRequest request, String id) {

        // 用户数据
        String token = (String) request.getAttribute("token");
        Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
        
		TrainCourse trainCourse = trainCourseService.selectByPrimaryKey(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", trainCourse.getId()); // ID
		map.put("headImage", trainCourse.getHeadImage()); // 封面
		map.put("title", trainCourse.getTitle()); // 标题
		map.put("amount", trainCourse.getAmount()); // 价格
		map.put("minAge", trainCourse.getMinAge() + "~" + trainCourse.getMaxAge() + "岁"); // 适合年龄
		map.put("classHour", trainCourse.getClassHour() + "课时"); // 价格
		map.put("toTeachNumber", trainCourse.getToTeachNumber() + "人以下"); // 适合人数
		
		map.put("toTeachTime", trainCourse.getToTeachTime()); // 上课时间
		map.put("classTrait", trainCourse.getClassTrait().split(";")); // 课程亮点
		
		TrainCoach trainCoach = trainCoachService.selectByPrimaryKey(trainCourse.getTrainCoachId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", trainCoach.getId()); // ID
		jsonObject.put("headImage", trainCoach.getHeadImage()); // 头像
		jsonObject.put("name", trainCoach.getName()); // 姓名
		TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByCoachTeam(trainCoach.getId(), trainCourse.getTrainTeamId());
		// 教练身份
		jsonObject.put("type", trainTeamCoach.getTeachType() == 1 ? "主教" : "助教"); // 类型
		jsonObject.put("workingAge", trainCoach.getWorkingAge()); // 教龄
		map.put("trainCoach", jsonObject); // 授课教练

		map.put("payMake", trainCourse.getPayMake()); // 预约信息
		map.put("payAdjust", trainCourse.getPayAdjust()); // 调课说明
		map.put("payRefund", trainCourse.getPayRefund()); // 退款说明
		map.put("applyFlag", trainCourse.getApplyFlag()); // 是否允许报名
		
		TrainCourse tc = trainCourseService.selectByMember(id, member.getId());
		if (tc != null) {
			map.put("collectFlag", 1); // 是否收藏
		}else {
			map.put("collectFlag", 0); // 是否收藏
		}
		
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**
	 * @Description: 获取培训机构详情数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月30日 上午11:13:14
	 */
	@RequestMapping(value = "/trainTeam/details")
	@ResponseBody
	public ApiMessage trainTeamdetails(HttpServletRequest request, String id, Double lng, Double lat) {

        // 用户数据
        String token = (String) request.getAttribute("token");
        Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
        
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", trainTeam.getId()); // ID
		map.put("headImage", trainTeam.getHeadImage()); // 封面图片
		map.put("title", trainTeam.getTitle()); // 名称
		map.put("phone", trainTeam.getPhone()); // 电话
		map.put("brandContent", trainTeam.getBrandContent()); // 介绍

		JSONArray venueJsonArray = new JSONArray();
		List<Venue> listVenues = venueService.selectByTrainTeamID(id);
		for (int i = 0; i < listVenues.size(); i++) {
			Venue venue = listVenues.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("lng", venue.getLongitude()); // 经度
			jsonObject.put("lat", venue.getLatitude()); // 纬度
			jsonObject.put("title", venue.getName()); // 场馆名
			jsonObject.put("area", (int) MapUtils.getDistance(lng, lat, trainTeam.getLongitude(), trainTeam.getLatitude())); // 距离
			jsonObject.put("address", venue.getAddress()); // 详细地址
			venueJsonArray.add(jsonObject);
		}
		map.put("trainVenues", venueJsonArray); // 地址集合

		JSONArray coachJsonArray = new JSONArray();
		List<TrainCoach> listCoachs = trainCoachService.selectByTrainTeamID(id);
		for (int i = 0; i < listCoachs.size(); i++) {
			TrainCoach trainCoach = listCoachs.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", trainCoach.getId()); // id
			jsonObject.put("headImage", trainCoach.getHeadImage()); // 头像
			jsonObject.put("name", trainCoach.getName()); // 姓名
			jsonObject.put("workingAge", trainCoach.getWorkingAge()); // 教龄
			coachJsonArray.add(jsonObject);
		}
		map.put("trainCoachs", coachJsonArray); // 教练集合

//		JSONArray classJsonArray = new JSONArray();
//		List<TrainCourse> listCourses = trainCourseService.selectByTrainTeamID(id);
//		for (int i = 0; i < listCourses.size(); i++) {
//			TrainCourse trainCourse = listCourses.get(i);
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("id", trainCourse.getId()); // ID
//			jsonObject.put("headImage", trainCourse.getHeadImage()); // 封面
//			jsonObject.put("title", trainCourse.getTitle()); // 标题
//			jsonObject.put("amount", trainCourse.getAmount()); // 价格
//			jsonObject.put("minAge", trainCourse.getMinAge() + "~" + trainCourse.getMaxAge() + "岁"); // 适合年龄
//			jsonObject.put("classHour", trainCourse.getClassHour() + "课时"); // 价格
//			jsonObject.put("toTeachNumber", trainCourse.getToTeachNumber() + "人以下"); // 价格
//			classJsonArray.add(jsonObject);
//		}
//		map.put("trainCourses", classJsonArray); // 课程集合

		TrainTeamCollect ttc = trainTeamCollectService.selectByMember(id, member.getId());
		if (ttc != null) {
			map.put("collectFlag", 1); // 是否收藏
		}else {
			map.put("collectFlag", 0); // 是否收藏
		}
		
		// 总评价数
		map.put("commentSum", trainOrderCommentService.countByTeamAll(id));
		// 近期10条评价
		List<TrainOrderComment> list = trainOrderCommentService.selectByTeamTen(id);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> commentMap = new HashMap<String, Object>();
			commentMap.put("id", list.get(i).getId()); // ID 
			commentMap.put("createTime", DateUtil.getFormat(list.get(i).getCreateTime())); // 时间
			commentMap.put("title", list.get(i).getTrainCourse().getTitle()); // 课程名称
			commentMap.put("classHour", list.get(i).getClassHour()); // 课时
			commentMap.put("appnickname", list.get(i).getMember().getAppnickname()); // 用户昵称
			commentMap.put("content", list.get(i).getContent()); // 评价内容
			commentMap.put("appavatarurl", list.get(i).getMember().getAppavatarurl()); // 用户头像
			commentMap.put("headImage", list.get(i).getTrainCoach().getHeadImage()); // 教练头像
			commentMap.put("commentSelect", list.get(i).getCommentSelect()); // 评价选择(1=好评2=中评3=差评4=拒绝评价)
			maps.add(commentMap);
		}
		map.put("commentTen", maps);
		
		return new ApiMessage(200, "获取成功", map);
	}

	/**  
	 * @Description: 机构管理数据
	 * @author 宋高俊  
	 * @param request
	 * @param trainTeamId
	 * @return 
	 * @date 2018年11月5日 下午3:52:39 
	 */ 
	@RequestMapping(value = "/manager/getTrainTeamManager")
	@ResponseBody
	public ApiMessage getTrainTeamManager(HttpServletRequest request, String trainTeamId) {
		
        // 用户数据
        String token = (String) request.getAttribute("token");
        Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
        
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(trainTeamId);
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 可用的状态
		List<Venue> listVenue = venueService.selectByTrainTeamID(trainTeamId);
		// 待审的状态
		List<VenueCheck> listVenueCheck = venueCheckService.selectByTrainTeamID(trainTeamId);

		List<TrainTeamCoach> list = trainTeamCoachService.selectByTrainTeamID(trainTeamId);
		int trainVenue = listVenue.size() + listVenueCheck.size();
		map.put("trainVenueSum", trainVenue);// 教学场地数量
		map.put("trainCoachSum", list.size());// 教练团队数量
		
		TrainCoach trainCoach = trainCoachService.selectByPrimaryKey(trainTeam.getTrainCoachId());
		
		map.put("coachId", trainCoach.getId());// 回款ID 
		map.put("account", trainCoach.getName());// 回款账号

		TrainCoach trainCoachManager = trainCoachService.selectByTeamManager(trainTeamId);

		map.put("trainCoachManagerId", trainCoachManager.getId());// 店长ID
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**  
	 * @Description: 设置机构回款账户
	 * @author 宋高俊  
	 * @param request
	 * @param trainTeamId
	 * @return 
	 * @date 2018年11月6日 下午3:29:38 
	 */ 
	@RequestMapping(value = "/manager/setTrainCoach")
	@ResponseBody
	public ApiMessage setTrainCoach(HttpServletRequest request, String trainTeamId, String trainCoachId) {
		
        // 用户数据
        String token = (String) request.getAttribute("token");
        Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
        
        TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByMemberTeam(member.getId(), trainTeamId);
        if (trainTeamCoach.getManager() == 1) {
			TrainTeam trainTeam = new TrainTeam();
			trainTeam.setId(trainTeamId);
			trainTeam.setTrainCoachId(trainCoachId);
			trainTeamService.updateByPrimaryKeySelective(trainTeam);
			return new ApiMessage(200, "修改成功");
		}else {
			return new ApiMessage(400, "当前用户权限不足");
		}
	}

	/**
	 * @Description: 转让店长接口
	 * @author 宋高俊
	 * @param request
	 * @return
	 * @date 2018年10月18日 下午7:49:43
	 */
	@RequestMapping(value = "/trainTeam/updateManager")
	@ResponseBody
	public ApiMessage updateManager(HttpServletRequest request, String trainTeamId, String trainCoachId) {
		// 用户数据
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 查询当前身份是否是店长
		TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByMemberTeam(member.getId(), trainTeamId);
		if (trainTeamCoach != null) {
			if (trainTeamCoach.getManager() == 1) {
				TrainTeamCoach newTrainTeamCoach = trainTeamCoachService.selectByCoachTeam(trainCoachId,trainTeamId);
				if (newTrainTeamCoach != null) {
					trainTeamCoach.setManager(2);
					trainTeamCoachService.updateByPrimaryKeySelective(trainTeamCoach);
					newTrainTeamCoach.setManager(1);
					trainTeamCoachService.updateByPrimaryKeySelective(newTrainTeamCoach);
					return new ApiMessage(200, "修改成功");
				}
			}
		}
		return new ApiMessage(400, "权限不足");
	}
	
	@RequestMapping(value = "/managerFlag")
	@ResponseBody
	public ApiMessage managerFlag(HttpServletRequest request) {
		return new ApiMessage(400, "权限不足");
	}

}
