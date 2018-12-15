package com.xiaoyi.ssm.service;

import java.util.Date;
import java.util.List;

import com.xiaoyi.ssm.dto.AdminOrderDto;
import com.xiaoyi.ssm.dto.OrderDto;
import com.xiaoyi.ssm.model.Order;

/**
 * @Description: 订单业务逻辑层
 * @author 宋高俊
 * @date 2018年8月16日 下午5:59:40
 */
public interface OrderService extends BaseService<Order, String> {

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
	List<Order> selectAll(String id, Integer type);

	/**  
	 * @Description: 根据会员和订单ID查询订单是否存在
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午3:15:50 
	 */ 
	Order selectByMemberOrder(String memberid, String orderid);

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

	/**  
	 * @Description: 会员订场总数
	 * @author 宋高俊  
	 * @param member
	 * @return 
	 * @date 2018年11月3日 下午4:01:24 
	 */ 
	Integer countByMember(String memberid);

	/**  
	 * @Description: 查询确认时间超时的订单
	 * @author 宋高俊  
	 * @param preTime
	 * @return 
	 * @date 2018年11月8日 上午9:27:06 
	 */ 
	List<Order> selectByTimeOut(Date preTime,Integer type);

	/**
	 * @Description: 查询昨天的场馆订单
	 * @param venueId
	 * @param date
	 * @return
	 * @date 2018年11月15日14:34:50
	 */
	List<Order> selectByPayVenue(String venueId, String date);

	/**
	 * @Description: 修改昨天的场馆订单为已提现
	 * @param venueId
	 * @param date
	 * @return
	 * @date 2018年11月15日14:34:50
	 */
	int updateByPayVenue(String memberId, String newDate, String amountId);
	
	/**
	 * @Description: 查询某时间之内的已确认待消费的订单
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月20日 上午10:08:31
	 */
	List<Order> selectByDateDay(Date date);

	/**
	 * @Description: 查询当天以后(含当天)所有的订单查询并结算退款
	 * @author 宋高俊
	 * @param nowDate
	 * @param venueid
	 * @return
	 * @date 2018年11月22日 上午9:48:35
	 */
	List<Order> selectByRelieveVenue(Date nowDate, String venueid);

	/**
	 * @Description: 根据场馆和状态查询
	 * @author 宋高俊
	 * @param venueid
	 * @param type
	 * @return
	 * @date 2018年11月28日上午9:18:36
	 */
	List<Order> selectByVenueAll(String venueid, Integer type);

	/**
	 * @Description: 根据汇款人ID和日期查询订单
	 * @author 宋高俊
	 * @param memberId
	 * @param nowDate
	 * @return
	 * @date 2018年12月4日下午7:34:30
	 */
	List<Order> selectByMemberDate(String memberId, String nowDate);

	/**
	 * @Description: 修改昨天待消费订单为已消费
	 * @author 宋高俊
	 * @param memberId
	 * @param nowDate
	 * @return
	 * @date 2018年12月5日下午2:11:19
	 */
	int updateByOrdertype(String memberId, String nowDate);

	/**
	 * @Description: 根据查询条件查询订单
	 * @author 宋高俊
	 * @param adminOrderDto
	 * @return
	 * @date 2018年12月7日下午3:04:38
	 */
	List<Order> selectBySearch(AdminOrderDto adminOrderDto);
}
