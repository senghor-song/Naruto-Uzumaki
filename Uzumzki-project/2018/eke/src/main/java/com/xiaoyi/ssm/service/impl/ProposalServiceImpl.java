package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.ProposalMapper;
import com.xiaoyi.ssm.model.Proposal;
import com.xiaoyi.ssm.service.ProposalService;

/**  
 * @Description: 建议业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月27日 上午10:31:57 
 */ 
@Service
public class ProposalServiceImpl extends AbstractService<Proposal,String> implements ProposalService{

	@Autowired
	private ProposalMapper proposalMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(proposalMapper);
	}

	@Override
	public List<Proposal> selectByNoDispose() {
		return proposalMapper.selectByNoDispose();
	}

	@Override
	public Integer countByNoDispose() {
		return proposalMapper.countByNoDispose();
	}
	
}
