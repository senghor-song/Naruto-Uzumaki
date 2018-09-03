package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.District;

/**  
 * @Description: 区县数据访问接口
 * @author 宋高俊  
 * @date 2018年6月30日 上午10:56:22 
 */ 
public interface DistrictMapper extends BaseMapper<District, String>{
	
	/**  
	 * @Description: 根据城市统计区县
	 * @author 宋高俊  
	 * @date 2018年7月27日 上午9:13:45 
	 */ 
	Integer countDistrictByCity(String cityId);
	
	/**  
	 * @Description: 根据区县查询区县
	 * @author 宋高俊  
	 * @date 2018年8月13日 下午3:35:10 
	 */ 
	District selectByDistrictName(String name);
}