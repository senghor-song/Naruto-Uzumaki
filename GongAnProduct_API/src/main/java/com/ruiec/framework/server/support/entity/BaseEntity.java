/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 实体基类<br>
 * 提供id,创建和修改日期
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月17日
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE, getterVisibility=JsonAutoDetect.Visibility.NONE, setterVisibility=JsonAutoDetect.Visibility.NONE, isGetterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 8877657215349486681L;
	
	private Integer id; //ID
	private Date createDate;//创建时间
	private Date modifyDate;//修改时间
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PRIKEY")
	public Integer getId() {
		return id;
	}
	/**
	 * ID
	 * @return
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 创建时间
	 * @return
	 */
//	@JsonProperty
	@Column(name="CREATE_TIME",updatable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 创建时间
	 * @return
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 修改时间
	 * @return
	 */
	@Column(name="MODIFY_TIME")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh", timezone="GMT+8")
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * 修改时间
	 * @return
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public void onSave(){
		
	}
	public void onUpdate(){
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
