/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.service;

import java.util.List;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.server.common.entity.Menu;

/**
 * 功能菜单服务接口
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月22日
 */
public interface MenuService extends BaseService<Menu, String>{
	
	/**
	 * 根据菜单id查询该id菜单的所有子菜单
	 * @param id 菜单id
	 * @return 当id为空时返回所有顶级菜单，不为空时返回该菜单所有子菜单
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public List<Menu> findMenu(String id);
}
