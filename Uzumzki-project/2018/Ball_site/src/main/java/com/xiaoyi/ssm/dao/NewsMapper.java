package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.News;

public interface NewsMapper extends BaseMapper<News, String> {

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