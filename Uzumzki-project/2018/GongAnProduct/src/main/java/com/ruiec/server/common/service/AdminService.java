/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.service;

import java.util.Date;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.server.common.entity.Admin;

/**
 * 管理员服务接口
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年5月20日
 */
public interface AdminService extends BaseService<Admin, String>{
	
	/**
	 * 按用户名和密码查询管理员
	 * @author 肖学良<br>
	 * Date: 2016年4月8日
	 */
	public Admin findByUsenameAndPassword(String username,String password);
	
	/**
	 * 按用户名查询管理员
	 * @author 肖学良<br>
	 * Date: 2016年4月8日
	 */
	public Admin findByUsename(String username);
	
	/**
	 * 更新登录时间，登录IP
	 * @author 熊华松<br>
	 * Date: 2016年7月15日
	 */
	public void updateLoginDateAndLoginIp(Date loginDate, String loginIp, String username);
	
}
