package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.DistrictMapper;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.service.DistrictService;

/**  
 * @Description: 区县业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class DistrictServiceImpl extends AbstractService<District,String> implements DistrictService{

	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(districtMapper);
	}

}
