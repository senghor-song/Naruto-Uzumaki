package com.xiaoyi.ssm.service;

import java.util.Date;
import java.util.List;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.VenueTemplate;

/**  
 * @Description: 场馆业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午5:59:40 
 */ 
public interface FieldTemplateService extends BaseService<FieldTemplate, String> {

	/**  
	 * @Description: 根据场馆和场地ID获取场地当天使用模板
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午8:17:21 
	 */ 
	FieldTemplate selectByVenueAndField(FieldTemplateDto ftd);

	/**
	 * @Description: 修改使用旧模板为使用新模板
	 * @author 宋高俊
	 * @param oldId
	 * @param nowId
	 * @return
	 * @date 2018年11月19日 下午3:57:35
	 */
	int updateByTemplate(String oldId, String nowId);

	/**
	 * @Description: 场地使用模板逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:42:17
	 */
	int updateByVenue(String venueid, String dateStr);

	/**
	 * @Description: 根据场馆和场地ID获取场地所有使用模板
	 * @author 宋高俊
	 * @param id
	 * @param startDate
	 * @param endDate
	 * @param fieldid
	 * @return
	 * @date 2018年12月14日下午8:44:35
	 */
	List<FieldTemplate> selectByNowDate(String id, Date startDate, Date endDate, String fieldid);

	/**
	 * @Description: 选配模板
	 * @author 宋高俊
	 * @param venueid
	 * @param venueTemplate
	 * @param date
	 * @param fieldid
	 * @return
	 * @date 2018年12月15日上午11:28:56
	 */
	ApiMessage saveFieldTemplate(String venueid, VenueTemplate venueTemplate, String date, String fieldid);

}
