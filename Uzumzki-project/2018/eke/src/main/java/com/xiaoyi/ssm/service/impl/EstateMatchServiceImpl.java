package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateMatchMapper;
import com.xiaoyi.ssm.model.EstateMatch;
import com.xiaoyi.ssm.service.EstateMatchService;

/**  
 * @Description: 商户变更日志
 * @author 宋高俊  
 * @date 2018年8月1日 上午10:27:35 
 */ 
@Service
public class EstateMatchServiceImpl extends AbstractService<EstateMatch,String> implements EstateMatchService{

	@Autowired
	private EstateMatchMapper estateMatchMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(estateMatchMapper);
	}

	/**  
	 * @Description: 查询该网站是否已经匹配过
	 * @author 宋高俊  
	 * @date 2018年8月1日 下午3:58:04 
	 */ 
	@Override
	public EstateMatch selectByEstateWeb(String estateId, String webId) {
		return estateMatchMapper.selectByEstateWeb(estateId, webId);
	}

}
