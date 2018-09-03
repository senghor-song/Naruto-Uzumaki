package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.PropertyFollowMapper;
import com.xiaoyi.ssm.model.PropertyFollow;
import com.xiaoyi.ssm.service.PropertyFollowService;

/**  
 * @Description: 房源跟进业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月26日 下午6:28:47 
 */ 
@Service
public class PropertyFollowServiceImpl extends AbstractService<PropertyFollow,String> implements PropertyFollowService{

	@Autowired
	private PropertyFollowMapper propertyFollowMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(propertyFollowMapper);
	}
	/**  
	 * @Description: 根据房源查询房源跟进数量
	 * @author 宋高俊  
	 * @date 2018年8月2日 下午4:14:54 
	 */ 
	@Override
	public Integer countByProperty(String id) {
		return propertyFollowMapper.countByProperty(id);
	}

}
