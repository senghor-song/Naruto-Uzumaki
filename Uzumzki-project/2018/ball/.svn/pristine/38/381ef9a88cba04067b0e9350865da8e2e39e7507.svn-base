package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.OrderLog;

public interface OrderLogMapper extends BaseMapper<OrderLog, String>{

	/**  
	 * @Description: 根据订单查询日志
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午3:39:51 
	 */ 
	List<OrderLog> selectByOrder(String orderid);

	/**  
	 * @Description: 统计日志数据
	 * @author 宋高俊  
	 * @date 2018年8月22日 上午10:06:44 
	 */ 
	Integer countByOrder(String orderid);
	
}