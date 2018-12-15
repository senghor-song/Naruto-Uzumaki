package com.xiaoyi.ssm.init;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSONObject;
import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.google.gson.Gson;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.VenueEnterService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.PropertiesUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

/**
 * @Description:
 * @author 宋高俊
 * @date 2018年11月6日 下午2:27:11
 */
public class SmsInit implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = Logger.getLogger(SmsInit.class);

	static class MyMessageListener implements MessageListener {
		private Gson gson = new Gson();

		@Override
		public boolean dealMessage(Message message) {
			LOGGER.info("接收到回复消息 ------------------>");

//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			// 消息的几个关键值
//			System.out.println("message receiver time from mns:" + format.format(new Date()));
//			System.out.println("message handle: " + message.getReceiptHandle());
//			System.out.println("message body: " + message.getMessageBodyAsString());
//			System.out.println("message id: " + message.getMessageId());
//			System.out.println("message dequeue count:" + message.getDequeueCount());
//			System.out.println("Thread:" + Thread.currentThread().getName());
			VenueEnterService venueEnterService = SpringUtils.getBean("venueEnterServiceImpl",VenueEnterService.class);
			OrderService orderService = SpringUtils.getBean("orderServiceImpl", OrderService.class);
			MemberService memberService = SpringUtils.getBean("memberServiceImpl", MemberService.class);
			ReserveService reserveService = SpringUtils.getBean("reserveServiceImpl", ReserveService.class);
			VenueService venueService = SpringUtils.getBean("venueServiceImpl", VenueService.class);
			TrainCoachService trainCoachService = SpringUtils.getBean("trainCoachServiceImpl", TrainCoachService.class);
			try {
				Map<String, Object> contentMap = gson.fromJson(message.getMessageBodyAsString(), HashMap.class);
				LOGGER.info(contentMap.toString());
				// phone_number String 短信接收号码 13000000000
				// content String 短信内容 true
				// sign_name String 短信签名 【阿里云】
				// send_time String 时间 20150101120000
				// dest_code String 扩展码 123456
				// sequence_id Double 消息序列ID 123456
				String redisKey = contentMap.get("phone_number") + "_" + contentMap.get("content");

				String orderid = RedisUtil.getRedis(redisKey);
				if (StringUtil.isBank(orderid)) {
					return true;
				}
				
				Order oldOrder = new Order();
				oldOrder.setId(orderid);
				oldOrder.setType(6);
				orderService.updateByPrimaryKeySelective(oldOrder);
				RedisUtil.delRedis(redisKey);

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
				try {

					Member venueMember = memberService.selectByPhone(venue.getContactPhone());
					// 场馆方：发送订单确认成功数据
					String openId = venueMember.getOpenid();
					if (!StringUtil.isBank(openId)) {
						// 预定通知消息
						JSONObject datajson = new JSONObject();
						datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
						datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
						datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
						datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"预定成功\"}"));
						datajson.put( "remark",
								JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area + "，日期"
										+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，预定成功。\"}"));
						if (!StringUtil.isBank(venue.getTrainteam())) {
							TrainCoach trainCoach = trainCoachService.selectByMemberTeamManager(venueMember.getId(), venue.getTrainteam());
							if (trainCoach != null) {
								// 有权限查看
								LOGGER.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/user/venueMenu/lock/lock?venueId="+venue.getId() + "&title=" + venue.getName(), datajson));
							}else {
								LOGGER.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
							}
						}else {
							LOGGER.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "pages/temp/venueEnter/enter", datajson));
						}
					}
					
					
					// 支付超时，发给用户
					String openId2 = member.getOpenid();
					if (!StringUtil.isBank(openId2)) {
						// 预定通知消息
						JSONObject datajson = new JSONObject();
						datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
						datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
						datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
						datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"场地已确认\"}"));
						datajson.put( "remark",
								JSONObject.parseObject("{\"value\":\"您预约的" + venue.getName() + area + "日期" + DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "时段" + time
										+ "已获场馆(" + venue.getOwner() + "教练" + venue.getContactPhone() + ")确认，请通知小伙伴准时前往。\"}"));
						LOGGER.info(WXPayUtil.sendWXappTemplate(openId2, WXConfig.wxTemplateId, "/pages/index/index", datajson));
					}
					
					LOGGER.info(orderid+"短信发送成功 ------------------>");
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info(orderid+"短信发送失败 ------------------>");
				}
				LOGGER.info(orderid+"订单处理成功 ------------------>");
				
				
				// 回复短信则代表愿意入驻
