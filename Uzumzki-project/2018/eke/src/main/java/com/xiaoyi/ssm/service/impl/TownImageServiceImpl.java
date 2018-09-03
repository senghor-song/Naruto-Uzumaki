package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TownImageMapper;
import com.xiaoyi.ssm.model.TownImage;
import com.xiaoyi.ssm.service.TownImageService;

/**  
 * @Description: 新盘图业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午4:04:54 
 */ 
@Service
public class TownImageServiceImpl extends AbstractService<TownImage,String> implements TownImageService{

	@Autowired
	private TownImageMapper townImageMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(townImageMapper);
	}
	
	/**  
	 * @Description: 根据新盘查询
	 * @author 宋高俊  
	 * @date 2018年8月3日 下午3:26:13 
	 */ 
	@Override
	public Integer countByTown(String id) {
		return townImageMapper.countByTown(id);
	}
	
}
