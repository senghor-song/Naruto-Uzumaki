package com.xiaoyi.ssm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dao.AmountRefundWayMapper;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.AmountRefund;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.model.TrainTeamVenue;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueCheck;
import com.xiaoyi.ssm.model.VenueEnter;
import com.xiaoyi.ssm.model.VenueError;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.model.VenueRefund;
import com.xiaoyi.ssm.model.VenueStatis;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.MemberService;
import com.xiaoyi.ssm.service.OperationLogService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.service.TrainCoachService;
import com.xiaoyi.ssm.service.TrainTeamCoachService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.VenueCheckService;
import com.xiaoyi.ssm.service.VenueCoachService;
import com.xiaoyi.ssm.service.VenueEnterService;
import com.xiaoyi.ssm.service.VenueErrorService;
import com.xiaoyi.ssm.service.VenueLogService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueStatisService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.ChineseCharacterUtil;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.ImportExcelUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 场馆控制器
 * @author 宋高俊
 * @date 2018年8月18日 上午11:35:49
 */
@Controller(value = "adminVenueController")
@RequestMapping(value = "/admin/venue")
public class VenueController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private VenueService venueService;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private VenueCoachService coachService;
	@Autowired
	private VenueTemplateService venueTemplateService;
	@Autowired
	private VenueLogService venueLogService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private VenueCheckService venueCheckService;
	@Autowired
	private VenueErrorService venueErrorService;
	@Autowired
	private VenueEnterService venueEnterService;
	@Autowired
	private TrainTeamService trainTeamService;
	@Autowired
	private TrainCoachService trainCoachService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private TrainTeamCoachService trainTeamCoachService;
	@Autowired
	private PermissionService permissionService;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private AmountRefundWayMapper amountRefundWayMapper;
    @Autowired
    private FieldTemplateService fieldTemplateService;
    @Autowired
    private VenueStatisService venueStatisService;

	/**
	 * @Description: 场馆页面
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/listview")
	public String listview(HttpServletRequest request, Model model) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		List<Permission> list = permissionService.selectByBtu(staff.getPower(), "22");
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute("btn"+list.get(i).getId(), "1");
		}
		return "admin/venue/list";
	}
	
	/**  
	 * @Description: 导入场馆页面
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月2日 下午2:45:33 
	 */ 
	@RequestMapping(value = "/importView")
	public String importView() {
		return "admin/venue/importView";
	}


	/**
	 * @Description: 场馆数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage, Integer selectType, String keyword) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Venue> venues = venueService.selectBySearch(selectType, keyword);
		PageInfo<Venue> pageInfo = new PageInfo<>(venues);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < venues.size(); i++) {
			Venue venue = venues.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venue.getId());// ID
			map.put("venueno", venue.getVenueno());// 场馆编号
			map.put("city", venue.getCityT().getCity());// 城市
			map.put("district", venue.getDistrictT().getDistrict());// 区县
			map.put("name", venue.getName());// 场馆
			if (venue.getType() != null) {
				map.put("type", venue.getType() == 1 ? "网球" : venue.getType() == 2 ? "足球" : venue.getType() == 3 ? "羽毛球" : venue.getType() == 4 ? "篮球"
						: "无");// 类型
			} else {
				map.put("type", "无");
			}

			if (!StringUtil.isBank(venue.getTrainteam())) {
				TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(venue.getTrainteam());
				if (trainTeam != null) {
					map.put("trainName", trainTeam.getTitle()); // 入驻机构名称
				}
			}
			map.put("reserveShow", venue.getReserveShow() == 0 ? "否" : "是"); // 订场入口(0=否1=是)
			map.put("reserveSms", venue.getReserveSms() == 0 ? "否" : "是"); // 订场短信(0=否1=是)
			map.put("reservePaySms", venue.getReservePaySms() == 0 ? "否" : "是"); // 订场支付短信(0=否1=是)
			
			
			if (venue.getLongitude() != null && venue.getLatitude() != null) {
				map.put("lnglat", "是"); // 坐标
			}else {
				map.put("lnglat", "否"); // 坐标
			}
			if (!StringUtil.isBank(venue.getInformPhone())) {
				if (venue.getInformPhone().length() > 4) {
					String tel = venue.getInformPhone().substring(0, venue.getInformPhone().length()-4) + "****";
					map.put("informPhone", tel); // 通知电话
				}else {
					map.put("informPhone", venue.getInformPhone()); // 通知电话
				}
			}
			
			if (!StringUtil.isBank(venue.getContactPhone())) {
				if (venue.getContactPhone().length() > 4) {
					String tel = venue.getContactPhone().substring(0, venue.getContactPhone().length()-4) + "****";
					map.put("contactPhone", tel); // 联系电话
				}else {
					map.put("contactPhone", venue.getContactPhone()); // 联系电话
				}
			}
			
			map.put("venueError", venueErrorService.countByVenue(venue.getId())); // 报错
			
			map.put("venuelogSum", venueLogService.countByVenue(venue.getId()));// 日志
			map.put("showflag", venue.getShowflag() == 1 ? "正常" : "屏蔽"); // 状态(0=审核中1=正常2=屏蔽)
			map.put("lng", venue.getLongitude());// 经度
			map.put("lat", venue.getLatitude());// 维度
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}
	
	/**
	 * @Description: 场馆数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/venueList")
	@ResponseBody
	public AdminMessage venueList(Integer selectType, String keyword) {
		List<Venue> venues = venueService.selectBySearch(selectType, keyword);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < venues.size(); i++) {
			Venue venue = venues.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venue.getId());// ID
			map.put("city", venue.getCityT().getCity());// 城市
			map.put("name", venue.getName());// 场馆
			listMap.add(map);
		}
		return new AdminMessage(venues.size(), listMap);
	}

	/**
	 * @Description: 场馆默认模板页面
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/venueTemplate")
	public String venueTemplate(Model model, String venueid) {
		// 由于场馆和模板有主键关联，主键关联的数据即为默认模板
		VenueTemplate venueTemplate = venueTemplateService.selectByVenueTemplate(venueid, venueid);
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		if (venueTemplate != null) {
			String[] templates = venueTemplate.getPrice().split(",");
			for (int i = 0; i < 48; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("time", StringUtil.timeToTimestr((i + 1) + ""));
				if (!"-1".equals(templates[i])) {
					map.put("price", templates[i]);
				}
				listMaps.add(map);
			}
		}else {
			for (int i = 0; i < 48; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("time", StringUtil.timeToTimestr((i + 1) + ""));
				listMaps.add(map);
			}
		}
		model.addAttribute("listMaps", listMaps);// 时间段
		model.addAttribute("venueid", venueid);// 场馆ID
		return "admin/venue/template";
	}
	
	/**
	 * @Description: 场馆默认模板保存设置数据
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/saveVenueTemplate")
	@ResponseBody
	public ApiMessage saveVenueTemplate(Model model, String venueid, @RequestParam("priceArr[]") String priceArr[]) {
		// 由于场馆和模板有主键关联，主键关联的数据即为默认模板
		VenueTemplate venueTemplate = venueTemplateService.selectByVenueTemplate(venueid, venueid);
		String template = "";
		if (priceArr == null) {
			for (int i = 0; i < 48; i++) {
				template += "-1,";
			}
		}else {
			for (int i = 0; i < priceArr.length; i++) {
				if ("".equals(priceArr[i])) {
					template += "-1,";
				}else {
					template += priceArr[i] + ",";
				}
			}
		}
		venueTemplate.setPrice(template);
		venueTemplateService.updateByPrimaryKeySelective(venueTemplate);
		return new ApiMessage(200, "保存成功");
	}
	
	/**  
	 * @Description: 场馆模板梳理页面
	 * @author 宋高俊  
	 * @param model
	 * @return 
	 * @date 2018年11月2日 下午2:29:38 
	 */ 
	@RequestMapping(value = "/venueTemplateAll")
	public String venueTemplateAll(Model model) {
		// 由于场馆和模板有主键关联，主键关联的数据即为默认模板
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 48; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("time", StringUtil.timeToTimestr((i + 1) + ""));
			listMaps.add(map);
		}
		model.addAttribute("listMaps", listMaps);// 时间段
		return "admin/venue/templateAll";
	}
	
	/**
	 * @Description: 场馆默认模板保存设置数据
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/saveVenueTemplateAll")
	@ResponseBody
	public ApiMessage saveVenueTemplateAll(Model model, Integer venueAll, @RequestParam("venues[]") String venues[], @RequestParam("priceArr[]") String priceArr[]
			, Integer selectType, String keyword) {
		String returnMsg = "";
		// 保存系统模板设置
		String template = "";
		for (int j = 0; j < priceArr.length; j++) {
			if ("".equals(priceArr[j])) {
				template += "-1,";
			}else {
				template += priceArr[j] + ",";
			}
		}
		// 是否是全选
		if (venueAll == 1) {
			List<Venue> venuelist = venueService.selectBySearch(selectType, keyword);
			for (int i = 0; i < venuelist.size(); i++) {
				// 由于场馆和模板有主键关联，主键关联的数据即为默认模板
				VenueTemplate venueTemplate = venueTemplateService.selectByVenueTemplate(venuelist.get(i).getId(), venuelist.get(i).getId());
				if (venueTemplate != null) {
					venueTemplate.setPrice(template);
					venueTemplateService.updateByPrimaryKeySelective(venueTemplate);
					returnMsg += venuelist.get(i).getName() + "修改成功";
				}else {
					returnMsg += venuelist.get(i).getName() + "修改失败";
				}
				returnMsg += "</br>";
			}
		}else {
			for (int i = 0; i < venues.length; i++) {
				// 由于场馆和模板有主键关联，主键关联的数据即为默认模板
				VenueTemplate venueTemplate = venueTemplateService.selectByVenueTemplate(venues[i], venues[i]);
				Venue venue = venueService.selectByPrimaryKey(venues[i]);
				if (venueTemplate != null) {
					venueTemplate.setPrice(template);
					venueTemplateService.updateByPrimaryKeySelective(venueTemplate);
					returnMsg += venue.getName() + "修改成功";
				}else {
					returnMsg += venue.getName() + "修改失败";
				}
				returnMsg += "</br>";
			}
		}
		return new ApiMessage(200, "保存成功", returnMsg);
	}
	
	/**  
	 * @Description: 场馆新增页面
	 * @author 宋高俊  
	 * @param model
	 * @param id
	 * @return 
	 * @date 2018年11月10日 下午4:39:36 
	 */ 
	@RequestMapping(value = "/insert/venue")
	@ResponseBody
	public ApiMessage insertView(Model model, Venue venue, String cityName, String districtName) {
		
		if (!StringUtil.isBank(cityName)) {
			cityName = cityName.substring(0, cityName.length()-1);
			City city = cityService.selectByName(cityName);
			if (city == null) {
				return new ApiMessage(400, "'"+cityName+"'城市不存在,保存失败");
			}
			venue.setCityid(city.getId());
		}else {
			return new ApiMessage(400, "城市不存在,保存失败");
		}

		if (!StringUtil.isBank(districtName)) {
			districtName = districtName.substring(0, districtName.length()-1);
			District district = districtService.selectByName(districtName);
			if (district == null) {
				return new ApiMessage(400, "'"+districtName+"'区县不存在,保存失败");
			}
			venue.setDistrictid(district.getId());
		}
		
		// 保存新增的场馆
		venue.setId(Utils.getUUID());
		venue.setCreatetime(new Date());
		venue.setModifytime(new Date());
		venue.setType(1);
		venue.setBallsum(1);
		venueService.insertSelective(venue);

		// 导入新增默认1号场
		Field field = new Field();
		field.setId(Utils.getUUID());
		field.setCreatetime(new Date());
		field.setModifytime(new Date());
		field.setVenueid(venue.getId());
		field.setName("1号场");
		fieldService.insertSelective(field);
		
		// 导入新增默认系统模板
		VenueTemplate venueTemplate = new VenueTemplate();
		venueTemplate.setId(venue.getId());
		venueTemplate.setCreatetime(new Date());
		venueTemplate.setDefaultflag(1);
		venueTemplate.setModifytime(new Date());
		venueTemplate.setName("系统模板");
		venueTemplate.setPrice("-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1");
		venueTemplate.setVenueid(venue.getId());
		venueTemplateService.insertSelective(venueTemplate);
		
		// 场地使用模板数据
		FieldTemplate fieldTemplate = new FieldTemplate();
		fieldTemplate.setCreatetime(new Date());
		fieldTemplate.setFieldid(field.getId());
		fieldTemplate.setTemplateid(venueTemplate.getId());
		fieldTemplate.setVenueid(venue.getId());
		
		// 统计日程表数据
		VenueStatis venueStatis = new VenueStatis();
		venueStatis.setId(Utils.getUUID());
		venueStatis.setCreatetime(new Date());
		venueStatis.setAmount(0.0);
		venueStatis.setScore(0.0);
		venueStatis.setVenueid(venue.getId());
		venueStatis.setTemplate(venueTemplate.getName());

		// 新增三十天的模板
		Date nowDate = new Date();
		for (int i = 0; i < 30; i++) {
			fieldTemplate.setId(Utils.getUUID());
			fieldTemplate.setFieldtime(nowDate);
			fieldTemplateService.insertSelective(fieldTemplate);
			
			venueStatis.setStatisdate(nowDate);
			venueStatisService.insertSelective(venueStatis);
			
			nowDate = DateUtil.getPreTime(nowDate, 3, 1);
			
		}
		
		// 新增默认退费费率
		AmountRefundWay amountRefundWay = new AmountRefundWay();
		amountRefundWay.setId(venue.getId());
		amountRefundWay.setCreateTime(new Date());
		amountRefundWay.setModifyTime(new Date());
		amountRefundWay.setFee1(20);
		amountRefundWay.setFee2(0);
		amountRefundWay.setFee3(0);
		amountRefundWay.setWeatherStart(0);
		amountRefundWay.setWeatherEnd(50);
		amountRefundWayMapper.insertSelective(amountRefundWay);
		
		return new ApiMessage(200, "新增成功");
	}
	
	/**  
	 * @Description: 场馆新增数据
	 * @author 宋高俊  
	 * @param model
	 * @param id
	 * @return 
	 * @date 2018年11月10日 下午4:39:36 
	 */ 
	@RequestMapping(value = "/add/view")
	public String addView(Model model, String id) {

		List<City> list = cityService.selectByAll(null);
		model.addAttribute("citys", list);
		
		return "admin/venue/add";
	}
	
	
	/**  
	 * @Description: 场馆修改页面
	 * @author 宋高俊  
	 * @param model
	 * @param id
	 * @return 
	 * @date 2018年10月22日 下午7:51:12 
	 */ 
	@RequestMapping(value = "/update/view")
	public String updateView(Model model, String id) {
		Venue venue = venueService.selectByPrimaryKey(id);
		model.addAttribute("venueid", id);
		model.addAttribute("venueno", venue.getVenueno());
		model.addAttribute("cityid", venue.getCityid());
		model.addAttribute("districtid", venue.getDistrictid());
		model.addAttribute("showflag", venue.getShowflag());
		model.addAttribute("name", venue.getName());
		model.addAttribute("contactPhone", venue.getContactPhone());
		model.addAttribute("informPhone", venue.getInformPhone());
		model.addAttribute("image", venue.getImage());
		model.addAttribute("owner", venue.getOwner());
		/*if (StringUtil.isBank(venue.getTrainteam())) {
			model.addAttribute("trainAddFlag", venue.getTrainAddFlag());// 入驻状态0=空1=添加
		}else {
			model.addAttribute("trainAddFlag", 2);// 入驻状态2入驻
		}*/
		model.addAttribute("reserveShow", venue.getReserveShow());
		model.addAttribute("reserveSms", venue.getReserveSms());
		model.addAttribute("reservePaySms", venue.getReservePaySms());
		if (!StringUtil.isBank(venue.getTrainteam())) {
			TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(venue.getTrainteam());
			model.addAttribute("trainTeamName", trainTeam.getTitle());
		}
		model.addAttribute("lng", venue.getLongitude());
		model.addAttribute("lat", venue.getLatitude());
		
		model.addAttribute("address", venue.getAddress());

		List<City> list = cityService.selectByAll(null);
		model.addAttribute("citys", list);
		
		return "admin/venue/edit";
	}
	
	/**  
	 * @Description: 场馆数据修改
	 * @author 宋高俊  
	 * @param id
	 * @param cityid
	 * @param districtid
	 * @param showflag
	 * @param venuename
	 * @param phone
	 * @return 
	 * @date 2018年10月23日 下午3:33:20 
	 */ 
	@RequestMapping(value = "/update/venue")
	@ResponseBody
	public ApiMessage updateVenue(HttpServletRequest request, String id, Integer showflag, String venueName, String contactPhone,String informPhone,
			String image, String owner, boolean reserveShow, boolean reserveSms, boolean reservePaySms, String cityName, String districtName, String address,
			Double lng, Double lat) {
		
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");

		Venue venue = venueService.selectByPrimaryKey(id);

		//修改内容
		String content = "";
		if (!StringUtil.isBank(cityName)) {
			cityName = cityName.substring(0, cityName.length()-1);
			City city = cityService.selectByName(cityName);
			if (city == null) {
				return new ApiMessage(400, "'"+cityName+"'城市不存在,保存失败");
			}

			if (!city.getId().equals(venue.getCityid())) {
				content += "城市修改为" + cityName + "。";
			}
			venue.setCityid(city.getId());
		}

		if (!StringUtil.isBank(districtName)) {
			districtName = districtName.substring(0, districtName.length()-1);
			District district = districtService.selectByName(districtName);
			if (district == null) {
				return new ApiMessage(400, "'"+districtName+"'区县不存在,保存失败");
			}

			if (!district.getId().equals(venue.getDistrictid())) {
				content += "区县修改为" + districtName + "。";
			}
			venue.setDistrictid(district.getId());
		}
		if (lng != venue.getLongitude() || lat == venue.getLatitude()) {
			content += "经纬度修改为" + lng + "," + lat;
			venue.setLongitude(lng);
			venue.setLatitude(lat);
		}
		
		if (!showflag.equals(venue.getShowflag())) {
			content += "状态修改为" + (showflag == 1 ? "正常" : "屏蔽") + "。";
		}
		if (!venueName.equals(venue.getName())) {
			content += "场馆名修改为" + venueName + "。";
		}
		if (!owner.equals(venue.getOwner())) {
			content += "联系人修改为" + owner + "。";
		}
		if (!contactPhone.equals(venue.getContactPhone())) {
			content += "联系电话修改为" + contactPhone + "。";
		}
		if (!informPhone.equals(venue.getInformPhone())) {
			content += "通知电话修改为" + informPhone + "。";
		}
		if ((reserveShow ? 1 : 0) != venue.getReserveShow()) {
			content += "订场入口修改为" + (reserveShow ? "开启" : "关闭") + "。";
		}
		if ((reserveSms ? 1 : 0) != venue.getReserveSms()) {
			content += "订场短信修改为" + (reserveSms ? "开启" : "关闭") + "。";
		}
		if ((reservePaySms ? 1 : 0) != venue.getReservePaySms()) {
			content += "订场支付短信修改为" + (reservePaySms ? "开启" : "关闭") + "。";
		}
		if (!image.equals(venue.getImage())) {
			content += "封面修改为" + image + "。";
		}
		if (!address.equals(venue.getAddress())) {
			content += "场馆地址修改为" + address + "。";
		}
		
		if (!StringUtil.isBank(content)) {
			venue.setShowflag(showflag);
			venue.setName(venueName);
			venue.setInformPhone(informPhone);
			venue.setContactPhone(contactPhone);
			venue.setImage(image);
			venue.setOwner(owner);
			venue.setReserveShow(reserveShow ? 1 : 0);
			venue.setReserveSms(reserveSms ? 1 : 0);
			venue.setReservePaySms(reservePaySms ? 1 : 0);
			venue.setAddress(address);
			venueService.updateByPrimaryKeySelective(venue);
			
			operationLogService.saveLog(staff.getId(), "场馆：<" + venue.getVenueno() + ">" + venue.getName() + content, Utils.getIpAddr(request));

			VenueLog venueLog = new VenueLog();
			venueLog.setId(Utils.getUUID());
			venueLog.setCreatetime(new Date());
			venueLog.setVenueid(id);
			venueLog.setContent(staff.getName()+":"+content);
			venueLogService.insertSelective(venueLog);
		}
		
		
		return new ApiMessage(200, "修改成功");
	}

	/**
	 * @Description: 场馆日志数据
	 * @author 宋高俊
	 * @date 2018年8月20日 下午2:19:17
	 */
	@RequestMapping(value = "/venueloglist")
	@ResponseBody
	public AdminMessage venueloglist(String venueid) {
		List<VenueLog> list = venueLogService.selectByVenue(venueid);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueLog venueLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venueLog.getId());// ID
			// map.put("createtime", DateUtil.getFormat(venueLog.getCreatetime()));// 时间
			// map.put("manager", venueLog.getMember().getAppnickname());//操作人
			map.put("content", DateUtil.getFormat(venueLog.getCreatetime()) + " " + venueLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}
	
	/**  
	 * @Description: 场馆日志所有数据
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月15日 下午4:00:48 
	 */ 
	@RequestMapping(value = "/venuelogAllList")
	@ResponseBody
	public AdminMessage venuelogAllList(AdminPage adminPage, Integer selectType,String keyword) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		
		List<VenueLog> list = venueLogService.selectBySearch(selectType, keyword);
		PageInfo<VenueLog> pageInfo = new PageInfo<>(list);
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueLog venueLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venueLog.getId());// ID
			map.put("createtime", DateUtil.getFormat(venueLog.getCreatetime()));// 时间
