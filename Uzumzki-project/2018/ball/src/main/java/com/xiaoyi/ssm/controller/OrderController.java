package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.OrderLogMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminOrderDto;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.AmountRefund;
import com.xiaoyi.ssm.model.MemberDay;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.VenueCoach;
import com.xiaoyi.ssm.service.AmountRefundService;
import com.xiaoyi.ssm.service.MemberDayService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.VenueCoachService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.StringUtil;

/**
 * @Description: 订单控制器
 * @author 宋高俊
 * @date 2018年8月18日 上午11:35:49
 */
@Controller(value = "adminOrderController")
@RequestMapping(value = "/admin/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private VenueCoachService venueCoachService;
	@Autowired
	private AmountRefundService amountRefundService;
	@Autowired
	private MemberDayService memberDayService;

	/**
	 * @Description: 订单页面
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "11");
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute("btn"+list.get(i).getId(), "1");
		}
		return "admin/order/list";
	}

	/**
	 * @Description: 订单数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage, Integer venueSelectType, Integer selectOrderType, String dateStr) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		AdminOrderDto adminOrderDto = new AdminOrderDto();
		adminOrderDto.setSelectType(venueSelectType);
		adminOrderDto.setOrderType(selectOrderType);
		adminOrderDto.setDateStr(dateStr);
		List<Order> list = orderService.selectBySearch(adminOrderDto);
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", order.getId());// ID
			map.put("createtime", DateUtil.getFormat(order.getCreatetime()));// 订场时间
			map.put("orderdate", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd"));// 订场时间
			map.put("orderno", order.getOrderno());// 编号
			map.put("city", order.getCity().getCity());// 城市
			map.put("venue", order.getVenue().getVenueno());// 场馆
			map.put("member", order.getMember().getMemberno());// 订场人
			map.put("price", String.format("%.2f", order.getPrice()));// 订单金额
			if (order.getVenueRefund() != null && order.getVenueRefund().getId() != null) {
				order.setType(7);
			}
			map.put("type",
					order.getType() == 0 ? "待支付"
							: order.getType() == 1 ? "订单完成"
									: order.getType() == 2 ? "取消支付" 
											: order.getType() == 3 ? "支付超时" 
													: order.getType() == 4 ? "已退款" 
															: order.getType() == 5 ? "支付成功待确认" 
																	: order.getType() == 7 ? "退款中" : "支付成功待消费");// 状态
			
			map.put("orderlog", orderLogMapper.countByOrder(order.getId()));// 日志

			
			map.put("showPrice", String.format("%.2f", order.getShowPrice()));// 订单应付
			map.put("price", String.format("%.2f", order.getPrice()));// 订单实付
			map.put("priceFee", String.format("%.2f", order.getPriceFee()));// 平台费
			
			if (order.getAmounttype() == 1) {
				double orderPrice = Arith.sub(order.getPrice(), order.getPriceFee());
				MemberDay memberDay = memberDayService.selectByPrimaryKey(order.getAmountid());
				if(memberDay != null && memberDay.getTypeFlag() == 1) {
					if (order.getType() == 1) {
						map.put("type", "已回款");// 已回款状态
						map.put("venuePayAmount", orderPrice);
					} else if(order.getType() == 4){
						map.put("type", "已退款");// 订单状态
						AmountRefund amountRefund = amountRefundService.selectByNowSourceId(order.getId());
						if (amountRefund != null && orderPrice > amountRefund.getAmount()) {
							map.put("venuePayAmount", Arith.sub(orderPrice, amountRefund.getAmount()));
						}
					}
				}
			}
			AmountRefund amountRefund = amountRefundService.selectByNowSourceId(order.getId());
			if (amountRefund != null) {
				map.put("amountRefund", amountRefund.getAmount());// 退款金额
			}
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 订单日志
	 * @author 宋高俊
	 * @date 2018年8月20日 下午3:34:33
	 */
	@RequestMapping(value = "/orderloglist")
	@ResponseBody
	public AdminMessage orderloglist(String orderid) {
		List<OrderLog> list = orderLogMapper.selectByOrder(orderid);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			OrderLog orderLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", orderLog.getId());// ID
			map.put("createtime", DateUtil.getFormat(orderLog.getCreatetime()));// 时间
			map.put("type", orderLog.getType() == 0 ? "系统" : "");// 类别
			map.put("content", orderLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}
	
	
	/**
	 * @Description: 查看订单详情
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月6日下午7:49:16
	 */
	@RequestMapping(value = "/orderDetails")
	@ResponseBody
	public AdminMessage orderDetails(String id) {
		Order order = orderService.selectByPrimaryKey(id);
		List<Map<String, Object>> reservelist = new ArrayList<>();

		for (int j = 0; j < order.getReserves().size(); j++) {
			Reserve reserve = order.getReserves().get(j);
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("venueName", order.getVenue().getName());// 场馆名称
			reservemap.put("fieldName", reserve.getField().getName());// 场地
			reservemap.put("type", "订场");// 项目
			String[] timestrs = reserve.getReservetimeframe().split(",");
			reservemap.put("timestr", DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + " "
					+ StringUtil.timeToTimestr(timestrs));// 时间数据
			reservemap.put("price", String.format("%.2f", reserve.getReserveamount()));// 单价
			reservelist.add(reservemap);
		}
		if (order.getCoachid() != null) {
			VenueCoach venueCoach = venueCoachService.selectByPrimaryKey(order.getCoachid());
			TrainCoach trainCoach = trainCoachService.selectByTrainCoachId(venueCoach.getTrainCoachId());
			Map<String, Object> reservemap = new HashMap<>();
			reservemap.put("venueName", order.getVenue().getName());// 教练名称
			reservemap.put("type", "陪练");// 项目
			reservemap.put("timestr",trainCoach.getName() + " 教练");// 内容

			reservemap.put("price", String.format("%.2f", order.getCoachamount()));// 单价
			reservelist.add(reservemap);
		}

		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("venueName", order.getVenue().getName());// 教练名称
		orderMap.put("type", "平台费");// 项目
		orderMap.put("timestr", "平台费");// 时间数据
		orderMap.put("price", String.format("%.2f", order.getPriceFee()));// 平台费
		orderMap.put("fieldName", "平台费");// 场地
		reservelist.add(orderMap);
		
		return new AdminMessage(100, 0, reservelist);
	}
	
}