//				VenueEnter venueEnter = (VenueEnter) RedisUtil.getRedisOne(Global.REDIS_VENUE, contentMap.get("phone_number"));
//				if (venueEnter != null) {
//					try {
//						if (venueEnterService.insertSelective(venueEnter) > 0) {
//							LOGGER.info("入驻处理成功 ------------------>");
//							RedisUtil.delRedis(Global.REDIS_VENUE, (String)contentMap.get("phone_number"));
//						}
//					} catch (Exception e) {
//						LOGGER.info("数据已存在 ------------------>");
//						return true;
//					}
//				}
			} catch (com.google.gson.JsonSyntaxException e) {
				LOGGER.error("处理失败:" + message.getMessageBodyAsString(), e);
				// 理论上不会出现格式错误的情况，所以遇见格式错误的消息，只能先delete,否则重新推送也会一直报错
				return true;
			} catch (Throwable e) {
				e.printStackTrace();
				LOGGER.info("处理失败 ------------------>");
				// 您自己的代码部分导致的异常，应该return false,这样消息不会被delete掉，而会根据策略进行重推
				return false;
			}
			// 消息处理成功，返回true, SDK将调用MNS的delete方法将消息从队列中删除掉
			return true;
		}

	}

	class ServiceThread extends Thread{  
	    //2):在A类中覆盖Thread类中的run方法.  
	    public void run() {  
	    	try {
				
				DefaultAlicomMessagePuller puller = new DefaultAlicomMessagePuller();

				// 设置异步线程池大小及任务队列的大小，还有无数据线程休眠时间
//				puller.setConsumeMinThreadSize(6);
//				puller.setConsumeMaxThreadSize(16);
//				puller.setThreadQueueSize(200);
//				puller.setPullMsgThreadSize(1);
				// 和服务端联调问题时开启,平时无需开启，消耗性能
				puller.openDebugLog(false);

				// TODO 此处需要替换成开发者自己的AK信息
				String accessKeyId = Global.aliyunSMSAccessKeyId;
				String accessKeySecret = Global.aliyunSMSAccessKeySecret;

				/*
				 * TODO 将messageType和queueName替换成您需要的消息类型名称和对应的队列名称云通信产品下所有的回执消息类型:
				 * 1:短信回执：SmsReport，2:短息上行：SmsUp3:语音呼叫：VoiceReport4:流量直冲：FlowReport
				 */
				String messageType = "SmsUp";// 短信回执：SmsReport，短信上行：SmsUp
				String queueName = "Alicom-Queue-1517103659658839-SmsUp";// 在云通信页面开通相应业务消息后，就能在页面上获得对应的queueName,格式类似Alicom-Queue-xxxxxx-SmsReport
				LOGGER.info("初始开启短信服务回调开始，初始化参数 ------------------>");
				puller.startReceiveMsg(accessKeyId, accessKeySecret, messageType, queueName, new MyMessageListener());
				LOGGER.info("初始开启短信服务回调开始，启动成功 ------------------>");
			} catch (Exception e) {
				LOGGER.info("初始开启短信服务回调时，出现错误：" + e);
			}
	    }  
	}  
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		LOGGER.info("初始开启短信服务回调开始 ------------------>");
		if (System.getProperties().getProperty("os.name").indexOf("Windows") != -1) {
			PropertiesUtil.updatePro(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "jdbc.properties", "sqlLogger", "true");
		} else {
			PropertiesUtil.updatePro(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "jdbc.properties", "sqlLogger", "false");
		}
		
		ServiceThread serviceThread = new ServiceThread();
		serviceThread.start();
		// 顺便保存项目启动时间
		Global.DATE_STRING = DateUtil.getFormat(new Date());
		LOGGER.info("初始开启短信服务回调结束 ------------------>");
	}
	
}
