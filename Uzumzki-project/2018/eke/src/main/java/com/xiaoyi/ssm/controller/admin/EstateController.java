package com.xiaoyi.ssm.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.xiaoyi.ssm.dto.EstateDto;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.model.EstateCorrect;
import com.xiaoyi.ssm.model.EstateImage;
import com.xiaoyi.ssm.model.EstateImageStorage;
import com.xiaoyi.ssm.model.EstateLog;
import com.xiaoyi.ssm.model.EstateMatch;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.model.WebSite;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.EmpStoreEstateService;
import com.xiaoyi.ssm.service.EstateCorrectService;
import com.xiaoyi.ssm.service.EstateImageService;
import com.xiaoyi.ssm.service.EstateImageStorageService;
import com.xiaoyi.ssm.service.EstateLogService;
import com.xiaoyi.ssm.service.EstateMatchService;
import com.xiaoyi.ssm.service.EstateService;
import com.xiaoyi.ssm.service.PropertyService;
import com.xiaoyi.ssm.service.WebSiteService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.ImportExcelUtil;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 小区控制器
 * @author 宋高俊
 * @date 2018年7月25日 下午10:29:24
 */
@Controller(value = "adminEstateController")
@RequestMapping("admin/estate")
public class EstateController {
	private final Logger LOGGER = LoggerFactory.getLogger(EstateController.class);

	@Autowired
	private EstateService estateService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private EstateCorrectService estateCorrectService;
	@Autowired
	private EstateLogService estateLogService;
	@Autowired
	private EstateImageService estateImageService;
	@Autowired
	private EstateImageStorageService estateImageStorageService;
	@Autowired
	private WebSiteService webSiteService;
	@Autowired
	private EstateMatchService estateMatchService;
	@Autowired
	private EmpStoreEstateService empStoreEstateService;
	@Autowired
	private CityService cityService;

	/**
	 * @Description: 小区列表
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/listview")
	public String list(Model model) {
		// 所有网站
		List<WebSite> listWeb = webSiteService.selectByAll(null);
		model.addAttribute("listWeb", listWeb);
		return "admin/estate/list";
	}

	/**
	 * @Description: 小区数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AdminMessage list(Model model, AdminPage adminPage, EstateDto estateDto) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		List<Estate> list = estateService.selectByAll(null);
		PageInfo<Estate> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Estate estate = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", estate.getId());// 小区ID
			map.put("city", estate.getCityT().getCity());// 城市
			map.put("estate", estate.getEstate());// 小区
			if (estate.getLatitude() == 0 && estate.getLongitude() == 0) {
				map.put("longitudeAndLatitude", "无");// 经纬
			} else {
				map.put("longitudeAndLatitude", "有");// 经纬
			}

			map.put("headimg", StringUtils.isBlank(estate.getHeadimg()) ? "否" : "是");// 封面图
			map.put("estateImageCount", estateService.selectByEstateImageCount(estate.getId()) + "");// 展示图
			map.put("imageStorageCount", estateService.selectByImageStorageCount(estate.getId()) + "");// 仓库图
			map.put("propertyCount", propertyService.selectByEstateCount(estate.getId()) + "");// 租售
			map.put("storeCount", estateService.selectByStoreCount(estate.getId()) + "");// 归属商户
			map.put("empStoreEstateCount", empStoreEstateService.countByEstateType(estate.getId()) + "");// 指定商户归属
			map.put("estateCorrectCount", estateCorrectService.selectByEstateCount(estate.getId()));// 信息纠错
			int estateNameNum = 0;
			if (!StringUtils.isBlank(estate.getMatch58())) {
				estateNameNum++;
			}
			if (!StringUtils.isBlank(estate.getMatchan())) {
				estateNameNum++;
			}
			if (!StringUtils.isBlank(estate.getMatchfang())) {
				estateNameNum++;
			}
			map.put("estateName", estateNameNum + "");// 第三方匹配
			map.put("estateLogSum", estateLogService.countLogByEstate(estate.getId()));// 日志
			listMap.add(map);
		}
		return new AdminMessage(pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 小区详情
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:30:02
	 */
	@RequestMapping("/datails")
	public String datails(Model model, String id, String type) {
		Estate estate = estateService.selectByPrimaryKey(id);
		model.addAttribute("estate", estate);
		model.addAttribute("key", Global.Baidu_Map_KRY);
		if ("look".equals(type)) {
			return "admin/estate/look";
		} else {
			List<City> citys = cityService.selectAllCity();
			model.addAttribute("citys", citys);
			return "admin/estate/datails";
		}
	}

