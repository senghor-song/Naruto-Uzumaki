package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainTeamImage;

/**  
 * @Description: 培训机构环境业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:04:23 
 */ 
public interface TrainTeamImageService extends BaseService<TrainTeamImage, String> {

	/** 
	 * @Description: 根据培训机构ID获取培训机构环境
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	List<TrainTeamImage> selectByTrainTeamID(String id);

	/**  
	 * @Description: 根据培训机构ID删除所有
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月8日 下午7:43:04 
	 */ 
	int deleteByTeamAll(String id);
}
