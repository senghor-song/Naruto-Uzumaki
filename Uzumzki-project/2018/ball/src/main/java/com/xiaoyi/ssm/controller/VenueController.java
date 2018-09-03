package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.service.CoachService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.ManagerService;
import com.xiaoyi.ssm.service.TrainService;
import com.xiaoyi.ssm.service.VenueLogService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.DateUtil;

/**  
 * @Description: 场馆控制器
 * @author 宋高俊  
 * @date 2018年8月18日 上午11:35:49 
 */ 
@Controller(value = "adminVenueController")
@RequestMapping(value = "/admin/venue")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private CoachService coachService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private VenueTemplateService venueTemplateService;
	@Autowired
	private VenueLogService venueLogService;
	@Autowired
	private TrainService trainService;
	
	/**  
	 * @Description: 场馆页面
	 * @author song  
	 * @date 2018年8月14日 下午7:02:41 
	 */ 
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/venue/list";
	}

	/**  
	 * @Description: 场馆数据
	 * @author song  
	 * @date 2018年8月14日 下午7:04:09 
	 */ 
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Venue> venues = venueService.selectByAll(null);
		PageInfo<Venue> pageInfo = new PageInfo<>(venues);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < venues.size(); i++) {
			Venue venue = venues.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venue.getId());// ID
			map.put("city", venue.getCityT().getCity());// 城市
			map.put("district", venue.getDistrictT().getDistrict());// 区县
			map.put("area", venue.getAreaT().getArea());// 街道
			map.put("name", venue.getName());// 场馆
			map.put("fieldSum", fieldService.countByVenue(venue.getId()));// 场地
			map.put("coachSum", coachService.countByVenue(venue.getId()));// 教练
			map.put("managerSum", managerService.countByVenue(venue.getId()));// 管理员
			map.put("venueTemplateSum", venueTemplateService.countByVenue(venue.getId()));// 模板
			map.put("venuelogSum", venueLogService.countByVenue(venue.getId()));// 日志
			map.put("amount", venue.getAmount());// 累计金额
			map.put("balance", venue.getBalance());// 存余金额
			map.put("freezeamount", venue.getFreezeamount());// 存余金额
			map.put("trainSum", trainService.countByVenue(venue.getId()));// 培训课程
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 管理员数据
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午2:19:17 
	 */ 
	@RequestMapping(value = "/managerlist")
	@ResponseBody
	public AdminMessage managerlist(AdminPage adminPage, String venueid) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Manager> managers = managerService.selectByVenue(venueid);
		PageInfo<Manager> pageInfo = new PageInfo<>(managers);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < managers.size(); i++) {
			Manager manager = managers.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", manager.getId());// ID
			map.put("createtime", DateUtil.getFormat(manager.getCreatetime()));// 创建时间
			map.put("name", manager.getName());// 姓名
			map.put("realname", manager.getRealname() == 0 ? "否" : "是");// 实名
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**  
	 * @Description: 场馆日志数据
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午2:19:17 
	 */ 
	@RequestMapping(value = "/venueloglist")
	@ResponseBody
	public AdminMessage venueloglist(String venueid) {
		List<VenueLog> list = venueLogService.selectByVenue(venueid);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueLog venueLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venueLog.getId());// ID
			map.put("createtime", DateUtil.getFormat(venueLog.getCreatetime()));// 时间
			map.put("manager", venueLog.getManager().getName());// 操作人
			map.put("content", venueLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}

}
