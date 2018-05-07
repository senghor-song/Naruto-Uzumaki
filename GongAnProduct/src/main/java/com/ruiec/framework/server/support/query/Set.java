/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.query;

import java.io.Serializable;
/**
 * 数据修改封装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月25日
 */
public class Set implements Serializable {

	private static final long serialVersionUID = -1651318539316701377L;

	private String name;//更新字段名称
	private String nameAlias;//更新字段别名（避免与过滤中的占位名同名冲突）
	private Object value;//更新值
	private Type type;//更新值类型
	
	public Set(){
		
	}
	
	public Set(String name, Object value, Type type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	public Set(String name, String nameAlias, Object value, Type type) {
		this.name = name;
		this.nameAlias = nameAlias;
		this.value = value;
		this.type = type;
	}

	/**
	 * 获取set组装类
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public static SetBuilder build(){
		return new SetBuilder();
	}
	
	/**
	 * 获取单值类型的set对象
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public static Set single(String name, Object value){
		return new Set(name, value, Type.single);
	}
	/**
	 * 获取单值类型的set对象
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public static Set single(String name, String nameAlias, Object value){
		return new Set(name, nameAlias, value, Type.single);
	}
	/**
	 * 获取多值类型的set对象
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public static Set list(String name, Object value){
		return new Set(name, value, Type.list);
	}
	
	/**
	 * 获取多值类型的set对象
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public static Set list(String name, String nameAlias, Object[] value){
		return new Set(name, nameAlias, value, Type.list);
	}
	
	
	/**
	 * 
	 * 版权所有：深圳源中瑞科技有限公司<br>
	 * 网 址：www.ruiec.com<br>
	 * 电 话：0755-33581131<br><br>
	 * 
	 * 更新值类型枚举
	 * @author 肖学良<br>
	 * Version: 1.0<br>
	 * Date: 2015年12月25日
	 */
	public enum Type{
		
		single("单个元素"),
		list("多个元素");
		
		private String name;

		private Type(String name){
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	/**
	 * 获取更新字段
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置更新字段
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 更新字段别名（避免与过滤中的占位名同名冲突）
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public String getNameAlias() {
		return nameAlias;
	}

	/**
	 * 更新字段别名（避免与过滤中的占位名同名冲突）
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public void setNameAlias(String nameAlias) {
		this.nameAlias = nameAlias;
	}

	/**
	 * 获取更新值
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置更新值
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 获取更新值类型
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 获取更新值类型
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	
}
