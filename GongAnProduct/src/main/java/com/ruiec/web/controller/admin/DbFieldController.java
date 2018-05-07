package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.DbField;
import com.ruiec.web.entity.DbShow;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DbFieldService;
import com.ruiec.web.service.DbShowService;
import com.ruiec.web.service.OperationLogService;

/**
 * 数据导入字段控制器
 * 
 * @author yuankai Date 2017年12月01
 * */
@Controller
@RequestMapping("/admin/dbField")
public class DbFieldController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(DbFieldController.class);

	@Resource
	private DbFieldService dbFieldService;
	@Resource
	private DbShowService dbShowService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 查询数据导入字段列表
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:41:54
	 */
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Page page, Model model, Integer id, Integer dbId) {
		try {
			page.add(Filter.eq("dbTable.id", id));
			page = dbFieldService.findByPage(page);
			model.addAttribute("page", page);
			// 数据源id
			model.addAttribute("dbId", dbId);
			// 数据导入表id
			model.addAttribute("tid", id);
		} catch (Exception e) {
			LOGGER.error("数据导入字段分页查询失败" + e);
			throw new RuntimeException("数据导入字段分页查询失败" + e);
		}
		return "/admin/dbField/list";
	}

	/**
	 * 新增数据导入表初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:42:16
	 */
	@RequestMapping(value = "add")
	public String add(Model model, Integer dbId, Integer tid) {
		try {
			// 查询可导入字段
			List<DbShow> dbShows = dbShowService.findAddShow(tid);
			model.addAttribute("tid", tid);
			model.addAttribute("dbId", dbId);
			model.addAttribute("dbShows", dbShows);
			return "/admin/dbField/add";
		} catch (Exception e) {
			throw new RuntimeException("添加字段初始化失败" + e);
		}
	}

	/**
	 * 编辑数据导入字段
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:42:40
	 */
	@RequestMapping(value = "edit")
	public String edit(Integer id, Model model, Integer dbId, Integer tid) {
		try {
			// 查询可导入字段
			DbField dbField = dbFieldService.get(id);
			// 查询可导入字段
			List<DbShow> dbShows = dbShowService.findAddShow(tid);
			dbShows.add(dbField.getDbShow());
			model.addAttribute("tid", tid);
			model.addAttribute("dbId", dbId);
			model.addAttribute("dbShows", dbShows);
			model.addAttribute("dbField", dbField);
			return "/admin/dbField/edit";
		} catch (Exception e) {
			throw new RuntimeException("编辑字段初始化失败");
		}
	}

	/**
	 * 添加
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午6:05:28
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn save(HttpServletRequest request, DbField dbField) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (dbField.getDbShow() != null && StringUtils.isNotBlank(dbField.getFieldName()) && dbField.getDbTable() != null) {
				if (!checkField(dbField)) {
					return new JsonReturn(400, "该字段已存在");
				}
				dbFieldService.save(dbField);
				// 插入日志
				operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "新增数据导入字段",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "添加成功");
			}
			return new JsonReturn(400, "添加失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据导入字段添加失败" + e);
			return new JsonReturn(400, "添加失败");
		}

	}

	/**
	 * 修改
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月19日 下午3:09:51
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn update(HttpServletRequest request, Model model, DbField dbField) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (dbField.getDbShow() != null && StringUtils.isNotBlank(dbField.getFieldName()) && dbField.getDbTable() != null) {
				if (!checkField(dbField)) {
					return new JsonReturn(400, "该字段已存在");
				}
				dbFieldService.update(dbField);
				// 插入日志
				operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + "修改数据导入字段",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "修改成功");
			}
			return new JsonReturn(400, "修改失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据导入字段修改失败" + e);
			return new JsonReturn(400, "修改失败");
		}
	}

	/**
	 * 删除
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月19日 下午3:10:06
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonReturn delete(HttpServletRequest request, Integer[] ids) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (ids.length > 0) {
				dbFieldService.delete(ids);
				// 插入日志
				operationLogService.insertOperationLogs(user, 2, request.getRequestURI().toString(), user.getPoliceName() + "删除数据导入字段",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "删除成功");
			}
			return new JsonReturn(400, "删除失败!请选择要删除的数据");
		} catch (Exception e) {
			LOGGER.error("数据导入字段删除失败" + e);
			return new JsonReturn(400, "删除失败!该条数据被其他地方所引用!");
		}
	}
	
	/**
	 * 检查字段名是否重复
	 * @author qinzhitian<br>
	 * @date 2018年2月5日 上午11:27:14
	 */
	public boolean checkField(DbField dbField) {
		List<Filter> filters = new ArrayList<>();
		filters.add(Filter.eq("dbTable.id", dbField.getDbTable().getId()));
		filters.add(Filter.eq("fieldName", dbField.getFieldName()));
		if (dbField.getId() != null) {
			filters.add(Filter.ne("id", dbField.getId()));
		}
		dbField = dbFieldService.get(filters);
		if (dbField != null && dbField.getId() != null) {
			return false;
		}
		return true;
	}
}
