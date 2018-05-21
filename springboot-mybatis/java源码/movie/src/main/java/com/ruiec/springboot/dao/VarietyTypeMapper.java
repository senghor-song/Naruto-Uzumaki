package com.ruiec.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 综艺类型访问接口
 * @author Senghor<br>
 * @date 2017年11月20日 上午11:10:00
 */
@Mapper
public interface VarietyTypeMapper extends BaseMapper{
	/**
	 * 根据条件查询列表
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	List<Map<String, Object>> selectByPage(Map<String, Object> map);
}