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

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DynamicInfoService;

/**
 * 轨迹数据服务实现类
 * 
 * @author bingo<br>
 * @date 2017年12月23日 上午9:25:37
 */
@Service("dynamicInfoServiceImpl")
public class DynamicInfoServiceImpl extends BaseServiceImpl<DynamicInfo, String> implements DynamicInfoService {

	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private ControlPersonService controlPersonService;

	/**
	 * 保存轨迹记录，同时生成预警记录
	 * 
	 * @author bingo<br>
	 * @date 2017年12月25日 下午2:12:08
	 */
	/*
	 * @Override
	 * 
	 * @Transactional public DynamicInfo save(DynamicInfo entity) { // 保存轨迹记录
	 * super.save(entity); ControlPersonAlarm controlPersonAlarm = new
	 * ControlPersonAlarm(); ControlPerson controlPerson =
	 * controlPersonService.get(entity.getControlPersonId());
	 * controlPersonAlarm.setControlPerson(controlPerson);
	 * controlPersonAlarm.setIdCard(controlPerson.getIdCard());
	 * controlPersonAlarm.setWarningLevel(String.valueOf(new
	 * Random().nextInt(4)));
	 * controlPersonAlarm.setControlUnit(controlPerson.getUnitId());
	 * controlPersonAlarm.setOrigin(entity.getOrigin());
	 * controlPersonAlarm.setDestination(entity.getDestination());
	 * controlPersonAlarm.setDynamicInfoPrikey(entity.getId());
	 * controlPersonAlarm.setDistributeStatus(1);
	 * controlPersonAlarm.setSignStatus(1); // 匹配预警地
	 * controlPersonAlarm.setAlarmUnit(entity.getAlarmUnit().getId()); // 生成预警记录
	 * controlPersonAlarmService.save(controlPersonAlarm); return entity; }
	 */

	/**
	 * 根据重点人id统计每种轨迹数据的数量
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月30日 下午4:47:47
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> dynamicInfoCount(Integer personId) {
		List<Map<String, Object>> dynamicInfos = new ArrayList<Map<String, Object>>();
		String hql = "select count(*),di.type from ControlPerson cp , DynamicInfo di where cp.id=di.controlPerson.id and di.controlPerson.id = :personId GROUP BY di.type";
		Query query = getSession().createQuery(hql);
		query.setParameter("personId", personId);
		List<Object[]> objects = query.list();
		for (int i = 0; i < objects.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("count", objects.get(i)[0]);
			map.put("type", objects.get(i)[1]);
			dynamicInfos.add(map);
		}
		return dynamicInfos;
	}
}
