package com.xiaoyi.ssm.controller.managerapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.AmountRefundWayMapper;
import com.xiaoyi.ssm.dao.CombineJoinMapper;
import com.xiaoyi.ssm.dao.TrainJoinMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.model.Coach;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.model.CombineJoin;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.TrainJoin;
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
	@Autowired
	private TrainJoinMapper trainJoinMapper;
	@Autowired
	private CombineJoinMapper combineJoinMapper;
	@Autowired
	private AmountRefundWayMapper amountRefundWayMapper;

	/**
	 * @Description: 场馆收入列表
	 * @author 宋高俊
	 * @date 2018年8月23日 下午7:33:34
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage amountlist(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue venue = venueService.selectByManager(manager.getId());
		List<Double> list = new ArrayList<>();
		Double orderAmountSum = orderService.countAmountByVenue(venue.getId());
		list.add(orderAmountSum == null ? 0.0 : orderAmountSum);
		Double combineAmountSum = combineService.countAmountByVenue(venue.getId());
		list.add(combineAmountSum == null ? 0.0 : combineAmountSum);
		Double trainAmountSum = trainService.countAmountByVenue(venue.getId());
		list.add(trainAmountSum == null ? 0.0 : trainAmountSum);
		return new ApiMessage(200, "查询成功", list);
	}

	/**
	 * @Description: 场馆订场收入列表
	 * @author 宋高俊
	 * @date 2018年8月23日 下午7:33:34
	 */
	@RequestMapping(value = "/order/list", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage orderlist(PageBean pageBean, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
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
			map.put("type", order.getType() == 0 ? "待支付" : order.getType() == 1 ? "订单完成" : order.getType() == 2 ? "取消支付" : order.getType() == 3 ? "支付超时"
					: order.getType() == 4 ? "已退款" : order.getType() == 5 ? "支付成功待确认" : "支付成功待消费");// 状态
			listmap.add(map);

		}
		Map<String, Object> map = new HashMap<>();
		map.put("pageTotal", pageInfo.getTotal());
		map.put("pageNum", pageInfo.getPageNum());
		map.put("pageList", listmap);
		return new ApiMessage(200, "查询成功", map);
	}

	/**
	 * @Description: 场馆订场收入详情
	 * @author 宋高俊
	 * @date 2018年8月23日 下午7:33:34
	 */
	@RequestMapping("/order/details")
	@ResponseBody
	public ApiMessage orderdetails(String id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
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
			reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " " + StringUtil.timeToTimestr(timestrs) + " "
					+ reserve.getField().getName());// 时间数据
			timeSumStr += StringUtil.timeToTimestr(timestrs) + " ";
			reservemap.put("price", reserve.getReserveamount());// 单价
			reservelist.add(reservemap);
		}
		if (order.getCoachid() != null) {
			Coach coach = coachService.selectByPrimaryKey(order.getCoachid());
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " " + timeSumStr + " 教练");// 时间数据

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
	public ApiMessage combinelist(PageBean pageBean, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
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
			map.put("type", combine.getType() == 0 ? "未取消" : "已取消");// 金额
			listmap.add(map);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("pageTotal", pageInfo.getTotal());
		map.put("pageNum", pageInfo.getPageNum());
		map.put("pageList", listmap);
		return new ApiMessage(200, "查询成功", map);
	}

	/**
	 * @Description: 场馆散拼收入详情
	 * @author 宋高俊
	 * @date 2018年8月23日 下午7:33:34
	 */
	@RequestMapping("/combine/details")
	@ResponseBody
	public ApiMessage combinedetails(String id, HttpServletRequest request) {

		Combine combine = combineService.selectByPrimaryKey(id);

		if (combine == null) {
			return new ApiMessage(400, "订单查询失败");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("id", combine.getId());// id
		map.put("date", DateUtil.getFormat(combine.getCombinedate(), "yyyy年MM月dd日") + " " + StringUtil.timeToTimestr(combine.getCombinetimeframe().split(",")));// 球馆地址
		map.put("address", combine.getVenue().getName() + " " + combine.getField().getName());// 详细地址
		map.put("boy", combine.getBoy());// 设定人数

		List<Map<String, Object>> list = new ArrayList<>();
		List<CombineJoin> combineJoins = combineJoinMapper.selectByCombine(id);
		for (CombineJoin combineJoin : combineJoins) {
			Map<String, Object> joinMap = new HashMap<>();
			joinMap.put("name", combineJoin.getMember().getName());// 姓名
			joinMap.put("price", combineJoin.getPrice());// updateCombine
			list.add(joinMap);
		}

		map.put("list", list);// 已加入
		return ApiMessage.succeed(map);
	}

	/**
	 * @Description: 场馆培训收入列表
	 * @author 宋高俊
	 * @date 2018年8月24日 上午9:33:14
	 */
	@RequestMapping("/train/list")
	@ResponseBody
	public ApiMessage trainlist(PageBean pageBean, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue venue = venueService.selectByManager(manager.getId());
		// 分页
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<TrainJoin> list = trainJoinMapper.selectByVenue(venue.getId());
		PageInfo<TrainJoin> pageInfo = new PageInfo<>(list);

		List<Map<String, Object>> listmap = new ArrayList<>();
		for (TrainJoin trainJoin : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", trainJoin.getId());// id
			map.put("name", trainJoin.getMember().getName());// 报名人
			map.put("date", DateUtil.getFormat(trainJoin.getCreatetime()));// 加入时间
			map.put("content", trainJoin.getContent());// 课程
			map.put("price", trainJoin.getPrice());// 金额
			map.put("type", trainJoin.getType() == 0 ? "待支付" : trainJoin.getType() == 1 ? "支付成功" : trainJoin.getType() == 2 ? "支付取消"
					: trainJoin.getType() == 3 ? "支付超时" : "已退款");// 状态
			listmap.add(map);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("pageTotal", pageInfo.getTotal());
		map.put("pageNum", pageInfo.getPageNum());
		map.put("pageList", listmap);
		return new ApiMessage(200, "查询成功", map);
	}

	/**
	 * @Description: 退费列表
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月7日 下午7:56:57
	 */
	@RequestMapping("/amountRefundWay/list")
	@ResponseBody
	public ApiMessage amountRefundWayList(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue venue = venueService.selectByManager(manager.getId());

		List<AmountRefundWay> list = amountRefundWayMapper.selectByVenue(venue.getId());

		Map<String, Object> returnMap = new HashMap<>();

		List<Map<String, Object>> listMap0 = new ArrayList<>();
		List<Map<String, Object>> listMap1 = new ArrayList<>();
		List<Map<String, Object>> listMap2 = new ArrayList<>();
		for (AmountRefundWay arw : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", arw.getId());// id
			map.put("rete", arw.getRete());// 费率
			map.put("proposal", arw.getProposal());// 建议值
			if (arw.getType() == 0) {
				if (arw.getStartTime() == 0) {
					map.put("date", "<" + arw.getEndTime() + "小时");// 时段
				} else if (arw.getEndTime() == 0) {
					map.put("date", ">" + arw.getStartTime() + "小时");// 时段
				} else {
					map.put("date", arw.getStartTime() + "~" + arw.getEndTime() + "小时");// 时段
				}
				listMap0.add(map);
			} else if (arw.getType() == 1) {
				if (arw.getStartTime() == 0) {
					map.put("date", "<" + arw.getEndTime() + "小时");// 时段
				} else if (arw.getEndTime() == 0) {
					map.put("date", ">" + arw.getStartTime() + "小时");// 时段
				} else {
					map.put("date", arw.getStartTime() + "~" + arw.getEndTime() + "小时");// 时段
				}
				listMap1.add(map);
			} else if (arw.getType() == 2) {
				if (arw.getStartTime() == 0) {
					map.put("date", "开始前");// 时段
				} else if (arw.getStartTime() == 1) {
					map.put("date", "开始后");// 时段
				}
				listMap2.add(map);
			}
		}

		returnMap.put("listMap0", listMap0);
		returnMap.put("listMap1", listMap1);
		returnMap.put("listMap2", listMap2);

		return new ApiMessage(200, "查询成功", returnMap);
	}

	/**
	 * @Description: 修改退费比率
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月7日 下午7:56:57
	 */
	@RequestMapping("/amountRefundWay/update")
	@ResponseBody
	public ApiMessage amountRefundWayUpdate(String id, int rete, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");

		AmountRefundWay arf = new AmountRefundWay();
		arf.setId(id);
		arf.setRete(rete);
		arf.setModifyTime(new Date());

		int flag = amountRefundWayMapper.updateByPrimaryKeySelective(arf);
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(200, "修改失败");
		}
	}
}