	/**
	 * @Description: 新增小区页面
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:30:02
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<City> citys = cityService.selectAllCity();
		model.addAttribute("citys", citys);
		return "admin/estate/add";
	}

	/**
	 * @Description: 保存小区
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:30:02
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ApiMessage save(Model model, Estate estate) {
		estate.setId(Utils.getUUID());
		estate.setModtime(new Date());
		int flag = estateService.insertSelective(estate);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 保存修改小区
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:30:02
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ApiMessage update(Model model, Estate estate) {
		estate.setModtime(new Date());
		int flag = estateService.updateByPrimaryKeySelective(estate);
		if (flag > 0) {
			return ApiMessage.succeed();
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 小区信息纠错表
	 * @author 宋高俊
	 * @date 2018年7月28日 下午2:53:33
	 */
	@RequestMapping("/estateCorrect/list")
	@ResponseBody
	public AdminMessage estateCorrectList(Model model, AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		EstateCorrect ec = new EstateCorrect();
		ec.setEstateid(id);
		List<EstateCorrect> list = estateCorrectService.selectByAll(ec);
		PageInfo<EstateCorrect> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			EstateCorrect estateCorrect = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("estateCorrectId", estateCorrect.getId());// 小区纠错ID
			map.put("createtime", estateCorrect.getCreatetime());// 纠错时间
			map.put("item", estateCorrect.getItem());// 项
			map.put("content", estateCorrect.getContent());// 参考值
			map.put("presenter", estateCorrect.getPresenter());// 纠错人标识
			listMap.add(map);
		}
		return new AdminMessage(1, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 小区信息日志表
	 * @author 宋高俊
	 * @date 2018年7月28日 下午2:53:33
	 */
	@RequestMapping("/estateLog/list")
	@ResponseBody
	public AdminMessage estateLogList(Model model, AdminPage adminPage, String id) {
		PageHelper.startPage(adminPage.getPage(), adminPage.getLimit());
		EstateLog el = new EstateLog();
		el.setEstateid(id);
		List<EstateLog> list = estateLogService.selectByAll(el);
		PageInfo<EstateLog> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			EstateLog estateLog = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("estateLogId", estateLog.getId());// 小区纠错ID
			map.put("logtime", DateUtil.getFormat(estateLog.getLogtime()));// 时间
			map.put("staff", estateLog.getStaffT().getRname());// 操作人
			map.put("content", estateLog.getContent());// 内容
			listMap.add(map);
		}
		return new AdminMessage(100, pageInfo.getTotal(), listMap);
	}

	/**
	 * @Description: 小区图片页面
	 * @author 宋高俊
	 * @date 2018年7月31日 下午5:57:19
	 */
	@RequestMapping("/estateImg")
	public String estateImg(Model model, String id) {
		Estate estate = estateService.selectByPrimaryKey(id);
		model.addAttribute("headimg", estate.getHeadimg());

		// 获取小区展示图
		List<EstateImage> listImage = new ArrayList<>();
		EstateImage estateImage = new EstateImage();
		estateImage.setEstateid(id);
		listImage = estateImageService.selectByAll(estateImage);

		// 获取小区仓库图
		List<EstateImageStorage> listImageStorage = new ArrayList<>();
		EstateImageStorage estateImageStorage = new EstateImageStorage();
		estateImageStorage.setEstate(id);
		listImageStorage = estateImageStorageService.selectByAll(estateImageStorage);

		model.addAttribute("listImage", listImage);
		model.addAttribute("listImageStorage", listImageStorage);
		model.addAttribute("id", id);
		return "admin/estate/estateImg";
	}

