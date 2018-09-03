package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.EstateMatch;

/**  
 * @Description: 小区匹配业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月1日 下午2:28:56 
 */ 
public interface EstateMatchService extends BaseService<EstateMatch, String>{

	/**  
	 * @Description: 查询该网站是否已经匹配过
	 * @author 宋高俊  
	 * @date 2018年8月1日 下午3:58:04 
	 */ 
	EstateMatch selectByEstateWeb(String estateId, String webId);
	
}
