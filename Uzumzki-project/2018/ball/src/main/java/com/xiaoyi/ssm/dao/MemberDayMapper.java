package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.MemberDayDto;
import com.xiaoyi.ssm.model.MemberDay;

public interface MemberDayMapper extends BaseMapper<MemberDay, String>{

	/**
	 * @Description: 根据日期查询统计数据
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月5日上午9:43:19
	 */
	List<MemberDay> selectByNowDate(String dateStr);

	/**
	 * @Description: 根据日期统计查询统计数据
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月5日上午10:05:37
	 */
	MemberDayDto countMemberDay(String dateStr);

	/**
	 * @Description: 根据日期和用户ID获取支付失败的回款
	 * @author 宋高俊
	 * @param memberId
	 * @param dateStr
	 * @return
	 * @date 2018年12月7日下午8:49:51
	 */
	MemberDay selectByMember(@Param("memberId")String memberId, @Param("dateStr")String dateStr); 
}