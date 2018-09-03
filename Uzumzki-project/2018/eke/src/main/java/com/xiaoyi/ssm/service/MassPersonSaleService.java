package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.dto.MassPersonDto;
import com.xiaoyi.ssm.model.MassPersonSale;
import com.xiaoyi.ssm.model.MassPersonSaleEmpCondi;

/**  
 * @Description: 个人出售业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:15:23 
 */ 
public interface MassPersonSaleService extends BaseService<MassPersonSale, String> {

	/**  
	 * @Description: 条件筛选个人出售数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:30:47 
	 */ 
	List<MassPersonSale> selectAll(MassPersonDto massPersonDto);
	
	/**  
	 * @Description: 查询属于经济人的常用搜索条件数据
	 * @author 宋高俊  
	 * @date 2018年6月27日 下午4:31:04 
	 */ 
	List<MassPersonSaleEmpCondi> selectAllSearch(String empId);
	
	/**  
	 * @Description: 保存个人出售的搜索条件
	 * @author 宋高俊  
	 * @date 2018年6月30日 上午10:04:36 
	 */ 
	int insertMassPersonSaleEmpCondi(MassPersonSaleEmpCondi massPersonSaleEmpCondi);
	
	
	
}
