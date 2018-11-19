package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.AmountMapper;
import com.xiaoyi.ssm.model.Amount;
import com.xiaoyi.ssm.service.AmountService;

/**  
 * @Description: 提现申请业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class AmountServiceImpl extends AbstractService<Amount,String> implements AmountService{

	@Autowired
	private AmountMapper amountMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(amountMapper);
	}
	
	/**  
	 * @Description: 根据场馆ID查询过去3条提现记录
	 * @author 宋高俊  
	 * @date 2018年8月31日 下午4:45:57 
	 */ 
	@Override
	public List<Amount> selectByVenue(String venueid) {
		return amountMapper.selectByVenue(venueid);
	}

}
