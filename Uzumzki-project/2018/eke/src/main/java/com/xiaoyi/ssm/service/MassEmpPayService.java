package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.MassEmpPay;

/**  
 * @Description: 支付业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月6日 下午7:54:15 
 */ 
public interface MassEmpPayService extends BaseService<MassEmpPay, String> {
	
	/**  
	 * @Description: 经纪人ID
	 * @author 宋高俊  
	 * @date 2018年7月7日 下午1:42:31 
	 */ 
	List<MassEmpPay> selectAll(String empId);

    /**  
     * @Description: 获取支付价格
     * @author 宋高俊  
     * @date 2018年7月12日 上午11:47:14 
     */ 
    String selectByPay();
}
