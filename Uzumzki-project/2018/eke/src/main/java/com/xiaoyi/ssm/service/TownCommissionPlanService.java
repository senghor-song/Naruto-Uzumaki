package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.TownCommissionPlan;

/**  
 * @Description: 新盘佣金方案业务逻辑层
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:03:17 
 */ 
public interface TownCommissionPlanService extends BaseService<TownCommissionPlan, String> {

	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	Integer countByTown(String id);

}
