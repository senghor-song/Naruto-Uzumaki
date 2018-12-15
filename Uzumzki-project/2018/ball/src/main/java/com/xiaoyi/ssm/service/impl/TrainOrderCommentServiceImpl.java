package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainOrderCommentMapper;
import com.xiaoyi.ssm.model.TrainOrderComment;
import com.xiaoyi.ssm.service.TrainOrderCommentService;

/**  
 * @Description: 培训课程评价业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainOrderCommentServiceImpl extends AbstractService<TrainOrderComment,String> implements TrainOrderCommentService{

	@Autowired
	private TrainOrderCommentMapper trainOrderCommentMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainOrderCommentMapper);
	}
	
	/**  
	 * @Description: 根据课时和订单ID查询是否已经发送过评价邀请
	 * @author 宋高俊  
	 * @param hour 当前课时数
	 * @param id 订单ID
	 * @return 
	 * @date 2018年10月9日 下午2:45:02 
	 */ 
	@Override
	public TrainOrderComment selectByHourOrder(int hour, String id) {
		return trainOrderCommentMapper.selectByHourOrder(hour, id);
	}

	/**
	 * @Description: 根据订单ID获取所有评论
	 * @author 宋高俊
	 * @date 2018/10/10 0010 9:11
	 */
	@Override
	public List<TrainOrderComment> selectByOrder(String id) {
		return trainOrderCommentMapper.selectByOrder(id);
	}

	/**  
	 * @Description: 查询24小时前创建的评论
	 * @author 宋高俊  
	 * @param preTime
	 * @return 
	 * @date 2018年10月13日 上午9:31:28 
	 */ 
	@Override
	public List<TrainOrderComment> selectByTimeOut(Date preTime) {
		return trainOrderCommentMapper.selectByTimeOut(preTime);
	}
	
	/**  
	 * @Description: 根据培训机构ID统计60天的评论总数
	 * @author 宋高俊  
	 * @param id
	 * @param date
	 * @return 
	 * @date 2018年10月15日 上午9:58:33 
	 */ 
	@Override
	public Integer countByTeamId(String id, Date date) {
		return trainOrderCommentMapper.countByTeamId(id, date);
	}
	
	/**  
	 * @Description: 根据培训机构ID统计评论总数
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:52:44 
	 */ 
	@Override
	public Integer countByTeamAll(String id) {
		return trainOrderCommentMapper.countByTeamAll(id);
	}

	/**  
	 * @Description: 查询培训机构最近10条评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	@Override
	public List<TrainOrderComment> selectByTeamTen(String id) {
		return trainOrderCommentMapper.selectByTeamTen(id);
	}

	/**  
	 * @Description: 查询培训机构评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	@Override
	public List<TrainOrderComment> selectByTeam(String id) {
		return trainOrderCommentMapper.selectByTeam(id);
	}

	/**  
	 * @Description: 查询课程评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	@Override
	public List<TrainOrderComment> selectByCourse(String id) {
		return trainOrderCommentMapper.selectByCourse(id);
	}
	
	/**  
	 * @Description: 查询教练评价
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月27日 上午9:53:23 
	 */ 
	@Override
	public List<TrainOrderComment> selectByCoach(String id) {
		return trainOrderCommentMapper.selectByCoach(id);
	}
	
}
