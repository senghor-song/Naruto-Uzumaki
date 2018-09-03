package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.WebSiteMapper;
import com.xiaoyi.ssm.model.WebSite;
import com.xiaoyi.ssm.service.WebSiteService;

/**  
 * @Description: 网站业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月20日 下午7:20:26 
 */ 
@Service("webSiteServiceImpl")
public class WebSiteServiceImpl extends AbstractService<WebSite,String> implements WebSiteService{

	@Autowired
	private WebSiteMapper webSiteMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(webSiteMapper);
	}

	/**  
	 * @Description: 根据经济人查询网站已经绑定账号
	 * @author 宋高俊  
	 * @date 2018年7月20日 下午7:49:16 
	 */ 
	@Override
	public List<WebSite> selectByEmpAll(String empid) {
		return webSiteMapper.selectByEmpAll(empid);
	}

}
