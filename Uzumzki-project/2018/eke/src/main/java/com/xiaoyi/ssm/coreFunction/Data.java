package com.xiaoyi.ssm.coreFunction;

/**  
 * @Description: 参数实体
 * @author 宋高俊  
 * @date 2018年7月17日 下午7:00:47 
 */ 
public class Data {
	
	public void put(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/** 参数名称 */
	private String name;
	/** 参数值 */
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
