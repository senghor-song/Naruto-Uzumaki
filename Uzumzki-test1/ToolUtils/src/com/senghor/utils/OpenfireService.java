package com.senghor.utils;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class OpenfireService {
	private static final Logger LOGGER = Logger.getLogger(OpenfireService.class);
	private static final String CONFIGURL = "openfire.properties";

	/**
	 * 连接openfire服务器
	 * 
	 * @author Senghor<br>
	 * @date 2018年4月9日 下午2:47:27
	 */
	public static XMPPConnection getXMPPConnection() throws Exception{
		try {
			/** openfire的ip */
			String ip = GetPropertiesValue.getValue(CONFIGURL, "openfire.server.ip");
			/** openfire的端口 */
			int port = Integer.parseInt(GetPropertiesValue.getValue(CONFIGURL, "openfire.server.port"));
			/** openfire的服务器名 */
			String server = GetPropertiesValue.getValue(CONFIGURL, "openfire.server.name");
			/** 一个用于XMPP连接配置的构建器。 */
			ConnectionConfiguration config = new ConnectionConfiguration(ip, port, server);
			/** 自动连接 */
			config.setReconnectionAllowed(false);
			/** 设置连接时使用的tls安全模式。 */
			config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
			/** 发送消息 */
			config.setSendPresence(true);
			XMPPConnection connection = new XMPPConnection(config);
			connection.connect();// 开启连接
			connection.login(GetPropertiesValue.getValue(CONFIGURL, "user.name"), GetPropertiesValue.getValue(CONFIGURL, "user.password"));
			return connection;
		} catch (Exception e) {
			new Exception("连接openfire服务器异常" + e);
			LOGGER.error("连接openfire服务器异常" + e);
		}
		return null;
	}

	/**
	 * 新用户注册代码：
	 * 
	 * @return 0=注册用户失败1=成功2=当前账户已存在
	 * @author Senghor<br>
	 * @date 2018年4月9日 下午2:47:34
	 */
	public static int register(String username, String pass) throws Exception{
		try {
			Connection connection = OpenfireService.getXMPPConnection();
			AccountManager amgr = connection.getAccountManager();// 获取账户管理类
			amgr.createAccount(username, pass);
			return 1;
		} catch (Exception e) {
			String error = e.getMessage();
			if ("conflict(409)".equals(error)) {
				// 当前账户已存在
				return 2;
			}
			new Exception("注册用户失败" + e);
			LOGGER.error("注册用户失败" + e);
			return 0;
		}
	}

	/**
	 * 根据用户名发送消息
	 * 
	 * @param toUserName接收用户
	 * @param msg消息
	 * @author Senghor<br>
	 * @date 2018年4月9日 下午2:47:53
	 */
	public static void getConferenceRoom(String toUserName, String msg) throws Exception {
		try {
			// 连接openfire服务器
			Connection connection = OpenfireService.getXMPPConnection();
			ChatManager chatmanager = connection.getChatManager();
			Chat newChat = chatmanager.createChat(toUserName + "@" + GetPropertiesValue.getValue(CONFIGURL, "openfire.server.name"), new MessageListener() {
				public void processMessage(Chat chat, Message message) {
					if (message.getBody() != null) {
						LOGGER.info("name 【" + message.getFrom() + "】 : " + message.getBody());
					}
				}
			});
			newChat.sendMessage(msg);
		} catch (Exception e) {
			new Exception("消息发送失败" + e);
		}
	}
	
	/**
     * 发送广播消息
     * 
     * @param msg消息
     * @author Senghor<br>
     * @date 2018年4月26日 上午09:34:25
     */
    public static void sendBroadcastMessage(String msg) throws Exception {
        try {  
            Connection connection=OpenfireService.getConnection();  
            connection.connect();  
            connection.login(GetPropertiesValue.getValue(CONFIGURL, "user.name"), GetPropertiesValue.getValue(CONFIGURL, "user.password"));
            Message message = new Message();  
            message.setBody(msg);//设置消息。  
            message.setTo("all@broadcast." + GetPropertiesValue.getValue(CONFIGURL, "openfire.server.name"));//all@broadcast.yyp-pc 说明一下只需要改后面的yyp-pc改成 相应的域名。 我这里是自己机器的名字。  
            connection.sendPacket(message);  
        } catch (XMPPException e) {  
            e.printStackTrace();  
            new Exception("消息发送失败" + e);
        }
    }
    
//    public static void main(String[] args) {
//        try {
//            OpenfireService.sendBroadcastMessage("121231");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /** 
     * 获取连接 
     * @return connection 
     */  
    public static Connection getConnection() {  
        try {
            /** openfire的ip */
            String ip = GetPropertiesValue.getValue(CONFIGURL, "openfire.server.ip");
            /** openfire的端口 */
            int port = Integer.parseInt(GetPropertiesValue.getValue(CONFIGURL, "openfire.server.port"));
            ConnectionConfiguration config = new ConnectionConfiguration(ip, port);  
            Connection connection = new XMPPConnection(config);  
            return connection;  
        } catch (Exception e) {
            new Exception("连接openfire服务器异常" + e);
            LOGGER.error("连接openfire服务器异常" + e);
            return null;  
        }
    }  
}
