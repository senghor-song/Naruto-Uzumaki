package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.MonitorAlarm;
import com.ruiec.web.entity.User;

/**
 * 布控预警服务
 * @author 陈靖原<br>
 * @date 2017年11月29日 下午10:34:14
 */
public interface MonitorAlarmService extends BaseService<MonitorAlarm, Integer>{

	/**
	 * 布控预警分页查询
	 * @author qinzhitian<br>
	 * @date 2018年1月11日 上午9:32:39
	 */
	public Map<String, Object> findByPage(Page page, User user, LoginUserUnit loginUserUnit);
	
	/**
	 * 获取字典数据
	 * @author 陈靖原<br>
	 * @date 2018年1月11日 下午2:38:48
	 */
	public List<Map<String, Object>> getDictionary(String name);
	
	/**
	 * 是否有权限查看预警或反馈
	 * @param aid预警编号
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer id, String type);

	/**
	 * 今日布控数量
	 * @author qinzhitian<br>
	 * @date 2018年1月11日 下午1:44:51
	 */
	public Long findTodayCount(LoginUserUnit loginUserUnit);
	
	/**
	 * 根据布控人统计预警数量
	 * @author 陈靖原<br>
	 * @date 2018年1月11日 下午2:38:48
	 */
	Long selectByMonitorAlarmCount(Integer personId);
	
	/**
	 * 根据id查询重点人基本信息
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 上午11:11:51
	 */
	public Map<String, Object> findMapById(Integer id);
	
	/**
	 * 根据id查询重点人员预警信息
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 上午11:11:51
	 */
	public Map<String, Object> findAlarmDetail(Integer id);
	
	/**
	 * 查询反馈记录
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 上午11:11:51
	 */
	public List<Map<String,Object>> findFeedBackList(Integer id);
	
	/**
	 * 根据待签收和待反馈查询
	 * @author Senghor<br>
	 * @date 2018年4月13日 下午1:45:57
	 */
	public Map<String, Object> findByTypePage(Page page, User loginUser, LoginUserUnit loginUserUnit,Integer type);
}
