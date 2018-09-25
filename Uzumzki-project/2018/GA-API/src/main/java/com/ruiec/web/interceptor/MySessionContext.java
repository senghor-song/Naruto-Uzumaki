package com.ruiec.web.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * 全局静态Map,保存session
 * @author 陈靖原<br>
 * @date 2017年12月25日 上午11:02:01
 */
public class MySessionContext {
	@SuppressWarnings("rawtypes")
	private static HashMap mymap = new HashMap();

	@SuppressWarnings("unchecked")
	public static synchronized void AddSession(HttpSession session) {
		if (session != null) {
			mymap.put(session.getId(), session);
		}
	}

	public static synchronized void DelSession(HttpSession session) {
		if (session != null) {
			mymap.remove(session.getId());
		}
	}

	public static synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) mymap.get(session_id);
	}
}
