package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.District;

public interface DistrictMapper extends BaseMapper<District, String>{

	/**  
	 * @Description: 根据城市名查询区县
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年9月20日 上午11:36:25 
	 */ 
	List<District> selectByCityName(@Param("cityname")String cityname);

	/**  
	 * @Description: 根据城市ID查询区县
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月21日 下午4:34:39 
	 */ 
	List<District> selectByCityId(String id);

	/**  
	 * @Description: 根据区县名查询区县
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年9月25日 上午11:45:42 
	 */ 
	District selectByName(String name);
}