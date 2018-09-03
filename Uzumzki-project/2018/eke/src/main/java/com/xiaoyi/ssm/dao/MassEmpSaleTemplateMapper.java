package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassEmpSaleTemplate;

public interface MassEmpSaleTemplateMapper extends BaseMapper<MassEmpSaleTemplate, String>{

	/**  
	 * @Description: 根据经纪人id查询描述信息
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午4:03:50 
	 */ 
	List<MassEmpSaleTemplate> selectByEmp(@Param("empId")String empId, @Param("title")String title);
	
	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @date 2018年6月29日 上午11:40:16 
	 */ 
	int updateByEmp(MassEmpSaleTemplate massEmpSaleTemplate);
	
}