package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsHeadMapper;
import com.xiaoyi.ssm.dto.AdminNewsHeadDto;
import com.xiaoyi.ssm.model.NewsHead;
import com.xiaoyi.ssm.service.NewsHeadService;

/**  
 * @Description: 公告业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月26日 下午7:11:07 
 */ 
@Service
public class NewsHeadServiceImpl extends AbstractService<NewsHead,String> implements NewsHeadService{

	@Autowired
	private NewsHeadMapper newsHeadMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsHeadMapper);
	}

	/**  
	 * @Description: 根据条件筛选数据
	 * @author 宋高俊  
	 * @date 2018年8月10日 上午9:14:56 
	 */ 
	@Override
	public List<NewsHead> selectBySearch(AdminNewsHeadDto adminNewsHeadDto) {
		return newsHeadMapper.selectBySearch(adminNewsHeadDto);
	}

}
