package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.AdminStoreDto;
import com.xiaoyi.ssm.model.EmpStore;
import com.xiaoyi.ssm.model.EmpStoreVerify;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.service.EmpStoreLogService;
import com.xiaoyi.ssm.service.EmpStoreService;
import com.xiaoyi.ssm.service.EmpStoreVerifyService;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;

/**
 * @Description: 商户接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminStoreController")
@RequestMapping("admin/store")
public class StoreController {

	@Autowired
	private EmpStoreService empStoreService;
	@Autowired
	private EmpStoreVerifyService empStoreVerifyService;
	@Autowired
	private EmpStoreLogService empStoreLogService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * @Description: 商户列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list(Model model) {
		model.addAttribute("key", Global.Baidu_Map_KRY);
		return "admin/store/list";
	}

	/**
	 * @Description: 商户数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage, AdminStoreDto adminStore) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<EmpStore> list = empStoreService.selectBySearch(adminStore);
		PageInfo<EmpStore> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			EmpStore empStore = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", empStore.getId());
			map.put("city", empStore.getCityT().getCity());// 城市
			map.put("empStore", empStore.getEmpstore());// 商户
			map.put("status", empStore.getStatus());// 合作状态
			map.put("view1", "-");// 耗材订单
			map.put("propertySum", empStoreService.countPropertyByStoreId(empStore.getId()));// 租售
			map.put("empSum", empStoreService.countEmpByStoreId(empStore.getId()));// 经纪人
			map.put("empAvgStore", empStoreService.avgStoreByStoreId(empStore.getId()));// 人均积分
			map.put("empStoreLogSum", empStoreLogService.countByEmpStore(empStore.getId()));// 商洽记录
			map.put("empStoreVerify", empStoreVerifyService.selectVerifyByStore(empStore.getId()) != null ? "是" : "否");// 续期申请
			//商户续期申请地址
			List<Map<String, Object>> listmap = new ArrayList<>();
			EmpStoreVerify oldEmpStoreVerify = new EmpStoreVerify();
			oldEmpStoreVerify.setEmpstoreid(empStore.getId());
			List<EmpStoreVerify> empStoreVerifies = empStoreVerifyService.selectByAll(oldEmpStoreVerify);
			for (int j = 0; j < empStoreVerifies.size(); j++) {
				EmpStoreVerify empStoreVerify = empStoreVerifies.get(j);
				Map<String, Object> empStoreVerifyMap = new HashMap<>();
				//判断是否有地址信息
				if (empStoreVerify.getLatitude() == 0 && empStoreVerify.getLongitude() == 0) {
					empStoreVerifyMap.put("longitudeAndLatitude", "无");// 范围
				} else {
					empStoreVerifyMap.put("longitudeAndLatitude", "有");// 范围
				}
				empStoreVerifyMap.put("lng", empStoreVerify.getLongitude());
				empStoreVerifyMap.put("lat", empStoreVerify.getLatitude());
				empStoreVerifyMap.put("date", DateUtil.getFormat(empStoreVerify.getVerifytime(),"yyyy-MM-dd"));
				listmap.add(empStoreVerifyMap);
			}
			map.put("empStoreVerifyList", listmap);
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 商户详情数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/details")
	@ResponseBody
	public AdminMessage details(Model model, AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Employee> list = employeeService.selectByStore(id);
		PageInfo<Employee> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Employee employee = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", employee.getId());// id
			map.put("emp", employee.getEmp());// 经纪人
			map.put("empno", employee.getEmpno());// 编号
			map.put("storemanage", employee.getStoremanage() == 0 ? "否" : "是");// 是否是管理
			map.put("tel", employee.getTel());// 电话
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 商户查看地图页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/baidumap")
	public String baidumap(Model model, String id) {
		EmpStore empStore = empStoreService.selectByPrimaryKey(id);
		model.addAttribute("empStore", empStore);
		return "admin/store/baidumap";
	}
}
