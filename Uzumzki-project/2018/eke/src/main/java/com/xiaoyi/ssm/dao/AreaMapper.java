package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.Area;

/**  
 * @Description: 片区数据访问接口
 * @author 宋高俊  
 * @date 2018年7月27日 上午9:14:26 
 */ 
public interface AreaMapper extends BaseMapper<Area, String> {

	/**  
	 * @Description: 根据城市统计片区
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:13:45 
	 */ 
	Integer countAreaByCity(String cityId);

	/**  
	 * @Description: 根据区县统计片区
	 * @author 宋高俊  
	 * @date 2018年7月27日 下午12:13:20 
	 */ 
	Integer countAreaByDistrict(String districtId);
	
	/**  
	 * @Description: 根据片区查询片区
	 * @author 宋高俊  
	 * @date 2018年8月13日 下午3:35:10 
	 */ 
	Area selectByAreaName(String name);
}