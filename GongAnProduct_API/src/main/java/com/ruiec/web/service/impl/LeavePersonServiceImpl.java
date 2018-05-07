package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.ControlPersonRelation;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.LeavePerson;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonRelationService;
import com.ruiec.web.service.LeavePersonService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;

/**
 * 离堰人员服务实现类
 * 
 * @author qinzhitian<br>
 * @date 2017年12月6日 下午8:17:34
 */
@Service("leavePersonServiceImpl")
public class LeavePersonServiceImpl extends BaseServiceImpl<LeavePerson, Integer> implements LeavePersonService {

	@Resource
	private ControlPersonRelationService controlPersonRelationService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;

	/**
	 * 分页查询离市人员
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月4日 上午11:19:46
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public 	Map<String, Object> findByPageOfLeavePerson(Page page, User loginUser, LoginUserUnit loginUserUnit,Integer id) {
		Map<String, Object> maps=new HashMap<String, Object>();
		// 查询离堰人员列表
		page = findByPage(page);
		List<LeavePerson> leavePersons = (List<LeavePerson>) page.getList();
		// 取出所有身份证
		String[] idCards = new String[10];
		int i = 0;
		for (LeavePerson leavePerson : leavePersons) {
			idCards[i] = leavePerson.getIdCard();
			i++;
		}
		// 查询重点人关系
		DetachedCriteria detached = DetachedCriteria.forClass(ControlPersonRelation.class);
		DetachedCriteria cp = detached.createCriteria("controlPerson");
		// 尚未被逻辑删除的重点人
		cp.add(Restrictions.eq("isDelete", 0));
		detached.add(Restrictions.in("idCard", idCards));
		List<ControlPersonRelation> controlPersonRelations = controlPersonRelationService.findList(detached, null, null, null);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		LeavePerson leavePerson =super.get(id);
		// 将重点人关系添加到对应的离堰人员实体
			for (ControlPersonRelation controlPersonRelation : controlPersonRelations) {
				if (controlPersonRelation.getIdCard().equals(leavePerson.getIdCard())) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", controlPersonRelation.getControlPerson().getName());
					Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonRelation.getTypeId());
					map.put("type", dictionary.getItemName());
					map.put("idCard", controlPersonRelation.getControlPerson().getIdCard());
					list.add(map);
				}
			}
			maps.put("list", list);
		return maps;
	}
}
