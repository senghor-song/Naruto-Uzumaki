package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.OrderDto;
import com.xiaoyi.ssm.model.Order;

public interface OrderMapper extends BaseMapper<Order, String>{
	
	/**  
	 * @Description: 判断该场地的单个时间段是否被预定
	 * @author 宋高俊  
	 * @date 2018年8月21日 下午8:21:06 
	 */ 
	Integer selectIsOrder(OrderDto orderDto);
	
	/**
	 * @Description: 根据会员查询订单数据
	 * @author 宋高俊
	 * @date 2018年8月22日 上午11:55:17
	 */
	List<Order> selectAll(@Param("id")String id, @Param("type")Integer type);
	
	/**  
	 * @Description: 根据会员和订单ID查询订单是否存在
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午3:15:50 
	 */ 
	Order selectByMemberOrder(@Param("memberid")String memberid, @Param("orderid")String orderid);
	
	/**  
	 * @Description: 根据场馆ID查询总收入
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午7:41:52 
	 */ 
	Double countAmountByVenue(String venueid);
	
	/**  
	 * @Description: 根据场馆ID查询订场数据
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午8:37:48 
	 */ 
	List<Order> selectByVenue(String venueid);

	/**  
	 * @Description: 根据提现ID查询需要提现的订单
	 * @author 宋高俊  
	 * @date 2018年8月30日 下午2:11:06 
	 */ 
	List<Order> selectByAmount(String amountid);
}