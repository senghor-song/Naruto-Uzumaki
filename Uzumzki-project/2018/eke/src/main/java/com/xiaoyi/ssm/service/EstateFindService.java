package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EstateFind;

/**
 * @Description: 增加小区业务逻辑代码
 * @author 宋高俊
 * @date 2018年7月26日 下午10:53:39
 */
public interface EstateFindService extends BaseService<EstateFind, String>{

	/**  
	 * @Description: 增加小区未处理数量
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:16:00 
	 */ 
	Integer countByNoDispose();

}
