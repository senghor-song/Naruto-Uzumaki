package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.WebsiteSearchMapDto;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.CityLog;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Estate;

/**
 * @Description: 城市业务逻辑接口
 * @author 宋高俊
 * @date 2018年7月14日 下午2:42:12
 */
public interface CityService {
	
	/**  
	 * @Description: 获取所有城市
	 * @author 宋高俊  
	 * @date 2018年7月14日 下午2:49:43 
	 */ 
	List<City> selectAllCity();

	/**
	 * @Description: 根据城市ID获取区县
	 * @author 宋高俊
	 * @date 2018年7月14日 下午2:43:01
	 */
	List<District> selectByCity(String cityId);

	/**
	 * @Description: 根据区县ID获取片区
	 * @author 宋高俊
	 * @date 2018年7月14日 下午2:43:01
	 */
	List<Area> selectByDistrict(String districtId);
	

	/**
	 * @Description: 根据城市ID获取片区
	 * @author 宋高俊
	 * @date 2018年7月14日 下午2:43:01
	 */
	List<Area> selectAreaByCity(String cityId);

	/**  
	 * @Description: 获取城市数据
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午7:49:54 
	 */ 
	List<City> selectByCityAll();
	
	/**  
	 * @Description: 获取所有区县数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 下午3:52:00 
	 */ 
	List<District> selectByDistrictAll();
	
	/**  
	 * @Description: 获取所有片区数据
	 * @author 宋高俊  
	 * @date 2018年8月14日 下午3:52:13 
	 */ 
	List<Area> selectByAreaAll();
	
	/**  
	 * @Description: 根据城市统计区县
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:16:06 
	 */ 
	Integer countDistrictByCity(String cityId);
	
	/**  
	 * @Description: 根据城市统计片区
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:16:23 
	 */ 
	Integer countAreaByCity(String cityId);

	/**  
	 * @Description: 根据区县统计片区
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:16:23 
	 */ 
	Integer countAreaByDistrict(String districtId);
	
	/**  
	 * @Description: 根据城市统计城市日志
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:29:16 
	 */ 
	Integer countCityLogByCity(String cityId);
	
	/**  
	 * @Description: 根据城市ID查询城市日志
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午11:14:50 
	 */ 
	List<CityLog> selectCityLogByCity(String cityId);

	/**  
	 * @Description: 根据城市查询所有经纪人平均分
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午9:32:18 
	 */ 
	Double avgEmpScoreByCity(String id);
	
	/**  
	 * @Description: 根据城市查询城市
	 * @author 宋高俊  
	 * @date 2018年8月13日 下午3:35:10 
	 */ 
	City selectByCityName(String name);
	
	/**  
	 * @Description: 根据片区查询片区
	 * @author 宋高俊  
	 * @date 2018年8月13日 下午3:35:10 
	 */ 
	Area selectByAreaName(String name);
	
	/**  
	 * @Description: 根据区县查询区县
	 * @author 宋高俊  
	 * @date 2018年8月13日 下午3:35:10 
	 */ 
	District selectByDistrictName(String name);

	/**  
	 * @Description: 根据经纬度查询片区
	 * @author 宋高俊  
	 * @param websitePropertyDto
	 * @return 
	 * @date 2018年9月6日 上午9:41:18 
	 */ 
	List<Area> selectBySearchDistrict(WebsiteSearchMapDto websitePropertyDto);

	/**  
	 * @Description: 根据经纬度查询区县
	 * @author 宋高俊  
	 * @param websitePropertyDto
	 * @return 
	 * @date 2018年9月6日 上午9:41:18 
	 */ 
	List<District> selectBySearchCity(WebsiteSearchMapDto websitePropertyDto);

	
}
