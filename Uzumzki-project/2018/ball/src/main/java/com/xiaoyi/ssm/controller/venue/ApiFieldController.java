package com.xiaoyi.ssm.controller.venue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.service.FieldService;

/**  
 * @Description: 球场管理控制器
 * @author 宋高俊  
 * @date 2018年9月6日 下午7:52:38 
 */ 
@Controller
@RequestMapping("venue/manager/field")
public class ApiFieldController {

	@Autowired
	private FieldService fieldService;

	/**  
	 * @Description: 场地列表
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月6日 下午8:01:11 
	 */ 
	@RequestMapping(value = "/list")
	@ResponseBody
	public ApiMessage list(HttpServletRequest request, String venueid) {
		List<Field> list = fieldService.selectByVenue(venueid);
		List<Map<String, Object>> maps = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("fieldId", list.get(i).getId());// ID
			map.put("fieldName", list.get(i).getName());//场地名
			maps.add(map);
		}
		return new ApiMessage(200, "修改成功", maps);
	}
	
	/**  
	 * @Description: 修改场地名称
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月6日 下午8:01:11 
	 */ 
	@RequestMapping(value = "/updateFieldName")
	@ResponseBody
	public ApiMessage updateFieldName(HttpServletRequest request, String fieldid, String fieldName) {
		Field field = fieldService.selectByPrimaryKey(fieldid);
		field.setName(fieldName);
		fieldService.updateByPrimaryKeySelective(field);
		return new ApiMessage(200, "修改成功");
	}
	
	
//	/**  
//	 * @Description: 模板详情
//	 * @author 宋高俊  
//	 * @date 2018年8月22日 下午7:21:28 
//	 */ 
//	@RequestMapping(value = "/deateils")
//	@ResponseBody
//	public ApiMessage deateils(String id) {
//		VenueTemplate venueTemplate = venueTemplateService.selectByPrimaryKey(id);
//		String[] template = venueTemplate.getPrice().split(",");
//		// 最终返回日期数据
//		List<Map<String, Object>> datelistmap = new ArrayList<>();
//		int time = 0;
//		for (int j = 0; j < template.length; j++) {
//			Map<String, Object> map = new HashMap<>();
//			map.put("no", j + 1);
//			if ((j + 1) % 2 == 0) {
//				map.put("time", time + ":30-" + (time + 1) + ":00");
//				time++;
//			} else {
//				map.put("time", time + ":00-" + time + ":30");
//			}
//			map.put("price", template[j]);
//			datelistmap.add(map);
//		}
//		Map<String, Object> returnmap = new HashMap<>();
//		returnmap.put("id", venueTemplate.getId());// 模板ID
//		returnmap.put("name", venueTemplate.getName());// 模板名称
//		returnmap.put("list", datelistmap);// 模板内容
//		return new ApiMessage(200, "查询成功", returnmap);
//	}
//	
//	/**  
//	 * @Description: 修改模板详情
//	 * @author 宋高俊  
//	 * @date 2018年8月22日 下午7:21:28 
//	 */ 
//	@RequestMapping(value = "/updateTmplate")
//	@ResponseBody
//	public ApiMessage updateTmplate(String id, String pricestr, String name) {
//		VenueTemplate venueTemplate = new VenueTemplate();
//		venueTemplate.setId(id);
//		venueTemplate.setName(name);
//		venueTemplate.setPrice(pricestr);
//		venueTemplate.setModifytime(new Date());
//		int flag = venueTemplateService.updateByPrimaryKeySelective(venueTemplate);
//		if (flag > 0 ) {
//			return new ApiMessage(200, "修改成功");
//		}
//		return new ApiMessage(400, "修改失败");
//	}
//	
//	/**  
//	 * @Description: 删除模板详情
//	 * @author 宋高俊  
//	 * @date 2018年8月22日 下午7:21:28 
//	 */ 
//	@RequestMapping(value = "/deleteTmplate")
//	@ResponseBody
//	public ApiMessage deleteTmplate(String id) {
//		int flag = venueTemplateService.deleteByPrimaryKey(id);
//		if (flag > 0 ) {
//			return new ApiMessage(200, "删除成功");
//		}
//		return new ApiMessage(400, "删除失败");
//	}
//	
//	/**  
//	 * @Description: 新增模板
//	 * @author 宋高俊  
//	 * @date 2018年8月22日 下午7:21:28 
//	 */ 
//	@RequestMapping(value = "/saveTmplate")
//	@ResponseBody
//	public ApiMessage saveTmplate(String pricestr, String name, HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
//		
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
//
//		Venue venue = venueService.selectByManager(manager.getId());
//
//		if (venue == null) {
//			return new ApiMessage(400, "请先新建场馆");
//		}
//		
//		VenueTemplate venueTemplate = new VenueTemplate();
//		venueTemplate.setId(Utils.getUUID());
//		venueTemplate.setName(name);
//		venueTemplate.setPrice(pricestr);
//		venueTemplate.setCreatetime(new Date());
//		venueTemplate.setModifytime(new Date());
//		venueTemplate.setVenueid(venue.getId());
//		
//		int flag = venueTemplateService.insert(venueTemplate);
//		if (flag > 0 ) {
//			return new ApiMessage(200, "添加成功");
//		}
//		return new ApiMessage(400, "添加失败");
//	}
}
