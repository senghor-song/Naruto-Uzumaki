package com.ruiec.web.service;


import java.util.List;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.Banner;

/**
 * 登录背景图片接口
 * @author yuankai
 * date 2017年12月02
 * */
public interface BannerService extends BaseService<Banner, String>{
    
	/**
	 * 查询默认登录背景图片接口
	 * @author yuankai
	 * Date 2018年1月26日 下午3:07:25
	 * */
	public int isShow();
   
	/**
	 * 修改默认登录背景图片接口
	 * @author yuankai
	 * Date 2018年1月26日 下午3:07:38
	 * */
	public void updateIsShow(Banner banner);
 
	/**
	 * 新增默认登录背景图片接口
	 * @author yuankai
	 * Date 2018年1月26日 下午3:07:10
	 * */
	public void saveBanner(Banner banner);
	

	/**
	 * 查询所有默认登录背景图片接口
	 * @author yuankai
	 * Date 2018年1月26日 下午3:07:25
	 * */
	List<Banner> isShow1();
	/**
	 * 删除不是默认登录背景图片接口
	 * @author yuankai
	 * Date 2017-12-02
	 * */
	public List<Banner> deleteIsShow();
	
	/**
	 * 默认登录背景图片服务类
	 * @author yuankai
	 * Date 2018年1月26日 下午3:07:43
	 * */
	public List<Banner> defaultPic();
}
