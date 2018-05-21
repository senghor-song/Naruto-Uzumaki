package com.ruiec.springboot.service;

import java.util.Map;

import com.ruiec.springboot.util.ResponseDTO;

/**
 * 电影下载资源管理服务接口
 * @author 陈靖原<br>
 * @date 2017年11月21日 上午11:18:33
 */
public interface MovieResourcesService extends BaseService{
	/**
	 * 根据条件查询列表
	 * @author 陈靖原<br>
	 * @date 2017年11月26日 下午5:05:34
	 */
	ResponseDTO selectByPage(Map<String, Object> map);
}
