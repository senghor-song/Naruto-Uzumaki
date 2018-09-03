package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.MassNoticeLog;

public interface MassNoticeLogMapper extends BaseMapper<MassNoticeLog, String> {

	/**  
	 * @Description: 根据公告统计修改日志
	 * @author 宋高俊  
	 * @date 2018年7月30日 上午9:45:16 
	 */ 
	Integer countLogByNotice(String massNoticeID);
}