package com.xiaoyi.ssm.controller.pc;

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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.coreFunction.AnJuKeUtil;
import com.xiaoyi.ssm.coreFunction.FangTianXiaUtil;
import com.xiaoyi.ssm.coreFunction.Tongcheng58Util;
import com.xiaoyi.ssm.dao.AreaMapper;
import com.xiaoyi.ssm.dao.DistrictMapper;
import com.xiaoyi.ssm.dao.EstateImageEmpMapper;
import com.xiaoyi.ssm.dao.HouseTypeImageEmpMapper;
import com.xiaoyi.ssm.dao.PropertyImageEmpMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.HouseEnterDto;
import com.xiaoyi.ssm.dto.MassPropertyDto;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Employee;
import com.xiaoyi.ssm.model.EstateImageEmp;
import com.xiaoyi.ssm.model.HouseTypeImageEmp;
import com.xiaoyi.ssm.model.MassHouse;
import com.xiaoyi.ssm.model.MassProperty;
import com.xiaoyi.ssm.model.PropertyImageEmp;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.EstateEmpService;
import com.xiaoyi.ssm.service.MassPropertyService;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

import net.sf.json.JSONArray;

/**
 * @Description: 首页房源录入信息控制器
 * @author 宋高俊
 * @date 2018年6月28日 上午11:57:54
 */
