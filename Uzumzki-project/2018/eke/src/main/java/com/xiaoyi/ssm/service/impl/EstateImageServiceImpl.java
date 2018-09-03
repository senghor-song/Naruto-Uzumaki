package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateImageMapper;
import com.xiaoyi.ssm.model.EstateImage;
import com.xiaoyi.ssm.service.EstateImageService;

/**  
 * @Description: 小区展示图业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月31日 下午6:05:43 
 */ 
@Service
public class EstateImageServiceImpl extends AbstractService<EstateImage,String> implements EstateImageService{

	@Autowired
	private EstateImageMapper estateImageMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(estateImageMapper);
	}

}
