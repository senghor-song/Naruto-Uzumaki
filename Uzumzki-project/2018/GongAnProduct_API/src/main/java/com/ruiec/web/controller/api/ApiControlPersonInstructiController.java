package com.ruiec.web.controller.api;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonInstructiService;
import com.ruiec.web.service.OperationLogService;

/**
 * 重点人员预警指令控制器
 * 
 * @author qinzhitian<br>
 * @date 2018年1月9日 下午2:09:08
 */
@Controller("apiControlPersonInstructiController")
@RequestMapping("/api/admin/controlPersonInstructi")
public class ApiControlPersonInstructiController {
	private static final Logger LOGGER = Logger.getLogger(ApiControlPersonInstructiController.class);

	@Resource
	private ControlPersonInstructiService controlPersonInstructiService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 预警签收记录
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月9日 下午2:09:55
	 */
	@RequestMapping("/instructionRecord")
	@ResponseBody
	public ApiReturn instructionRecord(HttpServletRequest request, Integer id, Page page) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			// 根据关联预警的ID查询指令
			Map<String, Object> map = controlPersonInstructiService.instructionRecord(id, page);
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看下发签收记录列表",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询预警签收记录成功", map);
		} catch (Exception e) {
			LOGGER.error("查询预警签收记录失败", e);
			return new ApiReturn(400, "查询预警签收记录失败");
		}
	}
}
