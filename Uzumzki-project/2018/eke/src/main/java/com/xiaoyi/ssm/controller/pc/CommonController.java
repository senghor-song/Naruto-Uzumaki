package com.xiaoyi.ssm.controller.pc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.MassPropertyDto;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.EmpStore;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassNotice;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.service.MassNoticeService;
import com.xiaoyi.ssm.service.MassPropertyService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 公告控制器
 * @author 宋高俊
 * @date 2018年6月27日 下午5:59:26
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MassNoticeService massNoticeService;
	@Autowired
	private MassPropertyService massPropertyService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CityService cityService;

	/**
	 * @Description: 登录页面
	 * @author 宋高俊
	 * @date 2018年6月27日 下午5:58:53
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "loginPage";
	}

	/**
	 * @Description: 主页面
	 * @author 宋高俊
	 * @date 2018年6月27日 下午5:58:53
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	/**
	 * @Description: 后台首页
	 * @author 宋高俊
	 * @date 2018年6月27日 下午5:59:18
	 */
	@RequestMapping(value = "/getMainRight", method = RequestMethod.GET)
	public String getMainRight(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<MassNotice> list = massNoticeService.selectByEmp5(employee.getId());
		model.addAttribute("noticeList", list);
		MassPropertyDto massPropertyDto = new MassPropertyDto();
		massPropertyDto.setEmpId(employee.getId());
		massPropertyDto.setStartDate(DateUtil.getFormat(DateUtil.getWeeHours(new Date(), 0), "yyyy-MM-dd HH:mm:ss"));
		massPropertyDto.setEndDate(DateUtil.getFormat(DateUtil.getWeeHours(new Date(), 1), "yyyy-MM-dd HH:mm:ss"));
		massPropertyDto = massPropertyService.selectByEmp(massPropertyDto);
		model.addAttribute("massPropertyDto", massPropertyDto);
		return "getMainRight";
	}

	/**
	 * @Description: 注册页面
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping("/register")
	public String register(Model model) {
		List<City> citys = cityService.selectAllCity();
		model.addAttribute("citys", citys);
		return "register";
	}

	/**
	 * @Description: 找回密码页面
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping("/retrievePassword")
	public String retrievePassword() {
		return "retrievePassword";
	}

	/**
	 * @Description: 保存注册的新用户
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping("/registerUser")
	@ResponseBody
	public ApiMessage registerUser(Employee employee, HttpServletRequest request, String token) {
		String registerSmsCode = (String) request.getSession()
				.getAttribute(Global.pc_register_SmsCode_ + employee.getTel());
		if (registerSmsCode.equals(token)) {
			employee.setId(Utils.getUUID());
			employeeService.insertSelective(employee);
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 验证手机号是否被注册过
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping("/getPhoneRegister")
	@ResponseBody
	public ApiMessage getPhoneRegister(String phone) {
		Employee employee = employeeService.getPhoneRegister(phone);
		if (employee == null) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 获取验证码
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58 type = 0 注册验证码 1 = 修改密码验证码
	 */
	@RequestMapping("/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request, String type) {
		if ("0".equals(type)) {
			String smsCode = Utils.getCode();
			if (Utils.getPhone(phone)) {
				try {
					Employee employee = employeeService.getPhoneRegister(phone);
					if (employee != null) {
						return new ApiMessage(400, "该手机号码已被注册");
					}
					MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
					HttpSession session = request.getSession();
					session.setAttribute(Global.pc_register_SmsCode_ + phone, smsCode);
					session.setMaxInactiveInterval(120);
				} catch (Exception e) {
					return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
				}
			} else {
				return new ApiMessage(400, "请输入正确的手机号码");
			}
		} else if ("1".equals(type)) {
			String smsCode = Utils.getCode();
			if (Utils.getPhone(phone)) {
				try {
					MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(120);
					session.setAttribute(Global.pc_updatePassword_SmsCode_ + phone, smsCode);
				} catch (Exception e) {
					return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
				}
			} else {
				return new ApiMessage(400, "请输入正确的手机号码");
			}
		}
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 忘记密码修改密码
	 * @author 宋高俊
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public ApiMessage updatePassword(HttpServletRequest request, String token, String phone, String password) {
		String updatePasswordSmsCode = (String) request.getSession()
				.getAttribute(Global.pc_updatePassword_SmsCode_ + phone);
		if (updatePasswordSmsCode.equals(token)) {
			Employee employee = new Employee();
			employee.setPassword(password);
			employee.setTel(phone);
			employeeService.updatePhonePassword(employee);
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 保存成功提示页面
	 * @author 宋高俊
	 * @date 2018年7月9日 下午7:26:03
	 */
	@RequestMapping("/returnSucceed")
	public String returnSucceed() {
		return "returnSucceed";
	}

	/**
	 * @Description: 错误提示页面
	 * @author 宋高俊
	 * @date 2018年7月9日 下午7:26:03
	 */
	@RequestMapping("/returnError")
	public String returnError() {
		return "returnError";
	}

	/**
	 * @Description: 获取经纪人公司
	 * @author 宋高俊
	 * @date 2018年7月17日 下午3:01:52
	 */
	@RequestMapping("/getCompanyForReg")
	@ResponseBody
	public ApiMessage getCompanyForReg(HttpServletRequest request, String cityname, String content) {
		List<EmpStore> list = employeeService.selectByCity(cityname, content);
		return ApiMessage.succeed(list);
	}

}
