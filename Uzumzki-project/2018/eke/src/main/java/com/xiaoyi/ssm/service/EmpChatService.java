package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EmpChat;

/**  
 * @Description: 经济人后台交流日志
 * @author 宋高俊  
 * @date 2018年8月1日 上午10:48:10 
 */ 
public interface EmpChatService extends BaseService<EmpChat, String> {
	
	/**  
	 * @Description: 根据经济人统计后台交流
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午10:55:34 
	 */ 
	Integer countByEmp(String id);
}
