package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.City;

public interface CityMapper extends BaseMapper<City, String> {
	
	/**  
	 * @Description: 根据城市首字母排序查询数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月20日 上午10:52:27 
	 */ 
	List<City> selectByInitial();

	/**  
	 * @Description: 查询五个热门城市
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月21日 下午7:50:53 
	 */ 
	List<City> selectByHotCity();

	/**  
	 * @Description: 根据城市名查询城市
	 * @author 宋高俊  
	 * @param cityName
	 * @return 
	 * @date 2018年10月8日 上午10:00:01 
	 */ 
	City selectByName(@Param("cityName")String cityName);
}