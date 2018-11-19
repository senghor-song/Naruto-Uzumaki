package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.NewsCollect;

/**  
 * @Description: 新闻资讯收藏
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:05:22 
 */ 
public interface NewsCollectService extends BaseService<NewsCollect, String> {

	/**  
	 * @Description: 删除收藏的新闻资讯
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	int deleteByIdAndMember(String id, String memberid);

	/**  
	 * @Description: 根据用户统计收藏的资讯数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午9:22:01 
	 */ 
	int countByMember(String id);

	/**  
	 * @Description: 根据用户查询该资讯是否被收藏
	 * @author 宋高俊  
	 * @param id
	 * @param memberid 
	 * @return 
	 * @date 2018年10月25日 上午10:21:26 
	 */ 
	NewsCollect selectByNews(String id, String memberid);
}
