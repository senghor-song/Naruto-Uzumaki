package com.xiaoyi.ssm.controller.venue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.util.ObjectUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.dao.AmountRefundWayMapper;
import com.xiaoyi.ssm.dao.MemberHabitMapper;
import com.xiaoyi.ssm.dao.OrderLogMapper;
import com.xiaoyi.ssm.dao.VenueLockMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.dto.OrderDto;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.MemberHabit;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueCoach;
import com.xiaoyi.ssm.model.VenueError;
import com.xiaoyi.ssm.model.VenueLock;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.model.VenueStatis;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OrderService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainTeamCoachService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.VenueCoachService;
import com.xiaoyi.ssm.service.VenueErrorService;
import com.xiaoyi.ssm.service.VenueLogService;
import com.xiaoyi.ssm.service.VenueRefundService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.Arith;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieVoiceUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXConfig;
import com.xiaoyi.ssm.wxPay.WXPayUtil;

/**  
 * @Description: 后台场馆控制器
 * @author 宋高俊  
 * @date 2018年10月16日 上午11:47:02 
 */ 
@Controller("wxappApiVenueController")
@RequestMapping("venue/manager")
public class ApiVenueController {

    private static Logger logger = Logger.getLogger(ApiVenueController.class.getName());
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private FieldTemplateService fieldTemplateService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private VenueLockMapper venueLockMapper;
	@Autowired
	private VenueCoachService coachService;
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private MemberHabitMapper memberHabitMapper;
	@Autowired
	private VenueErrorService venueErrorService;
	@Autowired
	private VenueLogService venueLogService;
	@Autowired
	private TrainTeamService trainTeamService;
	@Autowired
	private VenueCoachService venueCoachService;
	@Autowired
	private AmountRefundWayMapper amountRefundWayMapper;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private TrainTeamCoachService trainTeamCoachService;
	@Autowired
	private VenueRefundService venueRefundService;
	@Autowired
	private VenueTemplateService venueTemplateService;

