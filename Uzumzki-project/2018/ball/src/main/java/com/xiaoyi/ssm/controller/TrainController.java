package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
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
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainOrder;
import com.xiaoyi.ssm.model.TrainOrderLog;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainOrderLogService;
import com.xiaoyi.ssm.service.TrainOrderService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 培训控制器
 * @author 宋高俊
 * @date 2018年8月20日 上午11:20:56
 */
@Controller(value = "adminTrainController")
@RequestMapping(value = "/admin/train")
public class TrainController {

	@Autowired
	private TrainOrderService trainOrderService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private CityService cityService;
	@Autowired
	private TrainOrderLogService trainOrderLogService;
	@Autowired
	private TrainTeamService trainTeamService;
	@Autowired
	private PermissionService permissionService;

	/**
	 * @Description: 培训页面
	 * @author 宋高俊
	 * @date 2018年8月20日 上午11:20:56
	 */
	@RequestMapping(value = "/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "13");
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute("btn"+list.get(i).getId(), "1");
		}
		return "admin/train/list";
	}

	/**
	 * @Description: 培训数据
	 * @author 宋高俊
	 * @date 2018年8月20日 上午11:20:56
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<TrainOrder> trains = trainOrderService.selectAllAdmin();
		PageInfo<TrainOrder> pageInfo = new PageInfo<>(trains);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < trains.size(); i++) {
			// 培训订单数据
			TrainOrder trainOrder = trains.get(i);
			// 培训教练数据
			TrainCoach trainCoach = trainCoachService.selectByPrimaryKey(trainOrder.getTrainCourse().getTrainCoachId());
			// 培训机构数据
			TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(trainOrder.getTrainCourse().getTrainTeamId());
			// 培训机构城市数据
			City city = cityService.selectByPrimaryKey(trainTeam.getCityId());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainOrder.getId());// ID
			map.put("createTime", DateUtil.getFormat(trainOrder.getCreateTime()));// 下单时间
			map.put("courseTitle", trainOrder.getTrainCourse().getTitle());// 课程名
			map.put("coachName", trainCoach.getName());// 教练
			map.put("classHourSum", trainOrder.getClassHourSum());// 课时
			map.put("appnickname", trainOrder.getMember().getAppnickname());// 用户
			map.put("payType", trainOrder.getPayType() == 0 ? "待支付" : trainOrder.getPayType() == 1 ? "支付成功" : "支付取消");// 状态
			map.put("amount", trainOrder.getAmount());// 金额
			map.put("ballType", trainOrder.getTrainCourse().getBallType() == 1 ? "网球" : trainOrder.getTrainCourse().getBallType() == 2 ? "足球" : "羽毛球");// 业务
			map.put("city", city != null ? city.getCity() : "");// 城市
			map.put("trainTeam", trainTeam.getTitle());// 机构
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 培训日志数据
	 * @author 宋高俊  
	 * @param adminPage
	 * @return 
	 * @date 2018年10月30日 上午9:35:16 
	 */ 
	@RequestMapping(value = "/trainloglist")
	@ResponseBody
	public AdminMessage trainloglist(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<TrainOrderLog> trains = trainOrderLogService.selectByOrder(id);
		PageInfo<TrainOrderLog> pageInfo = new PageInfo<>(trains);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < trains.size(); i++) {
			TrainOrderLog trainOrderLog = trains.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainOrderLog.getId());// ID
			map.put("createTime", DateUtil.getFormat(trainOrderLog.getCreateTime()));// 时间
			map.put("type", trainOrderLog.getType() == 0 ? "系统" : "人工");// 类别
			map.put("content", trainOrderLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

}
