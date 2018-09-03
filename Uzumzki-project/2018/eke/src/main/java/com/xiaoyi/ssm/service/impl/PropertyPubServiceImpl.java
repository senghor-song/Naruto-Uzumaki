package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.PropertyPubMapper;
import com.xiaoyi.ssm.model.PropertyPub;
import com.xiaoyi.ssm.service.PropertyPubService;

/**  
 * @Description: 公盘业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月28日 上午9:16:57 
 */ 
@Service
public class PropertyPubServiceImpl extends AbstractService<PropertyPub,String> implements PropertyPubService{

	@Autowired
	private PropertyPubMapper propertyPubMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(propertyPubMapper);
	}
	
	/**  
	 * @Description: 根据公盘查询分发记录
	 * @author 宋高俊  
	 * @date 2018年8月6日 下午4:59:46 
	 */ 
	@Override
	public List<PropertyPub> selectByPropertyPre(String id) {
		return propertyPubMapper.selectByPropertyPre(id);
	}

	@Override
	public Integer countClaimByPropertypre(String propertyPreId) {
		return propertyPubMapper.countClaimByPropertypre(propertyPreId);
	}

	@Override
	public Integer countPubByPropertypre(String propertyPreId) {
		return propertyPubMapper.countPubByPropertypre(propertyPreId);
	}
	

}
