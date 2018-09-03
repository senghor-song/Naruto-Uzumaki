package com.xiaoyi.ssm.controller.api.cust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.NewsBanner;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.model.Property;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.NewsBannerService;
import com.xiaoyi.ssm.service.PropertyService;

/**
 * @Description: 客户端首页控制器
 * @author 宋高俊
 * @date 2018年7月18日 下午4:05:38
 */
@Controller
@RequestMapping("api/cust/index")
public class ApiIndexController {

	@Autowired
	private CityService cityService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private NewsBannerService newsBannerService;

	/**
	 * @Description: 消息数据
	 * @author 宋高俊
	 * @date 2018年7月25日 下午10:29:51
	 */
	@RequestMapping("/city/list")
	@ResponseBody
	public ApiMessage citylist(Model model, PageBean pageBean) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<City> list = cityService.selectAllCity();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			City city = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", city.getId());// 城市ID
			map.put("name", city.getCity());// 城市名称
			map.put("propertySum", propertyService.countPropertyByCity(city.getId()));// 房源数量
			map.put("townSum", "0");// 新盘数量
			listMap.add(map);
		}
		return ApiMessage.succeed(listMap);
	}

	/**
	 * @Description: 首页背景轮播图片
	 * @author 宋高俊
	 * @date 2018年8月4日 下午12:04:09
	 */
	@RequestMapping("/banner/list")
	@ResponseBody
	public ApiMessage bannerlist() {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<NewsBanner> list = new ArrayList<>();
		list = newsBannerService.selectCustByShow();
		for (int i = 0; i < list.size(); i++) {
			NewsBanner newsBanner = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", newsBanner.getId());// ID
			map.put("coverpath", newsBanner.getCoverpath());// 图片地址
			map.put("contentpath", newsBanner.getContentpath());// 内容地址
			listMap.add(map);
		}
		return ApiMessage.succeed(listMap);
	}

	/**
	 * @Description: 附件推荐列表
	 * @author 宋高俊
	 * @date 2018年8月6日 上午10:16:28
	 */
	@RequestMapping("/nearby/property/list")
	@ResponseBody
	public ApiMessage nearbyPropertyList(double lng, double lat, int toDistance, PageBean pageBean) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		double startlng = lng - 0.06;
		double endlng = lng + 0.06;
		double startlat = lat - 0.06;
		double endlat = lat + 0.06;

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<Property> list = propertyService.selectByLngLat(startlng, endlng, startlat, endlat);
		for (int i = 0; i < list.size(); i++) {
			Property property = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", property.getId());// ID
			map.put("headimgpath", property.getMassProperty().getHeadimgpath());// 封面图
			map.put("estate", property.getMassProperty().getEstate());// 小区名
			map.put("squarej", property.getMassProperty().getSquarej());// 面积
			map.put("direction", property.getMassProperty().getDirection());// 朝向
			if (property.getMassProperty().getEstateT() != null) {
				map.put("area", property.getMassProperty().getEstateT().getAreaT().getArea());// 地址
			} else {
				map.put("area", "未知");// 地址
			}
			listMap.add(map);
		}
		return ApiMessage.succeed(listMap);
	}
}
