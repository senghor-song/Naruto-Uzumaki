package com.ruiec.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static Cookie getCookie(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i];
			}
		}
		return null;
	}

	public static void addCookie(String name, String value, int minute, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60 * minute);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void addCookie(String name, String value, HttpServletResponse response) {
		// addCookie(name, value, 0, response);
		Cookie cookie = new Cookie(name, value);
		// cookie.setMaxAge(60*minute);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void clearCookie(String name, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
