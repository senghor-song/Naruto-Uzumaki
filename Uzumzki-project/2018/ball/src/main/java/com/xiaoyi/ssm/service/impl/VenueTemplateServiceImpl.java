package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueTemplateMapper;
import com.xiaoyi.ssm.model.VenueTemplate;
import com.xiaoyi.ssm.service.VenueTemplateService;

/**  
 * @Description: 场馆模板业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class VenueTemplateServiceImpl extends AbstractService<VenueTemplate,String> implements VenueTemplateService{

	@Autowired
	private VenueTemplateMapper venueTemplateMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueTemplateMapper);
	}
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return venueTemplateMapper.countByVenue(id);
	}

	/**  
	 * @Description: 根据管理员ID和场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:02:36 
	 */ 
	@Override
	public List<VenueTemplate> selectByVenue(String venueid) {
		return venueTemplateMapper.selectByVenue(venueid);
	}

	/**  
	 * @Description: 将场馆下的模板都设置成非默认模板
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月15日 下午2:31:32 
	 */ 
	@Override
	public Integer updateNoDefaultVenue(String id) {
		return venueTemplateMapper.updateNoDefaultVenue(id);
	}

}
