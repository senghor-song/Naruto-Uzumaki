package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.News;

/**  
 * @Description: 新闻资讯
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:05:22 
 */ 
public interface NewsService extends BaseService<News, String> {

	/**  
	 * @Description: 查询用户收藏的资讯
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午5:45:19 
	 */ 
	List<News> selectByCollect(String id);
	
	/**  
	 * @Description: 查询所有资讯
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午5:45:19 
	 */
	List<News> selectByAdminAll();
	
}
