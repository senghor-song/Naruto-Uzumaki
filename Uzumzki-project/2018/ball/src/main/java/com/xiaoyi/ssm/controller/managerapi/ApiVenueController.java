package com.xiaoyi.ssm.controller.managerapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dao.CombineJoinMapper;
import com.xiaoyi.ssm.dao.CombineLogMapper;
import com.xiaoyi.ssm.dao.CombineMapper;
import com.xiaoyi.ssm.dao.VenueLockMapper;
import com.xiaoyi.ssm.dao.VenueTeachMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.Coach;
import com.xiaoyi.ssm.model.Combine;
import com.xiaoyi.ssm.model.CombineJoin;
import com.xiaoyi.ssm.model.CombineLog;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Manager;
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
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.WXUtil;

/**
 * @Description: 场馆控制器
 * @author 宋高俊
 * @date 2018年8月21日 上午11:17:32
 */
@Controller("ManagerApiVenueController")
@RequestMapping("managerapi/venue")
public class ApiVenueController {

	private final Logger logger = Logger.getLogger(ApiVenueController.class.getName());

	@Autowired
	private VenueService venueService;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private FieldTemplateService fieldTemplateService;
	@Autowired
	private ReserveService reserveService;
	@Autowired
	private VenueLockMapper venueLockMapper;
	@Autowired
	private VenueTeachMapper venueTeachMapper;
	@Autowired
	private CombineMapper combineMapper;
	@Autowired
	private CombineJoinMapper combineJoinMapper;
	@Autowired
	private CombineLogMapper combineLogMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CoachService coachService;

	/**
	 * @Description: 场馆列表
	 * @author 宋高俊
	 * @date 2018年8月21日 上午11:20:26
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ApiMessage list(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);

		Venue venue = venueService.selectByManager(manager.getId());

		return new ApiMessage(200, "查询成功", venue);
	}

	/**
	 * @Description: 场馆详情
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:21:28
	 */
	@RequestMapping(value = "/deateils")
	@ResponseBody
	public ApiMessage deateils(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);

		Venue venue = venueService.selectByManager(manager.getId());
		Map<String, Object> map = new HashMap<>();
		map.put("venuename", venue.getName()); // 名称
		map.put("image", venue.getImage()); // 图标
		map.put("cityid", venue.getCityid()); // 球馆地址-城市
		map.put("districtid", venue.getDistrictid()); // 球馆地址-区县
		map.put("areaid", venue.getAreaid()); // 球馆地址-片区
		map.put("address", venue.getAddress()); // 详细地址
		map.put("tel", venue.getTel()); // 电话
		map.put("warmreminder", venue.getWarmreminder()); // 滚动提示
		map.put("account", "未绑定"); // 回款账号
		map.put("orderverify", venue.getOrderverify() == 0 ? "A" : "B"); // 订场确认

