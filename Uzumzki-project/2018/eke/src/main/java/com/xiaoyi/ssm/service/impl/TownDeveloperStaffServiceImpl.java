package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TownDeveloperStaffMapper;
import com.xiaoyi.ssm.model.TownDeveloperStaff;
import com.xiaoyi.ssm.service.TownDeveloperStaffService;

/**  
 * @Description: 新盘开发商账号业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:04:54 
 */ 
@Service
public class TownDeveloperStaffServiceImpl extends AbstractService<TownDeveloperStaff,String> implements TownDeveloperStaffService{

	@Autowired
	private TownDeveloperStaffMapper townDeveloperStaffMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(townDeveloperStaffMapper);
	}
	
	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	@Override
	public Integer countByTown(String id) {
		return townDeveloperStaffMapper.countByTown(id);
	}
}
