package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueDayStatisMapper;
import com.xiaoyi.ssm.model.VenueDayStatis;
import com.xiaoyi.ssm.service.VenueDayStatisService;

/**
 * @Description: 统计每天收入业务逻辑层实现
 * @author 宋高俊
 * @date 2018年11月27日下午2:49:57
 */
@Service
public class VenueDayStatisServiceImpl extends AbstractService<VenueDayStatis,String> implements VenueDayStatisService{

	@Autowired
	private VenueDayStatisMapper venueDayStatisMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueDayStatisMapper);
	}

	/**
	 * @Description: 查询昨天的收入总计数据最新
	 * @author 宋高俊
	 * @param nowDate
	 * @return
	 * @date 2018年11月27日下午8:24:39
	 */
	@Override
	public VenueDayStatis selectByNowDate(String nowDate) {
		return venueDayStatisMapper.selectByNowDate(nowDate);
	}


}
