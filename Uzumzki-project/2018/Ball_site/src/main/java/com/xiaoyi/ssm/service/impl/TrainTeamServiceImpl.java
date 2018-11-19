package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTeamMapper;
import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamVenue;
import com.xiaoyi.ssm.service.TrainTeamService;

/**  
 * @Description: 培训机构业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTeamServiceImpl extends AbstractService<TrainTeam,String> implements TrainTeamService{

	@Autowired
	private TrainTeamMapper trainTeamMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTeamMapper);
	}

	/**  
	 * @Description: 查询附近的
	 * @author 宋高俊  
	 * @param lng
	 * @param lat
	 * @return 
	 * @date 2018年9月29日 下午8:20:12 
	 */ 
	@Override
	public List<TrainTeam> selectByNearby(Double lng, Double lat) {
		return trainTeamMapper.selectByNearby(lng, lat);
	}

	/**  
	 * @Description: 根据教练查询所属培训机构
	 * @author 宋高俊  
	 * @param coachid
	 * @return 
	 * @date 2018年9月30日 下午5:16:51 
	 */ 
	@Override
	public TrainTeam selectByCoach(String coachid) {
		return trainTeamMapper.selectByCoach(coachid);
	}

	/**  
	 * @Description: 根据培训机构ID统计使用场地数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午5:14:49 
	 */ 
	@Override
	public int countByTeamId(String id) {
		return trainTeamMapper.countByTeamId(id);
	}
	
	/**  
	 * @Description: 后台查询所有培训机构
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月11日 下午8:24:50 
	 */ 
	@Override
	public List<TrainTeam> selectAllAdmin(Integer selectType, String keyword) {
		return trainTeamMapper.selectAllAdmin(selectType, keyword);
	}

	/**  
	 * @Description: 根据场馆查询驻场的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月18日 下午4:08:07 
	 */ 
	@Override
	public List<TrainTeam> selectByVenue(String id) {
		return trainTeamMapper.selectByVenue(id);
	}

	/**  
	 * @Description: 根据经纬度筛选培训机构
	 * @author 宋高俊  
	 * @param begLng
	 * @param endLng
	 * @param begLat
	 * @param endLat
	 * @return 
	 * @date 2018年10月18日 下午7:37:16 
	 */ 
	@Override
	public List<TrainTeam> selectByNearbyMapTrainTeam(double begLng, double endLng, double begLat, double endLat) {
		return trainTeamMapper.selectByNearbyMapTrainTeam(begLng, endLng, begLat, endLat);
	}

	/**  
	 * @Description: 新增培训机构使用场地
	 * @author 宋高俊  
	 * @param trainTeamVenue
	 * @return 
	 * @date 2018年10月19日 上午9:38:56 
	 */ 
	@Override
	public int saveTrainTeamVenue(TrainTeamVenue trainTeamVenue) {
		return trainTeamMapper.saveTrainTeamVenue(trainTeamVenue);
	}

	/**  
	 * @Description: 查询用户收藏的培训机构
	 * @author 宋高俊  
	 * @date 2018年10月24日 上午9:13:19 
	 */ 
	@Override
	public List<TrainTeam> selectByCollect(String id) {
		return trainTeamMapper.selectByCollect(id);
	}

	/**  
	 * @Description: 查询最低价格
	 * @author 宋高俊  
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月26日 上午11:37:54 
	 */ 
	@Override
	public Double selectMinAmount(String trainTeamId) {
		return trainTeamMapper.selectMinAmount(trainTeamId);
	}

	/**  
	 * @Description: 查询最低价格
	 * @author 宋高俊  
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月26日 上午11:37:54 
	 */ 
	@Override
	public Double selectVenueMinAmount(String venueid) {
		return trainTeamMapper.selectVenueMinAmount(venueid);
	}

	/**  
	 * @Description: 根据经纬度筛选机构
	 * @author 宋高俊  
	 * @param begLng
	 * @param endLng
	 * @param begLat
	 * @param endLat
	 * @return 
	 * @date 2018年10月20日 下午3:25:58 
	 */ 
	@Override
	public List<TrainTeam> selectByNearbyMapTrainTeamType(double begLng, double endLng, double begLat, double endLat, Integer ballType) {
		return trainTeamMapper.selectByNearbyMapTrainTeamType(begLng, endLng, begLat, endLat, ballType);
	}

	/**  
	 * @Description: 根据用户查询加入的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月31日 下午4:07:33 
	 */ 
	@Override
	public List<TrainTeam> selectByMember(String id) {
		return trainTeamMapper.selectByMember(id);
	}

	/**  
	 * @Description: 根据用户查询指定身份的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @param i
	 * @return 
	 * @date 2018年11月1日 下午8:13:11 
	 */ 
	@Override
	public List<TrainTeam> selectByMemberManager(String memberid, int manager) {
		return trainTeamMapper.selectByMemberManager(memberid, manager);
	}
}
