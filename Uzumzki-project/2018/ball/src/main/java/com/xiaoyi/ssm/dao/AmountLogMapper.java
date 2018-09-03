package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.AmountLog;

public interface AmountLogMapper extends BaseMapper<AmountLog, String>{
	
	/**  
	 * @Description: 根据提现记录获取日志
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午5:20:14 
	 */ 
	List<AmountLog> selectByAmount(String id);
}