package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.TrainOrderLog;

public interface TrainOrderLogMapper extends BaseMapper<TrainOrderLog, String>{

	/**  
	 * @Description: 根据订单ID查询订单日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月30日 上午9:42:44 
	 */ 
	List<TrainOrderLog> selectByOrder(String id);
}