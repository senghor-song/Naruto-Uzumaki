package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.MassEmpImg;

/**  
 * @Description: 推房图库数据访问层
 * @author 宋高俊  
 * @date 2018年7月30日 下午4:09:09 
 */ 
public interface MassEmpImgMapper extends BaseMapper<MassEmpImg, String> {

	/**  
	 * @Description: 统计推房图库
	 * @author 宋高俊  
	 * @date 2018年7月30日 下午4:09:04 
	 */ 
	Integer countEmpImgByEmp(String empId);
}