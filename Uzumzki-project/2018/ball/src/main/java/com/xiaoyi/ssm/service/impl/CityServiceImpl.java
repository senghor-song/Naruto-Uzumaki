package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CityMapper;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.service.CityService;

/**  
 * @Description: 城市业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class CityServiceImpl extends AbstractService<City,String> implements CityService{

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(cityMapper);
	}

}
