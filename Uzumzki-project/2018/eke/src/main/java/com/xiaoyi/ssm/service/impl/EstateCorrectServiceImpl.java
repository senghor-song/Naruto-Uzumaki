package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateCorrectMapper;
import com.xiaoyi.ssm.model.EstateCorrect;
import com.xiaoyi.ssm.service.EstateCorrectService;

/**  
 * @Description: 小区纠错业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月5日 下午7:44:44 
 */ 
@Service
public class EstateCorrectServiceImpl extends AbstractService<EstateCorrect,String> implements EstateCorrectService{

	@Autowired
	private EstateCorrectMapper estateCorrectMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(estateCorrectMapper);
	}

	/**  
	 * @Description: 根据小区查询小区错误信息数量
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:48:37 
	 */ 
	@Override
	public Integer selectByEstateCount(String estateid) {
		return estateCorrectMapper.selectByEstateCount(estateid);
	}
	

}
