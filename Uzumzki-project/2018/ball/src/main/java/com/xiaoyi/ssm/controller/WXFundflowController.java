package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.WXFundflow;
import com.xiaoyi.ssm.service.WXFundflowService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 场馆统计数据
 * @author 宋高俊
 * @date 2018年9月20日 下午5:03:09
 */
@Controller(value = "adminwxFundflowController")
@RequestMapping(value = "/admin/wxFundflow")
public class WXFundflowController {

	private static Logger logger = Logger.getLogger(WXFundflowController.class.getName());
	
	@Autowired
	private WXFundflowService wxFundflowService;

	/**
	 * @Description: 资金账单数据页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年11月26日下午7:37:13
	 */
	@RequestMapping(value = "/listview")
	public String listview(Model model) {
		return "admin/wxFundflow/list";
	}

	/**
	 * @Description: 资金账单数据
	 * @author 宋高俊
	 * @param adminPage 分页参数
	 * @param accountType 订单状态  1=基本户Basic 2=运营户Operation
	 * @return
	 * @date 2018年11月30日上午10:54:34
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage, Integer accountType) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<WXFundflow> list = wxFundflowService.selectByAccountType(accountType);
		PageInfo<WXFundflow> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			WXFundflow wxFundflow = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wxFundflow.getId());// ID
			map.put("createTime", DateUtil.getFormat(wxFundflow.getCreateTime()));// 
			map.put("recordTime", DateUtil.getFormat(wxFundflow.getRecordTime()));// 
			map.put("wxPayOrderid", wxFundflow.getWxPayOrderid());// 
			map.put("wxFundflowId", wxFundflow.getWxFundflowId());// 
			map.put("workName", wxFundflow.getWorkName());// 
			map.put("workType", wxFundflow.getWorkType());// 
			map.put("dealType", wxFundflow.getDealType());// 
			map.put("dealAmount", wxFundflow.getDealAmount());// 
			map.put("accountAmount", wxFundflow.getAccountAmount());// 
			map.put("applyUser", wxFundflow.getApplyUser());// 
			map.put("remark", wxFundflow.getRemark());// 
			map.put("workId", wxFundflow.getWorkId());// 
			map.put("accountType", wxFundflow.getAccountType() == 1 ? "基本户" : "运营户");// 1=基本账户2=运营账户3=手续费账户
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
}
