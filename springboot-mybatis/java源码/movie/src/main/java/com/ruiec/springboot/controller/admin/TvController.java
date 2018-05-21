package com.ruiec.springboot.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.TvAreaService;
import com.ruiec.springboot.service.TvService;
import com.ruiec.springboot.service.TvTypeService;
import com.ruiec.springboot.util.IdWorker;
import com.ruiec.springboot.util.JsonReturn;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧后台控制器
 * @author qinzhitian<br>
 * @date 2017年11月15日 下午7:50:08
 */
@Controller
@RequestMapping("/admin/tv")
public class TvController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(TvController.class);
	@Resource(name="tvServiceImpl")
	private TvService tvService;
	@Resource
	private TvAreaService tvAreaService;
	@Resource
	private TvTypeService tvTypeService;
	private IdWorker idWorker = new IdWorker();

	/**
	 * 电视剧列表页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@RequestMapping(value = { "" , "/" ,"/list"})
	@Description("电视剧列表页")
	public String list(HttpServletRequest request, Model model, PageBean<Map<String, Object>> pageBean) {
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		ResponseDTO responseDTO = tvService.selectAll();
		// 分页数据
		List<Map<String, Object>> tvList = responseDTO.getData();
		// 分页模型
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(tvList);
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(tvList);
		model.addAttribute("pageBean", pageBean);
		return "admin/tv/list";
	}
	
	/**
	 * 电视剧新增页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/add")
	@Description("电视剧新增页")
	public String add(Model model) {
		// 所有地域
		ResponseDTO responseDTO = tvAreaService.selectAll();
		List<Map<String, Object>> areaList = responseDTO.getData();
		// 所有电视剧类型
		responseDTO = tvTypeService.selectAll();
		List<Map<String, Object>> typeList = responseDTO.getData();

		model.addAttribute("areaList", areaList);
		model.addAttribute("typeList", typeList);
		return "admin/tv/add";
	}
	
	/**
	 * 电视剧新增页
	 * @author qinzhitian<br>
	 * @throws ParseException 
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/save")
	@Description("电视剧新增页")
	public String save(HttpServletRequest request) throws ParseException {
		Map<String, Object> tvMap = getMap(request);
		String isHot = request.getParameter("isHot");
		if (isHot != null && !isHot.isEmpty()) {
			tvMap.put("isHot", 1);
		} else {
			tvMap.put("isHot", 0);
		}
		// 时间类型转换
		String dataStr = (String) tvMap.get("releaseDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data = sdf.parse(dataStr);
		tvMap.put("releaseDate", data.getTime());
		dataStr = (String) tvMap.get("updatedDate");
		data = sdf.parse(dataStr);
		tvMap.put("updatedDate", data.getTime());
		tvMap.put("createOn", System.currentTimeMillis());
		tvMap.put("id", idWorker.nextId());
		ResponseDTO responseDTO = tvService.add(tvMap);
		LOGGER.info("------------------测试："+responseDTO.getMessage()+"---------------------");
	    return "redirect:list";
	}
	
	/**
	 * 电视剧编辑页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 上午10:32:33
	 */
	@RequestMapping("/edit")
	@Description("电视剧编辑页")
	public String edit(HttpServletRequest request, Long id, Model model) {
		if (id != null) {
			ResponseDTO responseDTO = tvService.selectById(id);
			Map<String, Object> obj = responseDTO.getData();
			LOGGER.info("------------------测试：" + obj.get("name") + responseDTO.getMessage() + "---------------------");
			model.addAttribute("tv", responseDTO.getData());
		}
		return "admin/tv/edit";
	}
	
	/**
	 * 电视剧修改页
	 * @author qinzhitian<br>
	 * @throws ParseException 
	 * @date 2017年11月21日 下午2:43:12
	 */
	@RequestMapping("/update")
	@Description("电视剧列表页")
	public String update(HttpServletRequest request) throws ParseException {
		Map<String, Object> tvMap = getMap(request);
		String isHot = request.getParameter("isHot");
		if (isHot != null && !isHot.isEmpty()) {
			tvMap.put("isHot", 1);
		} else {
			tvMap.put("isHot", 0);
		}
		// 时间类型转换
		String dataStr = (String) tvMap.get("releaseDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data = sdf.parse(dataStr);
		tvMap.put("releaseDate", data.getTime());
		dataStr = (String) tvMap.get("updatedDate");
		data = sdf.parse(dataStr);
		tvMap.put("updatedDate", data.getTime());
		tvMap.put("createOn", System.currentTimeMillis());
		tvService.update(tvMap);
	    return "redirect:list";
	}
	
	/**
	 * 删除电视剧
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:42:53
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonReturn delete(String[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				tvService.delete(ids);
			}else {
				return new JsonReturn(400, "请选择需要删除的选项");
			}
		} catch (Exception e) {
			LOGGER.error("删除电视剧失败!", e);
			return new JsonReturn(400, "删除失败");
		}
		return new JsonReturn(200, "删除成功");
	}
}
