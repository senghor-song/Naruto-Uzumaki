package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonBuyWant;
import com.xiaoyi.ssm.model.MassPersonBuyWantEmpCondi;

/**
 * @Description: 个人求购业务逻辑接口
 * @author 宋高俊
 * @date 2018年6月25日 下午7:13:35
 */
public interface MassPersonBuyWantService extends BaseService<MassPersonBuyWant, String> {

	/**
	 * @Description: 条件筛选个人求购数据
	 * @author 宋高俊
	 * @date 2018年6月27日 下午4:30:47
	 */
	List<MassPersonBuyWant> selectAll(MassPersonDto massPersonDto);

	/**
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊
	 * @date 2018年6月27日 下午4:31:04
	 */
	List<MassPersonBuyWantEmpCondi> selectAllSearch(String empId);

	/**  
	 * @Description: 保存个人求购的搜索条件
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午10:04:36 
	 */ 
	int insertMassPersonBuyWantEmpCondi(MassPersonBuyWantEmpCondi massPersonBuytWantEmpCondi);
}
