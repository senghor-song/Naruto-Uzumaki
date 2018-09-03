package com.xiaoyi.ssm.controller.api.cust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.EmpStore;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.service.EmpStoreService;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.util.MapUtils;

/**
 * @Description: 客户顾问接口
 * @author 宋高俊
 * @date 2018年8月6日 上午9:11:13
 */
@Controller
@RequestMapping("api/custEmpStore")
public class ApiCustEmpStoreController {

	@Autowired
	private EmpStoreService empStoreService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * @Description: 门店列表
	 * @author 宋高俊
	 * @date 2018年8月6日 上午9:38:17
	 */
	@RequestMapping("/empStore/list")
	@ResponseBody
	public ApiMessage empStorelist(double lng, double lat, int toDistance) {
		List<EmpStore> list = empStoreService.selectByAll(null);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			EmpStore empStore = list.get(i);
			// 遍历所有门店数据,比较门店离用户的距离
			double distance = MapUtils.getDistance(lng, lat, empStore.getLongitude(), empStore.getLatitude());
			distance = Math.ceil(distance);
			// 距离小于范围内则显示
			if (Math.ceil(distance) < toDistance) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", empStore.getId());// ID
				map.put("empStore", empStore.getEmpstore());// 商户名
				map.put("address", empStore.getAddress());// 商户地址
				map.put("lng", empStore.getLongitude());// 商户经度
				map.put("lat", empStore.getLatitude());// 商户纬度
				map.put("distance", distance);// 商户离当前用户距离
				map.put("empSum", empStore.getEmpsum());// 商户经纪人数量
				listMap.add(map);
			}
		}
		return ApiMessage.succeed(listMap);
	}

	/**
	 * @Description: 顾问列表
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/emp/list")
	@ResponseBody
	public ApiMessage list(Model model, PageBean pageBean, String empStoreId) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		Employee e = new Employee();
		e.setStoreid(empStoreId);
		List<Employee> list = employeeService.selectByAll(e);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Employee employee = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", employee.getId());// ID
			map.put("path", employee.getIcon());// 头像
			map.put("theStar", 0);// 星级
			map.put("emp", employee.getEmp());// 姓名
			map.put("brief", "5年从业经验,全部房源100条");// 简介
			map.put("isAuthstate", employee.getAuthstate());// 是否实名认证
			map.put("tel", employee.getTel());// 电话
			String[] feature = new String[] {};
			if (!StringUtils.isBlank(employee.getFeature())) {
				feature = employee.getFeature().split(",");
			}
			map.put("feature", feature);// 标签
			listMap.add(map);
		}
		return ApiMessage.succeed(listMap);
	}

	/**
	 * @Description: 顾问数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/details")
	@ResponseBody
	public ApiMessage details(String id) {
		return ApiMessage.succeed();
	}
}
