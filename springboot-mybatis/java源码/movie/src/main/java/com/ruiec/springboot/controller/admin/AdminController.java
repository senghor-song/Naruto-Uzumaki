package com.ruiec.springboot.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lm.com.framework.webmvc.result.JsonResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.UserService;
import com.ruiec.springboot.service.VarietyAreaService;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 后台控制器
 * @author Senghor<br>
 * @date 2017年11月20日 下午3:04:14
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TvController.class);
	
	@RequestMapping(value = { "" , "/" })
	String admin(HttpServletRequest request) {
	    return "admin/login";
	}
	
	@RequestMapping(value = { "/login" })
	@ResponseBody
	private JsonResult login(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", request.getParameter("userName"));
		map.put("password", request.getParameter("password"));
		ResponseDTO responseDTO = userService.login(map);
		List<Map<String, Object>> maps=responseDTO.getData();
		if (maps.size()>=1) {
			responseDTO.setCode(200);
			responseDTO.setMessage("登录成功!");
		}else {
			responseDTO.setCode(400);
			responseDTO.setMessage("登录失败!请核对账户密码");
		}
		return super.toJsonResult(responseDTO);
//	    return "admin/index";
	}
	
	@RequestMapping("/index")
	private String index(HttpServletRequest request) {
	    return "admin/index";
	}
	/*//分页
	@RequestMapping("/page")
	String page(HttpServletRequest request,PageBean pageBean,Model model) {
		// 分页数据
		PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
		List<String> allItems = new ArrayList<String>();//模拟数据
		// 分页模型
		PageInfo pageInfo = new PageInfo(allItems);
		// 分页模型
		pageBean.setPageSize(pageInfo.getTotal());
		pageBean.setItems(allItems);
		model.addAttribute("allItems", pageBean.getItems());
	    return "admin/index";
	}*/
}
