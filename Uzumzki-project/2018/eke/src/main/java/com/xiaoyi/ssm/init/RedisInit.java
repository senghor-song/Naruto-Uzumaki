package com.xiaoyi.ssm.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.xiaoyi.ssm.util.RedisUtil;

/**
 * 缓存初始化
 * @author 宋高俊<br>
 * @date 2018年1月23日 上午11:30:41
 */
public class RedisInit implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = Logger.getLogger(RedisInit.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		LOGGER.info("初始缓存数据开始 ------------------>");
		try {
			//初始化第一个缓存数据先清空缓存
			RedisUtil.removeAll();
			RedisUtil.initEstate();
			RedisUtil.initWeb();
			RedisUtil.initCity();
		} catch (Exception e) {
			LOGGER.info("初始化缓存数据时，出现错误："+e);
		}
		LOGGER.info("初始缓存数据结束 ------------------>");
	}
	
}
