package com.xiaoyi.ssm.service;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 基类业务逻辑层
 * @author 宋高俊
 * @date 2018年6月27日 下午4:50:41
 */
public interface BaseService<T, ID extends Serializable> {
	
	/**  
	 * @Description: 设置要进行数据访问的mapper
	 * @author 宋高俊  
	 * @date 2018年7月10日 下午5:06:47 
	 */ 
	void setBaseMapper();

	/**
	 * @Description: 根据ID删除
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:03:56
	 */
	int deleteByPrimaryKey(ID id);

	/**
	 * @Description: 根据ID的数组删除
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:03:56
	 */
	int deleteByPrimaryKeys(ID[] ids);

	/**
	 * @Description: 新增完整数据
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:04:25
	 */
	int insert(T record);

	/**
	 * @Description: 根据现有的数据新增
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:04:40
	 */
	int insertSelective(T record);

	/**
	 * @Description: 根据符合条件的数据获取所有数据
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:04:50
	 */
	List<T> selectByAll(T record);

	/**
	 * @Description: 根据ID获取数据
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:05:23
	 */
	T selectByPrimaryKey(ID id);

	/**
	 * @Description: 根据现有的数据进行修改
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:05:35
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * @Description: 特殊数据类型修改方法
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:06:07
	 */
	int updateByPrimaryKeyWithBLOBs(T record);

	/**
	 * @Description: 根据ID修改所有数据
	 * @author 宋高俊
	 * @date 2018年7月10日 下午5:06:17
	 */
	int updateByPrimaryKey(T record);
}