/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.service.impl;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.server.common.entity.Admin;
import com.ruiec.server.common.service.AdminService;
/**
 * 管理员服务接口实现类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年5月20日
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, String> implements AdminService {

	@Override
	@Transactional(readOnly = true)
	public Admin findByUsenameAndPassword(String username, String password) {
		if(username == null || password == null){
			return null;
		}
		return (Admin) getSession().createCriteria(Admin.class)
									.add(Restrictions.eq("username", username))
									.add(Restrictions.eq("password", password))
									.uniqueResult();
	}

	@Override
	@Transactional(readOnly = true)
	public Admin findByUsename(String username) {
		if(username == null){
			return null;
		}
		return (Admin) getSession().createCriteria(Admin.class)
									.add(Restrictions.eq("username", username))
									.uniqueResult();
	}
	
	/**
	 * 更新登录时间，登录IP
	 * @author 熊华松<br>
	 * Date: 2016年7月15日
	 */
	@Transactional
	public void updateLoginDateAndLoginIp(Date loginDate, String loginIp, String username) {
		String hql = "update Admin a set a.loginDate=:loginDate, a.loginIp=:loginIp where a.username=:username";
		Query query = getSession().createQuery(hql);
		query.setParameter("loginDate", loginDate);
		query.setParameter("loginIp", loginIp);
		query.setParameter("username", username);
		query.executeUpdate();
	}
	
}
