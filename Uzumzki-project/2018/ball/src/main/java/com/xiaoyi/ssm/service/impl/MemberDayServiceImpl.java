package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MemberDayMapper;
import com.xiaoyi.ssm.dto.MemberDayDto;
import com.xiaoyi.ssm.model.MemberDay;
import com.xiaoyi.ssm.service.MemberDayService;

/**
 * @Description: 统计每天收入业务逻辑层实现
 * @author 宋高俊
 * @date 2018年11月27日下午2:49:57
 */
@Service
public class MemberDayServiceImpl extends AbstractService<MemberDay,String> implements MemberDayService{

	@Autowired
	private MemberDayMapper memberDayMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(memberDayMapper);
	}

	/**
	 * @Description: 根据日期查询统计数据
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月5日上午9:43:19
	 */
	@Override
	public List<MemberDay> selectByNowDate(String dateStr) {
		return memberDayMapper.selectByNowDate(dateStr);
	}

	/**
	 * @Description: 根据日期统计查询统计数据
	 * @author 宋高俊
	 * @param dateStr
	 * @return
	 * @date 2018年12月5日上午10:05:37
	 */
	@Override
	public MemberDayDto countMemberDay(String dateStr) {
		return memberDayMapper.countMemberDay(dateStr);
	}

	/**
	 * @Description: 根据日期和用户ID获取支付失败的回款
	 * @author 宋高俊
	 * @param memberId
	 * @param dateStr
	 * @return
	 * @date 2018年12月7日下午8:49:51
	 */
	@Override
	public MemberDay selectByMember(String memberId, String dateStr) {
		return memberDayMapper.selectByMember(memberId, dateStr);
	}

}
