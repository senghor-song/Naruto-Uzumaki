package com.ruiec.web.service;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.entity.Message;

/**
 * 消息服务接口
 * 
 * @author 陈靖原<br>
 * @date 2018年2月27日 下午4:50:46
 */
public interface MessageService extends BaseService<Message, Integer> {
	/**
	 * 发送信息
	 * 
	 * @param sourceId消息来源ID
	 * @return
	 * @exception @author
	 *                陈靖原<br>
	 * @date 2018年2月27日 下午4:54:10
	 */
	Runnable sendMessage();
}
