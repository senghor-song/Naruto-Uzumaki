package com.ruiec.web.controller.admin;

import java.util.List;
import java.util.Map;

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
import com.ruiec.framework.web.support.controller.BaseController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.LeavePersonService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserUnitService;

/**
 * 离市人员控制器
 * 
 * @author qinzhitian<br>
 * @date 2017年12月6日 下午8:13:36
 */
@Controller
@RequestMapping("/admin/leavePerson")
public class LeavePersonController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(LeavePersonController.class);

	@Resource
	private LeavePersonService leavePersonService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private UnitService unitService;
	@Resource
	private APIConfigService apiConfigService;

	/**
	 * 分页查询离市人员
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午8:23:45
	 */
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Model model, Page page, String startDate, String dynamicInfoId, String endDate, String isSign, String unitId) {
		User loginUser = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		try {
			// 开始时间
			if (StringUtils.isNotBlank(startDate)) {
				page.add(Filter.ge("departureTime", startDate));
				model.addAttribute("startDate", startDate);
			}
			// 结束时间
			if (StringUtils.isNotBlank(endDate)) {
				page.add(Filter.le("departureTime", endDate));
				model.addAttribute("endDate", endDate);
			}
			// 是否签收
			if (StringUtils.isNotBlank(isSign)) {
				page.add(Filter.eq("isSign", isSign));
				model.addAttribute("isSign", isSign);
			}

			// 轨迹类型
			if (StringUtils.isNotBlank(dynamicInfoId)) {
				page.add(Filter.eq("dynamicInfoId", Integer.parseInt(dynamicInfoId)));
				model.addAttribute("dynamicInfoId", Integer.parseInt(dynamicInfoId));
			}

			// 单位id
			if (StringUtils.isNotBlank(unitId)) {
				List<Integer> ids = unitService.findSonIds(Integer.parseInt(unitId));
				page.add(Filter.in("nativePlacePoliceId", ids.toArray(new Integer[ids.size()])));
				model.addAttribute("unitId", Integer.parseInt(unitId));
			}
			// 查询单位列表
			List<Map<String, Object>> unitList = userUnitService.findListByUser(loginUser);
			model.addAttribute("unitList", unitList);

			// 查询所有轨迹类型
			List<Map<String, Object>> dynamicInfoList = apiConfigService.getApiConfig();
			model.addAttribute("dynamicInfoList", dynamicInfoList);

			// 分页查询离市人员列表
			page = leavePersonService.findByPageOfLeavePerson(page, loginUser, loginUserUnit);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOGGER.error("离市人员分页查询失败" + e);
			throw new RuntimeException("离市人员分页查询失败");
		}
		// 插入日志
//		operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURL().toString(), loginUser.getPoliceName() + "查询离市人员",
//				LogUtil.getData(request.getParameterMap()));
		return "/admin/leavePerson/list";
	}

	/**
	 * 删除离市人员
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年11月30日 下午3:00:42
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonReturn delete(HttpServletRequest request, Integer[] ids) {
		try {
			if (ids != null && ids.length > 0) {
				leavePersonService.delete(ids);
			} else {
				return new JsonReturn(400, "请选择要删除的选项");
			}
		} catch (Exception e) {
			LOGGER.error("删除离市人员失败!" + e);
			return new JsonReturn(400, "删除失败");
		}
		// 插入日志
		User user = (User) request.getSession().getAttribute("user");
		operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName() + "删除离市人员",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "删除成功");
	}
}
