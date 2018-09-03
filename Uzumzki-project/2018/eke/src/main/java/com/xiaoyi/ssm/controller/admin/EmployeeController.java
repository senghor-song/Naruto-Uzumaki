package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.EmpLogMapper;
import com.xiaoyi.ssm.dao.MassBindMapper;
import com.xiaoyi.ssm.dao.MassEmpWatermarkMapper;
import com.xiaoyi.ssm.dto.AdminEmpLoyeeDto;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.EmpLog;
import com.xiaoyi.ssm.model.EmpScore;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassBind;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.CustGrabService;
import com.xiaoyi.ssm.service.EmpChatService;
import com.xiaoyi.ssm.service.EmpCustService;
import com.xiaoyi.ssm.service.EmpScoreService;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.service.MassBindService;
import com.xiaoyi.ssm.service.MassEmpImgService;
import com.xiaoyi.ssm.service.MassPropertyPublishService;
import com.xiaoyi.ssm.service.PropertyService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 经纪人控制器
 * @author 宋高俊
 * @date 2018年7月25日 下午2:43:06
 */
@Controller(value = "adminEmployee")
@RequestMapping(value = "/admin/employee")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MassBindMapper massBindMapper;
	@Autowired
	private MassEmpWatermarkMapper massEmpWatermarkMapper;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private EmpCustService empCustService;
	@Autowired
	private EmpScoreService empScoreService;
	@Autowired
	private MassPropertyPublishService massPropertyPublishService;
	@Autowired
	private MassEmpImgService massEmpImgService;
	@Autowired
	private CustGrabService custGrabService;
	@Autowired
	private EmpChatService empChatService;
	@Autowired
	private EmpLogMapper empLogMapper;
	@Autowired
	private MassBindService massBindService;

	/**
	 * @Description: 经纪人页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/employee/list";
	}

	/**
	 * @Description: 经纪人数据
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage, AdminEmpLoyeeDto adminEmpLoyeeDto) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Employee> list = employeeService.selectBySearch(adminEmpLoyeeDto);
		PageInfo<Employee> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Employee employee = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", employee.getId());// ID
			map.put("city", employee.getCityname());// 城市
			map.put("emp", employee.getEmp());// 经纪人
			map.put("store", employee.getEmpStore().getEmpstore());// 商户
			map.put("authstate", employee.getAuthstate());// 实名
			map.put("status", employee.getStatus());// 状态
			map.put("propertySum", propertyService.countByEmp(employee.getId()));// 租售
			map.put("empCustSum", empCustService.countByEmp(employee.getId()));// 私客
			map.put("score", employee.getScore());// 积分
			map.put("empChatSum", empChatService.countByEmp(employee.getId()));// 后台交流
			map.put("masstype", employee.getMasstype());// 推房账户
			map.put("massvalidity", DateUtil.getFormat(employee.getMassvalidity(), "yyyy-MM-dd"));// 推房到期
			map.put("massPropertyPublishSum", massPropertyPublishService.countPublishByEmp(employee.getId()));// 累计群发
			map.put("bingSum", massBindMapper.countMassBindByEmp(employee.getId()));// 推房绑定
			map.put("empImgSum", massEmpImgService.countEmpImgByEmp(employee.getId()));// 推房图库
			map.put("watermark", massEmpWatermarkMapper.countWatermarkByEmpid(employee.getId()));// 推房水印
			map.put("view8", "未找到");// 云刷新组
			map.put("custGrabSum", custGrabService.countByEmpId(employee.getId()));// 主动抢客
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 经纪人详情
	 * @author 宋高俊
	 * @date 2018年7月30日 下午6:23:30
	 */
	@RequestMapping(value = "/details")
	public String details(Model model, String id, String type) {
		Employee employee = employeeService.selectByPrimaryKey(id);
		model.addAttribute("employee", employee);
		if ("look".equals(type)) {
			return "admin/employee/look";
		}else {
			return "admin/employee/details";
		}
	}

	/**
	 * @Description: 修改经纪人状态
	 * @author 宋高俊
	 * @date 2018年8月9日 上午11:07:07
	 */
	@RequestMapping(value = "/saveEmployee")
	@ResponseBody
	public ApiMessage saveEmployee(HttpServletRequest request, Model model, 
			String status, String storemanage, String remark, String id) {
		Staff staff = (Staff) request.getSession().getAttribute("adminloginuser");
		Employee employee = new Employee();
		employee.setId(id);
		employee.setRemark(remark);
		employee.setStatus(status);
		employee.setStoremanage(new Byte(storemanage));
		employeeService.updateByPrimaryKeySelective(employee);
		try {
			// 新增经纪人日志
			EmpLog empLog = new EmpLog();
			empLog.setId(Utils.getUUID());
			empLog.setRegdate(new Date());
			empLog.setEmpid(employee.getId());
			empLog.setContent(staff.getRname() + "修改状态->" + status + ",备注->" + remark + 
					",是否管理修改为->" + ("0".equals(storemanage) ? "否" : "是"));
			empLogMapper.insert(empLog);
		} catch (Exception e) {
			logger.error("", e);
		}
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 经纪人修改
	 * @author 宋高俊
	 * @date 2018年7月30日 下午6:23:30
	 */
	@RequestMapping(value = "/update")
	public String update(Model model, String id) {
		Employee employee = employeeService.selectByPrimaryKey(id);
		model.addAttribute("employee", employee);
		return "admin/employee/details";
	}

	/**
	 * @Description: 积分明细
	 * @author 宋高俊
	 * @date 2018年8月9日 下午4:47:37
	 */
	@RequestMapping(value = "/empscore/list")
	@ResponseBody
	public AdminMessage empscorelist(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<EmpScore> list = empScoreService.selectScoreByEmp(id);
		PageInfo<EmpScore> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			EmpScore empScore = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", empScore.getId());// ID
			map.put("time", DateUtil.getFormat(empScore.getTime()));// 时间
			map.put("change", empScore.getChange());// 积分变动
			map.put("score", empScore.getScore());// 总分
			map.put("remark", empScore.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 推房绑定账号
	 * @author 宋高俊
	 * @date 2018年8月9日 下午4:47:37
	 */
	@RequestMapping(value = "/massbind/list")
	@ResponseBody
	public AdminMessage massbindlist(AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<MassBind> list = massBindService.selectByEmp(id);
		PageInfo<MassBind> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MassBind massBind = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", massBind.getId());// ID
			map.put("webname", massBind.getWebSite().getWebname());// 网站
			map.put("account", massBind.getAccount());// 账户名
			map.put("checkavailable", massBind.getCheckavailable() == 0 ? "异常" : "正常");// 状态
			map.put("remark", massBind.getCheckmes());// 备注
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}
}
