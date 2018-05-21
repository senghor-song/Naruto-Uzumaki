package com.ruiec.springboot.controller.home;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.TvService;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺控制器
 * @author Senghor<br>
 * @date 2017年11月21日 上午9:52:26
 */
@Controller
@RequestMapping("/home/tv")
public class TvHomeController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TvHomeController.class);
	@Resource(name="tvServiceImpl")
	private TvService tvService;
	
	/**
	 * 跳转综艺列表
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:13
	 */
	@RequestMapping(value = { "" , "/" })
	private String list(HttpServletRequest request,Model model) {
	    return "/home/tv/tv";
	}
	
	/**
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月26日 下午8:53:51
	 */
	@RequestMapping(value = { "/teleplay"})
	public String home(Model model, Long id) {
		ResponseDTO responseDTO = tvService.selectById(id);
		Map<String, Object> obj = responseDTO.getData();
		LOGGER.info("------------------测试：" + obj.get("name") + responseDTO.getMessage() + "---------------------");
		model.addAttribute("tv", responseDTO.getData());
	    return "home/teleplay";
	}
	
}
