package com.xiaoyi.ssm.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.core.date.DateUtil;

/**
 * @Description: 对账控制器
 * @author 宋高俊
 * @date 2018年9月20日 下午3:32:38
 */
@Controller(value = "adminAmountController")
@RequestMapping(value = "/admin/amount")
public class AmountController {


	/**
	 * @Description: 对账页面
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月20日 下午3:33:36
	 */
	@RequestMapping(value = "/listview")
	public String listview(Model model) {
		model.addAttribute("date", DateUtil.formatDate(new Date()));
		return "admin/checkAccount/list";
	}

	/**  
	 * @Description: 偏移查询时间
	 * @author 宋高俊  
	 * @param dateStr
	 * @param flag
	 * @return 
	 * @date 2018年10月26日 下午4:15:42 
	 */ 
	@RequestMapping(value = "/offsetDay")
	public String offsetDay(Model model, String dateStr, Integer flag) {
		Date date = DateUtil.parse(dateStr);
		if (flag == 1) {
			date = DateUtil.offsetDay(date, 1);
		}else {
			date = DateUtil.offsetDay(date, -1);
		}
		model.addAttribute("date", DateUtil.formatDate(date));
		return "admin/checkAccount/list";
	}
}
