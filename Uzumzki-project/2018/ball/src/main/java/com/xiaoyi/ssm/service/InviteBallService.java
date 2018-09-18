package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.InviteDto;
import com.xiaoyi.ssm.model.InviteBall;

/**  
 * @Description: 约球业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月13日 下午4:02:38 
 */ 
public interface InviteBallService extends BaseService<InviteBall, String> {

	/**  
	 * @Description: 查询附近的约球信息
	 * @author 宋高俊  
	 * @param inviteDto
	 * @return 
	 * @date 2018年9月13日 下午4:05:59 
	 */ 
	List<InviteBall> selectByNearby(InviteDto inviteDto);

}
