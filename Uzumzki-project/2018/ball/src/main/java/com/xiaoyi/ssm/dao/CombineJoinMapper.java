package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.CombineJoin;

public interface CombineJoinMapper extends BaseMapper<CombineJoin, String>{

	/**  
	 * @Description: 根据散拼ID查询加入人数
	 * @author 宋高俊  
	 * @date 2018年8月20日 上午10:05:17 
	 */ 
	Integer countByCombine(String id);

	/**  
	 * @Description: 根据散拼ID查询加入会员列表
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月3日 下午3:15:56 
	 */ 
	List<CombineJoin> selectByCombine(String id);

}