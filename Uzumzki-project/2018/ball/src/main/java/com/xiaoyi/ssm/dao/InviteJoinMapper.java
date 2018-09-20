package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.InviteJoin;

public interface InviteJoinMapper extends BaseMapper<InviteJoin, String> {

	/**
	 * @Description: 根据约球ID查询已加入人员
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月14日 下午4:25:23
	 */
	List<InviteJoin> selectByJoinMember(String id);

	/**  
	 * @Description: 根据会员ID和约球ID查询信息
	 * @author 宋高俊  
	 * @param inviteId
	 * @param memberId
	 * @return 
	 * @date 2018年9月17日 上午9:46:02 
	 */ 
	InviteJoin selectByJoinMemberKey(@Param("inviteId") String inviteId, @Param("memberId") String memberId);
	/**  
	 * @Description: 统计当前报名人数
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月18日 下午3:56:37 
	 */ 
	Integer countByJoinBall(@Param("inviteId") String inviteId);
}