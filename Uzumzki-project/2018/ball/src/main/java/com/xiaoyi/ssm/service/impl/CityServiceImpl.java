package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CityMapper;
import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.service.CityService;

/**  
 * @Description: 城市业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月17日 上午10:43:11 
 */ 
@Service
public class CityServiceImpl extends AbstractService<City,String> implements CityService{

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(cityMapper);
	}
	/**  
	 * @Description: 根据城市首字母排序查询数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月20日 上午10:52:27 
	 */ 
	@Override
	public List<City> selectByInitial() {
		return cityMapper.selectByInitial();
	}

	/**  
	 * @Description: 查询五个热门城市
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月21日 下午7:50:53 
	 */ 
	@Override
	public List<City> selectByHotCity() {
		return cityMapper.selectByHotCity();
	}
	
	/**  
	 * @Description: 根据名称查询城市
	 * @author 宋高俊  
	 * @date 2018年9月25日 上午11:08:46 
	 */ 
	@Override
	public City selectByName(String name) {
		return cityMapper.selectByName(name);
	}

}
