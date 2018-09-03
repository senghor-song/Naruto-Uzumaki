package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.TownPoster;

public interface TownPosterMapper extends BaseMapper<TownPoster, String>{

	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	Integer countByTown(String id);
}