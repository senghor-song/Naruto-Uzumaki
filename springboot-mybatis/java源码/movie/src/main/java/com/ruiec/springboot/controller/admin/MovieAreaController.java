package com.ruiec.springboot.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lm.com.framework.webmvc.result.JsonResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.impl.MovieAreaServiceImpl;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影后台控制器
 * @author 陈靖原<br>
 * @date 2017年11月15日 下午7:50:08
 */
@Controller
@RequestMapping("/admin/movieArea")
public class MovieAreaController extends BaseController{

	@Resource
	private MovieAreaServiceImpl movieAreaService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieAreaController.class);

	/**
	 * 电影地域列表页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12 
	 */
	@RequestMapping(value = { "" , "/","list" })
	@Description("电影地域列表页")
	public String list(HttpServletRequest request, Model model, PageBean pageBean) {
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		ResponseDTO responseDTO = movieAreaService.selectAll();
		// 分页数据
		List<Map<String, Object>> movieAreaList = responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(movieAreaList);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(movieAreaList);
		model.addAttribute("pageBean", pageBean);
		return "admin/movieArea/list";
	}
	
	/**
	 * 电影地域新增页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping(value = { "/add" })
	@Description("电影地域新增页")
	public String add(HttpServletRequest request) {
	    return "admin/movieArea/add";
	}
	
	/**
	 * 电影地域新增
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@PostMapping(value = { "/insert" })
	@ResponseBody
	public JsonResult save(HttpServletRequest request, Model model) {
		Map<String, Object> movieAreaMap = getMap(request);
		movieAreaMap.put("createOn", System.currentTimeMillis());
		ResponseDTO responseDTO = movieAreaService.add(movieAreaMap);
		LOGGER.info("------------------新增地域电影："+responseDTO.isSuccess()+"---------------------");
		responseDTO.setMessage("添加成功");
		return super.toJsonResult(responseDTO);
	}
	
	/**
	 * 电影地域编辑页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 上午10:32:33
	 */
	@RequestMapping(value = { "/edit" })
	@Description("电影地域编辑页")
	public String edit(String id,Model model) {
		ResponseDTO responseDTO=movieAreaService.selectById(Long.valueOf(id));
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("movieArea", map);
	    return "/admin/movieArea/update";
	}
	
	/**
	 * 电影地域修改
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@PostMapping(value = { "/update" })
	@ResponseBody
	public JsonResult update(HttpServletRequest request) {
		Map<String, Object> movieAreaMap = getMap(request);
		ResponseDTO responseDTO = movieAreaService.update(movieAreaMap);
		LOGGER.info("------------------修改电影：" + responseDTO.isSuccess()+ "---------------------");
		responseDTO.setMessage("修改成功");
		return super.toJsonResult(responseDTO);
	}
	
	/**
	 * 删除电影地域
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:53
	 */
	@RequestMapping("/del")
	@Description("删除电影地域")
	public String delete(String id,Model model) {
		String[] ids=id.split(",");
		ResponseDTO responseDTO=movieAreaService.delete(ids);
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("movieArea", map);
	    return "redirect:/admin/movieArea/list";
	}
}
