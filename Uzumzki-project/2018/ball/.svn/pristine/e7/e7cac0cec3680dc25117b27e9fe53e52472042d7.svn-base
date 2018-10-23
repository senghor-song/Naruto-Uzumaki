package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MemberHabit;

public interface MemberHabitMapper extends BaseMapper<MemberHabit, String>{

	/**  
	 * @Description: 根据会员和场馆ID查询常去场馆
	 * @author 宋高俊  
	 * @param memberid
	 * @param venueid
	 * @return 
	 * @date 2018年9月26日 下午7:53:03 
	 */ 
	MemberHabit selectByMemberVenue(@Param("memberid")String memberid, @Param("venueid")String venueid);

	/**  
	 * @Description: 统计会员收藏场馆数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月18日 下午4:19:50 
	 */ 
	Integer countOften(String id);

}