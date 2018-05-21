package com.ruiec.springboot.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.MovieResourcesService;
import com.ruiec.springboot.service.TvAreaService;
import com.ruiec.springboot.service.impl.MovieResourcesServiceImpl;
import com.ruiec.springboot.service.impl.MovieServiceImpl;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

import lm.com.framework.webmvc.result.JsonResult;

/**
 * 电影后台控制器
 * @author 陈靖原<br>
 * @date 2017年11月15日 下午7:50:08
 */
@Controller
@RequestMapping("/admin/movieResources")
public class MovieResourcesController extends BaseController{

	@Resource
	private MovieResourcesServiceImpl movieResourcesService;
	@Resource
	private MovieServiceImpl movieService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieResourcesController.class);

	/**
	 * 电影来源列表页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12 
	 */
	@RequestMapping(value = { "" , "/","list" })
	@Description("电影来源列表页")
	public String list(HttpServletRequest request, Model model, PageBean pageBean) {
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		ResponseDTO responseDTO = movieResourcesService.selectAll();
		// 分页数据
		List<Map<String, Object>> movieResourcesList = responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(movieResourcesList);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(movieResourcesList);
		model.addAttribute("pageBean", pageBean);
		return "admin/movieResources/list";
	}
	
	/**
	 * 电影来源新增页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping(value = { "/add" })
	@Description("电影来源新增页")
	public String add(HttpServletRequest request,Model model) {
		ResponseDTO responseDTO = movieService.selectAll();
		// 分页数据
		List<Map<String, Object>> movieList = responseDTO.getData();
		model.addAttribute("movieList",movieList);
	    return "admin/movieResources/add";
	}
	
	/**
	 * 电影来源新增
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@PostMapping(value = { "/insert" })
	@ResponseBody
	public JsonResult save(HttpServletRequest request, Model model) {
		Map<String, Object> movieResourcesMap = getMap(request);
		movieResourcesMap.put("createOn", System.currentTimeMillis());
		if("1".equals(movieResourcesMap.get("downlodMode"))) {
			movieResourcesMap.put("downlodMode", true);
		}else {
			movieResourcesMap.put("downlodMode", false);
		}
		movieResourcesMap.put("size", Integer.valueOf(movieResourcesMap.get("size").toString()));
		ResponseDTO responseDTO = movieResourcesService.add(movieResourcesMap);
		LOGGER.info("------------------新增电影来源："+responseDTO.isSuccess()+"---------------------");
		responseDTO.setMessage("添加成功");
		return super.toJsonResult(responseDTO);
	}
	
	/**
	 * 电影来源编辑页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 上午10:32:33
	 */
	@RequestMapping(value = { "/edit" })
	@Description("电影来源编辑页")
	public String edit(String id,Model model) {
		ResponseDTO responseDTO=movieResourcesService.selectById(Long.valueOf(id));
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("movieResources", map);
	    return "/admin/movieResources/update";
	}
	
	/**
	 * 电影来源修改
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@PostMapping(value = { "/update" })
	@ResponseBody
	public JsonResult update(HttpServletRequest request) {
		Map<String, Object> movieResourcesMap = getMap(request);
		if("1".equals(movieResourcesMap.get("downlodMode"))) {
			movieResourcesMap.put("downlodMode", true);
		}else {
			movieResourcesMap.put("downlodMode", false);
		}
		String a = movieResourcesMap.get("size").toString();
		int c =a.indexOf('.');
		String  b = null;
		if (c != -1) {
			b = a.substring(0,c);
			movieResourcesMap.put("size", Integer.valueOf(b));
		}else {
			movieResourcesMap.put("size", null);
		}
		ResponseDTO responseDTO = movieResourcesService.update(movieResourcesMap);
		LOGGER.info("------------------修改电影来源：" + responseDTO.isSuccess()+ "---------------------");
		responseDTO.setMessage("修改成功");
		return super.toJsonResult(responseDTO);
	}
	
	/**
	 * 删除电影来源
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:53
	 */
	@RequestMapping("/del")
	@Description("删除电影来源")
	public String delete(String id,Model model) {
		String[] ids=id.split(",");
		ResponseDTO responseDTO=movieResourcesService.delete(ids);
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("movieResources", map);
	    return "redirect:/admin/movieResources/list";
	}
}
