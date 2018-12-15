package com.xiaoyi.ssm.controller.venue;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.AmountRefundWayMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.AmountRefund;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.MemberDay;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainOrderComment;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueCoach;
import com.xiaoyi.ssm.model.VenueRefund;
import com.xiaoyi.ssm.service.AmountRefundService;
import com.xiaoyi.ssm.service.MemberDayService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderLogService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainOrderCommentService;
import com.xiaoyi.ssm.service.VenueCoachService;
import com.xiaoyi.ssm.service.VenueRefundService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;
import com.xiaoyi.ssm.wxPay.WXPayWxappUtil;
import com.xiaoyi.ssm.wxPay.XMLUtil;

/**
 * @Description: 订单接口控制器
 * @author 宋高俊
 * @date 2018年8月22日 上午11:21:03
 */
@Controller
@RequestMapping("venue/manager/order")
public class ApiOrderController {

    private final Logger logger = Logger.getLogger(ApiOrderController.class.getName());

	@Autowired
	private ReserveService reserveService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private TrainOrderCommentService trainOrderCommentService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private AmountRefundWayMapper amountRefundWayMapper;
	@Autowired
	private VenueRefundService venueRefundService;
	@Autowired
	private VenueCoachService venueCoachService;
	@Autowired
	private AmountRefundService amountRefundService;
	@Autowired
	private MemberDayService memberDayService;

	// 获取线程池连接
//	@Autowired
//	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * @Description: 订单列表数据
	 * @author 宋高俊
	 * @date 2018年8月22日 上午11:21:40
	 */
	@RequestMapping(value = "/orderlist")
	@ResponseBody
	public ApiMessage orderlist(Integer type, HttpServletRequest request, PageBean pageBean) {
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		List<Order> list = orderService.selectAll(member.getId(), type);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("orderId", order.getId());// id
			map.put("orderNo", order.getOrderno());// 订单号
			map.put("price", order.getPrice());// 总价格
			map.put("showPrice", order.getShowPrice());// 应付金额
			map.put("priceFee", order.getPriceFee());// 平台费
			List<Map<String, Object>> reservelist = new ArrayList<>();
			
			String timeSumStr = "";
			for (int j = 0; j < order.getReserves().size(); j++) {
				Reserve reserve = order.getReserves().get(j);
				Map<String, Object> reservemap = new HashMap<>();
				reservemap.put("name", order.getVenue().getName() + " " + reserve.getField().getName());// 场馆名称
				reservemap.put("image", order.getVenue().getImage());// 图片
				String[] timestrs = reserve.getReservetimeframe().split(",");
				reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
						+ StringUtil.timeToTimestr(timestrs));// 时间数据
				timeSumStr += StringUtil.timeToTimestr(timestrs) + " ";
				reservemap.put("price", reserve.getReserveamount());// 单价
				reservelist.add(reservemap);
			}
			// 单独选择一个教练数据
			if (order.getCoachid() != null) {
				VenueCoach venueCoach = venueCoachService.selectByPrimaryKey(order.getCoachid());
				TrainCoach trainCoach = trainCoachService.selectByTrainCoachId(venueCoach.getTrainCoachId());
				Map<String, Object> reservemap = new HashMap<>();
				reservemap.put("name", trainCoach.getName() + " 教练");// 教练名称
				reservemap.put("image", trainCoach.getHeadImage());// 图片
				reservemap.put("timestr",
						DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " " + timeSumStr);// 时间数据

				reservemap.put("price", order.getCoachamount());// 单价
				reservelist.add(reservemap);
			}
			if (order.getVenueRefund() != null && order.getVenueRefund().getId() != null) {
				map.put("type", 7);// 退款中
			} else {
				map.put("type", order.getType());// 已完成
			}
			
