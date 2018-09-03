package com.ruiec.web.init;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.service.UserService;

/**
 * 缓存初始化
 * @author Senghor<br>
 * @date 2018年1月23日 上午11:30:41
 */
public class RedisInit implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = Logger.getLogger(RedisInit.class);

	@Resource
	private UserService userService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		LOGGER.info("初始缓存数据开始 ...");
		try {
			//初始化第一个缓存数据先清空缓存
			RedisUtil.removeAll();
			RedisUtil.initUser();
			RedisUtil.initUnit();
			RedisUtil.initDictionary();
			RedisUtil.initUserUnit();
			RedisUtil.initApiConfig();
		} catch (Exception e) {
			LOGGER.info("初始化缓存数据时，出现错误："+e);
		}
	}
	
}
