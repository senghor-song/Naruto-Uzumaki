package com.xiaoyi.ssm.quartz;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.service.TrainEnterService;
import com.xiaoyi.ssm.service.VenueCheckService;
import com.xiaoyi.ssm.service.VenueEnterService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;

/**
 * @Description: 后台顶部统计数据定时任务
 * @author 宋高俊
 * @date 2018年8月1日 下午4:44:47
 */
@Service
public class TopTagCountJob {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * @Description: 后台顶部统计数据定时任务
	 * @author 宋高俊
	 * @date 2018年8月1日 下午4:46:24
	 */
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void topTagCount() {
//		LOG.info("开始顶部统计数据----------------->");
		VenueCheckService venueCheckService = SpringUtils.getBean("venueCheckServiceImpl", VenueCheckService.class);
		VenueEnterService venueEnterService = SpringUtils.getBean("venueEnterServiceImpl", VenueEnterService.class);
		TrainEnterService trainEnterService = SpringUtils.getBean("trainEnterServiceImpl", TrainEnterService.class);
		Map<String, Object> map = new HashMap<>();
		map.put("venueCheck", venueCheckService.countCheck());// 待审核的教学场地
		map.put("venueEnter", venueEnterService.countEnter());// 场馆认领入驻
		map.put("trainEnter", trainEnterService.countEnter());// 培训机构入驻
		RedisUtil.addRedis(Global.REDIS_TOP_TAG_MAP, Global.REDIS_TOP_TAG_MAP, map);
//		LOG.info("顶部统计数据完毕----------------->");
	}
}
