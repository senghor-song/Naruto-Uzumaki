package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassBind;

/**
 * @Description: 网站绑定账号数据访问层
 * @author 宋高俊
 * @date 2018年6月29日 下午2:37:51
 */
public interface MassBindMapper extends BaseMapper<MassBind, String> {

	/**
	 * @Description: 根据经纪人查询绑定账号
	 * @author 宋高俊
	 * @date 2018年6月29日 下午2:38:20
	 */
	List<MassBind> selectByEmp(String empId);

	/**  
	 * @Description: 根据经纪人和网站查询数据
	 * @author 宋高俊  
	 * @date 2018年6月29日 下午3:54:07 
	 */ 
	List<MassBind> selectByEmpPlatform(@Param("empid") String empid, @Param("platform") String platform);
	
	/**  
	 * @Description: 根据经纪人获取绑定网站数据
	 * @author 宋高俊  
	 * @date 2018年7月28日 下午4:01:24 
	 */ 
	Integer countMassBindByEmp(String empId);
}