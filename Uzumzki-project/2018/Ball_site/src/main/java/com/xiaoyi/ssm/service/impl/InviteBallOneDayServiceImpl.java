package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.InviteBallOneDayMapper;
import com.xiaoyi.ssm.model.InviteBallOneDay;
import com.xiaoyi.ssm.service.InviteBallOneDayService;

/**  
 * @Description: 日统计活动收费
 * @author 宋高俊  
 * @date 2018年9月28日 下午4:57:34 
 */ 
@Service
public class InviteBallOneDayServiceImpl extends AbstractService<InviteBallOneDay,String> implements InviteBallOneDayService{

	@Autowired
	private InviteBallOneDayMapper inviteBallOneDayMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(inviteBallOneDayMapper);
	}

}
