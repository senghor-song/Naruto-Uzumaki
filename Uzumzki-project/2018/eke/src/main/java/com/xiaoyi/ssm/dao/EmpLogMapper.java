package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.EmpLog;

/**  
 * @Description: 经纪人变更记录访问接口
 * @author 宋高俊  
 * @date 2018年6月29日 下午6:04:35 
 */ 
public interface EmpLogMapper extends BaseMapper<EmpLog, String>{
	
	/**  
	 * @Description: 获取经纪人变更记录列表
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午6:04:48 
	 */ 
	List<EmpLog> selectAll(String empid);
}