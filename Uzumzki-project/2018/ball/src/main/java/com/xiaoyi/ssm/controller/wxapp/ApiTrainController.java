package com.xiaoyi.ssm.controller.wxapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainCourse;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.model.TrainTeamImage;
import com.xiaoyi.ssm.quartz.EverydayGetBillJob;
import com.xiaoyi.ssm.quartz.EverydayPayVenueJob;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainCourseService;
import com.xiaoyi.ssm.service.TrainOrderCommentService;
import com.xiaoyi.ssm.service.TrainTeamCoachService;
import com.xiaoyi.ssm.service.TrainTeamImageService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.MapUtils;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

/**
 * @Description: 培训公共接口控制器
 * @author 宋高俊
 * @date 2018年9月29日 下午7:01:57
 */
@Controller("wxappTrainController")
@RequestMapping("wxapp/train/common")
public class ApiTrainController {
	
    private static Logger logger = Logger.getLogger(ApiTrainController.class.getName());

	@Autowired
	private TrainTeamService trainTeamService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private TrainCourseService trainCourseService;
	@Autowired
	private TrainTeamImageService trainTeamImageService;
	@Autowired
	private TrainOrderCommentService trainOrderCommentService;
	@Autowired
	private CityService cityService;
	@Autowired
	private TrainTeamCoachService trainTeamCoachService;

