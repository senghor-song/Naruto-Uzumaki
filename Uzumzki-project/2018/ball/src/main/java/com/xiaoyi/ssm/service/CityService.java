package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.City;

/**  
 * @Description: 片区业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:42:34 
 */ 
public interface CityService extends BaseService<City, String> {
	
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
	 * @Description: 根据名称查询城市
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年9月25日 上午10:53:56 
	 */ 
	City selectByName(String name);
}
