package com.xiaoyi.ssm.controller.pc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.HouseEnterDto;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.service.EstateEmpService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**  
 * @Description: 小区控制器
 * @author 宋高俊  
 * @date 2018年7月10日 上午9:40:51 
 */ 
@Controller
@RequestMapping("/estate")
public class EstateController {
	
	@Autowired
	private EstateEmpService estateEmpService;
	
	/**  
	 * @Description: 根据名称模糊查询小区
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午9:42:08 
	 */ 
	@RequestMapping("/getCellsByInput")
	@ResponseBody
	public ApiMessage getCellsByInput(HouseEnterDto houseEnterDto) {
		List<Estate> list = estateEmpService.selectByName(houseEnterDto);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Estate estate = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("address", estate.getAddress());
			map.put("area", estate.getAreaid());
			map.put("areaName", estate.getArea());
			map.put("buildingType", "1");
			map.put("cellCode", estate.getId());
			map.put("cellName", estate.getEstate());
			map.put("city", estate.getCityid());
			map.put("completionDate", 0);
			map.put("district", estate.getDistrictid());
			map.put("districtName", estate.getDistrict());
			map.put("id", estate.getId());
			map.put("name", new String[] { estate.getEstate() });
			map.put("matchanjuke", estate.getMatchan());
			map.put("match58", estate.getMatch58());
			map.put("matchfang", estate.getMatchfang());
			map.put("estatename", estate.getEstate());
			returnList.add(map);
		}
		returnMap.put("cells", returnList);
		return ApiMessage.succeed(returnMap);
	}
	
	/**  
	 * @Description: 根据名称模糊查询小区
	 * @author 宋高俊  
	 * @date 2018年7月10日 上午9:42:08 
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping("/getCellsByAvg")
	@ResponseBody
	public ApiMessage getCellsByAvg(String estateid) {
		Map<String, String> map = (Map<String, String>) RedisUtil.getRedisOne(Global.REDIS_ESTATE_AVG_MAP,estateid);
		return ApiMessage.succeed(map);
	}
}
