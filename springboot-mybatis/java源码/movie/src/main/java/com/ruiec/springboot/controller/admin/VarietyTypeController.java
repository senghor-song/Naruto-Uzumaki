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
import com.ruiec.springboot.service.VarietyAreaService;
import com.ruiec.springboot.service.VarietyTypeService;
import com.ruiec.springboot.util.IdWorker;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺分类控制器
 * @author Senghor<br>
 * @date 2017年11月21日 上午9:52:26
 */
@Controller
@RequestMapping("/admin/varietyType")
public class VarietyTypeController extends BaseController{
	
	@Resource(name="varietyAreaServiceImpl")
	private VarietyAreaService varietyAreaService;
	
	@Resource(name="varietyTypeServiceImpl")
	private VarietyTypeService varietyTypeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VarietyTypeController.class);
	
	/**
	 * 跳转综艺分类列表
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:13
	 */
	@RequestMapping(value = { "" , "/" , "/list"})
	private String list(HttpServletRequest request,Model model,PageBean pageBean) {
		
		PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
		Map<String, Object> map=new HashMap<String, Object>();
		ResponseDTO responseDTO = varietyTypeService.selectByPage(map);
		// 分页数据
		List<Map<String, Object>> varietyTypes=responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(varietyTypes);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(varietyTypes);
		model.addAttribute("pageBean", pageBean);
		
	    return "/admin/varietyType/list";
	}
	
	/**
	 * 跳转至综艺分类添加页面
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:29
	 */
	@RequestMapping(value = { "/add" })
	private String add(HttpServletRequest request) {
	    return "/admin/varietyType/add";
	}
	
	/**
	 * 添加一条综艺分类数据
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:51
	 */
	@RequestMapping(value = { "/insert" })
	@ResponseBody
	private JsonResult insert(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("typeName", request.getParameter("typeName"));
		map.put("sort", request.getParameter("sort"));
		map.put("createOn", System.currentTimeMillis());
		ResponseDTO responseDTO = varietyTypeService.add(map);
		responseDTO.setMessage("添加成功");
		return super.toJsonResult(responseDTO);
	}
	
	
	/**
	 * 根据id获取综艺分类信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:21
	 */
	@RequestMapping(value = { "/edit" })
	private String edit(String id,Model model) {
		ResponseDTO responseDTO=varietyTypeService.selectById(Long.valueOf(id));
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("varietyType", map);
	    return "/admin/varietyType/update";
	}
	
	/**
	 * 根据id修改综艺分类信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:52
	 */
	@RequestMapping(value = { "/update" })
	@ResponseBody
	private JsonResult update(HttpServletRequest request) {
		ResponseDTO responseDTO = varietyTypeService.update(request);
		responseDTO.setMessage("修改成功");
		return super.toJsonResult(responseDTO);
	}
	

	/**
	 * 删除综艺分类数据
	 * @author Senghor<br>
	 * @date 2017年11月30日 下午9:31:11
	 */
	@RequestMapping(value = { "/del" })
	private String del(String id,Model model) {
		String[] ids=id.split(",");
		ResponseDTO responseDTO=varietyTypeService.delete(ids);
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("varietyType", map);
	    return "redirect:/admin/varietyType";
	}
}
