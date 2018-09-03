package com.ruiec.web.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.ControlPersonInstructi;
import com.ruiec.web.entity.User;

/**
 * 重点人预警指令服务接口
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:14:58
 */
public interface ControlPersonInstructiService extends BaseService<ControlPersonInstructi, Integer>{
	
	/**
	 * 判断权限
	 * 1：签收
	 * 2：反馈
	 * 3：审核
	 * 4：查看
	 * @author qinzhitian<br>
	 * @date 2018年1月25日 上午10:07:29
	 */
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer iid, int type);
	/**
	 * 预警下发
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 下午4:23:33
	 */
	public JsonReturn issued(Integer[] unitIds, Integer id, User user, LoginUserUnit loginUserUnit, Integer[] userIds, String content);

	/**
	 * 预警指令反馈
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:54:20
	 */
	public JsonReturn feedBack(Integer id, String json, User user);
	/**
	 * 预警反馈详情
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:54:20
	 */
	public List<JSONObject> findFeedBackDetail(Integer id);
	/**
	 * 查询最后一次预警反馈详情
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:54:20
	 */
	public JSONObject findLastFeedBack(Integer id);
	/**
	 * 查询可下发目标
	 * @author qinzhitian<br>
	 * @date 2017年12月17日 下午4:14:28
	 */
	public Map<String, List<Map<String, Object>>> findIssued(User user);
	/**
	 * 根据签收单位查询可下发单位或警员
	 * @author qinzhitian<br>
	 * @date 2017年12月28日 上午10:16:27
	 */
	public Map<String, List<Map<String, Object>>> findCommandIssued(User user, Integer id);
	/**
	 * 预警指令详情
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:54:20
	 */
	public JSONObject findInstructionDetail(Integer id);
	/**
	 * 签收
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午8:44:17
	 */
	public JsonReturn updateInstructionsState(Integer id,Integer state);
	/**
	 * 审核
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午8:44:17
	 */
	public JsonReturn audit(Integer id, Integer state, String content);
	
	/**
	 * 根据预警id查询签收指令信息(根据签收人分组)
	 * @author 陈靖原<br>
	 * @date 2017年12月26日 下午8:44:17
	 */
	public List<Map<String,Object>> issuedListByPerson(Integer id);
	
	/**
	 * 根据预警id查询签收指令信息(根据签收单位分组)
	 * @author 陈靖原<br>
	 * @date 2017年12月26日 下午8:44:17
	 */
	public List<Map<String,Object>> issuedListByUnit(Integer id);
}
