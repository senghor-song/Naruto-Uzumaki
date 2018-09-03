package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.MassEmpImg;

/**  
 * @Description: 推房图库业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月30日 下午4:04:18 
 */ 
public interface MassEmpImgService extends BaseService<MassEmpImg, String> {
	
	/**  
	 * @Description: 统计推房图库
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午4:05:19 
	 */ 
	Integer countEmpImgByEmp(String empId);
}
