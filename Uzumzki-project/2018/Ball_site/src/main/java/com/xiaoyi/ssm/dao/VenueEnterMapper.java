package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.VenueEnter;

public interface VenueEnterMapper extends BaseMapper<VenueEnter, String>{


	/**  
	 * @Description: 根据审核状态查询入驻申请
	 * @author 宋高俊  
	 * @param checkFlag
	 * @return 
	 * @date 2018年10月17日 下午4:59:31 
	 */ 
	List<VenueEnter> selectByEnterAll(Integer checkFlag);

	/**  
	 * @Description: 统计查询场馆入驻的申请数量
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月18日 下午2:19:30 
	 */ 
	Integer countEnter();
}