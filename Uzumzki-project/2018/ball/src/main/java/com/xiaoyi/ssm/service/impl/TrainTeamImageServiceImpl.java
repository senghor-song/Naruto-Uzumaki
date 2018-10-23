package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTeamImageMapper;
import com.xiaoyi.ssm.model.TrainTeamImage;
import com.xiaoyi.ssm.service.TrainTeamImageService;

/**  
 * @Description: 培训机构业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTeamImageServiceImpl extends AbstractService<TrainTeamImage,String> implements TrainTeamImageService{

	@Autowired
	private TrainTeamImageMapper trainTeamImageMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTeamImageMapper);
	}

	/** 
	 * @Description: 根据培训机构ID获取培训机构环境
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	@Override
	public List<TrainTeamImage> selectByTrainTeamID(String id) {
		return trainTeamImageMapper.selectByTrainTeamID(id);
	}

	@Override
	public int deleteByTeamAll(String id) {
		return trainTeamImageMapper.deleteByTeamAll(id);
	}

}
