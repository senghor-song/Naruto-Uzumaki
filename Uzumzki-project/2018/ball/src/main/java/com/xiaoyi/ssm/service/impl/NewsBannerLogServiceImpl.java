package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsBannerLogMapper;
import com.xiaoyi.ssm.model.NewsBannerLog;
import com.xiaoyi.ssm.service.NewsBannerLogService;

/**  
 * @Description: 横幅日志业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月26日 下午7:11:07 
 */ 
@Service
public class NewsBannerLogServiceImpl extends AbstractService<NewsBannerLog,String> implements NewsBannerLogService{

	@Autowired
	private NewsBannerLogMapper newsBannerLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsBannerLogMapper);
	}

	@Override
	public Integer countByBanner(String id) {
		return newsBannerLogMapper.countByBanner(id);
	}

}
