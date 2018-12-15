package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.VenueDayMapper;
import com.xiaoyi.ssm.dto.MemberDayDto;
import com.xiaoyi.ssm.model.VenueDay;
import com.xiaoyi.ssm.service.VenueDayService;

/**  
 * @Description: 统计每天场馆收入业务逻辑层实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class VenueDayServiceImpl extends AbstractService<VenueDay,String> implements VenueDayService{

	@Autowired
	private VenueDayMapper venueDayMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueDayMapper);
	}

	/**
	 * @Description: 根据日期查询当天的场馆统计数据
	 * @author 宋高俊
	 * @param nowDate
	 * @return
	 * @date 2018年11月26日下午8:04:35
	 */
	@Override
	public List<VenueDay> selectByNowDate(String nowDate) {
		return venueDayMapper.selectByNowDate(nowDate);
	}

	/**
	 * @Description: 根据时间统计当天的收支
	 * @author 宋高俊
	 * @param nowDate
	 * @return
	 * @date 2018年11月27日上午10:13:28
	 */
	@Override
	public MemberDayDto countVenueDay(String nowDate) {
		return venueDayMapper.countVenueDay(nowDate);
	}

}
