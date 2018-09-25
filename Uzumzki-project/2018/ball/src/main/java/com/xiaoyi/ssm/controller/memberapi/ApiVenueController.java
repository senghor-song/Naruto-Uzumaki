package com.xiaoyi.ssm.controller.memberapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dao.CombineMapper;
import com.xiaoyi.ssm.dao.OrderLogMapper;
import com.xiaoyi.ssm.dao.VenueLockMapper;
import com.xiaoyi.ssm.dao.VenueTeachMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.dto.OrderDto;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.Coach;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueLock;
import com.xiaoyi.ssm.model.VenueTeach;
import com.xiaoyi.ssm.service.CoachService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 微信订场列表接口
 * @author 宋高俊
 * @date 2018年8月16日 下午4:29:00
 */
@Controller
@RequestMapping("memberapi/venue")
public class ApiVenueController {

	@Autowired
	private FieldService fieldService;
	@Autowired
	private FieldTemplateService fieldTemplateService;
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private CoachService coachService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private VenueLockMapper venueLockMapper;
	@Autowired
	private VenueTeachMapper venueTeachMapper;
	@Autowired
	private CombineMapper combineMapper;
	@Autowired
	private VenueService venueService;

	// 获取线程池连接
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	/**  
	 * @Description: 常用场馆列表
	 * @author 宋高俊  
	 * @param pageBean
	 * @param cityid
	 * @param districtid
	 * @param areaid
	 * @return 
	 * @date 2018年9月10日 下午4:00:58 
	 */ 
	@RequestMapping(value = "/oftenVenue")
	@ResponseBody
	public ApiMessage oftenVenue(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
		
		List<Map<String, Object>> listmap = new ArrayList<>();
		List<Venue> venues = venueService.selectByOftenMember(member.getId());
		for (Venue venue : venues) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", venue.getId());// id
			map.put("image", venue.getImage());// 图片
			map.put("name", venue.getName());// 名称
			map.put("address", venue.getAddress());// 地址
			map.put("phone", venue.getTel());// 电话
			map.put("warmreminder", venue.getWarmreminder());// 温馨提示
			listmap.add(map);
		}
		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 新增场馆
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:21:28
	 */
	@RequestMapping(value = "/saveVenue")
	@ResponseBody
	public ApiMessage saveVenue(Venue venue, HttpServletRequest request){
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		return venueService.saveVenue(venue, openid);
	}

