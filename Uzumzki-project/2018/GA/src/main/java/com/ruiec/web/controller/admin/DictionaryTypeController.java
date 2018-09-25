package com.ruiec.web.controller.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.DictionaryType;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.OperationLogService;

/**
 * 字典类型控制器
 * 
 * @author 袁凱 date 2017年11月30
 * */
@Controller
@RequestMapping("/admin/dictionaryType")
public class DictionaryTypeController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(DictionaryTypeController.class);

	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 查询字典类型分页
	 *
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:18:37
	 */
	@RequestMapping("/list")
	public String selectAll(Model model, String itemName, Page page, HttpSession session, HttpServletRequest request) {
		// 获取当前登录用户
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		Sort createDate = Sort.desc("createDate");
		List<DictionaryType> dictionaryTypes = dictionaryTypeService.findList(null, null, createDate);
		model.addAttribute("dictionaryTypes", dictionaryTypes);
		/*if (!StringUtils.isBlank(itemName)) {
			page.add(Filter.like("itemName", itemName));
		}*/
		try {
			page.add(Sort.desc("createDate"));
			model.addAttribute("itemName", itemName);
			page = dictionaryTypeService.findByPage(page);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOGGER.error("分页查询字典类型错误" + e);
		}
//		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了字典类型列表",
//				LogUtil.getData(request.getParameterMap()));
		return "/admin/dictionaryType/list";
	}

	/**
	 * 添加字典类型
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:18:45
	 */
	@RequestMapping("/add")
	public String add(Model model, DictionaryType dictionaryType) {
		return "/admin/dictionaryType/add";
	}

	/**
	 * 保存字典类型
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:18:52
	 */
	@ResponseBody
	@RequestMapping("/save")
	public JsonReturn save(DictionaryType dictionaryType, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			if ("".equals(dictionaryType.getisUse()) || null == dictionaryType.getisUse()) {
				dictionaryType.setisUse(0);
			}
			if (dictionaryType.getItemName().length() > 10) {
				return new JsonReturn(400, "字典名称不得多于10字");
			}
			if (dictionaryType.getSort() > 100) {
				return new JsonReturn(400, "排序号不得大于100");
			}
			if (dictionaryType.getDescribe().length() > 80) {
				return new JsonReturn(400, "字典描述不得多于80字");
			}
			List<DictionaryType> list = dictionaryTypeService.repeat();
			if (list.contains(dictionaryType.getItemName())) {
				return new JsonReturn(400, "字典中文名称重复");
			}
			List<DictionaryType> list2 = dictionaryTypeService.repeatEnglish();
			if (list2.contains(dictionaryType.getItemCode())) {
				return new JsonReturn(400, "字典英文名称重复");
			}
			dictionaryType = dictionaryTypeService.save(dictionaryType);
		} catch (Exception e) {
			LOGGER.error("新增字典类型错误" + e);
			return new JsonReturn(400, "新增字典类型失败！");
		}
		operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "新增了字典类型信息",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "新增字典类型新增成功！", "/admin/dictionaryType/list.shtml");
	}

	/**
	 * 删除字典类型
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:19:00
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn delete(Integer[] ids, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			dictionaryTypeService.delete(ids);
			operationLogService.insertOperationLogs(user, 2, request.getRequestURI().toString(), user.getPoliceName() + "刪除了字典类型信息",
					LogUtil.getData(request.getParameterMap()));
			return new JsonReturn(200, "字典类型删除成功！");
		} catch (Exception e) {
			return new JsonReturn(400, "删除失败!该条数据被其他地方所引用!");
		}
	}

	/**
	 * 查询字典类型是否包含字典数据
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:19:12
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public JsonReturn check(Integer[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				for (Integer id : ids) {
					List<Map<String, Object>> list = dictionaryService.findByTypeId(id, null);
					if (list.size() > 0) {
						return new JsonReturn(400, "字典类型包含字典数据，请先删除字典数据");
					}
				}
			} else {
				return new JsonReturn(400, "请选择需要删除的选项");
			}
			return new JsonReturn(200, "无字典数据");
		} catch (Exception e) {
			LOGGER.error("查询字典类型失败!" + e);
			return new JsonReturn(400, "删除失败");
		}
	}

	/**
	 * 更新字典类型初始页
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:19:23
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		DictionaryType dictionaryType = dictionaryTypeService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("dictionaryType", dictionaryType);
		return "/admin/dictionaryType/edit";
	}

	/**
	 * 更新字典类型
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:19:31
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonReturn update(DictionaryType dictionaryType, Model model, HttpSession session, HttpServletRequest request, Integer id) {

		session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			if (StringUtils.isBlank(dictionaryType.getItemName()) || dictionaryType.getId() == null || dictionaryType.getDescribe() == null
					|| dictionaryType.getSort() == null) {
				return new JsonReturn(400, "字典数据不能为空");
			}
			if (dictionaryType.getItemName().length() > 80) {
				return new JsonReturn(400, "字典名称不得多于10字");
			}
			if (dictionaryType.getSort() > 100) {
				return new JsonReturn(400, "排序号不得大于100");
			}
			if (dictionaryType.getDescribe().length() > 80) {
				return new JsonReturn(400, "字典描述不得多于80字");
			}

			List<DictionaryType> list = dictionaryTypeService.repeat();
			List<DictionaryType> lists = dictionaryTypeService.repeatList(id);
			if (lists.contains(dictionaryType.getItemName()) || !list.contains(dictionaryType.getItemName())) {
				// 更新字典
				dictionaryTypeService.update(dictionaryType);
			} else {
				if (list.contains(dictionaryType.getItemName())) {
					return new JsonReturn(400, "字典中文名称重复");
				}
			}
		} catch (Exception e) {
			LOGGER.error("更新字段类型错误" + e);
			return new JsonReturn(400, "字典数据修改失败");
		}
		operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + "更新了字典类型信息",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "字典数据修改成功", "/admin/dictionaryType/list.shtml");
	}

	/**
	 * 字典类型是否默认
	 * 
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:19:40
	 */
	public String updateIsUse(Integer id, Model model) {
		try {
			dictionaryTypeService.updateIsUse(id);
			model.addAttribute("id", id);
		} catch (Exception e) {
			LOGGER.error("字典类型失败" + e);
		}
		return "redirect:list.shtml";
	}
}
