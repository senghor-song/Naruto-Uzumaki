package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueEnterMapper;
import com.xiaoyi.ssm.model.VenueEnter;
import com.xiaoyi.ssm.service.VenueEnterService;

/**  
 * @Description: 场馆入驻申请业务逻辑实现
 * @author 宋高俊  
 * @date 2018年10月17日 下午4:40:03 
 */ 
@Service
public class VenueEnterServiceImpl extends AbstractService<VenueEnter,String> implements VenueEnterService{

	@Autowired
	private VenueEnterMapper venueEnterMapper;
	
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueEnterMapper);
	}

	/**  
	 * @Description: 根据审核状态查询入驻申请
	 * @author 宋高俊  
	 * @param checkFlag
	 * @return 
	 * @date 2018年10月17日 下午4:59:31 
	 */ 
	@Override
	public List<VenueEnter> selectByEnterAll(Integer checkFlag) {
		return venueEnterMapper.selectByEnterAll(checkFlag);
	}

	/**  
	 * @Description: 统计查询场馆入驻的申请数量
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月18日 下午2:19:30 
	 */ 
	@Override
	public Integer countEnter() {
		return venueEnterMapper.countEnter();
	}
	
}
