package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.CustAppointResp;
import com.xiaoyi.ssm.service.CustAppointRespService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 约看接口控制器
 * @author 宋高俊
 * @date 2018年7月26日 下午4:17:14
 */
@Controller(value = "adminCustAppointRespController")
@RequestMapping("admin/custAppointResp")
public class CustAppointRespController {

	@Autowired
	private CustAppointRespService custAppointRespService;

	/**
	 * @Description: 约看列表页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list() {
		return "admin/custAppointResp/list";
	}

	/**
	 * @Description: 小区数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<CustAppointResp> list = custAppointRespService.selectByAll(null);
		PageInfo<CustAppointResp> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			CustAppointResp custAppointResp = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", custAppointResp.getId());
			map.put("appointtime", DateUtil.getFormat(custAppointResp.getAppointtime(), "yyyy-MM-dd HH:mm:ss"));// 约看时间
			if (!StringUtils.isBlank(custAppointResp.getCust().getMobile())) {
				StringBuilder sb = new StringBuilder(custAppointResp.getCust().getMobile());
				sb.replace(3, 7, "****");
				map.put("cust", sb.toString());// 客户
			} else {
				map.put("cust", "暂无");// 客户
			}
			map.put("city", custAppointResp.getEstate().getCity());// 城市
			map.put("estate", custAppointResp.getEstate().getEstate());// 小区
			map.put("empStore", custAppointResp.getEmployee().getStore());// 商户
			map.put("employee", custAppointResp.getEmployee().getEmp());// 经纪人
			map.put("respcontent", custAppointResp.getRespcontent());// 响应
			map.put("resptime", DateUtil.getFormat(custAppointResp.getResptime(), "yyyy-MM-dd HH:mm:ss"));// 响应时间
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

}
