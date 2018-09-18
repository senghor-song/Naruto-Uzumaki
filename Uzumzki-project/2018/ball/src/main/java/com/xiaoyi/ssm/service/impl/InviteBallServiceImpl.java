package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.InviteBallMapper;
import com.xiaoyi.ssm.dto.InviteDto;
import com.xiaoyi.ssm.model.InviteBall;
import com.xiaoyi.ssm.service.InviteBallService;

/**  
 * @Description: 约球业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月13日 下午4:03:48 
 */ 
@Service
public class InviteBallServiceImpl extends AbstractService<InviteBall,String> implements InviteBallService{

	@Autowired
	private InviteBallMapper inviteBallMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(inviteBallMapper);
	}

	/**  
	 * @Description: 查询附近的约球信息
	 * @author 宋高俊  
	 * @param inviteDto
	 * @return 
	 * @date 2018年9月13日 下午4:05:59 
	 */ 
	@Override
	public List<InviteBall> selectByNearby(InviteDto inviteDto) {
		return inviteBallMapper.selectByNearby(inviteDto);
	}

}
