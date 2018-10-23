package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainCourse;

/**  
 * @Description: 培训课程业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:04:23 
 */ 
public interface TrainCourseService extends BaseService<TrainCourse, String> {

	/** 
	 * @Description: 根据培训机构ID获取培训课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	List<TrainCourse> selectByTrainTeamID(String id);

	/**  
	 * @Description: 根据教练获取所开设的培训课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月30日 上午11:49:18 
	 */ 
	List<TrainCourse> selectByTrainCoachID(String id);

	/**  
	 * @Description: 根据教练统计开设课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午7:21:20 
	 */ 
	int countByCoach(String id);

	/**  
	 * @Description: 根据培训机构统计开设课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月11日 下午8:35:00 
	 */ 
	int countByTeam(String id);

}
