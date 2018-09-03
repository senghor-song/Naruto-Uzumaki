package com.xiaoyi.ssm.controller.website;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.NewsBanner;
import com.xiaoyi.ssm.model.NewsCust;
import com.xiaoyi.ssm.model.PageBean;
import com.xiaoyi.ssm.model.Property;
import com.xiaoyi.ssm.service.NewsBannerService;
import com.xiaoyi.ssm.service.NewsCustService;
import com.xiaoyi.ssm.service.PropertyService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

/**
 * @Description: 首页控制器
 * @author 宋高俊
 * @date 2018年8月15日 上午10:29:21
 */
@Controller("websiteIndexController")
@RequestMapping("/website/index")
public class IndexController {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private NewsBannerService newsBannerService;
	@Autowired
	private NewsCustService newsCustService;

	/**
	 * @Description: 首页banner图
	 * @author 宋高俊
	 * @date 2018年8月15日 下午3:58:35
	 */
	@RequestMapping(value = "/banner", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage banner() {
		NewsBanner newsBanner = new NewsBanner();
		newsBanner.setShowway("官网");
		List<NewsBanner> newsBanners = newsBannerService.selectByAll(newsBanner);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < newsBanners.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("image", newsBanners.get(i).getCoverpath());// 图片地址
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}

	/**
	 * @Description: 首页查询接口
	 * @author 宋高俊
	 * @date 2018年8月15日 上午9:46:09
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage search(HttpServletRequest request, String keyword) {
		return ApiMessage.succeed();
	}

	/**
	 * @Description: 首页房源推荐接口
	 * @author 宋高俊
	 * @date 2018年8月15日 上午9:46:09
	 */
	@RequestMapping(value = "/propertyRecommend", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage propertyRecommend(PageBean pageBean, HttpServletRequest request, Double lng, Double lat, String cityname) {
		City city = (City) RedisUtil.getRedisOne(Global.REDIS_CITY_MAP, cityname);
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());

		List<Property> properties = propertyService.selectByCity(city != null ? city.getId() : null);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < properties.size(); i++) {
			Property property = properties.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("id", property.getId()); // ID
			map.put("image", property.getMassProperty().getHeadimgpath()); // 图片
			map.put("estate", property.getMassProperty().getEstateT().getEstate()); // 小区名
			map.put("price", property.getMassProperty().getPrice()); // 价格
			map.put("room", property.getMassProperty().getCountf() + "房" + property.getMassProperty().getCountt() + "厅"); // 房室
			map.put("squarej", property.getMassProperty().getSquarej()); // 面积
			map.put("floor", property.getMassProperty().getFloor() + "/" + property.getMassProperty().getFloorall()); // 楼层
			String tagString = property.getMassProperty().getHouselabel();
			String[] tag = tagString.split(" ");
			map.put("tag", tag); // 房源标签
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

	/**
	 * @Description: 新闻资讯
	 * @author 宋高俊
	 * @date 2018年8月15日 下午5:33:58
	 */
	@RequestMapping(value = "/newsCust", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage newsCust(PageBean pageBean) {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<NewsCust> newsCusts = newsCustService.selectByAll(null);
		List<Map<String, Object>> listmap = new ArrayList<>();
		for (int i = 0; i < newsCusts.size(); i++) {
			NewsCust newsCust = newsCusts.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("id", newsCust.getId()); // ID
			map.put("title", newsCust.getTitle()); // 标题
			listmap.add(map);
		}
		return ApiMessage.succeed(listmap);
	}
	
	/**
	 * @Description: 新闻资讯详情
	 * @author 宋高俊
	 * @date 2018年8月15日 下午5:33:58
	 */
	@RequestMapping(value = "/newsCust/details", method = RequestMethod.POST)
	@ResponseBody
	public ApiMessage newsCustdetails(String id) {
		NewsCust newsCust = newsCustService.selectByPrimaryKey(id);
		return ApiMessage.succeed(newsCust);
	}
}
