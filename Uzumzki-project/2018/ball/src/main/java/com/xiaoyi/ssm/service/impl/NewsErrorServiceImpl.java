package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsErrorMapper;
import com.xiaoyi.ssm.model.NewsError;
import com.xiaoyi.ssm.service.NewsErrorService;

/**  
 * @Description: 新闻资讯业务逻辑实现
 * @author 宋高俊  
 * @date 2018年10月23日 下午5:06:31 
 */ 
@Service
public class NewsErrorServiceImpl extends AbstractService<NewsError,String> implements NewsErrorService{

	@Autowired
	private NewsErrorMapper newsErrorMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsErrorMapper);
	}

	/**  
	 * @Description: 统计资讯纠错
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午2:20:07 
	 */ 
	@Override
	public Integer countByNews(String id) {
		return newsErrorMapper.countByNews(id);
	}

	/**  
	 * @Description: 查询资讯的纠错
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 下午2:22:57 
	 */
	@Override
	public List<NewsError> selectByNews(String id) {
		return newsErrorMapper.selectByNews(id);
	}


}
