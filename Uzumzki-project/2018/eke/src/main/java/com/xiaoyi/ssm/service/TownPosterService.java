package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.TownPoster;

/**  
 * @Description: 新盘海报业务逻辑层
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:03:17 
 */ 
public interface TownPosterService extends BaseService<TownPoster, String> {

	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	Integer countByTown(String id);

}
