package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueLogMapper;
import com.xiaoyi.ssm.model.VenueLog;
import com.xiaoyi.ssm.service.VenueLogService;
import com.xiaoyi.ssm.util.Utils;

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
	
	/**
	 * @Description: 保存日志数据
	 * @author 宋高俊
	 * @return
	 * @date 2018年12月6日下午2:33:45
	 */
	@Override
	public int saveLog(String venueid, String managerid, String content) {
		VenueLog venueLog = new VenueLog();
		venueLog.setId(Utils.getUUID());
		venueLog.setCreatetime(new Date());
		venueLog.setVenueid(venueid);
		venueLog.setContent(content);
		venueLog.setManagerid(managerid);
		return venueLogMapper.insertSelective(venueLog);
	}

}
