package com.xiaoyi.ssm.service;


import java.util.List;

import com.xiaoyi.ssm.model.MassEmpSaleTemplate;

/**  
 * @Description: 描述信息业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月28日 下午3:57:45 
 */ 
public interface MassEmpSaleTemplateService extends BaseService<MassEmpSaleTemplate, String> {
	
	/**  
	 * @Description: 根据经纪人id查询描述信息
	 * @author 宋高俊  
	 * @date 2018年6月28日 下午3:59:43 
	 */ 
	List<MassEmpSaleTemplate> selectByEmp(String empId,String title);
	
	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @date 2018年6月29日 上午11:40:16 
	 */ 
	int updateByEmp(MassEmpSaleTemplate massEmpSaleTemplate);
}
