package com.xiaoyi.ssm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTeamPhoneMapper;
import com.xiaoyi.ssm.model.TrainTeamPhone;
import com.xiaoyi.ssm.service.TrainTeamPhoneService;

/**  
 * @Description: 培训机构业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTeamPhoneServiceImpl extends AbstractService<TrainTeamPhone,String> implements TrainTeamPhoneService{

	@Autowired
	private TrainTeamPhoneMapper trainTeamPhoneMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTeamPhoneMapper);
	}
	
	/**  
	 * @Description: 根据培训机构ID统计60天培训机构致电记录
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午5:14:49 
	 */ 
	@Override
	public int countByTeamId(String id, Date date) {
		return trainTeamPhoneMapper.countByTeamId(id, date);
	}

}
