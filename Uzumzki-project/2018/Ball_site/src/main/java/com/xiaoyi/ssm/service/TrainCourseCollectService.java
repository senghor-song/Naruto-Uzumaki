package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.TrainCourseCollect;

/**  
 * @Description: 课程收藏业务逻辑层
 * @author 宋高俊  
 * @date 2018年10月23日 下午8:23:41 
 */ 
public interface TrainCourseCollectService extends BaseService<TrainCourseCollect, String> {

	/**  
	 * @Description: 删除收藏的课程
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	int deleteByIdAndMember(String id, String memberid);

	/**  
	 * @Description: 根据用户统计收藏的课程数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午9:22:01 
	 */ 
	int countByMember(String id);
	
}
