package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.NewsError;

/**  
 * @Description: 新闻资讯纠错
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:05:22 
 */ 
public interface NewsErrorService extends BaseService<NewsError, String> {

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
