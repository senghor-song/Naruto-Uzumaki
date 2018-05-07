package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.User;

/**
 * 重点人员预警服务接口
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:13:21
 */
public interface ControlPersonAlarmService extends BaseService<ControlPersonAlarm, Integer>{

	/**
	 * 获取字典数据
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public List<Map<String, Object>> getDictionary(String name);
	
	/**
	 * 解析动态信息
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public List<Map<String,Object>> getDynamic(String j);
	
	/**
	 * 是否有权限查看预警或反馈
	 * @param aid预警编号 iid指令编号
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer aid, String type);
	
	/**
	 * 查询api预警信息列表
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 上午10:28:55
	 */
	public Map<String, Object> findApiAlarmList(Page page, LoginUserUnit loginUserUnit, User loginUser);

	/**
	 * 查询api预警信息详情
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 上午10:28:55
	 */
	public Map<String, Object> getMapById(Integer id);
	
	/**
	 * 查询历史预警记录
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 下午8:11:36
	 */
	public Map<String, Object> findAlarmHistorical(Page page,Integer id, User user);
	
	/**
	 * 系统查询预警信息封装预警列表结果集
	 * @author yuankai<br>
	 * @date 2018年1月8日 下午8:23:41
	 */
	public Map<String, Object> controlPersonAlarmList(Page page,LoginUserUnit loginUserUnit,String searchType,String search);

	/**
	 * 查询重点人信息
	 * @author qinzhitian<br>
	 * @date 2018年1月13日 下午1:47:28
	 */
	public Map<String, Object> findPersonById(Integer id);

	/**
	 * 根据签收和反馈状态查询
	 * @author Senghor<br>
	 * @date 2018年4月10日 下午3:49:24
	 */
	public Map<String, Object> findByTypeApiAlarmList(Page page, LoginUserUnit loginUserUnit, User loginUser, String type);
}
