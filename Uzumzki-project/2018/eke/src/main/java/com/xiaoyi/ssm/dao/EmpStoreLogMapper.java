package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EmpStoreLog;

public interface EmpStoreLogMapper extends BaseMapper<EmpStoreLog, String>{

	/**  
	 * @Description: 根据商户统计
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午10:29:41 
	 */ 
	Integer countByEmpStore(String id);
	
}