	// 获取线程池连接
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	/**  
	 * @Description: 根据经纬度获取场馆
	 * @author 宋高俊  
	 * @param request
	 * @param begLng 开始经度
	 * @param endLng 结束经度
	 * @param begLat 开始纬度
	 * @param endLat 结束纬度
	 * @return 
	 * @date 2018年10月18日 下午6:00:40 
	 */ 
	@RequestMapping(value = "/venue/getMapVenue")
	@ResponseBody
	public ApiMessage getMapVenue(HttpServletRequest request, double begLng, double endLng, double begLat, double endLat, Integer ballType) {

		List<Venue> venueList = venueService.selectByNearbyMapVenue(begLng, endLng, begLat, endLat, ballType);
		List<Map<String, Object>> venueListMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < venueList.size(); i++) {
			Venue venue = venueList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venue.getId()); // ID
			map.put("title", venue.getName()); // 场馆名称
			map.put("lng", venue.getLongitude()); // 经度
			map.put("lat", venue.getLatitude()); // 纬度
			map.put("type", venue.getType()); // 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
			venueListMap.add(map);
		}

		List<TrainTeam> teamList = trainTeamService.selectByNearbyMapTrainTeamType(begLng, endLng, begLat, endLat, ballType);
		List<Map<String, Object>> teamListMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < teamList.size(); i++) {
			TrainTeam trainTeam = teamList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trainTeam.getId()); // ID
			map.put("title", trainTeam.getTitle()); // 场馆名称
			map.put("lng", trainTeam.getLongitude()); // 经度
			map.put("lat", trainTeam.getLatitude()); // 纬度
			map.put("type", trainTeam.getTeachClass()); // 开设科目逗号隔开(网球,足球,羽毛球)
			teamListMap.add(map);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("venueListMap", venueListMap);
		map.put("teamListMap", teamListMap);
		return new ApiMessage(200, "获取成功", map);
	}
	
	
	/**
	 * @Description: 获取日期数据
	 * @author 宋高俊
	 * @date 2018年8月17日 下午2:41:12
	 */
	@RequestMapping(value = "/datelist")
	@ResponseBody
	public ApiMessage datelist(String id) {
		// 当前日期
		Date date = new Date();
		// 获取当天的00:00:00时间
		date = DateUtil.getWeeHours(date, 0);

		List<Map<String, Object>> datelistmap = new ArrayList<>();
		if (StringUtil.isBank(id)) {
			for (int i = 0; i < 7; i++) {
				Map<String, Object> datemap = new HashMap<>();
				datemap.put("date", DateUtil.getFormat(date, "yyyy-MM-dd"));// 日期
				datemap.put("week", DateUtil.dateToWeek(date));// 星期
				datelistmap.add(datemap);
				date = DateUtil.getPreTime(date, 3, 1);
			}
		}else {
			// 获取培训机构管理的场馆
			Venue venue = venueService.selectByPrimaryKey(id);
			
			for (int i = 0; i < venue.getMaxDay(); i++) {
				Map<String, Object> datemap = new HashMap<>();
				datemap.put("date", DateUtil.getFormat(date, "yyyy-MM-dd"));// 日期
				datemap.put("week", DateUtil.dateToWeek(date));// 星期
				datelistmap.add(datemap);
				date = DateUtil.getPreTime(date, 3, 1);
			}
		}
		
		return ApiMessage.succeed(datelistmap);
	}
	
	/**  
	 * @Description: 场馆列表
	 * @author 宋高俊  
	 * @param id 培训机构ID
	 * @param request
	 * @return 
	 * @date 2018年10月16日 上午11:49:05 
	 */ 
	@RequestMapping(value = "/venueList")
	@ResponseBody
	public ApiMessage venueList(String id, HttpServletRequest request) {

		// 获取培训机构管理的场馆
		List<Venue> list = venueService.selectByManager(id);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (Venue venue : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", venue.getId());// id
			map.put("image", venue.getImage());// 图片
			map.put("name", venue.getName());// 名称
			map.put("address", venue.getAddress());// 地址
			map.put("type", venue.getType());// 球场类型
			map.put("venueCoachSum", venueCoachService.countByVenue(venue.getId()));// 陪练数量
			map.put("venueRefundSum", venueRefundService.countByVenue(venue.getId()));// 统计申请数量
			listmap.add(map);
		}
		
		return new ApiMessage(200, "获取成功", listmap);
	}
	
	/**
	 * @Description: 场馆维护数据
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:21:28
	 */
	@RequestMapping(value = "/deateils")
	@ResponseBody
	public ApiMessage deateils(HttpServletRequest request , String venueid) {

		Venue venue = venueService.selectByPrimaryKey(venueid);
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("id", venue.getId()); // ID
		map.put("venuename", venue.getName()); // 名称
		map.put("ballsum", venue.getBallsum()); // 球场数

		City city = cityService.selectByPrimaryKey(venue.getCityid());
		District district = districtService.selectByPrimaryKey(venue.getDistrictid());
		
		map.put("city", ObjectUtil.defaultIfNull(city, city.getCity()) + "-" + ObjectUtil.defaultIfNull(district, district.getDistrict())); // 球馆地址-城市
		map.put("image", venue.getImage()); // 图标
		map.put("address", venue.getAddress()); // 详细地址
		map.put("tel", venue.getInformPhone()); // 通知电话
		map.put("warmreminder", venue.getWarmreminder()); // 滚动提示
		map.put("orderverify", venue.getOrderverify()); // 订场确认(0=人工确认1=自动确认)

		map.put("maxDay", venue.getMaxDay()); // 管理员数量
		
		return new ApiMessage(200, "查询成功", map);
	}
	
	
	/**
	 * @Description: 修改场馆维护数据
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:21:28
	 */
	@RequestMapping(value = "/updateVenue")
	@ResponseBody
	public ApiMessage updateTmplate(String venueid, Venue venue,String tel, HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		if (!Utils.getPhone(tel)) {
			return new ApiMessage(400, "手机号码不正确");
		}
		
		venue.setId(venueid);
		venue.setModifytime(new Date());
		venue.setInformPhone(tel);
		int flag = venueService.updateByPrimaryKeySelective(venue);
		if (flag > 0) {
			
			VenueLog venueLog = new VenueLog();
			venueLog.setId(Utils.getUUID());
			venueLog.setCreatetime(new Date());
			venueLog.setVenueid(venueid);
			venueLog.setContent(member.getAppnickname()+"在客户端修改场馆数据");
			venueLogService.insertSelective(venueLog);
			return new ApiMessage(200, "修改成功");
		}
		return new ApiMessage(400, "修改失败");
	}
	
	/**
	 * @Description: 会员选择日程数据(price为-1不可预订 -2已预订-3预订中-4已过期-5锁定)
	 * @author 宋高俊
	 * @date 2018年8月16日 下午7:35:14
	 */
	@RequestMapping(value = "/reserve")
	@ResponseBody
	public ApiMessage reserve(HttpServletRequest request, PageBean pageBean, String venueid, String datestr) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		// 当前日期
		Date newDate = DateUtil.getPreTime(new Date(), 2, 1);
		// 查询日期
		Date date = new Date();
		int outtime = 0;
		
		if (StringUtils.isBlank(datestr)) {
			// 获取当天的00:00:00时间
			date = DateUtil.getWeeHours(newDate, 0);
		} else {
			date = DateUtil.getParse(datestr, "yyyy-MM-dd");
		}
		// 判断是否是当前日期
		if (new Date().getTime() > date.getTime()) {
			String hour = DateUtil.getFormat(newDate, "HH");
			String minute = DateUtil.getFormat(newDate, "mm");
			// 已过期的时段
			outtime = (int) (Integer.valueOf(hour) / 0.5);
			// 分钟小于30则减一
			if (Integer.valueOf(minute) < 30) {
				outtime--;
			}
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
			String[] template = {};

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
					Date outDate = reserve.getCreatetime();
					if (reserve.getOrder().getType() == 1 || reserve.getOrder().getType() == 6) {
						// 已消费和支付待消费都是已预约状态
						priceType = "-2";
					} else if (reserve.getOrder().getType() == 0) {
						// 待支付是预约中状态
						priceType = "-3";
					} else if (reserve.getOrder().getType() == 5) {
						priceType = "-2";
						outDate = DateUtil.getPreTime(outDate, 1, 30);
					}

					TrainCoach trainCoach = new TrainCoach();
					String trainCoachName = "";
					if (!StringUtil.isBank(reserve.getOrder().getCoachid())) {
						trainCoach = trainCoachService.selectByPrimaryKey(reserve.getOrder().getCoachid());
						trainCoachName = trainCoach.getName();
					}

					// 修改时段内的状态
					for (int k = 0; k < timestr.length; k++) {
						int flag = Integer.valueOf(timestr[k]) - 1;
						Map<String, Object> timemap = datelistmap.get(flag);
						timemap.put("price", priceType);
						timemap.put("flag", "user" + j);
						timemap.put("coach", trainCoachName);
						timemap.put("outDate", DateUtil.getFormat(outDate, "HH:mm"));
						timemap.put("type", reserve.getOrder().getType());
						timemap.put("orderId", reserve.getOrder().getId());
						Member orderMember = memberService.selectByPrimaryKey(reserve.getOrder().getMemberid());
						timemap.put("memberName", orderMember.getAppnickname());
						if (member.getId().equals(reserve.getOrder().getMemberid())) {
							timemap.put("memberFlag", true);
						}
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
			}else {
				for (int j = 0; j < 48; j++) {
					Map<String, Object> map = new HashMap<>();
					map.put("no", j + 1);
					if ((j + 1) % 2 == 0) {
						map.put("time", time + ":30-" + (time + 1) + ":00");
						time++;
					} else {
						map.put("time", time + ":00-" + time + ":30");
					}
					if (j < 16) {
						map.put("price", "-1");
					} else {
						map.put("price", "-4");
					}
					map.put("flag", "");
					datelistmap.add(map);
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
	 * @Description: 管理员选择日程数据
	 * @author 宋高俊
	 * @date 2018年8月31日 下午7:42:02
	 */
	@RequestMapping(value = "/dayReserve")
	@ResponseBody
	public ApiMessage dayReserve(String datestr, HttpServletRequest request, String venueid) {

		// 当前日期
		Date newDate = DateUtil.getPreTime(new Date(), 2, 1);
		// 查询日期
		Date date = new Date();
		int outtime = 0;
		
		if (StringUtils.isBlank(datestr)) {
			// 获取当天的00:00:00时间
			date = DateUtil.getWeeHours(newDate, 0);
		} else {
			date = DateUtil.getParse(datestr, "yyyy-MM-dd");
		}
		// 判断是否是当前日期
		if (new Date().getTime() > date.getTime()) {
			String hour = DateUtil.getFormat(newDate, "HH");
			String minute = DateUtil.getFormat(newDate, "mm");
			// 已过期的时段
			outtime = (int) (Integer.valueOf(hour) / 0.5);
			// 分钟小于30则减一
			if (Integer.valueOf(minute) < 30) {
				outtime--;
			}
		}

		Venue venue = venueService.selectByPrimaryKey(venueid);
		// 获取当前场馆下的所有场地
		Field f = new Field();
		f.setVenueid(venue.getId());
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
			ftd.setVenueid(venue.getId());
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
				fieldTemplateDto.setVenueid(venue.getId());
				List<Reserve> reserves = reserveService.selectByFieldTemplateDto(fieldTemplateDto);
				// 修改时段状态
				for (int j = 0; j < reserves.size(); j++) {
					Reserve reserve = reserves.get(j);
					// 已有状态的时间段
					String[] timestr = reserve.getReservetimeframe().split(",");
					// 当前时段状态
					String priceType = "";
					if (reserve.getOrder().getType() == 1 || reserve.getOrder().getType() == 5 || reserve.getOrder().getType() == 6) {
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
						timemap.put("id", reserve.getOrderid());
						timemap.put("price", priceType);
						timemap.put("flag", "user" + j + priceType);
						timemap.put("type", reserve.getOrder().getType());
						timemap.put("orderId", reserve.getOrder().getId());

						Member orderMember = memberService.selectByPrimaryKey(reserve.getOrder().getMemberid());
						if (orderMember != null){
							timemap.put("memberName", orderMember.getAppnickname());
						}
						if(!StringUtil.isBank(reserve.getOrder().getCoachid())){
							TrainCoach trainCoach = trainCoachService.selectByPrimaryKey(reserve.getOrder().getCoachid());
							if (trainCoach != null){
								timemap.put("coachName", trainCoach.getName());
							}
						}
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
						timemap.put("id", venueLock.getId());
						timemap.put("price", "-5");
						timemap.put("flag", "user" + j + "-5");
						timemap.put("msg", venueLock.getContent());
						datelistmap.set(flag, timemap);
					}
				}
			}else {
				for (int j = 0; j < 48; j++) {
					Map<String, Object> map = new HashMap<>();
					map.put("no", j + 1);
					if ((j + 1) % 2 == 0) {
						map.put("time", time + ":30-" + (time + 1) + ":00");
						time++;
					} else {
						map.put("time", time + ":00-" + time + ":30");
					}
					if (j < 16) {
						map.put("price", "-1");
					} else {
						map.put("price", "-4");
					}
					map.put("flag", "");
					datelistmap.add(map);
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
	 * @Description: 设置锁定场地
	 * @author 宋高俊
	 * @param fieldid        场地ID
	 * @param content        内容
	 * @param venuetimeframe 所选时段
	 * @return
	 * @date 2018年9月3日 上午10:14:13
	 */
	@RequestMapping(value = "/setVenueLock")
	@ResponseBody
	public ApiMessage setVenueLock(String venueid, String fieldid, String content, String date, String venuetimeframe, HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		String[] times = venuetimeframe.split(",");
		for (int i = 0; i < times.length; i++) {
			Integer flag = venueLockMapper.selectIsVenueLock(member.getId(), venuetimeframe, DateUtil.getParse(date, "yyyy-MM-dd"));
			if (flag > 0) {
				return new ApiMessage(400, "抱歉," + StringUtil.timeToTimestr(times[i]) + "该时间段已被预约");
			}
		}
		VenueLock venueLock = new VenueLock();
		venueLock.setId(Utils.getUUID());
		venueLock.setCreatetime(new Date());
		venueLock.setLockdate(DateUtil.getParse(date, "yyyy-MM-dd"));
		venueLock.setVenueid(venueid);
		venueLock.setFieldid(fieldid);
		venueLock.setSetting(1);
		venueLock.setManagerid(member.getId());
		venueLock.setVenuetimeframe(venuetimeframe);
		venueLock.setContent(content);

		int flag = venueLockMapper.insert(venueLock);
		if (flag > 0) {
			return new ApiMessage(200, "新增成功");
		} else {
			return new ApiMessage(400, "新增失败");
		}
	}

	/**
	 * @Description: 历史锁定记录
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月3日 上午10:19:25
	 */
	@RequestMapping(value = "/setVenueLockList")
	@ResponseBody
	public ApiMessage setVenueLockList(HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		List<String> list = venueLockMapper.selectContentByManager(member.getId());
		return new ApiMessage(200, "查询成功", list);
	}

	/**
	 * @Description: 修改锁定内容
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月7日 下午2:15:40
	 */
	@RequestMapping(value = "/updateVenueLock")
	@ResponseBody
	public ApiMessage updateVenueLock(String id, String content, HttpServletRequest request) {
		
		VenueLock venueLock = new VenueLock();
		venueLock.setId(id);
		venueLock.setContent(content);
		int flag = venueLockMapper.updateByPrimaryKeySelective(venueLock);

		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 取消锁定
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月7日 下午2:15:40
	 */
	@RequestMapping(value = "/deleteVenueLock")
	@ResponseBody
	public ApiMessage deleteVenueLock(String id) {
		int flag = venueLockMapper.deleteByPrimaryKey(id);

		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 订单详情
	 * @author 宋高俊
	 * @param orderid
	 * @param response
	 * @param request
	 * @return
	 * @date 2018年9月7日 下午8:36:15
	 */
	/*@SuppressWarnings("unused")
	@RequestMapping(value = "/order/details")
	@ResponseBody
	public ApiMessage orderdetails(String orderid) {
		Order order = orderService.selectByPrimaryKey(orderid);
		Map<String, Object> map = new HashMap<>();
		map.put("id", order.getId());// id
		map.put("createTime", DateUtil.getFormat(order.getCreatetime()));// 订场时间
		map.put("member", order.getMember().getName());// 订场会员
		map.put("phone", order.getMember().getPhone());// 会员电话
		map.put("venue", order.getVenue().getName());// 场馆
		FieldTemplateDto fieldTemplateDto = new FieldTemplateDto();
		List<Reserve> reserves = reserveService.selectByFieldTemplateDto(fieldTemplateDto);
		// 修改时段状态
		for (int j = 0; j < reserves.size(); j++) {
			Reserve reserve = reserves.get(j);
			map.put("field", order.getId());// 场地
			map.put("id", order.getId());// 使用时间
			map.put("id", order.getId());// 场地费用
		}
		
		Coach coach = coachService.selectByPrimaryKey(order.getCoachid());
		if (coach != null) {
			map.put("coach", coach.getName());// 服务人员
			map.put("coachamount", order.getCoachamount());// 服务费用
		}else {
			map.put("coach", "无");// 服务人员
			map.put("coachamount", "0");// 服务费用
		}
		return new ApiMessage(200, "查询成功", map);
	}*/
	
	/**  
	 * @Description: 常用场馆列表
	 * @author 宋高俊  
	 * @return
	 * @date 2018年9月10日 下午4:00:58 
	 */ 
	@RequestMapping(value = "/oftenVenue")
	@ResponseBody
	public ApiMessage oftenVenue(HttpServletRequest request) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		List<Map<String, Object>> listmap = new ArrayList<>();
		List<Venue> venues = venueService.selectByOftenMember(member.getId());
		for (Venue venue : venues) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", venue.getId());// id
			map.put("image", venue.getImage());// 图片
			map.put("name", venue.getName());// 名称
			map.put("address", venue.getAddress());// 地址
			map.put("phone", venue.getContactPhone());// 电话
			map.put("warmreminder", venue.getWarmreminder());// 温馨提示
			map.put("type", venue.getType());// 球场类型
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
	public ApiMessage selectCoach(String venueid) {
		
		Venue venue = venueService.selectByPrimaryKey(venueid);
		List<VenueCoach> list = venueCoachService.selectByVenue(venueid);
		List<Map<String, Object>> listmap = new ArrayList<>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TrainTeamCoach trainTeamCoach = trainTeamCoachService.selectByCoachTeam(venueid, venue.getTrainteam());
				
				VenueCoach venueCoach = list.get(i);
				Map<String, Object> map = new HashMap<>();
				map.put("id", venueCoach.getTrainCoachId()); // 陪练ID
				map.put("coachid", venueCoach.getId()); // 陪练ID
				map.put("name", venueCoach.getTrainCoach().getName()); // 姓名
				map.put("price", venueCoach.getPrice()); // 价格
				map.put("image", venueCoach.getTrainCoach().getHeadImage()); // 图片
				map.put("manager", trainTeamCoach.getTeachType()); // 教学身份1=主教2=助教3=内勤
				listmap.add(map);
			}
		}else {
			Map<String, Object> map = new HashMap<>();
			TrainCoach trainCoach = trainCoachService.selectByDefault(venue.getCityid());
			map.put("coachid", trainCoach.getId()); // 陪练ID
			map.put("manager", 1); // 陪练ID
			map.put("name", trainCoach.getName()); // 姓名
			map.put("price", trainCoach.getMemberId()); // 由于没有用户保存价格
			map.put("image", trainCoach.getHeadImage()); // 图片
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}

	/**
	 * @Description: 保存订单接口
	 * @author 宋高俊
	 * @date 2018年8月21日 下午3:51:35
	 */
	@RequestMapping(value = "/saveorder")
	@ResponseBody
	public ApiMessage saveorder(String date, String timestr, String venueid, String coachid, HttpServletRequest request){

		String token = (String) request.getAttribute("token");
		final Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		if (StringUtil.isBank(date, timestr, venueid)) {
			return new ApiMessage(400, "参数不完整");
		}
		// 订单开始时间
		Date datetime = new Date();
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
		JSONArray jsonArray = JSONArray.parseArray(timestr);
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
			VenueCoach venueCoach = venueCoachService.selectByPrimaryKey(coachid);
			if (venueCoach != null) {
				order.setCoachid(coachid);
				// 半小时价格乘2
				order.setCoachamount(venueCoach.getPrice() * 2 * timesum);
				price = Arith.add(price, order.getCoachamount());
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
		
		MemberHabit oldMemberHabit = memberHabitMapper.selectByMemberVenue(member.getId(), venueid);
		if (oldMemberHabit == null) {
			// 保存常去场馆
			MemberHabit memberHabit = new MemberHabit();
			memberHabit.setId(Utils.getUUID());
			memberHabit.setCreatetime(new Date());
			memberHabit.setMemberid(member.getId());
			memberHabit.setVenueid(venueid);
			memberHabitMapper.insertSelective(memberHabit);
		}

		// 获取订单数据
		Venue venue = venueService.selectByPrimaryKey(order.getVenueid());
		List<Reserve> listReserve = reserveService.selectByOrder(order.getId());
		String area = "";
		String time = ""; 
		if (listReserve.size() > 0) {
			area = venue.getName() + listReserve.get(0).getField().getName();
			time = StringUtil.timeToTimestr(listReserve.get(0).getReservetimeframe().split(","));
		}
		final String area2 = area;
		final String time2 = time; 
//		// 发送订单取消数据
//		MoblieMessageUtil.sendTemplateSms(venue.getInformPhone(), member.getAppnickname(), member.getPhone(), area, 
//				DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd"), time);
		// 场馆
		Member venueMember = memberService.selectByPhone(venue.getContactPhone());
		final String openId = venueMember.getOpenid();
		
		// 订单数据
		Order newOrder = orderService.selectByPrimaryKey(orderid);
		// 预定通知消息
		JSONObject datajson = new JSONObject();
		datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
		datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + newOrder.getOrderno() + "\"}"));
		datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
		datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"待支付\"}"));
		datajson.put( "remark",
				JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area + "，日期"
						+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time + "用场，等待完成支付。\"}"));
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
		
		// 开启线程处理延时任务
		new Timer(order.getId() + "订单支付超时，已关闭").schedule(new TimerTask() {
			@Override
			public void run() {
				Order order = orderService.selectByPrimaryKey(orderid);
				OrderLog orderLog = new OrderLog();
				orderLog.setOrderid(orderid);
				orderLog.setType(0);
				if (order.getType() == 5 || order.getType() == 6) {
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
					

					// 预定通知消息
					JSONObject datajson = new JSONObject();
					datajson.put("first", JSONObject.parseObject("{\"value\":\"" + DateUtil.getFormat(new Date()) + "\"}"));
					datajson.put("keyword1", JSONObject.parseObject("{\"value\":\"" + order.getOrderno() + "\"}"));
					datajson.put("keyword2", JSONObject.parseObject("{\"value\":\"网球场预定\"}"));
					datajson.put("keyword3", JSONObject.parseObject("{\"value\":\"支付超时\"}"));
					
					// 支付超时，发给场馆
					if (!StringUtil.isBank(openId)) {
						datajson.put( "remark",
								JSONObject.parseObject("{\"value\":\"球友" + member.getAppnickname() + "(手机" + member.getPhone() + ")申请预约球场" + area2 + "，日期"
										+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time2 + "用场，支付超时。\"}"));
						logger.info(WXPayUtil.sendWXappTemplate(openId, WXConfig.wxTemplateId, "/pages/index/index", datajson));
					}

					// 支付超时，发给用户
					if (!StringUtil.isBank(member.getOpenid())) {
						datajson.put( "remark",
								JSONObject.parseObject("{\"value\":\"您预约的" + area2 + "，日期"
										+ DateUtil.getFormat(order.getOrderdate(), "yyyy-MM-dd") + "，时段" + time2 + "用场，因支付超时已被取消。\"}"));
						logger.info(WXPayUtil.sendWXappTemplate(member.getOpenid(), WXConfig.wxTemplateId, "/pages/index/index", datajson));
					}
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
	
	/**
	 * @Description: 增加常去场馆
	 * @author 宋高俊
	 * @param request
	 * @param id
	 * @return
	 * @date 2018年9月22日 下午4:18:30
	 */
	@RequestMapping(value = "/addVenueHabit")
	@ResponseBody
	public ApiMessage addVenueHabit(HttpServletRequest request, String id) {
		
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		MemberHabit oldMemberHabit = memberHabitMapper.selectByMemberVenue(member.getId(), id);
		if (oldMemberHabit == null) {
			// 保存常去场馆
			MemberHabit memberHabit = new MemberHabit();
			memberHabit.setId(Utils.getUUID());
			memberHabit.setCreatetime(new Date());
			memberHabit.setMemberid(member.getId());
			memberHabit.setVenueid(id);
			int flag = memberHabitMapper.insertSelective(memberHabit);
			if (flag > 0) {
				return new ApiMessage(200, "增加成功");
			} else {
				return new ApiMessage(400, "增加失败");
			}
		} else {
			return new ApiMessage(400, "已收藏该场馆");
		}
		
	}

	/**
	 * @Description: 取消常去场馆
	 * @author 宋高俊
	 * @param request
	 * @param id
	 * @return
	 * @date 2018年9月22日 下午4:18:30
	 */
	@RequestMapping(value = "/deleteVenueHabit")
	@ResponseBody
	public ApiMessage deleteVenueHabit(HttpServletRequest request, String id) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);

		MemberHabit memberHabit = memberHabitMapper.selectByMemberVenue(member.getId(), id);
		if (memberHabit.getMemberid().equals(member.getId())) {
			int flag = memberHabitMapper.deleteByPrimaryKey(memberHabit.getId());
			if (flag > 0) {
				return new ApiMessage(200, "删除成功");
			}
		}
		return new ApiMessage(400, "删除失败");
	}

	/**
	 * @Description: 场馆详情
	 * @author 宋高俊
	 * @date 2018年8月17日 上午11:16:24
	 */
	@RequestMapping(value = "/details")
	@ResponseBody
	public ApiMessage details(HttpServletRequest request, String venueid) {

		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		Venue venue = venueService.selectByPrimaryKey(venueid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", venue.getName());// 获取场馆名
		map.put("address", venue.getAddress());// 获取场馆地址
		map.put("tel", venue.getContactPhone());// 获取场馆电话
		map.put("lng", venue.getLongitude());// 获取经度
		map.put("lat", venue.getLatitude());// 获取纬度
		
		MemberHabit memberHabit = memberHabitMapper.selectByMemberVenue(member.getId(), venue.getId());
		if (memberHabit != null) {
			map.put("often", "1");// 是否是常用
		}else {
			map.put("often", "0");// 是否是常用
		}
		return new ApiMessage(200, "查询成功", map);
	}
	
	/**  
	 * @Description: 订场详情页面-纠错提交
	 * @author 宋高俊  
	 * @param request
	 * @param id
	 * @return 
	 * @date 2018年10月16日 上午10:51:06 
	 */ 
	@RequestMapping(value = "/saveVenueError")
	@ResponseBody
	public ApiMessage saveVenueError(HttpServletRequest request, String id, String content) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		VenueError ve = new VenueError();
		ve.setId(Utils.getUUID());
		ve.setCreateTime(new Date());
		ve.setContent(content);
		ve.setMemberId(member.getId());
		ve.setVenueId(id);
		
		venueErrorService.insertSelective(ve);
		return new ApiMessage(200, "纠错成功");
	}
	
	/**  
	 * @Description: 根据场馆获取退费费率
	 * @author 宋高俊  
	 * @param request
	 * @param venueid
	 * @return 
	 * @date 2018年11月6日 下午4:41:47 
	 */ 
	@RequestMapping(value = "/getAmountRefundWay")
	@ResponseBody
	public ApiMessage getAmountRefundWay(HttpServletRequest request, String venueid) {
		
		AmountRefundWay amountRefundWay = amountRefundWayMapper.selectByPrimaryKey(venueid);
		Map<String, Object> map = new HashMap<String, Object>();
		if (amountRefundWay != null) {
			map.put("id", amountRefundWay.getId());// ID
			map.put("fee1", amountRefundWay.getFee1());// <2小时内费率
			map.put("fee2", amountRefundWay.getFee2());// 2-4小时内费率
			map.put("fee3", amountRefundWay.getFee3());// 4-6小时内费率
			map.put("weatherStart", amountRefundWay.getWeatherStart());// 天气原因开始前
			map.put("weatherEnd", amountRefundWay.getWeatherEnd());// 天气原因开始后
		}else {
			map.put("fee1", 0);// <2小时内费率
			map.put("fee2", 0);// 2-4小时内费率
			map.put("fee3", 0);// 4-6小时内费率
			map.put("weatherStart", 0);// 天气原因开始前
			map.put("weatherEnd", 0);// 天气原因开始后
		}
		
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**  
	 * @Description: 修改场馆费率
	 * @author 宋高俊  
	 * @param request
	 * @param amountRefundWay
	 * @return 
	 * @date 2018年11月6日 下午4:48:04 
	 */ 
	@RequestMapping(value = "/updateAmountRefundWay")
	@ResponseBody
	public ApiMessage updateAmountRefundWay(HttpServletRequest request, AmountRefundWay amountRefundWay, String venueId) {
		
		int flag = 0;
		// 有退费ID则修改数据
		if (!StringUtil.isBank(amountRefundWay.getId())) {
			flag = amountRefundWayMapper.updateByPrimaryKeySelective(amountRefundWay);
		}else {
			// 查询是否已经有过场馆费率数据了
			AmountRefundWay lodAmountRefundWay = amountRefundWayMapper.selectByPrimaryKey(venueId);
			if (lodAmountRefundWay == null) {
				// 无则创建退费数据
				amountRefundWay.setId(venueId);
				amountRefundWay.setCreateTime(new Date());
				amountRefundWay.setModifyTime(new Date());
				flag = amountRefundWayMapper.insertSelective(amountRefundWay);
			}
		}
		
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		}else {
			return new ApiMessage(400, "修改失败");
		}
	}
	
	/**
	 * @Description: 根据手机号查询是否有匹配的场馆
	 * @param request
	 * @return
	 * @date 2018年11月15日20:27:37
	 */
	@RequestMapping(value = "/getMatchingVenue")
	@ResponseBody
	public ApiMessage getMatchingVenue(HttpServletRequest request) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		List<Venue> venues = venueService.selectByMatchingVenue(member.getPhone());
		if (venues.size() > 0) {
			// 有匹配的场馆
			return new ApiMessage(200, "获取成功", true);
		} else {
			// 无匹配的场馆
			return new ApiMessage(200, "获取成功", false);
		}
	}
	
	/**
	 * @Description: 根据手机号查询是否有匹配的场馆
	 * @param request
	 * @return
	 * @date 2018年11月15日20:27:37
	 */
	@RequestMapping(value = "/getMatchingVenue/list")
	@ResponseBody
	public ApiMessage getMatchingVenueList(HttpServletRequest request) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		List<Venue> venues = venueService.selectByMatchingVenue(member.getPhone());
		List<Map<String, Object>> venueListMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < venues.size(); i++) {
			Venue venue = venues.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venue.getId());// ID
			map.put("image", venue.getImage());// 封面
			map.put("venueName", venue.getName());// 场馆名称
			map.put("address", venue.getAddress());// 地址
			map.put("ballType", venue.getType());// 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
			if (StringUtil.isBank(venue.getTrainteam())) {
				map.put("enterFalg", false);// 是否入驻 true为是false为否
			} else {
				TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(venue.getTrainteam());
				map.put("enterFalg", true);// 是否入驻 true为是false为否
				map.put("enterTeamName", trainTeam.getTitle());// 入驻机构名
				map.put("owner", venue.getOwner());// 联系人
			}
			venueListMap.add(map);
		}
		return new ApiMessage(200, "获取成功", venueListMap);
	}
	
	/**
	 * @Description: 场馆详情页
	 * @param request
	 * @return
	 * @date 2018年11月15日20:27:37
	 */
	@RequestMapping(value = "/getMatchingVenue/details")
	@ResponseBody
	public ApiMessage getMatchingVenueDetails(HttpServletRequest request, String venueId) {
		
		Venue venue = venueService.selectByPrimaryKey(venueId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", venue.getId());// ID 
		map.put("venueName", venue.getName());// 场馆名称
		map.put("address", venue.getAddress());// 位置
		map.put("owner", venue.getOwner());// 联系人
		map.put("ballsum", venue.getBallsum());// 球场片数
		
		return new ApiMessage(200, "获取成功", map);
	}
	
	/**
	 * @Description: 入驻场馆
	 * @param request
	 * @return
	 * @date 2018年11月15日20:27:37
	 */
	@RequestMapping(value = "/getMatchingVenue/enter")
	@ResponseBody
	public ApiMessage getMatchingVenueEnter(HttpServletRequest request, String venueId, String owner, Integer ballSum, String trainTeamName,String trainTeamId) {
		String token = (String) request.getAttribute("token");
		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		
		// 场馆数据
		Venue venue = venueService.selectByPrimaryKey(venueId);
		
		// 新建培训机构
		TrainTeam trainTeam = new TrainTeam();
		
		// 是否已经有机构
		if (StringUtil.isBank(trainTeamId)) {
			// 无机构
			trainTeam.setId(Utils.getUUID());
			trainTeam.setCreateTime(new Date());
			trainTeam.setModifyTime(new Date());
			trainTeam.setAddress(venue.getAddress());
			trainTeam.setCityId(venue.getCityid());
			trainTeam.setTitle(trainTeamName);
			trainTeam.setPhone(member.getPhone());
			trainTeam.setHeadImage(venue.getImage());
			trainTeam.setLongitude(venue.getLongitude());
			trainTeam.setLatitude(venue.getLatitude());
			trainTeam.setTeachClass(venue.getType() == 1 ? "网球" : venue.getType() == 2 ? "足球" : venue.getType() == 3 ? "羽毛球" : "篮球");
			trainTeam.setTypeFlag(1);
			
			// 添加教练身份
			TrainTeamCoach trainTeamCoach = new TrainTeamCoach();
			trainTeamCoach.setId(Utils.getUUID());
			trainTeamCoach.setManager(1);
			trainTeamCoach.setShowFlag(1);
			trainTeamCoach.setTeachType(1);
			trainTeamCoach.setTrainTeamId(trainTeam.getId());

			// 查询是否已经成为教练
			TrainCoach oldTrainCoach = trainCoachService.selectByMember(member.getId());
			if (oldTrainCoach == null) {
				// 生成教练数据
				TrainCoach trainCoach = new TrainCoach();
				trainCoach.setId(Utils.getUUID());
				trainCoach.setCreateTime(new Date());
				trainCoach.setModifyTime(new Date());
				trainCoach.setMemberId(member.getId());
				trainCoach.setName(member.getAppnickname());
				trainCoach.setHeadImage(member.getAppavatarurl());
				trainCoachService.insertSelective(trainCoach);

				
				trainTeam.setTrainCoachId(trainCoach.getId());
				trainTeamCoach.setTrainCoachId(trainCoach.getId());
			} else {
				trainTeam.setTrainCoachId(oldTrainCoach.getId());
				trainTeamCoach.setTrainCoachId(oldTrainCoach.getId());
			}
			
			venue.setTrainteam(trainTeam.getId());
			venue.setOwner(owner);
			
			venueService.updateByPrimaryKeySelective(venue);
			trainTeamCoachService.insertSelective(trainTeamCoach);
			trainTeamService.insertSelective(trainTeam);
		}else {
			venue.setTrainteam(trainTeamId);
			venue.setOwner(owner);
		}
		venue.setBallsum(ballSum);
		
		List<Field> list = fieldService.selectByVenue(venueId);
		if (list.size() > 1) {
			VenueTemplate venueTemplate = venueTemplateService.selectByVenueDefault(venueId);
			for (int i = 1; i < list.size(); i++) {
				Field field = list.get(i);
				
				// 场地使用模板数据
				FieldTemplate fieldTemplate = new FieldTemplate();
				fieldTemplate.setCreatetime(new Date());
				fieldTemplate.setFieldid(field.getId());
				fieldTemplate.setTemplateid(venueTemplate.getId());
				fieldTemplate.setVenueid(venue.getId());
				
				// 统计日程表数据
				VenueStatis venueStatis = new VenueStatis();
				venueStatis.setCreatetime(new Date());
				venueStatis.setAmount(0.0);
				venueStatis.setScore(0.0);
				venueStatis.setVenueid(venue.getId());
				venueStatis.setTemplate(venueTemplate.getName());
				
				// 新增三十天的模板
				Date nowDate = new Date();
				for (int j = 0; j < 30; j++) {
					fieldTemplate.setId(Utils.getUUID());
					fieldTemplate.setFieldtime(nowDate);
					fieldTemplateService.insertSelective(fieldTemplate);
					
					nowDate = DateUtil.getPreTime(nowDate, 3, 1);
				}
			}
		}
		return new ApiMessage(200, "获取成功");
	}
	
	/**
	 * @Description: 发送语音验证码
	 * @author 宋高俊
	 * @param request
	 * @param tel
	 * @return
	 * @date 2018年11月17日 上午9:42:20
	 */
	@RequestMapping(value = "/sendVoiceTeamplate")
	@ResponseBody
	public ApiMessage sendVoiceTeamplate(HttpServletRequest request, String tel) {
		String code = Utils.getCode();
		MoblieVoiceUtil.sendVoiceTeamplate(tel, code);
		RedisUtil.setRedis(Global.wxapp_voice_SmsCode_ + tel, code, 300);
		return new ApiMessage(200, "发送成功");
	}
	
	/**
	 * @Description: 验证语音验证码
	 * @author 宋高俊
	 * @param request
	 * @param tel
	 * @return
	 * @date 2018年11月17日 上午9:42:20
	 */
	@RequestMapping(value = "/verifyCode")
	@ResponseBody
	public ApiMessage verifyCode(HttpServletRequest request, String tel, String code) {
		String codeNow = RedisUtil.getRedis(Global.wxapp_voice_SmsCode_ + tel);
		if (StringUtil.isBank(codeNow)) {
			return new ApiMessage(200, "请先发送验证码", false);
		}
		if (codeNow.equals(code)) {
			RedisUtil.delRedis(Global.wxapp_voice_SmsCode_ + tel);
			return new ApiMessage(200, "验证码正确", true);
		}else {
			return new ApiMessage(200, "验证码错误", false);
		}
	}
}

