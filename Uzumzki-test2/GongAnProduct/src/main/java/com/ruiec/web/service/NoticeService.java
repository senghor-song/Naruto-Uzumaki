package com.ruiec.web.service;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.Notice;

/**
 * 公告服务接口
 * @author yuankai<br>
 * Date: 2017年11月29日
 */
public interface NoticeService extends BaseService<Notice, String>{
	
	/**
	 * 修改用户未读公告(新增未读id)
	 * @author 陈靖原<br>
	 * @date 2018年1月24日 下午5:07:19
	 */
	public void updateUnreadNotice(Integer id);
}
