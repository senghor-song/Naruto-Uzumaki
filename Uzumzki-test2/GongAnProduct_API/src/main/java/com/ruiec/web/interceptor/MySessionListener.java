package com.ruiec.web.interceptor;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听,创建session时，添加到全局静态Map里
 * @author 陈靖原<br>
 * @date 2017年12月25日 上午11:01:37
 */
public class MySessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		MySessionContext.AddSession(httpSessionEvent.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		MySessionContext.DelSession(session);
	}
}
