package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.AreaMapper;
import com.xiaoyi.ssm.dao.CityLogMapper;
import com.xiaoyi.ssm.dao.CityMapper;
import com.xiaoyi.ssm.dao.DistrictMapper;
import com.xiaoyi.ssm.dto.WebsiteSearchMapDto;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.model.CityLog;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.service.CityService;

/**  
 * @Description: 城市业务逻辑实现类
 * @author 宋高俊  
 * @date 2018年7月14日 下午2:50:42 
 */ 
@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private DistrictMapper districtMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private CityLogMapper cityLogMapper;

	/**  
	 * @Description: 获取所有城市
	 * @author 宋高俊  
	 * @date 2018年7月14日 下午2:49:43 
	 */ 
	@Override
	public List<City> selectAllCity() {
		return cityMapper.selectByAll(null);
	}

	/**
	 * @Description: 根据城市ID获取区县
	 * @author 宋高俊
	 * @date 2018年7月14日 下午2:43:01
	 */
	@Override
	public List<District> selectByCity(String cityId) {
		District district = new District();
		district.setCityid(cityId);
		return districtMapper.selectByAll(district);
	}

	/**
	 * @Description: 根据区县ID获取片区
	 * @author 宋高俊
	 * @date 2018年7月14日 下午2:43:01
	 */
	@Override
	public List<Area> selectByDistrict(String districtId) {
		Area area = new Area();
		area.setDistrictid(districtId);
		return areaMapper.selectByAll(area);
	}

	/**
	 * @Description: 根据城市ID获取片区
	 * @author 宋高俊
	 * @date 2018年7月14日 下午2:43:01
	 */
	@Override
	public List<Area> selectAreaByCity(String cityId) {
		Area area = new Area();
		area.setCityid(cityId);
		return areaMapper.selectByAll(area);
	}

	/**  
	 * @Description: 获取城市数据
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午7:51:11 
	 */ 
	@Override
	public List<City> selectByCityAll() {
		return cityMapper.selectByCityAll();
	}

	/**  
	 * @Description: 根据城市统计区县
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:16:06 
	 */ 
	@Override
	public Integer countDistrictByCity(String cityId) {
		return districtMapper.countDistrictByCity(cityId);
	}

	/**  
	 * @Description: 根据城市统计片区
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:16:06 
	 */ 
	@Override
	public Integer countAreaByCity(String cityId) {
		return areaMapper.countAreaByCity(cityId);
	}

	/**  
	 * @Description: 根据城市统计城市日志
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:30:06 
	 */ 
	@Override
	public Integer countCityLogByCity(String cityId) {
		return cityLogMapper.countCityLogByCity(cityId);
	}

	/**  
	 * @Description: 根据城市ID查询城市日志
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午11:15:39 
	 */ 
	@Override
	public List<CityLog> selectCityLogByCity(String cityId) {
		return cityLogMapper.selectCityLogByCity(cityId);
	}

	/**  
	 * @Description: 根据区县统计片区
	 * @author 宋高俊  
	 * @date 2018年7月27日 下午12:13:20 
	 */ 
	@Override
	public Integer countAreaByDistrict(String districtId) {
		return areaMapper.countAreaByDistrict(districtId);
	}

	/**  
	 * @Description: 根据城市查询所有经纪人平均分
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午9:33:09 
	 */ 
	@Override
	public Double avgEmpScoreByCity(String id) {
		return cityMapper.avgEmpScoreByCity(id);
	}

	@Override
	public City selectByCityName(String name) {
		return cityMapper.selectByCityName(name);
	}

	@Override
	public Area selectByAreaName(String name) {
		return areaMapper.selectByAreaName(name);
	}

	@Override
	public District selectByDistrictName(String name) {
		return districtMapper.selectByDistrictName(name);
	}

	@Override
	public List<District> selectByDistrictAll() {
		return districtMapper.selectByAll(null);
	}

	@Override
	public List<Area> selectByAreaAll() {
		return areaMapper.selectByAll(null);
	}

	@Override
	public List<Area> selectBySearchDistrict(WebsiteSearchMapDto websitePropertyDto) {
		return areaMapper.selectBySearchDistrict(websitePropertyDto);
	}

	@Override
	public List<District> selectBySearchCity(WebsiteSearchMapDto websitePropertyDto) {
		return districtMapper.selectBySearchCity(websitePropertyDto);
	}

}
