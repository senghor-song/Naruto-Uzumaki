package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.TrainCourseCollect;

public interface TrainCourseCollectMapper extends BaseMapper<TrainCourseCollect, String>{
	/**  
	 * @Description: 删除收藏的课程
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	int deleteByIdAndMember(@Param("id")String id, @Param("memberid")String memberid);

	/**  
	 * @Description: 根据用户统计收藏的课程数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午9:22:01 
	 */ 
	int countByMember(String id);
}