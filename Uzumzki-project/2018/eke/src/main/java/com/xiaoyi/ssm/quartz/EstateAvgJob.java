package com.xiaoyi.ssm.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoyi.ssm.coreFunction.AnJuKeUtil;
import com.xiaoyi.ssm.coreFunction.FangTianXiaUtil;
import com.xiaoyi.ssm.coreFunction.Tongcheng58Util;
import com.xiaoyi.ssm.model.Estate;
import com.xiaoyi.ssm.service.EstateService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;

/**
 * @Description: 小区均价获取定时任务
 * @author 宋高俊
 * @date 2018年7月25日 上午9:40:08
 */
public class EstateAvgJob {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**  
	 * @Description: 获取小区均价
	 * @author 宋高俊  
	 * @date 2018年7月25日 上午10:20:39 
	 */ 
	public void estateAvg() {
		LOG.info("开始获取小区均价----------------->");
		EstateService estateService = SpringUtils.getBean("estateServiceImpl", EstateService.class);
		List<Estate> list = estateService.selectByAll(null);
		for (int i = 0; i < list.size(); i++) {
			Estate estate = list.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put("avganjuke", AnJuKeUtil.getEstateAvgPrice(estate.getMatchan()));
			map.put("avg58", Tongcheng58Util.getEstateAvgPrice(estate.getMatch58()));
			map.put("avgfang", FangTianXiaUtil.getEstateAvgPrice(estate.getMatchfang()));
			RedisUtil.addRedis(Global.REDIS_ESTATE_AVG_MAP, estate.getId(), map);
		}
		LOG.info("获取小区均价完毕----------------->");
    }
}
