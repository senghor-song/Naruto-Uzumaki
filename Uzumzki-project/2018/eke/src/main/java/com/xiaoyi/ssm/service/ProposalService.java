package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.Proposal;

/**
 * @Description: 建议业务逻辑
 * @author 宋高俊
 * @date 2018年7月26日 下午10:54:54
 */
public interface ProposalService extends BaseService<Proposal, String>{
	
	/**  
	 * @Description: 查询未处理的建议
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:04:38 
	 */ 
	List<Proposal> selectByNoDispose();
	
	/**  
	 * @Description: 统计未处理的建议数量
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:04:38 
	 */ 
	Integer countByNoDispose();
}
