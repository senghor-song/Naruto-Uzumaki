package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.AreaMapper;
import com.xiaoyi.ssm.model.Area;
import com.xiaoyi.ssm.service.AreaService;

/**  
 * @Description: 片区业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class AreaServiceImpl extends AbstractService<Area,String> implements AreaService{

	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(areaMapper);
	}

}
