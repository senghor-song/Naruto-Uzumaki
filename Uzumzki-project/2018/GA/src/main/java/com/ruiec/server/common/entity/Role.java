/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.ruiec.framework.server.support.entity.BaseEntity;
/**
 * 角色实体
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月22日
 */
@Entity
@Table(name="T_SYS_Role", indexes={
@Index(name="index_role_name", columnList="name", unique=true)
})
public class Role extends BaseEntity {

	private static final long serialVersionUID = 3845911912242873668L;
	
	@NotBlank
	private String name;//角色名称
	private String description;//角色描述
	private boolean isSystem;//是否是系统内置角色
	
	private Set<Admin> admins = new HashSet<Admin>();//关联的管理员（多对多）
	private List<String> authorities = new ArrayList<String>();//关联的权限（关联th_role_authority表）
	
	/**
	 * 角色名称
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getName() {
		return name;
	}
	/**
	 * 角色名称
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 角色描述
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 角色描述
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 是否是系统内置角色
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public boolean getIsSystem() {
		return isSystem;
	}
	/**
	 * 是否是系统内置角色
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setIsSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	/**
	 * 关联的管理员（多对多）
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@ManyToMany(mappedBy="roles",fetch=FetchType.LAZY)
	public Set<Admin> getAdmins() {
		return admins;
	}
	/**
	 * 关联的管理员（多对多）
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}
	
	/**
	 * 关联的权限（关联th_role_authority表）
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@ElementCollection
	@CollectionTable(name="T_SYS_role_authority")
	public List<String> getAuthorities() {
		return authorities;
	}
	/**
	 * 关联的权限（关联th_role_authority表）
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String toString() {
		return "Role [name=" + name + ", description=" + description
				+ ", is_system=" + isSystem + "]";
	}
	
}
