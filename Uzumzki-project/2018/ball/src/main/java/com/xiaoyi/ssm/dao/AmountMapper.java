package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.Amount;

public interface AmountMapper extends BaseMapper<Amount, String>{
	/**  
	 * @Description: 根据场馆ID查询过去3条提现记录
	 * @author 宋高俊  
	 * @date 2018年8月31日 下午4:45:57 
	 */ 
	List<Amount> selectByVenue(String venueid);

}