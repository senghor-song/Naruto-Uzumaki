package com.xiaoyi.ssm.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.MemberDay;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.VenueRefund;
import com.xiaoyi.ssm.service.MemberDayService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.VenueRefundService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SendEmail;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.Utils;

@Service
public class EverydayPayVenueJob {

	private static Logger logger = Logger.getLogger(EverydayPayVenueJob.class.getName());

	/**
	 * 定时任务，每天1:00:01执行统计昨天场馆收入
	 */
	@Scheduled(cron = "1 0 1 1/1 * ? ")
//	@Scheduled(cron = "0/1 * * * * ? ")
	@Transactional
	public void oneDayPayVenueJob() {
		logger.info("开始执行统计昨天场馆收入------------------->");
		OrderService orderService = SpringUtils.getBean("orderServiceImpl", OrderService.class);
		MemberService memberService = SpringUtils.getBean("memberServiceImpl", MemberService.class);
		VenueRefundService venueRefundService = SpringUtils.getBean("venueRefundServiceImpl", VenueRefundService.class);
		MemberDayService memberDayService = SpringUtils.getBean("memberDayServiceImpl", MemberDayService.class);
		OperationLogService operationLogService = SpringUtils.getBean("operationLogServiceImpl", OperationLogService.class);
		
		Date nowDate = DateUtil.getPreTime(new Date(), 3, -1);
		String date = DateUtil.getFormat(nowDate, "yyyy-MM-dd");
		
		// 查询昨天所有有过订单的场馆
		List<Member> memberDayAll = memberService.selectByDateOut(date);

		// 交易总额
		double amountSum = 0.0;
		// 订单总数
		int orderSum = 0;
		
		for (Member member : memberDayAll) {
			// 订单总数
			int orderCount = 0;
			// 订单收入总金额+场馆收取手续费
			double orderAmount = 0.0;
			// 平台费用
			double orderFeeAmount = 0.0;
			
			// 退款手续费收取金额
			List<VenueRefund> venueRefunds = venueRefundService.selectByMemberDate(member.getId(), date);
			for (VenueRefund venueRefund : venueRefunds) {
				// 计算场馆收取退款手续费总金额
				Order order = orderService.selectByPrimaryKey(venueRefund.getOrderId());
				// 退款订单的平台费
				orderFeeAmount = Arith.add(orderFeeAmount, order.getPriceFee());
				orderAmount = Arith.add(orderAmount, Arith.sub(order.getPrice(), venueRefund.getAmountRefund()));
				
			}
			
			// 计算订单总数
			orderCount += venueRefunds.size();
			
			// 根据用户ID查询有关订单
			List<Order> orders = orderService.selectByMemberDate(member.getId(), date);
			
			for (int j = 0; j < orders.size(); j++) {
				// 计算订单收入总金额
				orderAmount = Arith.add(orderAmount, orders.get(j).getPrice());
				// 计算平台费总额
				orderFeeAmount = Arith.add(orderFeeAmount, orders.get(j).getPriceFee());
			}
			
			orderSum += orders.size();
			// 计算订单总数
			orderCount += orders.size();
			
			MemberDay memberDay = new MemberDay();
			memberDay.setId(Utils.getUUID());
			memberDay.setCreateTime(new Date());
			memberDay.setModifyTime(new Date());
			memberDay.setMemberId(member.getId());
			memberDay.setMemberNo(member.getMemberno());
			memberDay.setMemberLimit(member.getWxpayment());
			memberDay.setCountDay(nowDate);
			memberDay.setOughtAmount(orderAmount);
			
			if(orderAmount == 0) {
				continue;
			}
			
			amountSum = Arith.add(amountSum, orderAmount);
			
//			// 订单收取手续费
//			double orderFee = 0.0;
//			// 补贴额度
//			double paySubsidy = 0.0;
//
//			if (orderAmount > member.getWxpayment()) {
//				if (member.getWxpayment() >= 0) {
//					orderFee = Arith.halfUp(Arith.mul(Arith.sub(orderAmount, member.getWxpayment()), 0.01), 2);
//					paySubsidy = Arith.halfUp(Arith.mul(member.getWxpayment(), 0.01), 2);
//				} else {
//					orderFee = Arith.halfUp(Arith.mul(orderAmount, 0.01), 2);
//				}
//			} else {
//				paySubsidy = Arith.halfUp(Arith.mul(orderAmount, 0.01), 2);
//			}

			memberDay.setRealityAmount(Arith.sub(orderAmount, orderFeeAmount));
			memberDay.setPaySubsidy(0.0);
			memberDay.setOrderFee(orderFeeAmount);
			memberDay.setOrderCount(orderCount);
			memberDay.setTypeFlag(0);
			memberDayService.insertSelective(memberDay);
			
			// 将已消费和已退款的订单修改为已提现状态
			orderService.updateByPayVenue(member.getId(),date, memberDay.getId());
			
			orderService.updateByOrdertype(member.getId(),date);
			
		}
		
		operationLogService.saveLog("00000000000000000000000000000000", "<订场>场馆订场共收入" + amountSum + "元，" + date + "，交易订单数" + orderSum, "0.0.0.0");

		SendEmail.sendEmail("13928769@qq.com", "自动退款任务执行完成", "<订场>场馆订场共收入" + amountSum + "元，" + date + "，交易订单数" + orderSum);
		
		logger.info("结束执行统计昨天场馆收入------------------->");
	}
}
