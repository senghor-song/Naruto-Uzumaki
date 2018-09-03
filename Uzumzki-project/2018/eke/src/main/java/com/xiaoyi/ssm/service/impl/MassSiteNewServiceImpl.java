package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassSiteNewMapper;
import com.xiaoyi.ssm.model.MassSiteNew;
import com.xiaoyi.ssm.service.MassSiteNewService;

/**  
 * @Description: 新增网站意见业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月27日 上午10:33:23 
 */ 
@Service
public class MassSiteNewServiceImpl extends AbstractService<MassSiteNew,String> implements MassSiteNewService{

	@Autowired
	private MassSiteNewMapper massSiteNewMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massSiteNewMapper);
	}
	

}
