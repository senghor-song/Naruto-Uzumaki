package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Venue;

/**  
 * @Description: 场馆业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午5:59:40 
 */ 
public interface VenueService extends BaseService<Venue, String> {

	/**  
	 * @Description: 根据管理员获取所管理的场馆
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:41:07 
	 */ 
	Venue selectByManager(String managerid);

	ApiMessage saveVenue(Venue newvenue, String token);
}
