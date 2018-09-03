package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.CombineJoin;

public interface CombineJoinMapper extends BaseMapper<CombineJoin, String>{

	/**  
	 * @Description: 根据散拼ID查询加入人数
	 * @author 宋高俊  
	 * @date 2018年8月20日 上午10:05:17 
	 */ 
	Integer countByCombine(String id);

}