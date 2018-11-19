package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueLogMapper;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.service.VenueLogService;

/**  
 * @Description: 场馆业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class VenueLogServiceImpl extends AbstractService<VenueLog,String> implements VenueLogService{

	@Autowired
	private VenueLogMapper venueLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueLogMapper);
	}
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return venueLogMapper.countByVenue(id);
	}

	/**  
	 * @Description: 根据场馆ID获取日志数据
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午3:03:51 
	 */ 
	@Override
	public List<VenueLog> selectByVenue(String venueid) {
		return venueLogMapper.selectByVenue(venueid);
	}
	
	/**  
	 * @Description: 条件查询场馆日志数据
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月15日 下午3:12:12 
	 */ 
	@Override
	public List<VenueLog> selectBySearch(Integer selectType, String keyword) {
		return venueLogMapper.selectBySearch(selectType, keyword);
	}

}
