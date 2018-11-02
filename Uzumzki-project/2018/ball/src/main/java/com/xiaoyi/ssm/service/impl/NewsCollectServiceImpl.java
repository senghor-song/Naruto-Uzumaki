package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsCollectMapper;
import com.xiaoyi.ssm.model.NewsCollect;
import com.xiaoyi.ssm.service.NewsCollectService;

/**  
 * @Description: 新闻资讯业务逻辑实现
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:06:31 
 */ 
@Service
public class NewsCollectServiceImpl extends AbstractService<NewsCollect,String> implements NewsCollectService{

	@Autowired
	private NewsCollectMapper newsCollectMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsCollectMapper);
	}
	
	/**  
	 * @Description: 删除收藏的新闻资讯
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	@Override
	public int deleteByIdAndMember(String id, String memberid) {
		return newsCollectMapper.deleteByIdAndMember(id, memberid);
	}

	/**  
	 * @Description: 根据用户统计收藏的资讯数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午9:22:01 
	 */ 
	@Override
	public int countByMember(String id) {
		return newsCollectMapper.countByMember(id);
	}

	/**  
	 * @Description: 根据用户查询该资讯是否被收藏
	 * @author 宋高俊  
	 * @param id
	 * @param memberid 
	 * @return 
	 * @date 2018年10月25日 上午10:21:26 
	 */ 
	@Override
	public NewsCollect selectByNews(String id, String memberid) {
		return newsCollectMapper.selectByNews(id, memberid);
	}

}