			map.put("reservelist", reservelist);
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}

	/**
	 * @Description: 订单详情数据
	 * @author 宋高俊
	 * @date 2018年8月22日 下午2:53:00
	 */
	@RequestMapping(value = "/orderPay")
	@ResponseBody
	public ApiMessage orderPay(String orderid, HttpServletRequest request,Boolean showFee) {
		if (showFee == null) {
			showFee = true;
		}
//		String token = (String) request.getAttribute("token");
//		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Order order = orderService.selectByPrimaryKey(orderid);
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", order.getId());// id
		map.put("orderNo", order.getOrderno());// 订单号
		if (showFee) {
			map.put("oughtPrice", order.getShowPrice());// 应付价格
			map.put("realityPrice", order.getPrice());// 实付价格
		} else {
			map.put("oughtPrice", Arith.sub(order.getPrice(), order.getPriceFee()));// 总价格
			map.put("realityPrice", Arith.sub(order.getShowPrice(), order.getPriceFee()));// 应付金额
		}
		map.put("lintFlag", order.getPrice() > 0 ? "lineUp" : "lineDown");// 线上线下
		map.put("startDate", new Date().getTime());
		map.put("endDate", DateUtil.getPreTime(order.getCreatetime(), 1, 5).getTime());
		map.put("priceFee", order.getPriceFee());// 平台费
		List<Map<String, Object>> reservelist = new ArrayList<>();

		String timeSumStr = "";
		for (int j = 0; j < order.getReserves().size(); j++) {
			Reserve reserve = order.getReserves().get(j);
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("name", order.getVenue().getName() + "  " + reserve.getField().getName());// 场馆名称
			reservemap.put("image", order.getVenue().getImage());// 图片
			String[] timestrs = reserve.getReservetimeframe().split(",");
			reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
					+ StringUtil.timeToTimestr(timestrs));// 时间数据
			timeSumStr += StringUtil.timeToTimestr(timestrs) + " ";
			reservemap.put("price", reserve.getReserveamount());// 单价
			reservelist.add(reservemap);
		}
		if (order.getCoachid() != null) {
			VenueCoach venueCoach = venueCoachService.selectByPrimaryKey(order.getCoachid());
			TrainCoach trainCoach = trainCoachService.selectByTrainCoachId(venueCoach.getTrainCoachId());
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("name", trainCoach.getName() + " 教练");// 教练名称
			reservemap.put("image", trainCoach.getHeadImage());// 图片
			reservemap.put("timestr",
					DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " " + timeSumStr);// 时间数据

			reservemap.put("price", order.getCoachamount());// 单价
			reservelist.add(reservemap);
		}

		map.put("reservelist", reservelist);
		
		map.put("createTime", DateUtil.getFormat(order.getCreatetime()));// 创建时间
		map.put("payTime", DateUtil.getFormat(order.getPaytime()));// 支付时间
		map.put("confirmTime", DateUtil.getFormat(order.getConfirmtime()));// 确认时间
		map.put("applyTime", DateUtil.getFormat(order.getApplytime()));// 申请退款时间
		map.put("cancelTime", DateUtil.getFormat(order.getCanceltime()));// 订单取消时间
		map.put("refundtime", DateUtil.getFormat(order.getRefundtime()));// 订单退款成功时间
		
		Member member = memberService.selectByPrimaryKey(order.getMemberid());
		map.put("appnickname", member.getAppnickname());// 昵称
		map.put("phone", member.getPhone());// 手机号
		
		AmountRefund amountRefund = amountRefundService.selectByNowSourceId(order.getId());
		if (amountRefund != null) {
			map.put("amountRefund", amountRefund.getAmount());// 退款金额
		} else {
			map.put("amountRefund", "");// 退款金额
		}
		
		if (order.getAmounttype() == 1) {
			MemberDay memberDay = memberDayService.selectByPrimaryKey(order.getAmountid());
			if(memberDay != null && memberDay.getTypeFlag() == 1) {
				double orderPrice = Arith.sub(order.getPrice(), order.getPriceFee());
				if (order.getType() == 1) {
					map.put("venuePayAmount", orderPrice);
				} else if(order.getType() == 4){
					if (amountRefund != null && orderPrice > amountRefund.getAmount()) {
						map.put("venuePayAmount", Arith.sub(orderPrice, amountRefund.getAmount()));
					}else {
						map.put("venuePayAmount", 0);
					}
				}
			}
		}
		return ApiMessage.succeed(map);
	}
	
	/**  
	 * @Description: 申请退款数据
	 * @author 宋高俊  
	 * @param orderid
	 * @param request
	 * @return 
	 * @date 2018年11月9日 下午2:20:31 
	 */ 
	@RequestMapping(value = "/getOrderRefund")
	@ResponseBody
	public ApiMessage getOrderRefund(String orderid, HttpServletRequest request) {

//		String token = (String) request.getAttribute("token");
//		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Order order = orderService.selectByPrimaryKey(orderid);
		List<Reserve> list = reserveService.selectByOrder(orderid);
		List<Integer> times = new ArrayList<Integer>();
		// 获取时段的所有半时
		for (int i = 0; i < list.size(); i++) {
			String[] strings = list.get(i).getReservetimeframe().split(",");
			for (int j = 0; j < strings.length; j++) {
				times.add(Integer.valueOf(strings[j]));
			}
		}
		// 从小到大排序
		Collections.sort(times);
		String time = StringUtil.timeToTimestr(times.get(0));
		Date newDate = new Date();
		Date orderDate = DateUtil.getParse(cn.hutool.core.date.DateUtil.formatDate(order.getOrderdate()) + " " + time, "yyyy-MM-dd HH:mm");
		
		JSONObject jsonObject = new JSONObject();
		
		AmountRefundWay amountRefundWay = amountRefundWayMapper.selectByPrimaryKey(order.getVenueid());
		

		// 申请时间大于订单时间则已开始
		
		if (newDate.getTime() > orderDate.getTime()) {
			jsonObject.put("flag", true);
			jsonObject.put("weather", amountRefundWay.getWeatherEnd());// 开始后费率
			return new ApiMessage(200, "已开始", jsonObject);
		} else {
			// 申请时间加6小时判断是否大于订单开始时间 
			
			jsonObject.put("isSixHour", false);
			jsonObject.put("weather", amountRefundWay.getWeatherStart());
			
			if (newDate.getTime() + 60 * 60 * 1000 * 2 >orderDate.getTime()) {
				jsonObject.put("fee", amountRefundWay.getFee1());
				jsonObject.put("isSixHour", true);
			} else if (newDate.getTime() + 60 * 60 * 1000 * 4 >orderDate.getTime()) {
				jsonObject.put("fee", amountRefundWay.getFee2());
				jsonObject.put("isSixHour", true);
			} else if (newDate.getTime() + 60 * 60 * 1000 * 6 >orderDate.getTime()) {
				jsonObject.put("fee", amountRefundWay.getFee3());
				jsonObject.put("isSixHour", true);
			}
			
			jsonObject.put("flag", false);
			jsonObject.put("time", DateUtil.between(newDate, orderDate));
			return new ApiMessage(200, "未开始", jsonObject);
		}
	}
	

	/**  
	 * @Description: 处理申请退款接口
	 * @author 宋高俊  
	 * @param orderid
	 * @param request
	 * @return 
	 * @date 2018年11月9日 下午2:20:31 
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/applyOrderRefund")
	@ResponseBody
	public ApiMessage applyOrderRefund(String orderid, HttpServletRequest request, String content) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Map<String, Object> dayCountMap = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_DAY_REFUND_COUNT, member.getId());
		String date = DateUtil.getFormat(new Date(), "yyyy-MM-dd");

		// 判断是否有过统计次数
		if (dayCountMap != null && date.equals(dayCountMap.get("date").toString())) {
			Integer count = (Integer) dayCountMap.get("count");
			if (count > 5) {
				return new ApiMessage(400, "您申请退款已超过5次");
			}
			count++;
			dayCountMap.put("count", count);
		} else {
			// 无统计过则初始化次数
			dayCountMap = new HashMap<String, Object>();
			dayCountMap.put("date", date);
			dayCountMap.put("count", 1);
		}
		
		Order order = orderService.selectByPrimaryKey(orderid);
		if (order == null) {
			return new ApiMessage(400, "订单不存在");
		}

		Date nowDate = DateUtil.getParse(DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " 23:00:00");
//		Date nowDate = DateUtil.getParse(DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " 10:30:00");
		if (new Date().getTime() > nowDate.getTime()) {
			return new ApiMessage(400, "只允许在"+DateUtil.getFormat(nowDate)+"发起前退款");
		}
		
		if (order.getType() == 5 || order.getType() == 6) {
			List<Reserve> list = reserveService.selectByOrder(orderid);
			List<Integer> times = new ArrayList<Integer>();
			// 获取时段的所有半时
			for (int i = 0; i < list.size(); i++) {
				String[] strings = list.get(i).getReservetimeframe().split(",");
				for (int j = 0; j < strings.length; j++) {
					times.add(Integer.valueOf(strings[j]));
				}
			}
			// 从小到大排序
			Collections.sort(times);
			String time = StringUtil.timeToTimestr(times.get(0));
			Date newDate = DateUtil.getPreTime(new Date(), 2, 6);
			Date orderDate = DateUtil.getParse(cn.hutool.core.date.DateUtil.formatDate(order.getOrderdate()) + " " + time, "yyyy-MM-dd HH:mm");
			
			Venue venue = venueService.selectByPrimaryKey(order.getVenueid());

			// 通知内容
			List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
			String area = "";
			String timeStr = ""; 
			if (listReserve.size() > 0) {
				area =  venue.getName() + listReserve.get(0).getField().getName();
				timeStr = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
			}
			
			// 退款发送给场馆
			Member venueMember = memberService.selectByPhone(venue.getContactPhone());
			String openId = venueMember.getOpenid();

			JSONObject datajson = new JSONObject();
			datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
			
			OrderLog orderLog = new OrderLog();
			orderLog.setId(Utils.getUUID());
			orderLog.setCreatetime(new Date());
			orderLog.setOrderid(orderid);
			orderLog.setType(0);
			
			// 保存申请时间
			order.setApplytime(new Date());
			
			// 离开始时间是否大于6小时并且状态需为已确认
			if (newDate.getTime() > orderDate.getTime() && !StringUtil.isBank(venue.getTrainteam()) && order.getType() == 6 && order.getPrice() > 0) {
				if (StringUtil.isBank(content)) {
					return new ApiMessage(400, "订单已确认,请重新申请退款");
				}
				
				// 小于6小时
				// 退款流水
				VenueRefund venueRefund = venueRefundService.selectByOrder(orderid, 0);
				if (venueRefund != null) {
					return new ApiMessage(400, "您已申请，请等待回复");
				}
				
				// 获取时段的所有半时
				for (int i = 0; i < list.size(); i++) {
					String[] strings = list.get(i).getReservetimeframe().split(",");
					for (int j = 0; j < strings.length; j++) {
						times.add(Integer.valueOf(strings[j]));
					}
				}
				// 从小到大排序
				Collections.sort(times);
				String time2 = StringUtil.timeToTimestr(times.get(0));
				Date newDate2 = new Date();
				Date orderDate2 = DateUtil.getParse(cn.hutool.core.date.DateUtil.formatDate(order.getOrderdate()) + " " + time2, "yyyy-MM-dd HH:mm");
				AmountRefundWay amountRefundWay = amountRefundWayMapper.selectByPrimaryKey(order.getVenueid());
				Integer fee = 0;
				if (content.indexOf("天气") > 0) {
					if (newDate2.getTime() > orderDate2.getTime()) {
						fee = amountRefundWay.getWeatherEnd();
					} else {
						fee = amountRefundWay.getWeatherStart();
					}
				} else {
					if (newDate2.getTime() + 60 * 60 * 1000 * 2 >orderDate2.getTime()) {
						fee = amountRefundWay.getFee1();
					} else if (newDate2.getTime() + 60 * 60 * 1000 * 4 >orderDate2.getTime()) {
						fee = amountRefundWay.getFee2();
					} else if (newDate2.getTime() + 60 * 60 * 1000 * 6 >orderDate2.getTime()) {
						fee = amountRefundWay.getFee3();
					}
				}
				
				if (fee == 0) {
					orderLog.setContent("用户申请退款,无需审核,直接退款");
					// 费率为0直接退款
					WXPayWxappUtil.weiXinRefund(orderid, order.getPrice(), order.getPrice(), "用户主动退款", 0);

					order.setModifytime(new Date());
					order.setType(4);
					order.setRefundtime(new Date());

					// 预定通知消息
					datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"退款成功\"}"));
					order.setCanceltime(new Date());
				} else {
					orderLog.setContent("用户申请退款");
					
					venueRefund = new VenueRefund();
					venueRefund.setId(Utils.getUUID());
					venueRefund.setCreateTime(new Date());
					venueRefund.setContent(content);
					// 减去平台费用的总金额
					double orderPrice = Arith.sub(order.getPrice(), order.getPriceFee());
					venueRefund.setAmountSum(orderPrice);
					venueRefund.setAmountFee(Arith.mul(orderPrice, Arith.round(fee/100.0,2)));
					venueRefund.setAmountRefund(Arith.sub(orderPrice, venueRefund.getAmountFee()));
					venueRefund.setAmountRate(fee);
					venueRefund.setOrderId(orderid);
					venueRefund.setRefundStatus(0);
					venueRefundService.insertSelective(venueRefund);
					
					// 预定通知消息
					datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"申请退款\"}"));
				}
				
			}else {
				orderLog.setContent("用户申请退款,无需审核,直接退款");
				// 大于6小时
				WXPayWxappUtil.weiXinRefund(orderid, order.getPrice(), order.getPrice(), "用户主动退款", 0);

				order.setModifytime(new Date());
				order.setType(4);
				order.setRefundtime(new Date());

				// 预定通知消息
				datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"退款成功\"}"));

				order.setCanceltime(new Date());
			}

			orderLogService.insert(orderLog);
			
			orderService.updateByPrimaryKeySelective(order);

			// 申请成功则加1次退款次数
			RedisUtil.addRedis(Global.REDIS_DAY_REFUND_COUNT, member.getId(), dayCountMap);

			datajson.put( "remark",
					JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area + "，日期"
							+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + timeStr + "用场，申请退款。\"}"));
			if (!StringUtil.isBank(venue.getTrainteam())) {
				TrainCoach trainCoach = trainCoachService.selectByMemberTeamManager(venueMember.getId(), venue.getTrainteam());
				if (trainCoach != null) {
					// 有权限查看
					logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/user/venueMenu/lock/lock?venueId="+venue.getId() + "&title=" + venue.getName(), datajson));
					return new ApiMessage(200, "退款操作中");
				}
			}
			logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
			return new ApiMessage(200, "退款操作中");
		} else if (order.getType() == 0 || order.getType() == 2 || order.getType() == 3) {
			return new ApiMessage(400, "该订单未支付金额");
		} else if (order.getType() == 1) {
			return new ApiMessage(400, "该订单已完成,不能退款");
		} else {
			return new ApiMessage(400, "该订单已退款");
		}
	}
	

	/**  
	 * @Description: 取消申请退款接口
	 * @author 宋高俊  
	 * @param orderid
	 * @param request
	 * @return 
	 * @date 2018年11月9日 下午2:20:31 
	 */ 
	@RequestMapping(value = "/cancelApplyOrderRefund")
	@ResponseBody
	public ApiMessage cancelApplyOrderRefund(String orderid, HttpServletRequest request) {

		VenueRefund venueRefund = venueRefundService.selectByOrder(orderid, 0);
		if (venueRefund == null) {
			return new ApiMessage(400, "当前订单没有申请退款");
		} else {
			venueRefund.setRefundStatus(2);
			venueRefund.setRemark("用户取消退款申请");
			venueRefundService.updateByPrimaryKeySelective(venueRefund);
			
			Order order = orderService.selectByPrimaryKey(orderid);
			order.setApplytime(null);
			orderService.updateByPrimaryKey(order);

			OrderLog orderLog = new OrderLog();
			orderLog.setId(Utils.getUUID());
			orderLog.setCreatetime(new Date());
			orderLog.setOrderid(orderid);
			orderLog.setType(0);
			orderLog.setContent("用户取消申请退款");
			orderLogService.insertSelective(orderLog);
			
			return new ApiMessage(200, "取消成功");
		}
	}
	
	/**
	 * @Description: 取消订单接口
	 * @author 宋高俊
	 * @date 2018年8月22日 下午4:01:02
	 */
	@RequestMapping(value = "/deleteOrder")
	@ResponseBody
	public ApiMessage deleteOrder(String orderid, HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Order order = orderService.selectByMemberOrder(member.getId(), orderid);
		if (order == null) {
			return new ApiMessage(400, "订单不存在");
		}
		if (order.getType() == 0) {
			order.setType(2);
			order.setModifytime(new Date());
			order.setCanceltime(new Date());
			order.setRefundtime(new Date());
			orderService.updateByPrimaryKeySelective(order);

			// 获取订单数据
			Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
			Member memberInfo = memberService.selectByPrimaryKey(order.getMemberid());
			List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
			String area = "";
			String time = ""; 
			if (listReserve.size() > 0) {
				area = venue.getName() + listReserve.get(0).getField().getName();
				time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
			}

			// 场馆取消订单
			Member venueMember = memberService.selectByPhone(venue.getContactPhone());
			String openId = venueMember.getOpenid();
			// 预定通知消息
			JSONObject datajson = new JSONObject();
			datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
			datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"取消订单\"}"));
			datajson.put( "remark",
					JSONObject.parseObject("{\"value\":\"球友" + memberInfo.getAppnickname() + "(手机" + memberInfo.getPhone() + ")申请预约球场" + area + "，日期"
							+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，取消订单。\"}"));
			// 判断是否有入驻
			if (!StringUtil.isBank(venue.getTrainteam())) {
				TrainCoach trainCoach = trainCoachService.selectByMemberTeamManager(venueMember.getId(), venue.getTrainteam());
				if (trainCoach != null) {
					// 有权限查看
					logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/user/venueMenu/lock/lock?venueId="+venue.getId() + "&title=" + venue.getName(), datajson));
					return new ApiMessage(200, "退款操作中");
				}
			}
			logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));

			OrderLog orderLog = new OrderLog();
			orderLog.setId(Utils.getUUID());
			orderLog.setCreatetime(new Date());
			orderLog.setOrderid(orderid);
			orderLog.setType(0);
			orderLog.setContent("用户取消订单");
			orderLogService.insertSelective(orderLog);
			
			return new ApiMessage(200, "取消成功");
		} else {
			return new ApiMessage(400, "该订单已取消");
		}
	}
	
	/**
	 * @Description: 删除订单接口
	 * @author 宋高俊
	 * @date 2018年8月22日 下午4:01:02
	 */
	@RequestMapping(value = "/removeOrder")
	@ResponseBody
	public ApiMessage removeOrder(String orderid, HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Order order = orderService.selectByMemberOrder(member.getId(), orderid);
		if (order == null) {
			return new ApiMessage(400, "订单不存在");
		}
		if (order.getDeleteflag() == 0) {
			order.setDeleteflag(1);
			order.setModifytime(new Date());
			orderService.updateByPrimaryKeySelective(order);

			OrderLog orderLog = new OrderLog();
			orderLog.setId(Utils.getUUID());
			orderLog.setCreatetime(new Date());
			orderLog.setOrderid(orderid);
			orderLog.setType(0);
			orderLog.setContent("用户删除订单");
			orderLogService.insertSelective(orderLog);
			
			return new ApiMessage(200, "删除成功");
		} else {
			return new ApiMessage(400, "该订单已删除");
		}
	}
	
	/**
	 * @Description: 确认订单接口
	 * @author 宋高俊
	 * @date 2018年8月22日 下午4:01:02
	 */
	@RequestMapping(value = "/determineOrder")
	@ResponseBody
	public ApiMessage determineOrder(String orderid, HttpServletRequest request) {

		Order order = orderService.selectByPrimaryKey(orderid);
		if (order == null) {
			return new ApiMessage(400, "订单不存在");
		}
		if (order.getType() == 5) {
			order.setType(6);
			order.setModifytime(new Date());
			order.setConfirmtime(new Date());
			orderService.updateByPrimaryKeySelective(order);
			
			// 获取订单数据
			Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
			Member member = memberService.selectByPrimaryKey(order.getMemberid());
			List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
			String area = "";
			String time = ""; 
			if (listReserve.size() > 0) {
				area = venue.getName() + listReserve.get(0).getField().getName();
				time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
			}

			// 场馆确认超时，发给用户
			String openId = member.getOpenid();
			// 预定通知消息
			JSONObject datajson = new JSONObject();
			datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
			datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"确认成功\"}"));
			datajson.put( "remark",
					JSONObject.parseObject("{\"value\":\"您申请预约" + area + "，日期"
							+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，确认成功，请按时前往场地。\"}"));
			logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "/pages/index/index", datajson));

			OrderLog orderLog = new OrderLog();
			orderLog.setId(Utils.getUUID());
			orderLog.setCreatetime(new Date());
			orderLog.setOrderid(orderid);
			orderLog.setType(0);
			orderLog.setContent("场馆确认订单");
			orderLogService.insertSelective(orderLog);
			
			return new ApiMessage(200, "确认成功");
		} else {
			return new ApiMessage(200, "该订单已操作");
		}
	}
	
	/**  
	 * @Description: 后台取消订单接口
	 * @author 宋高俊  
	 * @param orderid
	 * @param request
	 * @return 
	 * @date 2018年11月9日 下午8:45:45 
	 */ 
	@RequestMapping(value = "/cancelOrder")
	@ResponseBody
	public ApiMessage cancelOrder(String orderid, HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Order order = orderService.selectByPrimaryKey(orderid);
		
		order.setType(4);
		order.setModifytime(new Date());
		order.setCanceltime(new Date());
		order.setRefundtime(new Date());
		orderService.updateByPrimaryKeySelective(order);
		WXPayWxappUtil.weiXinRefund(orderid, order.getPrice(), order.getPrice(), "场馆联系人取消订单", 0);
		
		// 获取订单数据
		Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
		Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
		List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
		String area = "";
		String time = ""; 
		if (listReserve.size() > 0) {
			area = venue.getName() + listReserve.get(0).getField().getName();
			time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
		}

		// 场馆确认超时，发给用户
		String openId = orderMember.getOpenid();
		// 预定通知消息
		JSONObject datajson = new JSONObject();
		datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
		datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
		datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
		datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"订场失败\"}"));
		datajson.put( "remark",
				JSONObject.parseObject("{\"value\":\"您申请预约" + area + "，日期"
						+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，未能通过审核，可自行联系场馆方。\"}"));
		logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "/pages/index/index", datajson));
		
		OrderLog orderLog = new OrderLog();
		orderLog.setId(Utils.getUUID());
		orderLog.setCreatetime(new Date());
		orderLog.setOrderid(orderid);
		orderLog.setType(0);
		orderLog.setContent("场馆取消订单");
		orderLogService.insertSelective(orderLog);
		
		return new ApiMessage(200, "退款成功");
	}
	
	/**  
	 * @Description: 后台订场详情
	 * @author 宋高俊  
	 * @param orderid
	 * @param request
	 * @return 
	 * @date 2018年11月9日 下午8:52:10 
	 */ 
	@RequestMapping(value = "/getOrderDetails")
	@ResponseBody
	public ApiMessage getOrderDetails(String orderid, HttpServletRequest request) {
		
		Order order = orderService.selectByPrimaryKey(orderid);
		
		// 获取订单数据
		Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
		Member member = memberService.selectByPrimaryKey(order.getMemberid());
		List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
		String area = "";
		String time = ""; 
		if (listReserve.size() > 0) {
			area = listReserve.get(0).getField().getName();
			time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderid", order.getId());// ID 
		map.put("orderTime", DateUtil.getFormat(order.getCreatetime()));// 订场时间
		map.put("appnickname", member.getAppnickname());// 订场会员
		map.put("phone", member.getPhone());// 会员电话
		map.put("venueName", venue.getName());// 场馆
		map.put("area", area);// 场地
		map.put("time", DateUtil.getFormat(order.getOrderdate(), "MM-dd") + "  " + time);// 使用时间
		if (!StringUtil.isBank(order.getCoachid())) {
			VenueCoach venueCoach = venueCoachService.selectByPrimaryKey(order.getCoachid());
			TrainCoach trainCoach = trainCoachService.selectByTrainCoachId(venueCoach.getTrainCoachId());
			map.put("price", Arith.sub(order.getPrice(),order.getCoachamount()));// 场地费用
			map.put("trainCoach",trainCoach.getName());// 服务人员
			map.put("trainCoachAmount", order.getCoachamount());// 服务费用
		}else {
			map.put("price", order.getPrice());// 场地费用
		}
		return new ApiMessage(200, "获取成功", map);
	}

	/**
	 * @Description: 获取微信支付参数
	 * @author 宋高俊
	 * @date 2018年8月24日 下午3:34:38
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/toPay", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage dopay(HttpServletRequest request, String orderId) throws JDOMException, IOException {
		// 判断orderId
		if (orderId == null) {
			return new ApiMessage(400, "订单号为空");
		}

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		Order order = orderService.selectByMemberOrder(member.getId(), orderId);

		// 判断订单是否为空
		if (order == null) {
			return new ApiMessage(400, "订单不存在");
		}

		Map map = WXPayWxappUtil.getPayParams("订场预定金额", order.getId(), member.getAppopenid(), order.getPrice(), request.getRemoteAddr(), WXConfig.NOTIFY_URL1);
		
		return new ApiMessage(200, "支付参数", map);
	}

	/**
	 * 手机支付完成回调,这个方法主要是完成支付后，项目后台业务需要
	 * 
	 * @param request
	 * @param response 这个方法是用流传递的 这个方法是用流传递的 这个方法是用流传递的
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/weixinNotify", method = RequestMethod.POST)
	public void WXPayBack(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		// 读取参数
		InputStream inputStream = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		logger.info("in.toString() = " + in.toString());
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();
		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		Iterator it = m.keySet().iterator();
		logger.info("it =" + it);
		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();

		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}
		// 账号信息
		String key = WXConfig.paternerKey; // key
		// 判断签名是否正确
		if (WXPayUtil.isTenpaySign("UTF-8", packageParams, key)) {
			logger.info("微信支付成功回调");
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				String orderNo = (String) packageParams.get("out_trade_no");
				// 这里 根据实际业务场景 做相应的操作
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
				// 这一步开始就是写公司业务需要的代码了，不用参考我的 没有价值
				Order order = orderService.selectByPrimaryKey(orderNo);
				Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
				if (venue.getOrderverify() == 0) {
				
					// 修改订单状态支付成功
					order.setModifytime(new Date());
					order.setType(5);
					order.setPaytype(1);
					order.setPaytime(new Date());
					orderService.updateByPrimaryKeySelective(order);
	
					OrderLog orderLog = new OrderLog();
					orderLog.setId(Utils.getUUID());
					orderLog.setCreatetime(new Date());
					orderLog.setOrderid(orderNo);
					orderLog.setType(0);
					orderLog.setContent("接收到微信通知，订单已支付成功");
					orderLogService.insert(orderLog);
					logger.info("微信订单号" + orderNo + "付款成功");
					
					// 判断该场馆是否设置了发送支付短信
					if (venue.getReservePaySms() == 1) {
						try {
							Member member = memberService.selectByPrimaryKey(order.getMemberid());
							List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
							String area = "";
							String time = ""; 
							if (listReserve.size() > 0) {
								area =  venue.getName() + listReserve.get(0).getField().getName();
								time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
							}

							
							Member venueMember = memberService.selectByPhone(venue.getContactPhone());
							String openId = venueMember.getOpenid();

							if (StringUtil.isBank(openId)) {
								Integer code = Global.getTemplateCode(venue.getInformPhone());
								MoblieMessageUtil.sendTemplateSms2(venue.getInformPhone(), member.getAppnickname(), member.getPhone(),
										DateUtil.getFormat(order.getOrderdate(), "MM月dd号"), time, order.getPrice(), code);
								RedisUtil.setRedis(venue.getInformPhone() + "_" + code, order.getId(), 1800);
							}
							
							// 预定通知消息
							JSONObject datajson = new JSONObject();
							datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
							datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
							datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
							datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"支付完成\"}"));
							datajson.put( "remark",
									JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area + "，日期"
											+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，已支付待确认，已支付"+order.getPrice()+"元。请25分钟内确认用场信息，超时将自动取消，费用原路返回球友。\"}"));
							
							if (!StringUtil.isBank(venue.getTrainteam())) {
								TrainCoach trainCoach = trainCoachService.selectByMemberTeamManager(venueMember.getId(), venue.getTrainteam());
								if (trainCoach != null) {
									// 有权限查看
									logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/user/venueMenu/lock/lock?venueId="+venue.getId() + "&title=" + venue.getName(), datajson));
								}else {
									logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
								}
							}else {
								logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
							}
							
							
//							// 保存用于短信申请的入驻信息
//							VenueEnter venueEnter = new VenueEnter();
//							venueEnter.setId(Utils.getUUID());
//							venueEnter.setCreateTime(new Date());
//							venueEnter.setMemberId(venue.getOwner());
//							venueEnter.setSourceFlag(1);
//							venueEnter.setTitle(venue.getName());
//							venueEnter.setLongitude(venue.getLongitude());
//							venueEnter.setLatitude(venue.getLatitude());
//							venueEnter.setAddress(venue.getAddress());
//							venueEnter.setCheckFlag(0);
//							venueEnter.setBallType(venue.getType());
//							venueEnter.setMainName(venue.getOwner());
//							venueEnter.setMainPhone(venue.getContactPhone());
//							venueEnter.setBallSum(venue.getBallsum().toString());
//							venueEnter.setHeadImage(venue.getImage());
//							venueEnter.setTrainTeamName(venue.getName());
//							
//							City city = cityService.selectByPrimaryKey(venue.getCityid());
//							if (city != null) {
//								venueEnter.setCityName(city.getCity());
//								venueEnter.setCityId(city.getId());
//							}
//							
//							// 保存准备入驻的数据
//							RedisUtil.addRedis(Global.REDIS_VENUE, venue.getInformPhone(), venueEnter);
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("发送支付短信失败");
						}
					}
				
				}
			} else {
				logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {
			logger.info("通知签名验证失败");
		}
	}
	
	/**  
	 * @Description: 分页获取培训机构评价内容
	 * @author 宋高俊  
	 * @param pageBean
	 * @param request
	 * @param id
	 * @return 
	 * @date 2018年10月27日 下午3:34:52 
	 */ 
	@RequestMapping(value = "/getOrderComment")
	@ResponseBody
	public ApiMessage getOrderComment(PageBean pageBean, HttpServletRequest request, String id){
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<TrainOrderComment> list = trainOrderCommentService.selectByTeam(id);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> commentMap = new HashMap<String, Object>();
			commentMap.put("id", list.get(i).getId()); // ID 
			commentMap.put("createTime", DateUtil.getFormat(list.get(i).getCreateTime())); // 时间
			commentMap.put("title", list.get(i).getTrainCourse().getTitle()); // 课程名称
			commentMap.put("classHour", list.get(i).getClassHour()); // 课时
			commentMap.put("appnickname", list.get(i).getMember().getAppnickname()); // 用户昵称
			commentMap.put("content", list.get(i).getContent()); // 评价内容
			commentMap.put("appavatarurl", list.get(i).getMember().getAppavatarurl()); // 用户头像
			commentMap.put("headImage", list.get(i).getTrainCoach().getHeadImage()); // 教练头像
			commentMap.put("commentSelect", list.get(i).getCommentSelect()); // 评价选择(1=好评2=中评3=差评4=拒绝评价)
			maps.add(commentMap);
		}
		return new ApiMessage(200, "支付参数", maps);
	}
	
	/**  
	 * @Description: 分页获取培训机构评价内容
	 * @author 宋高俊  
	 * @param pageBean
	 * @param request
	 * @param id
	 * @return 
	 * @date 2018年10月27日 下午3:34:52 
	 */ 
	@RequestMapping(value = "/getCourseComment")
	@ResponseBody
	public ApiMessage getCourseComment(PageBean pageBean, HttpServletRequest request, String id){
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<TrainOrderComment> list = trainOrderCommentService.selectByTeam(id);
		PageInfo<TrainOrderComment> pageInfo = new PageInfo<TrainOrderComment>(list);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> commentMap = new HashMap<String, Object>();
			commentMap.put("id", list.get(i).getId()); // ID 
			commentMap.put("createTime", DateUtil.getFormat(list.get(i).getCreateTime())); // 时间
			commentMap.put("title", list.get(i).getTrainCourse().getTitle()); // 课程名称
			commentMap.put("classHour", list.get(i).getClassHour()); // 课时
			commentMap.put("appnickname", list.get(i).getMember().getAppnickname()); // 用户昵称
			commentMap.put("content", list.get(i).getContent()); // 评价内容
			commentMap.put("appavatarurl", list.get(i).getMember().getAppavatarurl()); // 用户头像
			commentMap.put("headImage", list.get(i).getTrainCoach().getHeadImage()); // 教练头像
			commentMap.put("commentSelect", list.get(i).getCommentSelect()); // 评价选择(1=好评2=中评3=差评4=拒绝评价)
			maps.add(commentMap);
		}
		return new ApiMessage(200, pageInfo.getTotal()+"", maps);
	}
	
	/**  
	 * @Description: 分页获取教练评价内容
	 * @author 宋高俊  
	 * @param pageBean
	 * @param request
	 * @param id
	 * @return 
	 * @date 2018年10月27日 下午3:34:52 
	 */ 
	@RequestMapping(value = "/getCoachComment")
	@ResponseBody
	public ApiMessage getCoachComment(PageBean pageBean, HttpServletRequest request, String id){
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<TrainOrderComment> list = trainOrderCommentService.selectByCoach(id);
		PageInfo<TrainOrderComment> pageInfo = new PageInfo<TrainOrderComment>(list);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> commentMap = new HashMap<String, Object>();
			commentMap.put("id", list.get(i).getId()); // ID 
			commentMap.put("createTime", DateUtil.getFormat(list.get(i).getCreateTime())); // 时间
			commentMap.put("title", list.get(i).getTrainCourse().getTitle()); // 课程名称
			commentMap.put("classHour", list.get(i).getClassHour()); // 课时
			commentMap.put("appnickname", list.get(i).getMember().getAppnickname()); // 用户昵称
			commentMap.put("content", list.get(i).getContent()); // 评价内容
			commentMap.put("appavatarurl", list.get(i).getMember().getAppavatarurl()); // 用户头像
			commentMap.put("headImage", list.get(i).getTrainCoach().getHeadImage()); // 教练头像
			commentMap.put("commentSelect", list.get(i).getCommentSelect()); // 评价选择(1=好评2=中评3=差评4=拒绝评价)
			maps.add(commentMap);
		}
		return new ApiMessage(200, pageInfo.getTotal()+"", maps);
	}
	
	
	/**
	 * @Description: 订单列表数据
	 * @author 宋高俊
	 * @date 2018年8月22日 上午11:21:40
	 */
	@RequestMapping(value = "/venue/orderlist")
	@ResponseBody
	public ApiMessage venueOrderList(String venueid, Integer type, HttpServletRequest request, PageBean pageBean) {
		
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, (String) request.getAttribute("token"));

		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		
		List<Order> list = orderService.selectByVenueAll(venueid, type);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("orderId", order.getId());// id
			map.put("orderNo", order.getOrderno());// 订单号
			double orderPrice = Arith.sub(order.getPrice(), order.getPriceFee());
			
			map.put("price", orderPrice);// 总价格
			map.put("showPrice", Arith.sub(order.getShowPrice(), order.getPriceFee()));// 应付金额
			map.put("priceFee", order.getPriceFee());// 平台费
			Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
			if (orderMember != null) {
				map.put("memberName", orderMember.getAppnickname());// 球友名称
				map.put("memberPhone", orderMember.getPhone());// 球友手机号
			} else {
				map.put("memberName", "");// 球友名称
				map.put("memberPhone", "");// 球友手机号
			}
			
			List<Map<String, Object>> reservelist = new ArrayList<>();
			
			String timeSumStr = "";
			for (int j = 0; j < order.getReserves().size(); j++) {
				Reserve reserve = order.getReserves().get(j);
				Map<String, Object> reservemap = new HashMap<>();
				reservemap.put("name", order.getVenue().getName() + " " + reserve.getField().getName());// 场馆名称
				reservemap.put("image", order.getVenue().getImage());// 图片
				String[] timestrs = reserve.getReservetimeframe().split(",");
				reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
						+ StringUtil.timeToTimestr(timestrs));// 时间数据
				timeSumStr += StringUtil.timeToTimestr(timestrs) + " ";
				reservemap.put("price", reserve.getReserveamount());// 单价
				reservelist.add(reservemap);
			}
			// 单独选择一个教练数据
			if (order.getCoachid() != null) {
				VenueCoach venueCoach = venueCoachService.selectByPrimaryKey(order.getCoachid());
				if (venueCoach != null ) {
					TrainCoach trainCoach = trainCoachService.selectByTrainCoachId(venueCoach.getTrainCoachId());
					if (venueCoach != null ) {
						Map<String, Object> reservemap = new HashMap<>();
						reservemap.put("name", trainCoach.getName() + " 教练");// 教练名称
						reservemap.put("image", trainCoach.getHeadImage());// 图片
						reservemap.put("timestr",
								DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " " + timeSumStr);// 时间数据
						
						reservemap.put("price", order.getCoachamount());// 单价
						reservelist.add(reservemap);
					}
				}
			}
			if (order.getVenueRefund() != null && order.getVenueRefund().getId() != null) {
				map.put("type", 7);// 退款中
				map.put("venueRefundId", order.getVenueRefund().getId());// 退款中
			} else {
				if (order.getAmounttype() == 1) {
					MemberDay memberDay = memberDayService.selectByPrimaryKey(order.getAmountid());
					if(memberDay != null && memberDay.getTypeFlag() == 1) {
						if (order.getType() == 1) {
							map.put("type", 8);// 已回款状态
							map.put("venuePayAmount", orderPrice);
						} else if(order.getType() == 4){
							map.put("type", order.getType());// 订单状态
							AmountRefund amountRefund = amountRefundService.selectByNowSourceId(order.getId());
							if (amountRefund != null && orderPrice > amountRefund.getAmount()) {
								map.put("venuePayAmount", Arith.sub(orderPrice, amountRefund.getAmount()));
							}else {
								map.put("venuePayAmount", 0);
							}
						}else {
							map.put("type", order.getType());// 订单状态
							map.put("venuePayAmount", 0);
						}
					}else {
						map.put("type", order.getType());// 订单状态
					}
				}else {
					map.put("type", order.getType());// 订单状态
				}
			}
			
//			Map<String, Object> orderMap = new HashMap<>();
//			orderMap.put("name", "平台费");// 平台费用
//			orderMap.put("image", "https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/baseImage/orderFee.png");// 图片
//			orderMap.put("timestr", "");// 时间数据
//			orderMap.put("price", order.getPriceFee());// 平台费
//			reservelist.add(orderMap);
			
			map.put("reservelist", reservelist);
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}
}
