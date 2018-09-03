package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EstateLog;

/**  
 * @Description: 小区日志表
 * @author 宋高俊  
 * @date 2018年7月31日 下午5:38:05 
 */ 
public interface EstateLogMapper extends BaseMapper<EstateLog, String>{
	
	/**  
	 * @Description: 根据小区统计日志
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午5:37:53 
	 */ 
	Integer countLogByEstate(String id);
}