package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.MassPersonBuyWantEmpCondi;

/**  
 * @Description: 个人求购数据访问接口
 * @author 宋高俊  
 * @date 2018年7月5日 上午9:45:49 
 */ 
public interface MassPersonBuyWantEmpCondiMapper extends BaseMapper<MassPersonBuyWantEmpCondi, String>{

	/**  
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:31:04 
	 */ 
	List<MassPersonBuyWantEmpCondi> selectAllSearch(String empId);
}