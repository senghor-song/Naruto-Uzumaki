package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.TrainLog;

public interface TrainLogMapper extends BaseMapper<TrainLog, String>{

	/**  
	 * @Description: 根据培训ID查询日志数量
	 * @author 宋高俊  
	 * @date 2018年8月20日 上午10:05:17 
	 */ 
	Integer countByTrain(String id);

	/**  
	 * @Description: 根据培训ID查询日志
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午4:36:12 
	 */ 
	List<TrainLog> selectByTrain(String id);
}