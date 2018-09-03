package com.xiaoyi.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.MassBind;

/**  
 * @Description: 网站绑定账号业务逻辑接口
 * @author 宋高俊  
 * @date 2018年6月29日 下午1:38:33 
 */ 
public interface MassBindService extends BaseService<MassBind, String> {
	
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
}
