package com.xiaoyi.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 跳转登录页面
 * @author 宋高俊
 * @date 2018年12月8日下午3:48:23
 */
@Controller(value = "adminIndexController")
public class IndexController {

	/**
	 * @Description: 跳转登录页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年12月8日下午3:48:05
	 */
	@RequestMapping(value = { "/", "" })
	public String index() {
		return "redirect:/admin/common/login";
	}
}
