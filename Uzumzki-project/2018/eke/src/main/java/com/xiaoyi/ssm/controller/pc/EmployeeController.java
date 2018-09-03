package com.xiaoyi.ssm.controller.pc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.EmpLogMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.EmployeeDto;
import com.xiaoyi.ssm.model.EmpLog;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 经济人控制器
 * @author 宋高俊
 * @date 2018年6月25日 下午6:31:58
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmpLogMapper empLogMapper;

	/**
	 * @Description: 登录方法
	 * @author 宋高俊
	 * @date 2018年6月25日 下午6:35:37
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage login(Model model, Employee employeeForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Employee employee = employeeService.login(employeeForm);
		// PageHelper.startPage(PaginationContext.getPageNum(),
		// PaginationContext.getPageSize());
		if (employee != null) {
			employee.setRemainingDays((int)DateUtil.getTimeDifference(new Date(), employee.getMassvalidity(), 3));
			RedisUtil.addRedis(Global.redis_loginuser_SessionID, employee.getId(), session.getId());
			session.setAttribute("loginuser", employee);
			return new ApiMessage(200, "登录成功");
		} else {
			return new ApiMessage(400, "登录失败");
		}
	}

	/**
	 * @Description: 退出登录
	 * @author 宋高俊
	 * @date 2018年6月25日 下午6:35:23
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String loginout(Model model, Employee employeeForm, HttpServletRequest request) {
		request.getSession().removeAttribute("loginuser");
		return "redirect:/common/login";
	}

	/**
	 * @Description: 经纪人的个人信息
	 * @author 宋高俊
	 * @date 2018年6月28日 下午2:05:33
	 */
	@RequestMapping(value = "/personInfo")
	public String personInfo(Model model, HttpServletRequest request) {
		return "/personManage/employee/personInfo";
	}

	/**
	 * @Description: 经纪人修改密码
	 * @author 宋高俊
	 * @date 2018年6月28日 下午2:23:59
	 */
	@RequestMapping(value = "/udpatePassord", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage udpatePassord(Model model, EmployeeDto employeeDto, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		employeeDto.setId(employee.getId());
		if (employee.getPassword().equals(employeeDto.getOldPwd())) {
			int flag = employeeService.updatePassword(employeeDto);
			if (flag == 1) {
				EmpLog empLog = new EmpLog();
				empLog.setId(Utils.getUUID());
				empLog.setRegdate(new Date());
				empLog.setEmpid(employee.getId());
				empLog.setContent("修改密码");
				empLogMapper.insert(empLog);
				return new ApiMessage(200, "修改成功");
			}
		}
		return new ApiMessage(400, "旧密码错误,修改失败");
	}

	/**
	 * @Description: 经纪人修改姓名
	 * @author 宋高俊
	 * @date 2018年6月28日 下午2:23:59
	 */
	@RequestMapping(value = "/udpateName", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage udpateName(Model model, String password, String trueName, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		if (employee.getPassword().equals(password)) {
			Employee employee2 = new Employee();
			employee2.setId(employee.getId());
			employee2.setEmp(trueName);
			int flag = employeeService.updateByPrimaryKeySelective(employee2);
			if (flag == 1) {
				EmpLog empLog = new EmpLog();
				empLog.setId(Utils.getUUID());
				empLog.setRegdate(new Date());
				empLog.setEmpid(employee.getId());
				empLog.setContent("姓名从" + employee.getEmp() + "修改为" + trueName);
				empLogMapper.insert(empLog);
				return new ApiMessage(200, "修改成功");
			}
		}
		return new ApiMessage(400, "登录密码错误,请重新输入");
	}

	/**
	 * @Description: 经纪人修改手机号
	 * @author 宋高俊
	 * @date 2018年6月28日 下午2:23:59
	 */
	@RequestMapping(value = "/udpatePhone", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage udpatePhone(Model model, String password, String phone, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		if (employee.getPassword().equals(password)) {
			Employee employee2 = new Employee();
			employee2.setId(employee.getId());
			employee2.setTel(phone);
			int flag = employeeService.updateByPrimaryKeySelective(employee2);
			if (flag == 1) {
				EmpLog empLog = new EmpLog();
				empLog.setId(Utils.getUUID());
				empLog.setRegdate(new Date());
				empLog.setEmpid(employee.getId());
				empLog.setContent("手机号从" + employee.getTel() + "修改为" + phone);
				empLogMapper.insert(empLog);
				return new ApiMessage(200, "修改成功");
			}
		}
		return new ApiMessage(400, "登录密码错误,请重新输入");
	}

	/**
	 * @Description: 获取经纪人变更列表
	 * @author 宋高俊
	 * @date 2018年6月28日 下午2:23:59
	 */
	@RequestMapping(value = "/getApplyChange", method = RequestMethod.POST)
	public String getApplyChange(Model model, HttpServletRequest request, PageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<EmpLog> list = empLogMapper.selectAll(employee.getId());
		pageInfo = new PageInfo<>(list);
		model.addAttribute("page", pageInfo);
		return "/personManage/employee/applyChange";
	}

}
