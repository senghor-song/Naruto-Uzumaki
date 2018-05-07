package com.ruiec.web.common;

/**
 * 轨迹获取数据对象
 * 
 * @author Senghor<br>
 * @date 2017年12月30日 下午9:24:27
 */
public class JsonData {
	/** json的key值 */
	private String key;
	/** json的属性值 */
	private String value;
	/** json的数据类型值 */
	private String type;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
