package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueRefundMapper;
import com.xiaoyi.ssm.model.VenueRefund;
import com.xiaoyi.ssm.service.VenueRefundService;

/**  
 * @Description: 场馆退费申请业务逻辑层
 * @author 宋高俊  
 * @date 2018年11月16日17:18:06
 */ 
@Service
public class VenueRefundServiceImpl extends AbstractService<VenueRefund,String> implements VenueRefundService{

	@Autowired
	private VenueRefundMapper venueRefundMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueRefundMapper);
	}

	/**  
	 * @Description: 根据订单和状态查询
	 * @author 宋高俊
	 * @param orderid
	 * @return
	 * @date 2018年11月16日 下午5:24:59
	 */
	@Override
	public VenueRefund selectByOrder(String orderid, Integer status) {
		return venueRefundMapper.selectByOrder(orderid, status);
	}
	
	/**
	 * @Description: 查询场馆的所有申请
	 * @author 宋高俊
	 * @param venueid
	 * @return
	 * @date 2018年11月16日 下午7:37:42
	 */
	@Override
	public List<VenueRefund> selectByVenue(String venueid, Date dateStart, Date dateEnd) {
		return venueRefundMapper.selectByVenue(venueid, dateStart, dateEnd);
	}
	
	/**
	 * @Description: 统计申请数量
	 * @author 宋高俊
	 * @param venueid
	 * @return
	 * @date 2018年11月16日 下午8:18:15
	 */
	@Override
	public Integer countByVenue(String venueid) {
		return venueRefundMapper.countByVenue(venueid);
	}

	/**
	 * @Description: 查询处理时间超时的退款申请
	 * @author 宋高俊
	 * @param date
	 * @return
	 * @date 2018年11月20日 上午9:25:18
	 */
	@Override
	public List<VenueRefund> selectByTimeOut(Date dateStart, Date dateEnd) {
		return venueRefundMapper.selectByTimeOut(dateStart, dateEnd);
	}
	
	/**
	 * @Description: 查询场馆未结算的申请
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @date 2018年11月23日 下午4:05:44
	 */
	@Override
	public List<VenueRefund> selectBySettleVenue(String venueid, Date dateStart, Date dateEnd) {
		return venueRefundMapper.selectBySettleVenue(venueid, dateStart, dateEnd);
	}

	/**
	 * @Description: 根据回款人ID和日期查询退款记录
	 * @author 宋高俊
	 * @param memberId
	 * @param nowDate
	 * @return
	 * @date 2018年12月4日下午8:08:46
	 */
	@Override
	public List<VenueRefund> selectByMemberDate(String memberId, String nowDate) {
		return venueRefundMapper.selectByMemberDate(memberId, nowDate);
	}

}
