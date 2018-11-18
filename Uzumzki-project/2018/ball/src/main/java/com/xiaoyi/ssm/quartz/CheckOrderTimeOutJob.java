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
import com.xiaoyi.ssm.wxPay.WXPayWxappUtil;

@Service
public class CheckOrderTimeOutJob {

    private static Logger logger = Logger.getLogger(CheckOrderTimeOutJob.class.getName());

	/**
	 * @Description: 定时任务检查是否有时间是半小时前的订单
	 * @author 宋高俊
	 * @date 2018年9月20日 下午8:35:13
	 */
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void getCheckTimeOut() {
		OrderService orderService = SpringUtils.getBean("orderServiceImpl", OrderService.class);
		OrderLogService orderLogService = SpringUtils.getBean("orderLogServiceImpl", OrderLogService.class);
		VenueService venueService = SpringUtils.getBean("venueServiceImpl", VenueService.class);
		MemberService memberService = SpringUtils.getBean("memberServiceImpl", MemberService.class);
		ReserveService reserveService = SpringUtils.getBean("reserveServiceImpl", ReserveService.class);
		TrainCoachService trainCoachService = SpringUtils.getBean("trainCoachServiceImpl", TrainCoachService.class);
		List<Order> list = orderService.selectByTimeOut(DateUtil.getPreTime(new Date(), 1, -30), 5);
		int flag = 0;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Order order = list.get(i);
				WXPayWxappUtil.weiXinRefund(order.getId(), order.getPrice(), order.getPrice(), "场馆超时确认", 0);

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

				// 场馆确认超时，发给场馆
				Member venueMember = memberService.selectByPhone(venue.getContactPhone());
				String openId = venueMember.getOpenid();
				if (!StringUtil.isBank(openId)) {
					// 预定通知消息
					JSONObject datajson = new JSONObject();
					datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
					datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
					datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
					datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"超时确认\"}"));
					datajson.put( "remark",
							JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area + "，日期"
									+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，超过三十分钟未确认，自动取消。\"}"));
					if (!StringUtil.isBank(venue.getTrainteam())) {
						TrainCoach trainCoach = trainCoachService.selectByMemberTeamManager(venueMember.getId(), venue.getTrainteam());
						if (trainCoach != null) {
							// 有权限查看
							logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/user/venueMenu/lock/lock?venueId="+venue.getId(), datajson));
						}else {
							logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
						}
					}else {
						logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
					}
				}
				
				order.setType(4);
				flag += orderService.updateByPrimaryKeySelective(order);
				
				OrderLog orderLog = new OrderLog();
				orderLog.setId(Utils.getUUID());
				orderLog.setCreatetime(new Date());
				orderLog.setOrderid(order.getId());
				orderLog.setType(0);
				orderLog.setContent("订单创建三十分钟未确认，自动退款");
				orderLogService.insert(orderLog);
				
			}
			logger.info("已处理" + flag + "条超时未确认的订单");
		}
	}
}
