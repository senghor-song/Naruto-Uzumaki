package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EstateCorrect;

public interface EstateCorrectMapper extends BaseMapper<EstateCorrect, String>{

	/**  
	 * @Description: 根据小区查询小区错误信息数量
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午6:48:37 
	 */ 
	Integer selectByEstateCount(String estateid);
}