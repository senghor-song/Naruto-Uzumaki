package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EstateLog;

/**  
 * @Description: 小区日志业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:13:35 
 */ 
public interface EstateLogService extends BaseService<EstateLog, String> {

	/**  
	 * @Description: 根据小区统计日志
	 * @author 宋高俊  
	 * @date 2018年7月31日 下午5:37:53 
	 */ 
	Integer countLogByEstate(String id);
}
