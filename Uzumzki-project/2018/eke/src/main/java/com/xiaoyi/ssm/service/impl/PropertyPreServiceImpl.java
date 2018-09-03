package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.PropertyPreMapper;
import com.xiaoyi.ssm.model.PropertyPre;
import com.xiaoyi.ssm.service.PropertyPreService;

/**  
 * @Description: 公盘业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月28日 上午9:16:57 
 */ 
@Service
public class PropertyPreServiceImpl extends AbstractService<PropertyPre,String> implements PropertyPreService{

	@Autowired
	private PropertyPreMapper propertyPreMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(propertyPreMapper);
	}
	

}
