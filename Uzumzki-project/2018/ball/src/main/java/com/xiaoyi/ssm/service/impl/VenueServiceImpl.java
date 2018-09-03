package com.xiaoyi.ssm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyi.ssm.dao.ManagerVenueMapper;
import com.xiaoyi.ssm.dao.VenueMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.ManagerVenue;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 场馆业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月16日 下午6:00:43 
 */ 
@Service
public class VenueServiceImpl extends AbstractService<Venue,String> implements VenueService{

	@Autowired
	private VenueMapper venueMapper;
	@Autowired
	private ManagerVenueMapper managerVenueMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(venueMapper);
	}

	/**  
	 * @Description: 根据管理员获取所管理的场馆
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午7:41:07 
	 */ 
	@Override
	public Venue selectByManager(String managerid) {
		return venueMapper.selectByManager(managerid);
	}

	/**  
	 * @Description: 保存场馆数据
	 * @author 宋高俊  
	 * @date 2018年8月23日 上午10:08:39 
	 */ 
	@Override
	@Transactional
	public ApiMessage saveVenue(Venue venue, String token) {
		Manager manager = (Manager) RedisUtil.getRedisOne(Global.redis_manager, token);
		venue.setId(Utils.getUUID());
		venue.setCreatetime(new Date());
		venue.setModifytime(new Date());
		insertSelective(venue);
		
		//保存管理员管理的场馆
		ManagerVenue mv = new ManagerVenue();
		mv.setId(Utils.getUUID());
		mv.setCreatetime(new Date());
		mv.setVenueid(venue.getId());
		mv.setManagerid(manager.getId());
		try {
			managerVenueMapper.insert(mv);
			return new ApiMessage(200, "添加成功");
		} catch (Exception e) {
			return new ApiMessage(400, "添加失败,管理员只能管理一个场馆，不能新增更多");
		}
	}

}
