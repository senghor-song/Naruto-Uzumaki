package com.xiaoyi.ssm.quartz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.service.CityService;
import com.xiaoyi.ssm.service.EmpStoreVerifyService;
import com.xiaoyi.ssm.service.EstateFindService;
import com.xiaoyi.ssm.service.ProposalService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;

/**  
 * @Description: 城市定时缓存
 * @author 宋高俊  
 * @date 2018年8月14日 下午3:39:46 
 */ 
public class CityJob {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @date 2018年8月14日 下午3:43:15 
	 */ 
	@SuppressWarnings("rawtypes")
	public void cityJobMap() {
		LOG.info("开始缓存城市数据----------------->");
		CityService cityService = SpringUtils.getBean("cityServiceImpl", CityService.class);
		List<City> citys = cityService.selectByCityAll(); 
		for (Iterator iterator = citys.iterator(); iterator.hasNext();) {
			City city = (City) iterator.next();
			RedisUtil.addRedis(Global.REDIS_CITY_MAP, city.getCity(), city);
		}
		List<District> districts = cityService.selectByDistrictAll();
		for (Iterator iterator = districts.iterator(); iterator.hasNext();) {
			District district = (District) iterator.next();
			RedisUtil.addRedis(Global.REDIS_DISTRICT_MAP, district.getDistrict(), district);
		}
		List<Area> areas = cityService.selectByAreaAll();
		for (Iterator iterator = areas.iterator(); iterator.hasNext();) {
			Area area = (Area) iterator.next();
			RedisUtil.addRedis(Global.REDIS_AREA_MAP, area.getArea(), area);
		}
		LOG.info("缓存城市数据完毕----------------->");
	}
}
