package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.service.APIConfigService;

/**
 * api 轨迹数据API接口配置服务实现类
 * 
 * @author 陈靖原<br>
 * @date 2018年1月23日 上午9:43:45
 */
@Service("apiConfigServiceImpl")
public class APIConfigServiceImpl extends BaseServiceImpl<APIConfig, Integer> implements APIConfigService {

	/**
	 * 获取所有api配置的id和名称
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月23日 上午11:04:10
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> getApiConfig() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<APIConfig> apis = getAll();
		for (APIConfig apiConfig : apis) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", apiConfig.getId());
			map.put("name", apiConfig.getName());
			list.add(map);
		}
		return list;
	}

}