@Controller
@RequestMapping("/massProperty")
public class MassPropertyController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HouseTypeImageEmpMapper houseTypeImageEmpMapper;
	@Autowired
	private EstateImageEmpMapper estateImageEmpMapper;
	@Autowired
	private PropertyImageEmpMapper propertyImageEmpMapper;
	@Autowired
	private EstateEmpService estateEmpService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private DistrictMapper districtMapper;
	@Autowired
	private MassPropertyService massPropertyService;

	/**
	 * @Description: 房源录入页面
	 * @author 宋高俊
	 * @date 2018年6月30日 下午4:34:29
	 */
	@RequestMapping(value = "/houseEnter")
	public String houseEnter(Model model, HouseEnterDto houseEnterDto, HttpServletRequest request) {
		//当前登录对象
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		model.addAttribute("postType", houseEnterDto.getPostType());
		List<Map<String, Object>> indoorUrls = new ArrayList<Map<String, Object>>();//获取到室内图片文件的列表 
		List<Map<String, Object>> houseTypeUrls = new ArrayList<Map<String, Object>>();//获取到房型图文件的列
		List<Map<String, Object>> estateUrls = new ArrayList<Map<String, Object>>();//获取到小区图片文件的列表 
		MassProperty massProperty = new MassProperty();
		if (houseEnterDto.getHouseId() != null) {
            indoorUrls = estateEmpService.selectByImageUrl(employee.getId(), houseEnterDto.getHouseId(), 1);
			houseTypeUrls = estateEmpService.selectByImageUrl(employee.getId(), houseEnterDto.getHouseId(), 2);
			estateUrls = estateEmpService.selectByImageUrl(employee.getId(), houseEnterDto.getHouseId(), 0);
			massProperty = massPropertyService.selectByPrimaryKey(houseEnterDto.getHouseId());
		}
		model.addAttribute("indoorUrls", indoorUrls);
		model.addAttribute("houseTypeUrls", houseTypeUrls);
		model.addAttribute("estateUrls", estateUrls);
		model.addAttribute("massProperty", massProperty);
		List<District> districts = cityService.selectByCity(employee.getCityid());
		model.addAttribute("districts", districts);
		return "houseEnter/houseEnter";
	}

	/**
	 * @Description: 保存出售录入
	 * @author 宋高俊
	 * @date 2018年6月28上午11:08:48
	 */
	@RequestMapping(value = "/saveHouseEnter")
	public String saveSaleHouseEnter(MassHouse massHouse, MassProperty massProperty, HttpServletRequest request, 
			HouseEnterDto houseEnterDto, MultipartHttpServletRequest imageRequest) {
		//当前登录对象
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		// 图片获取对象
		MultiValueMap<String, MultipartFile> map = imageRequest.getMultiFileMap();
		
		List<MultipartFile> HeadImg = map.get("HeadImg");// 获取到封面图
		List<Map<String, Object>> HeadImgs = Utils.getImageUrls(HeadImg);
		if (HeadImgs.size() > 0) {
			massProperty.setHeadimgpath((String) HeadImgs.get(0).get("url"));
		}	
		massProperty.setAreaname(areaMapper.selectByPrimaryKey(massProperty.getAreaid()).getArea());
		massProperty.setDistrictname(districtMapper.selectByPrimaryKey(massProperty.getDistrictid()).getDistrict());
		//新建时间
		Date newDate = new Date();
		String uuid = "";
		massProperty.setEstateid(houseEnterDto.getCellCode());
		massProperty.setModifytime(newDate);
		//房库类型
		massProperty.setTrade(Integer.valueOf(houseEnterDto.getPostType()));
		//判断是保存还是修改
		if (StringUtils.isBlank(houseEnterDto.getHouseId())) {
			//生成ID
			uuid = Utils.getUUID();
			
			massProperty.setId(uuid);
			massProperty.setEmpid(employee.getId());
			massProperty.setRegdate(newDate);
			massPropertyService.insertSelective(massProperty);
			massHouse.setId(uuid);
			massPropertyService.insertHouse(massHouse, houseEnterDto);
		}else {
			//获取要修改的ID
			uuid = houseEnterDto.getHouseId();
			
			//根据用户删除的图片ID进行删除图片数据
			if(houseEnterDto.getDelIndoorUrls().length > 0){
				propertyImageEmpMapper.deleteByPrimaryKeys(houseEnterDto.getDelIndoorUrls());
			}
			if(houseEnterDto.getDelHouseTypeUrls().length > 0){
				houseTypeImageEmpMapper.deleteByPrimaryKeys(houseEnterDto.getDelHouseTypeUrls());
			}
			if(houseEnterDto.getDelEstateUrls().length > 0){
				estateImageEmpMapper.deleteByPrimaryKeys(houseEnterDto.getDelEstateUrls());
			}
			
			massProperty.setId(uuid);
			massPropertyService.updateByPrimaryKeySelective(massProperty);
			massHouse.setId(uuid);
			massPropertyService.updateHouse(massHouse, houseEnterDto);
			
		}
		
		List<MultipartFile> indoorUrl = map.get("div_IMG_I");// 获取到室内图片文件的列表
		List<MultipartFile> houseTypeUrl = map.get("div_IMG_M");// 获取到房型图文件的列表
		List<MultipartFile> estateUrl = map.get("div_IMG_O");// 获取到小区图片文件的列表
		List<Map<String, Object>> indoorUrls = Utils.getImageUrls(indoorUrl);
		List<Map<String, Object>> houseTypeUrls = Utils.getImageUrls(houseTypeUrl);
		List<Map<String, Object>> estateUrls = Utils.getImageUrls(estateUrl);

		//保存图片
		for (int i = 0; i < indoorUrls.size(); i++) {
			PropertyImageEmp propertyImageEmp = new PropertyImageEmp();
			propertyImageEmp.setId(Utils.getUUID());
			propertyImageEmp.setHouseid(uuid);
			propertyImageEmp.setOperatorid(employee.getId());
			propertyImageEmp.setEstateid(massProperty.getEstateid());
			propertyImageEmp.setEstate(massProperty.getEstate());
			propertyImageEmp.setCountf(massProperty.getCountf());
			propertyImageEmp.setAddtime(newDate);
			propertyImageEmp.setFilename((String) indoorUrls.get(i).get("name"));
			propertyImageEmp.setSize((Integer) indoorUrls.get(i).get("size"));
			propertyImageEmp.setPath((String) indoorUrls.get(i).get("url"));
			propertyImageEmpMapper.insertSelective(propertyImageEmp);
		}
		for (int i = 0; i < houseTypeUrls.size(); i++) {
			HouseTypeImageEmp houseTypeImageEmp = new HouseTypeImageEmp();
			houseTypeImageEmp.setId(Utils.getUUID());
			houseTypeImageEmp.setHouseid(uuid);
			houseTypeImageEmp.setOperatorid(employee.getId());
			houseTypeImageEmp.setEstateid(massProperty.getEstateid());
			houseTypeImageEmp.setEstate(massProperty.getEstate());
			houseTypeImageEmp.setCountf(massProperty.getCountf());
			houseTypeImageEmp.setAddtime(newDate);
			houseTypeImageEmp.setFilename((String) houseTypeUrls.get(i).get("name"));
			houseTypeImageEmp.setSize((Integer) houseTypeUrls.get(i).get("size"));
			houseTypeImageEmp.setPath((String) houseTypeUrls.get(i).get("url"));
			houseTypeImageEmpMapper.insertSelective(houseTypeImageEmp);
		}
		for (int i = 0; i < estateUrls.size(); i++) {
			EstateImageEmp estateImageEmp= new EstateImageEmp();
			estateImageEmp.setId(Utils.getUUID());
			estateImageEmp.setHouseid(uuid);
			estateImageEmp.setOperatorid(employee.getId());
			estateImageEmp.setEstateid(massProperty.getEstateid());
			estateImageEmp.setEstate(massProperty.getEstate());
			estateImageEmp.setCountf(massProperty.getCountf());
			estateImageEmp.setAddtime(newDate);
			estateImageEmp.setFilename((String) estateUrls.get(i).get("name"));
			estateImageEmp.setSize((Integer) estateUrls.get(i).get("size"));
			estateImageEmp.setPath((String) estateUrls.get(i).get("url"));
			estateImageEmpMapper.insertSelective(estateImageEmp);
		}
		return "redirect:/common/returnSucceed";
	}

	/**
	 * @Description: 云群发列表
	 * @author 宋高俊
	 * @date 2018年7月3日 上午9:59:20
	 */
	@RequestMapping(value = "/saleMassList")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saleMassList(Model model, HttpServletRequest request, HouseEnterDto houseEnterDto, PageInfo pageInfo) {
		// 分页查询
		PageHelper.startPage(pageInfo.getPageNum(), 5);
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		houseEnterDto.setEmpId(employee.getId());
		// 统计各类录入数量
		MassPropertyDto massPropertyDto = new MassPropertyDto();
		massPropertyDto.setEmpId(employee.getId());
		massPropertyDto.setPostType(houseEnterDto.getPostType());
		// 查询经纪人房库总数(因加了分页查询故需要分页查询的数据需优先执行,后续查询不会执行分页)
		List list = massPropertyService.selectByEmpAll(houseEnterDto);
		massPropertyDto = massPropertyService.selectCountSum(massPropertyDto);
		pageInfo = new PageInfo<>(list);
		model.addAttribute("page", pageInfo);
		model.addAttribute("houseEnterDto", houseEnterDto);
		model.addAttribute("massPropertyDto", massPropertyDto);
		List<City> citys = new ArrayList<City>();
		citys = cityService.selectAllCity();
		model.addAttribute("citys", citys);
		return "cloudRelease/houseMass/houseMassList";
	}

	/**
	 * @Description: 异步请求云群发列表
	 * @author 宋高俊
	 * @date 2018年7月3日 上午9:59:20
	 */
	@RequestMapping(value = "/ajaxSaleMassList")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String ajaxSaleMassList(Model model, HttpServletRequest request, HouseEnterDto houseEnterDto, PageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), 5);
		Employee employee = (Employee) request.getSession().getAttribute("loginuser");
		houseEnterDto.setEmpId(employee.getId());
		List list = massPropertyService.selectByEmpAll(houseEnterDto);
		pageInfo = new PageInfo<>(list);
		model.addAttribute("page", pageInfo);
		model.addAttribute("houseEnterDto", houseEnterDto);
		return "cloudRelease/houseMass/ajaxMassList";
	}

	/**
	 * @Description: 删除房源
	 * @author 宋高俊
	 * @date 2018年7月3日 下午6:03:13
	 */
	@RequestMapping(value = "/deletehouses")
	@ResponseBody
	public ApiMessage deletehouses(Model model, HouseEnterDto houseEnterDto) {
		int succeed = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		String[] ids = houseEnterDto.getBuildingIds().split(",");
		if (!StringUtils.isBlank(houseEnterDto.getPostType())) {
			MassProperty massPropertySale = new MassProperty();
			if (houseEnterDto.getDeleteType() == 1) {
				// 逻辑删除
				massPropertySale.setDeletedtime(new Date());
				massPropertySale.setFlagdeleted(new Byte("1"));
				massPropertySale.setStatus("回收站");
				for (int i = 0; i < ids.length; i++) {
					massPropertySale.setId(ids[i]);
					succeed += massPropertyService.updateByPrimaryKeySelective(massPropertySale);
				}
			} else if (houseEnterDto.getDeleteType() == 2) {
				// 数据删除
				for (int i = 0; i < ids.length; i++) {
					succeed += massPropertyService.deleteByPrimaryKey(ids[i]);
				}
			} else if (houseEnterDto.getDeleteType() == 3) {
				// 还原数据
				massPropertySale.setFlagdeleted(new Byte("0"));
				massPropertySale.setStatus("发布中");
				for (int i = 0; i < ids.length; i++) {
					massPropertySale.setId(ids[i]);
					succeed += massPropertyService.updateByPrimaryKeySelective(massPropertySale);
				}
			} else if (houseEnterDto.getDeleteType() == 4) {
				// 清空回收站中的数据
				succeed += massPropertyService.deleteAllRecycle(houseEnterDto.getPostType());
			}
			map.put("usedNum", succeed);
			model.addAttribute("postType", houseEnterDto.getPostType());
			return new ApiMessage(200, "删除成功", map);
		} else {
			map.put("usedNum", succeed);
			return new ApiMessage(400, "删除失败", map);
		}
	}

	/**
	 * @Description: 网络房源
	 * @author 宋高俊
	 * @date 2018年7月5日 上午10:45:29
	 */
	@RequestMapping("/publicbuilding")
	public String publicbuilding(Model model) {

		return "houseEnter/publicbuilding";
	}

	/**
	 * @Description: 共享房源
	 * @author 宋高俊
	 * @date 2018年7月5日 上午10:45:29
	 */
	@RequestMapping("/sharebuilding")
	public String sharebuilding(Model model) {

		return "houseEnter/sharebuilding";
	}

	/**
	 * @Description: 房源导入
	 * @author 宋高俊
	 * @date 2018年7月5日 上午10:45:29
	 */
	@RequestMapping("/houseImport")
	public String houseImport(Model model) {

		return "houseEnter/houseImport";
	}
	

	/**
	 * @Description: 房源标签页面
	 * @author 宋高俊
	 * @date 2018年7月5日 上午10:45:29
	 */
	@RequestMapping("/getHouseLabels")
	public String getHouseLabels(Model model, Integer postType) {
		if (postType == 0) {
			return "houseEnter/houseLabels";
		}else if (postType == 1) {
			return "houseEnter/houseLabels";
		}else {
			return "houseEnter/houseLabels";
		}
	}
	
	/**
	 * @Description: 房源标签页面
	 * @author 宋高俊
	 * @date 2018年7月5日 上午10:45:29
	 */
	@RequestMapping("/getCellLables")
	@ResponseBody
	public ApiMessage getCellLables(Model model, Integer postType) {
		String[] strings = new String[]{"得房率高", "双学区", "配套成熟", "带车库", "带花园", "交通便利", "景观房", "黄金楼层", "稀缺户型", "知名物业", "带阁楼", "带露台", "东边户"};
		return ApiMessage.succeed(strings);
	}
	
	/**  
	 * @Description: 获取帮我写标题页面
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午4:30:01 
	 */ 
	@RequestMapping("/getTitleView")
	public String getTitleView(Model model, Integer postType,MassProperty massProperty) {
		return "/houseEnter/titleView";
	}
	
	/**  
	 * @Description: 获取帮我写标题页面数据
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午4:30:01 
	 */ 
	@RequestMapping("/getTitleList")
	@ResponseBody
	public ApiMessage getTitleList(Integer postType,MassProperty massProperty) {
		massProperty.setAreaname(areaMapper.selectByPrimaryKey(massProperty.getAreaid()).getArea());
		massProperty.setDistrictname(districtMapper.selectByPrimaryKey(massProperty.getDistrictid()).getDistrict());
		List<String> list = StringUtil.replaceTitle(massProperty, postType);
		return ApiMessage.succeed(list);
	}
	
	/**  
	 * @Description: 获取帮我写描述页面
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午4:30:01 
	 */ 
	@RequestMapping("/getDescView")
	public String getDescView() {
		return "/houseEnter/descView";
	}
	
	/**  
	 * @Description: 获取帮我写描述页面数据
	 * @author 宋高俊  
	 * @date 2018年7月23日 下午4:30:01 
	 */ 
	@RequestMapping("/getDescList")
	@ResponseBody
	public ApiMessage getDescList(Integer postType,MassProperty massProperty) {
		massProperty.setAreaname(areaMapper.selectByPrimaryKey(massProperty.getAreaid()).getArea());
		massProperty.setDistrictname(districtMapper.selectByPrimaryKey(massProperty.getDistrictid()).getDistrict());
		List<String> list = StringUtil.replaceDesc(massProperty, postType);
		return ApiMessage.succeed(list);
	}
	
	/**
	 * @Description: 用于返回外网小区匹配数据
	 * @author 宋高俊
	 * @param webType =1 安居客=2 58同城=3 房天下
	 * @date 2018年7月24日 下午4:22:23
	 */
	@RequestMapping("/getCellMappings")
	@ResponseBody
	public ApiMessage getCellMappings(String name, String webType) {
		List<Map<String, String>> list = new ArrayList<>();
		if (!StringUtils.isBlank(webType)) {
			if ("1".equals(webType)) {
				JSONArray jsonArray = AnJuKeUtil.getSelectByName(name);
				for (int i = 0; i < jsonArray.size(); i++) {
					Map<String, String> map = new HashMap<>();
					map.put("name", jsonArray.getJSONObject(i).getString("keyword"));
					map.put("address", jsonArray.getJSONObject(i).getString("address"));
					list.add(map);
				}
			}else if ("2".equals(webType)) {
				JSONArray jsonArray = Tongcheng58Util.getSelectByName(name);
				for (int i = 0; i < jsonArray.size(); i++) {
					Map<String, String> map = new HashMap<>();
					map.put("name", jsonArray.getJSONObject(i).getString("k"));
					map.put("address", jsonArray.getJSONObject(i).getString("s"));
					list.add(map);
				}
			}else if ("3".equals(webType)) {
				List<Map> listMaps = FangTianXiaUtil.getSelectByName(name);
				for (int i = 0; i < listMaps.size(); i++) {
					Map<String, String> map = new HashMap<>();
					map.put("name", listMaps.get(i).get("name").toString());
					map.put("address", listMaps.get(i).get("address").toString());
					list.add(map);
				}
			}
		}
		return ApiMessage.succeed(list);
	}
}
