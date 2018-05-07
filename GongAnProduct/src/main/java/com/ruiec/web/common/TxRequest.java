package com.ruiec.web.common;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 功能：封装的请求处理特殊字符
 * 
 * @author qinzhitian<br>
 * @date 2018年2月6日 下午5:09:47
 */
public class TxRequest extends HttpServletRequestWrapper {
	// 结果集
	private Map<String, String[]> params;

	public TxRequest(HttpServletRequest request, Map<String, String[]> newParams) {
		super(request);
		this.params = newParams;
	}

	/**
	 * 获取结果集
	 * @author qinzhitian<br>
	 * @date 2018年2月6日 下午5:57:12
	 */
	public Map<String, String[]> getParameterMap() {
		return params;
	}

	public Enumeration getParameterNames() {
		Vector l = new Vector(params.keySet());
		return l.elements();
	}

	/**
	 * 替换字符串数组中的<br>
	 * "<"->"$lt",<br>
	 * ">"->"$gt"<br>
	 * 并返回结果
	 * @author qinzhitian<br>
	 * @date 2018年2月6日 下午5:59:23
	 */
	public String[] getParameterValues(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			String[] value = (String[]) v;
			for (int i = 0; i < value.length; i++) {
				value[i] = value[i].replaceAll("<", "&lt;");
				value[i] = value[i].replaceAll(">", "&gt;");
			}
			return (String[]) value;
		} else if (v instanceof String) {
			String value = (String) v;
			value = value.replaceAll("<", "&lt;");
			value = value.replaceAll(">", "&gt;");
			return new String[] { (String) value };
		} else {
			return new String[] { v.toString() };
		}
	}

	/**
	 * 替换字符串中的<br>
	 * "<"->"$lt",<br>
	 * ">"->"$gt"<br>
	 * 并返回结果
	 * @author qinzhitian<br>
	 * @date 2018年2月6日 下午5:59:23
	 */
	public String getParameter(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				String value = strArr[0];
				value = value.replaceAll("<", "&lt;");
				value = value.replaceAll("<", "&gt;");
				return value;
			} else {
				return null;
			}
		} else if (v instanceof String) {
			String value = (String) v;
			value = value.replaceAll("<", "&lt;");
			value = value.replaceAll(">", "&gt;");
			return (String) value;
		} else {
			return v.toString();
		}
	}
}
