package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueCoachMapper;
import com.xiaoyi.ssm.model.VenueCoach;
import com.xiaoyi.ssm.service.VenueCoachService;

/**  
 * @Description: 教练业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class VenueCoachServiceImpl extends AbstractService<VenueCoach,String> implements VenueCoachService{

	@Autowired
	private VenueCoachMapper venueCoachMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueCoachMapper);
	}
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return venueCoachMapper.countByVenue(id);
	}

	/**  
	 * @Description: 根据场馆查询培训的教练
	 * @author 宋高俊  
	 * @param venueid
	 * @return 
	 * @date 2018年10月30日 下午3:28:29 
	 */ 
	@Override
	public List<VenueCoach> selectByVenue(String venueid) {
		return venueCoachMapper.selectByVenue(venueid);
	}

}
