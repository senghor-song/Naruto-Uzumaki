package com.xiaoyi.ssm.controller.pc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.BlackListEmp;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.service.BlackListEmpService;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 黑名单控制器
 * @author 宋高俊
 * @date 2018年7月5日 下午7:35:52 s
 */
@Controller
@RequestMapping("/blacklist")
public class BlacklistController {
	@Autowired
	private BlackListEmpService blackListEmpService;

	/**
	 * @Description: 黑名单列表
	 * @author 宋高俊
	 * @date 2018年7月5日 下午7:36:17
	 */
	@RequestMapping(value = "/blacklist")
	public String blacklist(Model model, HttpServletRequest request, PageInfo<BlackListEmp> pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), 15);
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		List<BlackListEmp> list = blackListEmpService.selectAll(employee.getId());
		pageInfo = new PageInfo<>(list);
		model.addAttribute("page", pageInfo);
		return "blackList/blackList";
	}

	/**
	 * @Description: 添加黑名单
	 * @author 宋高俊
	 * @date 2018年7月5日 下午7:38:56
	 */
	@RequestMapping(value = "/addBlacklist")
	@ResponseBody
	public ApiMessage addBlacklist(BlackListEmp blackListEmp, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		blackListEmp.setId(Utils.getUUID());
		blackListEmp.setEmpid(employee.getId());
		int flag = blackListEmpService.insertSelective(blackListEmp);
		if (flag == 1) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 删除黑名单
	 * @author 宋高俊
	 * @date 2018年7月5日 下午7:38:56
	 */
	@RequestMapping(value = "/delBlacklist")
	@ResponseBody
	public ApiMessage delBlacklist(String id) {
		String[] ids = id.split(",");
		int flag = 0;
		for (int i = 0; i < ids.length; i++) {
			flag += blackListEmpService.deleteByPrimaryKey(ids[i]);
		}
		if (flag == 1) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 修改黑名单
	 * @author 宋高俊
	 * @date 2018年7月5日 下午7:38:56
	 */
	@RequestMapping(value = "/updateBlacklist")
	@ResponseBody
	public ApiMessage updateBlacklist(BlackListEmp blackListEmp) {
		int flag = blackListEmpService.updateByPrimaryKeySelective(blackListEmp);
		if (flag == 1) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}
	
}
