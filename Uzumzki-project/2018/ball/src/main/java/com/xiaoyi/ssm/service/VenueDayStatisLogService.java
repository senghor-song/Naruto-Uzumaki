package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.VenueDayStatisLog;

/**
 * @Description: 统计日志业务逻辑层
 * @author 宋高俊
 * @date 2018年11月27日下午2:47:43
 */
public interface VenueDayStatisLogService extends BaseService<VenueDayStatisLog, String> {

	/**
	 * @Description: 根据统计ID查询日志数量
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	int countVenueDay(String venueDayStatisId);

	/**
	 * @Description: 根据统计ID查询日志列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	List<VenueDayStatisLog> selectByVenueDay(String venueDayStatisId);

}
