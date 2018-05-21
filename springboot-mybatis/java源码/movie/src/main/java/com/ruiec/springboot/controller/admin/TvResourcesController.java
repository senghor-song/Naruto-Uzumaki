package com.ruiec.springboot.controller.admin;

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
import com.ruiec.springboot.service.TvResourcesService;
import com.ruiec.springboot.service.TvService;
import com.ruiec.springboot.util.JsonReturn;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧资源后台控制器
 * @author qinzhitian<br>
 * @date 2017年11月15日 下午7:50:08
 */
@Controller
@RequestMapping("/admin/tvResources")
public class TvResourcesController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TvResourcesController.class);

	@Resource
	private TvResourcesService tvResourcesService;
	@Resource
	private TvService tvService;
	

	/**
	 * 电视剧资源列表页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@RequestMapping(value = { "" , "/","list" })
	@Description("电视剧资源列表页")
	public String list(HttpServletRequest request, Model model, PageBean<Map<String, Object>> pageBean) {
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		ResponseDTO responseDTO = tvResourcesService.selectAll();
		// 分页数据
		List<Map<String, Object>> tvResourcesList = responseDTO.getData();
		// 分页模型
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(tvResourcesList);
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(tvResourcesList);
		model.addAttribute("pageBean", pageBean);
		return "admin/tvResources/list";
	}
	
	/**
	 * 电视剧资源新增页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/add")
	@Description("电视剧资源新增页")
	public String add(HttpServletRequest request, Model model) {
		ResponseDTO responseDTO = tvService.selectAll();
		List<Map<String, Object>> tvs = responseDTO.getData();
		
		model.addAttribute("tvs", tvs);
	    return "admin/tvResources/add";
	}
	
	/**
	 * 电视剧资源新增页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/save")
	@Description("电视剧资源新增页")
	public String save(HttpServletRequest request, Model model) {
		Map<String, Object> tvResourcesMap = getMap(request);
		tvResourcesMap.put("createOn", System.currentTimeMillis());
		ResponseDTO responseDTO = tvResourcesService.add(tvResourcesMap);
		LOGGER.info("------------------新增资源电视剧："+responseDTO.isSuccess()+"---------------------");
		return "redirect:list";
	}
	
	/**
	 * 电视剧资源编辑页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 上午10:32:33
	 */
	@RequestMapping("/edit")
	@Description("电视剧资源编辑页")
	public String edit(HttpServletRequest request, Long id, Model model) {
		if (id != null) {
			ResponseDTO responseDTO = tvResourcesService.selectById(id);
			Map<String, Object> obj = responseDTO.getData();
			LOGGER.info("------------------电视剧资源编辑：" + responseDTO.isSuccess() + "---------------------");
			model.addAttribute("tvResources", obj);
		}
		ResponseDTO responseDTO = tvService.selectAll();
		List<Map<String, Object>> tvs = responseDTO.getData();
		model.addAttribute("tvs", tvs);
		return "admin/tvResources/edit";
	}
	
	/**
	 * 电视剧资源修改页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@PostMapping(value="/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Description("电视剧资源修改页")
	public String update(HttpServletRequest request, Model model) {
		Map<String, Object> tvResourcesMap = getMap(request);
		ResponseDTO responseDTO = tvResourcesService.update(tvResourcesMap);
		LOGGER.info("------------------修改电视剧资源：" + responseDTO.isSuccess()+ "---------------------");
		if(responseDTO.isSuccess()){
			return "redirect:list";
		}else {
			//String createOnString = request.getParameter("createOn");
			model.addAttribute("tvResources", tvResourcesMap);
			return "admin/tvResources/edit";
		}
	}
	
	/**
	 * 删除电视剧资源
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:42:53
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonReturn delete(String[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				tvResourcesService.delete(ids);
			}else {
				return new JsonReturn(400, "请选择需要删除的选项");
			}
		} catch (Exception e) {
			LOGGER.error("删除电视剧资源失败!", e);
			return new JsonReturn(400, "删除失败");
		}
		return new JsonReturn(200, "删除成功");
	}
}
