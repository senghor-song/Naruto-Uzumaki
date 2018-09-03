package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateImageStorageMapper;
import com.xiaoyi.ssm.model.EstateImageStorage;
import com.xiaoyi.ssm.service.EstateImageStorageService;

/**  
 * @Description: 小区仓储图业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午6:05:43 
 */ 
@Service
public class EstateImageStorageServiceImpl extends AbstractService<EstateImageStorage,String> implements EstateImageStorageService{

	@Autowired
	private EstateImageStorageMapper estateImageStorageMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(estateImageStorageMapper);
	}

}
