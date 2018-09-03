package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.dto.AdminNewsHeadDto;
import com.xiaoyi.ssm.model.NewsHead;

public interface NewsHeadMapper extends BaseMapper<NewsHead, String>{
	
	/**  
	 * @Description: 根据条件筛选数据
	 * @author 宋高俊  
	 * @date 2018年8月10日 上午9:14:56 
	 */ 
	List<NewsHead> selectBySearch(AdminNewsHeadDto adminNewsHeadDto);
}