package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.OrderMapper;
import com.xiaoyi.ssm.dto.AdminOrderDto;
import com.xiaoyi.ssm.dto.OrderDto;
import com.xiaoyi.ssm.model.Order;
import com.xiaoyi.ssm.service.OrderService;

/**  
 * @Description: 订单业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class OrderServiceImpl extends AbstractService<Order,String> implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(orderMapper);
	}
	/**  
	 * @Description: 判断该场地的单个时间段是否被预定
	 * @author 宋高俊  
	 * @date 2018年8月21日 下午8:21:06 
	 */ 
	@Override
	public Integer selectIsOrder(OrderDto orderDto) {
		return orderMapper.selectIsOrder(orderDto);
	}
	
	/**
	 * @Description: 根据会员查询订单数据
	 * @author 宋高俊
	 * @date 2018年8月22日 上午11:55:17
	 */
	@Override
	public List<Order> selectAll(String id, Integer type) {
		return orderMapper.selectAll(id, type);
	}
	
	/**  
	 * @Description: 根据会员和订单ID查询订单是否存在
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午3:15:50 
	 */ 
	@Override
	public Order selectByMemberOrder(String memberid, String orderid) {
		return orderMapper.selectByMemberOrder(memberid, orderid);
	}
	
	/**  
	 * @Description: 根据场馆ID查询总收入
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午7:41:52 
	 */ 
	@Override
	public Double countAmountByVenue(String venueid) {
		return orderMapper.countAmountByVenue(venueid);
	}

	/**  
	 * @Description: 根据场馆ID查询订场数据
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午8:37:48 
	 */ 
	@Override
	public List<Order> selectByVenue(String venueid) {
		return orderMapper.selectByVenue(venueid);
	}

	/**  
	 * @Description: 根据提现ID查询需要提现的订单
	 * @author 宋高俊  
	 * @date 2018年8月30日 下午2:11:06 
	 */ 
	@Override
	public List<Order> selectByAmount(String amountid) {
		return orderMapper.selectByAmount(amountid);
	}
	
	/**  
	 * @Description: 会员订场总数
	 * @author 宋高俊  
	 * @param member
	 * @return 
	 * @date 2018年11月3日 下午4:01:24 
	 */ 
	@Override
	public Integer countByMember(String memberid) {
		return orderMapper.countByMember(memberid);
	}
	
	/**  
	 * @Description: 查询确认时间超时的订单
	 * @author 宋高俊  
	 * @param preTime
	 * @return 
	 * @date 2018年11月8日 上午9:27:06 
	 */ 
	@Override
	public List<Order> selectByTimeOut(Date preTime, Integer type) {
		return orderMapper.selectByTimeOut(preTime, type);
	}

	/**
	 * @Description: 查询昨天的场馆订单
	 * @param venueId
	 * @param date
	 * @return
	 * @date 2018年11月15日14:34:50
	 */
	@Override
	public List<Order> selectByPayVenue(String venueId, String date) {
		return orderMapper.selectByPayVenue(venueId, date);
	}
	
	/**
	 * @Description: 修改昨天的场馆订单为已提现
	 * @param venueId
	 * @param date
	 * @return
	 * @date 2018年11月15日14:34:50
	 */
	@Override
	public int updateByPayVenue(String memberId, String newDate, String amountId) {
		return orderMapper.updateByPayVenue(memberId, newDate, amountId);
	}
	

	/**
	 * @Description: 查询某时间之内的已确认待消费的订单
	 * @author 宋高俊
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月20日 上午10:08:31
	 */
	@Override
	public List<Order> selectByDateDay(Date date) {
		return orderMapper.selectByDateDay(date);
	}
	
	/**
	 * @Description: 查询当天以后(含当天)所有的订单查询并结算退款
	 * @author 宋高俊
	 * @param nowDate
	 * @param venueid
	 * @return
	 * @date 2018年11月22日 上午9:48:35
	 */
	@Override
	public List<Order> selectByRelieveVenue(Date nowDate, String venueid) {
		return orderMapper.selectByRelieveVenue(nowDate, venueid);
	}
	
	/**
	 * @Description: 根据场馆和状态查询
	 * @author 宋高俊
	 * @param venueid
	 * @param type
	 * @return
	 * @date 2018年11月28日上午9:18:36
	 */
	@Override
	public List<Order> selectByVenueAll(String venueid, Integer type) {
		return orderMapper.selectByVenueAll(venueid, type);
	}
	
	/**
	 * @Description: 根据汇款人ID和日期查询订单
	 * @author 宋高俊
	 * @param memberId
	 * @param nowDate
	 * @return
	 * @date 2018年12月4日下午7:34:30
	 */
	@Override
	public List<Order> selectByMemberDate(String memberId, String nowDate) {
		return orderMapper.selectByMemberDate(memberId, nowDate);
	}
	
	/**
	 * @Description: 修改昨天待消费订单为已消费
	 * @author 宋高俊
	 * @param memberId
	 * @param newDate
	 * @return
	 * @date 2018年12月5日下午2:11:19
	 */
	@Override
	public int updateByOrdertype(String memberId, String nowDate) {
		return orderMapper.updateByOrdertype(memberId, nowDate);
	}

	/**
	 * @Description: 根据查询条件查询订单
	 * @author 宋高俊
	 * @param adminOrderDto
	 * @return
	 * @date 2018年12月7日下午3:04:38
	 */
	@Override
	public List<Order> selectBySearch(AdminOrderDto adminOrderDto) {
		return orderMapper.selectBySearch(adminOrderDto);
	}

}
