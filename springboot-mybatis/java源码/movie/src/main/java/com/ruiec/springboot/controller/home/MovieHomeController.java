package com.ruiec.springboot.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruiec.springboot.controller.BaseController;

/**
 * 综艺控制器
 * @author Senghor<br>
 * @date 2017年11月21日 上午9:52:26
 */
@Controller
@RequestMapping("/home/movie")
public class MovieHomeController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieHomeController.class);
	
	/**
	 * 跳转综艺列表
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:13
	 */
	@RequestMapping(value = { "" , "/" })
	private String list(HttpServletRequest request,Model model) {
	    return "/home/movie/movie";
	}
	
}
