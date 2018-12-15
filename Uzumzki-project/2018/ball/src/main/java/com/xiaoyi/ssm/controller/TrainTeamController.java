package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainEnter;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.model.TrainTeamFeedback;
import com.xiaoyi.ssm.model.TrainTeamLog;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainCourseService;
import com.xiaoyi.ssm.service.TrainEnterService;
import com.xiaoyi.ssm.service.TrainOrderCommentService;
import com.xiaoyi.ssm.service.TrainTeamCoachService;
import com.xiaoyi.ssm.service.TrainTeamFeedbackService;
import com.xiaoyi.ssm.service.TrainTeamLogService;
import com.xiaoyi.ssm.service.TrainTeamPhoneService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 培训机构控制器
 * @author 宋高俊
 * @date 2018年10月11日 下午8:10:17
 */
@Controller(value = "adminTrainTeamController")
@RequestMapping(value = "/admin/trainTeam")
public class TrainTeamController {

	@Autowired
	private TrainTeamService trainTeamService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private TrainCourseService trainCourseService;
	@Autowired
	private TrainTeamLogService trainTeamLogService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private CityService cityService;
	@Autowired
	private TrainTeamFeedbackService trainTeamFeedbackService;
	@Autowired
	private TrainTeamPhoneService trainTeamPhoneService;
	@Autowired
	private TrainOrderCommentService trainOrderCommentService;
	@Autowired
	private TrainEnterService trainEnterService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private TrainTeamCoachService trainTeamCoachService;
	@Autowired
	private PermissionService permissionService;

