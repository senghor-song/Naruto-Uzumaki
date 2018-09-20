package com.xiaoyi.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.InviteDto;
import com.xiaoyi.ssm.model.InviteBall;

public interface InviteBallMapper extends BaseMapper<InviteBall, String>{

	/**  
	 * @Description: 查询附近的约球信息
	 * @author 宋高俊  
	 * @param inviteDto
	 * @return 
	 * @date 2018年9月13日 下午4:05:59 
	 */ 
	List<InviteBall> selectByNearby(InviteDto inviteDto);

	/**  
	 * @Description: 根据时间段查询已到期的约球
	 * @author 宋高俊  
	 * @param startDate
	 * @param endDate
	 * @return 
	 * @date 2018年9月18日 下午4:39:22 
	 */ 
	List<InviteBall> selectByTimeOut(@Param("startDate")Date startDate, @Param("endDate")Date endDate);

	/**  
	 * @Description: 根据用户ID查询已加入的约球
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月18日 下午5:55:52 
	 */ 
	List<InviteBall> selectByMyInviteBall(String id);

	/**  
	 * @Description: 根据用户ID查询发起的约球
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月18日 下午5:55:52 
	 */ 
	List<InviteBall> selectByMyApplyInviteBall(String id);

	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月19日 下午3:01:46 
	 */ 
	InviteBall selectByJoinKey(String joinId);
}