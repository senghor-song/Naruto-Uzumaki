package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.AdminNewsHeadDto;
import com.xiaoyi.ssm.model.NewsHead;

/**  
 * @Description: 软文营销业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月20日 下午4:24:55 
 */ 
public interface NewsHeadService extends BaseService<NewsHead, String> {

	/**  
	 * @Description: 根据条件筛选数据
	 * @author 宋高俊  
	 * @date 2018年8月10日 上午9:14:56 
	 */ 
	List<NewsHead> selectBySearch(AdminNewsHeadDto adminNewsHeadDto);
	
}
