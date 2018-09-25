/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.ControlPersonInstructi;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.enumeration.WarningColor;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonInstructiService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 重点人员预警指令服务接口实现类
 * 
 * @author Senghor<br>
 * @date 2017年11月30日 上午9:01:37
 */
@Repository("controlPersonInstructiServiceImpl")
public class ControlPersonInstructiServiceImpl extends BaseServiceImpl<ControlPersonInstructi, Integer> implements ControlPersonInstructiService {

	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;

	/**
	 * 预警签收记录api
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月9日 下午2:30:04
	 * @param alarmId
	 *            预警信息id
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map<String, Object> instructionRecord(Integer alarmId, Page page) {
		// 根据关联预警的ID查询指令
		DetachedCriteria cpi = DetachedCriteria.forClass(ControlPersonInstructi.class);
		DetachedCriteria cpa = cpi.createCriteria("controlPersonAlarm");
		DetachedCriteria cp = cpi.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		// 尚未被逻辑删除的重点人
		cp.add(Restrictions.eq("isDelete", 0));
		cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		cpa.add(Restrictions.eq("id", alarmId));
		super.findByPage(page, cpi);
		// 重新封装预警列表结果集
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> mapList = new ArrayList<>();
		
		ControlPersonAlarm controlPersonAlarm = controlPersonAlarmService.get(alarmId);
		if (controlPersonAlarm.getSignStatus() == 2) {
			Map<String, Object> map = new HashMap<String, Object>();
			User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, String.valueOf(controlPersonAlarm.getSignPrikey()));
			map.put("userName", user == null ? "无" : user.getPoliceName());//签收人
			if (user != null) {
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, String.valueOf(user.getUnitId()));
				map.put("unitName", unit == null ? "无" : unit.getUnitName());//签收单位
			}else {
				map.put("unitName", GlobalUnit.NULLMSG);//签收单位
			}
			map.put("createTime", controlPersonAlarm.getSignTime());//签收时间
			// 预警级别
			Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonAlarm.getWarningLevel());
			if (dictionary == null) {
				dictionary = dictionaryService.get(Integer.parseInt(controlPersonAlarm.getWarningLevel()));
			}
			if (dictionary == null) {
				map.put("warningLevelColor", GlobalUnit.NULLMSG);
				map.put("warningLevelValue", GlobalUnit.NULLMSG);
			} else {
				map.put("warningLevelColor", dictionary.getItemName());
				map.put("warningLevelValue", WarningColor.getValue(dictionary.getItemName()));
			}

			// 是否签收
			if (controlPersonAlarm.getSignStatus() == 1) {
				map.put("state", "未签收");
			} else {
				map.put("state", "已签收");
			}
			mapList.add(map);
		}
		List<ControlPersonInstructi> instructis = (List<ControlPersonInstructi>) page.getList();
		if (instructis != null && instructis.size() > 0) {
			for (ControlPersonInstructi controlPersonInstructi : instructis) {
				Map<String, Object> map = new HashMap<String, Object>();
//				String cpt_str = new String();
				// map.put("id", controlPersonInstructi.getId());
//				map.put("controlPersonId", controlPersonInstructi.getControlPerson().getId());
//				map.put("name", controlPersonInstructi.getControlPerson().getName());// 重点人姓名
//				map.put("idCard", controlPersonInstructi.getControlPerson().getIdCard());// 重点人身份证号
				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, String.valueOf(controlPersonInstructi.getInstructionRecipientPrikey()));
				map.put("userName", user == null ? "无" : user.getPoliceName());//签收人
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, String.valueOf(controlPersonInstructi.getInstructionRecipientUnitId()));
				map.put("unitName", unit == null ? "无" : unit.getUnitName());//签收单位
				map.put("createTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPersonInstructi.getCreateDate()));
				
				/*// 人员类别
				List<ControlPersonType> cptList = controlPersonInstructi.getControlPerson().getControlPersonTypes();
				if (cptList != null && cptList.size() > 0) {
					for (ControlPersonType controlPersonType : cptList) {
						dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonType.getDictionaryId());
						if (dictionary == null) {
							dictionary = dictionaryService.get(controlPersonType.getDictionaryId());
						}
						cpt_str = cpt_str + dictionary.getItemName() + ", ";
					}
					// 移除最后一个，
					cpt_str = cpt_str.substring(0, cpt_str.length() - 2);
				}
				map.put("personnelType", cpt_str);*/

				// 预警级别
				Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonInstructi.getFourColourWarning());
				if (dictionary == null) {
					dictionary = dictionaryService.get(controlPersonInstructi.getFourColourWarning());
				}
				if (dictionary == null) {
					map.put("warningLevelColor", GlobalUnit.NULLMSG);
					map.put("warningLevelValue", GlobalUnit.NULLMSG);
				} else {
					map.put("warningLevelColor", dictionary.getItemName());
					map.put("warningLevelValue", WarningColor.getValue(dictionary.getItemName()));
				}

				// 是否签收
				if (controlPersonInstructi.getInstructionsState() == 0) {
					map.put("state", "未签收");
				} else {
					map.put("state", "已签收");
				}
				mapList.add(map);
			}
		}
		resultMap.put("mapList", mapList);
		resultMap.put("pageNumber", page.getPageNumber());
		resultMap.put("pageSize", page.getPageSize());
		resultMap.put("totalCount", page.getTotalCount());
		return resultMap;
	}
}
