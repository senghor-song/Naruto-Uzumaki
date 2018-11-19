package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.InviteBallAAMapper;
import com.xiaoyi.ssm.model.InviteBallAA;
import com.xiaoyi.ssm.service.InviteBallAAService;

/**  
 * @Description: 约球aa退费接口
 * @author 宋高俊  
 * @date 2018年9月27日 下午7:23:49 
 */ 
@Service
public class InviteBallAAServiceImpl extends AbstractService<InviteBallAA,String> implements InviteBallAAService{

	@Autowired
	private InviteBallAAMapper inviteBallAAMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(inviteBallAAMapper);
	}

	/**  
	 * @Description: 根据约球ID查询
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月27日 上午11:36:50 
	 */ 
	@Override
	public InviteBallAA selectByInviteBallId(String id) {
		return inviteBallAAMapper.selectByInviteBallId(id);
	}

}
