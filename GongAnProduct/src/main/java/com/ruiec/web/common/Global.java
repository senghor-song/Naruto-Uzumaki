package com.ruiec.web.common;

import com.ruiec.web.util.SettingUtils;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * WEB全局变量,用于页面获取公共数据
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月24日
 */

public class Global {
	
	private String authorityCenterUrl;
	
	/**
	 * 获取系统配置
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	public Setting getSetting(){
		return SettingUtils.get();
	}
	
	/**
	 * 获取认证中心域名
	 * @author 肖学良<br>
	 * Date: 2016年1月17日
	 */
	public String getAuthorityCenterUrl() {
		return authorityCenterUrl;
	}

	/**
	 * 设置认证中心域名
	 * @author 肖学良<br>
	 * Date: 2016年1月17日
	 */
	public void setAuthorityCenterUrl(String authorityCenterUrl) {
		this.authorityCenterUrl = authorityCenterUrl;
	}
	
}
