package com.ruiec.web.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Message;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.MessageService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.util.OpenfireService;
import com.ruiec.web.util.RuiecBeanUtils;
import com.ruiec.web.util.SpringUtils;

/**
 * 消息服务接口实现类
 * 
 * @author 陈靖原<br>
 * @date 2018年2月27日 下午4:52:27
 */
@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl<Message, Integer> implements MessageService {
	private static final Logger LOGGER = Logger.getLogger(MessageServiceImpl.class);

	/**
	 * 发送消息
	 * 
	 * @param sourceId消息来源ID
	 * @return
	 * @exception @author
	 *                陈靖原<br>
	 * @date 2018年2月27日 下午4:59:06
	 */
	@Override
	@Transactional
	public Runnable sendMessage() {
		return new Runnable() {
			@Override
			public void run() {
				try {
					MessageService messageService = (MessageService) SpringUtils.getBean("messageServiceImpl");
					// 获取所有未推送消息
					List<Message> list = messageService.findList(null, Filter.eq("isPush", 0), null);
					int succeedNum = 0;
					int errorNum = 0;
					for (Message message : list) {
						JSONObject json = new JSONObject();
						json.put("type", message.getMessageType());
						json.put("id", message.getId());
						String msg = "";
						if ("1".equals(message.getMessageType().toString())) {
                            msg = "您有一条新的重点人预警消息需要查收";
                        }else if ("2".equals(message.getMessageType().toString())) {
							msg = "您有一条新的布控预警消息需要查收";
						}else if ("3".equals(message.getMessageType().toString())) {
							msg = "您有一条新的合成作战任务需要查收";
						}else if ("4".equals(message.getMessageType().toString())) {
                            msg = "您有一条新的公告消息需要查看";
                        }else if ("5".equals(message.getMessageType().toString())) {
                            msg = "首页图片更新";
                        }
						json.put("msg", msg);
						json.put("title", GlobalUnit.GONGAN);
						if (message.getSourceId() == 0) {
						    try {
                                OpenfireService.sendBroadcastMessage(json.toString());
                            } catch (Exception e) {
                                LOGGER.error("消息推送失败！" + e);
                            }
                        }else {
                            User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, message.getReceiveId());
                            //注册用户
                            int registerReturn = OpenfireService.register(user.getIdCard(), "admin");
                            LOGGER.info(user.getIdCard() + (registerReturn == 1 ? "注册成功" : registerReturn == 0? "注册失败" : "该用户已注册"));
                            try {
                                OpenfireService.getConferenceRoom( user.getIdCard().toLowerCase(), json.toString());
                            } catch (Exception e) {
                                errorNum++;
                                continue;
                            }
                            //发送成功加1
                            succeedNum++;
                        }
						//修改推送状态为已推送
						Message newMessage = new Message();
						newMessage.setId(message.getId());
						newMessage.setIsPush(1);
						messageService.updateInclude(newMessage, new String[]{"isPush"}, null);
					}
					LOGGER.info("向app端成功推送：" + succeedNum + "条消息！未成功推送："+errorNum+"条信息！");
				} catch (Exception e) {
					LOGGER.error("消息推送失败！" + e);
				}
			}
		};
	}
}
