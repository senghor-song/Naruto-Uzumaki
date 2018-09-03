package com.xiaoyi.ssm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;

/**  
 * @Description: 订场控制器
 * @author 宋高俊  
 * @date 2018年8月14日 下午7:02:30 
 */ 
@Controller(value = "adminReserveController")
@RequestMapping(value = "/admin/reserve")
public class ReserveController {

	/**  
	 * @Description: 订场页面
	 * @author 宋高俊  
	 * @date 2018年8月14日 下午7:02:41 
	 */ 
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/reserve/list";
	}

	/**  
	 * @Description: 订场数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 下午7:04:09 
	 */ 
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("view", "");// ID
			map.put("view", "深圳");// 城市
			map.put("view1", "福田");// 区县
			map.put("view2", "莲花街道");// 街道
			map.put("view3", "和平花园会所");// 场馆
			map.put("view4", "2");// 场地
			map.put("view5", "4");// 教练
			map.put("view6", "5");// 管理员
			map.put("view7", "4");// 模板
			map.put("view8", "16");// 日志
			map.put("view9", "1200");// 累计金额
			map.put("view10", "10");// 存余金额
			map.put("view11", "2");// 培训课程       
			listMap.add(map);
		}
		return new AdminMessage(10, listMap);
	}

}
