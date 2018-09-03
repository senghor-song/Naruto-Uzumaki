package com.xiaoyi.ssm.controller.website;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.WebsitePropertyDto;
import com.xiaoyi.ssm.dto.WebsiteSearchMapDto;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.model.Property;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.EstateService;
import com.xiaoyi.ssm.service.PropertyService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**
 * @Description: 首页控制器
 * @author 宋高俊
 * @date 2018年8月15日 上午10:29:21
 */
@Controller("websiteSearchController")
@RequestMapping("/website/search")
public class SearchController {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private CityService cityService;
	@Autowired
	private EstateService estateService;

	/**
	 * @Description: 房源区域接口
	 * @author 宋高俊
	 * @date 2018年8月21日 下午2:24:39
	 */
	@RequestMapping(value = "/district", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage district(String cityname) {
		City city = (City) RedisUtil.getRedisOne(Global.REDIS_CITY_MAP, cityname);
		if (city != null) {
			List<District> districts = cityService.selectByCity(city.getId());
			List<Map<String, Object>> listmap = new ArrayList<>();
			for (int i = 0; i < districts.size(); i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("districtid", districts.get(i).getId());// 区域ID
				map.put("district", districts.get(i).getDistrict());// 区域名称
				listmap.add(map);
			}
			return ApiMessage.succeed(listmap);
		} else {
			return ApiMessage.error();
		}
	}

	/**
	 * @Description: 首页房源推荐接口
	 * @author 宋高俊
	 * @date 2018年8月15日 上午9:46:09
	 */
	@RequestMapping(value = "/propertyRecommend", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage propertyRecommend(PageBean pageBean, WebsitePropertyDto websitePropertyDto) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Property> properties = propertyService.selectByWebsitePropertyDto(websitePropertyDto);
		PageInfo pageInfo = new PageInfo<>(properties);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("id", property.getId()); // ID
			map.put("image", property.getMassProperty().getHeadimgpath()); // 图片
			map.put("title", property.getMassProperty().getTitle()); // 标题
			map.put("room", property.getMassProperty().getCountf() + "房" + property.getMassProperty().getCountt() + "厅"
					+ property.getMassProperty().getCountw() + "卫"); // 房室
			map.put("squarej", property.getMassProperty().getSquarej()); // 面积
			if (property.getMassProperty().getFloor() != null && property.getMassProperty().getFloorall() != null) {
				double result = ((float) property.getMassProperty().getFloor()
						/ (float) (property.getMassProperty().getFloorall()) * 100);
				if (result <= 33) {
					map.put("floor", "低层/" + property.getMassProperty().getFloorall() + "层"); // 楼层
				} else if (33 < result && result <= 66) {
					map.put("floor", "中层/" + property.getMassProperty().getFloorall() + "层"); // 楼层
				} else if (result > 66) {
					map.put("floor", "高层/" + property.getMassProperty().getFloorall() + "层"); // 楼层
				} else {
					map.put("floor", "中层/" + property.getMassProperty().getFloorall() + "层"); // 楼层
				}

//				map.put("floor", property.getMassProperty().getFloor() + "层/" + property.getMassProperty().getFloorall() + "层"); // 楼层
			} else {
				map.put("floor", "中层"); // 楼层
			}
			map.put("price", property.getMassProperty().getPrice() + "万"); // 价格
			map.put("estate", property.getMassProperty().getEstateT().getEstate()); // 小区名
			String tagString = property.getMassProperty().getHouselabel();
			String[] tag = tagString.split(" ");
			map.put("tag", tag); // 房源标签

			map.put("store", property.getMassProperty().getEmpStoreT().getEmpstore());// 商户名称
			map.put("emp", property.getMassProperty().getEmployeeT().getEmp());// 经纪人姓名
			listmap.add(map);
		}
		Map<String, Object> returnmap = new HashMap<>();
		returnmap.put("pageTotal", pageInfo.getTotal());
		returnmap.put("pages", pageInfo.getPages());
		returnmap.put("pageNum", pageInfo.getPageNum());
		returnmap.put("pageList", listmap);
		return ApiMessage.succeed(returnmap);
	}

