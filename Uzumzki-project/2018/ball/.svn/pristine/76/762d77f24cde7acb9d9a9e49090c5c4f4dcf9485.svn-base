package com.xiaoyi.ssm.service.impl;

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
}
