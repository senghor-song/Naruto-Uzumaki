package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.VenueDayStatis;
import com.xiaoyi.ssm.model.VenueDayStatisLog;
import com.xiaoyi.ssm.service.StaffService;
import com.xiaoyi.ssm.service.VenueDayStatisLogService;
import com.xiaoyi.ssm.service.VenueDayStatisService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 每天统计数据控制器
 * @author 宋高俊
 * @date 2018年11月27日下午2:40:24
 */
@Controller(value = "adminVenueDayStatisController")
@RequestMapping(value = "/admin/venueDayStatis")
public class VenueDayStatisController {

	@Autowired
	private VenueDayStatisLogService venueDayStatisLogService;
	@Autowired
	private VenueDayStatisService venueDayStatisService;
	@Autowired
	private StaffService staffService;

	/**
	 * @Description: 场馆统计数据页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年11月26日下午7:37:13
	 */
	@RequestMapping(value = "/listview")
	public String listview(Model model) {
		return "admin/venueDayStatis/list";
	}

	/**
	 * @Description: 场馆统计数据列表
	 * @author 宋高俊
	 * @param adminPage
	 * @return
	 * @date 2018年11月26日下午7:40:35
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		
		List<VenueDayStatis> list = venueDayStatisService.selectByAll(null);
		PageInfo<VenueDayStatis> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueDayStatis venueDayStatis = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venueDayStatis.getId());// ID
			map.put("createTime", DateUtil.getFormat(venueDayStatis.getCreateTime()));// 交易时间
			map.put("nowDate", DateUtil.getFormat(venueDayStatis.getNowDate(), "yyyy-MM-dd"));// 交易时间
			map.put("systemFlag", venueDayStatis.getSystemFlag());// 系统风评
			map.put("realityAmount", String.format("%.2f", venueDayStatis.getRealityAmount()));// 拟转用户
			map.put("paySubsidy", String.format("%.2f", venueDayStatis.getPaySubsidy()));// 额度补贴
			map.put("profitAmount", String.format("%.2f", venueDayStatis.getProfitAmount()));// 当日盈亏
			map.put("userFlag", venueDayStatis.getUserFlag());// 人工风评
			int countLog = venueDayStatisLogService.countVenueDay(venueDayStatis.getId());
			map.put("countLog", countLog);// 日志
			map.put("dealIncome", String.format("%.2f", venueDayStatis.getDealIncome()));// 基本户-交易收入
			map.put("dealRefund", String.format("%.2f", venueDayStatis.getDealRefund()));// 基本户-交易退款
			map.put("dealFee", String.format("%.2f", venueDayStatis.getDealFee()));// 基本户-微信手续费
			map.put("basicToOperation", String.format("%.2f", venueDayStatis.getBasicToOperation()));// 基本户-基本转运营
			map.put("operationToBasic", String.format("%.2f", venueDayStatis.getOperationToBasic()));// 基本户-运营转基本
			map.put("accountAmount", String.format("%.2f", venueDayStatis.getAccountAmount()));// 基本户-当日结余
			map.put("restsIncome", String.format("%.2f", venueDayStatis.getRestsIncome()));// 基本户-其他收入
			map.put("paymentAmountOperation", String.format("%.2f", venueDayStatis.getPaymentAmountOperation()));// 运营户-企业支付
			map.put("basicToOperationOperation", String.format("%.2f", venueDayStatis.getBasicToOperationOperation()));// 运营户-基本转运营
			map.put("operationToBasicOperation", String.format("%.2f", venueDayStatis.getOperationToBasicOperation()));// 运营户-运营转基本
			map.put("accountAmountOperation", String.format("%.2f", venueDayStatis.getAccountAmountOperation()));// 运营户-当日结余
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**
	 * @Description: 查询统计日志
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:47:24
	 */
	@RequestMapping(value = "/log/list")
	@ResponseBody
	public AdminMessage logList(String id) {
		List<VenueDayStatisLog> list = venueDayStatisLogService.selectByVenueDay(id);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueDayStatisLog venueDayStatisLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venueDayStatisLog.getId());// ID
			map.put("createTime", DateUtil.getFormat(venueDayStatisLog.getCreateTime(), "yyyy-MM-dd"));// 记录时间
			Staff staff = staffService.selectByPrimaryKey(venueDayStatisLog.getStaffId());
			map.put("name", staff.getName());// 记录人
			map.put("content", venueDayStatisLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(0, listMap);
	}

	/**
	 * @Description: 记录日志
	 * @author 宋高俊
	 * @param request
	 * @param venueDayStatisId
	 * @param content
	 * @return
	 * @date 2018年11月27日下午4:14:21
	 */
	@RequestMapping(value = "/log/add")
	@ResponseBody
	public ApiMessage logAdd(HttpServletRequest request, String venueDayStatisId, String content) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		VenueDayStatisLog venueDayStatisLog = new VenueDayStatisLog();
		venueDayStatisLog.setId(Utils.getUUID());
		venueDayStatisLog.setContent(content);
		venueDayStatisLog.setCreateTime(new Date());
		venueDayStatisLog.setVenueDayStatisId(venueDayStatisId);
		venueDayStatisLog.setStaffId(staff.getId());
		venueDayStatisLogService.insertSelective(venueDayStatisLog);
		return new ApiMessage(200, "添加成功");
	}
}
