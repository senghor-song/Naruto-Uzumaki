package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.PropertyMapper;
import com.xiaoyi.ssm.dto.WebsitePropertyDto;
import com.xiaoyi.ssm.model.Property;
import com.xiaoyi.ssm.service.PropertyService;

/**  
 * @Description: 房源业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月26日 下午6:28:47 
 */ 
@Service
public class PropertyServiceImpl extends AbstractService<Property,String> implements PropertyService{

	@Autowired
	private PropertyMapper propertyMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(propertyMapper);
	}

	@Override
	public Integer selectByEstateCount(String estateid) {
		return propertyMapper.selectByEstateCount(estateid);
	}
	
	/**  
	 * @Description: 根据经纪人统计房源数量
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午2:31:55 
	 */ 
	@Override
	public Integer countByEmp(String empId) {
		return propertyMapper.countByEmp(empId);
	}

	/**  
	 * @Description: 根据城市查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:01:02 
	 */ 
	@Override
	public Integer countPropertyByCity(String id) {
		return propertyMapper.countPropertyByCity(id);
	}

	/**  
	 * @Description: 根据经纬度范围筛选一遍数据
	 * @author 宋高俊  
	 * @date 2018年8月6日 下午2:33:28 
	 */ 
	@Override
	public List<Property> selectByLngLat(double startlng, double endlng, double startlat, double endlat) {
		return propertyMapper.selectByLngLat(startlng, endlng, startlat, endlat);
	}

	/**  
	 * @Description: 根据官网的检索找房
	 * @author 宋高俊  
	 * @date 2018年8月15日 上午10:54:51 
	 */ 
	@Override
	public List<Property> selectByWebsitePropertyDto(WebsitePropertyDto websitePropertyDto) {
		return propertyMapper.selectByWebsitePropertyDto(websitePropertyDto);
	}
	/**  
	 * @Description: 根据区县查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:21:13 
	 */ 
	@Override
	public Integer countPropertyByDistrict(String id, Integer type) {
		return propertyMapper.countPropertyByDistrict(id, type);
	}

	/**  
	 * @Description: 根据片区查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:21:49 
	 */ 
	@Override
	public Integer countPropertyByArea(String id, Integer type) {
		return propertyMapper.countPropertyByArea(id, type);
	}

	/**  
	 * @Description: 根据小区查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:44:37 
	 */ 
	@Override
	public Integer countPropertyByEstate(String id, Integer type) {
		return propertyMapper.countPropertyByEstate(id, type);
	}
	
	/**  
	 * @Description: 根据小区名和小区ID查询房源
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:51:43 
	 */ 
	@Override
	public List<Property> selectByEstate(String estate, String estateid) {
		return propertyMapper.selectByEstate(estate, estateid);
	}

	/**  
	 * @Description: 根据城市查询房源数据
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:01:02 
	 */ 
	@Override
	public List<Property> selectByCity(String cityid) {
		return propertyMapper.selectByCity(cityid);
	}

}
