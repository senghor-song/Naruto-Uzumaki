/*
* 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 功能菜单(后台使用)
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月22日
 */
@Entity
@Table(name="T_SYS_Menu")
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 3951659597820911403L;
	
	private String id;//主键
	private Integer sort;//排序
	private String name;//菜单名
	private String link;//菜单链接
	private String className;//样式名字
	
	private Menu parent;//上级菜单
	private List<Menu> children = new ArrayList<Menu>();//下级菜单(一对多)
	
	
	
	/**
	 * 主键
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@Id
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 排序
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 功能菜单名称 
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 功能菜单名称 
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 功能菜单链接
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * 功能菜单链接
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * css样式的class名字
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * css样式的class名字
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 功能菜单父级
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	public Menu getParent() {
		return parent;
	}
	
	/**
	 * 功能菜单父级
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	/**
	 * 功能菜单子级
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@OneToMany(fetch=FetchType.LAZY, mappedBy="parent")
	//@OrderBy("sort asc")
	public List<Menu> getChildren() {
		return children;
	}
	
	/**
	 * 功能菜单子级
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
}
