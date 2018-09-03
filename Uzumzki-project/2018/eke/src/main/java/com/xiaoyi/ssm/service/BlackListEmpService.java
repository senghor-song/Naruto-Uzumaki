package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.BlackListEmp;


/**  
 * @Description: 黑名单业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月5日 下午7:42:36 
 */ 
public interface BlackListEmpService extends BaseService<BlackListEmp, String> {

	/**  
	 * @Description: 根据经纪人ID查询黑名单
	 * @author 宋高俊  
	 * @date 2018年7月5日 下午7:50:44 
	 */ 
	List<BlackListEmp> selectAll(String empId);
	
}
