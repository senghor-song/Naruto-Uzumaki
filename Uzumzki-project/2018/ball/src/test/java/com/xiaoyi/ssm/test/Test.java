package com.xiaoyi.ssm.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

import cn.hutool.http.HttpUtil;

public class Test {

	private static Logger logger = Logger.getLogger(Test.class);

	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(Arith.round(10/100.0,2));
		
//		// 预定通知消息
//		JSONObject datajson = new JSONObject();
//		datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
//		datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"100001\"}"));
//		datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
//		datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"超时确认\"}"));
//		datajson.put( "remark",
//				JSONObject.parseObject("{\"value\":\"球友我小时候可帅了(手机15207108156)申请预约球场东边球场，日期 2018-11-16，时段 12:00-13:00用场，超过三十分钟未确认，自动取消。\"}"));
//		logger.info(WXPayUtil.sendWXappTemplate("oozuywqi1Ivg1SljKJ7LussZ6Zq8", WXConfig.wxTemplateId, "/pages/index/index", datajson));
		
//		System.out.println((int) MapUtils.getDistance(114, 22, 114.3, 22.3));
//		Date date1 = DateUtil.parse("2018-06-25");
//		Date date2 = DateUtil.parse("2018-11-15");
//		System.out.println(DateUtil.betweenDay(date1, date2, true));
//		System.out.println(137/7);
		
//		String string = "/WebBackAPI/admin/inviteBall/listview";
//		String[] strings = string.split("/");
//		System.err.println();
//		System.out.println("最终兑换"+maxBox(10)+ "瓶矿泉水");
//		WXUtil.weiXinRefund("85f730b12aee4bb5af5b338d74ab4eba", 0.5, 0.5, "", 1);

//		double a = 0.00055123456;
//
//		BigDecimal b = BigDecimal.valueOf(a);
//		System.out.println(b);
		
//		InviteBall inviteBall = new InviteBall();
//		inviteBall.setReceiveAmount(new BigDecimal(0.3));
//		System.out.println(inviteBall.getReceiveAmount().doubleValue());
		
//		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, "omKueweB2JSy1iBWKSyqi8XO7EHw");
//		System.out.println(member.getSessionKey());
//
//		String result = AES.decrypt("tCqZoa7GqNv19NE954uN8v7Dc3HZfNAFyNtLnRbtw6XFojmQaa6crBzhiqR3aK/lR3K0r3yS8dq1FiUiy2cWD01wO+6fHzk8Szu9Fh5n7sTnytjA7ymAvG9dnAGQk+5OE411ZsC9aSEeuCAltFDkEbmBwW03peprPRES6fhKeblNGSax0p5xufM+8D6hZdEqpbg3BizAehyHXLSInlaz0Q==", 
//				member.getSessionKey(), "cGd6KrMXiTh5AEUFMG2THA==", "UTF-8");
//		if (null != result && result.length() > 0) {
//			JSONObject json = JSONObject.fromObject(result);
//			System.out.println(json);
//		}
//		
		
		
		
		
//		String fileName = "image/png";
//		fileName = fileName.replace("/", ".");
//		
//		fileName = Utils.getUUID() + fileName.substring(fileName.lastIndexOf("."));
//		System.out.println(fileName);
//		List<Person> list = new ArrayList<>();
//		list.add(new Person("jack", 20));
//		list.add(new Person("mike", 25));
//		list.add(new Person("tom", 30));
//		List<String> newlist =  list.stream().map(Person::getName).collect(null);
//		for (int i = 0; i < newlist.size(); i++) {
//			System.out.println(newlist.get(i));
//		}

//		// 创建一个数值格式化对象
//		NumberFormat numberFormat = NumberFormat.getInstance();
//		// 设置精确到小数点后2位
//		numberFormat.setMaximumFractionDigits(0);
////		String result = numberFormat.format((float) 5 / (float) (20) * 100);
//		double result = ((float) 5 / (float) (9) * 100);
//		if (result <= 33) {
//			System.out.println(result);
//			System.out.println("低层");
//		}else if (33 < result && result <= 66) {
//			System.out.println(result);
//			System.out.println("中层");
//		}else if (result > 66) {
//			System.out.println(result);
//			System.out.println("高层");
//		}
//		new Timer("timer - " + 0).schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + " run ");
//            }
//        }, 1000);
//		
//		for (int i = 0; i < 10; ++i) {
//            new Timer("timer - " + i).schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " run ");
//                }
//            }, 5000);
//        }
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(10);
//		executor.setMaxPoolSize(15);
//		executor.setKeepAliveSeconds(1);
//		executor.setQueueCapacity(5);
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//		executor.initialize();
//		executor.execute(new Runnable() {
//			@Override
//			public void run() {
//				//执行的代码
//				System.out.println(1);
//			}
//			
//		});
//		executor.execute(new Runnable() {
//			@Override
//			public void run() {
//				//执行的代码
//				new Timer("订单支付超时，已关闭").schedule(new TimerTask() {
//		            @Override
//		            public void run() {
//		                System.out.println(2);
//		            }
//		        }, 5000);
//			}
//		});
//		int sum = (new BigDecimal(Double.toString(10.03)).multiply(new BigDecimal("100"))).intValue();
//		System.out.println(sum);

//		String data = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid=wxa76f9c07c99468b2&secret=3caf90ffa0f40292524eba00588f6e0d");
//		JSONObject jsonObject = JSONObject.fromObject(data);
//		System.out.println(jsonObject.get("access_token"));
//		@SuppressWarnings("unchecked")
//		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, "wxa76f9c07c99468b2"); 
//		System.out.println(map.get("access_token"));
//		try {
////			long from = DateUtil.getParse("2016-05-01 13:59:58").getTime();
////			long to = DateUtil.getParse("2016-05-01 14:00:00").getTime();
////			DecimalFormat df = new DecimalFormat("0.000");
////			Number d = df.parse(df.format((float) (to - from) / (1000 * 60 * 60)));
////			System.out.println(d.doubleValue());
////			
//			
//			double a = 1.3; 
//			double b = 2.1; 
//			BigDecimal bca = new BigDecimal(a);
//			BigDecimal bcb = new BigDecimal(b);
//			System.out.println(bca.add(bcb).doubleValue());
//			BigDecimal b1 = new BigDecimal(Double.toString(a));  
//	        BigDecimal b2 = new BigDecimal(Double.toString(b));  
//			System.out.println(Arith.add(a, b));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
//		System.out.println(Utils.countFee(5,1));
	}
	

	static int maxBox(int beerNum) {
		int allBox = beerNum; // 总兑换数量
		int allGai = beerNum; // 盖子数量
		int allBeer = beerNum; // 瓶子数量
		int i = 1;
		while (allBox >= 2 || allGai >= 4) {
			System.out.print("第" + i + "次,已兑换矿泉水" + allBeer + "瓶,上次已兑换"+allBox+"瓶矿泉水,");
			int x = allBox % 2;// 剩余瓶子
			int y = (allBox - x) / 2;// 可兑换数量
			System.out.print("瓶子剩余" + allBox + "个,可兑换"+y+"瓶矿泉水,");
			int a = allGai % 4;// 剩余盖子
			int b = (allGai - a) / 4;// 可兑换数量
			System.out.print("盖子剩余" + allGai + "个,可兑换"+b+"瓶矿泉水,");
			allBox = x + y + b; // 剩余瓶子加上瓶子兑换的矿泉水数再加上盖子兑换的矿泉水数等于最终剩余瓶子
			allGai = a + b + y; // 剩余盖子加上盖子兑换的矿泉水数再加上瓶子兑换的矿泉水数等于最终剩余盖子
			allBeer += y + b; // 本次兑换的总矿泉水数
			System.out.println("共可兑换" + allBox + "瓶矿泉水");
			i++;
		}
		return allBeer;
	}

}
