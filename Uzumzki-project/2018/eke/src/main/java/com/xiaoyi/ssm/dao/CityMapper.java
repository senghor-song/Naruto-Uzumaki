package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.City;

public interface CityMapper extends BaseMapper<City, String> {

	/**  
	 * @Description: 获取城市数据
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午7:49:54 
	 */ 
	List<City> selectByCityAll();

	/**  
	 * @Description: 根据城市查询所有经纪人平均分
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午9:33:30 
	 */ 
	Double avgEmpScoreByCity(String id);
	
	/**  
	 * @Description: 根据城市查询城市
	 * @author 宋高俊  
	 * @date 2018年8月13日 下午3:35:10 
	 */ 
	City selectByCityName(String name);
}