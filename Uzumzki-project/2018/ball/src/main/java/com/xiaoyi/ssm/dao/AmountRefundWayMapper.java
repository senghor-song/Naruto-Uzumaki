package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.AmountRefundWay;

public interface AmountRefundWayMapper extends BaseMapper< AmountRefundWay, String>{
	
	/**  
	 * @Description: 根据场馆ID查询退费费率
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月7日 下午5:58:06 
	 */ 
	AmountRefundWay selectByVenue(String id);

	/**
	 * @Description: 退费费率逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:51:49
	 */
	int updateByVenue(@Param("venueid")String venueid, @Param("dateStr")String dateStr);
}