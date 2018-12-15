package com.xiaoyi.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamVenue;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueCheck;
import com.xiaoyi.ssm.model.VenueEnter;
import com.xiaoyi.ssm.model.VenueError;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.service.TrainTeamService;
import com.xiaoyi.ssm.service.VenueCheckService;
import com.xiaoyi.ssm.service.VenueEnterService;
import com.xiaoyi.ssm.service.VenueErrorService;
import com.xiaoyi.ssm.service.VenueLogService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.service.VenueTemplateService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.MoblieMessageUtil;
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
	private PermissionService permissionService;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private FieldTemplateService fieldTemplateService;

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
		model.addAttribute("phone", staff.getTel());
		return "admin/venue/list";
	}
	
	/**  
	 * @Description: 导入场馆页面
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月2日 下午2:45:33 
	 */ 
	@RequestMapping(value = "/importView")
	public String importView(Model model) {
		model.addAttribute("date", DateUtil.getFormat(new Date(), "yyMMdd"));
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
			map.put("modifytime", DateUtil.getFormat(venue.getModifytime()));// 场馆编号
			map.put("city", venue.getCityT().getCity());// 城市
			map.put("district", venue.getDistrictT().getDistrict());// 区县
			map.put("name", venue.getName());// 场馆
			if (venue.getType() != null) {
				map.put("type",
						venue.getType() == 1 ? "网球"
								: venue.getType() == 2 ? "足球"
										: venue.getType() == 3 ? "羽毛球" : venue.getType() == 4 ? "篮球" : "无");// 类型
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
		}else {
			District district = districtService.selectByName(cityName);
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
		// 初始化场馆数据
		venueService.initVenue(venue);
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

		return venueService.updateVenue(id, showflag, venueName, contactPhone, informPhone, image, owner, reserveShow,
				reserveSms, reservePaySms, cityName, districtName, address, lng, lat, staff, Utils.getIpAddr(request));
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
		
		venueService.initVenue(venue);
		
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
	@SuppressWarnings("unused")
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

			map.put("lng", venueEnter.getLongitude());// 场馆经度
			map.put("lat", venueEnter.getLatitude());// 场馆纬度
			map.put("userLng", venueEnter.getUserLng());// 用户经度
			map.put("userLat", venueEnter.getUserLat());// 用户纬度
			
			if (venueEnter.getSourceFlag() == 0) {
				map.put("appnickname", venueEnter.getMember().getAppnickname());// 申请人
				map.put("phone", venueEnter.getMember().getPhone());// 操作人手机
			}else {
				map.put("appnickname", venueEnter.getMemberId());// 申请人
			}
			
			Venue venue = venueService.selectByPrimaryKey(venueEnter.getVenueId());
			if (venue != null) {
				map.put("title", venue.getName());// 场馆名
			} else {
				map.put("title", venueEnter.getTitle());// 场馆名
			}
			
			// 判断是已有机构还是新增机构
			if (StringUtil.isBank(venueEnter.getTrainTeamId())) {
				map.put("trainTeamFlag", "创建");// 机构添加
				map.put("trainTeamName", venueEnter.getTrainTeamName());// 机构
			} else {
				map.put("trainTeamFlag", "已有");// 机构添加
				map.put("trainTeamName", venueEnter.getTrainTeamName());// 机构
			}
			
			map.put("cityName", venueEnter.getCityName());// 城市
			map.put("districtName", venueEnter.getDistrictName());// 区县
			map.put("mainName", venueEnter.getMainName());// 负责人
			map.put("mainPhone", venueEnter.getMainPhone());// 负责人电话
			map.put("ballType", venueEnter.getBallType() == 1 ? "网球场" : venueEnter.getBallType() == 1 ? "足球场" : venueEnter.getBallType() == 1 ? "羽毛球场" : "篮球场");// 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
			map.put("checkFlag", venueEnter.getCheckFlag() == 0 ? "待核" : venueEnter.getCheckFlag() == 1 ? "通过" : "无效");// 审核状态0=待审核1=审核通过2=审核拒绝
			map.put("rname", venueEnter.getStaff().getName());// 审核人
			map.put("content", venueEnter.getContent());// 意见
			map.put("checkTime", DateUtil.getFormat(venueEnter.getCheckTime()));// 审核时间

//			map.put("mainFlag", venueEnter.getMainFlag() == 1 ? "负责人" : venueEnter.getMainFlag() == 2 ? "同事" : "业主或其他");// 意见
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
	public ApiMessage venueEnterCheck(HttpServletRequest request, Integer check, String checkContent, String id,
			String contactPhone, String informPhone, String venueAddress, Integer mainFlag, Integer ballSum) {
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		return venueService.venueEnterCheck(staff, check,  checkContent,  id,  contactPhone,  informPhone,  venueAddress,  mainFlag,  ballSum);
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
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		return venueService.importExcel(staff, file, request.getSession().getId(), Utils.getIpAddr(request));
	}

	
	/**  
	 * @Description: 解除入驻机构
	 * @author 宋高俊  
	 * @param model
	 * @return 
	 * @date 2018年11月2日 下午7:41:16 
	 */ 
	@RequestMapping(value = "/relieveVenue")
	@ResponseBody
	public ApiMessage relieveVenue(HttpServletRequest request, String venueid, String phoneCode) {
		
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		if (StringUtil.isBank(venueid)) {
			return new ApiMessage(400, "场馆ID为空");
		}
		

		String code = RedisUtil.getRedis(Global.web_relieve_venue_SmsCode_ + staff.getTel() + venueid);
		if (StringUtil.isBank(code)) {
			return new ApiMessage(400, "验证码不存在或已过期");
		} else {
			if (!phoneCode.equals(code)) {
				return new ApiMessage(400, "验证码不正确");
			}
		}
		
		Venue venue = venueService.relieveVenue(venueid);
		// 初始化场馆数据
		venueService.initVenue(venue);

		return new ApiMessage(200, "解除成功");
	}
	
	/**
	 * @Description: 根据ID获取场馆入驻申请的部分数据
	 * @author 宋高俊
	 * @param request
	 * @param id
	 * @return
	 * @date 2018年12月11日上午9:57:07
	 */
	@RequestMapping(value = "/getVenueEnter")
	@ResponseBody
	public ApiMessage getVenueEnter(HttpServletRequest request, String id) {
		
		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		Map<String, Object> map = new HashMap<>();
		VenueEnter venueEnter = venueEnterService.selectByPrimaryKey(id);
		if (!StringUtil.isBank(venueEnter.getVenueId())) {
			Venue venue = venueService.selectByPrimaryKey(venueEnter.getVenueId());
			if (venue != null) {
				if (!StringUtil.isBank(venue.getTrainteam())) {
					TrainTeam trainTeam = trainTeamService.selectByPrimaryKey(venue.getTrainteam());
					map.put("trainTeamName", trainTeam.getTitle());// 入驻机构
				} else {
					map.put("trainTeamName", "");// 入驻机构
				}
				map.put("contactPhone", venue.getContactPhone());// 场馆订场电话
				map.put("informPhone", venue.getInformPhone());// 订场短信通知
				map.put("venueAddress", venue.getAddress());// 场馆地址
			} else {
				map.put("trainTeamName", "");// 入驻机构
				map.put("contactPhone", "");// 场馆订场电话
				map.put("informPhone", "");// 订场短信通知
				map.put("venueAddress", venueEnter.getAddress());// 场馆地址
			}
		} else {
			map.put("trainTeamName", "");// 入驻机构
			map.put("contactPhone", "");// 场馆订场电话
			map.put("informPhone", "");// 订场短信通知
			map.put("venueAddress", venueEnter.getAddress());// 场馆地址
		}
		map.put("ballSum", venueEnter.getBallSum().toString());// 球场片数
		map.put("checkContent", venueEnter.getContent());// 审核意见
		map.put("mainFlag", venueEnter.getMainFlag());// 联系人关系
		return new ApiMessage(200, "获取成功", map);
	}
	
	
	/**
	 * @Description: 获取解除入驻的验证码
	 * @author 宋高俊
	 * @param phone
	 * @param request
	 * @param venueid
	 * @return
	 * @date 2018年12月12日下午2:44:04
	 */
	@RequestMapping(value = "/getSMSCode")
	@ResponseBody
	public ApiMessage getSMSCode(String phone, HttpServletRequest request, String venueid) {
		String smsCode = Utils.getCode();
		try {
			MoblieMessageUtil.sendIdentifyingCode(phone, smsCode);
			RedisUtil.setRedis(Global.web_relieve_venue_SmsCode_ + phone + venueid, smsCode, 120);
		} catch (Exception e) {
			return ApiMessage.error("发送次数已达上限,请次日使用验证功能");
		}
		return new ApiMessage(200, "短信验证码已发送");
	}
	
	/**
	 * @Description: 判断场馆是否存在
	 * @author 宋高俊
	 * @param venueNo
	 * @return
	 * @date 2018年12月13日下午8:15:06
	 */
	@RequestMapping(value = "/field/flag")
	@ResponseBody
	public ApiMessage fieldFlag(String venueNo) {
		Venue venue = venueService.selectByVenueNo(venueNo);
		if (venue == null) {
			return new ApiMessage(400, "场馆不存在");
		} else {
			return new ApiMessage(200, "");
		}
	}
	
	/**
	 * @Description: 根据场馆编号获取球场列表
	 * @author 宋高俊
	 * @param venueNo
	 * @return
	 * @date 2018年12月13日上午11:56:30
	 */
	@RequestMapping(value = "/field/list")
	@ResponseBody
	public AdminMessage fieldList(String venueNo) {
		Venue venue = venueService.selectByVenueNo(venueNo);
		List<Field> fields = fieldService.selectByVenue(venue.getId());// 场地列表
		List<Map<String , Object>> listMap = new ArrayList<>();
		for (int i = 0; i < fields.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", fields.get(i).getId());// 场地ID 
			map.put("venueNo", venue.getVenueno());// 场馆编号
			map.put("venueName", venue.getName());// 场馆名称
			map.put("fieldName", fields.get(i).getName());// 场地名称
			listMap.add(map);
		}
		return new AdminMessage(fields.size(), listMap);
	}
	
	/**
	 * @Description: 删除场地数据
	 * @author 宋高俊
	 * @param venueNo
	 * @return
	 * @date 2018年12月13日上午11:56:30
	 */
	@RequestMapping(value = "/field/delete")
	@ResponseBody
	public ApiMessage fieldDelete(String fieldId) {
		// 场地数据
		Field field = fieldService.selectByPrimaryKey(fieldId);// 场地
		if (field == null) {
			return new ApiMessage(400, "场地不存在");
		}

		// 当前时间串
		String dateStr = field.getVenueid() + "-" + DateUtil.getFormat(new Date(), "yyyyMMdd-HHmmss");
		field.setVenueid(dateStr);
		fieldService.updateByPrimaryKeySelective(field);
		return new ApiMessage(200, "删除成功");
	}
	
	/**
	 * @Description: 新增场地数据
	 * @author 宋高俊
	 * @param fieldId
	 * @param fieldName
	 * @return
	 * @date 2018年12月13日下午7:33:22
	 */
	@RequestMapping(value = "/field/add")
	@ResponseBody
	public ApiMessage fieldAdd(String venueNo, String fieldName) {
		Venue venue = venueService.selectByVenueNo(venueNo);
		if (venue == null) {
			return new ApiMessage(400, "场馆不存在");
		}
		
		Field oldField = fieldService.selectByVenueName(venue.getId(), fieldName);
		if (oldField != null) {
			return new ApiMessage(400, "该场地已存在");
		}
		
		// 场地数据
		Field field = new Field();// 场地
		field.setId(Utils.getUUID());
		field.setCreatetime(new Date());
		field.setModifytime(new Date());
		field.setDefaultTemplate(venue.getId());
		field.setName(fieldName);
		field.setVenueid(venue.getId());
		int flag = fieldService.insertSelective(field);
		if (flag > 0) {
			// 场地使用模板数据
			FieldTemplate fieldTemplate = new FieldTemplate();
			fieldTemplate.setCreatetime(new Date());
			fieldTemplate.setFieldid(field.getId());
			fieldTemplate.setTemplateid(venue.getId());
			fieldTemplate.setVenueid(venue.getId());

			// 新增七天的模板
			Date nowDate = new Date();
			for (int i = 0; i < 7; i++) {
				fieldTemplate.setId(Utils.getUUID());
				fieldTemplate.setFieldtime(nowDate);
				fieldTemplateService.insertSelective(fieldTemplate);
				nowDate = DateUtil.getPreTime(nowDate, 3, 1);
			}
			return new ApiMessage(200, "新增成功");
		} else {
			return new ApiMessage(400, "新增失败");
		}
	}
	
}