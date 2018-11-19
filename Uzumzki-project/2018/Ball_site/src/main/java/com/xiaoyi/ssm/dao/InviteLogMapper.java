package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.InviteLog;

public interface InviteLogMapper extends BaseMapper<InviteLog, String> {

	/**  
	 * @Description: 发起人查询约球日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月13日 下午7:40:38 
	 */ 
	List<InviteLog> selectByInviteAdmin(String id);

	/**  
	 * @Description: 普通人查询约球日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月13日 下午7:40:38 
	 */ 
	List<InviteLog> selectByInvite(String id);

}