package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EmpStoreVerify;

/**  
 * @Description: 月检数据访问层
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:46:59 
 */ 
public interface EmpStoreVerifyMapper extends BaseMapper<EmpStoreVerify, String>{
	
	/**  
	 * @Description: 获取是否有续期申请
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午4:45:49 
	 */ 
	EmpStoreVerify selectVerifyByStore(String id);

	/**  
	 * @Description: 统计未处理的合作续期
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午11:12:35 
	 */ 
	Integer countByNoDispose();
}