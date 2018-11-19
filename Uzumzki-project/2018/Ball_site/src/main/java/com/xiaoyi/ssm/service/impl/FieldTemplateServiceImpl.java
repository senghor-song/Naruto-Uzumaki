package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.FieldTemplateMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.model.VenueStatis;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.VenueStatisService;

/**  
 * @Description: 场馆业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class FieldTemplateServiceImpl extends AbstractService<FieldTemplate,String> implements FieldTemplateService{

	@Autowired
	private FieldTemplateMapper fieldTemplateMapper;
	@Autowired
	private VenueStatisService venueStatisService;
	
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

	@Override
	public ApiMessage saveFieldTemplateStatis(VenueStatis venueStatis, Venue venue,VenueTemplate venueTemplate, Date statisdate) {
		//修改日历统计使用模板
		venueStatis.setTemplate(venueTemplate.getName());
		int flag = venueStatisService.updateByPrimaryKeySelective(venueStatis);
		if (flag > 0) {
			//根据场馆ID和日期修改当天使用的模板ID
			FieldTemplate ft = new FieldTemplate();
			ft.setVenueid(venue.getId());
			ft.setFieldtime(statisdate);
			List<FieldTemplate> fieldTemplates = fieldTemplateMapper.selectByAll(ft);
			for (int i = 0; i < fieldTemplates.size(); i++) {
				FieldTemplate fieldTemplate = fieldTemplates.get(i);
				fieldTemplate.setTemplateid(venueTemplate.getId());
				fieldTemplateMapper.updateByPrimaryKeySelective(fieldTemplate);
			}
			return new ApiMessage(200, "选配模板成功");
		}else {
			return new ApiMessage(400, "选配模板失败");
		}
	}

}
