package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.Proposal;

/**  
 * @Description: 建议数据访问层
 * @author 宋高俊  
 * @date 2018年7月12日 下午12:29:00 
 */ 
public interface ProposalMapper extends BaseMapper<Proposal, String>{

	/**  
	 * @Description: 查询未处理的建议
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:05:43 
	 */ 
	List<Proposal> selectByNoDispose();

	/**  
	 * @Description: 统计未处理的建议数量
	 * @author 宋高俊  
	 * @date 2018年7月31日 上午11:07:13 
	 */ 
	Integer countByNoDispose();
	
}