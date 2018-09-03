package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsBannerMapper;
import com.xiaoyi.ssm.model.NewsBanner;
import com.xiaoyi.ssm.service.NewsBannerService;

/**  
 * @Description: 横幅业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月26日 下午7:11:07 
 */ 
@Service
public class NewsBannerServiceImpl extends AbstractService<NewsBanner,String> implements NewsBannerService{

	@Autowired
	private NewsBannerMapper newsBannerMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsBannerMapper);
	}
	/**  
	 * @Description: 查询客户端首页三张轮播图
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:31:08 
	 */ 
	@Override
	public List<NewsBanner> selectCustByShow() {
		return newsBannerMapper.selectCustByShow();
	}

	/**  
	 * @Description: 查询经济端端首页三张轮播图
	 * @author 宋高俊  
	 * @date 2018年8月4日 下午12:31:08 
	 */ 
	@Override
	public List<NewsBanner> selectEmployeeByShow() {
		return newsBannerMapper.selectEmployeeByShow();
	}

}
