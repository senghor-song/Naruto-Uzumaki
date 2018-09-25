package com.ruiec.web.enumeration;

/**
 * 单位级别枚举类
 * @author Senghor<br>
 * @date 2017年12月18日 下午3:26:47
 */
public enum UnitRank {
	
	provide("provide"), 
	city("city"), 
	area("area"), 
	town("town"), 
	other1("other1"), 
	other2("other2");
	
	private String name;

	private UnitRank(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
