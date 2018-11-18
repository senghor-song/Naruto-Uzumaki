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
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.util.DateUtil;

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

	/**
	 * @Description: 订单页面
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "12");
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
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Order> list = orderService.selectByAll(null);
		PageInfo<Order> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", order.getId());// ID
			map.put("createtime", DateUtil.getFormat(order.getCreatetime()));// 订场时间
			map.put("orderno", order.getOrderno());// 编号
			map.put("city", order.getCity().getCity());// 城市
			map.put("venue", order.getVenue().getName());// 场馆
			map.put("member", order.getMember().getName());// 订场人
			map.put("time", order.getTimesum() / 0.5);// 时间片
			map.put("type",
					order.getType() == 0 ? "待支付"
							: order.getType() == 1 ? "订单完成"
									: order.getType() == 2 ? "取消支付" 
											: order.getType() == 3 ? "支付超时" 
													: order.getType() == 4 ? "已退款" : 
														order.getType() == 5 ? "支付成功待确认" : "支付成功待消费");// 状态
			map.put("orderlog", orderLogMapper.countByOrder(order.getId()));// 日志
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
	
}
