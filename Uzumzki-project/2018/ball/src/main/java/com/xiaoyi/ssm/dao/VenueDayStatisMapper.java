package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.VenueDayStatis;

public interface VenueDayStatisMapper extends BaseMapper<VenueDayStatis, String>{

	/**
	 * @Description: 查询昨天的收入总计数据最新
	 * @author 宋高俊
	 * @param nowDate
	 * @return
	 * @date 2018年11月27日下午8:24:39
	 */
	VenueDayStatis selectByNowDate(String nowDate);
}