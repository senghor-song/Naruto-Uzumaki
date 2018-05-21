package com.ruiec.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 电影语言类型访问接口
 * @author 陈靖原<br>
 * @date 2017年11月30日 上午9:45:56
 */
@Mapper
public interface MovieLanguageMapper extends BaseMapper{
	/**
	 * 根据条件查询列表
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午22:14:34
	 */
	List<Map<String, Object>> selectByPage(Map<String, Object> map);
	
	/**
	 * 查询所有电视剧类型
	 * @author 陈靖原<br>
	 * @date 2017年11月30日 下午9:14:26
	 */
	List<Map<String, Object>> selectAll();
}