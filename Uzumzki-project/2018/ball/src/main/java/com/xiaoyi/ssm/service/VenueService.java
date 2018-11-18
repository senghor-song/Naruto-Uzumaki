package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.TrainTeamVenue;
import com.xiaoyi.ssm.model.Venue;

/**  
 * @Description: 场馆业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午5:59:40 
 */ 
public interface VenueService extends BaseService<Venue, String> {

	/**  
	 * @Description: 根据培训机构获取所管理的场馆
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:41:07 
	 */ 
	List<Venue> selectByManager(String id);

	/**  
	 * @Description: 查询常用场馆
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月10日 下午4:19:24 
	 */ 
	List<Venue> selectByOftenMember(String id);

	/**  
	 * @Description: 根据场馆名和城市查询场馆
	 * @author 宋高俊  
	 * @param venuename
	 * @return 
	 * @date 2018年9月21日 下午8:58:30 
	 */ 
	Venue selectByVenueCity(String venueName, String id);

	/**  
	 * @Description: 自定义条件查询场馆列表
	 * @author 宋高俊  
	 * @param ballType 
	 * @return 
	 * @date 2018年10月8日 上午9:19:58 
	 */ 
	List<Venue> selectByAllVenue(String cityid, String districtid, Double lng, Double lat, Integer ballType);
	
	
	/** 
	 * @Description: 根据培训机构ID获取培训场地
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	List<Venue> selectByTrainTeamID(String id);

	/**  
	 * @Description: 
	 * @author 宋高俊  
	 * @param trainTeamId 培训机构ID
	 * @param id 场馆ID
	 * @return 
	 * @date 2018年10月10日 下午2:20:20 
	 */ 
	int deleteByTeamVenue(String trainTeamId, String id);

	/**  
	 * @Description: 保存培训机构使用场地
	 * @author 宋高俊  
	 * @param id
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月10日 下午2:50:27 
	 */ 
	int saveTeamVenue(TrainTeamVenue trainTeamVenue);

	/**  
	 * @Description: 根据培训机构统计所使用的场地
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 上午9:10:10 
	 */ 
	int countByTeam(String id);

	/**  
	 * @Description: 条件查询场地数据
	 * @author 宋高俊  
	 * @param name
	 * @param longitude
	 * @param latitude
	 * @return 
	 * @date 2018年10月13日 下午4:25:07 
	 */ 
	List<Venue> selectTrainVenue(String name, Double longitude, Double latitude);

	/**  
	 * @Description: 根据名称模糊查询场地
	 * @author 宋高俊  
	 * @param name
	 * @return 
	 * @date 2018年10月18日 下午2:59:01 
	 */ 
	List<Venue> selectByName(String name);

	/**  
	 * @Description: 根据经纬度筛选场馆
	 * @author 宋高俊  
	 * @param begLng
	 * @param endLng
	 * @param begLat
	 * @param endLat
	 * @return 
	 * @date 2018年10月20日 下午3:25:58 
	 */ 
	List<Venue> selectByNearbyMapVenue(double begLng, double endLng, double begLat, double endLat, Integer ballType);

	/**  
	 * @Description: 条件查询场馆
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月30日 上午10:09:42 
	 */ 
	List<Venue> selectBySearch(Integer selectType, String keyword);

	/**  
	 * @Description: 条件查询模板分析数据
	 * @author 宋高俊  
	 * @param ballType 
	 * @param trainAddFlag 
	 * @return 
	 * @date 2018年11月2日 下午8:08:47 
	 */ 
	List<Venue> selectByVenueSearch(String cityid, Integer sumTemplate, Integer trainAddFlag, Integer ballType);

	/**  
	 * @Description: 查询订单已消费可以结算的订单
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月15日14:23:09
	 */ 
	List<Venue> selectByDateOut(String date);

	/**  
	 * @Description: 根据手机号查询是否有匹配的场馆
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年11月15日14:23:09
	 */ 
	List<Venue> selectByMatchingVenue(String phone);
}
