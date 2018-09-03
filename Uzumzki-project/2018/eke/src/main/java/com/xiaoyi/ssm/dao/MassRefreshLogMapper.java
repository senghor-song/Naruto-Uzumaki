package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.MassRefreshDto;
import com.xiaoyi.ssm.model.MassRefreshLog;

/**  
 * @Description: 刷新日志数据访问层
 * @author 宋高俊  
 * @date 2018年7月4日 下午1:28:05 
 */ 
public interface MassRefreshLogMapper extends BaseMapper<MassRefreshLog, String>{
	
	/**  
	 * @Description: 获取所有的刷新日志
	 * @author 宋高俊  
	 * @date 2018年7月4日 下午1:48:45 
	 */ 
	List<MassRefreshLog> selectAll(MassRefreshDto massRefreshDto);
}