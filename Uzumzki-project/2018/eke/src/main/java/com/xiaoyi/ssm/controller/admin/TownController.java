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
import com.xiaoyi.ssm.model.Town;
import com.xiaoyi.ssm.service.TownCommissionPlanService;
import com.xiaoyi.ssm.service.TownDeveloperStaffService;
import com.xiaoyi.ssm.service.TownImageService;
import com.xiaoyi.ssm.service.TownLogService;
import com.xiaoyi.ssm.service.TownPosterService;
import com.xiaoyi.ssm.service.TownService;

/**
 * @Description: 新盘控制器
 * @author 宋高俊
 * @date 2018年7月25日 下午2:43:06
 */
@Controller(value = "adminTownController")
@RequestMapping(value = "/admin/town")
public class TownController {

	@Autowired
	private TownService townService;
	@Autowired
	private TownImageService townImageService;
	@Autowired
	private TownPosterService townPosterService;
	@Autowired
	private TownCommissionPlanService townCommissionPlanService;
	@Autowired
	private TownLogService townLogService;
	@Autowired
	private TownDeveloperStaffService townDeveloperStaffService;

	/**
	 * @Description: 新盘页面
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/town/list";
	}

	/**
	 * @Description: 新盘数据
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Town> list = townService.selectByAll(null);
		PageInfo<Town> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Town town = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", town.getId());// ID
			map.put("city", town.getCityT().getCity());// 城市
			map.put("town", town.getTown());// 新盘
			map.put("townImageSum", townImageService.countByTown(town.getId()));// 新盘图
			map.put("townPosterSum", townPosterService.countByTown(town.getId()));// 海报
			map.put("averageprice", town.getAverageprice());// 均价
			map.put("townCommissionPlanSum", townCommissionPlanService.countByTown(town.getId()));// 佣金方案
			map.put("view", "未找到");// 主力户型
			map.put("status", town.getStatus() == 0 ? "展示" : "下架");// 状态
			map.put("view1", "未找到");// 维护伙伴
			map.put("townDeveloperStaffSum", townDeveloperStaffService.countByTown(town.getId()));// 开发商账号
			map.put("townLogSum", townLogService.countByTown(town.getId()));// 日志
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 新盘
	 * @author 宋高俊
	 * @date 2018年7月28日 上午9:08:11
	 */
	@RequestMapping(value = "/details")
	public String details(Model model, String id) {
		Town town = townService.selectByPrimaryKey(id);
		model.addAttribute("town", town);
		return "admin/town/details";
	}

}
