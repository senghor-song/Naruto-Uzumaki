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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.impl.MovieAreaServiceImpl;
import com.ruiec.springboot.service.impl.MovieLanguageServiceImpl;
import com.ruiec.springboot.service.impl.MovieServiceImpl;
import com.ruiec.springboot.service.impl.MovieTypeServiceImpl;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影后台控制器
 * @author 陈靖原<br>
 * @date 2017年11月15日 下午7:50:08
 */
@Controller
@RequestMapping("/admin/movie")
public class MovieController extends BaseController{

	@Resource
	private MovieServiceImpl movieService;
	@Resource
	private MovieAreaServiceImpl movieAreaService;
	@Resource
	private MovieTypeServiceImpl movieTypeService;
	@Resource
	private MovieLanguageServiceImpl movieLanguageService;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	/**
	 * 电影列表页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12 
	 */
	@RequestMapping(value = { "" , "/","list" })
	@Description("电影列表页")
	public String list(HttpServletRequest request, Model model, PageBean pageBean) {
		PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
		Map<String, Object> map=new HashMap<String, Object>();
		ResponseDTO responseDTO = movieService.selectAll();
		// 分页数据
		List<Map<String, Object>> movies=responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(movies);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(movies);
		model.addAttribute("pageBean", pageBean);
	    return "/admin/movie/list";
	}
	
	/**
	 * 电影新增页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/add")
	@Description("电影新增页")
	public String add(HttpServletRequest request,Model model) {
		ResponseDTO areas=movieAreaService.selectAll();
		ResponseDTO types=movieTypeService.selectAll();
		ResponseDTO language=movieLanguageService.selectAll();
		model.addAttribute("areas",areas.getData());
		model.addAttribute("types",types.getData());
		model.addAttribute("language",language.getData());
	    return "admin/movie/add";
	}
	
	/**
	 * 添加电影
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/save")
	@Description("添加电影")
	public String save(HttpServletRequest request, Model model) {
		Map<String, Object> movieMap = getMap(request);
		if("on".equals(movieMap.get("isHot"))) {
			movieMap.put("isHot",true);
		}else {
			movieMap.put("isHot",false);
		}
		movieMap.put("createOn", System.currentTimeMillis());
		ResponseDTO responseDTO = movieService.add(movieMap);
		LOGGER.info("------------------新增电影："+responseDTO.isSuccess()+"---------------------");
		return list(request, model, new PageBean());
	}
	
	/**
	 * 电影编辑页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 上午10:32:33
	 */
	@RequestMapping("/edit")
	@Description("电影编辑页")
	public String edit(HttpServletRequest request, Long id, Model model) {
		if (id != null) {
			ResponseDTO responseDTO = movieService.selectById(id);
			Map<String, Object> obj = responseDTO.getData();
			LOGGER.info("------------------电影编辑：" + responseDTO.isSuccess() + "---------------------");
			model.addAttribute("movie", obj);
		}
		return "admin/movie/edit";
	}
	
	/**
	 * 电影修改页
	 * @author 陈靖原<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@PostMapping(value="/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Description("电影修改页")
	public String update(HttpServletRequest request, Model model) {
		Map<String, Object> movieMap = getMap(request);
		ResponseDTO responseDTO = movieService.update(movieMap);
		LOGGER.info("------------------修改电影：" + responseDTO.isSuccess()+ "---------------------");
		if(responseDTO.isSuccess()){
			return list(request, model, new PageBean());
		}else {
			String createOnString = request.getParameter("createOn");
			model.addAttribute("movie", movieMap);
			return "admin/movie/edit";
		}
	}
	
	/**
	 * 删除电影
	 * @author 陈靖原<br>
	 * @date 2017年12月1日 下午9:31:11
	 */
	@RequestMapping(value = { "/del" })
	private String del(String id,Model model) {
		String[] ids=id.split(",");
		ResponseDTO responseDTO=movieService.delete(ids);
		Map<String, Object> map=responseDTO.getData();
		model.addAttribute("movie", map);
	    return "redirect:/admin/movie";
	}
}
