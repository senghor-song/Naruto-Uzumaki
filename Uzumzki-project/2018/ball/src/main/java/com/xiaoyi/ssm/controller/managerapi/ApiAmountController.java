package com.xiaoyi.ssm.controller.managerapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.Coach;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Train;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.CoachService;
import com.xiaoyi.ssm.service.CombineService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.TrainService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;

/**
 * @Description: 管理员控制器接口
 * @author 宋高俊
 * @date 2018年8月18日 下午4:10:29
 */
@Controller
@RequestMapping("managerapi/amount")
public class ApiAmountController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CombineService combineService;
	@Autowired
	private TrainService trainService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private CoachService coachService;

	/**
	 * @Description: 场馆收入列表
	 * @author 宋高俊
	 * @date 2018年8月23日 下午7:33:34
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ApiMessage amountlist(String token) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
		Venue venue = venueService.selectByManager(manager.getId());
		List<Double> list = new ArrayList<>();
		Double orderAmountSum = orderService.countAmountByVenue(venue.getId());
		list.add(orderAmountSum);
		Double combineAmountSum = combineService.countAmountByVenue(venue.getId());
		list.add(combineAmountSum);
		Double trainAmountSum = trainService.countAmountByVenue(venue.getId());
		list.add(trainAmountSum);
		return new ApiMessage(200, "查询成功", list);
	}
	
	/**
	 * @Description: 场馆订场收入列表
	 * @author 宋高俊
	 * @date 2018年8月23日 下午7:33:34
	 */
	@RequestMapping("/order/list")
	@ResponseBody
	public ApiMessage orderlist(String token, PageBean pageBean) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
		Venue venue = venueService.selectByManager(manager.getId());
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Order> list = orderService.selectByVenue(venue.getId());
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (Order order : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", order.getId());// 订场人
			map.put("name", order.getMember().getName());// 订场人
			map.put("date", DateUtil.getFormat(order.getCreatetime()));// 订场时间
			map.put("orderno", order.getOrderno());// 订场编号
			map.put("price", order.getPrice());// 金额
			map.put("type",
					order.getType() == 0 ? "待支付"
							: order.getType() == 1 ? "订单完成"
									: order.getType() == 2 ? "取消支付" 
											: order.getType() == 3 ? "支付超时" 
													: order.getType() == 4 ? "已退款" : 
														order.getType() == 5 ? "支付成功待确认" : "支付成功待消费");// 状态
			listmap.add(map);
			
		}
		Map<String, Object> map = new HashMap<>();
		map.put("pageTotal", pageInfo.getTotal());
		map.put("pageNum", pageInfo.getPageNum());
		map.put("pageList", listmap);
		return new ApiMessage(200, "查询成功", map);
	}
	
	/**
	 * @Description: 场馆订场收入列表
	 * @author 宋高俊
	 * @date 2018年8月23日 下午7:33:34
	 */
	@RequestMapping("/order/details")
	@ResponseBody
	public ApiMessage orderdetails(String token, String id) {
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		Order order = orderService.selectByMemberOrder(member.getId(), id);
		if (order == null) {
			return new ApiMessage(400, "订单查询失败");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", order.getId());// id
		map.put("orderNo", order.getOrderno());// 订单号
		map.put("venuename", order.getVenue().getName());// 场馆名称
		map.put("mamber", order.getMember().getName());// 会员名称
		map.put("price", order.getPrice());// 总价格
		List<Map<String, Object>> reservelist = new ArrayList<>();
		
		String timeSumStr = "";
		for (int j = 0; j < order.getReserves().size(); j++) {
			Reserve reserve = order.getReserves().get(j);
			Map<String, Object> reservemap = new HashMap<>();
			String[] timestrs = reserve.getReservetimeframe().split(",");
			reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
					+ StringUtil.timeToTimestr(timestrs) + " " + reserve.getField().getName());// 时间数据
			timeSumStr += StringUtil.timeToTimestr(timestrs) + " ";
			reservemap.put("price", reserve.getReserveamount());// 单价
			reservelist.add(reservemap);
		}
		if (order.getCoachid() != null) {
			Coach coach = coachService.selectByPrimaryKey(order.getCoachid());
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
					+ timeSumStr + " 教练");// 时间数据
			
			reservemap.put("price", order.getCoachamount());// 单价
			reservelist.add(reservemap);
		}
		map.put("reservelist", reservelist);
		return ApiMessage.succeed(map);
	}
	
	/**  
	 * @Description: 场馆散拼收入列表
	 * @author 宋高俊  
	 * @date 2018年8月24日 上午9:33:14 
	 */ 
	@RequestMapping("/combine/list")
	@ResponseBody
	public ApiMessage combinelist(String token, PageBean pageBean) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
		Venue venue = venueService.selectByManager(manager.getId());
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Combine> list = combineService.selectByVenue(venue.getId());
		PageInfo<Combine> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (Combine combine : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", combine.getId());// id
			map.put("name", combine.getManager().getName());// 发起人
			map.put("date", DateUtil.getFormat(combine.getCreatetime()));// 加入时间
			map.put("combine", combine.getCombineno());// 编号
			map.put("price", combine.getAmountsum());// 金额
			listmap.add(map);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("pageTotal", pageInfo.getTotal());
		map.put("pageNum", pageInfo.getPageNum());
		map.put("pageList", listmap);
		return new ApiMessage(200, "查询成功", map);
	}
	
	/**  
	 * @Description: 场馆培训收入列表
	 * @author 宋高俊  
	 * @date 2018年8月24日 上午9:33:14 
	 */ 
	@RequestMapping("/train/list")
	@ResponseBody
	public ApiMessage trainlist(String token, PageBean pageBean) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
		Venue venue = venueService.selectByManager(manager.getId());
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Train> list = trainService.selectByVenue(venue.getId());
		PageInfo<Train> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (Train train : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", train.getId());// id
			map.put("name", train.getManager().getName());// 发起人
			map.put("date", DateUtil.getFormat(train.getCreatetime()));// 加入时间
			map.put("combine", train.getTrainno());// 编号
			map.put("price", train.getPrice());// 金额
			listmap.add(map);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("pageTotal", pageInfo.getTotal());
		map.put("pageNum", pageInfo.getPageNum());
		map.put("pageList", listmap);
		return new ApiMessage(200, "查询成功", map);
	}
}
