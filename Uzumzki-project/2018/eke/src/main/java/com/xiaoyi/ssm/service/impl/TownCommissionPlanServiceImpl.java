package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TownCommissionPlanMapper;
import com.xiaoyi.ssm.model.TownCommissionPlan;
import com.xiaoyi.ssm.service.TownCommissionPlanService;

/**  
 * @Description: 新盘佣金方案业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:04:54 
 */ 
@Service
public class TownCommissionPlanServiceImpl extends AbstractService<TownCommissionPlan,String> implements TownCommissionPlanService{

	@Autowired
	private TownCommissionPlanMapper townCommissionPlanMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(townCommissionPlanMapper);
	}
	
	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	@Override
	public Integer countByTown(String id) {
		return townCommissionPlanMapper.countByTown(id);
	}
	
}