	@RequestMapping(value = "/oneDayPayVenueJob")
	@ResponseBody
	public ApiMessage oneDayPayVenueJob(String date) throws IOException {
		EverydayPayVenueJob oneDayPayVenueJob = new EverydayPayVenueJob();
		oneDayPayVenueJob.oneDayPayVenueJob();
		
//		EverydayGetBillJob everydayGetBillJob = new EverydayGetBillJob();
//		everydayGetBillJob.oneHourGetJob();
		
//		Date nowdate = new Date();
//		for (int i = 1; i <= 5; i++) {
//			String datestr = DateUtil.getFormat(DateUtil.getPreTime(nowdate, 3, -i), "yyyyMMdd");
//			WXPayUtil.downloadbill(datestr);
//		}
		
		return new ApiMessage(200);
	}
	
	
	/**  
	 * @Description: 获取短信上行消息
	 * @author 宋高俊  
	 * @return
	 * @date 2018年11月6日 上午10:15:31 
	 */ 
	@RequestMapping(value = "/receiveSms")
	@ResponseBody
	public Map<String, Object> receiveSms(HttpServletRequest request) {
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			logger.info("获取到上行消息:"+paraName + ": " + request.getParameter(paraName));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "接收成功");
		return map;
	}

	/**
	 * @Description: 获取培训机构列表数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月30日 上午11:13:14
	 */
	@RequestMapping(value = "/getTrainTeamList")
	@ResponseBody
	public ApiMessage getTrainTeamList(PageBean pageBean, HttpServletRequest request, Double lng, Double lat) {
		// 开始分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<TrainTeam> list = trainTeamService.selectByNearby(lng, lat);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
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
	 * @Description: 获取培训机构介绍数据
	 * @author 宋高俊
	 * @param request
	 * @param id
	 * @return
	 * @date 2018年9月30日 上午11:13:14
	 */
	@RequestMapping(value = "/trainTeam/details")
	@ResponseBody
	public ApiMessage trainTeamdetails(HttpServletRequest request, String id) {
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", trainTeam.getId()); // ID
		map.put("headImage", trainTeam.getHeadImage()); // 封面图片
		map.put("title", trainTeam.getTitle()); // 名称
		map.put("brandContent", trainTeam.getBrandContent()); // 品牌故事
		map.put("trait", trainTeam.getTrait().split(";"));// 办学特色

		List<String> list = new ArrayList<String>();
		List<TrainTeamImage> listTeamImages = trainTeamImageService.selectByTrainTeamID(id);
		for (int i = 0; i < listTeamImages.size(); i++) {
			list.add(listTeamImages.get(i).getPath());
		}
		map.put("trainImages", list); // 教学环境集合
		return new ApiMessage(200, "获取成功", map);
	}

	/**
	 * @Description: 获取培训机构全部教练数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月30日 上午11:13:14
	 */
	@RequestMapping(value = "/trainTeam/coach/list")
	@ResponseBody
	public ApiMessage trainTeamcoachlist(HttpServletRequest request, String id) {

		List<TrainTeamCoach> trainTeamCoachs = trainTeamCoachService.selectByTrainTeamID(id);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < trainTeamCoachs.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", trainTeamCoachs.get(i).getId()); // ID
			map.put("headImage", trainTeamCoachs.get(i).getTrainCoach().getHeadImage()); // 头像
			map.put("name", trainTeamCoachs.get(i).getTrainCoach().getName()); // 姓名
			map.put("type", trainTeamCoachs.get(i).getTeachType() == 1 ? "主教练" : trainTeamCoachs.get(i).getTeachType() == 2 ? "教练" : trainTeamCoachs.get(i).getTeachType() == 3 ? "助教" : "其他"); // 类型
			map.put("workingAge", trainTeamCoachs.get(i).getTrainCoach().getWorkingAge()); // 教龄
			listmap.add(map);
		}
		return new ApiMessage(200, "获取成功", listmap);
	}

	/**
	 * @Description: 获取培训机构教练详细数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月30日 上午11:13:14
	 */
	@RequestMapping(value = "/trainTeam/coach/details")
	@ResponseBody
	public ApiMessage trainTeamcoachdetails(HttpServletRequest request, String id) {

		TrainCoach trainCoach = trainCoachService.selectByPrimaryKey(id);
		if (trainCoach == null) {
			return new ApiMessage(400, "教练不存在");
		}
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", trainCoach.getId()); // ID
		map.put("headImage", trainCoach.getHeadImage()); // 头像
		map.put("name", trainCoach.getName()); // 姓名
		map.put("type", trainCoach.getTrainTeamCoach().getTeachType() == 1 ? "主教练" : trainCoach.getTrainTeamCoach().getTeachType() == 2 ? "教练" : trainCoach.getTrainTeamCoach().getTeachType() == 3 ? "助教" : "其他"); // 类型
		map.put("workingAge", trainCoach.getWorkingAge()); // 教龄
		map.put("lectureStyle", trainCoach.getLectureStyle()); // 讲课风格
		map.put("vitae", trainCoach.getVitae()); // 个人简介

		map.put("education", trainCoach.getEducation()); // 教育背景
		map.put("honor", trainCoach.getHonor()); // 相关经历

		List<TrainCourse> listTrainCoachs = trainCourseService.selectByTrainCoachID(id);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < listTrainCoachs.size(); i++) {
			Map<String, Object> courseMap = new LinkedHashMap<String, Object>();
			courseMap.put("id", listTrainCoachs.get(i).getId()); // ID
			courseMap.put("headImage", listTrainCoachs.get(i).getHeadImage()); // 封面
			courseMap.put("title", listTrainCoachs.get(i).getTitle()); // 标题
			courseMap.put("amount", listTrainCoachs.get(i).getAmount()); // 价格
			courseMap.put("minAge", listTrainCoachs.get(i).getMinAge() + "~" + listTrainCoachs.get(i).getMaxAge() + "岁"); // 适合年龄
			courseMap.put("classHour", listTrainCoachs.get(i).getClassHour() + "课时"); // 价格
			courseMap.put("toTeachNumber", listTrainCoachs.get(i).getToTeachNumber() + "人以下"); // 适合人数
			listmap.add(courseMap);
		}

		map.put("courses", listmap); // 开设课程
		return new ApiMessage(200, "获取成功", map);
	}
	
		
	/**
	 * @Description: 获取培训机构全部课程数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月30日 上午11:13:14
	 */
	@RequestMapping(value = "/trainTeam/course/list")
	@ResponseBody
	public ApiMessage trainTeamcourselist(HttpServletRequest request, String id) {

		List<TrainCourse> listTrainCoachs = trainCourseService.selectByTrainTeamID(id);
		List<Map<String, Object>> listmap1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listmap2 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listmap3 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listTrainCoachs.size(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", listTrainCoachs.get(i).getId()); // ID
			map.put("headImage", listTrainCoachs.get(i).getHeadImage()); // 封面
			map.put("title", listTrainCoachs.get(i).getTitle()); // 标题
			map.put("amount", listTrainCoachs.get(i).getAmount()); // 价格
			map.put("minAge", listTrainCoachs.get(i).getMinAge() + "~" + listTrainCoachs.get(i).getMaxAge() + "岁"); // 适合年龄
			map.put("classHour", listTrainCoachs.get(i).getClassHour() + "课时"); // 课时
			map.put("toTeachNumber", listTrainCoachs.get(i).getToTeachNumber() + "人以下"); // 适合人数
			
			if (listTrainCoachs.get(i).getBallType() == 1) {
				listmap1.add(map);
			}else if (listTrainCoachs.get(i).getBallType() == 2) {
				listmap2.add(map);
			}else if (listTrainCoachs.get(i).getBallType() == 3) {
				listmap3.add(map);
			}
		}
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(id);
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("ballHour", trainTeam.getBallHour1());
		map1.put("listMap", listmap1);
		Map<String, Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("ballHour", trainTeam.getBallHour2());
		map2.put("listMap", listmap2);
		Map<String, Object> map3 = new LinkedHashMap<String, Object>();
		map3.put("ballHour", trainTeam.getBallHour3());
		map3.put("listMap", listmap3);
		
		returnMap.put("ballHour1", map1);
		returnMap.put("ballHour2", map2);
		returnMap.put("ballHour3", map3);
		return new ApiMessage(200, "获取成功", returnMap);
	}

	/**  
	 * @Description: 根据经纬度获取培训机构
	 * @author 宋高俊  
	 * @param request
	 * @param begLng 开始经度
	 * @param endLng 结束经度
	 * @param begLat 开始纬度
	 * @param endLat 结束纬度
	 * @return 
	 * @date 2018年10月18日 下午6:00:40 
	 */ 
	@RequestMapping(value = "/trainTeam/getMapTrainTeam")
	@ResponseBody
	public ApiMessage getMapTrainTeam(HttpServletRequest request, double begLng, double endLng, double begLat, double endLat) {

		List<TrainTeam> list = trainTeamService.selectByNearbyMapTrainTeam(begLng, endLng, begLat, endLat);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainTeam trainTeam = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeam.getId()); // ID
			map.put("title", trainTeam.getTitle()); // 培训机构名称
			map.put("lng", trainTeam.getLongitude()); // 经度
			map.put("lat", trainTeam.getLatitude()); // 纬度
			listmap.add(map);
		}
		return new ApiMessage(200, "获取成功", listmap);
	}

	/**  
	 * @Description: 根据城市名称查询获取是否显示地图 
	 * @author 宋高俊  
	 * @param request
	 * @param name
	 * @return 
	 * @date 2018年10月18日 下午7:49:43 
	 */ 
	@RequestMapping(value = "/trainTeam/getCity")
	@ResponseBody
	public ApiMessage trainTeamGetCity(HttpServletRequest request, String name) {
		City city = cityService.selectByName(name);
		return new ApiMessage(200, "获取成功", city.getMapflag());
	}
	
	

}

