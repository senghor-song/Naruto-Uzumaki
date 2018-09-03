package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.MassNoticeEmpMapper;
import com.xiaoyi.ssm.dao.MassNoticeLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.MassNotice;
import com.xiaoyi.ssm.model.MassNoticeEmp;
import com.xiaoyi.ssm.model.MassNoticeLog;
import com.xiaoyi.ssm.service.EmployeeService;
import com.xiaoyi.ssm.service.MassNoticeService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 公告接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminNoticeController")
@RequestMapping("admin/notice")
public class NoticeController {

	@Autowired
	private MassNoticeService massNoticeService;
	@Autowired
	private MassNoticeLogMapper massNoticeLogMapper;
	@Autowired
	private MassNoticeEmpMapper massNoticeEmpMapper;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * @Description: 公告列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list() {
		return "admin/notice/list";
	}

	/**
	 * @Description: 公告数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<MassNotice> list = massNoticeService.selectByAll(null);
		PageInfo<MassNotice> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MassNotice massNotice = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", massNotice.getId());
			map.put("modtime", DateUtil.getFormat(massNotice.getModifytime()));// 最后修改
			map.put("noticeLogSum", massNoticeLogMapper.countLogByNotice(massNotice.getId()));// 日志
			map.put("title", massNotice.getTitle());// 标题
			map.put("noticeSum", massNoticeEmpMapper.countEmpByNoice(massNotice.getId()));// 分发
			map.put("noticeReadSum", massNoticeEmpMapper.countEmpByNoiceRead(massNotice.getId()));// 已读
			map.put("remark", massNotice.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**
	 * @Description: 公告分发数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/employee/list")
	@ResponseBody
	public AdminMessage employeelist(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Employee> list = employeeService.selectByAll(null);
		PageInfo<Employee> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Employee employee = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", employee.getId());// id
			map.put("city", employee.getEmpStore().getCityT().getCity());// 城市
			map.put("empstore", employee.getEmpStore().getEmpstore());// 商户
			map.put("emp", employee.getEmp());// 经纪人
			map.put("empno", employee.getEmpno());// 经纪人编号
			map.put("remark", employee.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(200, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 公告分发经济端明细数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/massNoticeEmp/list")
	@ResponseBody
	public AdminMessage massNoticeEmplist(Model model, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<MassNoticeEmp> list = massNoticeEmpMapper.selectByAll(null);
		PageInfo<MassNoticeEmp> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MassNoticeEmp massNoticeEmp = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", massNoticeEmp.getId());// id
			map.put("staff", massNoticeEmp.getStaff().getRname());// 分发人
			map.put("createtime", DateUtil.getFormat(massNoticeEmp.getCreatetime()));// 发送时间
			map.put("emp", massNoticeEmp.getEmployee().getEmp());// 经纪人
			map.put("empno", massNoticeEmp.getEmployee().getEmpno());// 经纪人编号
			map.put("state", massNoticeEmp.getState() == 0 ? '否' : '是');// 是否查看
			map.put("receivetime", DateUtil.getFormat(massNoticeEmp.getReceivetime()));// 查看时间
			map.put("remark", massNoticeEmp.getRemark());// 备注
			listMap.add(map);
		}
		return new AdminMessage(200, pageInfo.getTotal(), listMap);
	}
	
	/**
	 * @Description: 公告修改日志数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/massNoticeLog/list")
	@ResponseBody
	public AdminMessage massNoticeLoglist(Model model, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<MassNoticeLog> list = massNoticeLogMapper.selectByAll(null);
		PageInfo<MassNoticeLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			MassNoticeLog massNoticeLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", massNoticeLog.getId());// id
			map.put("modtime", DateUtil.getFormat(massNoticeLog.getModtime()));// 修改时间
			map.put("staff", massNoticeLog.getStaff().getRname());// 修改人
			map.put("remark", massNoticeLog.getRemark());// 内容
			listMap.add(map);
		}
		return new AdminMessage(200, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 公告创建页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/notice/add";
	}
	
	/**
	 * @Description: 公告保存
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ApiMessage save(Model model, MassNotice massNotice) {
		massNotice.setId(Utils.getUUID());
		massNotice.setCreatetime(new Date());
		massNotice.setModifytime(new Date());
		massNotice.setType(0);
		int flag = massNoticeService.insertSelective(massNotice);
		if (flag > 0) {
			return ApiMessage.succeed();
		}else {
			return ApiMessage.error();
		}
	}
	
	/**
	 * @Description: 公告修改页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/edit")
	public String edit(Model model, String id) {
		MassNotice massNotice = massNoticeService.selectByPrimaryKey(id);
		model.addAttribute("massNotice", massNotice);
		return "admin/notice/edit";
	}
	
	/**
	 * @Description: 公告保存
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ApiMessage update(Model model, MassNotice massNotice) {
		massNotice.setModifytime(new Date());
		massNotice.setType(0);
		int flag = massNoticeService.updateByPrimaryKeySelective(massNotice);
		if (flag > 0) {
			return ApiMessage.succeed();
		}else {
			return ApiMessage.error();
		}
	}
}
