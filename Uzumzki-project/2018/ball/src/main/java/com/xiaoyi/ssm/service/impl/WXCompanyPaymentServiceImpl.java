package com.xiaoyi.ssm.service.impl;

import java.util.List;

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
@Service("wxCompanyPaymentServiceImpl")
public class WXCompanyPaymentServiceImpl extends AbstractService<WXCompanyPayment,String> implements WXCompanyPaymentService{

	@Autowired
	private WXCompanyPaymentMapper wxCompanyPaymentMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(wxCompanyPaymentMapper);
	}

	/**
	 * @Description: 根据订单ID查询最新的支付信息
	 * @author 宋高俊
	 * @param orderid
	 * @return
	 * @date 2018年11月27日下午4:57:23
	 */
	@Override
	public WXCompanyPayment selectByOrder(String orderid) {
		return wxCompanyPaymentMapper.selectByOrder(orderid);
	}

	/**
	 * @Description: 查询该订单的所有支付记录
	 * @author 宋高俊
	 * @param orderid
	 * @return
	 * @date 2018年11月30日下午3:39:55
	 */
	@Override
	public List<WXCompanyPayment> selectByOrderLog(String orderid) {
		return wxCompanyPaymentMapper.selectByOrderLog(orderid);
	}

}
