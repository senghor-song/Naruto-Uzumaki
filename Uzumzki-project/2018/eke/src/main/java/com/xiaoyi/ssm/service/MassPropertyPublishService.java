package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.MassPropertyPublish;

/**  
 * @Description: 群发任务业务逻辑层
 * @author 宋高俊  
 * @date 2018年7月30日 下午3:57:21 
 */ 
public interface MassPropertyPublishService extends BaseService<MassPropertyPublish, String> {

	/**  
	 * @Description: 统计累计群发
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午4:00:09 
	 */ 
	Integer countPublishByEmp(String empId);
	
}
