package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.AmountRefundMapper;
import com.xiaoyi.ssm.model.AmountRefund;
import com.xiaoyi.ssm.service.AmountRefundService;

/**  
 * @Description: 退款业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月11日 下午5:01:56 
 */ 
@Service
public class AmountRefundServiceImpl extends AbstractService<AmountRefund,String> implements AmountRefundService{

	@Autowired
	private AmountRefundMapper amountRefundMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(amountRefundMapper);
	}

}
