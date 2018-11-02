package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.Permission;

public interface PermissionMapper extends BaseMapper<Permission, String>{

	/**  
	 * @Description: 获取左侧菜单数据
	 * @author 宋高俊  
	 * @param rightType 
	 * @return 
	 * @date 2018年10月27日 下午5:50:45 
	 */ 
	List<Permission> selectByMenu(@Param("rightType")Integer rightType);

	/**  
	 * @Description: 根据权限和URL查询是否有权限访问
	 * @author 宋高俊  
	 * @param power
	 * @param requestUri
	 * @return 
	 * @date 2018年10月29日 上午10:07:30 
	 */ 
	Permission selectIsMenu(@Param("rightType")Integer rightType, @Param("requestUri")String requestUri);
}