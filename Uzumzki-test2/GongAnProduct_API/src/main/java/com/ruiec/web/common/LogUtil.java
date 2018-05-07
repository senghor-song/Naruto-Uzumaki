package com.ruiec.web.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LogUtil {
	public static String getData(Map map) {
		StringBuffer data = new StringBuffer();
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Entry) iter.next();
			Object key = entry.getKey();
			String[] str = (String[]) entry.getValue();
			for (String string : str) {
				if (!"".equals(string)) {
					data.append(key);
					data.append("=");
					data.append(string);
					data.append("&");
				}
			}

		}
		return data.toString();
	}
}
