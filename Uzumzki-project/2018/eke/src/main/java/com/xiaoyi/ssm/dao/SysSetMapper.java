package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.SysSet;

/**  
 * @Description: 系统配置数据访问接口
 * @author 宋高俊  
 * @date 2018年7月12日 上午11:45:53 
 */ 
public interface SysSetMapper extends BaseMapper<SysSet, String>{
	
    /**  
     * @Description: 获取支付价格
     * @author 宋高俊  
     * @date 2018年7月12日 上午11:47:14 
     */ 
    String selectByPay();
}