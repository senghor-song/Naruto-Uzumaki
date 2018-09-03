package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EmpChat;

/**  
 * @Description: 后台交流日志
 * @author 宋高俊  
 * @date 2018年8月1日 上午10:47:20 
 */ 
public interface EmpChatMapper extends BaseMapper<EmpChat, String>{

	/**  
	 * @Description: 根据经纪人统计后台交流日志
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午10:57:07 
	 */ 
	Integer countByEmp(String id);

}