package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TownMapper;
import com.xiaoyi.ssm.model.Town;
import com.xiaoyi.ssm.service.TownService;

/**  
 * @Description: 新盘业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:04:54 
 */ 
@Service
public class TownServiceImpl extends AbstractService<Town,String> implements TownService{

	@Autowired
	private TownMapper townMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(townMapper);
	}
	
}
