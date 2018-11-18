package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.OperationLog;

/**
 * @Description: 操作日志业务逻辑层
 * @author 宋高俊
 * @date 2018年11月7日 上午11:36:32
 */
public interface OperationLogService extends BaseService<OperationLog, String> {
	
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
