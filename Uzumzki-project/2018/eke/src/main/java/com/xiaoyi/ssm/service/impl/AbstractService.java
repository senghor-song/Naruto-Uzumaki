package com.xiaoyi.ssm.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoyi.ssm.dao.BaseMapper;
import com.xiaoyi.ssm.service.BaseService;

/**  
 * @Description: 基类业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年6月27日 下午4:50:41 
 */ 
public abstract class AbstractService<T, ID extends Serializable> implements
		BaseService<T, ID> {
	
	@Autowired
	private BaseMapper<T, ID> baseMapper;

	public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public int deleteByPrimaryKey(ID id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKeys(ID[] ids) {
		if (ids.length > 0) {
			return baseMapper.deleteByPrimaryKeys(ids);
		}else {
			return 0;
		}
	}

	@Override
	public int insertSelective(T record) {
		return baseMapper.insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(ID id) {
		return baseMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<T> selectByAll(T record) {
		return baseMapper.selectByAll(record);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(T record) {
		return baseMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insert(T record) {
		return baseMapper.insert(record);
	}
}
