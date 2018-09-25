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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.AdminMessage;
import com.xiaoyi.ssm.dto.AdminPage;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.CoachService;
import com.xiaoyi.ssm.service.DistrictService;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.ManagerService;
import com.xiaoyi.ssm.service.TrainService;
import com.xiaoyi.ssm.service.VenueLogService;
import com.xiaoyi.ssm.service.VenueService;
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
	private CoachService coachService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private VenueTemplateService venueTemplateService;
	@Autowired
	private VenueLogService venueLogService;
	@Autowired
	private TrainService trainService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;

	/**
	 * @Description: 场馆页面
	 * @author song
	 * @date 2018年8月14日 下午7:02:41
	 */
	@RequestMapping(value = "/listview")
	public String listview() {
		return "admin/venue/list";
	}

	/**
	 * @Description: 场馆数据
	 * @author song
	 * @date 2018年8月14日 下午7:04:09
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public AdminMessage list(AdminPage adminPage) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Venue> venues = venueService.selectByAll(null);
		PageInfo<Venue> pageInfo = new PageInfo<>(venues);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < venues.size(); i++) {
			Venue venue = venues.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", venue.getId());// ID
			map.put("city", venue.getCityT().getCity());// 城市
			map.put("district", venue.getDistrictT().getDistrict());// 区县
			map.put("name", venue.getName());// 场馆
			map.put("fieldSum", fieldService.countByVenue(venue.getId()));// 场地
			map.put("coachSum", coachService.countByVenue(venue.getId()));// 教练
			map.put("managerSum", managerService.countByVenue(venue.getId()));// 管理员
			map.put("venueTemplateSum", venueTemplateService.countByVenue(venue.getId()));// 模板
			map.put("venuelogSum", venueLogService.countByVenue(venue.getId()));// 日志
			map.put("amount", venue.getAmount());// 累计金额
			map.put("balance", venue.getBalance());// 存余金额
			map.put("freezeamount", venue.getFreezeamount());// 存余金额
			map.put("trainSum", trainService.countByVenue(venue.getId()));// 培训课程
			if (venue.getType() != null) {
				map.put("type", venue.getType() == 1 ? "网球场" : venue.getType() == 2 ? "足球场" : venue.getType() == 3 ? "羽毛球馆" : venue.getType() == 4 ? "篮球场"
						: "无");// 培训课程
			} else {
				map.put("type", "无");
			}
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 管理员数据
	 * @author 宋高俊
	 * @date 2018年8月20日 下午2:19:17
	 */
	@RequestMapping(value = "/managerlist")
	@ResponseBody
	public AdminMessage managerlist(AdminPage adminPage, String venueid) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Manager> managers = managerService.selectByVenue(venueid);
		PageInfo<Manager> pageInfo = new PageInfo<>(managers);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < managers.size(); i++) {
			Manager manager = managers.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", manager.getId());// ID
			map.put("createtime", DateUtil.getFormat(manager.getCreatetime()));// 创建时间
			map.put("name", manager.getName());// 姓名
			map.put("realname", manager.getRealname() == 0 ? "否" : "是");// 实名
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
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
			map.put("createtime", DateUtil.getFormat(venueLog.getCreatetime()));// 时间
			map.put("manager", venueLog.getManager().getName());// 操作人
			map.put("content", venueLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, list.size(), listMap);
	}

	/**
	 * @Description: 楼讯导入表格
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/importExcel")
	@ResponseBody
	public ApiMessage importExcel(HttpServletRequest request, MultipartFile file, Model model, AdminPage adminPage) {
		Staff staff = (Staff) request.getSession().getAttribute("adminloginuser");
		
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
				RedisUtil.addRedis(Global.REDIS_SESSION_UPLOAD_MAP, staff.getStaffid() + "venue", redismap);

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
				Venue oldVenue = venueService.selectByVenueName(venueName);

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
				if (!"无".equals(cityName)) {
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
				}

				nowVenue.setTel(phone);

				// 如果表格中有地址则使用表格地址
				if (!"无".equals(address)) {
					nowVenue.setAddress(address);
				}

				// 保存场馆数据
				try {
					venueService.insertSelective(nowVenue);
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
