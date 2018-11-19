package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.TrainCourse;

/**  
 * @Description: 培训课程数据持久层
 * @author 宋高俊  
 * @date 2018年9月30日 上午10:13:32 
 */ 
public interface TrainCourseMapper extends BaseMapper<TrainCourse, String>{
	
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

	/**  
	 * @Description: 获取我收藏的课程
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 上午9:24:44 
	 */ 
	List<TrainCourse> selectByCollect(String id);

	/**  
	 * @Description: 查询该课程是否被用户收藏
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月25日 下午2:52:14 
	 */ 
	TrainCourse selectByMember(@Param("id")String id, @Param("memberid")String memberid);

}