	/**
	 * @Description: 地图找房接口
	 * @author 宋高俊
	 * @date 2018年8月15日 上午11:30:01
	 */
	@RequestMapping(value = "/searchmap", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage searchmap(PageBean pageBean, WebsiteSearchMapDto websitePropertyDto) {
		List<Map<String, Object>> listmap = new ArrayList<>();
		City city = (City) RedisUtil.getRedisOne(Global.REDIS_CITY_MAP, websitePropertyDto.getCity());
		if (websitePropertyDto.getLevel() == 2) {
			// 第二级数据
			List<Area> areas = cityService.selectByDistrict(websitePropertyDto.getId());
			for (int i = 0; i < areas.size(); i++) {
				Area area = areas.get(i);
				Map<String, Object> map = new HashMap<>();
				map.put("id", area.getId());
				map.put("name", area.getArea());
				map.put("count", propertyService.countPropertyByArea(area.getId(), websitePropertyDto.getType()));
				map.put("lng", area.getLongitude());// 经度
				map.put("lat", area.getLatitude());// 纬度
				listmap.add(map);
			}
		} else if (websitePropertyDto.getLevel() == 3) {
			// 第三级数据
			List<Estate> estates = estateService.selectByArea(websitePropertyDto.getId());
			for (int i = 0; i < estates.size(); i++) {
				Estate estate = estates.get(i);
				Map<String, Object> map = new HashMap<>();
				map.put("id", estate.getId());
				map.put("name", estate.getEstate());
				map.put("count", propertyService.countPropertyByEstate(estate.getId(), websitePropertyDto.getType()));
				map.put("lng", estate.getLongitude());// 经度
				map.put("lat", estate.getLatitude());// 纬度
				listmap.add(map);
			}
		} else {
			// 默认进入第一层数据
			if (city == null) {
				city = cityService.selectByCityName(websitePropertyDto.getCity());
				if (city == null) {
					return ApiMessage.succeed(listmap);
				}
			}
			List<District> districts = cityService.selectByCity(city.getId());
			for (int i = 0; i < districts.size(); i++) {
				District district = districts.get(i);
				Map<String, Object> map = new HashMap<>();
				map.put("id", district.getId());
				map.put("name", district.getDistrict());
				map.put("count", propertyService.countPropertyByDistrict(district.getId(), websitePropertyDto.getType()));
				map.put("lng", district.getLongitude());// 经度
				map.put("lat", district.getLatitude());// 纬度
				listmap.add(map);
			}
		}

		return ApiMessage.succeed(listmap);
	}

	/**
	 * @Description: 地图查询房源列表
	 * @author 宋高俊
	 * @date 2018年8月15日 上午9:46:09
	 */
	@RequestMapping(value = "/searchMapProperty", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage searchMapProperty(PageBean pageBean, String estate, String estateid, Integer type) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Property> properties = propertyService.selectByEstate(estate, estateid);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("id", property.getId()); // ID
			map.put("image", property.getMassProperty().getHeadimgpath()); // 图片
			map.put("title", property.getMassProperty().getTitle()); // 标题
			map.put("room", property.getMassProperty().getCountf() + "房" + property.getMassProperty().getCountt() + "厅"
					+ property.getMassProperty().getCountw() + "卫"); // 房室
			map.put("squarej", property.getMassProperty().getSquarej()); // 面积
			map.put("floor",
					property.getMassProperty().getFloor() + "层/" + property.getMassProperty().getFloorall() + "层"); // 楼层
			map.put("price", property.getMassProperty().getPrice() + "万"); // 价格
			map.put("estate", property.getMassProperty().getEstateT().getEstate()); // 小区名
			String tagString = property.getMassProperty().getHouselabel();
			String[] tag = tagString.split(" ");
			map.put("tag", tag); // 房源标签

			map.put("store", property.getMassProperty().getEmpStoreT().getEmpstore());// 商户名称
			map.put("emp", property.getMassProperty().getEmployeeT().getEmp());// 经纪人姓名
			listmap.add(map);
		}
		PageInfo<Property> pageInfo = new PageInfo<>(properties);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("pageTotal", pageInfo.getTotal());
		returnMap.put("pages", pageInfo.getPages());
		returnMap.put("pageNum", pageInfo.getPageNum());
		returnMap.put("pageList", listmap);
		return ApiMessage.succeed(returnMap);
	}

}
