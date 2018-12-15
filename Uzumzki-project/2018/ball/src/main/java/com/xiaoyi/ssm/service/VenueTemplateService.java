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
	 * @Description: 根据场馆ID查询所有模板
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:02:36 
	 */ 
	List<VenueTemplate> selectByVenue(String venueid);

	/**  
	 * @Description: 根据场馆馆和模板id查询
	 * @author 宋高俊  
	 * @param venueid
	 * @param templateid
	 * @return 
	 * @date 2018年11月2日 上午9:17:32 
	 */ 
	VenueTemplate selectByVenueTemplate(String venueid, String templateid);

	/**
	 * @Description: 模板逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:15:02
	 */
	int updateByVenue(String venueid, String dateStr);

	/**
	 * @Description: 根据场馆ID和日期查询
	 * @author 宋高俊
	 * @param venueid
	 * @param date
	 * @return
	 * @date 2018年11月28日上午10:35:23
	 */
	List<VenueTemplate> selectByVenueDate(String venueid, String date);

}
