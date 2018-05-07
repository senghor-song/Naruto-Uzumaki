package com.ruiec.web.service;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.LeavePerson;
import com.ruiec.web.entity.User;

/**
 * 离市人员服务接口
 * @author qinzhitian<br>
 * @date 2017年12月6日 下午8:15:13
 */
public interface LeavePersonService extends BaseService<LeavePerson, Integer>{

	/**
	 * 分页查询离市人员
	 * @author qinzhitian<br>
	 * @date 2018年1月4日 上午11:19:46
	 */
	public Page findByPageOfLeavePerson(Page page, User loginUser, LoginUserUnit loginUserUnit);
	
	
	/**
	 * 分页系统查询离市人员
	 * 
	 * @author yuankai<br>
	 * @date 2018年2月2日 上午8:40:01
	 */
	public Page findByPageOf(Page page);
}
