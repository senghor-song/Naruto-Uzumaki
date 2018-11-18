package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueDayMapper;
import com.xiaoyi.ssm.model.VenueDay;
import com.xiaoyi.ssm.service.VenueDayService;

/**  
 * @Description: 统计每天场馆收入业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class VenueDayServiceImpl extends AbstractService<VenueDay,String> implements VenueDayService{

	@Autowired
	private VenueDayMapper venueDayMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueDayMapper);
	}

}