	/**
	 * @Description: 获取日期数据
	 * @author 宋高俊
	 * @date 2018年8月17日 下午2:41:12
	 */
	@RequestMapping(value = "/datelist")
	@ResponseBody
	public ApiMessage datelist() {
		// 当前日期
		Date date = new Date();
		// 获取当天的00:00:00时间
		date = DateUtil.getWeeHours(date, 0);
		List<Map<String, Object>> datelistmap = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			Map<String, Object> datemap = new HashMap<>();
			datemap.put("date", DateUtil.getFormat(date, "yyyy-MM-dd"));// 日期
			datemap.put("week", DateUtil.dateToWeek(date));// 星期
			datelistmap.add(datemap);
			date = DateUtil.getPreTime(date, 3, 1);
		}
		return ApiMessage.succeed(datelistmap);
	}

	/**
	 * @Description: 会员选择日程数据(price为-1不可预订 -2已预订-3预订中-4已过期)
	 * @author 宋高俊
	 * @date 2018年8月16日 下午7:35:14
	 */
	@RequestMapping(value = "/reserve")
	@ResponseBody
	public ApiMessage reserve(PageBean pageBean, String venueid, String datestr) {
		// 当前日期
		Date date = new Date();
		String hour = DateUtil.getFormat(date, "HH");
		String minute = DateUtil.getFormat(date, "mm");
		// 已过期的时段
		int outtime = (int) (Integer.valueOf(hour) / 0.5);
		if (Integer.valueOf(minute) < 30) {
			outtime--;
		}
		if (StringUtils.isBlank(datestr)) {
			// 获取当天的00:00:00时间
			date = DateUtil.getWeeHours(date, 0);
		} else {
			date = DateUtil.getParse(datestr, "yyyy-MM-dd");
		}

		// 获取当前场馆下的所有场地
		Field f = new Field();
		f.setVenueid(venueid);
		List<Field> fields = fieldService.selectByAll(f);

		List<Map<String, Object>> listmap = new ArrayList<>();

		// 开始封装当天日期的日程
		for (int i = 0; i < fields.size(); i++) {
			// 最终返回日期数据
			List<Map<String, Object>> datelistmap = new ArrayList<>();

			Field field = fields.get(i);

			// 根据场馆和场地ID获取场地当天使用模板
			FieldTemplateDto ftd = new FieldTemplateDto();
			ftd.setDate(date);
			ftd.setFieldid(field.getId());
			ftd.setVenueid(venueid);
			FieldTemplate ft = fieldTemplateService.selectByVenueAndField(ftd);
			// 初始模板价格数据
			String[] template = new String[48];

			int time = 0;
			if (ft != null) {
				// 获取模板时段价格初始状态
				template = ft.getVenueTemplate().getPrice().split(",");
				for (int j = 0; j < template.length; j++) {
					Map<String, Object> map = new HashMap<>();
					map.put("no", j + 1);
					if ((j + 1) % 2 == 0) {
						map.put("time", time + ":30-" + (time + 1) + ":00");
						time++;
					} else {
						map.put("time", time + ":00-" + time + ":30");
					}
					// 小于或等于过期时段的时段状态则修改为已过期
					if (j <= outtime && !"-1".equals(template[j])) {
						map.put("price", "-4");
					} else {
						map.put("price", template[j]);
					}
					map.put("flag", "");
					datelistmap.add(map);
				}
				// 获取当天所有预约的时段数据
				FieldTemplateDto fieldTemplateDto = new FieldTemplateDto();
				fieldTemplateDto.setDate(date);
				fieldTemplateDto.setFieldid(field.getId());
				fieldTemplateDto.setVenueid(venueid);
				List<Reserve> reserves = reserveService.selectByFieldTemplateDto(fieldTemplateDto);
				// 修改时段状态
				for (int j = 0; j < reserves.size(); j++) {
					Reserve reserve = reserves.get(j);
					// 将已预约成功的时段改为已预约状态
					String[] timestr = reserves.get(j).getReservetimeframe().split(",");
					// 当前时段状态
					String priceType = "";
					if (reserve.getOrder().getType() == 1 || reserve.getOrder().getType() == 6) {
						// 已消费和支付待消费都是已预约状态
						priceType = "-2";
					} else if (reserve.getOrder().getType() == 0) {
						// 待支付是预约中状态
						priceType = "-3";
					}

					// 修改时段内的状态
					for (int k = 0; k < timestr.length; k++) {
						int flag = Integer.valueOf(timestr[k]) - 1;
						Map<String, Object> timemap = datelistmap.get(flag);
						timemap.put("price", priceType);
						timemap.put("flag", "user" + j);
						datelistmap.set(flag, timemap);
					}
				}

				// 获取当天锁定占用时段=-5
				List<VenueLock> venueLocks = venueLockMapper.selectByNowDate(fieldTemplateDto);
				// 修改时段状态
				for (int j = 0; j < venueLocks.size(); j++) {
					VenueLock venueLock = venueLocks.get(j);
					// 已有状态的时间段
					String[] timestr = venueLock.getVenuetimeframe().split(",");

					// 修改时段内的状态
					for (int k = 0; k < timestr.length; k++) {
						int flag = Integer.valueOf(timestr[k]) - 1;
						Map<String, Object> timemap = datelistmap.get(flag);
						timemap.put("price", "-5");
						timemap.put("flag", "user" + j + "-5");
						datelistmap.set(flag, timemap);
					}
				}

				// 获取当天教学占用时段=-6
				List<VenueTeach> venueTeachs = venueTeachMapper.selectByNowDate(fieldTemplateDto);
				// 修改时段状态
				for (int j = 0; j < venueTeachs.size(); j++) {
					VenueTeach venueTeach = venueTeachs.get(j);
					// 已有状态的时间段
					String[] timestr = venueTeach.getVenuetimeframe().split(",");

					// 修改时段内的状态
					for (int k = 0; k < timestr.length; k++) {
						int flag = Integer.valueOf(timestr[k]) - 1;
						Map<String, Object> timemap = datelistmap.get(flag);
						timemap.put("price", "-6");
						timemap.put("flag", "user" + j + "-6");
						datelistmap.set(flag, timemap);
					}
				}

				// 获取当天拼场占用时段=-7
				List<Combine> combines = combineMapper.selectByNowDate(fieldTemplateDto);
				for (int j = 0; j < combines.size(); j++) {
					Combine combine = combines.get(j);
					// 已有状态的时间段
					String[] timestr = combine.getCombinetimeframe().split(",");

					// 修改时段内的状态
					for (int k = 0; k < timestr.length; k++) {
						int flag = Integer.valueOf(timestr[k]) - 1;
						Map<String, Object> timemap = datelistmap.get(flag);
						timemap.put("price", "-7");
						timemap.put("flag", "user" + j + "-7");
						datelistmap.set(flag, timemap);
					}
				}

			}
			Map<String, Object> map = new HashMap<>();
			map.put("id", field.getId());
			map.put("name", field.getName());
			map.put("datelist", datelistmap);
			listmap.add(map);
		}

		return new ApiMessage(200, "查询成功", listmap);
	}

	/**
	 * @Description: 获取服务人员数据
	 * @author 宋高俊
	 * @date 2018年8月17日 下午4:59:32
	 */
	@RequestMapping(value = "/selectCoach")
	@ResponseBody
	public ApiMessage selectCoach(String date, String timestr, String venueid) {
		if (StringUtil.isBank(date, timestr, venueid)) {
			return new ApiMessage(400, "参数不完整");
		}

		List<String> datalist = new ArrayList<>();// 保存多个订场时间段详情数据

		double timesum = 0;// 小时数
		double price = 0;// 价格总数

		// 解析提交的时间段选择数据
		JSONArray jsonArray = JSONArray.fromObject(timestr);
		for (int i = 0; i < jsonArray.size(); i++) {
			// 预约时间段详情
			String datastr = "";
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			// 场地数据
			Field field = fieldService.selectByPrimaryKey(jsonObject.get("fieldid").toString());
			// 获取模板数据
			FieldTemplateDto ftd = new FieldTemplateDto();
			// 当前日期
			ftd.setDate(DateUtil.getParse(date, "yyyy-MM-dd"));
			ftd.setFieldid(field.getId());
			ftd.setVenueid(venueid);
			FieldTemplate ft = fieldTemplateService.selectByVenueAndField(ftd);
			if (ft == null) {
				return new ApiMessage(400, field.getName() + date + "未开放预约");
			}
			// 模板价格数据
			String[] template = ft.getVenueTemplate().getPrice().split(",");

			// 获取当前场地被预约的时间段
			String timeno = (String) jsonObject.get("datestr");
			String[] timesums = timeno.split(",");
			int[] timetoint = new int[timesums.length];
			for (int j = 0; j < timesums.length; j++) {
				timetoint[j] = Integer.valueOf(timesums[j]);
			}
			List<String> arrays = StringUtil.getContinuousNumber(timetoint);
			for (int k = 0; k < arrays.size(); k++) {
				String[] time = arrays.get(k).split(",");
				for (int j = 0; j < time.length; j++) {
					// 计算总价格
					int timeflag = Integer.valueOf(time[j]) - 1;
					price = Arith.add(price, Double.valueOf(template[timeflag]));
				}
				// 生成预约时段数据
				datastr = field.getName() + "(" + date + " " + StringUtil.timeToTimestr(time) + ")";
				datalist.add(datastr);
				// 计算总小时数
				timesum += time.length * 0.5;
			}

		}

		Coach c = new Coach();
		c.setVenueid(venueid);
		List<Coach> list = coachService.selectByAll(c);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Coach coach = list.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("id", coach.getId()); // ID
			map.put("name", coach.getName()); // 姓名
			map.put("price", coach.getPrice() * timesum); // 价格
			map.put("image", coach.getImage()); // 图片
			map.put("introduce", coach.getIntroduce()); // 图片
			listmap.add(map);
		}
		Map<String, Object> returnmap = new HashMap<>();
		returnmap.put("datalist", datalist); // 订场详情数据
		returnmap.put("time", timesum); // 小时数
		returnmap.put("price", price); // 价格
		returnmap.put("coachlist", listmap); // 教练数据

		returnmap.put("date", date); // 回传数据
		returnmap.put("timestr", timestr); // 回传数据
		returnmap.put("venueid", venueid); // 回传数据
		return ApiMessage.succeed(returnmap);
	}

	/**
	 * @Description: 保存订单接口
	 * @author 宋高俊
	 * @date 2018年8月21日 下午3:51:35
	 */
	@RequestMapping(value = "/saveorder")
	@ResponseBody
	public ApiMessage saveorder(String date, String timestr, String venueid, String coachid, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");

		if (StringUtil.isBank(date, timestr, venueid)) {
			return new ApiMessage(400, "参数不完整");
		}
		// 订单开始时间
		Date datetime = new Date();
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
		if (member == null) {
			return new ApiMessage(400, "请登录后操作");
		}
		// 订单数据
		Order order = new Order();
		// 订单ID
		final String orderid = Utils.getUUID();
		order.setId(orderid);
		order.setCreatetime(datetime);
		order.setModifytime(datetime);
		order.setVenueid(venueid);
		order.setOrderdate(DateUtil.getParse(date, "yyyy-MM-dd"));
		order.setMemberid(member.getId());
		order.setType(0);

		List<String> datalist = new ArrayList<>();// 保存多个订场时间段详情数据

		double timesum = 0;// 小时数
		double price = 0;// 价格总数

		// 解析提交的时间段选择数据
		JSONArray jsonArray = JSONArray.fromObject(timestr);
		for (int i = 0; i < jsonArray.size(); i++) {
			// 预约时间段详情
			String datastr = "";
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			// 场地数据
			Field field = fieldService.selectByPrimaryKey(jsonObject.get("fieldid").toString());
			// 获取模板数据
			FieldTemplateDto ftd = new FieldTemplateDto();
			// 当前日期
			ftd.setDate(DateUtil.getParse(date, "yyyy-MM-dd"));
			ftd.setFieldid(field.getId());
			ftd.setVenueid(venueid);
			FieldTemplate ft = fieldTemplateService.selectByVenueAndField(ftd);
			if (ft == null) {
				return new ApiMessage(400, field.getName() + date + "未开放预约");
			}
			// 模板价格数据
			String[] template = ft.getVenueTemplate().getPrice().split(",");

			// 获取当前场地被预约的时间段
			String timeno = (String) jsonObject.get("datestr");
			String[] timesums = timeno.split(",");
			int[] timetoint = new int[timesums.length];
			for (int j = 0; j < timesums.length; j++) {
				timetoint[j] = Integer.valueOf(timesums[j]);
			}
			// 订单查询条件
			OrderDto orderDto = new OrderDto();
			orderDto.setDate(DateUtil.getParse(date, "yyyy-MM-dd"));
			orderDto.setFieldid(field.getId());
			orderDto.setVenueid(venueid);
			List<String> arrays = StringUtil.getContinuousNumber(timetoint);
			for (int k = 0; k < arrays.size(); k++) {
				String[] time = arrays.get(k).split(",");
				Double reserveamount = 0.0;
				for (int j = 0; j < time.length; j++) {
					orderDto.setTime(time[j]);
					Integer flag = orderService.selectIsOrder(orderDto);
					if (flag > 0) {
						return new ApiMessage(400, "抱歉," + StringUtil.timeToTimestr(time[j]) + "该时间段已被预约");
					}
					// 计算总价格
					int timeflag = Integer.valueOf(time[j]) - 1;
					reserveamount = Arith.add(reserveamount, Double.valueOf(template[timeflag]));
				}
				// 生成预约时段数据
				datastr = field.getName() + "(" + date + " " + StringUtil.timeToTimestr(time) + ")";
				datalist.add(datastr);

				Reserve reserve = new Reserve();
				reserve.setId(Utils.getUUID());
				reserve.setCreatetime(datetime);
				reserve.setFieldid(field.getId());
				reserve.setOrderid(orderid);
				reserve.setReservetimeframe(arrays.get(k));
				reserve.setReserveamount(reserveamount);
				reserveService.insertSelective(reserve);
				// 计算总价格
				price = Arith.add(price, reserveamount);
				// 计算总小时数
				timesum += time.length * 0.5;
			}
		}
		if (!StringUtil.isBank(coachid)) {
			Coach coach = coachService.selectByPrimaryKey(coachid);
			if (coach != null) {
				order.setCoachid(coach.getId());
				order.setCoachamount(coach.getPrice() * timesum);
				price = Arith.add(price, coach.getPrice() * timesum);
			}
		}
		order.setPrice(price);
		order.setTimesum(timesum);
		// 保存订单数据
		orderService.insertSelective(order);
		// 记录订单日志
		OrderLog orderLog = new OrderLog();
		orderLog.setId(Utils.getUUID());
		orderLog.setCreatetime(datetime);
		orderLog.setOrderid(orderid);
		orderLog.setType(0);
		orderLog.setContent("创建订单,等待支付");
		orderLogMapper.insert(orderLog);

		// 开启线程处理延时任务
		new Timer(order.getId() + "订单支付超时，已关闭").schedule(new TimerTask() {
			@Override
			public void run() {
				Order order = orderService.selectByPrimaryKey(orderid);
				OrderLog orderLog = new OrderLog();
				orderLog.setOrderid(orderid);
				orderLog.setType(0);
				if (order.getType() == 1 || order.getType() == 4) {
					// 记录日志
					orderLog.setId(Utils.getUUID());
					orderLog.setCreatetime(new Date());
					orderLog.setContent("订单支付时间结束，检查订单已支付成功");
					orderLogMapper.insertSelective(orderLog);
				} else {
					Order neworder = new Order();
					neworder.setId(orderid);
					neworder.setModifytime(new Date());
					neworder.setType(3);
					orderService.updateByPrimaryKeySelective(neworder);
					// 记录日志
					orderLog.setId(Utils.getUUID());
					orderLog.setCreatetime(new Date());
					orderLog.setContent("订单支付时间结束，检查订单未支付成功，处理为支付超时");
					orderLogMapper.insertSelective(orderLog);
				}
			}
		}, 300000);
//		taskExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				//延迟任务
//				System.out.println("开始计时，5分钟后处理超时订单");
//				new Timer(order.getId() + "订单支付超时，已关闭").schedule(new TimerTask() {
//		            @Override
//		            public void run() {
//		            	Order order = orderService.selectByPrimaryKey(orderid);
//		                if (order.getType() == 1 || order.getType() == 4) {
//							System.out.println("支付已支付");
//						}else {
//							Order neworder = new Order();
//							neworder.setId(orderid);
//							neworder.setType(3);
//							orderService.updateByPrimaryKeySelective(neworder);
//							System.out.println("支付超时");
//						}
//		            }
//		        }, 10000);
//			}
//		});
		return ApiMessage.succeed(orderid);
	}

}
