package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EmpStoreVerify;

/**  
 * @Description: 后台交流业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月31日 上午9:17:41 
 */ 
public interface EmpStoreVerifyService extends BaseService<EmpStoreVerify, String> {

	/**  
	 * @Description: 获取是否有续期申请
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午4:45:49 
	 */ 
	EmpStoreVerify selectVerifyByStore(String id);

	/**  
	 * @Description: 查询未处理的合作续期
	 * @author 宋高俊  
	 * @date 2018年8月1日 上午11:11:34 
	 */ 
	Integer countByNoDispose();
}
