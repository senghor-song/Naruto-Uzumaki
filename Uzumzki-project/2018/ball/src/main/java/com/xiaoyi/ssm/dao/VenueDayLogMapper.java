package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.VenueDayLog;

public interface VenueDayLogMapper extends BaseMapper<VenueDayLog, String>{
	
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