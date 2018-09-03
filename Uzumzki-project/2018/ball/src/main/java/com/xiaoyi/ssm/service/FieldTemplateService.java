package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.FieldTemplate;

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
	 * @Description: 根据场馆和场地ID获取场地所有使用模板
	 * @author 宋高俊  
	 * @date 2018年8月17日 下午2:31:49 
	 */ 
	List<FieldTemplate> selectByVenueAndFieldAll(FieldTemplateDto ftd);

}
