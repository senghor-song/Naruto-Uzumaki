package com.xiaoyi.ssm.service.impl;

import java.util.Date;
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

	/**  
	 * @Description: 根据时间段查询已到期的约球
	 * @author 宋高俊  
	 * @param startDate
	 * @param endDate
	 * @return 
	 * @date 2018年9月18日 下午4:39:22 
	 */ 
	@Override
	public List<InviteBall> selectByTimeOut(Date startDate, Date endDate) {
		return inviteBallMapper.selectByTimeOut(startDate, endDate);
	}

	/**  
	 * @Description: 根据用户ID查询已加入的约球
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月18日 下午5:55:52 
	 */ 
	@Override
	public List<InviteBall> selectByMyInviteBall(String id) {
		return inviteBallMapper.selectByMyInviteBall(id);
	}

	/**  
	 * @Description: 根据用户ID查询发起的约球
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月18日 下午5:55:52 
	 */ 
	@Override
	public List<InviteBall> selectByMyApplyInviteBall(String id) {
		return inviteBallMapper.selectByMyApplyInviteBall(id);
	}

	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月19日 下午3:01:46 
	 */ 
	@Override
	public InviteBall selectByJoinKey(String joinId) {
		return inviteBallMapper.selectByJoinKey(joinId);
	}

}