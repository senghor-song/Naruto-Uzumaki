package com.xiaoyi.ssm.controller.managerapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 模板规划控制器
 * @author 宋高俊  
 * @date 2018年8月21日 上午11:17:32 
 */ 
@Controller
@RequestMapping("managerapi/venueTemplate")
public class ApiVenueTemplateController {

	@Autowired
	private VenueTemplateService venueTemplateService;
	@Autowired
	private VenueService venueService;

	/**  
	 * @Description: 模板列表
	 * @author 宋高俊  
	 * @date 2018年8月21日 上午11:20:26 
	 */ 
	@RequestMapping(value = "/list")
	@ResponseBody
	public ApiMessage list(String token) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);

		Venue venue = venueService.selectByManager(manager.getId());

		if (venue == null) {
			return new ApiMessage(400, "请先新建场馆");
		}
		List<VenueTemplate> list = venueTemplateService.selectByVenue(venue.getId());
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (VenueTemplate venueTemplate : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", venueTemplate.getId());// id
			map.put("name", venueTemplate.getName());// 模板名称
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}
	
	/**  
	 * @Description: 模板详情
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:21:28 
	 */ 
	@RequestMapping(value = "/deateils")
	@ResponseBody
	public ApiMessage deateils(String id) {
		VenueTemplate venueTemplate = venueTemplateService.selectByPrimaryKey(id);
		String[] template = venueTemplate.getPrice().split(",");
		// 最终返回日期数据
		List<Map<String, Object>> datelistmap = new ArrayList<>();
		int time = 0;
		for (int j = 0; j < template.length; j++) {
			Map<String, Object> map = new HashMap<>();
			map.put("no", j + 1);
			if ((j + 1) % 2 == 0) {
				map.put("time", time + ":30-" + (time + 1) + ":00");
				time++;
			} else {
				map.put("time", time + ":00-" + time + ":30");
			}
			map.put("price", template[j]);
			datelistmap.add(map);
		}
		Map<String, Object> returnmap = new HashMap<>();
		returnmap.put("id", venueTemplate.getId());// 模板ID
		returnmap.put("name", venueTemplate.getName());// 模板名称
		returnmap.put("list", datelistmap);// 模板内容
		return new ApiMessage(200, "查询成功", returnmap);
	}
	
	/**  
	 * @Description: 修改模板详情
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:21:28 
	 */ 
	@RequestMapping(value = "/updateTmplate")
	@ResponseBody
	public ApiMessage updateTmplate(String id, String pricestr, String name) {
		VenueTemplate venueTemplate = new VenueTemplate();
		venueTemplate.setId(id);
		venueTemplate.setName(name);
		venueTemplate.setPrice(pricestr);
		venueTemplate.setModifytime(new Date());
		int flag = venueTemplateService.updateByPrimaryKeySelective(venueTemplate);
		if (flag > 0 ) {
			return new ApiMessage(200, "修改成功");
		}
		return new ApiMessage(400, "修改失败");
	}
	
	/**  
	 * @Description: 删除模板详情
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:21:28 
	 */ 
	@RequestMapping(value = "/deleteTmplate")
	@ResponseBody
	public ApiMessage deleteTmplate(String id) {
		int flag = venueTemplateService.deleteByPrimaryKey(id);
		if (flag > 0 ) {
			return new ApiMessage(200, "删除成功");
		}
		return new ApiMessage(400, "删除失败");
	}
	
	/**  
	 * @Description: 新增模板
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:21:28 
	 */ 
	@RequestMapping(value = "/saveTmplate")
	@ResponseBody
	public ApiMessage saveTmplate(String pricestr, String name, String token) {
		
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);

		Venue venue = venueService.selectByManager(manager.getId());

		if (venue == null) {
			return new ApiMessage(400, "请先新建场馆");
		}
		
		VenueTemplate venueTemplate = new VenueTemplate();
		venueTemplate.setId(Utils.getUUID());
		venueTemplate.setName(name);
		venueTemplate.setPrice(pricestr);
		venueTemplate.setCreatetime(new Date());
		venueTemplate.setModifytime(new Date());
		venueTemplate.setVenueid(venue.getId());
		
		int flag = venueTemplateService.insert(venueTemplate);
		if (flag > 0 ) {
			return new ApiMessage(200, "添加成功");
		}
		return new ApiMessage(400, "添加失败");
	}
}
