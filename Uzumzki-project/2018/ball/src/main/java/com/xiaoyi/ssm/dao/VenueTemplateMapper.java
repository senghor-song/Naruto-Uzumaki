package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.VenueTemplate;

public interface VenueTemplateMapper extends BaseMapper<VenueTemplate, String>{

	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(@Param("id")String id);
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:02:36 
	 */ 
	List<VenueTemplate> selectByVenue(@Param("venueid")String venueid);
	
	/**  
	 * @Description: 将场馆下的模板都设置成非默认模板
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月15日 下午2:31:32 
	 */ 
	Integer updateNoDefaultVenue(@Param("id")String id);

	/**  
	 * @Description: 根据场馆馆和模板id查询
	 * @author 宋高俊  
	 * @param venueid
	 * @param templateid
	 * @return 
	 * @date 2018年11月2日 上午9:17:32 
	 */ 
	VenueTemplate selectByVenueTemplate(@Param("venueid")String venueid, @Param("templateid")String templateid);
}