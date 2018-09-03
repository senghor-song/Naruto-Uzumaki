package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.CombineLog;

public interface CombineLogMapper extends BaseMapper<CombineLog, String>{

	/**  
	 * @Description: 根据散拼ID查询日志数量
	 * @author 宋高俊  
	 * @date 2018年8月20日 上午10:05:17 
	 */ 
	Integer countByCombine(String id);

	/**  
	 * @Description: 根据散拼ID获取日志数据
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午4:18:35 
	 */ 
	List<CombineLog> selectByCombine(String id);

}