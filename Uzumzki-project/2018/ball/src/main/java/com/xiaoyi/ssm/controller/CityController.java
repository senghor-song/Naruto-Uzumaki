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
import com.xiaoyi.ssm.dao.CityLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.CityLog;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.service.TrainCoachService;
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
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private TrainCoachService trainCoachService;
    @Autowired
    private OperationLogService operationLogService;

	/**
	 * @Description: 城市页面
	 * @author 宋高俊  
	 * @date 2018年9月21日 下午3:55:30 
	 */
	@RequestMapping(value = "/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "32");
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute("btn"+list.get(i).getId(), "1");
		}
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
			map.put("mapflag", city.getMapflag() == 0 ? "否" : "是");// 是否有地图
			map.put("cityflag", city.getCityflag() == 0 ? "关闭" : "开放");// 是否开放
			map.put("city", city.getCity());// 城市
			
			List<District> list2 = districtService.selectByCityId(city.getId());
			/*String distinctString = "";
			for (District distinct : list2) {
				distinctString += distinct.getDistrict() + ",";
			}
			if (distinctString.length() > 0) {
				distinctString = distinctString.substring(0, distinctString.length()-1);
			}*/
			map.put("distinct", list2.size());// 下属区县
//			map.put("venuesum", city.getVenuesum());// 场馆数
			
			Integer cityLogSum = cityLogMapper.countByCity(city.getId());
			map.put("cityLogSum", cityLogSum);// 日志
			
			// 获取缺省教练
			TrainCoach trainCoach = trainCoachService.selectByDefault(city.getId());
			if (trainCoach != null) {
				map.put("coachPrice", trainCoach.getMemberId());// 教练价格
			}

			map.put("ball1", city.getBall1());// 网球
			map.put("ball2", city.getBall2());// 足球
			map.put("ball3", city.getBall3());// 羽毛球
			map.put("ball4", city.getBall4());// 蓝球
			
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
	 * @Description: 新增区县数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月16日 上午9:43:22 
	 */ 
	@RequestMapping(value = "/district/add")
	@ResponseBody
	public ApiMessage districtAdd(String cityid,String name, HttpServletRequest request) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		District oldDistrict = districtService.selectByName(name);
		if (oldDistrict != null) {
			return new ApiMessage(400, "已有同名的区县");
		}
		District district = new District();
		district.setId(Utils.getUUID());
		district.setDistrict(name);
		district.setCityid(cityid);
		districtService.insertSelective(district);
		
		CityLog cityLog = new CityLog();
		cityLog.setId(Utils.getUUID());
		cityLog.setCreatetime(new Date());
		cityLog.setStaffid(staff.getId());
		cityLog.setCityid(cityid);
		cityLog.setContent("新增区县"+name);
		cityLogMapper.insertSelective(cityLog);
		
		operationLogService.saveLog(staff.getId(), "新增区县"+name, Utils.getIpAddr(request));
		
		return new ApiMessage(200, "新增成功");
	}
	
	/**  
	 * @Description: 删除区县数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月16日 上午9:43:22 
	 */ 
	@RequestMapping(value = "/district/del")
	@ResponseBody
	public ApiMessage districtDel(String districtid, HttpServletRequest request) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		District district = districtService.selectByPrimaryKey(districtid);
		// 删除区县
		districtService.deleteByPrimaryKey(districtid);

		CityLog cityLog = new CityLog();
		cityLog.setId(Utils.getUUID());
		cityLog.setCreatetime(new Date());
		cityLog.setStaffid(staff.getId());
		cityLog.setCityid(district.getCityid());
		cityLog.setContent("删除区县"+district.getDistrict());
		cityLogMapper.insertSelective(cityLog);
		
		operationLogService.saveLog(staff.getId(), "删除区县"+district.getDistrict(), Utils.getIpAddr(request));
		
		return new ApiMessage(200, "删除成功");
	}
	
	/**
	 * @Description: 修改区县名
	 * @author 宋高俊
	 * @param districtid
	 * @param name
	 * @return
	 * @date 2018年11月18日 下午4:46:51
	 */
	@RequestMapping(value = "/district/update")
	@ResponseBody
	public ApiMessage districtUpdate(String districtid,String name, HttpServletRequest request) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		// 查询是否有同名的区县
		District districtLikeName = districtService.selectByName(name);
		if (districtLikeName != null) {
			return new ApiMessage(400, "已有同名的区县", districtLikeName.getDistrict());
		}
		// 查询区县旧数据
		District oldDistrict = districtService.selectByPrimaryKey(districtid);
		
		District district = new District();
		district.setId(districtid);
		district.setDistrict(name);
		districtService.updateByPrimaryKeySelective(district);
		
		CityLog cityLog = new CityLog();
		cityLog.setId(Utils.getUUID());
		cityLog.setCreatetime(new Date());
		cityLog.setStaffid(staff.getId());
		cityLog.setCityid(oldDistrict.getCityid());
		cityLog.setContent("修改区县名,旧值:" + oldDistrict.getDistrict() + ",新值:" + district.getDistrict());
		cityLogMapper.insertSelective(cityLog);
		
		operationLogService.saveLog(staff.getId(), "修改区县名,旧值:" + oldDistrict.getDistrict() + ",新值:" + district.getDistrict(), Utils.getIpAddr(request));
		return new ApiMessage(200, "修改成功");
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
			operationLogService.saveLog(staff.getId(), "设为热门城市", Utils.getIpAddr(request));
		}else {
			cityLog.setContent("取消热门城市");
			operationLogService.saveLog(staff.getId(), "取消热门城市", Utils.getIpAddr(request));
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
			operationLogService.saveLog(staff.getId(), "设为地图显示", Utils.getIpAddr(request));
		}else {
			cityLog.setContent("取消地图显示");
			operationLogService.saveLog(staff.getId(), "取消地图显示", Utils.getIpAddr(request));
		}
		cityLogMapper.insertSelective(cityLog);
		return new ApiMessage(200, "修改成功");
	}

	/**
	 * @Description: 修改是否开放入口
	 * @author 宋高俊
	 * @param id
	 * @param check
	 * @return
	 * @date 2018年10月20日 下午2:23:24
	 */
	@RequestMapping(value = "/uodateCityFlag")
	@ResponseBody
	public ApiMessage uodateCityFlag(String id, Integer check, HttpServletRequest request) {
		// 登录用户
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");

		City city = cityService.selectByPrimaryKey(id);
		city.setCityflag(check);
		cityService.updateByPrimaryKeySelective(city);

		CityLog cityLog = new CityLog();
		cityLog.setId(Utils.getUUID());
		cityLog.setCreatetime(new Date());
		cityLog.setStaffid(staff.getId());
		cityLog.setCityid(id);
		if (check == 1) {
			cityLog.setContent("开放城市入口");
			operationLogService.saveLog(staff.getId(), "开放城市入口", Utils.getIpAddr(request));
		}else {
			cityLog.setContent("关闭城市入口");
			operationLogService.saveLog(staff.getId(), "关闭城市入口", Utils.getIpAddr(request));
		}
		cityLogMapper.insertSelective(cityLog);
		return new ApiMessage(200, "修改成功");
	}

	/**  
	 * @Description: 修改缺省教练价格
	 * @author 宋高俊  
	 * @param id
	 * @param price
	 * @return 
	 * @date 2018年11月7日 上午9:41:55 
	 */ 
	@RequestMapping(value = "/updateCoach")
	@ResponseBody
	public ApiMessage updateCoach(String id, String price) {
		TrainCoach trainCoach = trainCoachService.selectByDefault(id);
		try {
			Integer.valueOf(price);
		} catch (Exception e) {
			return new ApiMessage(400, "请输入正确价格");
		}
		
		if (trainCoach != null) {
			trainCoach.setMemberId(price);
			trainCoachService.updateByPrimaryKeySelective(trainCoach);
		}else {
			trainCoach = new TrainCoach();
			trainCoach.setId(id);
			trainCoach.setCreateTime(new Date());
			trainCoach.setModifyTime(new Date());
			trainCoach.setName("陪练");
			trainCoach.setHeadImage("https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/baseImage/venue-coach.png");
			trainCoach.setMemberId(price);
			trainCoachService.insertSelective(trainCoach);
		}
		return new ApiMessage(200, "");
	}
	
}
