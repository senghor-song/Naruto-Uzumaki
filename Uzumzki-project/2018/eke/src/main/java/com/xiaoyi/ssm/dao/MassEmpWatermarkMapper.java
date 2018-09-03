package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.MassEmpWatermark;

/**  
 * @Description: 水印数据访问层
 * @author 宋高俊  
 * @date 2018年7月28日 下午4:05:33 
 */ 
public interface MassEmpWatermarkMapper extends BaseMapper<MassEmpWatermark, String> {
	
	/**  
	 * @Description: 根据经纪人查询水印
	 * @author 宋高俊  
	 * @date 2018年7月28日 下午4:10:57 
	 */ 
	Integer countWatermarkByEmpid(String empId);
}