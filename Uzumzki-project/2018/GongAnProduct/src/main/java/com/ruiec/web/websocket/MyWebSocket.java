package com.ruiec.web.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ruiec.web.common.GlobalUnit;

@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
	private static final Logger LOGGER = Logger.getLogger(MyWebSocket.class);

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
		this.session = session;
		LOGGER.info("有新连接" + session.getId() + "加入！");
		webSocketSet.add(this);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        LOGGER.info("有一连接" + session.getId() + "关闭");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 
     * 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message,HttpServletRequest request, Session session) {
    	//LOGGER.info("来自客户端" + session.getId() + "的消息:" + message);
    	try {
    		Thread.currentThread().sleep(500);
    		while (GlobalUnit.isOK) {
    			Thread.currentThread().sleep(100);
    			if (GlobalUnit.count > 0) {
					//session.getBasicRemote().sendText(String.valueOf(result_new));
					this.session.getAsyncRemote().sendText(String.valueOf(GlobalUnit.count)+","+String.valueOf(GlobalUnit.total)+","
					+String.valueOf(GlobalUnit.successTotal)+","+String.valueOf(GlobalUnit.failTotal));
				}
    			
			}
    		GlobalUnit.total=0;
    		GlobalUnit.count=0;
    		GlobalUnit.successTotal=0;
    		GlobalUnit.failTotal=0;
    		// 关闭连接
    		onClose();
		} catch (Exception e) {
			LOGGER.error("IO错误", e);
		}
    }

    /**
     * 发生错误时调用
     * */
    @OnError
    public void onError(Session session, Throwable error) {
    	LOGGER.error("发生错误", error);
        //error.printStackTrace();
    }
}