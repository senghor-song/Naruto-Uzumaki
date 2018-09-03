package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateFindMapper;
import com.xiaoyi.ssm.model.EstateFind;
import com.xiaoyi.ssm.service.EstateFindService;

/**  
 * @Description: 增加小区意见业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月27日 上午10:30:21 
 */ 
@Service
public class EstateFindServiceImpl extends AbstractService<EstateFind,String> implements EstateFindService{

	@Autowired
	private EstateFindMapper estateFindMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(estateFindMapper);
	}

	@Override
	public Integer countByNoDispose() {
		return estateFindMapper.countByNoDispose();
	}
	

}
