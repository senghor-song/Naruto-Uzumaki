package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.TrainJoin;

public interface TrainJoinMapper extends BaseMapper<TrainJoin, String>{
	/**  
	 * @Description: 根据培训ID查询加入数量
	 * @author 宋高俊  
	 * @date 2018年8月20日 上午10:05:17 
	 */ 
	Integer countByTrain(String id);

	/**  
	 * @Description: 根据提现ID查询培训记录
	 * @author 宋高俊  
	 * @date 2018年8月31日 下午4:01:08 
	 */ 
	List<TrainJoin> selectByAmount(String amountid);

}