package com.xiaoyi.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.FieldTemplate;

public interface FieldTemplateMapper extends BaseMapper<FieldTemplate, String>{
	
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
	int updateByTemplate(@Param("oldId")String oldId, @Param("nowId")String nowId);

	/**
	 * @Description: 场地使用模板逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:42:17
	 */
	int updateByVenue(@Param("venueid")String venueid, @Param("dateStr")String dateStr);
	
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
	List<FieldTemplate> selectByNowDate(@Param("id")String id, @Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("fieldid")String fieldid);

}