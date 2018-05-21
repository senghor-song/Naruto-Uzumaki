package com.ruiec.springboot.controller.admin;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.springboot.controller.BaseController;
import com.ruiec.springboot.service.TvTypeService;
import com.ruiec.springboot.util.JsonReturn;
import com.ruiec.springboot.util.Message;
import com.ruiec.springboot.util.PageBean;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电视剧后台控制器
 * @author qinzhitian<br>
 * @date 2017年11月15日 下午7:50:08
 */
@Controller
@RequestMapping("/admin/tvType")
public class TvTypeController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TvTypeController.class);

	@Resource
	private TvTypeService tvTypeService;

	/**
	 * 电视剧类型列表页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@RequestMapping(value = { "" , "/","list" })
	@Description("电视剧类型列表页")
	public String list(HttpServletRequest request, Model model, PageBean pageBean) {
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		ResponseDTO responseDTO = tvTypeService.selectAll();
		// 分页数据
		List<Map<String, Object>> tvTypeList = responseDTO.getData();
		// 分页模型
		PageInfo pageInfo = new PageInfo(tvTypeList);
		// 分页模型
		pageBean.setTotalNum((int) pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		pageBean.setItems(tvTypeList);
		model.addAttribute("pageBean", pageBean);
		return "admin/tvType/list";
	}
	
	/**
	 * 电视剧类型新增页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/add")
	@Description("电视剧类型新增页")
	public String add(HttpServletRequest request) {
	    return "admin/tvType/add";
	}
	
	/**
	 * 电视剧类型新增页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:42:35
	 */
	@RequestMapping("/save")
	@Description("电视剧类型新增页")
	public String save(HttpServletRequest request, Model model) {
		Map<String, Object> tvTypeMap = getMap(request);
		tvTypeMap.put("createOn", System.currentTimeMillis());
		ResponseDTO responseDTO = tvTypeService.add(tvTypeMap);
		LOGGER.info("------------------新增类型电视剧："+responseDTO.isSuccess()+"---------------------");
		return "redirect:list";
	}
	
	/**
	 * 电视剧类型编辑页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 上午10:32:33
	 */
	@RequestMapping("/edit")
	@Description("电视剧类型编辑页")
	public String edit(HttpServletRequest request, Long id, Model model) {
		if (id != null) {
			ResponseDTO responseDTO = tvTypeService.selectById(id);
			Map<String, Object> obj = responseDTO.getData();
			LOGGER.info("------------------电视剧类型编辑：" + responseDTO.isSuccess() + "---------------------");
			model.addAttribute("tvType", obj);
		}
		return "admin/tvType/edit";
	}
	
	/**
	 * 电视剧类型修改页
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午2:43:12
	 */
	@RequestMapping("/update")
	@Description("电视剧类型修改页")
	public String update(HttpServletRequest request, Model model) {
		Map<String, Object> tvTypeMap = getMap(request);
		ResponseDTO responseDTO = tvTypeService.update(tvTypeMap);
		LOGGER.info("------------------修改电视剧：" + responseDTO.isSuccess()+ "---------------------");
		if(responseDTO.isSuccess()){
			return "redirect:list";
		}else {
			model.addAttribute("tvType", tvTypeMap);
			return "admin/tvType/edit";
		}
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
				tvTypeService.delete(ids);
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
