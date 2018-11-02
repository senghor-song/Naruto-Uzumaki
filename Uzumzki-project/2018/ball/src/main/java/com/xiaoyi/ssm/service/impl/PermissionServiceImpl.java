package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.PermissionMapper;
import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.service.PermissionService;

/**  
 * @Description: 权限业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class PermissionServiceImpl extends AbstractService<Permission,String> implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(permissionMapper);
	}

	/**  
	 * @Description: 获取左侧菜单数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月27日 下午5:50:45 
	 */ 
	@Override
	public List<Permission> selectByMenu(Integer rightType) {
		return permissionMapper.selectByMenu(rightType);
	}

	/**  
	 * @Description: 根据权限和URL查询是否有权限访问
	 * @author 宋高俊  
	 * @param power
	 * @param requestUri
	 * @return 
	 * @date 2018年10月29日 上午10:07:30 
	 */ 
	@Override
	public Permission selectIsMenu(Integer rightType, String requestUri) {
		return permissionMapper.selectIsMenu(rightType, requestUri);
	}

}
