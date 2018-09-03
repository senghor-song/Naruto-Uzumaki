package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.MassRefreshDto;
import com.xiaoyi.ssm.model.MassRefreshLog;

/**  
 * @Description: 刷新日志业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月28日 上午10:40:19 
 */ 
public interface MassRefreshLogService extends BaseService<MassRefreshLog, String> {
	
	/**  
	 * @Description: 获取所有的刷新日志数据
	 * @author 宋高俊  
	 * @date 2018年7月4日 下午3:01:48 
	 */ 
	List<MassRefreshLog> selectAll(MassRefreshDto massRefreshDto);
}
