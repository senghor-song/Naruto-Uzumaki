package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.Monitor;
import com.ruiec.web.entity.User;

/**
 * 布控人员信息服务
 * @author 陈靖原<br>
 * @date 2017年11月29日 下午10:34:14
 */
public interface MonitorService extends BaseService<Monitor, Integer>{
	
	/**
	 * 保存布控人员
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午10:55:04
	 */
	boolean saveMonitor(User user,LoginUserUnit loginUserUnit,Monitor monitor, String Categories, Integer unitId, Integer unitId2);
	
	/**
	 * 是否有权限操作改人员
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午10:55:04
	 */
	boolean isPower(Integer id,User user,LoginUserUnit loginUserUnit);
	
	/**
	 * 根据身份证查询已布控人信息(是否存在)
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午10:55:04
	 */
	boolean selByIdCard(String idCard);
	
	/**
	 * 根据身份证查询布控人信息(个人)
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午4:04:27
	 */
	public Map<String, Object> selOneByIdCard(Integer id,Integer ptype);
	
	/**
	 * 登录用户是否可以审核该布控人信息
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午4:04:27
	 */
	public String isAudit(User user,LoginUserUnit loginUserUnit,Monitor monitor);
	
	/**
	 * 撤除所有过期信息
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午4:04:27
	 */
	public boolean updateIsExpired(List<Integer> list);
	
	/**
	 * 修改布控人员
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午10:59:58
	 */
	public boolean updMonitor(User user, LoginUserUnit loginUserUnit, Monitor monitor, String Categories, Integer unitId, Integer unitId2);
}
