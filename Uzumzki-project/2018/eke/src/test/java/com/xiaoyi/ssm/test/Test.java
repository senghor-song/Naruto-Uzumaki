package com.xiaoyi.ssm.test;

import com.xiaoyi.ssm.model.City;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

public class Test {
	public static void main(String[] args) {
//		RedisUtil.addRedis(Global.REDIS_CITY_MAP, "song", "测试1");
		City city = new City();
		city.setCity("城市1");
		RedisUtil.addRedis(Global.REDIS_CITY_MAP, city.getCity(), city);
		String city2 = (String) RedisUtil.getRedisOne(Global.REDIS_CITY_MAP, city.getCity());
		System.out.println(city2);
	}
}
