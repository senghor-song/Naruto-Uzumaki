package com.xiaoyi.ssm.controller.venue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueRefund;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.VenueRefundService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueStatisService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;
import com.xiaoyi.ssm.wxPay.WXPayWxappUtil;

/**
 * @Description: 退款数据控制器
 * @author 宋高俊
 * @date 2018年11月16日16:37:09
 */
@Controller
@RequestMapping("venue/amountRefund")
public class ApiAmountRefundController {
	
    private final Logger logger = Logger.getLogger(ApiAmountRefundController.class.getName());

	@Autowired
	private MemberService memberService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private VenueStatisService venueStatisService;
	@Autowired
	private VenueTemplateService venueTemplateService;
	@Autowired
	private VenueRefundService venueRefundService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ReserveService reserveService;

	/**
	 * @Description: 申请订场退款列表
	 * @author 宋高俊
	 * @param date  2018-08
	 * @return
	 * @date 2018年11月16日16:38:08
	 */
	@RequestMapping(value = "/applyList")
	@ResponseBody
	public ApiMessage applyList(HttpServletRequest request, String venueid) {
		
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		// 获取申请订场退款的数据
		List<VenueRefund> list = venueRefundService.selectByVenue(venueid);
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (VenueRefund venueRefund : list) {
			Order order = orderService.selectByPrimaryKey(venueRefund.getOrderId());
			Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
			
			Map<String, Object> map = new HashMap<>();
			map.put("id", venueRefund.getId());// id
			map.put("appnickname", orderMember.getAppnickname());// 用户名称
			map.put("price", order.getPrice());// 预收金额
			map.put("refundStatus", venueRefund.getRefundStatus());// 退款状态(0=申请中1=退款成功2=退款失败)
			map.put("remark", venueRefund.getRemark());// 备注
			
			listMaps.add(map);
		}
		return new ApiMessage(200, "查询成功", listMaps);
	}
	
	/**
	 * @Description: 退款处理数据接口
	 * @author 宋高俊
	 * @param request
	 * @param venueid
	 * @return
	 * @date 2018年11月16日 下午8:32:44
	 */
	@RequestMapping(value = "/getVenueRefund")
	@ResponseBody
	public ApiMessage getVenueRefund(HttpServletRequest request, String venueRefundId) {
		
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		// 获取申请订场退款的数据
		VenueRefund venueRefund = venueRefundService.selectByPrimaryKey(venueRefundId);
		Order order = orderService.selectByPrimaryKey(venueRefund.getOrderId());
		Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
		Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", venueRefund.getId());// id
		map.put("appnickname", orderMember.getAppnickname());// 用户名称
		map.put("createTime", DateUtil.getFormat(venueRefund.getCreateTime()));// 申请时间
		map.put("venueName", venue.getName());// 场馆名称
		
		List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
		String area = "";
		String time = ""; 
		if (listReserve.size() > 0) {
			area = venue.getName() + listReserve.get(0).getField().getName();
			time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
		}

		
		map.put("field", area);// 球场
		map.put("time", time);// 时段
		map.put("content", venueRefund.getContent());// 理由
		
		List<Integer> times = new ArrayList<Integer>();
		// 获取时段的所有半时
		for (int i = 0; i < listReserve.size(); i++) {
			String[] strings = listReserve.get(i).getReservetimeframe().split(",");
			for (int j = 0; j < strings.length; j++) {
				times.add(Integer.valueOf(strings[j]));
			}
		}
		// 从小到大排序
		Collections.sort(times);
		String time2 = StringUtil.timeToTimestr(times.get(0));
		Date orderDate = DateUtil.getParse(cn.hutool.core.date.DateUtil.formatDate(order.getOrderdate()) + " " + time2, "yyyy-MM-dd HH:mm");
		map.put("dateTime", DateUtil.between(venueRefund.getCreateTime(), orderDate));// 开始前时间
		map.put("amountRate", venueRefund.getAmountRate());// 费率
		map.put("amountRefund", venueRefund.getAmountRefund());// 可退金额
		map.put("amountFee", venueRefund.getAmountFee());// 扣费金额
		
		
		return new ApiMessage(200, "查询成功", map);
	}
	
	/**
	 * @Description: 处理申请订场退款
	 * @author 宋高俊
	 * @param date  2018-08
	 * @return
	 * @date 2018年11月16日16:38:08
	 */
	@RequestMapping(value = "/setVenueRefund")
	@ResponseBody
	public ApiMessage setVenueRefund(HttpServletRequest request, String venueRefundId, String content, Double price) {
		
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 获取申请订场退款的数据
		VenueRefund venueRefund = venueRefundService.selectByPrimaryKey(venueRefundId);
		venueRefund.setAmountRefund(Arith.sub(venueRefund.getAmountSum(), price));
		venueRefund.setAmountFee(price);
		venueRefund.setRemark(content);
		venueRefund.setRefundStatus(1);
		venueRefundService.updateByPrimaryKeySelective(venueRefund);

		// 大于6小时
		WXPayWxappUtil.weiXinRefund(venueRefund.getOrderId(), venueRefund.getAmountSum(), venueRefund.getAmountRefund(), "审核通过退款", 0);
		
		Order order = orderService.selectByPrimaryKey(venueRefund.getOrderId());
		Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
		Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
		// 通知内容
		List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
		String area = "";
		String timeStr = ""; 
		if (listReserve.size() > 0) {
			area =  venue.getName() + listReserve.get(0).getField().getName();
			timeStr = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
		}
		
		// 预定通知消息
		if (!StringUtil.isBank(orderMember.getOpenid())) {
			JSONObject datajson = new JSONObject();
			datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
			datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
			datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
			datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"退款成功\"}"));
			datajson.put( "remark",
					JSONObject.parseObject("{\"value\":\"您预订的" + area + "，日期"
							+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + timeStr + "用场，退款成功。\"}"));
			logger.info(WXPayUtil.sendWXappTemplate(orderMember.getOpenid(), WXConfig.wxTemplateId, "/pages/index/index", datajson));
		}
		return new ApiMessage(200, "审核成功");
	}

}
