package com.xiaoyi.ssm.service;

import java.util.Date;
import java.util.List;

import com.xiaoyi.ssm.model.TrainOrderComment;

/**  
 * @Description: 培训课程评价业务逻辑层
 * @author 宋高俊  
 * @date 2018年10月9日 下午2:36:10 
 */ 
public interface TrainOrderCommentService extends BaseService<TrainOrderComment, String> {

	/**  
	 * @Description: 根据课时和订单ID查询是否已经发送过评价邀请
	 * @author 宋高俊  
	 * @param hour 当前课时数
	 * @param id 订单ID
	 * @return 
	 * @date 2018年10月9日 下午2:45:02 
	 */ 
	TrainOrderComment selectByHourOrder(int hour, String id);

	/**
	 * @Description: 根据订单ID获取所有评论
	 * @author 宋高俊
	 * @date 2018/10/10 0010 9:11
	 */
	List<TrainOrderComment> selectByOrder(String id);

	/**  
	 * @Description: 查询24小时前创建的评论
	 * @author 宋高俊  
	 * @param preTime
	 * @return 
	 * @date 2018年10月13日 上午9:31:28 
	 */ 
	List<TrainOrderComment> selectByTimeOut(Date preTime);

	/**  
	 * @Description: 根据培训机构ID统计60天的评论总数
	 * @author 宋高俊  
	 * @param id
	 * @param date
	 * @return 
	 * @date 2018年10月15日 上午9:58:33 
	 */ 
	Integer countByTeamId(String id, Date date);
	
	/**  
	 * @Description: 根据培训机构ID统计评论总数
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:52:44 
	 */ 
	Integer countByTeamAll(String id);

	/**  
	 * @Description: 查询培训机构最近10条评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	List<TrainOrderComment> selectByTeamTen(String id);
	
	/**  
	 * @Description: 查询培训机构评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	List<TrainOrderComment> selectByTeam(String id);
	
	/**  
	 * @Description: 查询课程评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	List<TrainOrderComment> selectByCourse(String id);

	/**  
	 * @Description: 查询教练评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	List<TrainOrderComment> selectByCoach(String id);
}
