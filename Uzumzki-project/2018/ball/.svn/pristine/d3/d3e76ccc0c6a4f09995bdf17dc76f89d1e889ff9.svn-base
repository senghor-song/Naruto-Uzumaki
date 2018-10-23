package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.AmountRefundWay;

public interface AmountRefundWayMapper extends BaseMapper< AmountRefundWay, String>{
	
	/**  
	 * @Description: 新增场馆时初始化退费数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月7日 下午5:48:20 
	 */ 
	int insertInitWay(String id);

	/**  
	 * @Description: 根据场馆ID查询退费费率
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月7日 下午5:58:06 
	 */ 
	List<AmountRefundWay> selectByVenue(String id);
}