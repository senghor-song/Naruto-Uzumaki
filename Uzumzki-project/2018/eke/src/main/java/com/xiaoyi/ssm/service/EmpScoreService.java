package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.EmpScore;

/**  
 * @Description: 积分业务逻辑
 * @author 宋高俊  
 * @date 2018年7月30日 下午3:45:45 
 */ 
public interface EmpScoreService extends BaseService<EmpScore, String> {
	
	/**  
	 * @Description: 积分明细
	 * @author 宋高俊  
	 * @date 2018年8月9日 下午4:56:18 
	 */ 
	List<EmpScore> selectScoreByEmp(String id);
}
