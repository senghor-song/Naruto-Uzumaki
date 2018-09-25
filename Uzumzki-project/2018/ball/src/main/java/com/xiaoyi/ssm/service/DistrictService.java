package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.District;

/**  
 * @Description: 区县业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:42:34 
 */ 
public interface DistrictService extends BaseService<District, String> {

	/**  
	 * @Description: 根据城市名查询区县
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年9月20日 上午11:36:25 
	 */ 
	List<District> selectByCityName(String name);

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
