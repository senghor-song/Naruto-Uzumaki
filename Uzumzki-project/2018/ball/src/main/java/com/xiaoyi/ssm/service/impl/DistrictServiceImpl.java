package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.DistrictMapper;
import com.xiaoyi.ssm.model.District;
import com.xiaoyi.ssm.service.DistrictService;

/**  
 * @Description: 区县业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class DistrictServiceImpl extends AbstractService<District,String> implements DistrictService{

	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(districtMapper);
	}

	@Override
	public List<District> selectByCityName(String name) {
		return districtMapper.selectByCityName(name);
	}

	/**  
	 * @Description: 根据城市ID查询区县
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月21日 下午4:34:39 
	 */ 
	@Override
	public List<District> selectByCityId(String id) {
		return districtMapper.selectByCityId(id);
	}

	/**  
	 * @Description: 根据区县名查询区县
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年9月25日 上午11:45:42 
	 */ 
	@Override
	public District selectByName(String name) {
		return districtMapper.selectByName(name);
	}

}
