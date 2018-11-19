package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.InviteJoinMapper;
import com.xiaoyi.ssm.model.InviteJoin;
import com.xiaoyi.ssm.service.InviteJoinService;

/**  
 * @Description: 加入约球业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月27日 下午5:13:14 
 */ 
@Service
public class InviteJoinServiceImpl extends AbstractService<InviteJoin,String> implements InviteJoinService{

	@Autowired
	private InviteJoinMapper inviteJoinMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(inviteJoinMapper);
	}

	/**
	 * @Description: 根据约球ID查询已加入人员
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月14日 下午4:25:23
	 */
	@Override
	public List<InviteJoin> selectByJoinMember(String id) {
		return inviteJoinMapper.selectByJoinMember(id);
	}

	/**  
	 * @Description: 根据会员ID和约球ID查询信息
	 * @author 宋高俊  
	 * @param inviteId
	 * @param memberId
	 * @return 
	 * @date 2018年9月17日 上午9:46:02 
	 */ 
	@Override
	public InviteJoin selectByJoinMemberKey(String inviteId, String memberId) {
		return inviteJoinMapper.selectByJoinMemberKey(inviteId, memberId);
	}

	/**  
	 * @Description: 统计当前报名人数
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月18日 下午3:56:37 
	 */ 
	@Override
	public Integer countByJoinBall(String inviteId) {
		return inviteJoinMapper.countByJoinBall(inviteId);
	}

	/**  
	 * @Description: 用于后台查询该约球的报名情况
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月20日 下午7:47:18 
	 */ 
	@Override
	public List<InviteJoin> selectByJoinDetails(String id) {
		return inviteJoinMapper.selectByJoinDetails(id);
	}

	/**  
	 * @Description: 根据用户ID查询已加入的约球
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月21日 上午9:24:32 
	 */ 
	@Override
	public Integer countByMyJoinBall(String id) {
		return inviteJoinMapper.countByMyJoinBall(id);
	}

	/**  
	 * @Description: 根据用户ID查询上一次约球的数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月26日 上午11:14:37 
	 */ 
	@Override
	public InviteJoin selectByLastJoinBall(String id) {
		return inviteJoinMapper.selectByLastJoinBall(id);
	}

}
