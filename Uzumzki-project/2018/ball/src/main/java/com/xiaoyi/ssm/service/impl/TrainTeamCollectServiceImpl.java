package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTeamCollectMapper;
import com.xiaoyi.ssm.model.TrainTeamCollect;
import com.xiaoyi.ssm.service.TrainTeamCollectService;

/**  
 * @Description: 培训机构业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTeamCollectServiceImpl extends AbstractService<TrainTeamCollect,String> implements TrainTeamCollectService{

	@Autowired
	private TrainTeamCollectMapper trainTeamCollectMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTeamCollectMapper);
	}
	/**  
	 * @Description: 删除收藏的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月23日 下午8:36:26 
	 */ 
	@Override
	public int deleteByIdAndMember(String id, String memberid) {
		return trainTeamCollectMapper.deleteByIdAndMember(id, memberid);
	}

	/**  
	 * @Description: 根据用户统计收藏的培训机构数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月25日 上午9:22:01 
	 */ 
	@Override
	public int countByMember(String id) {
		return trainTeamCollectMapper.countByMember(id);
	}
	
	/**  
	 * @Description: 查询该培训机构是否被用户收藏
	 * @author 宋高俊  
	 * @param id
	 * @param memberid
	 * @return 
	 * @date 2018年10月25日 下午3:23:09 
	 */
	@Override
	public TrainTeamCollect selectByMember(String id, String memberid) {
		return trainTeamCollectMapper.selectByMember(id, memberid);
	}

}
