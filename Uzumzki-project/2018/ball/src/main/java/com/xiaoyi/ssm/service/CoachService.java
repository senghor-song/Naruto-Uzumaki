package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.Coach;

/**  
 * @Description: 教练业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月17日 下午5:03:20 
 */ 
public interface CoachService extends BaseService<Coach, String> {

	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);

}
