package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.VenueTemplate;

/**  
 * @Description: 场馆模板业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午5:59:40 
 */ 
public interface VenueTemplateService extends BaseService<VenueTemplate, String> {

	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);

	/**  
	 * @Description: 根据管理员ID和场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:02:36 
	 */ 
	List<VenueTemplate> selectByVenue(String venueid);

}
