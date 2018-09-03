package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.Combine;

public interface CombineMapper extends BaseMapper<Combine, String>{
	
	/**  
	 * @Description: 根据场馆ID查询总收入
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午7:41:52 
	 */ 
	Double countAmountByVenue(String venueid);

	/**  
	 * @Description: 根据场馆ID查询记录
	 * @author 宋高俊  
	 * @date 2018年8月24日 上午9:54:19 
	 */ 
	List<Combine> selectByVenue(String venueid);
	
	/**  
	 * @Description: 根据提现ID获取散拼数据
	 * @author 宋高俊  
	 * @date 2018年8月31日 上午10:58:51 
	 */ 
	List<Combine> selectByAmount(String amountid);
}