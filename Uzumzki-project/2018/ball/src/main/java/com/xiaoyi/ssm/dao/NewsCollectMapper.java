package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.NewsCollect;

public interface NewsCollectMapper extends BaseMapper<NewsCollect, String>{
	/**  
	 * @Description: 删除收藏的新闻资讯
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	int deleteByIdAndMember(@Param("id")String id, @Param("memberid")String memberid);

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
	NewsCollect selectByNews(@Param("id")String id, @Param("memberid")String memberid);
}