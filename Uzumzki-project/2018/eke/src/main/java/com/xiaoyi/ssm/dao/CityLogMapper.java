package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.CityLog;

/**  
 * @Description: 城市日志数据访问层
 * @author 宋高俊  
 * @date 2018年7月27日 上午9:28:24 
 */ 
public interface CityLogMapper extends BaseMapper<CityLog, String>{
	
	/**  
	 * @Description: 根据城市统计日志
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:28:35 
	 */ 
	Integer countCityLogByCity(String cityId);

	/**  
	 * @Description: 根据城市ID查询城市日志
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午11:15:59 
	 */ 
	List<CityLog> selectCityLogByCity(String cityId);
}