package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MemberDayLogMapper;
import com.xiaoyi.ssm.model.MemberDayLog;
import com.xiaoyi.ssm.service.MemberDayLogService;

/**
 * @Description: 统计每天收入业务逻辑层实现
 * @author 宋高俊
 * @date 2018年11月27日下午2:49:57
 */
@Service
public class MemberDayLogServiceImpl extends AbstractService<MemberDayLog,String> implements MemberDayLogService{

	@Autowired
	private MemberDayLogMapper memberDayLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(memberDayLogMapper);
	}
	/**
	 * @Description: 根据统计ID查询日志数量
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	@Override
	public int countMemberDay(String memberDayId) {
		return memberDayLogMapper.countMemberDay(memberDayId);
	}


	/**
	 * @Description: 根据统计ID查询日志列表
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年11月27日下午3:16:59
	 */
	@Override
	public List<MemberDayLog> selectByMemberDay(String memberDayId) {
		return memberDayLogMapper.selectByMemberDay(memberDayId);
	}

	/**
	 * @Description: 根据对账ID查询最新的日志
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月12日下午8:48:55
	 */
	@Override
	public MemberDayLog selectByNowDate(String id) {
		return memberDayLogMapper.selectByNowDate(id);
	}

}
