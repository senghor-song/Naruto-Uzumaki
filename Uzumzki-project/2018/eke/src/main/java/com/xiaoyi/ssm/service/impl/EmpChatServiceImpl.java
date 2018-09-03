package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpChatMapper;
import com.xiaoyi.ssm.model.EmpChat;
import com.xiaoyi.ssm.service.EmpChatService;

/**  
 * @Description: 商户变更日志
 * @author 宋高俊  
 * @date 2018年8月1日 上午10:27:35 
 */ 
@Service
public class EmpChatServiceImpl extends AbstractService<EmpChat,String> implements EmpChatService{

	@Autowired
	private EmpChatMapper empChatMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(empChatMapper);
	}

	@Override
	public Integer countByEmp(String id) {
		return empChatMapper.countByEmp(id);
	}

	
}
