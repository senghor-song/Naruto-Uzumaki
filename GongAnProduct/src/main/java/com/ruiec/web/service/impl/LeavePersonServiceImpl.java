package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.ControlPersonRelation;
import com.ruiec.web.entity.LeavePerson;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonRelationService;
import com.ruiec.web.service.LeavePersonService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;

/**
 * 离市人员服务实现类
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
	public Page findByPageOfLeavePerson(Page page, User loginUser, LoginUserUnit loginUserUnit) {
		List<Integer> unitIds = new ArrayList<>();
		if (loginUserUnit.getUnitSonIds() != null && !loginUserUnit.getUnitSonIds().isEmpty()) {
			unitIds.addAll(loginUserUnit.getUnitSonIds());
		}
		Integer[] ids = null;
		// 查询关联单位
		unitIds.add(loginUser.getUnitId());
		if (!"市局管理员".equals(loginUserUnit.getUserName())) {
			// 非市局管理员
			ids = unitIds.toArray(new Integer[unitIds.size()]);
			page.add(Filter.in("nativePlacePoliceId", ids));
		}
		// 查询离市人员列表
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

		List<LeavePerson> leavePersonList = new ArrayList<LeavePerson>();
		// 将重点人关系添加到对应的离市人员实体
		for (LeavePerson leavePerson : leavePersons) {
			for (ControlPersonRelation controlPersonRelation : controlPersonRelations) {
				if (controlPersonRelation.getIdCard().equals(leavePerson.getIdCard())) {
					leavePerson.setControlPersonRelation(controlPersonRelation);
				}
			}
			leavePersonList.add(leavePerson);
		}

		page.setList(leavePersonList);
		return page;
	}
	
	
	/**
	 * 分页系统查询离市人员
	 * 
	 * @author yuankai<br>
	 * @date 2018年2月2日 上午8:40:01
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page findByPageOf(Page page) {
		// 查询离市人员列表
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

		List<LeavePerson> leavePersonList = new ArrayList<LeavePerson>();
		// 将重点人关系添加到对应的离市人员实体
		for (LeavePerson leavePerson : leavePersons) {
			for (ControlPersonRelation controlPersonRelation : controlPersonRelations) {
				if (controlPersonRelation.getIdCard().equals(leavePerson.getIdCard())) {
					leavePerson.setControlPersonRelation(controlPersonRelation);
				}
			}
			leavePersonList.add(leavePerson);
		}

		page.setList(leavePersonList);
		return page;
	}
}