//			map.put("manager", venueLog.getMember().getAppnickname());// 操作人
			map.put("name", venueLog.getVenue().getName());// 场馆
			map.put("venueno", venueLog.getVenue().getVenueno());// 场馆编号
			map.put("content", venueLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 场馆纠错数据
	 * @author 宋高俊  
	 * @param adminPage
	 * @return 
	 * @date 2018年11月1日 上午10:03:27 
	 */ 
	@RequestMapping(value = "/venueErrorList")
	@ResponseBody
	public AdminMessage venueErrorList(AdminPage adminPage, String venueid) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		
		List<VenueError> list = venueErrorService.selectByVenue(venueid);
		PageInfo<VenueError> pageInfo = new PageInfo<>(list);
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueError venueError = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("createtime", DateUtil.getFormat(venueError.getCreateTime()));// 时间
			map.put("member", venueError.getMember().getAppnickname());// 操作人
			map.put("content", venueError.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 场馆审核所有数据
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月15日 下午4:00:48 
	 */ 
	@RequestMapping(value = "/venueCheckList")
	@ResponseBody
	public AdminMessage venueCheckList(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<VenueCheck> list = venueCheckService.selectByCheck();
		PageInfo<VenueCheck> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueCheck venueCheck = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venueCheck.getId());// ID

			map.put("lng", venueCheck.getLng());// 经度
			map.put("lat", venueCheck.getLat());// 纬度
			
			map.put("createTime", DateUtil.getFormat(venueCheck.getCreateTime()));// 时间
			map.put("trainTeam", venueCheck.getTrainTeam().getTitle());// 上传机构
			map.put("title", venueCheck.getTitle());// 场馆名
			map.put("type", venueCheck.getBallType() == 1 ? "网球场" : venueCheck.getBallType() == 2 ? "足球场" : venueCheck.getBallType() == 3 ? "羽毛球馆" : "篮球馆" );// 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
			map.put("content", venueCheck.getContent());// 描述
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}
	
	/**  
	 * @Description: 场馆审核操作接口
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月15日 下午4:00:48 
	 */ 
	@RequestMapping(value = "/venueCheck")
	@ResponseBody
	public ApiMessage venueCheck(HttpServletRequest request, String id, Integer check) {

		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		// 修改审核状态
		VenueCheck venueCheck = venueCheckService.selectByPrimaryKey(id);
		venueCheck.setCheckFlag(check);
		venueCheck.setModifyTime(new Date());
		venueCheckService.updateByPrimaryKeySelective(venueCheck);
		
		Venue venue = new Venue();
		venue.setId(Utils.getUUID());
		venue.setCreatetime(new Date());
		venue.setModifytime(new Date());
		venue.setName(venueCheck.getTitle());
		venue.setType(venueCheck.getBallType());
		venue.setInformPhone(venueCheck.getPhone());
		venue.setContactPhone(venueCheck.getPhone());
		venue.setImage(venueCheck.getHeadImage());
		venue.setLongitude(venueCheck.getLng());
		venue.setLatitude(venueCheck.getLat());
		venue.setAddress(venueCheck.getAddress());
		venueService.insertSelective(venue);
		
		TrainTeamVenue trainTeamVenue = new TrainTeamVenue();
		trainTeamVenue.setId(Utils.getUUID());
		trainTeamVenue.setTrainVenueId(venue.getId());
		trainTeamVenue.setTrainTeamId(venueCheck.getTrainTeamId());
		trainTeamService.saveTrainTeamVenue(trainTeamVenue);
		
		VenueLog venueLog = new VenueLog();
		venueLog.setId(Utils.getUUID());
		venueLog.setCreatetime(new Date());
		venueLog.setVenueid(venue.getId());
		venueLog.setContent(staff.getName()+"在后台审核场馆数据");
		venueLogService.insertSelective(venueLog);
		return new ApiMessage(200, "审核成功");
	}

	/**  
	 * @Description: 场馆认领入驻
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月17日 下午4:46:55 
	 */ 
	@RequestMapping(value = "/venueEnter/list")
	@ResponseBody
	public AdminMessage trainenter(HttpServletRequest request,AdminPage adminPage, Integer checkFlag) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<VenueEnter> list = venueEnterService.selectByEnterAll(checkFlag);
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			VenueEnter venueEnter = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venueEnter.getId());// ID
			map.put("createTime", DateUtil.getFormat(venueEnter.getCreateTime()));// 申请时间
			map.put("source", venueEnter.getSourceFlag() == 0 ? "移动端" : "短信");// 来源
			if (venueEnter.getSourceFlag() == 0) {
				map.put("appnickname", venueEnter.getMember().getAppnickname());// 申请人
				map.put("phone", venueEnter.getMember().getPhone());// 绑定
			}else {
				map.put("appnickname", venueEnter.getMemberId());// 申请人
			}
			map.put("title", venueEnter.getTitle());// 机构名
			map.put("address", venueEnter.getCityName());// 城市
			map.put("mainName", venueEnter.getMainName());// 负责人
			map.put("mainPhone", venueEnter.getMainPhone());// 负责人电话
			map.put("ballType", venueEnter.getBallType() == 1 ? "网球场" : venueEnter.getBallType() == 1 ? "足球场" : venueEnter.getBallType() == 1 ? "羽毛球场" : "篮球场");// 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
			map.put("checkFlag", venueEnter.getCheckFlag() == 0 ? "待核" : venueEnter.getCheckFlag() == 1 ? "通过" : "无效");// 审核状态0=待审核1=审核通过2=审核拒绝
			map.put("rname", venueEnter.getStaff().getName());// 审核人
			map.put("content", venueEnter.getContent());// 意见
			map.put("checkTime", DateUtil.getFormat(venueEnter.getCheckTime()));// 审核时间
			listMap.add(map);
		}
		return new AdminMessage(0, listMap);
	}
	
	/**  
	 * @Description: 审核场馆入驻
	 * @author 宋高俊  
	 * @param request
	 * @param check
	 * @param content
	 * @param id
	 * @return 
	 * @date 2018年10月17日 下午8:44:16 
	 */ 
	@RequestMapping(value = "/venueEnter/check")
	@ResponseBody
	public ApiMessage venueEnterCheck(HttpServletRequest request,Integer check, String content, String id, String trainTeamId) {
		
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		VenueEnter venueEnter = venueEnterService.selectByPrimaryKey(id);
		if (venueEnter.getSourceFlag() == 1) {
			return new ApiMessage(400, "短信申请无法审核");
		}
		
		venueEnter.setContent(content);
		venueEnter.setCheckFlag(check);
		venueEnter.setCheckStaff(staff.getId());
		venueEnter.setCheckTime(new Date());
		int flag = venueEnterService.updateByPrimaryKeySelective(venueEnter);
		// 场馆申请入驻是否修改成功
		if (flag > 0) {
			if (check == 1) {
				
				Member member = memberService.selectByPrimaryKey(venueEnter.getMemberId());
				// 生成场馆数据
				Venue venue = new Venue();
				venue.setId(Utils.getUUID());
				venue.setCreatetime(new Date());
				venue.setModifytime(new Date());
				venue.setName(venueEnter.getTitle());
				venue.setType(venueEnter.getBallType());
				venue.setInformPhone(venueEnter.getMainPhone());
				venue.setContactPhone(venueEnter.getMainPhone());
				venue.setImage(venueEnter.getHeadImage());
				venue.setLongitude(venueEnter.getLongitude());
				venue.setLatitude(venueEnter.getLatitude());
				venue.setAddress(venueEnter.getAddress());
				venueService.insertSelective(venue);

				VenueLog venueLog = new VenueLog();
				venueLog.setId(Utils.getUUID());
				venueLog.setCreatetime(new Date());
				venueLog.setVenueid(venue.getId());
				venueLog.setContent(staff.getName()+"在后台审核场馆入驻数据");
				venueLogService.insertSelective(venueLog);
				
				TrainCoach lodTrainCoach = trainCoachService.selectByMember(venueEnter.getMemberId());
				TrainTeam trainTeam = new TrainTeam();
				if (lodTrainCoach == null) {
					// 通过审核即创建培训机构
					trainTeam.setId(Utils.getUUID());
					trainTeam.setCreateTime(new Date());
					trainTeam.setModifyTime(new Date());
					trainTeam.setTitle(venueEnter.getTrainTeamName());
					trainTeam.setLongitude(venueEnter.getLongitude());
					trainTeam.setLatitude(venueEnter.getLatitude());
					trainTeam.setTeachClass(venueEnter.getBallType() == 1 ? "网球场" : venueEnter.getBallType() == 1 ? "足球场" : venueEnter.getBallType() == 1 ? "羽毛球场" : "篮球场");
					trainTeam.setPhone(venueEnter.getMainPhone());
					// 获取城市数据
					City city = cityService.selectByName(venueEnter.getCityName());
					trainTeam.setCityId(city.getId());
					trainTeam.setAddress(venueEnter.getAddress());
					trainTeam.setLevel(12);
					trainTeam.setLevelTime(new Date());
					trainTeamService.insertSelective(trainTeam);
					
					// 生成教练数据
					TrainCoach trainCoach = new TrainCoach();
					trainCoach.setId(Utils.getUUID());
					trainCoach.setCreateTime(new Date());
					trainCoach.setModifyTime(new Date());
					trainCoach.setMemberId(venueEnter.getMemberId());
					trainCoach.setName(member.getAppnickname());
					trainCoach.setHeadImage(member.getAppavatarurl());
					trainCoachService.insertSelective(trainCoach);

					// 添加教练身份
					TrainTeamCoach trainTeamCoach = new TrainTeamCoach();
					trainTeamCoach.setId(Utils.getUUID());
					trainTeamCoach.setManager(1);
					trainTeamCoach.setShowFlag(1);
					trainTeamCoach.setTeachType(1);
					trainTeamCoach.setTrainCoachId(trainCoach.getId());
					trainTeamCoach.setTrainTeamId(trainTeam.getId());
					trainTeamCoachService.insertSelective(trainTeamCoach);
					
					venueEnter.setTrainTeamId(trainTeam.getId());
				}
				
				// 生成培训机构管理的场馆数据
				TrainTeamVenue trainTeamVenue = new TrainTeamVenue();
				trainTeamVenue.setId(Utils.getUUID());
				trainTeamVenue.setTrainVenueId(venue.getId());
				trainTeamVenue.setTrainTeamId(venueEnter.getTrainTeamId());
				trainTeamService.saveTrainTeamVenue(trainTeamVenue);
				
			}
			return new ApiMessage(200, "审核成功");
		}
		return new ApiMessage(400, "审核失败");
	}

	/**  
	 * @Description: 模板分析数据
	 * @author 宋高俊  
	 * @param model
	 * @return 
	 * @date 2018年11月2日 下午7:41:16 
	 */ 
	@RequestMapping(value = "/venueAnalyze")
	public String venueAnalyze(Model model) {
		List<City> cities = cityService.selectByAll(null);
		model.addAttribute("cities", cities);
		return "admin/venue/venueAnalyze";
	}
	
	/**  
	 * @Description: 模板分析数据
	 * @author 宋高俊  
	 * @param model
	 * @return 
	 * @date 2018年11月2日 下午7:41:16 
	 */ 
	@RequestMapping(value = "/venueAnalyzeList")
	@ResponseBody
	public ApiMessage venueAnalyzeList(String cityid, Integer sumTemplate, Integer trainAddFlag, Integer ballType) {
		// 由于场馆和模板有主键关联，主键关联的数据即为默认模板
		List<Venue> venueList = venueService.selectByVenueSearch(cityid, sumTemplate, trainAddFlag, ballType);
		// 标题数据
		String[] title = new String[48];
		for (int i = 0; i < 48; i++) {
			title[i] = StringUtil.timeToTimestr((i + 1) + "");
		}
		Integer sumInteger = venueList.size() * 2;
		String[][] values = new String[sumInteger][48];
		Integer sumIndex = 0;
		for (int i = 0; i < venueList.size(); i++) {
			String[] value = new String[5];
			value[0] = venueList.get(i).getCityT().getCity();
			value[1] = venueList.get(i).getName();
			if (StringUtil.isBank(venueList.get(i).getTrainteam())) {
				value[2] = venueList.get(i).getTrainAddFlag() == 0 ? "空" : "添加";
			}else {
				value[2] = "入驻";
			}
			value[3] = venueList.get(i).getType() == 1 ? "网球" : venueList.get(i).getType() == 2 ? "足球" : venueList.get(i).getType() == 3 ? "羽毛球" : "篮球";
			value[4] = venueList.get(i).getSumTemplate().toString();
			values[sumIndex] = value;
			sumIndex++;
			String[] template = venueList.get(i).getVenueTemplate().getPrice().split(",");
			for (int j = 0; j < template.length; j++) {
				if ("-1".equals(template[j])) {
					template[j] = "N";
				}
			}
			values[sumIndex] = template;
			sumIndex++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("values", values);
		return new ApiMessage(200, "", map);
	}
	
	
	/**
	 * @Description: 场馆导入表格
	 * @author 宋高俊  
	 * @param request
	 * @param file
	 * @return 
	 * @date 2018年9月28日 下午8:58:13 
	 */ 
	@RequestMapping("/importExcel")
	@ResponseBody
	public ApiMessage importExcel(HttpServletRequest request, MultipartFile file) {
//		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		
		Map<String, Object> redismap = new HashMap<>();
		int countLine = 0;
		int row = 1;
		logger.info("开始导入场馆execl表格");
		Map<String, Object> map = new HashMap<>();
		List<String> errorlist = new ArrayList<String>();
		List<String> succeedlist = new ArrayList<String>();
		if (file.isEmpty()) {
			logger.info("文件不存在,停止导入");
			return new ApiMessage(400, "导入失败,文件不存在!");
		}
		InputStream in = null;
		List<List<Object>> listob = null;
		try {
			in = file.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		} catch (Exception e) {
			logger.info("文件格式不正确,停止导入!");
			return new ApiMessage(400, "请上传execl文件");
		}
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		if (listob != null && listob.size() > 0) {
			countLine = listob.size();
			for (List<Object> excelList : listob) {
				row++;
				String result = numberFormat.format((float) row / (float) (countLine + 1) * 100);
				redismap.put("current", result);
				redismap.put("page", (row-1)+"/"+countLine);
				RedisUtil.addRedis(Global.REDIS_SESSION_UPLOAD_MAP, request.getSession().getId() + "venue", redismap);

				String cityName = excelList.get(0).toString();
				String districtName = excelList.get(1).toString();
				String type = excelList.get(2).toString();
				String venueName = excelList.get(3).toString();
				String phone = excelList.get(4).toString();
				String lng = excelList.get(5).toString();
				String lat = excelList.get(6).toString();
				String address = excelList.get(7).toString();

				if ("无".equals(type)) {
					errorlist.add("第" + row + "行类型为空");
					continue;
				}
				if ("无".equals(venueName)) {
					errorlist.add("第" + row + "行馆名为空");
					continue;
				}
				if ("无".equals(cityName)) {
					errorlist.add("第" + row + "行城市为空");
					continue;
					
				}
				

				Venue nowVenue = new Venue();
				nowVenue.setId(Utils.getUUID());
				nowVenue.setCreatetime(new Date());
				nowVenue.setModifytime(new Date());

				if (!"无".equals(type)) {
					if ("网球场".equals(type)) {
						nowVenue.setType(1);
					} else if ("足球场".equals(type)) {
						nowVenue.setType(2);
					} else if ("羽毛球馆".equals(type)) {
						nowVenue.setType(3);
					} else if ("篮球场".equals(type)) {
						nowVenue.setType(4);
					}
				}

				// 第一个条件，场馆名是否存在
				City city = cityService.selectByName(cityName);
				if (city == null) {
					city = new City();
					city.setId(Utils.getUUID());
					city.setCity(cityName);
					city.setHotflag(0);
					city.setInitial(ChineseCharacterUtil.getPinYingLetter(cityName));
					city.setVenuesum(0);
					cityService.insertSelective(city);
				}
				nowVenue.setCityid(city.getId()); // 城市ID
				
				Venue oldVenue = venueService.selectByVenueCity(venueName, city.getId());

				if (oldVenue != null) {
					errorlist.add("第" + row + "行场馆已存在");
					continue;
				}
				nowVenue.setName(venueName);// 场馆名称

				// 第二个条件，经纬度是否存在
				String mapGetCity = "";
				String mapGetDistrict = "";
				String mapGetAddress = "";
				if (!"无".equals(lng) && !"无".equals(lat)) {

					nowVenue.setLongitude(Double.parseDouble(lng));// 经度
					nowVenue.setLatitude(Double.parseDouble(lat));// 纬度

					// 使用百度地图根据经纬度获取地址信息
					String jsonString = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + lat + "," + lng
							+ "&output=json&pois=0&ak=" + Global.Baidu_Map_KRY, null);
					if (!StringUtil.isBank(jsonString)) {
						try {
							jsonString = jsonString.replace("renderReverse&&renderReverse(", "");
							jsonString = jsonString.substring(0, jsonString.length() - 1);
							JSONObject jsonObject = JSONObject.fromObject(jsonString);

							mapGetCity = jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("city");
							mapGetDistrict = jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("district");
							mapGetAddress = jsonObject.getJSONObject("result").getString("formatted_address");

							mapGetCity = mapGetCity.substring(0, mapGetCity.length() - 1);
							mapGetDistrict = mapGetDistrict.substring(0, mapGetDistrict.length() - 1);

							nowVenue.setAddress(mapGetAddress);// 场馆地址
						} catch (Exception e) {

						}
					}
				}

				// 第三个条件，城市是否存在
				if (!"".equals(mapGetCity)) {
					cityName = mapGetCity;
				}

				// 第四个条件，区县是否存在
				if (!"".equals(mapGetDistrict)) {
					districtName = mapGetDistrict;
				}
				if (!"无".equals(districtName)) {
					District district = districtService.selectByName(districtName);
					if (district == null) {
						district = new District();
						district.setId(Utils.getUUID());
						district.setDistrict(districtName);
						district.setCityid(city.getId());
						districtService.insertSelective(district);
					}
					nowVenue.setDistrictid(district.getId()); // 区县ID
				}

				nowVenue.setInformPhone(phone);
				nowVenue.setContactPhone(phone);

				// 如果表格中有地址则使用表格地址
				if (!"无".equals(address)) {
					nowVenue.setAddress(address);
				}

				// 保存场馆数据
				try {
					venueService.insertSelective(nowVenue);
					
					// 导入新增默认1号场
					Field field = new Field();
					field.setId(Utils.getUUID());
					field.setCreatetime(new Date());
					field.setModifytime(new Date());
					field.setVenueid(nowVenue.getId());
					field.setName("1号场");
					fieldService.insertSelective(field);
					
					// 导入新增默认系统模板
					VenueTemplate venueTemplate = new VenueTemplate();
					venueTemplate.setId(Utils.getUUID());
					venueTemplate.setCreatetime(new Date());
					venueTemplate.setDefaultflag(1);
					venueTemplate.setModifytime(new Date());
					venueTemplate.setName("系统模板");
					venueTemplate.setPrice("-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1");
					venueTemplate.setVenueid(nowVenue.getId());
					venueTemplateService.insertSelective(venueTemplate);
					
					succeedlist.add("第" + row + "行导入成功");
				} catch (Exception e) {
					errorlist.add("第" + row + "行未知错误");
				}
			}
		} else {
			logger.info("文件没有内容,停止导入!");
			return new ApiMessage(400, "请填写的模板表格内容!");
		}
		map.put("count", countLine);// 总条数
		map.put("succeed", succeedlist.size());// 成功条数
		map.put("error", errorlist.size());// 失败条数

		map.put("succeedlist", succeedlist);// 成功条数
		map.put("errorlist", errorlist);// 失败条数
		return new ApiMessage(200, "导入成功", map);
	}
	
}