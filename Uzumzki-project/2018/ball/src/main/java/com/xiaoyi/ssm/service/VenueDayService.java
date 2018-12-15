package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.MemberDayDto;
import com.xiaoyi.ssm.model.VenueDay;

/**  
 * @Description: 统计每天场馆收入业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午5:59:40 
 */ 
public interface VenueDayService extends BaseService<VenueDay, String> {

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
