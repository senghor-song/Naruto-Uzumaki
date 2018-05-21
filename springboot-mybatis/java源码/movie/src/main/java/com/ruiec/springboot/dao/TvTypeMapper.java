package com.ruiec.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 电视剧类型服务访问接口
 * @author qinzhitian<br>
 * @date 2017年11月16日 下午4:42:09
 */
@Mapper
public interface TvTypeMapper extends BaseMapper {
	/**
	 * 查询所有电视剧类型
	 * @author qinzhitian<br>
	 * @date 2017年11月28日 下午9:14:26
	 */
	List<Map<String, Object>> selectAll();
	/**
	 * 根据名称查询
	 * @author qinzhitian<br>
	 * @date 2017年11月29日 下午2:53:46
	 */
	Integer selectByName(Map<String, Object> obj);
}