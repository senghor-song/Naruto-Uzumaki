package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainCourseMapper;
import com.xiaoyi.ssm.model.TrainCourse;
import com.xiaoyi.ssm.service.TrainCourseService;

/**  
 * @Description: 培训 课程业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainCourseServiceImpl extends AbstractService<TrainCourse,String> implements TrainCourseService{

	@Autowired
	private TrainCourseMapper trainCourseMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainCourseMapper);
	}
	
	/** 
	 * @Description: 根据培训机构ID获取培训课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	@Override
	public List<TrainCourse> selectByTrainTeamID(String id) {
		return trainCourseMapper.selectByTrainTeamID(id);
	}

	/**  
	 * @Description: 根据教练获取所开设的培训课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月30日 上午11:49:18 
	 */ 
	@Override
	public List<TrainCourse> selectByTrainCoachID(String id) {
		return trainCourseMapper.selectByTrainCoachID(id);
	}

	/**  
	 * @Description: 根据教练统计开设课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午7:21:20 
	 */ 
	@Override
	public int countByCoach(String id) {
		return trainCourseMapper.countByCoach(id);
	}

	/**  
	 * @Description: 根据培训机构统计开设课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月11日 下午8:35:00 
	 */ 
	@Override
	public int countByTeam(String id) {
		return trainCourseMapper.countByTeam(id);
	}

	/**  
	 * @Description: 获取我收藏的课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 上午9:24:44 
	 */ 
	@Override
	public List<TrainCourse> selectByCollect(String id) {
		return trainCourseMapper.selectByCollect(id);
	}

	/**  
	 * @Description: 查询该课程是否被用户收藏
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月25日 下午2:52:14 
	 */ 
	@Override
	public TrainCourse selectByMember(String id, String memberid) {
		return trainCourseMapper.selectByMember(id, memberid);
	}

}
