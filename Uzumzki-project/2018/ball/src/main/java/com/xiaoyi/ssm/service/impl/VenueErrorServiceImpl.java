package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueErrorMapper;
import com.xiaoyi.ssm.model.VenueError;
import com.xiaoyi.ssm.service.VenueErrorService;

/**  
 * @Description: 场馆纠错业务逻辑实现
 * @author 宋高俊  
 * @date 2018年10月16日 上午10:56:56 
 */ 
@Service
public class VenueErrorServiceImpl extends AbstractService<VenueError,String> implements VenueErrorService{

	@Autowired
	private VenueErrorMapper venueErrorMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueErrorMapper);
	}

	/**  
	 * @Description: 根据场馆统计纠错数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月17日 上午10:02:59 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return venueErrorMapper.countByVenue(id);
	}
	
}
