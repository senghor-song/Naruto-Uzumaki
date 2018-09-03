package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.AreaMapper;
import com.xiaoyi.ssm.dao.CityMapper;
import com.xiaoyi.ssm.dao.DistrictMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.CityLog;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 城市控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午7:44:53
 */
@Controller(value = "adminCity")
@RequestMapping("admin/city")
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private DistrictMapper districtMapper;
	@Autowired
	private AreaMapper areaMapper;

	/**
	 * @Description: 城市页面
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/listview")
	public String listview(Model model) {
		model.addAttribute("key", Global.Baidu_Map_KRY);
		return "admin/city/list";
	}

	/**
	 * @Description: 城市数据
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<City> list = cityService.selectByCityAll();
		PageInfo<City> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			City city = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cityid", city.getId());// 城市名称
			map.put("cityname", city.getCity());// 城市名称
			map.put("flagmap", city.getFlagmap());// 是否有地图
			map.put("districtCount", cityService.countDistrictByCity(city.getId()));// 区县数量
			map.put("districtScope", "未找到");// 区县范围
			map.put("areaCount", cityService.countAreaByCity(city.getId()));// 片区数量
			map.put("areaScope", "未找到");// 片区范围
			map.put("cityLog", cityService.countCityLogByCity(city.getId()));// 日志
			map.put("empScoreAvg", cityService.avgEmpScoreByCity(city.getId()));// 经纪人均分
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 城市日志数据
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/cityLogList")
	@ResponseBody
	public AdminMessage cityLogList(AdminPage adminPage, String cityId) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<CityLog> list = cityService.selectCityLogByCity(cityId);
		PageInfo<CityLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			CityLog cityLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cityLogId", cityLog.getId());// 城市日志ID
			map.put("logTime", DateUtil.getFormat(cityLog.getLogtime()));// 城市日志时间
			map.put("content", cityLog.getContent());// 城市日志内容
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 城市下属区县数据
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/cityDistrictList")
	@ResponseBody
	public AdminMessage cityDistrictList(AdminPage adminPage, String cityId) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<District> list = cityService.selectByCity(cityId);
		PageInfo<District> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			District district = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("districtId", district.getId());// 区县ID
			map.put("district", district.getDistrict());// 区县名称
			map.put("areaCount", cityService.countAreaByDistrict(district.getId()));// 区县下属片区数量
			if (district.getLatitude() == 0 && district.getLongitude() == 0) {
				map.put("longitudeAndLatitude", "无");// 范围
			} else {
				map.put("longitudeAndLatitude", "有");// 范围
			}
			map.put("lng", district.getLongitude());
			map.put("lat", district.getLatitude());
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap, 1);
	}

	/**
	 * @Description: 城市下属片区数据
	 * @author 宋高俊
	 * @date 2018年7月26日 下午7:46:48
	 */
	@RequestMapping("/cityAreaList")
	@ResponseBody
	public AdminMessage cityAreaList(AdminPage adminPage, String districtId) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Area> list = cityService.selectByDistrict(districtId);
		PageInfo<Area> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Area area = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaid", area.getId());// 片区ID
			map.put("area", area.getArea());// 片区
			if (area.getLatitude() == 0 && area.getLongitude() == 0) {
				map.put("longitudeAndLatitude", "无");// 范围
			} else {
				map.put("longitudeAndLatitude", "有");// 范围
			}
			map.put("lng", area.getLongitude());
			map.put("lat", area.getLatitude());
			listMap.add(map);
		}
		return new AdminMessage(200, pageInfo.getTotal(), listMap, 1);
	}

	/**
	 * @Description: 新增区县方法
	 * @author 宋高俊
	 * @date 2018年7月27日 下午4:09:07
	 */
	@RequestMapping("/addDistrict")
	@ResponseBody
	public ApiMessage addDistrict(String cityId, String districtName) {
		City city = cityMapper.selectByPrimaryKey(cityId);
		if (city != null) {
			District district = new District();
			district.setId(Utils.getUUID());
			district.setDistrict(districtName);
			district.setCity(city.getCity());
			district.setCityid(cityId);
			districtMapper.insertSelective(district);
			return ApiMessage.succeed();
		}
		return ApiMessage.error();
	}

	/**
	 * @Description: 新增片区方法
	 * @author 宋高俊
	 * @date 2018年7月27日 下午4:09:21
	 */
	@RequestMapping("/addArea")
	@ResponseBody
	public ApiMessage addArea(String districtId, String areaName) {
		District district = districtMapper.selectByPrimaryKey(districtId);
		if (district != null) {
			Area area = new Area();
			area.setId(Utils.getUUID());
			area.setCity(district.getCity());
			area.setCityid(district.getCityid());
			area.setDistrict(district.getDistrict());
			area.setDistrictid(district.getId());
			area.setArea(areaName);
			areaMapper.insertSelective(area);
			return ApiMessage.succeed();
		}
		return ApiMessage.error();
	}

	/**
	 * @Description: 删除区县方法
	 * @author 宋高俊
	 * @date 2018年7月27日 下午4:14:56
	 */
	@RequestMapping("/delDistrict")
	@ResponseBody
	public ApiMessage delDistrict(String districtId) {
		districtMapper.deleteByPrimaryKey(districtId);
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 删除区县方法
	 * @author 宋高俊
	 * @date 2018年7月27日 下午4:14:56
	 */
	@RequestMapping("/delArea")
	@ResponseBody
	public ApiMessage delArea(String areaId) {
		areaMapper.deleteByPrimaryKey(areaId);
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 根据区县获取片区
	 * @author 宋高俊
	 * @date 2018年8月2日 下午2:11:54
	 */
	@RequestMapping("/getAreaList")
	@ResponseBody
	public ApiMessage getAreaList(String id) {
		Area area = new Area();
		area.setDistrictid(id);
		List<Area> areas = areaMapper.selectByAll(area);
		return ApiMessage.succeed(areas);
	}

	/**
	 * @Description: 根据城市获取区县
	 * @author 宋高俊
	 * @date 2018年8月2日 下午2:12:12
	 */
	@RequestMapping("/getDistrictList")
	@ResponseBody
	public ApiMessage getDistrictList(String id) {
		District district = new District();
		district.setCityid(id);
		List<District> districts = districtMapper.selectByAll(district);
		return ApiMessage.succeed(districts);
	}
}
