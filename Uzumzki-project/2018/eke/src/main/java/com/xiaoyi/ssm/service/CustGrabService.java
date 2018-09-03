package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.CustGrab;

/**  
 * @Description: 主动抢客业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月31日 上午9:30:09 
 */ 
public interface CustGrabService extends BaseService<CustGrab, String> {
	
	/**  
	 * @Description: 根据经济人统计
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午9:31:48 
	 */ 
	Integer countByEmpId(String empId);
}
