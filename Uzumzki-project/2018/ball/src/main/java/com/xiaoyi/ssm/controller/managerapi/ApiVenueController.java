package com.xiaoyi.ssm.controller.managerapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyi.ssm.dao.VenueLockMapper;
import com.xiaoyi.ssm.dao.VenueTemplateMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.dto.PageBean;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Reserve;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueLock;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.ReserveService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 场馆控制器
 * @author 宋高俊
 * @date 2018年8月21日 上午11:17:32
 */
@Controller("ManagerApiVenueController")
@RequestMapping("managerapi/venue")
public class ApiVenueController {

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
	private VenueTemplateMapper venueTemplateMapper;

	/**
	 * @Description: 场馆列表
	 * @author 宋高俊
	 * @date 2018年8月21日 上午11:20:26
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ApiMessage list(String token) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);

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
	public ApiMessage deateils(String id, String token) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);

		Venue venue = venueService.selectByPrimaryKey(id);
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
	public ApiMessage updateTmplate(String token, Venue venue) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
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
	 * @Description: 新增场馆
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:21:28
	 */
	@RequestMapping(value = "/saveVenue")
	@ResponseBody
	public ApiMessage saveVenue(Venue venue, String token) {
		return venueService.saveVenue(venue, token);
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
			} else {
				continue;
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
	 * @param id
	 * @param content
	 * @return
	 * @date 2018年9月1日 下午4:54:24
	 */
	@RequestMapping(value = "/setVenueLock")
	@ResponseBody
	public ApiMessage setVenueLock(String token, String id, String content, String venuetimeframe) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);

		VenueLock venueLock = new VenueLock();
		venueLock.setId(Utils.getUUID());
		venueLock.setCreatetime(new Date());
		venueLock.setSetting(1);
		venueLock.setManagerid(manager.getId());
		venueLock.setVenuetimeframe(venuetimeframe);

		int flag = venueLockMapper.insert(venueLock);
		if (flag > 0) {
			return new ApiMessage(200, "新增成功");
		} else {
			return new ApiMessage(400, "新增失败");
		}
	}
}
