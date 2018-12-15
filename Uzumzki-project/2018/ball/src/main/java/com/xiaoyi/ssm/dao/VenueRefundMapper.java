package com.xiaoyi.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.VenueRefund;

public interface VenueRefundMapper extends BaseMapper<VenueRefund, String>{

	/**  
	 * @Description: 根据订单和状态查询
	 * @author 宋高俊
	 * @param orderid
	 * @return
	 * @date 2018年11月16日 下午5:24:59
	 */
	VenueRefund selectByOrder(@Param("orderid")String orderid, @Param("status")Integer status);

	/**
	 * @Description: 查询场馆的所有申请
	 * @author 宋高俊
	 * @param venueid
	 * @return
	 * @date 2018年11月16日 下午7:37:42
	 */
	List<VenueRefund> selectByVenue(@Param("venueid")String venueid, @Param("dateStart")Date dateStart, @Param("dateEnd")Date dateEnd);
	
	/**
	 * @Description: 统计申请数量
	 * @author 宋高俊
	 * @param venueid
	 * @return
	 * @date 2018年11月16日 下午8:18:15
	 */
	Integer countByVenue(String venueid);

	/**
	 * @Description: 查询处理时间超时的退款申请
	 * @author 宋高俊
	 * @param date
	 * @return
	 * @date 2018年11月20日 上午9:25:18
	 */
	List<VenueRefund> selectByTimeOut(@Param("dateStart")Date dateStart, @Param("dateEnd")Date dateEnd);
	
	/**
	 * @Description: 查询场馆未结算的申请
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月23日 下午4:05:44
	 */
	List<VenueRefund> selectBySettleVenue(@Param("venueid")String venueid, @Param("dateStart")Date dateStart, @Param("dateEnd")Date dateEnd);
	
	/**
	 * @Description: 根据回款人ID和日期查询退款记录
	 * @author 宋高俊
	 * @param memberId
	 * @param nowDate
	 * @return
	 * @date 2018年12月4日下午8:08:46
	 */
	List<VenueRefund> selectByMemberDate(@Param("memberId")String memberId, @Param("nowDate")String nowDate);
}