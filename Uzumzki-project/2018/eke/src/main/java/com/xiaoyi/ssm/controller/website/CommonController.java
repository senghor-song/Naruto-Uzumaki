package com.xiaoyi.ssm.controller.website;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Cust;
import com.xiaoyi.ssm.service.CustService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 公共接口控制器
 * @author 宋高俊
 * @date 2018年8月15日 上午11:06:29
 */
@Controller("websiteCommonController")
@RequestMapping("/website/common")
public class CommonController {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CustService custService;

	/**
	 * @Description: 登录接口
	 * @author 宋高俊
	 * @date 2018年8月15日 上午9:38:14
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage login(String phone, String smsCode, HttpServletRequest request) {
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(smsCode)) {
			return new ApiMessage(400, "缺失参数");
		}
		// 比较验证码
		if (StringUtil.toCompare((String) request.getSession().getAttribute(Global.website_login_SmsCode_ + phone), smsCode)) {
			Cust cust = custService.getPhoneRegister(phone);
			// 根据手机号查询用户是否存在,不存在则注册
			if (cust == null) {
				cust = new Cust();
				cust.setId(Utils.getUUID());
				cust.setMobile(phone);
				custService.insertSelective(cust);
			}
			request.getSession().setAttribute("websitecust", cust);
			return ApiMessage.succeed();
		} else {
			return new ApiMessage(400, "短信验证码不正确");
		}
	}

	/**
	 * @Description: 获取验证码
	 * @author 宋高俊
	 * @date 2018年8月15日 上午9:40:12
	 */
	@RequestMapping(value = "/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request) {
		String smsCode = Utils.getCode();
		if (Utils.getPhone(phone)) {
			try {
				MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
				HttpSession session = request.getSession();
				session.setAttribute(Global.website_login_SmsCode_ + phone, smsCode);
				session.setMaxInactiveInterval(120);
			} catch (Exception e) {
				return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
			}
		} else {
			return new ApiMessage(400, "请输入正确的手机号码");
		}
		return ApiMessage.succeed();
	}

}
