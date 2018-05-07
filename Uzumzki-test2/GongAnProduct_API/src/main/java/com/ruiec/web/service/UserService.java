package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;

/**
 * 警员信息服务
 * @author 陈靖原<br>
 * @date 2017年11月29日 下午10:34:14
 */
public interface UserService extends BaseService<User, Integer>{

	/**
	 * 登录
	 * @param User
	 * @return User
	 * @author 陈靖原<br>
	 * @date 2017年11月29日 下午10:37:09
	 */
	public User login(User user);
	
	/**
	 * 根据单位查询下面所面所有的警员
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午9:58:54
	 */
	List<Map<String, Object>> getUnitPerson(Integer areaId,Integer townId);
	
	/**
	 * 根据单位查询下面所面所有的警员
	 * @author qinzhitian<br>
	 * @date 2017年12月28日 下午8:22:52
	 */
	public List<Map<String, Object>> getUnitPerson(Integer unitId);

	/**
	 * 根据登录用户获取单位数据
	 * @author Senghor<br>
	 * @date 2017年12月22日 上午10:17:20
	 */
	public List<Unit> findUserUnit(User user, LoginUserUnit loginUserUnit,Integer isCity);
	
}
