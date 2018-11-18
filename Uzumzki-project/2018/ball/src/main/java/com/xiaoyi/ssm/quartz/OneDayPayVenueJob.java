package com.xiaoyi.ssm.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueDay;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.VenueDayService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

@Service
public class OneDayPayVenueJob {

	private static Logger logger = Logger.getLogger(OneDayPayVenueJob.class.getName());

	/**
	 * 定时任务，每天0:00:01执行统计昨天场馆收入
	 */
	@Scheduled(cron = "1 0 0 1/1 * ? ")
//	@Scheduled(cron = "0 0/1 * * * ? ")
	public void countOneDayPayVenueJob() {
		logger.info("开始执行统计昨天场馆收入------------------->");
		VenueService venueService = SpringUtils.getBean("venueServiceImpl", VenueService.class);
		OrderService orderService = SpringUtils.getBean("orderServiceImpl", OrderService.class);
		MemberService memberService = SpringUtils.getBean("memberServiceImpl", MemberService.class);
		VenueDayService venueDayService = SpringUtils.getBean("venueDayServiceImpl", VenueDayService.class);
		
		String date = DateUtil.getFormat(new Date(), "yyyy-MM-dd");
		// 查询昨天以前已确认待消费的订单
		List<Venue> venues = venueService.selectByDateOut(date);
		for (int i = 0; i < venues.size(); i++) {
			// 昨天有订单的场馆
			Venue venue = venues.get(i);
			Member member = memberService.selectByPrimaryKey(venue.getMemberId());
			if (StringUtil.isBank(member.getOpenid())) {
				logger.info(venue.getName()+" 场馆汇款人未关注公众号，取消本次统计场馆收入金额");
				continue;
			}
			
			List<Order> orders = orderService.selectByPayVenue(venue.getId(),date);
			// 订单收入总金额
			double amountCollect = 0;
			for (int j = 0; j < orders.size(); j++) {
				// 计算订单收入总金额
				amountCollect = Arith.add(amountCollect, orders.get(j).getPrice());
			}
			
			// 退款手续费收入总金额
			
			
			
			
			
			
			
			
			
			
			
			
			
			String amountId = Utils.getUUID();
			VenueDay venueDay = new VenueDay();
			venueDay.setId(amountId);
			venueDay.setCreateTime(new Date());
			venueDay.setModifyTime(new Date());
			venueDay.setVenueId(venue.getId());
			venueDay.setCountDay(new Date());
			venueDay.setCountAmount(amountCollect);
			venueDay.setMemberId(member.getId());
			venueDay.setTypeFlag(0);
			venueDayService.insertSelective(venueDay);
			
			// 需要调用企业支付，支付订场金额
//			if (WXPayUtil.wxCompanyPayment(amountId, member.getOpenid(), amountCollect, "场馆收入")) {
//				// 企业支付成功
//				venueDay.setTypeFlag(1);
//				
//				orderService.updateByPayVenue(venue.getId(),date);
//				
//			} else {
//				// 企业支付失败
//				venueDay.setTypeFlag(2);
//			}
			// 暂时默认支付成功
			venueDay.setTypeFlag(1);
			orderService.updateByPayVenue(venue.getId(),date);
			
			venueDayService.updateByPrimaryKeySelective(venueDay);
		}
		logger.info("结束执行统计昨天场馆收入------------------->");
	}
}
