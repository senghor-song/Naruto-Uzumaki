package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyi.ssm.dao.VenueStatisMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.FieldTemplate;
import com.xiaoyi.ssm.model.VenueStatis;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.VenueStatisService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 场馆业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class VenueStatisServiceImpl extends AbstractService<VenueStatis,String> implements VenueStatisService{

	@Autowired
	private VenueStatisMapper venueStatisMapper;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private FieldTemplateService fieldTemplateService;
	
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueStatisMapper);
	}

	@Transactional
	@Override
	public ApiMessage saveVenueStatis(String venueid,VenueTemplate venueTemplate,String date) {
		
		//新增一条日期模板数据
		VenueStatis vs = new VenueStatis();
		vs.setId(Utils.getUUID());
		vs.setCreatetime(new Date());
		vs.setVenueid(venueid);
		vs.setTemplate(venueTemplate.getName());
		vs.setStatisdate(DateUtil.getParse(date, "yyyy-MM-dd"));
		
		int flag = venueStatisMapper.insertSelective(vs);
		if (flag > 0) {
			Field f = new Field();
			f.setVenueid(venueid);
			List<Field> fields = fieldService.selectByAll(f);
			for (int i = 0; i < fields.size(); i++) {
				FieldTemplate ft = new FieldTemplate();
				ft.setId(Utils.getUUID());
				ft.setCreatetime(new Date());
				ft.setVenueid(venueid);
				ft.setFieldtime(DateUtil.getParse(date, "yyyy-MM-dd"));
				ft.setTemplateid(venueTemplate.getId());
				ft.setFieldid(fields.get(i).getId());
				fieldTemplateService.insert(ft);
			}
			return new ApiMessage(200, "选配模板成功");
		}else {
			return new ApiMessage(400, "选配模板失败");
		}
	}

	@Override
	public List<VenueStatis> selectByVenue(String venueid, Date startDate, Date endDate) {
		return venueStatisMapper.selectByVenue(venueid, startDate, endDate);
	}

}
