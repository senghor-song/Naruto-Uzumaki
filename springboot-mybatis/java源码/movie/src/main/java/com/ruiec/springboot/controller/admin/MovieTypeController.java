package com.ruiec.springboot.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.impl.MovieTypeServiceImpl;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

import lm.com.framework.webmvc.result.JsonResult;

/**
 * 电影后台控制器
 * @author 陈靖原<br>
 * @date 2017年11月15日 下午7:50:08
 */
@Controller
@RequestMapping("/admin/movieType")
public class MovieTypeController extends BaseController{

	@Resource
	private MovieTypeServiceImpl movieTypeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieTypeController.class);

	/**
	 * 电影类型列表页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12 
	 */
	@RequestMapping(value = { "" , "/","list" })
	@Description("电影类型列表页")
	public String list(HttpServletRequest request, Model model, PageBean pageBean) {
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		ResponseDTO responseDTO = movieTypeService.selectAll();
		// 分页数据
		List<Map<String, Object>> movieTypeList = responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(movieTypeList);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(movieTypeList);
		model.addAttribute("pageBean", pageBean);
		return "admin/movieType/list";
	}
	
	/**
	 * 电影类型新增页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping(value = { "/add" })
	@Description("电影类型新增页")
	public String add(HttpServletRequest request) {
	    return "admin/movieType/add";
	}
	
	/**
	 * 电影类型新增
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@PostMapping(value = { "/insert" })
	@ResponseBody
	public JsonResult save(HttpServletRequest request, Model model) {
		Map<String, Object> movieTypeMap = getMap(request);
		movieTypeMap.put("createOn", System.currentTimeMillis());
		ResponseDTO responseDTO = movieTypeService.add(movieTypeMap);
		LOGGER.info("------------------新增电影类型："+responseDTO.isSuccess()+"---------------------");
		responseDTO.setMessage("添加成功");
		return super.toJsonResult(responseDTO);
	}
	
	/**
	 * 电影类型编辑页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 上午10:32:33
	 */
	@RequestMapping(value = { "/edit" })
	@Description("电影类型编辑页")
	public String edit(String id,Model model) {
		ResponseDTO responseDTO=movieTypeService.selectById(Long.valueOf(id));
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("movieType", map);
	    return "/admin/movieType/update";
	}
	
	/**
	 * 电影类型修改
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@PostMapping(value = { "/update" })
	@ResponseBody
	public JsonResult update(HttpServletRequest request) {
		Map<String, Object> movieTypeMap = getMap(request);
		ResponseDTO responseDTO = movieTypeService.update(movieTypeMap);
		LOGGER.info("------------------修改电影类型：" + responseDTO.isSuccess()+ "---------------------");
		responseDTO.setMessage("修改成功");
		return super.toJsonResult(responseDTO);
	}
	
	/**
	 * 删除电影类型
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:53
	 */
	@RequestMapping("/del")
	@Description("删除电影类型")
	public String delete(String id,Model model) {
		String[] ids=id.split(",");
		ResponseDTO responseDTO=movieTypeService.delete(ids);
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("movieType", map);
	    return "redirect:/admin/movieType/list";
	}
}
