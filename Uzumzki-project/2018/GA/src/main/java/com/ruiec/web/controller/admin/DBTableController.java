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

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.DbField;
import com.ruiec.web.entity.DbShow;
import com.ruiec.web.entity.DbTable;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DbFieldService;
import com.ruiec.web.service.DbShowService;
import com.ruiec.web.service.DbTableService;
import com.ruiec.web.service.OperationLogService;

/**
 * 数据导入表控制器
 * 
 * @author yuankai Date 2017年12月01
 * */
@Controller
@RequestMapping("/admin/dbTable")
public class DBTableController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(DBTableController.class);

	@Resource
	private DbTableService dbTableService;
	@Resource
	private DbFieldService dbFieldService;
	@Resource
	private DbShowService dbShowService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 分页查询
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:51:01
	 * @param id
	 *            数据源主键
	 */
	@RequestMapping(value = "list")
	public String list(Page page, Model model, Integer id) {
		try {
			// 获取登录用户
			// User user = (User) request.getSession().getAttribute("user");
			// DetachedCriteria cp = DetachedCriteria.forClass(DbTable.class);
			page.add(Filter.eq("db.id", id));
			page = dbTableService.findByPage(page);
			model.addAttribute("page", page);
			model.addAttribute("dbId", id);
			// 插入日志
//			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看数据导入表列表",
//					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("数据导入表分页查询失败" + e);
			throw new RuntimeException("数据导入表分页查询失败" + e);
		}
		return "/admin/dbTable/list";
	}

	/**
	 * 新增初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:52:42
	 * @param dbId
	 *            数据源主键
	 */
	@RequestMapping(value = "add")
	public String add(Model model, Integer dbId) {
		try {
			// 查询可导入表信息
			List<DbShow> dbShows = dbShowService.findList(null, Filter.isNull("parentId"), null);
			model.addAttribute("dbId", dbId);
			model.addAttribute("dbShows", dbShows);
			return "/admin/dbTable/add";
		} catch (Exception e) {
			LOGGER.error("数据导入表新增初始化失败" + e);
			throw new RuntimeException("数据导入表新增初始化失败" + e);
		}
	}

	/**
	 * 编辑初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:54:08
	 * @param dbId
	 *            数据源主键
	 */
	@RequestMapping(value = "edit")
	public String edit(Integer id, Model model, Integer dbId) {
		try {
			// 查询数据导入表详情
			DbTable dbTable = dbTableService.get(id);
			// 查询可导入表信息
			List<DbShow> dbShows = dbShowService.findList(null, Filter.isNull("parentId"), null);

			model.addAttribute("dbId", dbId);
			model.addAttribute("dbTable", dbTable);
			model.addAttribute("dbShows", dbShows);
			return "/admin/dbTable/edit";
		} catch (Exception e) {
			LOGGER.error("数据导入表编辑初始化失败" + e);
			throw new RuntimeException("数据导入表编辑初始化失败" + e);
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
	public JsonReturn save(HttpServletRequest request, DbTable dbTable) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (StringUtils.isNotBlank(dbTable.getTableName()) && dbTable.getDbShow() != null && dbTable.getDb() != null) {
				if (!checkTable(dbTable)) {
					return new JsonReturn(400, "该表已存在");
				}
				dbTableService.save(dbTable);
				// 插入日志
				operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "新增数据导入表",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "添加成功");
			}
			return new JsonReturn(400, "添加失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据导入表添加失败" + e);
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
	public JsonReturn update(HttpServletRequest request, Model model, DbTable dbTable) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (StringUtils.isNotBlank(dbTable.getTableName()) && dbTable.getDbShow() != null && dbTable.getDb() != null) {
				if (!checkTable(dbTable)) {
					return new JsonReturn(400, "该表已存在");
				}
				dbTableService.update(dbTable);
				// 插入日志
				operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + "修改数据导入表",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "修改成功");
			}
			return new JsonReturn(400, "修改失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据导入表修改失败" + e);
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
				dbTableService.delete(ids);
				// 插入日志
				operationLogService.insertOperationLogs(user, 2, request.getRequestURI().toString(), user.getPoliceName() + "删除数据导入表",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "删除成功");
			}
			return new JsonReturn(400, "删除失败!请选择要删除的数据");
		} catch (Exception e) {
			LOGGER.error("数据导入表删除失败" + e);
			return new JsonReturn(400, "删除失败!该条数据被其他地方所引用!");
		}
	}

	/**
	 * 导入表
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月19日 下午3:10:06
	 */
	@RequestMapping("/importTable")
	@ResponseBody
	public JsonReturn importTable(HttpServletRequest request, Integer id, String startDate, String endDate) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (GlobalUnit.isOK) {
				return new JsonReturn(400, "数据导入中...");
			}
			DbTable dbTable = dbTableService.get(id, null, Fetch.alias("db", "dbAlias"));
			List<DbField> dbFieldList = dbFieldService.findList(null, Filter.eq("dbTable", dbTable), null);
			dbTable.setDbFields(dbFieldList);
			// 校验字段
			List<DbTable> dbTables = new ArrayList<>();
			dbTables.add(dbTable);
			boolean b = dbTableService.checkField(dbTables);
			if (!b) {
				return new JsonReturn(400, "请提供所有必须的字段");
			}
			GlobalUnit.isOK=true;
			// 导入数据
			Long begin = System.currentTimeMillis();
			JsonReturn jsonReturn = dbTableService.tableImport(dbTable, startDate, endDate);
			Long end = System.currentTimeMillis();
			Long time = (end - begin) / 1000;
			LOGGER.info("--------------------------------------------------------------------------------------------");
			LOGGER.info("-----------------------------------导入时间：" + time + "s-----------------------------------");
			if (jsonReturn.getCode() == 200) {
				// 插入日志
				operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "导入了"
						+ dbTable.getDbShow().getName() + "数据", LogUtil.getData(request.getParameterMap()));
			}
			GlobalUnit.isOK=false;
			return jsonReturn;
		} catch (Exception e) {
			GlobalUnit.isOK=false;
			LOGGER.error("数据导入失败" + e);
			return new JsonReturn(400, "数据导入失败");
		}
	}

	/**
	 * 导入数据弹窗
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月31日 下午4:09:47
	 */
	@RequestMapping("/importInit")
	public String tableImportInit(Model model, Integer id,String startDate ,String endDate) {
		model.addAttribute("id", id);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "/admin/dbTable/importLoading";
	}
	
	/**
	 * 检查表名是否重复
	 * @author qinzhitian<br>
	 * @date 2018年2月5日 上午11:27:14
	 */
	public boolean checkTable(DbTable dbTable) {
		List<Filter> filters = new ArrayList<>();
		filters.add(Filter.eq("db.id", dbTable.getDb().getId()));
		filters.add(Filter.eq("tableName", dbTable.getTableName()));
		if (dbTable.getId() != null) {
			filters.add(Filter.ne("id", dbTable.getId()));
		}
		dbTable = dbTableService.get(filters);
		if (dbTable!=null && dbTable.getId()!=null) {
			return false;
		}
		return true;
	}
}
