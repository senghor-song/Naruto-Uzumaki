package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.Permission;

/**
 * @Description: 权限业务逻辑层
 * @author 宋高俊
 * @date 2018年8月16日 下午5:59:40
 */
public interface PermissionService extends BaseService<Permission, String> {

	/**  
	 * @Description: 获取左侧菜单数据
	 * @author 宋高俊  
	 * @param rightType 
	 * @return 
	 * @date 2018年10月27日 下午5:50:45 
	 */ 
	List<Permission> selectByMenu(Integer rightType);

	/**  
	 * @Description: 根据权限和URL查询是否有权限访问
	 * @author 宋高俊  
	 * @param power
	 * @param requestUri
	 * @return 
	 * @date 2018年10月29日 上午10:07:30 
	 */ 
	Permission selectIsMenu(Integer rightType, String requestUri);

	/**  
	 * @Description: 查询后台权限页面数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月3日 上午10:59:53 
	 */ 
	List<Permission> selectByAdmin();

	/**  
	 * @Description: 根据用户级别和菜单ID查询子级权限
	 * @author 宋高俊  
	 * @param power
	 * @param parentid
	 * @return 
	 * @date 2018年11月3日 下午2:12:06 
	 */ 
	List<Permission> selectByBtu(Integer power, String parentid);

}
