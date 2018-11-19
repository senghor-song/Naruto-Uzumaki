package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.NewsLog;

public interface NewsLogMapper extends BaseMapper<NewsLog, String> {

	/**  
	 * @Description: 统计资讯日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午2:20:07 
	 */ 
	Integer countByNews(String id);

	/**  
	 * @Description: 查询资讯的日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午2:22:57 
	 */ 
	List<NewsLog> selectByNews(String id);
	
}