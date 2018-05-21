package com.ruiec.springboot.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.VarietyAreaService;
import com.ruiec.springboot.service.VarietyService;
import com.ruiec.springboot.service.VarietyTypeService;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺控制器
 * @author Senghor<br>
 * @date 2017年11月21日 上午9:52:26
 */
@Controller
@RequestMapping("/home/variety")
public class VarietyHomeController extends BaseController{
	
	@Resource(name="varietyAreaServiceImpl")
	private VarietyAreaService varietyAreaService;
	
	@Resource(name="varietyTypeServiceImpl")
	private VarietyTypeService varietyTypeService;
	
	@Resource(name="varietyServiceImpl")
	private VarietyService varietyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VarietyHomeController.class);
	
	/**
	 * 跳转综艺列表
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:13
	 */
	@RequestMapping(value = { "" , "/" , "/list"})
	private String list(HttpServletRequest request,Model model,PageBean<Map<String, Object>> pageBean) {
		
		PageHelper.startPage(pageBean.getCurrentPage(),30);
		Map<String, Object> map=new HashMap<String, Object>();
		ResponseDTO responseDTO = varietyService.selectAll();
		// 分页数据
		List<Map<String, Object>> varietys=responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(varietys);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(varietys);
		model.addAttribute("pageBean", pageBean);
		
		responseDTO = varietyTypeService.selectAll();
		List<Map<String, Object>> varietyTypes = responseDTO.getData();
		//综艺类型
		model.addAttribute("varietyTypes" , varietyTypes);
		
	    return "/home/variety/variety";
	}
	
	@RequestMapping(value = { "download"})
	private String download(HttpServletRequest request,Model model) {
		ResponseDTO responseDTO=varietyService.selectById(Long.valueOf(request.getParameter("id").toString()));
		model.addAttribute("variety", responseDTO.getData());
	    return "/home/variety/download";
	}
}
