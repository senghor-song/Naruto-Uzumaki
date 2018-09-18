package com.xiaoyi.ssm.dao;

import java.util.List;

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
}