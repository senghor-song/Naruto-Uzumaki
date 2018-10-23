package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainOrder;

/**  
 * @Description: 培训课程订单业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:04:23 
 */ 
public interface TrainOrderService extends BaseService<TrainOrder, String> {

	/**  
	 * @Description: 根据课程id查询报名人员
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月9日 上午10:46:43 
	 */ 
	List<TrainOrder> selectByTrainCourseID(String id);

	/**  
	 * @Description: 根据用户查询订单
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月9日 下午5:36:29 
	 */ 
	List<TrainOrder> selectByMember(String id);

	/**
	 * @Description: 根据用户ID统计报名课程
	 * @author 宋高俊
	 * @date 2018/10/10 0010 10:22
	 */
	Integer countByMyTrainOrder(String id);

	/**  
	 * @Description: 查询所有订单数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月11日 下午3:42:05 
	 */ 
	List<TrainOrder> selectAllAdmin();

}
