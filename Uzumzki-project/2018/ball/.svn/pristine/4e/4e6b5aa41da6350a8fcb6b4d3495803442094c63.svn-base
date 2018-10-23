package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.OrderMapper;
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
}
