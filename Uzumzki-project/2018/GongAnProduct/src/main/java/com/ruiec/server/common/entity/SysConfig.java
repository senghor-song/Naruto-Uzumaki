/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 系统配置项实体类
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月21日
 */
@Entity
@Table(name="T_SYS_SysConfig", indexes={@Index(name="index_sys_name",columnList="name",unique=true)})
public class SysConfig extends BaseEntity{

	private static final long serialVersionUID = -333728582005253694L;
	
	private String name;//名称
	private String value;//数值
	private String desc;//描述
	
	
	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 数值
	 */
	@Lob
	public String getValue() {
		return value;
	}
	/**
	 * 数值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 描述
	 */
	@Column(name="description")
	public String getDesc() {
		return desc;
	}
	/**
	 * 描述
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
