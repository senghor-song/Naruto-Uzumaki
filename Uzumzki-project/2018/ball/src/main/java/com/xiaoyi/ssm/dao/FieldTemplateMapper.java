package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.FieldTemplate;

public interface FieldTemplateMapper extends BaseMapper<FieldTemplate, String>{
	
	/**  
	 * @Description: 根据场馆和场地ID获取场地当天使用模板
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午8:17:21 
	 */ 
	FieldTemplate selectByVenueAndField(FieldTemplateDto ftd);

	List<FieldTemplate> selectByVenueAndFieldAll(FieldTemplateDto ftd);

}