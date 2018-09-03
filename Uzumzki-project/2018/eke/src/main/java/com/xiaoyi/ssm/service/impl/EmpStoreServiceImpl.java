package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpStoreMapper;
import com.xiaoyi.ssm.dto.AdminStoreDto;
import com.xiaoyi.ssm.model.EmpStore;
import com.xiaoyi.ssm.service.EmpStoreService;

/**  
 * @Description: 商户业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月31日 上午9:35:59 
 */ 
@Service
public class EmpStoreServiceImpl extends AbstractService<EmpStore,String> implements EmpStoreService{

	@Autowired
	private EmpStoreMapper empStoreMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(empStoreMapper);
	}

	@Override
	public Integer countPropertyByStoreId(String id) {
		return empStoreMapper.countPropertyByStoreId(id);
	}

	@Override
	public Integer countEmpByStoreId(String id) {
		return empStoreMapper.countEmpByStoreId(id);
	}

	@Override
	public Double avgStoreByStoreId(String id) {
		return empStoreMapper.avgStoreByStoreId(id);
	}

	@Override
	public List<EmpStore> selectByType(String type) {
		return empStoreMapper.selectByType(type);
	}

	/**  
	 * @Description: 根据条件筛选数据
	 * @author 宋高俊  
	 * @date 2018年8月8日 下午7:57:42 
	 */ 
	@Override
	public List<EmpStore> selectBySearch(AdminStoreDto adminStore) {
		return empStoreMapper.selectBySearch(adminStore);
	}
}
