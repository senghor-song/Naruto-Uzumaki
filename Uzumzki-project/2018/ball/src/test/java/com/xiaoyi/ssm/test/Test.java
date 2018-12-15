package com.xiaoyi.ssm.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.WXFundflow;
import com.xiaoyi.ssm.service.WXFundflowService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;
import com.xiaoyi.ssm.wxPay.XMLUtil;

class MyRunnable implements Runnable{
    
    @Override
    public void run() {
    	for (int i = 0; i < 5; i++) {
    		System.out.println("子线程ID："+Thread.currentThread().getId());
    		System.out.println(Utils.getUUID());
		}
    }
}
public class Test {

	private static Logger logger = Logger.getLogger(Test.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static void createMenu(Map map) {
//		System.out.println("map >>> "+ menus);
		Iterator entries = map.entrySet().iterator(); 
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			if ("menu".equals(key)) {
				List<Map> value = (List<Map>) entry.getValue();
				for (Map sonMap : value) {
			        createMenu(sonMap);
				}
				System.out.println("Key = " + key + ", Value = " + value);
			} else {
				Object value = entry.getValue();
				System.out.println("Key = " + key + ", Value = " + value);
			}
			
		}
	}

	public static void main(String[] args) throws Exception {

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, "omKuewSD7muFIojIEzqwusyK9ZMk");
		System.out.println(member.getSessionKey());
//    	Integer count = 0;
//    	System.out.println(count++);
//		File xmlFile = ResourceUtils.getFile("classpath:menus.xml");
//        //xml转map
//        SAXReader saxReader = new SAXReader();
////        Document document = saxReader.read(new ByteArrayInputStream(respXml.getBytes()));
//        Document document = saxReader.read(xmlFile);
//        Element incomingForm = document.getRootElement();
//        Map menus =  ParseXMLUtils.Dom2Map(incomingForm, false);
//        
////        List<Map> menuList = (List<Map>) menus.get("menu");
////        for (Map menu : menuList) {
////			Permission permission = new Permission();
////			permission.setId(menu.getOrDefault("id", "id").toString());
////		}
//        
//        
//        Iterator entries = menus.entrySet().iterator(); 
//		while (entries.hasNext()) {
//			Map.Entry entry = (Map.Entry) entries.next();
//			String key = (String) entry.getKey();
//			List<Map> value = (List<Map>) entry.getValue();
//			for (Map map : value) {
//		        createMenu(map);
//			}
//			
//			System.out.println("Key = " + key + ", Value = " + value);
//		}
		
		
//		// 使用百度地图根据经纬度获取地址信息
//		String jsonString = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=22.61804,114.04031&output=json&pois=0&ak=" + Global.Baidu_Map_KRY, null);
//		if (!StringUtil.isBank(jsonString)) {
//			try {
//				jsonString = jsonString.replace("renderReverse&&renderReverse(", "");
//				jsonString = jsonString.substring(0, jsonString.length() - 1);
//				JSONObject jsonObject = JSONObject.fromObject(jsonString);
//				System.out.println(jsonString);
//				System.out.println(jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("city"));
//				System.out.println(jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("district"));
//				System.out.println(jsonObject.getJSONObject("result").getString("formatted_address"));
//				
//			} catch (Exception e) {
//
//			}
//		}
		
//		System.out.println(Arith.mul(Arith.sub(amountCollect, 0), 0.01));
//		System.out.println(Arith.round(Arith.sub(amountCollect, Arith.mul(Arith.sub(amountCollect, 0), 0.01)), 2));
		
//		System.out.println("===========操作系统是:" + System.getProperties().getProperty("os.name"));
//		System.out.println("===========文件的分隔符为file.separator:" + System.getProperties().getProperty("file.separator"));
		
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
		
//		String string = "/admin/inviteBall/listview";
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
