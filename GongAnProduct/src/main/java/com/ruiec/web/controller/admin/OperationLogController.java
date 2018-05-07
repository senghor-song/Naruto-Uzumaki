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

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.entity.OperationLog;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.OperationLogService;

/**
 * 操作日志控制器
 * 
 * @author yuankai Date 2017-12-05
 * */
@Controller
@RequestMapping("/admin/operationLog")
public class OperationLogController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(OperationLogController.class);

	@Resource
	private OperationLogService operationLogService;

	/**
	 * 分页查询操作日志
	 * 
	 * @author yuankai 
	 * @data 2017年12月7日 下午5:00:43
	 * */
	@RequestMapping("/list")
	public String list(Model model, Page page, OperationLog operationLog, String startDate, String endDate, String policeName, HttpServletRequest request) {
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		try {
			// 按操作用户模糊查询
			if (StringUtils.isNotBlank(policeName)) {
				page.add(Filter.like("userId", policeName));
				model.addAttribute("policeName", policeName);
			}
			// 按所在单位模糊查询
			if (operationLog.getUnitName() != null) {
				page.add(Filter.like("unitName", operationLog.getUnitName()));
			}
			// 按ip模糊查询
			if (operationLog.getIp() != null) {
				page.add(Filter.like("ip", operationLog.getIp()));
			}
			// 按备注模糊查询
			if (operationLog.getRemark() != null) {
				page.add(Filter.like("remark", operationLog.getRemark()));
			}
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
		} catch (Exception e) {
			LOGGER.error("分页查询操作日志" + e);
		}
		page.add(Sort.desc("id"));
		page = operationLogService.findByPage(page);
		model.addAttribute("page", page);
		model.addAttribute("operationLog", operationLog);
		// 插入操作日志
//		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了操作日志",
//				LogUtil.getData(request.getParameterMap()));
		return "/admin/operationLog/list";
	}
}
