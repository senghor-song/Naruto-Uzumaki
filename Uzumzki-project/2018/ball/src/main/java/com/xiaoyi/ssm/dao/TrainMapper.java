package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.Train;

public interface TrainMapper extends BaseMapper<Train, String>{
	
	/**  
	 * @Description: 根据场馆ID查询培训次数
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午2:03:29 
	 */ 
	Integer countByVenue(String id);
	
	/**  
	 * @Description: 根据场馆ID查询总收入
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午7:41:52 
	 */ 
	Double countAmountByVenue(String venueid);

	/**  
	 * @Description: 根据场馆ID查询收入记录
	 * @author 宋高俊  
	 * @date 2018年8月24日 上午10:05:04 
	 */ 
	List<Train> selectByVenue(String venueid);
}