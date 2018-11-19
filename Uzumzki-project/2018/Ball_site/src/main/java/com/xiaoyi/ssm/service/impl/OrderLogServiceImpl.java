package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.OrderLogMapper;
import com.xiaoyi.ssm.model.OrderLog;
import com.xiaoyi.ssm.service.OrderLogService;

/**  
 * @Description: 订单日志业务逻辑实现
 * @author 宋高俊  
 * @date 2018年11月8日 上午9:41:37 
 */ 
@Service
public class OrderLogServiceImpl extends AbstractService<OrderLog,String> implements OrderLogService{

	@Autowired
	private OrderLogMapper orderLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(orderLogMapper);
	}
	
}
