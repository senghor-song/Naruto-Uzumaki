package com.ruiec.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruiec.framework.web.support.controller.BaseAdminController;

/**
 * 公共控制器
 * @author Senghor<br>
 * @date 2017年12月15日 上午11:11:22
 */
@Controller
@RequestMapping("/admin/common")
public class CommonController extends BaseAdminController {
	
	/**
	 * 宋高俊
	 * @author Senghor<br>
	 * @date 2017年12月15日 上午11:10:58
	 */
	@RequestMapping(value = "/main")
	public String main() {
		return "/admin/common/main";
	}
	
	/**
	 * 宋高俊
	 * @author Senghor<br>
	 * @date 2017年12月15日 上午11:10:58
	 */
	@RequestMapping(value = "/centerForShow")
	public String centerForShow() {
		return "/admin/common/centerForShow";
	}
	
}
