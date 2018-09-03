package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.FieldTemplateMapper;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.service.FieldTemplateService;

/**  
 * @Description: 场馆业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class FieldTemplateServiceImpl extends AbstractService<FieldTemplate,String> implements FieldTemplateService{

	@Autowired
	private FieldTemplateMapper fieldTemplateMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(fieldTemplateMapper);
	}

	/**  
	 * @Description: 根据场馆和场地ID获取场地当天使用模板
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午8:17:21 
	 */ 
	@Override
	public FieldTemplate selectByVenueAndField(FieldTemplateDto ftd) {
		return fieldTemplateMapper.selectByVenueAndField(ftd);
	}

	@Override
	public List<FieldTemplate> selectByVenueAndFieldAll(FieldTemplateDto ftd) {
		return fieldTemplateMapper.selectByVenueAndFieldAll(ftd);
	}

}
