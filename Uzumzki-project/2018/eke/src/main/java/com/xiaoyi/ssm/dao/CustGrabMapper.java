package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.CustGrab;

public interface CustGrabMapper extends BaseMapper<CustGrab, String>{

	/**  
	 * @Description: 根据经济人统计
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午9:32:23 
	 */ 
	Integer countByEmpId(String empId);
	
}