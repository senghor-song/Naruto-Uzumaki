package com.xiaoyi.ssm.controller.venue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * @Description: 球场管理控制器
 * @author 宋高俊  
 * @date 2018年9月6日 下午7:52:38 
 */ 
@Controller
@RequestMapping("venue/manager/field")
public class ApiFieldController {

//	@Autowired
//	private VenueTemplateService venueTemplateService;
//	@Autowired
//	private VenueService venueService;
//	@Autowired
//	private FieldService fieldService;
//
//	/**  
//	 * @Description: 
//	 * @author 宋高俊  
//	 * @return 
//	 * @date 2018年9月6日 下午8:01:11 
//	 */ 
//	@RequestMapping(value = "/list")
//	@ResponseBody
//	public ApiMessage list(HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
//		Venue venue = venueService.selectByManager(manager.getId());
//		//判断是否有场馆
//		if (venue == null) {
//			return new ApiMessage(400, "请先新建场馆");
//		}
//		//判断是否有球场
//		Field f = new Field();
//		f.setVenueid(venue.getId());
//		List<Field> list = fieldService.selectByAll(f);
//		List<Map<String, Object>> listmap = new ArrayList<>();
//		for (Field field : list) {
//			Map<String, Object> map = new HashMap<>();
//			map.put("id", field.getId());// id
//			map.put("name", field.getName());// 模板名称
//			listmap.add(map);
//		}
//		return new ApiMessage(200, "查询成功", listmap);
//	}
//	
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
