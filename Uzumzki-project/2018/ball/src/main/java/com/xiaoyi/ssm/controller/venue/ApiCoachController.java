package com.xiaoyi.ssm.controller.venue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 管理员教练控制器接口
 * @author 宋高俊
 * @date 2018年8月18日 下午4:10:29
 */
@Controller
@RequestMapping("venue/manager/coach")
public class ApiCoachController {
	
//	@Autowired
//	private CoachService coachService;
//	@Autowired
//	private VenueService venueService;
//
//	/**
//	 * @Description: 教练列表
//	 * @author 宋高俊
//	 * @date 2018年8月20日 下午12:01:57
//	 */
//	@RequestMapping(value = "/coachlist")
//	@ResponseBody
//	public ApiMessage coachlist(HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
//
//		Venue venue = venueService.selectByManager(manager.getId());
//		if (venue == null) {
//			return new ApiMessage(400, "请先新建场馆");
//		}
//		Coach c = new Coach();
//		c.setVenueid(venue.getId());
//		List<Coach> coachs = coachService.selectByAll(c);
//		List<Map<String, Object>> listmap = new ArrayList<>();
//		for (Coach coach : coachs) {
//			Map<String, Object> map = new HashMap<>();
//			map.put("id", coach.getId());// id
//			map.put("image", coach.getImage());// 图片
//			map.put("name", coach.getName());// 姓名
//			map.put("price", coach.getPrice());// 单价
//			map.put("introduce", coach.getIntroduce());// 简介
//			listmap.add(map);
//		}
//		return new ApiMessage(200, "查询成功", listmap);
//	}
//
//	/**
//	 * @Description: 添加教练接口
//	 * @author 宋高俊
//	 * @date 2018年8月20日 下午12:00:39
//	 */
//	@RequestMapping(value = "/saveCoach")
//	@ResponseBody
//	public ApiMessage saveCoach(Coach coach, HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
//
//		if (StringUtil.isBank(coach.getImage(), coach.getPrice() == null ? "" : "0", coach.getIntroduce(),
//				coach.getName())) {
//			return new ApiMessage(400, "缺少参数");
//		}
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
//		
//		Venue venue = venueService.selectByManager(manager.getId());
//
//		if (venue == null) {
//			return new ApiMessage(400, "请先新建场馆");
//		}
//		coach.setId(Utils.getUUID());
//		coach.setCreatetime(new Date());
//		coach.setModifytime(new Date());
//		coach.setVenueid(venue.getId());
//		coachService.insertSelective(coach);
//		return new ApiMessage(200, "添加成功");
//	}
//
//	/**
//	 * @Description: 修改教练数据接口
//	 * @author 宋高俊
//	 * @date 2018年8月20日 下午12:00:39
//	 */
//	@RequestMapping(value = "/editCoach")
//	@ResponseBody
//	public ApiMessage editCoach(String coachid, HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
//		Coach coach = coachService.selectByPrimaryKey(coachid);
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
//		int flag = managerService.selectByManagerAndVenue(manager.getId(), coach.getVenueid());
//		if (flag == 0) {
//			return new ApiMessage(400, "非法操作");
//		}
//		return new ApiMessage(200, "查询成功", coach);
//	}
//	
//	/**
//	 * @Description: 保存修改的教练数据接口
//	 * @author 宋高俊
//	 * @date 2018年8月20日 下午12:00:39
//	 */
//	@RequestMapping(value = "/updateCoach")
//	@ResponseBody
//	public ApiMessage updateCoach(String coachid, String image, Double price, String introduce, String name) {
//		Coach coach = new Coach();
//		coach.setId(coachid);
//		coach.setImage(image);
//		coach.setIntroduce(introduce);
//		coach.setPrice(price);
//		coach.setModifytime(new Date());
//		coach.setName(name);
//		//判断是否修改成功
//		int flag2 = coachService.updateByPrimaryKeySelective(coach);
//		if (flag2 > 0) {
//			return new ApiMessage(200, "修改成功");
//		}
//		return new ApiMessage(400, "修改失败");
//	}
//	
//	/**
//	 * @Description: 删除教练数据接口
//	 * @author 宋高俊
//	 * @date 2018年8月20日 下午12:00:39
//	 */
//	@RequestMapping(value = "/deleteCoach")
//	@ResponseBody
//	public ApiMessage deleteCoach(String coachid, HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
//		Coach coach = coachService.selectByPrimaryKey(coachid);
//		if (coach != null) {
//			//判断是否本人管理的教练
//			Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
//			int flag = managerService.selectByManagerAndVenue(manager.getId(), coach.getVenueid());
//			if (flag == 0) {
//				return new ApiMessage(400, "非法操作");
//			}
//			int flag2 = coachService.deleteByPrimaryKey(coachid);
//			if (flag2 > 0) {
//				return new ApiMessage(200, "删除成功");
//			}
//		}
//		return new ApiMessage(400, "删除失败");
//	}
	
}
