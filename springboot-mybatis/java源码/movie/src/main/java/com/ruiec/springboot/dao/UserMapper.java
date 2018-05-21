package com.ruiec.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper{
	/**
	 * 登录
	 * @author Senghor<br>
	 * @date 2017年11月21日 上午9:17:30
	 */
	List<Map<String, Object>> login(Map<String, Object> map);
}