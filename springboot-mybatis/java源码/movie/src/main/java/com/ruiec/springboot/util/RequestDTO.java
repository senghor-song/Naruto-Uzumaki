package com.ruiec.springboot.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 请求传输对象
 * 
 * @author mrluo735
 *
 */
public class RequestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1380879395062010464L;

	private Map<String, Object> data = new HashMap<String, Object>();

	private String locale = "zh_CN";

	/**
	 * 重载+1 构造函数
	 */
	public RequestDTO() {
	}

	/**
	 * 获取data
	 * 
	 * @return
	 */
	public Map<String, Object> getData() {
		return this.data;
	}

	/**
	 * 获取方言
	 * 
	 * @return
	 */
	public Locale getLocale() {
		if (this.locale==null || this.locale.isEmpty() || this.locale.indexOf("_") < 0)
			return Locale.SIMPLIFIED_CHINESE;
		try {
			String[] locales = this.locale.split("_");
			return new Locale(locales[0], locales[1]);
		} catch (Exception ex) {
			return Locale.SIMPLIFIED_CHINESE;
		}
	}

	/**
	 * 设置方言
	 * 
	 * @param locale
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * 重载+1 put数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public RequestDTO put(String key, Object value) {
		data.put(key, value);
		return this;
	}

	/**
	 * 重载+2 put map数据
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RequestDTO put(Map map) {
		this.data.putAll(map);
		return this;
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) data.get(key);
	}


	/**
	 * 清除
	 * 
	 * @return
	 */
	public RequestDTO clear() {
		this.data.clear();
		return this;
	}

	/**
	 * 是否包含key
	 */
	public boolean containsKey(String key) {
		return data.containsKey(key);
	}

	/**
	 * 是否包含值
	 * 
	 * @param value
	 * @return
	 */
	public boolean containsValue(Object value) {
		return data.containsValue(value);
	}

	/**
	 * key 存在，并且 value 不为 null
	 */
	public boolean notNull(String key) {
		return data.get(key) != null;
	}

	/**
	 * key 不存在，或者 key 存在但 value 为null
	 */
	public boolean isNull(String key) {
		return data.get(key) == null;
	}

	/**
	 * key 存在，并且 value 为 true，则返回 true
	 */
	public boolean isTrue(String key) {
		Object value = data.get(key);
		return (value instanceof Boolean && ((Boolean) value == true));
	}

	/**
	 * key 存在，并且 value 为 false，则返回 true
	 */
	public boolean isFalse(String key) {
		Object value = data.get(key);
		return (value instanceof Boolean && ((Boolean) value == false));
	}

	/**
	 * 移除数据
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T remove(String key) {
		return (T) data.remove(key);
	}

	/**
	 * 
	 * @param RequestDTO
	 * @return
	 */
	public boolean equals(RequestDTO RequestDTO) {
		return RequestDTO != null && this.data.equals(RequestDTO.data);
	}
}
