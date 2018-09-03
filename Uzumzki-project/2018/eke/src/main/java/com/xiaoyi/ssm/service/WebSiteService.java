package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.WebSite;

/**  
 * @Description: 网站业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月20日 下午7:13:14 
 */ 
public interface WebSiteService extends BaseService<WebSite, String>{
	
	/**  
	 * @Description: 根据经济人查询网站已经绑定账号
	 * @author 宋高俊  
	 * @date 2018年7月20日 下午7:48:22 
	 */ 
	List<WebSite> selectByEmpAll(String empid);
	
}
