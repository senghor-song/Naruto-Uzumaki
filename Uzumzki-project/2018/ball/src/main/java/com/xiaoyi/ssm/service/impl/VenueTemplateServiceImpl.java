package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueTemplateMapper;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.FieldTemplateService;
import com.xiaoyi.ssm.service.VenueTemplateService;

/**
 * @Description: 场馆模板业务逻辑实现
 * @author 宋高俊
 * @date 2018年8月16日 下午6:00:43
 */
@Service
public class VenueTemplateServiceImpl extends AbstractService<VenueTemplate, String> implements VenueTemplateService {

	@Autowired
	private VenueTemplateMapper venueTemplateMapper;
	@Autowired
	private FieldTemplateService fieldTemplateService;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(venueTemplateMapper);
	}

	/**
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊
	 * @date 2018年8月18日 下午2:03:12
	 */
	@Override
	public Integer countByVenue(String id) {
		return venueTemplateMapper.countByVenue(id);
	}

	/**
	 * @Description: 根据管理员ID和场馆ID查询
	 * @author 宋高俊
	 * @date 2018年8月22日 下午7:02:36
	 */
	@Override
	public List<VenueTemplate> selectByVenue(String venueid) {
		return venueTemplateMapper.selectByVenue(venueid);
	}

	/**
	 * @Description: 根据场馆馆和模板id查询
	 * @author 宋高俊
	 * @param venueid
	 * @param templateid
	 * @return
	 * @date 2018年11月2日 上午9:17:32
	 */
	@Override
	public VenueTemplate selectByVenueTemplate(String venueid, String templateid) {
		return venueTemplateMapper.selectByVenueTemplate(venueid, templateid);
	}


	/**
	 * @Description: 模板逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:15:02
	 */
	@Override
	public int updateByVenue(String venueid, String dateStr) {
		return venueTemplateMapper.updateByVenue(venueid, dateStr);
	}
	
	/**
	 * @Description: 根据场馆ID和日期查询
	 * @author 宋高俊
	 * @param venueid
	 * @param date
	 * @return
	 * @date 2018年11月28日上午10:35:23
	 */
	@Override
	public List<VenueTemplate> selectByVenueDate(String venueid, String date) {
		return venueTemplateMapper.selectByVenueDate(venueid, date);
	}

}
