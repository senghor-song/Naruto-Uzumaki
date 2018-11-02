package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainCourseCollectMapper;
import com.xiaoyi.ssm.model.TrainCourseCollect;
import com.xiaoyi.ssm.service.TrainCourseCollectService;

/**  
 * @Description: 课程业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainCourseCollectServiceImpl extends AbstractService<TrainCourseCollect,String> implements TrainCourseCollectService{

	@Autowired
	private TrainCourseCollectMapper trainCourseCollectMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainCourseCollectMapper);
	}
	/**  
	 * @Description: 删除收藏的课程
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	@Override
	public int deleteByIdAndMember(String id, String memberid) {
		return trainCourseCollectMapper.deleteByIdAndMember(id, memberid);
	}

	/**  
	 * @Description: 根据用户统计收藏的课程数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午9:22:01 
	 */ 
	@Override
	public int countByMember(String id) {
		return trainCourseCollectMapper.countByMember(id);
	}

}
