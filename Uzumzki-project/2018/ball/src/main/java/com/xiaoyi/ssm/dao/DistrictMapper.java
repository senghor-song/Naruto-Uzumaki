package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.District;

public interface DistrictMapper extends BaseMapper<District, String>{

	/**  
	 * @Description: 根据城市名查询区县
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年9月20日 上午11:36:25 
	 */ 
	List<District> selectByCityName(String name);
}