	/**
	 * @Description: 保存上传成功后的图片地址
	 * @author 宋高俊
	 * @date 2018年7月31日 下午7:26:39
	 */
	@RequestMapping(value = "/saveImage")
	@ResponseBody
	public ApiMessage saveImage(String id, int type, String url, HttpServletRequest request) {
		Staff staff = (Staff) request.getSession().getAttribute("adminloginuser");
		if (type == 0) {
			// 保存封面图片
			Estate estate = new Estate();
			estate.setId(id);
			estate.setHeadimg(url);
			estateService.updateByPrimaryKeySelective(estate);
		} else if (type == 1) {
			// 保存展示图片
			EstateImage estateImage = new EstateImage();
			estateImage.setId(Utils.getUUID());
			estateImage.setEstateid(id);
			estateImage.setPath(url);
			estateImage.setAddtime(new Date());
			estateImage.setOperatorid(staff.getStaffid());
			estateImageService.insertSelective(estateImage);
		} else if (type == 2) {
			// 保存库存图片
			EstateImageStorage eis = new EstateImageStorage();
			eis.setId(Utils.getUUID());
			eis.setAddtime(new Date());
			eis.setEstate(id);
			eis.setPath(url);
			estateImageStorageService.insertSelective(eis);
		}
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 小区第三方匹配数据
	 * @author 宋高俊
	 * @date 2018年8月1日 下午2:36:44
	 */
	@RequestMapping("/matchEstateNameList")
	@ResponseBody
	public AdminMessage matchEstateNameList(String id) {
		// 根据小区查询网站匹配名
		EstateMatch estateMatch = new EstateMatch();
		estateMatch.setEstateid(id);
		List<EstateMatch> listEstae = estateMatchService.selectByAll(estateMatch);
		List<Map<String, Object>> listMap = new ArrayList<>();
		for (int i = 0; i < listEstae.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", listEstae.get(i).getId());// id
			map.put("matchno", listEstae.get(i).getMatchno());// 编号
			map.put("web", listEstae.get(i).getWebSite().getWebname());// 网站名
			map.put("name", listEstae.get(i).getName());// 匹配值
			listMap.add(map);
		}
		return new AdminMessage(100, listEstae.size(), listMap, 1);
	}

	/**
	 * @Description: 新增网站第三方匹配值
	 * @author 宋高俊
	 * @date 2018年8月1日 下午2:36:44
	 */
	@RequestMapping("/addEstateMacth")
	@ResponseBody
	public ApiMessage addEstateMacth(String estateId, String webId, String name) {
		EstateMatch oldEstateMatch = estateMatchService.selectByEstateWeb(estateId, webId);
		// 判断是否已为该网站添加匹配值
		if (oldEstateMatch == null) {
			EstateMatch estateMatch = new EstateMatch();
			estateMatch.setId(Utils.getUUID());
			estateMatch.setCreatetime(new Date());
			estateMatch.setName(name);
			estateMatch.setWebid(webId);
			estateMatch.setEstateid(estateId);
			int flag = estateMatchService.insertSelective(estateMatch);
			if (flag > 0) {
				return new ApiMessage(200, "添加成功");
			} else {
				return new ApiMessage(400, "添加失败");
			}
		} else {
			return new ApiMessage(400, "添加失败,该网站已添加匹配值,如需添加新的匹配值,请删除旧匹配值");
		}
	}

	/**
	 * @Description: 删除网站第三方匹配值
	 * @author 宋高俊
	 * @date 2018年8月1日 下午2:36:44
	 */
	@RequestMapping("/delEstateMacth")
	@ResponseBody
	public ApiMessage delEstateMacth(String id) {
		int flag = estateMatchService.deleteByPrimaryKey(id);
		if (flag > 0) {
			return new ApiMessage(200, "删除成功");
		} else {
			return new ApiMessage(400, "删除失败");
		}
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
		Date date = new Date();// 导入表格时间
		int row = 1;
		LOGGER.info("开始导入重点人员execl表格");
		Map<String, Object> map = new HashMap<>();
		List<String> errorlist = new ArrayList<String>();
		List<String> succeedlist = new ArrayList<String>();
		if (file.isEmpty()) {
			LOGGER.info("文件不存在,停止导入");
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
			LOGGER.info("文件格式不正确,停止导入!");
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
				RedisUtil.addRedis(Global.REDIS_SESSION_UPLOAD_MAP, request.getSession().getId() + "estate", redismap);
				if ("无".equals(excelList.get(3).toString())) {
					errorlist.add("第" + row + "行小区名为空");
					continue;
				}
				//小区是否已存在,已存在则导入下一条数据
				Estate oldEstate = estateService.selectByEstateName(excelList.get(3).toString());
				if (oldEstate != null) {
					errorlist.add("第" + row + "行小区已存在");
					continue;
				}
				Estate estate = new Estate();
				estate.setId(Utils.getUUID());
				estate.setModtime(date);
				City city = cityService.selectByCityName(excelList.get(0).toString());
				District district = cityService.selectByDistrictName(excelList.get(1).toString());
				Area area = cityService.selectByAreaName(excelList.get(2).toString());
				//判断城市是否存在
				if (city != null) {
					estate.setCityid(city.getId());
				}
				//判断区县是否存在
				if (district != null) {
					estate.setDistrictid(district.getId());
				}
				//判断片区是否存在
				if (area != null) {
					estate.setAreaid(area.getId());
				}
				estate.setEstate(excelList.get(3).toString());
				estate.setEstateno(excelList.get(4).toString());
				if (!"无".equals(excelList.get(5).toString())) {
					estate.setLatitude(Double.parseDouble(excelList.get(5).toString()));
				}
				if (!"无".equals(excelList.get(6).toString())) {
					estate.setLongitude(Double.parseDouble(excelList.get(6).toString()));
				}
				estate.setMoredescribe(excelList.get(7).toString());
				estate.setMorecompleteyear(excelList.get(8).toString());
				estate.setMoreparking(excelList.get(9).toString());
				estate.setMoredeveloper(excelList.get(12).toString());
				estate.setMoremgt(excelList.get(13).toString());

				if (!"无".equals(excelList.get(15).toString())) {
					estate.setMoreroom(Integer.valueOf(excelList.get(15).toString()));
				}
				for (int i = 19; i < 30; i++) {
					if ("无".equals(excelList.get(i).toString())) {
						break;
					}
					EstateImage estateImage = new EstateImage();
					estateImage.setId(Utils.getUUID());
					estateImage.setEstateid(estate.getId());
					estateImage.setAddtime(date);
					estateImage.setOperatorid(staff.getStaffid());
					estateImage.setPath(excelList.get(i).toString());
				}
				// 保存楼讯数据
				try {
					estateService.insertSelective(estate);
					succeedlist.add("第" + row + "行导入成功");
				} catch (Exception e) {
					errorlist.add("第" + row + "行未知错误");
				}
			}
		} else {
			LOGGER.info("文件没有内容,停止导入!");
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
