package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.NewsError;

public interface NewsErrorMapper extends BaseMapper<NewsError, String> {

	/**  
	 * @Description: 统计资讯纠错
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午2:20:07 
	 */ 
	Integer countByNews(String id);

	/**  
	 * @Description: 查询资讯的纠错
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午2:22:57 
	 */
	List<NewsError> selectByNews(String id);
	
}