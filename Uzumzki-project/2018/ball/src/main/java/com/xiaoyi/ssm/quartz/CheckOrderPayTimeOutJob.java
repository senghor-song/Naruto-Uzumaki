package com.xiaoyi.ssm.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderLogService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

@Service
public class CheckOrderPayTimeOutJob {

    private static Logger logger = Logger.getLogger(CheckOrderPayTimeOutJob.class.getName());

	/**
	 * @Description: 每分钟检查定时任务检查5分钟支付时间超时的订单
	 * @author 宋高俊
	 * @date 2018年9月20日 下午8:35:13
	 */
//	@Scheduled(cron = "0 0/1 * * * ? ")
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void checkOrderPayTimeOutJob() {
		OrderService orderService = SpringUtils.getBean("orderServiceImpl", OrderService.class);
		OrderLogService orderLogService = SpringUtils.getBean("orderLogServiceImpl", OrderLogService.class);
		VenueService venueService = SpringUtils.getBean("venueServiceImpl", VenueService.class);
		MemberService memberService = SpringUtils.getBean("memberServiceImpl", MemberService.class);
		ReserveService reserveService = SpringUtils.getBean("reserveServiceImpl", ReserveService.class);
		TrainCoachService trainCoachService = SpringUtils.getBean("trainCoachServiceImpl", TrainCoachService.class);
		List<Order> list = orderService.selectByTimeOut(DateUtil.getPreTime(new Date(), 1, -5), 0);
		int flag = 0;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Order order = list.get(i);
				order.setType(3);
				flag += orderService.updateByPrimaryKeySelective(order);
				
				OrderLog orderLog = new OrderLog();
				orderLog.setId(Utils.getUUID());
				orderLog.setCreatetime(new Date());
				orderLog.setOrderid(order.getId());
				orderLog.setType(0);
				orderLog.setContent("订单支付时间结束，检查订单未支付成功，处理为支付超时");
				orderLogService.insertSelective(orderLog);
				
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

				// 预定通知消息
				JSONObject datajson = new JSONObject();
				datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
				datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
				datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
				datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"支付超时\"}"));
				
				
				// 支付超时，发给场馆
				Member venueMember = memberService.selectByPhone(venue.getContactPhone());
				if (venueMember != null) {
					String venueOpenId = venueMember.getOpenid();
					if (!StringUtil.isBank(venueOpenId)) {
						datajson.put( "remark",
								JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area + "，日期"
										+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，支付超时。\"}"));
						if (!StringUtil.isBank(venue.getTrainteam())) {
							TrainCoach trainCoach = trainCoachService.selectByMemberTeamManager(venueMember.getId(), venue.getTrainteam());
							if (trainCoach != null) {
								// 有权限查看
								logger.info(WXPayUtil.sendWXappTemplate(venueOpenId, WXConfig.wxTemplateId, "pages/user/venueMenu/lock/lock?venueId="+venue.getId() + "&title=" + venue.getName(), datajson));
							}else {
								logger.info(WXPayUtil.sendWXappTemplate(venueOpenId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
							}
						}else {
							logger.info(WXPayUtil.sendWXappTemplate(venueOpenId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
						}
					}
				}
				
				
				// 支付超时，发给用户
				String openId = member.getOpenid();
				if (!StringUtil.isBank(openId)) {
					datajson.put( "remark",
							JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area + "，日期"
									+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，支付超时。\"}"));
					logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "/pages/index/index", datajson));
				}
				
			}
			logger.info("已处理" + flag + "条超时未支付的订单");
		}
	}
}
