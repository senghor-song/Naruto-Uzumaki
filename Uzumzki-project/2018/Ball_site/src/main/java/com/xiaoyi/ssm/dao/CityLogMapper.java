package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.CityLog;

public interface CityLogMapper extends BaseMapper<CityLog, String>{

	/**  
	 * @Description: 根据城市ID统计日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月21日 下午5:15:36 
	 */ 
	Integer countByCity(String id);
	
	/**  
	 * @Description: 根据城市ID查询日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月21日 下午5:15:36 
	 */ 
	List<CityLog> selectByCity(String id);

}