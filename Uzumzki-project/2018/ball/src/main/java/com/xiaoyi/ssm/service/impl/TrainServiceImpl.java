package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainMapper;
import com.xiaoyi.ssm.model.Train;
import com.xiaoyi.ssm.service.TrainService;

/**  
 * @Description: 培训业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月20日 上午11:11:36 
 */ 
@Service
public class TrainServiceImpl extends AbstractService<Train,String> implements TrainService{

	@Autowired
	private TrainMapper trainMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainMapper);
	}

	/**  
	 * @Description: 根据场馆ID查询培训次数
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午2:03:29 
	 */ 
	@Override
	public Integer countByVenue(String id) {
		return trainMapper.countByVenue(id);
	}

	/**  
	 * @Description: 根据场馆ID查询总收入
	 * @author 宋高俊  
	 * @date 2018年8月23日 下午7:41:52 
	 */
	@Override
	public Double countAmountByVenue(String venueid) {
		return trainMapper.countAmountByVenue(venueid);
	}

	/**  
	 * @Description: 根据场馆ID查询收入记录
	 * @author 宋高俊  
	 * @date 2018年8月24日 上午10:05:04 
	 */ 
	@Override
	public List<Train> selectByVenue(String venueid) {
		return trainMapper.selectByVenue(venueid);
	}

}
