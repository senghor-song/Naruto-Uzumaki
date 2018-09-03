package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TownLogMapper;
import com.xiaoyi.ssm.model.TownLog;
import com.xiaoyi.ssm.service.TownLogService;

/**  
 * @Description: 新盘日志业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:04:54 
 */ 
@Service
public class TownLogServiceImpl extends AbstractService<TownLog,String> implements TownLogService{

	@Autowired
	private TownLogMapper townLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(townLogMapper);
	}

	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	@Override
	public Integer countByTown(String id) {
		return townLogMapper.countByTown(id);
	}
	
}
