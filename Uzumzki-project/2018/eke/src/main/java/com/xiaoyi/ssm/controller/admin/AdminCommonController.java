package com.xiaoyi.ssm.controller.admin;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.StaffService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 经济人接口控制器
 * @author 宋高俊
 * @date 2018年7月18日 下午4:05:38
 */
@Controller
@RequestMapping("admin/common")
public class AdminCommonController {

	@Autowired
	private StaffService staffService;

	/**
	 * @Description: 后台登录页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}

	/**
	 * @Description: 后台注册页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午12:15:57
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "admin/register";
	}

	/**
	 * @Description: 左侧菜单页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/left")
	public String left() {
		return "admin/layout/leftSidebar";
	}

	/**
	 * @Description: 顶部菜单页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/top")
	public String top() {
		return "admin/layout/topSidebar";
	}

	/**
	 * @Description: 首页
	 * @author 宋高俊
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "admin/index";
	}

	@RequestMapping(value = "/countTop")
	@ResponseBody
	public ApiMessage countTop() {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_TOP_TAG_MAP,Global.REDIS_TOP_TAG_MAP);
		return ApiMessage.succeed(map);
	}

	/**
	 * @Description: 后台注册获取验证码
	 * @author 宋高俊
	 * @param type = 0 注册验证码 1 = 修改密码验证码
	 * @date 2018年7月25日 下午1:43:06
	 */
	@RequestMapping(value = "/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request, String type) {
		if ("0".equals(type)) {
			String smsCode = Utils.getCode();
			if (Utils.getPhone(phone)) {
				try {
					Staff staff = staffService.getPhoneRegister(phone);
					if (staff != null) {
						return new ApiMessage(400, "该手机号码已被注册");
					}
					MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
					HttpSession session = request.getSession();
					session.setAttribute(Global.admin_register_SmsCode_ + phone, smsCode);
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
					session.setAttribute(Global.admin_updatePassword_SmsCode_ + phone, smsCode);
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
	 * @Description: 注册方法
	 * @author 宋高俊
	 * @date 2018年7月25日 下午2:37:32
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage register(HttpServletRequest request, Staff staff, String smscode) {
		HttpSession session = request.getSession();
		if (!smscode.equals(session.getAttribute(Global.admin_register_SmsCode_ + staff.getTel()))) {
			return new ApiMessage(400, "验证码错误");
		}
		session.removeAttribute(Global.admin_register_SmsCode_ + staff.getTel());
		staff.setStaffid(Utils.getUUID());
		staff.setStatus("冻结");
		int flag = staffService.insertSelective(staff);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}
	
	/**  
	 * @Description: 获取上传进度数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 上午10:23:30 
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/redisupload", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage redisupload(HttpServletRequest request, String redisname) {
		HttpSession session = request.getSession();
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_SESSION_UPLOAD_MAP, session.getId() + redisname);
		return ApiMessage.succeed(map);
	}
}
