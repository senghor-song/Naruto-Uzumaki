package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.NewsBanner;

/**  
 * @Description: 横幅业务逻辑接口
 * @author 宋高俊  
 * @date 2018年8月3日 上午11:19:34 
 */ 
public interface NewsBannerService extends BaseService<NewsBanner, String> {
	
	/**  
	 * @Description: 查询客户端首页三张轮播图
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:31:08 
	 */ 
	List<NewsBanner> selectCustByShow();
	
	/**  
	 * @Description: 查询经济端端首页三张轮播图
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:31:08 
	 */ 
	List<NewsBanner> selectEmployeeByShow();
}
