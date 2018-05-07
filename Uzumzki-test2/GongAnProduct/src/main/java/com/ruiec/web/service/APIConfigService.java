package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.APIConfig;

/**
 * 轨迹数据API接口配置DAO接口
 * 
 * @author bingo
 * @date 2018年1月19日 下午3:33:35
 */
public interface APIConfigService extends BaseService<APIConfig, Integer> {

	/**
	 * 获取所有api配置的id和名称
	 * @author 陈靖原<br>
	 * @date 2018年1月23日 上午11:03:08
	 */
	public List<Map<String, Object>> getApiConfig();
}
