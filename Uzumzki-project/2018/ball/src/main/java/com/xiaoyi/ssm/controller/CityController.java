package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.CityLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.CityLog;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 城市控制器
 * @author 宋高俊  
 * @date 2018年9月21日 下午3:55:30 
 */ 
@Controller(value = "adminCityController")
@RequestMapping(value = "/admin/city")
public class CityController {
	
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private CityLogMapper cityLogMapper;

	/**
	 * @Description: 城市页面
	 * @author 宋高俊  
	 * @date 2018年9月21日 下午3:55:30 
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/city/list";
	}

	/**
	 * @Description: 城市数据
	 * @author 宋高俊  
	 * @date 2018年9月21日 下午3:55:30 
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		
		List<City> list = cityService.selectByAll(null);
		
		PageInfo<City> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			City city = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", city.getId());// ID
			map.put("cityno", city.getCityno());// 编号
			map.put("initial", city.getInitial());// 字母
			map.put("hotflag", city.getHotflag() == 0 ? "否" : "是");// 热门
			map.put("city", city.getCity());// 城市
			
			List<District> list2 = districtService.selectByCityId(city.getId());
			String distinctString = "";
			for (District distinct : list2) {
				distinctString += distinct.getDistrict() + ",";
			}
			if (distinctString.length() > 0) {
				distinctString = distinctString.substring(0, distinctString.length()-1);
			}
			map.put("distinct", distinctString);// 下属区县
			map.put("map", city.getMapflag() == 0 ? "否" : "是");// 场馆数
//			map.put("venuesum", city.getVenuesum());// 场馆数
			
			Integer cityLogSum = cityLogMapper.countByCity(city.getId());
			map.put("cityLogSum", cityLogSum);// 日志
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**  
	 * @Description: 城市日志数据
	 * @author 宋高俊  
	 * @param adminPage
	 * @return 
	 * @date 2018年9月21日 下午5:20:02 
	 */ 
	@RequestMapping(value = "/cityLog/list")
	@ResponseBody
	public AdminMessage cityLogList(AdminPage adminPage, String id) {
		
		List<CityLog> list = cityLogMapper.selectByCity(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			CityLog cityLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", cityLog.getId());// ID
			map.put("cityno", cityLog.getCreatetime());// 日志时间
			map.put("initial", cityLog.getStaff().getName());// 操作人
			map.put("hotflag", cityLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, 0, listMap);
	}
	
	/**  
	 * @Description: 区县数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月16日 上午9:43:22 
	 */ 
	@RequestMapping(value = "/district/list")
	@ResponseBody
	public AdminMessage districtlist(String id) {
		List<District> list = districtService.selectByCityId(id);
		PageInfo<District> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			District district = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", district.getId());// ID
			map.put("districtno", district.getDistrictno());// 编号
			map.put("district", district.getDistrict());// 名称
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 修改热门城市
	 * @author 宋高俊  
	 * @param id
	 * @param check
	 * @return 
	 * @date 2018年10月20日 下午2:23:24 
	 */ 
	@RequestMapping(value = "/uodateHot")
	@ResponseBody
	public ApiMessage uodateHot(String id, Integer check, HttpServletRequest request) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		City city = cityService.selectByPrimaryKey(id);
		city.setHotflag(check);
		cityService.updateByPrimaryKeySelective(city);
		
		CityLog cityLog = new CityLog();
		cityLog.setId(Utils.getUUID());
		cityLog.setCreatetime(new Date());
		cityLog.setStaffid(staff.getId());
		cityLog.setCityid(id);
		if (check == 1) {
			cityLog.setContent("设为热门城市");
		}else {
			cityLog.setContent("取消热门城市");
		}
		cityLogMapper.insertSelective(cityLog);
		return new ApiMessage(200, "修改成功");
	}
	
	/**  
	 * @Description: 修改是否显示地图
	 * @author 宋高俊  
	 * @param id
	 * @param check
	 * @return 
	 * @date 2018年10月20日 下午2:23:24 
	 */ 
	@RequestMapping(value = "/uodateMap")
	@ResponseBody
	public ApiMessage uodateMap(String id, Integer check, HttpServletRequest request) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		City city = cityService.selectByPrimaryKey(id);
		city.setMapflag(check);
		cityService.updateByPrimaryKeySelective(city);

		CityLog cityLog = new CityLog();
		cityLog.setId(Utils.getUUID());
		cityLog.setCreatetime(new Date());
		cityLog.setStaffid(staff.getId());
		cityLog.setCityid(id);
		if (check == 1) {
			cityLog.setContent("设为地图显示");
		}else {
			cityLog.setContent("取消地图显示");
		}
		cityLogMapper.insertSelective(cityLog);
		return new ApiMessage(200, "修改成功");
	}
}
