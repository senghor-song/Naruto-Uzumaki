package com.ruiec.springboot.controller.admin;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.VarietyAreaService;
import com.ruiec.springboot.service.VarietyService;
import com.ruiec.springboot.service.VarietyTypeService;
import com.ruiec.springboot.util.IdWorker;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺控制器
 * @author Senghor<br>
 * @date 2017年11月21日 上午9:52:26
 */
@Controller
@RequestMapping("/admin/variety")
public class VarietyController extends BaseController{
	
	@Resource(name="varietyAreaServiceImpl")
	private VarietyAreaService varietyAreaService;
	
	@Resource(name="varietyTypeServiceImpl")
	private VarietyTypeService varietyTypeService;
	
	@Resource(name="varietyServiceImpl")
	private VarietyService varietyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VarietyController.class);
	
	/**
	 * 跳转综艺列表
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:13
	 */
	@RequestMapping(value = { "" , "/" , "/list"})
	private String list(HttpServletRequest request,Model model,PageBean pageBean) {
		
		PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
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
		
	    return "/admin/variety/list";
	}
	
	/**
	 * 跳转至综艺添加页面
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:29
	 */
	@RequestMapping(value = { "/add" })
	private String add(HttpServletRequest request,Model model) {
		
		ResponseDTO areas=varietyAreaService.selectAll();
		ResponseDTO types=varietyTypeService.selectAll();
		model.addAttribute("areas",areas.getData());
		model.addAttribute("types",types.getData());
	    return "/admin/variety/add";
	}
	
	/**
	 * 添加一条综艺数据
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:51
	 */
	@RequestMapping(value = { "/insert" })
	@ResponseBody
	private void insert(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
    	IdWorker id = new IdWorker();
		map.put("id", id.nextId());
		map.put("areaName", request.getParameter("areaName"));
		map.put("sort", request.getParameter("sort"));
		ResponseDTO responseDTO = varietyService.add(map);
	}
	
	
	/**
	 * 根据id获取综艺信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:21
	 */
	@RequestMapping(value = { "/edit" })
	private String edit(String id,Model model) {
		ResponseDTO responseDTO=varietyService.selectById(Long.valueOf(id));
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("variety", map);
	    return "/admin/variety/update";
	}
	
	/**
	 * 根据id修改综艺信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:52
	 */
	@RequestMapping(value = { "/update" })
	private String update(HttpServletRequest request) {
		
	    return "/admin/variety/list";
	}
}
