package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.AmountRefundWayMapper;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.service.AmountRefundWayService;

/**  
 * @Description: 退款费率逻辑实现
 * @author 宋高俊  
 * @date 2018年9月11日 下午5:01:56 
 */ 
@Service
public class AmountRefundWayServiceImpl extends AbstractService<AmountRefundWay,String> implements AmountRefundWayService{

	@Autowired
	private AmountRefundWayMapper amountRefundWayMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(amountRefundWayMapper);
	}

	/**  
	 * @Description: 根据场馆ID查询退费费率
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月7日 下午5:58:06 
	 */ 
	@Override
	public AmountRefundWay selectByVenue(String id) {
		return amountRefundWayMapper.selectByVenue(id);
	}

	/**
	 * @Description: 退费费率逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:51:49
	 */
	@Override
	public int updateByVenue(String venueid, String dateStr) {
		return amountRefundWayMapper.updateByVenue(venueid, dateStr);
	}

}
