package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsMapper;
import com.xiaoyi.ssm.model.News;
import com.xiaoyi.ssm.service.NewsService;

/**  
 * @Description: 新闻资讯业务逻辑实现
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:06:31 
 */ 
@Service
public class NewsServiceImpl extends AbstractService<News,String> implements NewsService{

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsMapper);
	}

	/**  
	 * @Description: 查询用户收藏的资讯
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午5:45:19 
	 */ 
	@Override
	public List<News> selectByCollect(String id) {
		return newsMapper.selectByCollect(id);
	}

	/**  
	 * @Description: 查询所有资讯
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午5:45:19 
	 */
	@Override
	public List<News> selectByAdminAll() {
		return newsMapper.selectByAdminAll();
	}

}