	/**
	 * @Description: 培训机构页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年10月11日 下午8:16:51
	 */
	@RequestMapping(value = "/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "23");
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute("btn"+list.get(i).getId(), "1");
		}
		return "admin/trainTeam/list";
	}

	/**
	 * @Description: 培训机构数据
	 * @author 宋高俊
	 * @param adminPage
	 * @return
	 * @date 2018年10月11日 下午8:17:04
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage, Integer selectType, String keyword) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<TrainTeam> list = trainTeamService.selectAllAdmin(selectType, keyword);
		PageInfo<TrainTeam> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainTeam trainTeam = list.get(i);
			City city = cityService.selectByPrimaryKey(trainTeam.getCityId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeam.getId());// ID
			map.put("modifyTime", DateUtil.getFormat(trainTeam.getModifyTime()));// ID
			map.put("city", city != null ? city.getCity() : "");// 城市
			map.put("lng", trainTeam.getLongitude());// 经度
			map.put("lat", trainTeam.getLatitude());// 纬度
			map.put("title", trainTeam.getTitle());// 机构
			map.put("typeFlag", trainTeam.getTypeFlag() == 1 ? "正常" : "禁用");// 评级
			map.put("phone", StringUtil.isBank(trainTeam.getPhone()) ? "否" : "是");// 是否有电话
			if (trainTeam.getLongitude() != null && trainTeam.getLatitude() != null) {
				map.put("lngAndLat", "是");// 是否有经纬
			}else {
				map.put("lngAndLat", "否");// 是否有经纬
			}
			
			map.put("level", trainTeam.getLevel());// 评级
			map.put("levelTime", DateUtil.getFormat(trainTeam.getLevelTime(), "yyyy-MM-dd"));// 当前评级
			map.put("trainCoachSum", trainTeamCoachService.selectByTrainTeamID(trainTeam.getId()).size());// 教练数量
			map.put("trainCourseSum", trainCourseService.countByTeam(trainTeam.getId()));// 课程数量
			
			// 获取两个月前的时间
			Date date = DateUtil.getPreTime(new Date(), 4, -2);
			int dayPhoneSum = trainTeamPhoneService.countByTeamId(trainTeam.getId(), date);
			int dayCommentSum = trainOrderCommentService.countByTeamId(trainTeam.getId(), date);
			map.put("dayPhoneSum", dayPhoneSum);// 电话接入数量(60天内总数)
			map.put("trainTeamFeedbackSum", trainTeamFeedbackService.countByTeam(trainTeam.getId()));// 线下报名反馈数量
			map.put("dayCommentSum", dayCommentSum);// 学员评价数量(60天内总数)
			map.put("trainTeamLogSum", trainTeamLogService.countByTeam(trainTeam.getId()));// 日志数量
			map.put("dayUseSum", Arith.div(dayPhoneSum, dayCommentSum, 2));// 平台依赖指数
			map.put("trainVenueSum", venueService.countByTeam(trainTeam.getId()));// 编外培训场地数量
			

			List<Venue> venueList = venueService.selectByManager(trainTeam.getId());
			map.put("venueSum", venueList.size());// 场馆数量
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 培训机构日志数据
	 * @author 宋高俊
	 * @param adminPage
	 * @return
	 * @date 2018年10月11日 下午8:17:04
	 */
	@RequestMapping(value = "/log/list")
	@ResponseBody
	public AdminMessage loglist(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<TrainTeamLog> list = trainTeamLogService.selectByTeam(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainTeamLog trainTeamLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeamLog.getId());// ID
			map.put("createTime", DateUtil.getFormat(trainTeamLog.getCreateTime()));// 时间
			map.put("content", trainTeamLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(0, listMap);
	}

	/**
	 * @Description: 待审核场地数据
	 * @author 宋高俊
	 * @param adminPage
	 * @return
	 * @date 2018年10月11日 下午8:17:04
	 */
	@RequestMapping(value = "/feedback/list")
	@ResponseBody
	public AdminMessage feedbacklist(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<TrainTeamFeedback> list = trainTeamFeedbackService.selectByTeam(id);
		PageInfo<TrainTeamFeedback> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainTeamFeedback trainTeamFeedback = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeamFeedback.getId());// ID
			map.put("createTime", DateUtil.getFormat(trainTeamFeedback.getCreateTime()));// 时间
			map.put("appnickname", trainTeamFeedback.getMember().getAppnickname());// 操作人
			map.put("content", trainTeamFeedback.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 机构认领入驻
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月17日 下午4:46:55 
	 */ 
	@RequestMapping(value = "/trainEnter/list")
	@ResponseBody
	public AdminMessage trainEnterList(HttpServletRequest request,AdminPage adminPage, Integer checkFlag) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<TrainEnter> list = trainEnterService.selectByEnterAll(checkFlag);
		PageInfo<TrainEnter> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainEnter trainEnter = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainEnter.getId());// ID
			map.put("lng", trainEnter.getLongitude());// 经度
			map.put("lat", trainEnter.getLatitude());// 纬度
			map.put("createTime", DateUtil.getFormat(trainEnter.getCreateTime()));// 申请时间
			map.put("appnickname", trainEnter.getMember().getAppnickname());// 申请人
			map.put("phone", trainEnter.getMember().getPhone());// 绑定
			map.put("title", trainEnter.getTitle());// 机构名
			map.put("address", trainEnter.getCityName());// 城市
			map.put("mainName", trainEnter.getMainName());// 负责人
			map.put("mainPhone", trainEnter.getMainPhone());// 负责人电话
			map.put("teachClass", trainEnter.getTeachClass());// 培训科目
			map.put("checkFlag", trainEnter.getCheckFlag() == 0 ? "待核" : trainEnter.getCheckFlag() == 1 ? "通过" : "无效");// 审核状态0=待审核1=审核通过2=审核拒绝
			map.put("rname", trainEnter.getStaff().getName());// 审核人 
			map.put("content", trainEnter.getContent());// 审核人 
			map.put("checkTime", DateUtil.getFormat(trainEnter.getCheckTime()));// 审核时间
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 审核机构入驻
	 * @author 宋高俊  
	 * @param request
	 * @param check
	 * @param content
	 * @param id
	 * @return 
	 * @date 2018年10月17日 下午8:44:16 
	 */ 
	@RequestMapping(value = "/trainEnter/check")
	@ResponseBody
	public ApiMessage trainEnterCheck(HttpServletRequest request,Integer check, String content, String id) {
		
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		TrainEnter trainEnter = trainEnterService.selectByPrimaryKey(id);
		City city = cityService.selectByName(trainEnter.getCityName());
		// 判断城市是否有录入
		if (city == null) {
			return new ApiMessage(400, "'"+trainEnter.getCityName()+"'城市不存在");
		}
		
		trainEnter.setContent(content);
		trainEnter.setCheckFlag(check);
		trainEnter.setCheckStaff(staff.getId());
		trainEnter.setCheckTime(new Date());
		// 申请人的数据
		Member member = memberService.selectByPrimaryKey(trainEnter.getMemberId());
		int flag = trainEnterService.updateByPrimaryKeySelective(trainEnter);
		if (flag > 0) {
			if (check == 1) {
				// 先查询这个人是否已经有过教练身份了 
				TrainCoach trainCoach = trainCoachService.selectByMember(trainEnter.getMemberId());
				if (trainCoach == null) {
					// 新建教练数据
					trainCoach = new TrainCoach();
					trainCoach.setId(Utils.getUUID());
					trainCoach.setCreateTime(new Date());
					trainCoach.setModifyTime(new Date());
					trainCoach.setMemberId(trainEnter.getMemberId());
					trainCoach.setName(member.getAppnickname());
					trainCoach.setHeadImage(member.getAppavatarurl());
					trainCoach.setSex(member.getAppgender());
					trainCoachService.insertSelective(trainCoach);
				}

				// 通过审核即创建培训机构
				TrainTeam trainTeam = new TrainTeam();
				trainTeam.setId(Utils.getUUID());
				trainTeam.setCreateTime(new Date());
				trainTeam.setModifyTime(new Date());
				trainTeam.setTitle(trainEnter.getTitle());
				trainTeam.setHeadImage(trainEnter.getHeadImage());
				trainTeam.setLongitude(trainEnter.getLongitude());
				trainTeam.setLatitude(trainEnter.getLatitude());
				trainTeam.setTeachClass(trainEnter.getTeachClass());
				trainTeam.setPhone(trainEnter.getMainPhone());
				trainTeam.setCityId(city.getId());
				trainTeam.setAddress(trainEnter.getAddress());
				trainTeam.setLevel(12);
				trainTeam.setLevelTime(new Date());
				trainTeam.setTrainCoachId(trainCoach.getId());
				trainTeamService.insertSelective(trainTeam);
				
				// 创建馆长身份
				TrainTeamCoach trainTeamCoach = new TrainTeamCoach();
				trainTeamCoach.setId(Utils.getUUID());
				trainTeamCoach.setManager(1);
				trainTeamCoach.setShowFlag(1);
				trainTeamCoach.setTeachType(1);
				trainTeamCoach.setTrainCoachId(trainCoach.getId());
				trainTeamCoach.setTrainTeamId(trainTeam.getId());
				trainTeamCoachService.insertSelective(trainTeamCoach);
			}
			return new ApiMessage(200, "审核成功");
		}
		return new ApiMessage(400, "审核失败");
	}
	
	/**  
	 * @Description: 修改培训机构数据页面
	 * @author 宋高俊  
	 * @param model
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午11:18:12 
	 */ 
	@RequestMapping(value = "/edit")
	public String edit(Model model, String id) {
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(id);
		
		model.addAttribute("id", trainTeam.getId());// ID
		model.addAttribute("trainTeamNo", trainTeam.getTrainTeamNo());// 编号
		model.addAttribute("cityid", trainTeam.getCityId());// 城市ID
		model.addAttribute("level", trainTeam.getLevel());// 评级
		model.addAttribute("title", trainTeam.getTitle());// 机构
		model.addAttribute("phone", trainTeam.getPhone());// 电话
		model.addAttribute("brandContent", trainTeam.getBrandContent());// 简介
		model.addAttribute("headImage", trainTeam.getHeadImage());// 封面
		model.addAttribute("lng", trainTeam.getLongitude());// 经度
		model.addAttribute("lat", trainTeam.getLatitude());// 维度
		model.addAttribute("typeFlag", trainTeam.getTypeFlag());// 维度

		List<City> list = cityService.selectByAll(null);
		model.addAttribute("citys", list);
		
		return "admin/trainTeam/edit";
	}
	
	/**
	 * @Description: 修改培训机构数据页面
	 * @author 宋高俊  
	 * @param model
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午11:18:12 
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public ApiMessage update(HttpServletRequest request, TrainTeam trainTeam) {
		
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		trainTeam.setModifyTime(new Date());
		trainTeamService.updateByPrimaryKeySelective(trainTeam);
		
		TrainTeamLog trainTeamLog = new TrainTeamLog();
		trainTeamLog.setId(Utils.getUUID());
		trainTeamLog.setCreateTime(new Date());
		trainTeamLog.setTrainTeamId(trainTeam.getId());
		trainTeamLog.setContent(staff.getName()+"在后台修改培训机构数据");
		trainTeamLogService.insertSelective(trainTeamLog);
		return ApiMessage.succeed();
	}	
	
	/**  
	 * @Description: 调整评级页面
	 * @author 宋高俊  
	 * @param model
	 * @param id
	 * @return 
	 * @date 2018年10月29日 下午8:08:52 
	 */ 
	@RequestMapping(value = "/updateLevel")
	public String updateLevel(Model model, String id) {
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(id);
		model.addAttribute("id", id);
		model.addAttribute("level", trainTeam.getLevel());
		return "admin/trainTeam/updateLevel";
	}
	
	/**  
	 * @Description: 调整评级页面
	 * @author 宋高俊  
	 * @param model
	 * @param id
	 * @return 
	 * @date 2018年10月29日 下午8:08:52 
	 */ 
	@RequestMapping(value = "/saveLevel")
	@ResponseBody
	public ApiMessage saveLevel(Integer level, String id) {
		TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(id);
		trainTeam.setLevel(level);
		trainTeam.setLevelTime(new Date());
		trainTeamService.updateByPrimaryKeySelective(trainTeam);
		return new ApiMessage(200, "修改成功");
	}
	
	/**
	 * @Description: 培训机构教练数据
	 * @author 宋高俊
	 * @param adminPage
	 * @return
	 * @date 2018年10月11日 下午8:17:04
	 */
	@RequestMapping(value = "/coach/list")
	@ResponseBody
	public AdminMessage coachList(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<TrainTeamCoach> list = trainTeamCoachService.selectByTrainTeamID(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			TrainTeamCoach trainTeamCoach = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachName", trainTeamCoach.getTrainCoach().getName());// 时间
			map.put("manager", trainTeamCoach.getManager() == 0 ? "外聘" : trainTeamCoach.getManager() == 1 ? "馆长" : "管理");// 内容
			map.put("memberNo", trainTeamCoach.getMember().getMemberno());// 用户编号
			
			TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(id);
			if (trainTeam.getTrainCoachId().equals(trainTeamCoach.getTrainCoach().getId())) {
				map.put("amountFlag", "是");// 回款人
			} else {
				map.put("amountFlag", "否");// 回款人
			}
			
			listMap.add(map);
		}
		return new AdminMessage(0, listMap);
	}
	
	/**
	 * @Description: 获取机构下属场馆
	 * @author 宋高俊
	 * @param trainTeamId
	 * @return
	 * @date 2018年12月13日上午9:23:36
	 */
	@RequestMapping(value = "/venue/list")
	@ResponseBody
	public AdminMessage venuelist(String trainTeamId) {
		List<Venue> list = venueService.selectByManager(trainTeamId);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Venue venue = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("venueNo", venue.getVenueno());// 编号
			map.put("venueName", venue.getName());// 场馆名称
			listMap.add(map);
		}
		return new AdminMessage(list.size(), listMap);
	}
}
