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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.VarietyAreaService;
import com.ruiec.springboot.service.VarietyService;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺地区控制器
 * @author Senghor<br>
 * @date 2017年11月21日 下午2:39:09
 */
@Controller
@RequestMapping("/admin/varietyArea")
public class VarietyAreaController extends BaseController{
	
	@Resource(name="varietyAreaServiceImpl")
	private VarietyAreaService varietyAreaService;
	
	@Resource(name="varietyServiceImpl")
	private VarietyService varietyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VarietyAreaController.class);
	
	/**
	 * 跳转综艺地区列表
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:13
	 */
	@RequestMapping(value = { "" , "/" , "/list"})
	private String list(HttpServletRequest request,Model model,PageBean pageBean) {
		
		PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
		Map<String, Object> map=new HashMap<String, Object>();
		ResponseDTO responseDTO = varietyAreaService.selectByPage(map);
		// 分页数据
		List<Map<String, Object>> varietyAreas=responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(varietyAreas);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(varietyAreas);
		model.addAttribute("pageBean", pageBean);
		
	    return "/admin/varietyArea/list";
	}
	
	/**
	 * 跳转至综艺地区添加页面
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:29
	 */
	@RequestMapping(value = { "/add" })
	private String add(HttpServletRequest request) {
	    return "/admin/varietyArea/add";
	}
	
	/**
	 * 添加一条综艺地区数据
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:50:51
	 */
	@PostMapping(value = { "/insert" })
	@ResponseBody
	private JsonResult insert(HttpServletRequest request) {
		ResponseDTO responseDTO = varietyAreaService.add(request);
		responseDTO.setMessage("添加成功");
		return super.toJsonResult(responseDTO);
	}
	
	
	/**
	 * 根据id获取综艺地区信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:21
	 */
	@RequestMapping(value = { "/edit" })
	private String edit(String id,Model model) {
		ResponseDTO responseDTO=varietyAreaService.selectById(Long.valueOf(id));
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("varietyArea", map);
	    return "/admin/varietyArea/update";
	}
	
	/**
	 * 根据id修改综艺地区信息
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:51:52
	 */
	@RequestMapping(value = { "/update" })
	@ResponseBody
	private JsonResult update(HttpServletRequest request) {
		ResponseDTO responseDTO = varietyAreaService.update(request);
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
		ResponseDTO responseDTO=varietyAreaService.delete(ids);
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("varietyArea", map);
	    return "redirect:/admin/varietyArea";
	}
}
