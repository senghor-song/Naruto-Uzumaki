/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.query;

import java.io.Serializable;
import java.util.TreeSet;
/**
 * 初始化组装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年1月9日
 */
public class InitPathBuilder implements Serializable {

	private static final long serialVersionUID = 4619451744974498450L;
	
	private java.util.Set<String> initPathSet = new TreeSet<String>();
	
	public static InitPathBuilder create(){
		return new InitPathBuilder();
	}
	
	/**
	 * 添加初始化路径
	 * @author 肖学良<br>
	 * Date: 2016年1月9日
	 */
	public InitPathBuilder add(String initPath){
		initPathSet.add(initPath);
		return this;
	}
	/**
	 * 返回初始化路径集
	 * @author 肖学良<br>
	 * Date: 2016年1月9日
	 */
	public java.util.Set<String> build(){
		return initPathSet;
	}
}
