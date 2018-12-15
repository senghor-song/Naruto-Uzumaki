package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.MemberDayLog;

/**
 * @Description: 统计日志业务逻辑层
 * @author 宋高俊
 * @date 2018年11月27日下午2:47:43
 */
public interface MemberDayLogService extends BaseService<MemberDayLog, String> {

	/**
	 * @Description: 根据统计ID查询日志数量
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	int countMemberDay(String memberDayId);

	/**
	 * @Description: 根据统计ID查询日志列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	List<MemberDayLog> selectByMemberDay(String memberDayId);

	/**
	 * @Description: 根据对账ID查询最新的日志
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月12日下午8:48:55
	 */
	MemberDayLog selectByNowDate(String id);

}
