package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.VenueDayStatis;

/**
 * @Description: 统计每天收入业务逻辑层
 * @author 宋高俊
 * @date 2018年11月27日下午2:47:43
 */
public interface VenueDayStatisService extends BaseService<VenueDayStatis, String> {

	/**
	 * @Description: 查询昨天的收入总计数据最新
	 * @author 宋高俊
	 * @param nowDate
	 * @return
	 * @date 2018年11月27日下午8:24:39
	 */
	VenueDayStatis selectByNowDate(String nowDate);

}
