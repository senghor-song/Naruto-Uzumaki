package com.xiaoyi.ssm.controller.pc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassNotice;
import com.xiaoyi.ssm.model.MassNoticeEmp;
import com.xiaoyi.ssm.service.MassNoticeService;

/**
 * @Description: 公告控制器
 * @author 宋高俊
 * @date 2018年6月25日 下午6:31:58
 */
@Controller
@RequestMapping("/massNotice")
public class MassNoticeController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MassNoticeService massNoticeService;

	/**
	 * @Description: 公告列表页
	 * @author 宋高俊
	 * @date 2018年6月27日 下午2:56:58
	 */
	@RequestMapping(value = "/noticeList")
	public String noticeList() {
		return "notice/noticeList";
	}

	/**
	 * @Description: 公告数据页
	 * @author 宋高俊
	 * @date 2018年6月27日 下午1:46:03
	 */
	@RequestMapping(value = "/ajaxList")
	public String ajaxList(Model model, String noticeType, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<MassNotice> massNotices = massNoticeService.selectByEmpLIst(employee.getId(), noticeType);
		model.addAttribute("massNotices", massNotices);
		return "notice/ajaxList";
	}

	/**
	 * @Description: 公告详情页
	 * @author 宋高俊
	 * @date 2018年6月27日 下午1:46:03
	 */
	@RequestMapping(value = "/details")
	public String details(Model model, String noticeId, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		MassNotice massNotice = massNoticeService.selectByPrimaryKey(noticeId);
		model.addAttribute("massNotice", massNotice);
		MassNoticeEmp massNoticeEmp = massNoticeService.selectByEmpAndNotice(massNotice.getId(), employee.getId());
		if (massNoticeEmp.getState().equals(new Byte("0"))) {
			massNoticeService.updateByEmpAndNotice(massNotice.getId(), employee.getId());
		}
		return "notice/details";
	}

}
