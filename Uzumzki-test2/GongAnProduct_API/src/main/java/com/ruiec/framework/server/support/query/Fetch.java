/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.query;

import java.io.Serializable;

import org.hibernate.FetchMode;
import org.hibernate.sql.JoinType;

/**
 * 抓取策略封装类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月11日
 */
public class Fetch implements Serializable {

	private static final long serialVersionUID = -6468848474236186907L;

	private String associationPath;//关联路径
	
	private FetchMode fetchMode;//连接抓取策略
	
	private String alias;//关联别名
	
	private JoinType joinType;

	public Fetch(String associationPath, FetchMode fetchMode) {
		this.associationPath = associationPath;
		this.fetchMode = fetchMode;
	}
	
	public Fetch(String associationPath, String alias, JoinType joinType, FetchMode fetchMode) {
		this.associationPath = associationPath;
		this.alias = alias;
		this.joinType = joinType;
		this.fetchMode = fetchMode;
	}
	
	/**
	 * 获取连接抓取策略
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	public static Fetch join(String associationPath){
		return new Fetch(associationPath, FetchMode.JOIN);
	}
	/**
	 * 获取子查询抓取策略
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	public static Fetch select(String associationPath){
		return new Fetch(associationPath, FetchMode.SELECT);
	}
	/**
	 * 创建关联别名
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	public static Fetch alias(String associationPath, String alias){
		return new Fetch(associationPath, alias, null, null);
	}
	
	/**
	 * 创建关联别名
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	public static Fetch alias(String associationPath, String alias, JoinType joinType){
		return new Fetch(associationPath, alias, joinType, null);
	}

	/**
	 * 获取关联路径
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	public String getAssociationPath() {
		return associationPath;
	}

	/**
	 * 获取抓取策略
	 * @author 肖学良<br>
	 * Date: 2015年12月11日
	 */
	public FetchMode getFetchMode() {
		return fetchMode;
	}
	/**
	 * 关联别名
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * 关联别名
	 * @author 肖学良<br>
	 * Date: 2015年12月15日
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 连接方式
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public JoinType getJoinType() {
		return joinType;
	}

	/**
	 * 连接方式
	 * @author 肖学良<br>
	 * Date: 2015年12月25日
	 */
	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}
	
}
