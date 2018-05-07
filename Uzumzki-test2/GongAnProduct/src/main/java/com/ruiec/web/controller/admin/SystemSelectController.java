package com.ruiec.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.dto.AlarmDTO;
import com.ruiec.web.dto.ControlPersonDTO;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.LeavePerson;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.LeavePersonService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UserUnitService;

/**
 * 系统查询控制器
 * 
 * @author Senghor<br>
 * @date 2017年12月31日 下午4:57:13
 */
@Controller
@RequestMapping("/admin/systemSelect")
public class SystemSelectController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(SystemSelectController.class);
	@Resource
	private LeavePersonService leavePersonService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
    @Resource
    private UserUnitService userService;
	/**    
	 * 系统查询获取重点人员列表
	 * 
	 * @author yuankai<br>
	 * @date 2017年12月06日 上午9:11:01
	 */
	@RequestMapping("/controlPersonList")
	public String controlPersonList(Page page, Model model, String search, String searchType, HttpServletRequest request, ControlPerson controlPerson,
			ControlPersonDTO controlPersonDTO) {
		// 判断登录用户
		User user = (User) request.getSession().getAttribute("user");
		if (searchType == null) {
			return "/admin/systemSelect/controlPersonList";
		}
		if ("name".equals(search)) {
			page.add(Filter.like("name", searchType));
		}
		if ("idCard".equals(search)) {
			page.add(Filter.eq("idCard", searchType));
		}
		controlPersonService.findByPage(page, controlPerson, controlPersonDTO);
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("searchType", searchType);
		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "使用系统查询了重点人员列表",
				LogUtil.getData(request.getParameterMap()));
		return "/admin/systemSelect/controlPersonList";
	}
   
	/**
	 * 系统查询预警信息分页
	 * 
	 * @author yuankai 
	 * Date 2017年12月7日 下午5:00:43
	 *  @param  search 查询的类型         searchType 查询的内容
	 *        
	 * */
	@RequestMapping("/controlPersonAlarmList")
	public String controlPersonAlarmList(Page page, Model model, ControlPerson controlPerson, String search, String searchType, HttpServletRequest request,
			HttpSession session, AlarmDTO alarmDTO) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cpn = cpa.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		cpn.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		if (searchType == null) {
			return "/admin/systemSelect/controlPersonAlarmList";
		}
		// 重点人姓名
		if (searchType != null && "name".equals(search)) {
			cpn.add(Restrictions.like("name", searchType,MatchMode.ANYWHERE));
		}
		// 重点人身份证
		if (searchType != null && "idCard".equals(search)) {
			cpn.add(Restrictions.eq("idCard", searchType));
		}
		controlPersonAlarmService.findByPage(page, cpa);
		// 预警地区
		if (null != alarmDTO.getUnitId()) {
			cpa.add(Restrictions.eq("id", alarmDTO.getUnitId()));
		}
		model.addAttribute("search", search);
		model.addAttribute("searchType", searchType);
		model.addAttribute("page", page);
		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "使用系统查询了预警信息列表",
				LogUtil.getData(request.getParameterMap()));
		return "/admin/systemSelect/controlPersonAlarmList";
	}

	/**
	 * 系统查询离市人员分页信息
	 * 
	 * @author yuankai 
	 * @data 2017年12月7日 下午5:00:43
	 * */
	@RequestMapping("/leavePersonList")
	public String leavePersonList(Page page, Model model, String search, String searchType, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		User loginUser = (User) session.getAttribute("user");
	//	LoginUserUnit loginUserUnit=(LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (searchType == null || searchType.length() == 0 || "".equals(searchType)) {
			return "/admin/systemSelect/leavePersonList";
		}
		if ("name".equals(search)) {
			page.add(Filter.like("name", searchType));
		}
		if ("idCard".equals(search)) {
			page.add(Filter.eq("idCard", searchType));
		}
	  //  leavePersonService.findByPage(page);
		page=leavePersonService.findByPageOf(page);
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("searchType", searchType);
		operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName() + "使用系统查询了离市人员列表",
				LogUtil.getData(request.getParameterMap()));
		return "/admin/systemSelect/leavePersonList";
	}
}
