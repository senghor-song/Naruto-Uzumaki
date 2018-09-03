package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.EmpScore;

/**  
 * @Description: 积分数据访问层
 * @author 宋高俊  
 * @date 2018年7月30日 下午3:53:18 
 */ 
public interface EmpScoreMapper extends BaseMapper<EmpScore, String>{

	/**  
	 * @Description: 根据经纪人查询积分明细
	 * @author 宋高俊  
	 * @date 2018年8月9日 下午5:01:35 
	 */ 
	List<EmpScore> selectScoreByEmp(String id);
	
}