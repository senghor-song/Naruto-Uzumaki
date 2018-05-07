package com.ruiec.web.controller.admin;

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
import com.ruiec.web.entity.DbShow;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DbShowService;
import com.ruiec.web.service.OperationLogService;

/**
 * 数据展示控制器
 * 
 * @author yuankai Date 2017年12月01
 * */
@Controller
@RequestMapping("/admin/dbShow")
public class DbShowController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(DbShowController.class);

	@Resource
	private DbShowService dbShowService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 分页查询数据展示列表
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午10:03:29
	 * @param pid
	 *            父级id
	 */
	@RequestMapping(value = "list")
	public String list(Page page, Model model, String pid) {
		try {
			// 获取登录用户
//			User user = (User) request.getSession().getAttribute("user");
			if (StringUtils.isNotBlank(pid)) {
				page.add(Filter.eq("parentId", Integer.parseInt(pid)));
				model.addAttribute("pid", pid);
			} else {
				page.add(Filter.isNull("parentId"));
			}
			page = dbShowService.findByPage(page);
			model.addAttribute("page", page);
			// 插入日志
//			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看数据展示列表",
//					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("数据展示分页查询失败" + e);
			throw new RuntimeException("数据展示分页查询失败" + e);
		}
		return "/admin/dbShow/list";
	}

	/**
	 * 数据展示新增初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午10:03:07
	 */
	@RequestMapping(value = "add")
	public String add(Model model, String pid) {
		if (StringUtils.isNotBlank(pid)) {
			model.addAttribute("pid", pid);
		}
		return "/admin/dbShow/add";
	}

	/**
	 * 数据展示编辑初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月29日 上午10:02:51
	 */
	@RequestMapping(value = "edit")
	public String edit(Integer id, Model model) {
		try {
			DbShow dbShow = dbShowService.get(id);
			model.addAttribute("dbShow", dbShow);
			return "/admin/dbShow/edit";
		} catch (Exception e) {
			LOGGER.error("数据展示编辑初始化失败" + e);
			throw new RuntimeException("数据展示编辑初始化失败" + e);
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
	public JsonReturn save(HttpServletRequest request, DbShow dbShow) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (StringUtils.isNotBlank(dbShow.getDbName()) && StringUtils.isNotBlank(dbShow.getName())) {
				dbShowService.save(dbShow);
				// 插入日志
				operationLogService.insertOperationLogs(user, 1, request.getRequestURI().toString(), user.getPoliceName() + "新增数据展示",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "添加成功");
			}
			return new JsonReturn(400, "添加失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据展示添加失败" + e);
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
	public JsonReturn update(HttpServletRequest request, Model model, DbShow dbShow) {
		try {
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			if (user.getId() != 1) {
				LOGGER.info("权限不足");
				return new JsonReturn(400, "权限不足");
			}
			if (StringUtils.isNotBlank(dbShow.getDbName()) && StringUtils.isNotBlank(dbShow.getName())) {
				dbShowService.update(dbShow);
				// 插入日志
				operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + "修改数据展示",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "修改成功");
			}
			return new JsonReturn(400, "修改失败,数据不能为空");
		} catch (Exception e) {
			LOGGER.error("数据展示修改失败" + e);
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
				dbShowService.deleteByIds(ids);
				// 插入日志
				operationLogService.insertOperationLogs(user, 2, request.getRequestURI().toString(), user.getPoliceName() + "删除数据展示",
						LogUtil.getData(request.getParameterMap()));
				return new JsonReturn(200, "删除成功");
			}
			return new JsonReturn(400, "请选择要删除的数据");
		} catch (Exception e) {
			LOGGER.error("数据展示删除失败" + e);
			return new JsonReturn(400, "删除失败!该条数据被其他地方所引用!");
		}
	}

	/**
	 * 下级数据校验
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月22日 上午9:30:17
	 */
	@RequestMapping("/check")
	@ResponseBody
	public JsonReturn check(Integer[] ids) {
		try {
			if (ids.length > 0) {
				List<DbShow> dbShows = dbShowService.findList(null, Filter.in("parentId", ids), null);
				if (dbShows.size() > 0) {
					return new JsonReturn(200, "此数据含有下级数据");
				}
				return new JsonReturn(300, "此数据没有下级数据");
			}
			return new JsonReturn(400, "删除失败!请选择要删除的数据");
		} catch (Exception e) {
			LOGGER.error("数据展示删除失败" + e);
			return new JsonReturn(400, "删除失败!该条数据被其他地方所引用!");
		}
	}
}
