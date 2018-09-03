package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.PropertyImageMapper;
import com.xiaoyi.ssm.model.PropertyImage;
import com.xiaoyi.ssm.service.PropertyImageService;

/**  
 * @Description: 室内图业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月26日 下午6:28:47 
 */ 
@Service
public class PropertyImageServiceImpl extends AbstractService<PropertyImage,String> implements PropertyImageService{

	@Autowired
	private PropertyImageMapper propertyImageMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(propertyImageMapper);
	}
	/**  
	 * @Description: 根据房源查询室内图数量
	 * @author 宋高俊  
	 * @date 2018年8月2日 下午4:14:54 
	 */ 
	@Override
	public Integer countByProperty(String id) {
		return propertyImageMapper.countByProperty(id);
	}

}
