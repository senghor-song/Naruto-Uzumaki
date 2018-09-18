package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateMapper;
import com.xiaoyi.ssm.dto.WebsiteSearchMapDto;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.service.EstateService;

/**  
 * @Description: 小区业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:16:41 
 */ 
@Service("estateServiceImpl")
public class EstateServiceImpl extends AbstractService<Estate,String> implements EstateService{

	@Autowired
	private EstateMapper estateMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(estateMapper);
	}

	@Override
	public Integer selectByStoreCount(String estateid) {
		return estateMapper.selectByStoreCount(estateid);
	}

	@Override
	public Integer selectByImageStorageCount(String estateid) {
		return estateMapper.selectByImageStorageCount(estateid);
	}

	@Override
	public Integer selectByEstateImageCount(String estateid) {
		return estateMapper.selectByEstateImageCount(estateid);
	}

	@Override
	public Estate selectByEstateName(String name) {
		return estateMapper.selectByEstateName(name);
	}

	@Override
	public List<Estate> selectByArea(String id) {
		return estateMapper.selectByArea(id);
	}

	@Override
	public List<Estate> selectBySearchArea(WebsiteSearchMapDto websitePropertyDto) {
		return estateMapper.selectBySearchArea(websitePropertyDto);
	}
	
}
