package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainTeam;
import com.xiaoyi.ssm.model.TrainTeamVenue;

/**  
 * @Description: 培训机构业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:04:23 
 */ 
public interface TrainTeamService extends BaseService<TrainTeam, String> {

	/**  
	 * @Description: 查询附近的
	 * @author 宋高俊  
	 * @param lng
	 * @param lat
	 * @return 
	 * @date 2018年9月29日 下午8:20:12 
	 */ 
	List<TrainTeam> selectByNearby(Double lng, Double lat);

	/**  
	 * @Description: 根据教练查询所属培训机构
	 * @author 宋高俊  
	 * @param coachid
	 * @return 
	 * @date 2018年9月30日 下午5:16:51 
	 */ 
	TrainTeam selectByCoach(String coachid);

	/**  
	 * @Description: 根据培训机构ID统计使用场地数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午5:14:49 
	 */ 
	int countByTeamId(String id);

	/**  
	 * @Description: 后台查询所有培训机构
	 * @author 宋高俊  
	 * @param keyword 
	 * @param selectType 
	 * @return 
	 * @date 2018年10月11日 下午8:24:50 
	 */ 
	List<TrainTeam> selectAllAdmin(Integer selectType, String keyword);

	/**  
	 * @Description: 根据场馆查询驻场的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月18日 下午4:08:07 
	 */ 
	List<TrainTeam> selectByVenue(String id);

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
	List<TrainTeam> selectByNearbyMapTrainTeam(double begLng, double endLng, double begLat, double endLat);

	/**  
	 * @Description: 新增培训机构使用场地
	 * @author 宋高俊  
	 * @param trainTeamVenue
	 * @return 
	 * @date 2018年10月19日 上午9:38:56 
	 */ 
	int saveTrainTeamVenue(TrainTeamVenue trainTeamVenue);

	/**  
	 * @Description: 查询用户收藏的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月24日 上午9:10:11 
	 */ 
	List<TrainTeam> selectByCollect(String id);

	/**  
	 * @Description: 查询最低价格
	 * @author 宋高俊  
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月26日 上午11:37:54 
	 */ 
	Double selectMinAmount(String trainTeamId);

	/**  
	 * @Description: 查询最低价格
	 * @author 宋高俊  
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月26日 上午11:37:54 
	 */ 
	Double selectVenueMinAmount(String venueid);

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
	List<TrainTeam> selectByNearbyMapTrainTeamType(double begLng, double endLng, double begLat, double endLat, Integer ballType);

	/**  
	 * @Description: 根据用户查询加入的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月31日 下午4:07:33 
	 */ 
	List<TrainTeam> selectByMember(String id);

	/**  
	 * @Description: 根据用户查询指定身份的培训机构
	 * @author 宋高俊  
	 * @param id
	 * @param i
	 * @return 
	 * @date 2018年11月1日 下午8:13:11 
	 */ 
	List<TrainTeam> selectByMemberManager(String memberid, int manager);
}
