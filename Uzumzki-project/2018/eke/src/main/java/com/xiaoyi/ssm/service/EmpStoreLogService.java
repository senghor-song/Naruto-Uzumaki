package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EmpStoreLog;

/**  
 * @Description: 商户变更日志
 * @author 宋高俊  
 * @date 2018年8月1日 上午10:26:46 
 */ 
public interface EmpStoreLogService extends BaseService<EmpStoreLog, String> {

	/**  
	 * @Description: 根据商户统计变更日志
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午10:48:31 
	 */ 
	Integer countByEmpStore(String id);
}
