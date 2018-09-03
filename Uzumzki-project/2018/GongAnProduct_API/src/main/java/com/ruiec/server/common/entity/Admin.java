/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.ruiec.framework.server.support.entity.BaseEntity;
/**
 * 管理员实体
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月22日
 */
@Entity
@Table(name="T_SYS_Admin", indexes={
@Index(name="index_admin_username", columnList="username", unique=true)
})
public class Admin extends BaseEntity{

	private static final long serialVersionUID = 4069774545690196376L;

	public static final String ADMIN_LOGIN_SIGN = "admin_login_sign_xxl";
	
	private String name; // 名称
	@NotNull
	@NotBlank
	private String username;//用户名
	@NotNull
	@NotBlank
	private String password;//密码
	@Email
	private String email;//邮箱
	private boolean isEnabled;//是否激活
	private boolean isSystem;//是否是系统内置管理员
	private boolean isLocked;//是否锁定
	private Date lockedDate;//锁定时间
	private Date loginDate;//登录时间
	private String loginIp;//登录IP
	private int loginFailureCount;//登录失败数
	private String department;//部门
	private String realName;//真实姓名
	private Sex sex;//性别(枚举)
	private String phone;//手机号码
	private String address;//地址
	

	private Set<Role> roles = new HashSet<Role>();
	
	/**
	 * 名称
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getName() {
		return name;
	}
	/**
	 * 名称
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 用户名
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@Column(updatable=false)
	public String getUsername() {
		return username;
	}
	/**
	 * 用户名
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 密码
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 密码
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 邮箱
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 邮箱
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 是否激活
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public boolean getIsEnabled() {
		return isEnabled;
	}
	/**
	 * 是否激活
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * 是否锁定
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public boolean getIsLocked() {
		return isLocked;
	}
	/**
	 * 是否锁定
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	/**
	 * 锁定时间
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public Date getLockedDate() {
		return lockedDate;
	}
	/**
	 * 锁定时间
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	/**
	 * 登录时间
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public Date getLoginDate() {
		return loginDate;
	}
	/**
	 * 登录时间
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	/**
	 * 登录IP
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getLoginIp() {
		return loginIp;
	}
	/**
	 * 登录IP
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * 登录失败次数
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public int getLoginFailureCount() {
		return loginFailureCount;
	}
	/**
	 * 登录失败次数
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setLoginFailureCount(int loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	/**
	 * 部门
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * 部门
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * 角色(多对多)
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="T_SYS_admin_role")
	public Set<Role> getRoles() {
		return roles;
	}
	
	/**
	 * 角色(多对多)
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * 真实名称
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getRealName() {
		return realName;
	}
	
	/**
	 * 真实名称
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * 性别(枚举)
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public Sex getSex() {
		return sex;
	}
	/**
	 * 性别(枚举)
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	/**
	 * 手机号码
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 手机号码
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 地址
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 地址
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 是否是系统内置管理员
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public boolean getIsSystem() {
		return isSystem;
	}
	/**
	 * 是否是系统内置管理员
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	public void setIsSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}

	/**
	 * 
	 * 版权所有：深圳源中瑞科技有限公司<br>
	 * 网 址：www.ruiec.com<br>
	 * 电 话：0755-33581131<br><br>
	 * 
	 * 性别枚举类
	 * @author 肖学良<br>
	 * Version: 1.0<br>
	 * Date: 2015年12月22日
	 */
	public enum Sex{
		male, female
	}


	@Override
	public String toString() {
		return "Admin [name=" + name + ", username=" + username + ", password="
				+ password + ", email=" + email + ", is_enabled=" + isEnabled
				+ ", is_system=" + isSystem + ", is_locked=" + isLocked
				+ ", locked_date=" + lockedDate + ", login_date=" + loginDate
				+ ", login_ip=" + loginIp + ", login_failure_count="
				+ loginFailureCount + ", department=" + department
				+ ", realName=" + realName + ", sex=" + sex + ", phone="
				+ phone + ", address=" + address + ", roles=" + roles + "]";
	}
	
	
	
}
