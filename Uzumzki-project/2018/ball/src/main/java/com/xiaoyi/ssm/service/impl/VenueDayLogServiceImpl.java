package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueDayLogMapper;
import com.xiaoyi.ssm.model.VenueDayLog;
import com.xiaoyi.ssm.service.VenueDayLogService;

/**
 * @Description: 统计每天收入业务逻辑层实现
 * @author 宋高俊
 * @date 2018年11月27日下午2:49:57
 */
@Service
public class VenueDayLogServiceImpl extends AbstractService<VenueDayLog,String> implements VenueDayLogService{

	@Autowired
	private VenueDayLogMapper venueDayLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueDayLogMapper);
	}
	/**
	 * @Description: 根据统计ID查询日志数量
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	@Override
	public int countVenueDay(String venueDayId) {
		return venueDayLogMapper.countVenueDay(venueDayId);
	}


	/**
	 * @Description: 根据统计ID查询日志列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	@Override
	public List<VenueDayLog> selectByVenueDay(String venueDayId) {
		return venueDayLogMapper.selectByVenueDay(venueDayId);
	}


}
