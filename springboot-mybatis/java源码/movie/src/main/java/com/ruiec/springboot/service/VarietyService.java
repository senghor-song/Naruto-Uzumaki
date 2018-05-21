package com.ruiec.springboot.service;

import java.util.Map;

import com.ruiec.springboot.util.ResponseDTO;

/**
 * 综艺管理业务逻辑接口服务类
 * @author Senghor<br>
 * @date 2017年11月16日 下午2:43:16
 */
public interface VarietyService extends BaseService{

	/**
	 * 根据条件查询列表
	 * @author Senghor<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	ResponseDTO selectByPage(Map<String, Object> map);
	
}
