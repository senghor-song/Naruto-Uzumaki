package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TownPosterMapper;
import com.xiaoyi.ssm.model.TownPoster;
import com.xiaoyi.ssm.service.TownPosterService;

/**  
 * @Description: 新盘海报业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:04:54 
 */ 
@Service
public class TownPosterServiceImpl extends AbstractService<TownPoster,String> implements TownPosterService{

	@Autowired
	private TownPosterMapper townPosterMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(townPosterMapper);
	}

	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	@Override
	public Integer countByTown(String id) {
		return townPosterMapper.countByTown(id);
	}
}
