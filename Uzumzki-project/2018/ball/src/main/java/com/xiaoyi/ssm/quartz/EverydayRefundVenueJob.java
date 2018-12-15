package com.xiaoyi.ssm.quartz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueRefund;
import com.xiaoyi.ssm.service.AmountRefundWayService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.VenueRefundService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SendEmail;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;
import com.xiaoyi.ssm.wxPay.WXPayWxappUtil;

@Service
public class EverydayRefundVenueJob {

	private static Logger logger = Logger.getLogger(EverydayRefundVenueJob.class.getName());

	/**
	 * 定时任务，每天23:45:00执行统计昨天场馆收入
	 */
	@Scheduled(cron = "0 45 23 * * ? ")
//	@Scheduled(cron = "0/1 * * * * ? ")
	@Transactional
	public void oneDayRefundVenueJob() {
		logger.info("开始执行统计今天场馆未处理退款------------------->");
		VenueService venueService = SpringUtils.getBean("venueServiceImpl", VenueService.class);
		OrderService orderService = SpringUtils.getBean("orderServiceImpl", OrderService.class);
		MemberService memberService = SpringUtils.getBean("memberServiceImpl", MemberService.class);
		VenueRefundService venueRefundService = SpringUtils.getBean("venueRefundServiceImpl", VenueRefundService.class);
		OperationLogService operationLogService = SpringUtils.getBean("operationLogServiceImpl", OperationLogService.class);
		ReserveService reserveService = SpringUtils.getBean("reserveServiceImpl", ReserveService.class);
		AmountRefundWayService amountRefundWayService = SpringUtils.getBean("amountRefundWayServiceImpl", AmountRefundWayService.class);
		
		Date nowDate = new Date();
		String date = DateUtil.getFormat(nowDate, "yyyy-MM-dd");
		Date dateStart = DateUtil.getWeeHours(nowDate, 0);
		Date dateEnd = DateUtil.getWeeHours(nowDate, 1);
		
		// 查询昨天所有有过订单的场馆
		List<Venue> venuesDateAll = venueService.selectByDateOut(date, -1);

		int autoRefundCount = 0;// 自动退款数
		int manualRefundCount = 0;// 人工退款数
		double refundAmountSum = 0.0;// 退款总金额
		double refundFeeSum = 0.0;// 场馆收取退款手续费
		for (Venue venue : venuesDateAll) {
			
			// 本次场馆统计的退款总额
			double venueRefundAmount = 0.0;// 退款总金额
			double venueRefundFee = 0.0;// 场馆收取退款手续费
			
			// 优先处理昨天未处理的退款申请
			List<VenueRefund> venueRefunds = venueRefundService.selectBySettleVenue(venue.getId(), dateStart, dateEnd);
			for (VenueRefund venueRefund : venueRefunds) {
				// 退款失败不做处理
				if (venueRefund.getRefundStatus() == 2) {
					continue;
				}
				// 处理未处理的退款申请
				if (venueRefund.getRefundStatus() == 0) {
					autoRefundCount++;
					// 判断是否是天气原因导致的退款
					if (venueRefund.getContent().indexOf("天气") > 0) {
						Order order = orderService.selectByPrimaryKey(venueRefund.getOrderId());
						AmountRefundWay amountRefundWay = amountRefundWayService.selectByPrimaryKey(order.getVenueid());
						List<Reserve> list = reserveService.selectByOrder(venueRefund.getOrderId());
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
						Date newDate = venueRefund.getCreateTime();
						Date orderDate = DateUtil.getParse(cn.hutool.core.date.DateUtil.formatDate(order.getOrderdate()) + " " + time, "yyyy-MM-dd HH:mm");
						Integer fee = 0;
						if (newDate.getTime() + 60 * 60 * 1000 * 2 >orderDate.getTime()) {
							fee = amountRefundWay.getFee1();
						} else if (newDate.getTime() + 60 * 60 * 1000 * 4 >orderDate.getTime()) {
							fee = amountRefundWay.getFee2();
						} else if (newDate.getTime() + 60 * 60 * 1000 * 6 >orderDate.getTime()) {
							fee = amountRefundWay.getFee3();
						}

						venueRefund.setAmountFee(Arith.mul(order.getPrice(), Arith.round(fee/100.0,2)));
						venueRefund.setAmountRefund(Arith.sub(order.getPrice(), venueRefund.getAmountFee()));
						venueRefund.setAmountRate(fee);
					}

					// 修改订单状态
					Order order = orderService.selectByPrimaryKey(venueRefund.getOrderId());
					
					if (WXPayWxappUtil.weiXinRefund(venueRefund.getOrderId(), order.getPrice(), venueRefund.getAmountRefund(), "场馆超时未处理自动退款", 0)) {
						venueRefund.setRefundStatus(1);
					} else {
						venueRefund.setRefundStatus(2);
					}
					// 修改退款申请的状态
					venueRefundService.updateByPrimaryKeySelective(venueRefund);

					order.setModifytime(new Date());
					order.setRefundtime(new Date());
					order.setType(4);
					orderService.updateByPrimaryKeySelective(order);
					
					List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
					String area = "";
					String time = ""; 
					if (listReserve.size() > 0) {
						area = venue.getName() + listReserve.get(0).getField().getName();
						time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
					}
					// 场馆
					Member orderMember = memberService.selectByPrimaryKey(order.getMemberid());
					// 预定通知消息
					JSONObject datajson = new JSONObject();
					datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
					datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
					datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
					datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"已退款\"}"));
					
					// 支付超时,发给用户
					datajson.put( "remark",
							JSONObject.parseObject("{\"value\":\"您预约的" + area + "，日期"
									+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，场馆默认按扣费费率退款。\"}"));
					logger.info(WXPayUtil.sendWXappTemplate(orderMember.getOpenid(), WXConfig.wxTemplateId, "/pages/index/index", datajson));
				}else {
					manualRefundCount++;
				}

				// 计算退款总金额
				venueRefundAmount = Arith.add(venueRefundAmount, venueRefund.getAmountRefund());
				// 计算场馆收取退款手续费总金额
				venueRefundFee = Arith.add(venueRefundFee, venueRefund.getAmountFee());
				
			}
			
			refundAmountSum = Arith.add(refundAmountSum, venueRefundAmount);
			refundFeeSum = Arith.add(refundFeeSum, venueRefundFee);
		}
		
		operationLogService.saveLog("00000000000000000000000000000000", "<订场>申请退款，场馆处理" + manualRefundCount + "个申请，自动处理" + autoRefundCount + 
				"个申请，退款金额共" + refundAmountSum + "元", "0.0.0.0");
		
		SendEmail.sendEmail("13928769@qq.com", "自动退款任务执行完成", "<订场>申请退款，场馆处理" + manualRefundCount + "个申请，自动处理" + autoRefundCount + 
				"个申请，退款金额共" + refundAmountSum + "元");
		
		logger.info("开始执行统计今天场馆未处理退款------------------->");
	}
}
