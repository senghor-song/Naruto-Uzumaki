package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CoachMapper;
import com.xiaoyi.ssm.model.Coach;
import com.xiaoyi.ssm.service.CoachService;

/**  
 * @Description: 教练业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class CoachServiceImpl extends AbstractService<Coach,String> implements CoachService{

	@Autowired
	private CoachMapper coachMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(coachMapper);
	}
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return coachMapper.countByVenue(id);
	}

}
