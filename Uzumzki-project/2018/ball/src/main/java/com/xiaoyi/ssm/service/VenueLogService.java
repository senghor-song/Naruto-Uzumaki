package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.VenueLog;

/**  
 * @Description: 场馆日志业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午5:59:40 
 */ 
public interface VenueLogService extends BaseService<VenueLog, String> {
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);

	/**  
	 * @Description: 根据场馆ID获取日志数据
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午3:03:51 
	 */ 
	List<VenueLog> selectByVenue(String venueid);

	/**  
	 * @Description: 条件查询场馆日志数据
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月15日 下午3:12:12 
	 */ 
	List<VenueLog> selectBySearch(Integer selectType, String keyword);

	/**
	 * @Description: 保存日志数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年12月6日下午2:33:45
	 */
	int saveLog(String venueid, String managerid, String content);
}
