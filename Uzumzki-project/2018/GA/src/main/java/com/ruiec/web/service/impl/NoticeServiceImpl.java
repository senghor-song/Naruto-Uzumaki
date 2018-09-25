/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.Notice;
import com.ruiec.web.service.NoticeService;

/**
 * 公告服务接口实现类
 * 
 * @author yuankai<br>
 *         Date: 2017年11月29日
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice, String> implements NoticeService {

	/**
	 * 修改用户未读公告(新增未读id)
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月24日 下午5:07:19
	 */
	@Override
	@Transactional
	public void updateUnreadNotice(Integer id) {
		String sql = "update T_SYS_USER set UNREAD_NOTICE = (UNREAD_NOTICE || '," + id + "')";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
}
