package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EmpCust;

public interface EmpCustMapper extends BaseMapper<EmpCust, String>{

	/**  
	 * @Description: 根据经纪人ID查询黑名单
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午3:41:10 
	 */ 
	Integer countByEmp(String empId);
}