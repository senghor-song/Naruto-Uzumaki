package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonRent;
import com.xiaoyi.ssm.model.MassPersonRentEmpCondi;
/**  
 * @Description: 个人出租业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:13:35 
 */ 
public interface MassPersonRentService extends BaseService<MassPersonRent, String> {
	
	/**
	 * @Description: 条件筛选个人出租数据
	 * @author 宋高俊
	 * @date 2018年6月27日 下午4:30:47
	 */
	List<MassPersonRent> selectAll(MassPersonDto massPersonDto);
	
	/**  
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:31:04 
	 */ 
	List<MassPersonRentEmpCondi> selectAllSearch(String empId);

	/**  
	 * @Description: 保存个人出租的搜索条件
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午10:04:36 
	 */ 
	int insertMassPersonRentEmpCondi(MassPersonRentEmpCondi massPersonRentEmpCondi);
}

