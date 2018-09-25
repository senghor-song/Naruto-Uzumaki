/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.service;

import java.util.List;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.server.common.entity.SysConfig;

/**
 * 系统配置项服务接口
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月22日
 */
public interface SysConfigService extends BaseService<SysConfig, String>{
	
	/**
	 * 批量更新系统配置
	 * @author 肖学良<br>
	 * Date: 2016年1月6日
	 */
	public void update(List<SysConfig> sysConfigs);
}
