package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainTeamLog;

/**  
 * @Description: 培训机构日志业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:04:23 
 */ 
public interface TrainTeamLogService extends BaseService<TrainTeamLog, String> {
	/**  
	 * @Description: 根据培训机构统计日志数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月11日 下午8:52:37 
	 */ 
	int countByTeam(String id);

	/**  
	 * @Description: 根据培训机构获取日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 下午8:27:28 
	 */ 
	List<TrainTeamLog> selectByTeam(String id);
}
