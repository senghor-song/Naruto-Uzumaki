package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainOrderMapper;
import com.xiaoyi.ssm.model.TrainOrder;
import com.xiaoyi.ssm.service.TrainOrderService;

/**  
 * @Description: 培训课程订单业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainOrderServiceImpl extends AbstractService<TrainOrder,String> implements TrainOrderService{

	@Autowired
	private TrainOrderMapper trainOrderMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainOrderMapper);
	}
	
	/**  
	 * @Description: 根据课程id查询报名人员
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月9日 上午10:46:43 
	 */ 
	@Override
	public List<TrainOrder> selectByTrainCourseID(String id) {
		return trainOrderMapper.selectByTrainCourseID(id);
	}

	/**  
	 * @Description: 根据用户查询订单
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月9日 下午5:36:29 
	 */ 
	@Override
	public List<TrainOrder> selectByMember(String id) {
		return trainOrderMapper.selectByMember(id);
	}

	@Override
	public Integer countByMyTrainOrder(String id) {
		return trainOrderMapper.countByMyTrainOrder(id);
	}

	/**  
	 * @Description: 查询所有订单数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月11日 下午3:42:05 
	 */ 
	@Override
	public List<TrainOrder> selectAllAdmin() {
		return trainOrderMapper.selectAllAdmin();
	}
}
