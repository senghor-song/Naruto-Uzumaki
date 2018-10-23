package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.WXCompanyPaymentMapper;
import com.xiaoyi.ssm.model.WXCompanyPayment;
import com.xiaoyi.ssm.service.WXCompanyPaymentService;

/**  
 * @Description: 企业支付业务逻辑实现层
 * @author 宋高俊  
 * @date 2018年10月11日 下午5:06:35 
 */ 
@Service
public class WXCompanyPaymentServiceImpl extends AbstractService<WXCompanyPayment,String> implements WXCompanyPaymentService{

	@Autowired
	private WXCompanyPaymentMapper wxCompanyPaymentMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(wxCompanyPaymentMapper);
	}

}
