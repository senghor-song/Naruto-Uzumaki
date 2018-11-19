package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainOrderLog;

/**  
 * @Description: 培训课程日志业务逻辑层
 * @author 宋高俊  
 * @date 2018年10月30日 上午9:36:23 
 */ 
public interface TrainOrderLogService extends BaseService<TrainOrderLog, String> {

	/**  
	 * @Description: 根据订单ID查询订单日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月30日 上午9:42:44 
	 */ 
	List<TrainOrderLog> selectByOrder(String id);

}
