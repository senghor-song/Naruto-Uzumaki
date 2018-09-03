package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
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
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.PropertyPre;
import com.xiaoyi.ssm.model.PropertyPub;
import com.xiaoyi.ssm.service.PropertyPreService;
import com.xiaoyi.ssm.service.PropertyPubService;
import com.xiaoyi.ssm.util.DateUtil;

/**
 * @Description: 公盘控制器
 * @author 宋高俊
 * @date 2018年7月25日 下午2:43:06
 */
@Controller(value = "adminPropertyPubController")
@RequestMapping(value = "/admin/propertyPub")
public class PropertyPubController {

	@Autowired
	private PropertyPreService propertyPreService;
	@Autowired
	private PropertyPubService propertyPubService;

	/**
	 * @Description: 公盘页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/propertyPub/list";
	}

	/**
	 * @Description: 公盘数据
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<PropertyPre> list = propertyPreService.selectByAll(null);
		PageInfo<PropertyPre> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			PropertyPre propertyPre = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", propertyPre.getId());// ID
			map.put("regdate", propertyPre.getRegdate());// 登记时间
			map.put("regway", propertyPre.getRegway());// 来源
			map.put("city", propertyPre.getEstateT().getCity());// 城市
			map.put("estate", propertyPre.getEstate());// 小区
			map.put("square", propertyPre.getSquare());// 面积
			map.put("trade", propertyPre.getTrade());// 类别
			map.put("price", propertyPre.getPrice());// 报价
			map.put("flagpay", propertyPre.getFlagpay() == 0 ? "否" : "是");// 业主付拥
			map.put("pubCount", propertyPubService.countPubByPropertypre(propertyPre.getId()));// 已分发
			map.put("claimCount", propertyPubService.countClaimByPropertypre(propertyPre.getId()));// 已认领
			map.put("view4", "未找到");// 最大允许认领
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 分发
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/distribute")
	@ResponseBody
	public AdminMessage distribute(String id, AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<PropertyPub> list = propertyPubService.selectByPropertyPre(id);
		PageInfo<PropertyPub> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			PropertyPub propertyPub = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", propertyPub.getId());// ID
			map.put("empStore", propertyPub.getEmployee().getEmpStore().getEmpstore());// 商户
			map.put("emp", propertyPub.getEmployee().getEmp());// 经纪人
			map.put("view", "-");// 商户距离
			map.put("view", "-");// 商户合作存续
			map.put("view", "-");// 经纪人积分
			map.put("empclaimtime", DateUtil.getFormat(propertyPub.getEmpclaimtime()));// 认领时间
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 公盘
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/details")
	public String details(Model model, String id) {
		PropertyPre propertyPre = propertyPreService.selectByPrimaryKey(id);
		model.addAttribute("propertyPre", propertyPre);
		return "admin/propertyPub/details";
	}

}
