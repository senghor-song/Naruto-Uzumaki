package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.VenueDayStatisLog;

public interface VenueDayStatisLogMapper extends BaseMapper<VenueDayStatisLog, String>{
	
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