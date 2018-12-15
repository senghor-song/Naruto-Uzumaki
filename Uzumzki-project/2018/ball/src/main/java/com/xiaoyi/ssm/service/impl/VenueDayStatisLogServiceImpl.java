package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueDayStatisLogMapper;
import com.xiaoyi.ssm.model.VenueDayStatisLog;
import com.xiaoyi.ssm.service.VenueDayStatisLogService;

/**
 * @Description: 统计每天收入业务逻辑层实现
 * @author 宋高俊
 * @date 2018年11月27日下午2:49:57
 */
@Service
public class VenueDayStatisLogServiceImpl extends AbstractService<VenueDayStatisLog,String> implements VenueDayStatisLogService{

	@Autowired
	private VenueDayStatisLogMapper venueDayStatisLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueDayStatisLogMapper);
	}
	/**
	 * @Description: 根据统计ID查询日志数量
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	@Override
	public int countVenueDay(String venueDayStatisId) {
		return venueDayStatisLogMapper.countVenueDay(venueDayStatisId);
	}


	/**
	 * @Description: 根据统计ID查询日志列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	@Override
	public List<VenueDayStatisLog> selectByVenueDay(String venueDayStatisId) {
		return venueDayStatisLogMapper.selectByVenueDay(venueDayStatisId);
	}


}
