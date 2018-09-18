package com.xiaoyi.ssm.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Cust;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.service.CustService;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.PropertiesUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 经济人接口控制器
 * @author 宋高俊
 * @date 2018年7月18日 下午4:05:38
 */
@Controller
@RequestMapping("api/common")
public class ApiCommonController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CustService custService;

	/**
	 * @Description: 获取验证码
	 * @author 宋高俊
	 * @param smsType     = 0 客户端登录验证码 = 1 注册验证码 = 2 修改密码验证码
	 * @param accountType = 1 经纪人 2 = 客户
	 * @date 2018年7月6日 下午6:49:58
	 */
	@RequestMapping(value = "/getSMSCode", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage getSMSCode(String tel, int accountType, int smsType) {
		if (!Utils.getPhone(tel)) {
			return new ApiMessage(400, "请输入正确的手机号码");
		}
		if ((smsType != 1 && smsType != 2 && smsType != 0) || (accountType != 1 && accountType != 2)) {
			return new ApiMessage(400, "参数错误");
		}
		if (smsType == 1) {
			String smsCode = Utils.getCode();
			try {
				if (accountType == 1) {
					Employee employee = employeeService.getPhoneRegister(tel);
					if (employee != null) {
						return new ApiMessage(400, "该手机号码已被注册");
					} else {
						MoblieMessageUtil.sendIdentifyingCode(tel, smsCode);
						RedisUtil.setRedis(Global.api_employee_updatePassword_SmsCode_ + tel, smsCode, 120);
					}
				} else if (accountType == 2) {
					Cust cust = custService.getPhoneRegister(tel);
					if (cust != null) {
						return new ApiMessage(400, "该手机号码已被注册");
					} else {
						MoblieMessageUtil.sendIdentifyingCode(tel, smsCode);
						RedisUtil.setRedis(Global.api_cust_updatePassword_SmsCode_ + tel, smsCode, 120);
					}
				}
			} catch (Exception e) {
				return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
			}
		} else if (smsType == 0 || smsType == 2) {
			String smsCode = Utils.getCode();
			try {
				// 判断是客户还是经济
				if (accountType == 1) {
					Employee employee = employeeService.getPhoneRegister(tel);
					if (employee == null) {
						return new ApiMessage(400, "该手机号码未注册");
					} else {
						MoblieMessageUtil.sendIdentifyingCode(tel, smsCode);
						RedisUtil.setRedis(Global.api_employee_updatePassword_SmsCode_ + tel, smsCode, 120);
					}
				} else if (accountType == 2) {
					Cust cust = custService.getPhoneRegister(tel);
					if (cust == null) {
						return new ApiMessage(400, "该手机号码未注册");
					} else {
						MoblieMessageUtil.sendIdentifyingCode(tel, smsCode);
						// 判断是登录还是修改密码
						if (smsType == 0) {
							RedisUtil.setRedis(Global.api_cust_login_SmsCode_ + tel, smsCode, 120);
						} else if (smsType == 2) {
							RedisUtil.setRedis(Global.api_cust_updatePassword_SmsCode_ + tel, smsCode, 120);
						}
					}
				}
			} catch (Exception e) {
				return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
			}
		}
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 客户登录
	 * @author 宋高俊
	 * @date 2018年7月18日 下午4:50:55
	 */
	@RequestMapping(value = "/cust/login")
	@ResponseBody
	public ApiMessage login(String mobile, String smsCode) {
		if (!StringUtil.toCompare(RedisUtil.getRedis(Global.api_cust_login_SmsCode_ + mobile), smsCode)) {
			return new ApiMessage(400, "短信验证码不正确");
		}
		// 验证成功则将验证码提前删除
		RedisUtil.delRedis(Global.api_cust_login_SmsCode_ + mobile);
		Cust loginCust = custService.getPhoneRegister(mobile);
//		Cust loginCust = custService.login(cust);
		if (loginCust != null) {
			RedisUtil.addRedis(Global.redis_cust, loginCust.getId(), loginCust);
			Map<String, Object> map = new HashMap<>();
			map.put("token", loginCust.getId());
			return ApiMessage.succeed(map);
		} else {
			return ApiMessage.error("账号或密码错误");
		}
	}

	/**
	 * @Description: 客户注册
	 * @author 宋高俊
	 * @date 2018年7月18日 下午4:50:43
	 */
	@RequestMapping(value = "/cust/register", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage register(Cust cust) {
		int flag = custService.register(cust);
		if (flag == 1) {
			return ApiMessage.succeed("注册成功");
		}
		return ApiMessage.succeed("注册失败");
	}

	/**
	 * @Description: 经纪人登录
	 * @author 宋高俊
	 * @date 2018年7月18日 下午4:50:55
	 */
	@RequestMapping(value = "/employee/login", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage login(Employee employee) {
		Employee loginEmployee = employeeService.login(employee);
		if (loginEmployee != null) {
			RedisUtil.addRedis(Global.redis_employee, loginEmployee.getId(), loginEmployee);
			Map<String, Object> map = new HashMap<>();
			map.put("token", loginEmployee.getId());
			return ApiMessage.succeed(map);
		} else {
			return ApiMessage.error("账号或密码错误");
		}
	}

	/**
	 * @Description: 经纪人注册
	 * @author 宋高俊
	 * @date 2018年7月18日 下午4:50:43
	 */
	@RequestMapping(value = "/employee/register", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage register(Employee employee, String smsCode) {
		if (StringUtils.isBlank(smsCode) || StringUtils.isBlank(employee.getEmp())
				|| StringUtils.isBlank(employee.getTel()) || StringUtils.isBlank(employee.getPassword())) {
			return new ApiMessage(400, "请输入完整信息");
		}
		if (!StringUtil.toCompare(RedisUtil.getRedis(Global.api_employee_register_SmsCode_ + employee.getTel()),
				smsCode)) {
			return new ApiMessage(400, "短信验证码不正确");
		}
		employee.setId(Utils.getUUID());
		int flag = employeeService.insertSelective(employee);
		if (flag == 1) {
			// 验证成功则将验证码提前删除
			RedisUtil.delRedis(Global.api_employee_register_SmsCode_ + employee.getTel());
			return ApiMessage.succeed("注册成功");
		}
		return ApiMessage.succeed("注册失败");
	}

	/**
	 * @Description: 经纪人找回密码
	 * @author 宋高俊
	 * @date 2018年7月18日 下午4:50:55
	 */
	@RequestMapping(value = "/employee/retrievePassword ", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage retrievePassword(String tel, String smsCode, String newPassword) {
		if (StringUtils.isBlank(tel) || StringUtils.isBlank(smsCode) || StringUtils.isBlank(newPassword)) {
			return new ApiMessage(400, "参数不完整");
		}
		if (!StringUtil.toCompare(RedisUtil.getRedis(Global.api_employee_updatePassword_SmsCode_ + tel), smsCode)) {
			return new ApiMessage(400, "短信验证码不正确");
		}
		// 验证成功则将验证码提前删除
		RedisUtil.delRedis(Global.api_employee_updatePassword_SmsCode_ + tel);
		Employee employee = new Employee();
		employee.setTel(tel);
		employee.setPassword(newPassword);
		int flag = employeeService.updatePhonePassword(employee);
		if (flag == 1) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	@RequestMapping(value = "/sql")
	public void sql(String sql) {
		PropertiesUtil.updatePro(
				Thread.currentThread().getContextClassLoader().getResource("").getPath() + "jdbc.properties",
				"sqlLogger", sql);
	}

	/**
	 * @Description: 小程序接口
	 * @author 宋高俊
	 * @param number
	 * @date 2018年9月8日 下午4:27:58
	 */
	@RequestMapping(value = "/setNumber")
	@ResponseBody
	public ApiMessage setNumber(Integer number, HttpServletRequest request) {
		Global global = new Global();
		global.setNumber(number);
		return new ApiMessage(200, "成功", number);
	}

	/**
	 * @Description: 小程序接口
	 * @author 宋高俊
	 * @param number
	 * @date 2018年9月8日 下午4:27:58
	 */
	@RequestMapping(value = "/getNumber")
	@ResponseBody
	public ApiMessage getNumber(Integer number, HttpServletRequest request) {
		Global global = new Global();
		global.getNumber();
		return new ApiMessage(200, "成功", global.getNumber());
	}
}
