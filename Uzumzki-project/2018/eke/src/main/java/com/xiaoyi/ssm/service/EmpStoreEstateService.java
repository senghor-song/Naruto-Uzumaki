package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EmpStoreEstate;

/**  
 * @Description: 小区日志业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:13:35 
 */ 
public interface EmpStoreEstateService extends BaseService<EmpStoreEstate, String> {
	
	/**  
	 * @Description: 根据商户统计数据
	 * @author 宋高俊  
	 * @date 2018年8月1日 下午5:00:53 
	 */ 
	Integer countByEstateType(String id);
}
