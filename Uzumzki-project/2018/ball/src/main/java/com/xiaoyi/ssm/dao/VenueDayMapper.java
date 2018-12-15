package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.MemberDayDto;
import com.xiaoyi.ssm.model.VenueDay;

public interface VenueDayMapper extends BaseMapper<VenueDay, String>{

	/**
	 * @Description: 根据日期查询当天的场馆统计数据
	 * @author 宋高俊
	 * @param nowDate
	 * @return
	 * @date 2018年11月26日下午8:04:35
	 */
	List<VenueDay> selectByNowDate(String nowDate);

	/**
	 * @Description: 根据时间统计当天的收支
	 * @author 宋高俊
	 * @param nowDate
	 * @return
	 * @date 2018年11月27日上午10:13:28
	 */
	MemberDayDto countVenueDay(String nowDate);
}