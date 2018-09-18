package com.xiaoyi.ssm.service;

import java.util.List;

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

	/**  
	 * @Description: 保存场馆
	 * @author 宋高俊  
	 * @param newvenue
	 * @param token
	 * @return 
	 * @date 2018年9月10日 下午4:19:07 
	 */ 
	ApiMessage saveVenue(Venue newvenue, String token);

	/**  
	 * @Description: 查询常用场馆
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月10日 下午4:19:24 
	 */ 
	List<Venue> selectByOftenMember(String id);
}
