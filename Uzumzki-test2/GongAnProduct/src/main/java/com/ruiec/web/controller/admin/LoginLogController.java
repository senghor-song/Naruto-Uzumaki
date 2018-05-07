package com.ruiec.web.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.LoginLogService;
import com.ruiec.web.service.OperationLogService;

/**
 * 登录日志后台控制器
 * 
 * @author qinzhitian<br>
 * @date 2017年12月5日 上午8:54:23
 */
@Controller
@RequestMapping("/admin/loginLog")
public class LoginLogController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(LoginLogController.class);

	@Resource
	private LoginLogService loginLogService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 分页查询登录日志
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月5日 上午9:07:01
	 */
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request, Page page, String startDate, String endDate, String name, String ip) {
		User user = (User) request.getSession().getAttribute("user");
		if (user.getId() != 1) {
			LOGGER.info("权限不足");
			return "redirect:/admin/login.shtml";
		}
		try {
			// 开始时间
			if (StringUtils.isNotBlank(startDate)) {
				model.addAttribute("startDate", startDate);
				startDate = startDate + ":00";
				Date beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
				page.add(Filter.ge("createDate", beginDate));
			}
			// 结束时间
			if (StringUtils.isNotBlank(endDate)) {
				model.addAttribute("endDate", endDate);
				endDate = endDate + ":59";
				Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
				page.add(Filter.le("createDate", endTime));
			}
			// 用户名
			if (StringUtils.isNotBlank(name)) {
				page.add(Filter.like("userName", name));
				model.addAttribute("name", name);
			}
			// 登录ip
			if (StringUtils.isNotBlank(ip)) {
				page.add(Filter.like("ip", ip));
				model.addAttribute("ip", ip);
			}

			page.add(Sort.desc("createDate"));
			page = this.loginLogService.findByPage(page);
			model.addAttribute("page", page);
			// 插入日志
//			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看登录日志",
//					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("分页查询登录日志错误" + e);
		}
		return "/admin/loginLog/list";
	}

	/**
	 * 删除登录日志
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月5日 上午9:06:02
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonReturn delete(HttpServletRequest request, Integer[] ids) {
		User user = (User) request.getSession().getAttribute("user");
		if (user.getId() != 1) {
			LOGGER.info("权限不足");
			return new JsonReturn(400, "权限不足");
		}
		try {
			if (ids != null && ids.length > 0) {
				loginLogService.delete(ids);
				operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName() + "删除登录日志",
						LogUtil.getData(request.getParameterMap()));
			} else {
				return new JsonReturn(400, "请选择要删除的选项");
			}
		} catch (Exception e) {
			LOGGER.error("删除登录日志失败" + e);
			return new JsonReturn(400, "删除失败");
		}
		return new JsonReturn(200, "删除成功");
	}
}
