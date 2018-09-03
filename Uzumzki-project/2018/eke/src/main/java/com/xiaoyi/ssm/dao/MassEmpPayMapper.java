package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.MassEmpPay;

/**  
 * @Description: 用户支付流水数据访问层
 * @author 宋高俊  
 * @date 2018年7月7日 下午1:41:00 
 */ 
public interface MassEmpPayMapper extends BaseMapper<MassEmpPay, String> {
	
	/**  
	 * @Description: 经纪人ID
	 * @author 宋高俊  
	 * @date 2018年7月7日 下午1:42:31 
	 */ 
	List<MassEmpPay> selectAll(String empId);
	
}