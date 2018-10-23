package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.StaffLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.StaffLog;
import com.xiaoyi.ssm.service.StaffService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 管理员控制器
 * @author 宋高俊
 * @date 2018年7月25日 下午2:43:06
 */
@Controller(value = "adminStaffController")
@RequestMapping(value = "admin/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;
	@Autowired
	private StaffLogMapper staffLogMapper;

	/**
	 * @Description: 登录方法
	 * @author 宋高俊
	 * @date 2018年7月25日 下午2:37:32
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage login(HttpServletRequest request, Staff staff) {
		Staff loginStaff = staffService.login(staff);
		if (loginStaff != null) {
			if ("正常".equals(loginStaff.getStatus())) {
				Staff updateLogin = new Staff();
				updateLogin.setStaffid(loginStaff.getStaffid());
				updateLogin.setLoginchange(new Date());
				staffService.updateByPrimaryKeySelective(updateLogin);
				request.getSession().setAttribute("adminloginuser", loginStaff);
				return new ApiMessage(200, "登录成功");
			} else {
				return new ApiMessage(400, "账号" + loginStaff.getStatus() + ",请联系工作人员!");
			}
		} else {
			return new ApiMessage(400, "账号不存在,请检查账号密码是否正确");
		}
	}

	/**
	 * @Description: 退出登录
	 * @author 宋高俊
	 * @date 2018年7月25日 下午2:37:32
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage loginout(HttpServletRequest request, Staff staff) {
		request.getSession().removeAttribute("adminloginuser");
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 修改密码
	 * @author 宋高俊
	 * @date 2018年8月2日 上午9:45:46
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage updatePassword(HttpServletRequest request, String oldPassword, String newPassword) {
		Staff staff = (Staff) request.getSession().getAttribute("adminloginuser");
		if (staff.getPassword().equals(oldPassword)) {
			Staff updateStaff = new Staff();
			updateStaff.setStaffid(staff.getStaffid());
			updateStaff.setPassword(newPassword);
			staffService.updateByPrimaryKeySelective(updateStaff);
			// 修改完成后跳转到登录页面
			request.getSession().removeAttribute("adminloginuser");
			return ApiMessage.succeed();
		} else {
			return new ApiMessage(400, "旧密码错误");
		}
	}

	/**
	 * @Description: 伙伴列表页面
	 * @author 宋高俊
	 * @date 2018年7月27日 下午6:55:37
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/staff/list";
	}

	/**
	 * @Description: 伙伴列表页面
	 * @author 宋高俊
	 * @date 2018年7月27日 下午6:55:37
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Staff> list = staffService.selectByAll(null);
		PageInfo<Staff> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Staff staff = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", staff.getStaffid());// ID
			map.put("staffno", staff.getStaffno());// 编号
			map.put("ename", staff.getEname());// 登录名
			map.put("rname", staff.getRname());// 姓名
			map.put("position", staff.getPosition());// 岗位
			map.put("power", staff.getPower() == 1 ? "一级"
					: staff.getPower() == 2 ? "二级"
							: staff.getPower() == 3 ? "三级"
									: staff.getPower() == 4 ? "四级"
											: staff.getPower() == 5 ? "五级" : staff.getPower() == 6 ? "六级" : "七级");// 权级
			map.put("status", staff.getStatus());// 状态
			map.put("loginchange", DateUtil.getFormat(staff.getLoginchange()));// 最近登录
			map.put("remark", staff.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 修改页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:34:28
	 */
	@RequestMapping(value = "/editStaff")
	public String editStaff(Model model, String id) {
		Staff staff = staffService.selectByPrimaryKey(id);
		model.addAttribute("staff", staff);
		return "admin/staff/update";
	}

	/**
	 * @Description: 修改页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:34:28
	 */
	@RequestMapping(value = "/updateStaff")
	@ResponseBody
	public ApiMessage updateStaff(HttpServletRequest request, Staff staff) {
		Staff loginstaff = (Staff) request.getSession().getAttribute("adminloginuser");

		Staff oldstaff = staffService.selectByPrimaryKey(staff.getStaffid());
		String logdata = "";
		if (!oldstaff.getPosition().equals(staff.getPosition())) {
			logdata += "岗位从" + oldstaff.getPosition() + "修改为" + staff.getPosition() + ";";
		}
		if (!oldstaff.getPower().equals(staff.getPower())) {
			logdata += "级别从" + oldstaff.getPower() + "级修改为" + staff.getPower() + "级;";
		}
		if (!oldstaff.getStatus().equals(staff.getStatus())) {
			logdata += "状态从" + oldstaff.getStatus() + "修改为" + staff.getStatus() + ";";
		}
		//管理员修改日志
		StaffLog staffLog = new StaffLog();
		staffLog.setId(Utils.getUUID());
		staffLog.setStaffid(staff.getStaffid());
		staffLog.setEditstaffid(loginstaff.getStaffid());
		staffLog.setContent(logdata);
		staffLog.setTime(new Date());

		staffLogMapper.insertSelective(staffLog);

		int flag = staffService.updateByPrimaryKeySelective(staff);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 新增页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:34:28
	 */
	@RequestMapping(value = "/addStaff")
	public String addStaff() {
		return "admin/staff/add";
	}

	/**
	 * @Description: 保存伙伴页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:34:28
	 */
	@RequestMapping(value = "/saveStaff")
	@ResponseBody
	public ApiMessage saveStaff(Staff staff) {
		staff.setStaffid(Utils.getUUID());
		if (StringUtils.isBlank(staff.getPassword())) {
			staff.setPassword("123456");
		}
		Staff oldstaff = staffService.selectByEName(staff.getEname());
		if (oldstaff != null) {
			return new ApiMessage(400, "英文名重复，请修改后提交");
		} else {
			int flag = staffService.insertSelective(staff);
			if (flag > 0) {
				return ApiMessage.succeed();
			} else {
				return ApiMessage.error();
			}
		}
	}

	/**
	 * @Description: 删除伙伴
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:34:28
	 */
	@RequestMapping(value = "/delStaff")
	@ResponseBody
	public ApiMessage delStaff(String staffId) {
		int flag = staffService.deleteByPrimaryKey(staffId);
		if (flag > 0) {
			return ApiMessage.succeed();
		}
		return ApiMessage.error();
	}

	/**  
	 * @Description: 个人资料
	 * @author 宋高俊  
	 * @date 2018年8月11日 上午10:57:13 
	 */ 
	@RequestMapping(value = "/staffDetails")
	public String staffDetails() {
		return "admin/staff/staffDetails";
	}
}
