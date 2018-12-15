package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.FieldTemplateMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.dto.FieldTemplateDto;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;

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

	/**
	 * @Description: 修改使用旧模板为使用新模板
	 * @author 宋高俊
	 * @param oldId
	 * @param nowId
	 * @return
	 * @date 2018年11月19日 下午3:57:35
	 */
	@Override
	public int updateByTemplate(String oldId, String nowId) {
		return fieldTemplateMapper.updateByTemplate(oldId, nowId);
	}
	
	/**
	 * @Description: 场地使用模板逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:42:17
	 */
	@Override
	public int updateByVenue(String venueid, String dateStr) {
		return fieldTemplateMapper.updateByVenue(venueid, dateStr);
	}

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
	@Override
	public List<FieldTemplate> selectByNowDate(String id, Date startDate,
			Date endDate, String fieldid) {
		return fieldTemplateMapper.selectByNowDate(id, startDate, endDate, fieldid);
	}

	/**
	 * @Description: 选配模板
	 * @author 宋高俊
	 * @param venueid
	 * @param venueTemplate
	 * @param date
	 * @param fieldid
	 * @return
	 * @date 2018年12月15日上午11:32:24
	 */
	@Override
	public ApiMessage saveFieldTemplate(String venueid, VenueTemplate venueTemplate, String date, String fieldid) {
		FieldTemplateDto ftd = new FieldTemplateDto();
	    ftd.setDate(DateUtil.getParse(date, "yyyy-MM-dd"));
	    ftd.setFieldid(fieldid);
	    ftd.setVenueid(venueid);
	    FieldTemplate oldFieldTemplate = selectByVenueAndField(ftd);
	    if (oldFieldTemplate != null) {
	      oldFieldTemplate.setTemplateid(venueTemplate.getId());
	      updateByPrimaryKeySelective(oldFieldTemplate);
	      return new ApiMessage(200, "修改成功");
	    }
	    FieldTemplate ft = new FieldTemplate();
	    ft.setId(Utils.getUUID());
	    ft.setCreatetime(new Date());
	    ft.setVenueid(venueid);
	    ft.setFieldtime(DateUtil.getParse(date, "yyyy-MM-dd"));
	    ft.setTemplateid(venueTemplate.getId());
	    ft.setFieldid(fieldid);
	    insert(ft);
	    return new ApiMessage(200, "设置成功");
	}
}
