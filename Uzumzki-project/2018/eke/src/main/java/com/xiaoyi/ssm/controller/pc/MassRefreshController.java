package com.xiaoyi.ssm.controller.pc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.MassRefreshDto;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassRefreshLog;
import com.xiaoyi.ssm.service.MassRefreshLogService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 云刷新的控制器
 * @author 宋高俊
 * @date 2018年7月4日 下午2:11:59
 */
@Controller
@RequestMapping("/massRefresh")
public class MassRefreshController {
	@Autowired
	private MassRefreshLogService massRefreshLogService;
	
	/**
	 * @Description: 刷新日志的列表
	 * @author 宋高俊
	 * @date 2018年7月4日 下午2:13:49
	 */
	@RequestMapping("/refreshLogList")
	public String refreshLogList(Model model, String tabIndex) {
		List<String> list = new ArrayList<String>();
		Date newDate = new Date();
		list.add(DateUtil.getFormat(newDate, "MM月dd号"));
		list.add(DateUtil.getFormat(DateUtil.getPreTime(newDate, 3, -1), "MM月dd号"));
		list.add(DateUtil.getFormat(DateUtil.getPreTime(newDate, 3, -2), "MM月dd号"));
		list.add(DateUtil.getFormat(DateUtil.getPreTime(newDate, 3, -3), "MM月dd号"));
		list.add(DateUtil.getFormat(DateUtil.getPreTime(newDate, 3, -4), "MM月dd号"));
		list.add(DateUtil.getFormat(DateUtil.getPreTime(newDate, 3, -5), "MM月dd号"));
		list.add(DateUtil.getFormat(DateUtil.getPreTime(newDate, 3, -6), "MM月dd号"));
		model.addAttribute("dateList", list);
		model.addAttribute("tabIndex", tabIndex);
		return "cloudRefersh/refreshLogList";
	}
	
	/**
	 * @Description: 异步请求刷新日志的列表
	 * @author 宋高俊
	 * @date 2018年7月4日 下午2:13:49
	 */
	@RequestMapping("/ajaxRefreshLogList")
	public String ajaxRefreshLogList(Model model ,HttpServletRequest request, MassRefreshDto massRefreshDto,PageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), 10);
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		massRefreshDto.setEmpId(employee.getId());
		List<MassRefreshLog> list = massRefreshLogService.selectAll(massRefreshDto);
		pageInfo = new PageInfo<>(list);
		model.addAttribute("massRefreshDto", massRefreshDto);
		model.addAttribute("page", pageInfo);
		return "cloudRefersh/ajaxList";
	}
}
