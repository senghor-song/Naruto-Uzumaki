package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassPropertyPublishMapper;
import com.xiaoyi.ssm.model.MassPropertyPublish;
import com.xiaoyi.ssm.service.MassPropertyPublishService;

/**  
 * @Description: 黑名单业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月5日 下午7:44:44 
 */ 
@Service
public class MassPropertyPublishServiceImpl extends AbstractService<MassPropertyPublish,String> implements MassPropertyPublishService{

	@Autowired
	private MassPropertyPublishMapper massPropertyPublishMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massPropertyPublishMapper);
	}

	@Override
	public Integer countPublishByEmp(String empId) {
		return massPropertyPublishMapper.countPublishByEmp(empId);
	}
	

}
