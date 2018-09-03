package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.WebSite;

/**  
 * @Description: 网站数据访问接口
 * @author 宋高俊  
 * @date 2018年7月20日 下午7:17:58 
 */ 
public interface WebSiteMapper extends BaseMapper<WebSite, String>{

	/**  
	 * @Description: 根据经济人查询网站已经绑定账号
	 * @author 宋高俊  
	 * @date 2018年7月20日 下午7:48:22 
	 */ 
	List<WebSite> selectByEmpAll(String empid);
}