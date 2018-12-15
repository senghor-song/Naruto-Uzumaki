package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.VenueDayLog;

/**
 * @Description: 统计日志业务逻辑层
 * @author 宋高俊
 * @date 2018年11月27日下午2:47:43
 */
public interface VenueDayLogService extends BaseService<VenueDayLog, String> {

	/**
	 * @Description: 根据统计ID查询日志数量
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	int countVenueDay(String venueDayId);

	/**
	 * @Description: 根据统计ID查询日志列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	List<VenueDayLog> selectByVenueDay(String venueDayId);

}
