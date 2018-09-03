package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.NewsCustLogMapper;
import com.xiaoyi.ssm.model.NewsCustLog;
import com.xiaoyi.ssm.service.NewsCustLogService;

/**  
 * @Description: 新闻业务逻辑实现
 * @author 宋高俊  
 * @date 2018年6月26日 下午7:11:07 
 */ 
@Service
public class NewsCustLogServiceImpl extends AbstractService<NewsCustLog,String> implements NewsCustLogService{

	@Autowired
	private NewsCustLogMapper newsCustLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(newsCustLogMapper);
	}

	@Override
	public Integer countLogByNewsCust(String id) {
		return newsCustLogMapper.countLogByNewsCust(id);
	}

}