		map.put("manager", manager.getName()); // 当前用户
		map.put("managerAdmin", "0"); // 管理账号
		return new ApiMessage(200, "查询成功", map);
	}

	/**
	 * @Description: 修改场馆详情
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:21:28
	 */
	@RequestMapping(value = "/updateVenue")
	@ResponseBody
	public ApiMessage updateTmplate(Venue venue, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue oldVenue = venueService.selectByManager(manager.getId());
		venue.setId(oldVenue.getId());
		venue.setModifytime(new Date());
		int flag = venueService.updateByPrimaryKeySelective(venue);
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		}
		return new ApiMessage(400, "修改失败");
	}

	/**
	 * @Description: 删除场馆详情
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:21:28
	 */
	@RequestMapping(value = "/deleteVenue")
	@ResponseBody
	public ApiMessage deleteVenue(String id) {
		int flag = venueService.deleteByPrimaryKey(id);
		if (flag > 0) {
			return new ApiMessage(200, "删除成功");
		}
		return new ApiMessage(400, "删除失败");
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
	 * @Description: 管理员选择日程数据
	 * @author 宋高俊
	 * @date 2018年8月31日 下午7:42:02
	 */
	@RequestMapping(value = "/reserve")
	@ResponseBody
	public ApiMessage reserve(PageBean pageBean, String datestr, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue venue = venueService.selectByManager(manager.getId());
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
						timemap.put("id", reserve.getOrderid());
						timemap.put("price", priceType);
						timemap.put("flag", "user" + j + priceType);
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

//				// 获取当天教学占用时段=-6
//				List<VenueTeach> venueTeachs = venueTeachMapper.selectByNowDate(fieldTemplateDto);
//				// 修改时段状态
//				for (int j = 0; j < venueTeachs.size(); j++) {
//					VenueTeach venueTeach = venueTeachs.get(j);
//					// 已有状态的时间段
//					String[] timestr = venueTeach.getVenuetimeframe().split(",");
//
//					// 修改时段内的状态
//					for (int k = 0; k < timestr.length; k++) {
//						int flag = Integer.valueOf(timestr[k]) - 1;
//						Map<String, Object> timemap = datelistmap.get(flag);
//						timemap.put("id", venueTeach.getId());
//						timemap.put("price", "-6");
//						timemap.put("flag", "user" + j + "-6");
//						timemap.put("msg", venueTeach.getContent());
//						datelistmap.set(flag, timemap);
//					}
//				}
//
//				// 获取当天拼场占用时段=-7
//				List<Combine> combines = combineMapper.selectByNowDate(fieldTemplateDto);
//				for (int j = 0; j < combines.size(); j++) {
//					Combine combine = combines.get(j);
//					// 已有状态的时间段
//					String[] timestr = combine.getCombinetimeframe().split(",");
//
//					List<CombineJoin> combineJoins = combineJoinMapper.selectByCombine(combine.getId());
//					String boy = "";
//					for (int l = 0; l < combineJoins.size(); l++) {
//						boy += combineJoins.get(i).getMember().getName();
//						if (combineJoins.size() == l + 1) {
//							boy += ",";
//						}
//					}
//					// 修改时段内的状态
//					for (int k = 0; k < timestr.length; k++) {
//						int flag = Integer.valueOf(timestr[k]) - 1;
//						Map<String, Object> timemap = datelistmap.get(flag);
//						timemap.put("id", combine.getId());
//						timemap.put("price", "-7");
//						timemap.put("flag", "user" + j + "-7");
//						timemap.put("msg", "每人" + combine.getAmountsum() + "元,限" + combine.getBoy() + "人");
//						timemap.put("boy", boy);
//						datelistmap.set(flag, timemap);
//					}
//				}

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
	public ApiMessage setVenueLock(String fieldid, String content, String date, String venuetimeframe, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue venue = venueService.selectByManager(manager.getId());

		String[] times = venuetimeframe.split(",");
		for (int i = 0; i < times.length; i++) {
			Integer flag = venueLockMapper.selectIsVenueLock(manager.getId(), venuetimeframe, DateUtil.getParse(date, "yyyy-MM-dd"));
			if (flag > 0) {
				return new ApiMessage(400, "抱歉," + StringUtil.timeToTimestr(times[i]) + "该时间段已被预约");
			}
		}
		VenueLock venueLock = new VenueLock();
		venueLock.setId(Utils.getUUID());
		venueLock.setCreatetime(new Date());
		venueLock.setLockdate(DateUtil.getParse(date, "yyyy-MM-dd"));
		venueLock.setVenueid(venue.getId());
		venueLock.setFieldid(fieldid);
		venueLock.setSetting(1);
		venueLock.setManagerid(manager.getId());
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
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		List<String> list = venueLockMapper.selectContentByManager(manager.getId());
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
		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
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
	 * @Description: 设置教学场地
	 * @author 宋高俊
	 * @param fieldid        场地ID
	 * @param content        内容
	 * @param venuetimeframe 所选时段
	 * @return
	 * @date 2018年9月3日 上午10:14:13
	 */
	@RequestMapping(value = "/setVenueTeach")
	@ResponseBody
	public ApiMessage setVenueTeach(String fieldid, String content, String date, String venuetimeframe, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue venue = venueService.selectByManager(manager.getId());

		String[] times = venuetimeframe.split(",");
		for (int i = 0; i < times.length; i++) {
			Integer flag = venueTeachMapper.selectIsVenueTeach(manager.getId(), venuetimeframe);
			if (flag > 0) {
				return new ApiMessage(400, "抱歉," + StringUtil.timeToTimestr(times[i]) + "该时间段已被预约");
			}
		}
		
		VenueTeach venueTeach = new VenueTeach();
		venueTeach.setId(Utils.getUUID());
		venueTeach.setCreatetime(new Date());
		venueTeach.setTeachdate(DateUtil.getParse(date, "yyyy-MM-dd"));
		venueTeach.setVenueid(venue.getId());
		venueTeach.setFieldid(fieldid);
		venueTeach.setSetting(1);
		venueTeach.setManagerid(manager.getId());
		venueTeach.setVenuetimeframe(venuetimeframe);
		venueTeach.setContent(content);

		int flag = venueTeachMapper.insert(venueTeach);
		if (flag > 0) {
			return new ApiMessage(200, "新增成功");
		} else {
			return new ApiMessage(400, "新增失败");
		}
	}

	/**
	 * @Description: 历史教学记录
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月3日 上午10:19:25
	 */
	@RequestMapping(value = "/setVenueTeachList")
	@ResponseBody
	public ApiMessage setVenueTeachList(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		List<String> list = venueTeachMapper.selectContentByManager(manager.getId());
		return new ApiMessage(200, "查询成功", list);
	}

	/**
	 * @Description: 修改教学内容
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月7日 下午2:15:40
	 */
	@RequestMapping(value = "/updateVenueTeach")
	@ResponseBody
	public ApiMessage updateVenueTeach(String id, String content, HttpServletRequest request) {
		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
		VenueTeach venueTeach = new VenueTeach();
		venueTeach.setId(id);
		venueTeach.setContent(content);
		int flag = venueTeachMapper.updateByPrimaryKeySelective(venueTeach);

		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 取消教学
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月7日 下午2:15:40
	 */
	@RequestMapping(value = "/deleteVenueTeach")
	@ResponseBody
	public ApiMessage deleteVenueTeach(String id) {
		int flag = venueTeachMapper.deleteByPrimaryKey(id);

		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 设置散拼数据
	 * @author 宋高俊
	 * @param combine
	 * @param date    散拼日期
	 * @return
	 * @date 2018年9月3日 上午11:59:29
	 */
	@RequestMapping(value = "/setCombine")
	@ResponseBody
	public ApiMessage setCombine(Combine combine, String date, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");

		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);
		Venue venue = venueService.selectByManager(manager.getId());

		combine.setId(Utils.getUUID());
		combine.setCreatetime(new Date());
		combine.setModifytime(new Date());
		combine.setManagerid(manager.getId());
		combine.setCombinedate(DateUtil.getParse(date, "yyyy-MM-dd"));
		combine.setVenueid(venue.getId());

		int flag = combineMapper.insertSelective(combine);
		if (flag > 0) {
			return new ApiMessage(200, "新增成功");
		} else {
			return new ApiMessage(400, "新增失败");
		}
	}

	/**
	 * @Description: 查看拼场
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月3日 下午2:18:09
	 */
	@RequestMapping(value = "/lookCombine")
	@ResponseBody
	public ApiMessage lookCombine(String id, HttpServletRequest request) {
		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
		Combine combine = combineMapper.selectByPrimaryKey(id);

		Map<String, Object> map = new HashMap<>();
		map.put("id", id); // ID
		String date = "";
		date = DateUtil.getFormat(combine.getCombinedate(), "yyyy年MM月dd日");
		date += " " + StringUtil.timeToTimestr(combine.getCombinetimeframe().split(","));
		map.put("date", date); // 时间
		map.put("venue", combine.getVenue().getName()); // 场地
		map.put("title", combine.getTitle()); // 主题
		map.put("demand", combine.getDemand()); // 水平要求
		map.put("boy", combine.getBoy()); // 最大人数
		map.put("content", combine.getContent()); // 其他说明
		map.put("amount", combine.getAmountsum()); // 费用
		map.put("nowboy", combineJoinMapper.countByCombine(id)); // 当前报名人数
		return ApiMessage.succeed(map);
	}

	/**
	 * @Description: 根据ID关闭拼场
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月3日 下午2:53:26
	 */
	@RequestMapping(value = "/daleteCombine")
	@ResponseBody
	public ApiMessage daleteCombine(String id) {
		int flag = combineMapper.deleteByPrimaryKey(id);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 保存修改散拼时段
	 * @author 宋高俊
	 * @param id        散拼ID
	 * @param title     散拼标题
	 * @param demand    要求
	 * @param boy       人数上限
	 * @param content   活动描述
	 * @param amountsum 每人费用
	 * @return
	 * @date 2018年9月3日 下午3:00:40
	 */
	@RequestMapping(value = "/updateCombine")
	@ResponseBody
	public ApiMessage updateCombine(String id, String title, String demand, Integer boy, String content,
			Double amountsum, HttpServletRequest request) {
		
//		HttpSession session = request.getSession();
//		String openid = (String) session.getAttribute("openid");
//		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, openid);

		Combine combine = new Combine();
		combine.setId(id);
		combine.setModifytime(new Date());
		if (!StringUtil.isBank(title)) {
			combine.setTitle(title);
		}
		if (!StringUtil.isBank(demand)) {
			combine.setDemand(demand);
		}
		if (boy != null) {
			combine.setBoy(boy);
		}
		if (!StringUtil.isBank(content)) {
			combine.setContent(content);
		}
		if (amountsum != null) {
			combine.setAmountsum(amountsum);
		}

		int flag = combineMapper.updateByPrimaryKeySelective(combine);
		if (flag > 0) {
			return new ApiMessage(200, "修改成功");
		} else {
			return new ApiMessage(400, "修改失败");
		}
	}

	/**
	 * @Description: 报名人数列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月3日 下午3:23:42
	 */
	@RequestMapping(value = "/combineJoinList")
	@ResponseBody
	public ApiMessage combineJoinList(String id) {

		List<Map<String, Object>> list = new ArrayList<>();
		List<CombineJoin> combineJoins = combineJoinMapper.selectByCombine(id);
		for (CombineJoin combineJoin : combineJoins) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", combineJoin.getId());// ID
			map.put("name", combineJoin.getMember().getName());// 姓名
			map.put("price", combineJoin.getPrice());// updateCombine
			list.add(map);
		}
		return ApiMessage.succeed(list);
	}

	/**
	 * @Description: 关闭约球
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月3日 下午3:23:42
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteCombine")
	@ResponseBody
	public ApiMessage deleteCombine(String id, String content) {
		// 修改散拼为关闭状态
		Combine combine = combineMapper.selectByPrimaryKey(id);
		combine.setModifytime(new Date());
		combine.setType(1);
		combineMapper.updateByPrimaryKeySelective(combine);

		// 获取所有未退款的会员
		List<Map<String, Object>> list = new ArrayList<>();
		List<CombineJoin> combineJoins = combineJoinMapper.selectByCombine(id);

		for (CombineJoin combineJoin : combineJoins) {
			try {
				Map map = WXUtil.weiXinRefund(id, combineJoin.getPrice(), combineJoin.getPrice(), content,1);
				// 记录日志
				CombineLog combineLog = new CombineLog();
				combineLog.setId(Utils.getUUID());
				combineLog.setCreatetime(new Date());
				combineLog.setCombineid(id);
				combineLog.setType(0);

				// result_code 返回SUCCESS/FAIL, SUCCESS
				if ("SUCCESS".equals(map.get("result_code"))) {
					// 修改订单状态为已退款
					combineJoin.setType(4);
					combineJoinMapper.updateByPrimaryKeySelective(combineJoin);
					// 记录退款的日志
					combineLog.setContent(combineJoin.getMember().getName() + "用户已全额退款,退款ID:" + combineJoin.getId());
				} else {
					// 记录退款的日志
					combineLog.setContent(combineJoin.getMember().getName() + "用户退款失败,退款ID:" + combineJoin.getId()
							+ ";原因:" + map.get("err_code_des").toString());
				}
				combineLogMapper.insert(combineLog);
			} catch (Exception e) {
				logger.error("", e);
				continue;
			}

		}
		return ApiMessage.succeed(list);
	}

	/**
	 * @Description: 散拼退款接口
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月3日 下午3:23:42
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/combineRefund")
	@ResponseBody
	public ApiMessage combineRefund(String id, String content) {
		CombineJoin combineJoin = combineJoinMapper.selectByPrimaryKey(id);

		Map map = WXUtil.weiXinRefund(id, combineJoin.getPrice(),
				combineJoin.getPrice(),content , 1);

		CombineLog combineLog = new CombineLog();
		combineLog.setId(Utils.getUUID());
		combineLog.setCreatetime(new Date());
		combineLog.setType(0);
		combineLog.setCombineid(combineJoin.getCombineid());
		// result_code 返回SUCCESS/FAIL, SUCCESS
		if ("SUCCESS".equals(map.get("result_code"))) {
			// 修改订单状态为已退款
			combineJoin.setType(4);
			combineJoinMapper.updateByPrimaryKeySelective(combineJoin);

			combineLog.setContent(combineJoin.getMember().getName() + "用户已全额退款,退款ID:" + combineJoin.getId());
			combineLogMapper.insert(combineLog);
			return new ApiMessage(200, "退款成功");
		} else {
			combineLog.setContent(combineJoin.getMember().getName() + "用户退款失败,退款ID:" + combineJoin.getId() + ";原因:"
					+ map.get("err_code_des").toString());
			combineLogMapper.insert(combineLog);
			return new ApiMessage(400, map.get("err_code_des").toString());
		}
	}

	/**
	 * 订单微信退款 此处用的 自己生成的订单号 就是上面支付中生成的订单号
	 * 
	 * @param merchantNumber      商户这边的订单号
	 * @param wxTransactionNumber 微信那边的交易单号
	 * @param totalFee            订单的金额
	 * @return 返回参数见微信支付查询订单
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/weiXinPayOrderRefund")
	@ResponseBody
	public ApiMessage weiXinPayOrderRefund(String orderid, HttpServletResponse response, HttpServletRequest request, String content) {
		Order order = orderService.selectByPrimaryKey(orderid);

		Map map = WXUtil.weiXinRefund(orderid, order.getPrice(), order.getPrice(),content,0);

		// result_code 返回SUCCESS/FAIL, SUCCESS
		if ("SUCCESS".equals(map.get("result_code"))) {
			// 修改订单状态为已退款
			order.setModifytime(new Date());
			order.setType(4);
			orderService.updateByPrimaryKeySelective(order);

			OrderLog orderLog = new OrderLog();
			orderLog.setId(Utils.getUUID());
			orderLog.setCreatetime(new Date());
			orderLog.setType(0);
			orderLog.setOrderid(orderid);
			orderLog.setContent("发起退款申请，已全额退款");
			return new ApiMessage(200, "退款成功");
		} else {
			return new ApiMessage(400, map.get("err_code_des").toString());
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
	@SuppressWarnings("unused")
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
	}

}
