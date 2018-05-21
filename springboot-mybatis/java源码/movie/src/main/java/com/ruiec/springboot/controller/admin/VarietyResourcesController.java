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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.VarietyResourcesService;
import com.ruiec.springboot.service.VarietyService;
import com.ruiec.springboot.util.IdWorker;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺资源控制器
 * @author Senghor<br>
 * @date 2017年11月21日 上午9:52:26
 */
@Controller
@RequestMapping("/admin/varietyResources")
public class VarietyResourcesController extends BaseController{
	
	@Resource(name="varietyResourcesServiceImpl")
	private VarietyResourcesService varietyResourcesService;
	
	@Resource(name="varietyServiceImpl")
	private VarietyService varietyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VarietyResourcesController.class);
	
	/**
	 * 跳转综艺资源列表
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:13
	 */
	@RequestMapping(value = { "" , "/" , "/list"})
	private String list(HttpServletRequest request,Model model,PageBean pageBean) {
		
		PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
		Map<String, Object> map=new HashMap<String, Object>();
		
		ResponseDTO responseDTO = varietyResourcesService.selectByPage(map);
		// 分页数据
		List<Map<String, Object>> varietyResourcess=responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(varietyResourcess);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(varietyResourcess);
		model.addAttribute("pageBean", pageBean);
		
	    return "/admin/varietyResources/list";
	}
	
	/**
	 * 跳转至综艺资源添加页面
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:29
	 */
	@RequestMapping(value = { "/add" })
	private String add(HttpServletRequest request) {
	    return "/admin/varietyResources/add";
	}
	
	/**
	 * 添加一条综艺资源数据
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:51
	 */
	@RequestMapping(value = { "/insert" })
	@ResponseBody
	private JsonResult insert(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
    	IdWorker id = new IdWorker();
		map.put("id", id.nextId());
		map.put("varietyId", Integer.valueOf(request.getParameter("varietyId")));
		map.put("programName", request.getParameter("programName"));
		map.put("clarity", request.getParameter("clarity"));
		map.put("size", Integer.valueOf(request.getParameter("size")));
		if ("0".equals(request.getParameter("downlodMode"))) {
			map.put("downlodMode", false);
		}else {
			map.put("downlodMode", true);
		}
		map.put("downlodLink", request.getParameter("downlodLink"));
		map.put("createOn", System.currentTimeMillis());
		ResponseDTO responseDTO = varietyResourcesService.add(map);
		responseDTO.setMessage("添加成功");
		return super.toJsonResult(responseDTO);
	}
	
	
	/**
	 * 根据id获取综艺资源信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:21
	 */
	@RequestMapping(value = { "/edit" })
	private String edit(String id,Model model) {
		ResponseDTO responseDTO=varietyResourcesService.selectById(Long.valueOf(id));
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("varietyResources", map);
	    return "/admin/varietyResources/update";
	}
	
	/**
	 * 根据id修改综艺资源信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:52
	 */
	@RequestMapping(value = { "/update" })
	@ResponseBody
	private JsonResult update(HttpServletRequest request) {
		ResponseDTO responseDTO = varietyResourcesService.update(request);
		responseDTO.setMessage("修改成功");
		return super.toJsonResult(responseDTO);
	}

	/**
	 * 删除综艺地区数据
	 * @author Senghor<br>
	 * @date 2017年11月30日 下午9:31:11
	 */
	@RequestMapping(value = { "/del" })
	private String del(String id,Model model) {
		String[] ids=id.split(",");
		ResponseDTO responseDTO=varietyResourcesService.delete(ids);
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("varietyResources", map);
	    return "redirect:/admin/varietyResources";
	}
}
