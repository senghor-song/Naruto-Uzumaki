package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.OperationLog;

public interface OperationLogMapper extends BaseMapper<OperationLog, String>{

	/**  
	 * @Description: 记录日志数据
	 * @author 宋高俊  
	 * @param staffId
	 * @param content
	 * @param ip
	 * @return 
	 * @date 2018年11月7日 上午11:37:47 
	 */ 
	int saveLog(String staffId, String content, String ip);
}