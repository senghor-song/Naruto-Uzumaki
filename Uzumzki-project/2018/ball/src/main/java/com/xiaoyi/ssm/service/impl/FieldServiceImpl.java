package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.FieldMapper;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.service.FieldService;

/**  
 * @Description: 场馆业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class FieldServiceImpl extends AbstractService<Field,String> implements FieldService{

	@Autowired
	private FieldMapper fieldMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(fieldMapper);
	}
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return fieldMapper.countByVenue(id);
	}

	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	@Override
	public List<Field> selectByVenue(String id) {
		return fieldMapper.selectByVenue(id);
	}
	
	/**
	 * @Description: 场地逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:29:25
	 */
	@Override
	public int updateByVenue(String venueid, String dateStr) {
		return fieldMapper.updateByVenue(venueid, dateStr);
	}

	/**
	 * @Description: 根据模板ID查询默认使用的场地
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月14日上午10:33:03
	 */
	@Override
	public List<Field> selectByDefaultVenue(String venueId, String venueTemplateId) {
		return fieldMapper.selectByDefaultVenue(venueId, venueTemplateId);
	}
	
	/**
	 * @Description: 根据场馆ID和场地名称查询
	 * @author 宋高俊
	 * @param id
	 * @param fieldName
	 * @return
	 * @date 2018年12月15日下午4:06:39
	 */
	@Override
	public Field selectByVenueName(String venueid, String fieldName) {
		return fieldMapper.selectByVenueName(venueid, fieldName);
	}
}
