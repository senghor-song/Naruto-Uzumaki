package com.ruiec.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.dto.AlarmDTO;
import com.ruiec.web.dto.ControlPersonAlarmDTO;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.User;

/**
 * 重点人员预警服务接口
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:13:21
 */
public interface ControlPersonAlarmService extends BaseService<ControlPersonAlarm, Integer>{
	/**
	 * 根据预警级别分类获取数量
	 * @author 陈靖原<br>
	 * @date 2017年12月6日 下午10:50:06
	 */
	public List<ControlPersonAlarmDTO> getWarnCount(LoginUserUnit loginUserUnit,User loginUser);
	
	/**
	 * 获取稳控列表
	 * @author yuankai 
	 * @date 2017年12月6日 下午10:50:06
	 * */
	 public List<Object> steadyControl();
	 	 
     /**
	 * 获取所有目的地
	 * @author yuankai 
	 * @date 2017年12月6日 下午10:50:06
	 * */
	 public List<String> selectLocation();
	 
	/**
	 * 获取稳控列表
	 *  @author yuankai
	 * @date 2017年12月6日 下午10:50:06
	 * */
	 public Map<String,Object> stableControlList(Page page,String signStatus, String search,String searchType, ControlPerson cp, ControlPersonAlarm cpam, AlarmDTO alarmDTO);
	/**
	 * 根据条件查询预警
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	 public Map<String,Object> searchForAlarm(Page page,ControlPerson cp,ControlPersonAlarm cpam,AlarmDTO alarmDTO,LoginUserUnit loginUserUnit,User loginUser);

	/**
	 * 获取字典数据
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public List<Map<String, Object>> getDictionary(String name);
	
	/**
	 * 获取时间
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public Date getTimeOfDay(Date date,int hour,int min,int sec,int misec);
	
	/**
	 * 根据身份查询预警总数
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public Long alarmCount(LoginUserUnit loginUserUnit, User loginUser);
	
	/**
	 * 根据重点人统计预警数量
	 * @author Senghor<br>
	 * @date 2017年12月30日 下午1:51:13
	 */
	public Long selectByPersonAlarmCount(Integer personId);
	
	/**
	 * 解析动态信息
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public List<Map<String,Object>> getDynamic(String j);
	
	/**
	 * 是否有权限进行预警操作
	 * @param aid预警编号 pid重点人编号
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer aid, String type);
	
	/**
	 * 当日预警数量
	 * @author 陈靖原<br>
	 * @date 2018年1月22日 上午9:07:28
	 */
	public List<Map<String, Object>> alarmCounts(User loginUser, LoginUserUnit loginUserUnit);
	
	/**
	 * 预警反馈
	 * @author 陈靖原<br>
	 * @date 2018年1月22日 上午9:07:28
	 */
	public JsonReturn feedback(String jsonStr, Integer id, User user, LoginUserUnit loginUserUnit);
	
	/**
	 * 预警反馈详情
	 * @author 陈靖原<br>
	 * @date 2018年1月22日 上午9:07:28
	 */
	public JsonReturn alarmFeedbackDetails(Integer id);
}
