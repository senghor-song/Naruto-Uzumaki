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

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.EmpStore;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.model.Town;
import com.xiaoyi.ssm.service.EmpStoreService;
import com.xiaoyi.ssm.service.EstateService;
import com.xiaoyi.ssm.service.TownService;
import com.xiaoyi.ssm.util.Global;

/**
 * @Description: 调用百度地图控制器
 * @author 宋高俊
 * @date 2018年7月30日 下午7:13:06
 */
@RequestMapping(value = "/admin/baiduMap")
@Controller
public class BaiduMapController {

	@Autowired
	private EmpStoreService empStoreService;
	@Autowired
	private EstateService estateService;
	@Autowired
	private TownService townService;

	/**
	 * @Description: 控制台首页地图页面
	 * @author 宋高俊
	 * @date 2018年7月30日 下午7:14:34
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("key", Global.Baidu_Map_KRY);
		return "admin/baidu/index";
	}

	/**
	 * @Description: 统计需要展示的坐标数据
	 * @author 宋高俊
	 * @date 2018年8月2日 下午5:30:09
	 */
	@RequestMapping("/index/getTopLngLat")
	@ResponseBody
	public ApiMessage getTopLngLat() {
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> listMap0 = new ArrayList<Map<String, Object>>();// 有效商户
		List<Map<String, Object>> listMap1 = new ArrayList<Map<String, Object>>();// 无效商户
		List<Map<String, Object>> listMap2 = new ArrayList<Map<String, Object>>();// 新盘
		List<Map<String, Object>> listMap3 = new ArrayList<Map<String, Object>>();// 小区
		List<EmpStore> list0 = empStoreService.selectByType("1");// 有效商户
		for (int i = 0; i < list0.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("lng", list0.get(i).getLongitude());
			map.put("lat", list0.get(i).getLatitude());
			listMap0.add(map);
		}
		List<EmpStore> list1 = empStoreService.selectByType("0");// 无效商户
		for (int i = 0; i < list1.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("lng", list1.get(i).getLongitude());
			map.put("lat", list1.get(i).getLatitude());
			listMap1.add(map);
		}
		List<Town> list2 = townService.selectByAll(null);
		for (int i = 0; i < list2.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("lng", list2.get(i).getLongitude());
			map.put("lat", list2.get(i).getLatitude());
			listMap2.add(map);
		}
		List<Estate> list3 = estateService.selectByAll(null);
		for (int i = 0; i < list3.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("lng", list3.get(i).getLongitude());
			map.put("lat", list3.get(i).getLatitude());
			listMap3.add(map);
		}
		returnMap.put("list0", listMap0);
		returnMap.put("list1", listMap1);
		returnMap.put("list2", listMap2);
		returnMap.put("list3", listMap3);
		return ApiMessage.succeed(returnMap);
	}

	/**
	 * @Description: 统计需要展示的坐标数据
	 * @author 宋高俊
	 * @date 2018年8月2日 下午5:30:09
	 */
	@RequestMapping("/index/getTopLngLatById")
	@ResponseBody
	public ApiMessage getTopLngLatById(String id) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		if ("1".equals(id)) {
			List<EmpStore> list0 = empStoreService.selectByType("1");// 有效商户
			for (int i = 0; i < list0.size(); i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("lng", list0.get(i).getLongitude());
				map.put("lat", list0.get(i).getLatitude());
				listMap.add(map);
			}
		} else if ("2".equals(id)) {
			List<EmpStore> list1 = empStoreService.selectByType("0");// 无效商户
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("lng", list1.get(i).getLongitude());
				map.put("lat", list1.get(i).getLatitude());
				listMap.add(map);
			}
		} else if ("3".equals(id)) {
			List<Town> list2 = townService.selectByAll(null);
			for (int i = 0; i < list2.size(); i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("lng", list2.get(i).getLongitude());
				map.put("lat", list2.get(i).getLatitude());
				listMap.add(map);
			}
		} else if ("4".equals(id)) {
			List<Estate> list3 = estateService.selectByAll(null);
			for (int i = 0; i < list3.size(); i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("lng", list3.get(i).getLongitude());
				map.put("lat", list3.get(i).getLatitude());
				listMap.add(map);
			}
		}
		return ApiMessage.succeed(listMap);
	}

	/**
	 * @Description: 根据经纬度显示位置
	 * @author 宋高俊
	 * @date 2018年8月1日 下午7:06:44
	 */
	@RequestMapping("/showBaiduMap1")
	public String showLngLat(Model model, String lng, String lat) {
		model.addAttribute("key", Global.Baidu_Map_KRY);
		model.addAttribute("lng", lng);
		model.addAttribute("lat", lat);
		return "admin/baidu/showBaiduMap1";
	}
}
