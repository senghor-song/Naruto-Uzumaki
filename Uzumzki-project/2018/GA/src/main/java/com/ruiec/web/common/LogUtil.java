package com.ruiec.web.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class LogUtil {
	/**
	 * 获取所有参数拼接
	 * @author Senghor<br>
	 * @date 2018年2月5日 下午6:09:28
	 */
	public static String getData(Map map) {
		StringBuffer data= new StringBuffer();
		for (Iterator iter=map.entrySet().iterator();iter.hasNext();) {
			Map.Entry entry=(Entry) iter.next();
			Object key=entry.getKey();
			String[] str=(String[]) entry.getValue();
			for (String string : str) {
				if(!"".equals(string)){
					data.append(key);
					data.append("=");
					data.append(string);
					data.append("&");
				}
			}
			
		}
		return data.toString();
	}
	
	/**
	 * 获取所有参数拼接
	 * @author Senghor<br>
	 * @date 2018年2月5日 下午6:09:28
	 */
	public static void replaceAllJs(HttpServletRequest request) {
		Map map = request.getParameterMap();
		StringBuffer data= new StringBuffer();
		for (Iterator iter=map.entrySet().iterator();iter.hasNext();) {
			Map.Entry entry=(Entry) iter.next();
			Object key=entry.getKey();
			String[] str=(String[]) entry.getValue();
			for (String string : str) {
				string = string.replaceAll("<", "《");
				string = string.replaceAll(">", "》");
				map.put(key, string);
			}
		}
	}
}
