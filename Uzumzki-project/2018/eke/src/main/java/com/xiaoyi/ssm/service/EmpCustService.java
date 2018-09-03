package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EmpCust;

/**  
 * @Description: 私客业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月30日 下午3:36:59 
 */ 
public interface EmpCustService extends BaseService<EmpCust, String> {

	/**  
	 * @Description: 根据经纪人统计私客数量
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午3:39:45 
	 */ 
	Integer countByEmp(String empId);
	
}
