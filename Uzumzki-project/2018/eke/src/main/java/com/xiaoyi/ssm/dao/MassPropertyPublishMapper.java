package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.MassPropertyPublish;

/**  
 * @Description: 群发任务数据访问层
 * @author 宋高俊  
 * @date 2018年7月30日 下午4:00:57 
 */ 
public interface MassPropertyPublishMapper extends BaseMapper<MassPropertyPublish, String> {

	/**  
	 * @Description: 统计累计群发
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午4:00:50 
	 */ 
	Integer countPublishByEmp(String empId);
}