package com.ruiec.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.Db;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DbService;
import com.ruiec.web.service.OperationLogService;

/**
 * 数据导入数据源控制器
 * 
 * @author yuankai Date 2017年12月01
 * */
@Controller
@RequestMapping("/admin/db")
public class DbController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(DbController.class);

	@Resource
	private DbService dbService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 分页查询
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月19日 下午3:54:13
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn page(Page page) {
		try {
			// 获取登录用户
//			User user = (User) request.getSession().getAttribute("user");
			page = dbService.findByPage(page);
			// 插入日志
			/*operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看数据源列表",
					LogUtil.getData(request.getParameterMap()));*/
			return new JsonReturn(200, "分页查询成功", page);
		} catch (Exception e) {
			LOGGER.error("数据源分页查询失败" + e);
			return new JsonReturn(400, "分页查询失败");
		}
	}

	/**
	 * 分页查询数据源
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:23:26
	 */
	@RequestMapping(value = "list")
	public String list(Page page, Model model) {
		try {
			// 获取登录用户
//			User user = (User) request.getSession().getAttribute("user");
			page = dbService.findByPage(page);
			model.addAttribute("page", page);
			// 插入日志
			/*operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看数据源列表",
					LogUtil.getData(request.getParameterMap()));*/
		} catch (Exception e) {
			LOGGER.error("数据源分页查询失败" + e);
		}
		return "/admin/db/list";
	}

	/**
	 * 新增初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:22:41
	 */
	@RequestMapping(value = "add")
	public String add() {
		return "/admin/db/add";
	}

	/**
	 * 编辑初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午9:22:09
	 */
	@RequestMapping(value = "edit")
	public String edit(Integer id, Model model) {
		try {
			Db db = dbService.get(id);
			model.addAttribute("db", db);
			return "/admin/db/edit";
		} catch (Exception e) {
			throw new RuntimeException("编辑数据源初始化错误");
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
	public JsonReturn save(HttpServletRequest request, Db db) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (StringUtils.isNotBlank(db.getUrl()) && StringUtils.isNotBlank(db.getUserName()) && StringUtils.isNotBlank(db.getPassword())) {
				// 获取登录用户
				JsonReturn jsonReturn = dbService.saveDb(db);
				if (jsonReturn.getCode() == 200) {
					// 插入日志
					operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "新增数据源",
							LogUtil.getData(request.getParameterMap()));
				}
				return jsonReturn;
			}
			return new JsonReturn(400, "添加失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据源添加失败" + e);
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
	public JsonReturn update(HttpServletRequest request, Model model, Db db) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (StringUtils.isNotBlank(db.getUrl()) && StringUtils.isNotBlank(db.getUserName()) && StringUtils.isNotBlank(db.getPassword())) {
				JsonReturn jsonReturn = dbService.updateDb(db);
				if (jsonReturn.getCode() == 200) {
					// 插入日志
					operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + "修改数据源",
							LogUtil.getData(request.getParameterMap()));
				}
				
				return jsonReturn;
			}
			return new JsonReturn(400, "修改失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据源修改失败" + e);
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
				dbService.delete(ids);
				// 插入日志
				operationLogService.insertOperationLogs(user, 2, request.getRequestURI().toString(), user.getPoliceName() + "删除数据源",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "删除成功");
			}
			return new JsonReturn(400, "删除失败!请选择要删除的数据");
		} catch (Exception e) {
			LOGGER.error("数据源删除失败" + e);
			return new JsonReturn(400, "删除失败!该条数据被其他地方所引用!");
		}
	}

	/**
	 * 数据导入
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月24日 下午4:02:50
	 */
	@RequestMapping(value = "dataImport")
	@ResponseBody
	public JsonReturn dataImport(HttpServletRequest request, Integer id, String startDate, String endDate) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			Long begin = System.currentTimeMillis();
			JsonReturn jsonReturn = dbService.dataImport(id, startDate, endDate);
			Long end = System.currentTimeMillis();
			Long time = (end - begin) / 1000;
			LOGGER.info("--------------------------------------------------------------------------------------------");
			LOGGER.info("-----------------------------------导入时间：" + time + "s-----------------------------------");
			if (jsonReturn.getCode() == 200) {
				// 插入日志
				operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "导入数据",
						LogUtil.getData(request.getParameterMap()));
			}
			return jsonReturn;
		} catch (Exception e) {
			LOGGER.error("导入数据失败" + e);
			return new JsonReturn(400, "导入数据失败");
		}

	}

	/**
	 * 导入数据弹窗
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月31日 下午4:09:47
	 */
	@RequestMapping("/dataImportInit")
	public String dataImportInit(Model model, Integer id) {
		model.addAttribute("id", id);
		return "/admin/db/importData";
	}
}
