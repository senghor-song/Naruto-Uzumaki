package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.NewsBanner;

public interface NewsBannerMapper extends BaseMapper<NewsBanner, String>{
	
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