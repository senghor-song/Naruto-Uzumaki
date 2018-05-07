package com.ruiec.web.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;

/**
 * 每间隔30分钟刷新缓存数据
 * @author Senghor<br>
 * @date 2017年12月27日 下午4:14:54
 */
@Component
@EnableScheduling
public class ThirtyRefreshUtilJob {

	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThirtyRefreshUtilJob.class);
	
	/**
	 * 30分钟刷新缓存数据一次
	 * @author Senghor<br>
	 * @date 2017年11月9日 下午3:47:22
	 */
	@Scheduled(cron = "0 0/30 * * * ? ")
	private void thirtyRefresh() {
		LOGGER.info("-------------30分钟刷新缓存数据定时任务开始---------------------------");
		RedisUtil.initUser();
		RedisUtil.initUnit();
		RedisUtil.initDictionary();
		RedisUtil.initUserUnit();
		RedisUtil.initApiConfig();
		LOGGER.info("-------------30分钟刷新缓存数据定时任务结束---------------------------");
	}

}
