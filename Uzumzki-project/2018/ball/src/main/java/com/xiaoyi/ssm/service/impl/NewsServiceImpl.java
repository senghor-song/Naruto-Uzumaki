package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsMapper;
import com.xiaoyi.ssm.model.News;
import com.xiaoyi.ssm.service.NewsService;

/**  
 * @Description: 新闻咨询业务逻辑实现
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

}
