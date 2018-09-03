package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.WebsitePropertyDto;
import com.xiaoyi.ssm.model.Property;

/**  
 * @Description: 房源业务逻辑层
 * @author 宋高俊  
 * @date 2018年7月26日 下午6:26:38 
 */ 
public interface PropertyService extends BaseService<Property, String>{
	
	/**  
	 * @Description: 根据小区ID获取数量 
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:27:53 
	 */ 
	Integer selectByEstateCount(String estateid);

	/**  
	 * @Description: 根据经纪人统计房源数量
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午2:31:55 
	 */ 
	Integer countByEmp(String empId);

	/**  
	 * @Description: 根据城市查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:01:02 
	 */ 
	Integer countPropertyByCity(String id);

	/**  
	 * @Description: 根据经纬度范围筛选一遍数据
	 * @author 宋高俊  
	 * @date 2018年8月6日 下午2:33:28 
	 */ 
	List<Property> selectByLngLat(double startlng, double endlng, double startlat, double endlat);

	/**  
	 * @Description: 根据官网的检索找房
	 * @author 宋高俊  
	 * @date 2018年8月15日 上午10:52:30 
	 */ 
	List<Property> selectByWebsitePropertyDto(WebsitePropertyDto websitePropertyDto);
	
	/**  
	 * @Description: 根据区县查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:21:13 
	 */ 
	Integer countPropertyByDistrict(String id, Integer type);
	
	/**  
	 * @Description: 根据片区查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:21:49 
	 */ 
	Integer countPropertyByArea(String id, Integer type);
	
	/**  
	 * @Description: 根据小区查询房源数量
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:21:49 
	 */ 
	Integer countPropertyByEstate(String id, Integer type);

	/**  
	 * @Description: 根据小区名和小区ID查询房源
	 * @author 宋高俊  
	 * @date 2018年8月15日 下午2:51:43 
	 */ 
	List<Property> selectByEstate(String estate, String estateid);

	/**  
	 * @Description: 根据城市查询房源数据
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:01:02 
	 */ 
	List<Property> selectByCity(String cityid);
}
