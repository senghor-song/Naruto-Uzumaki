package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoyi.ssm.dao.AmountRefundWayMapper;
import com.xiaoyi.ssm.dao.ManagerVenueMapper;
import com.xiaoyi.ssm.dao.VenueMapper;
import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.AmountRefundWay;
import com.xiaoyi.ssm.model.Field;
import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.ManagerVenue;
import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.service.FieldService;
import com.xiaoyi.ssm.service.ManagerService;
import com.xiaoyi.ssm.service.VenueService;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;
import com.xiaoyi.ssm.wxPay.SHA1;

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
	@Autowired
	private ManagerService managerService;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private AmountRefundWayMapper amountRefundWayMapper;
	
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
	public ApiMessage saveVenue(Venue venue, String openid) {
		try {
			Date date = new Date();
			Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, openid);
			
			Manager manager = managerService.selectByPhone(member.getPhone());
			if (manager != null) {
				return new ApiMessage(400, "添加失败,管理员只能管理一个场馆，不能新增更多");
			}
			//还不是管理员时，创建管理员账号
			Manager newManager = new Manager();
			String managerid = Utils.getUUID(); 
			newManager.setId(managerid);
			newManager.setCreatetime(date);
			newManager.setModifytime(date);
			newManager.setPhone(member.getPhone());
			newManager.setName(member.getName());
			newManager.setType(0);
			managerService.insertSelective(newManager);
			
			//创建场馆
			venue.setId(Utils.getUUID());
			venue.setCreatetime(date);
			venue.setModifytime(date);
			if(venue.getWarmreminder() == null){
				venue.setWarmreminder("欢迎光临");
			}
			insertSelective(venue);
			
			//保存管理员管理的场馆
			ManagerVenue mv = new ManagerVenue();
			mv.setId(Utils.getUUID());
			mv.setCreatetime(date);
			mv.setVenueid(venue.getId());
			mv.setManagerid(managerid);
			managerVenueMapper.insert(mv);
			
			//新增场地
			Field field = new Field();
			field.setId(Utils.getUUID());
			field.setCreatetime(date);
			field.setModifytime(date);
			field.setName("场地1");
			field.setVenueid(venue.getId());
			fieldService.insert(field);

			//初始化退费数据
			amountRefundWayMapper.insertInitWay(venue.getId());
			
			Map<String, Object> map = new HashMap<>();
			// 本次登录使用的token
			String loginToken = SHA1.encode(SHA1.encode(UUID.randomUUID().toString()));
//			String loginToken = loginmanager.getId();
			RedisUtil.addRedis(Global.redis_manager, loginToken, newManager);
			map.put("token", loginToken);
			
			return new ApiMessage(200, "登录成功", map);
		} catch (Exception e) {
			return new ApiMessage(400, "添加失败,管理员只能管理一个场馆，不能新增更多");
		}
	}

	@Override
	public List<Venue> selectByOftenMember(String id) {
		return venueMapper.selectByOftenMember(id);
	}

}
