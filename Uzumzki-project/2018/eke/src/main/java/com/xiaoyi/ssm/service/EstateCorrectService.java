package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EstateCorrect;

/**  
 * @Description: 小区信息纠错业务逻辑层
 * @author 宋高俊  
 * @date 2018年7月26日 下午6:46:50 
 */ 
public interface EstateCorrectService extends BaseService<EstateCorrect, String>{
	
	/**  
	 * @Description: 根据小区查询小区错误信息数量
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:48:37 
	 */ 
	Integer selectByEstateCount(String estateid);
}
