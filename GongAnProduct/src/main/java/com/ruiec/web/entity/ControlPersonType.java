package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 重点人员管理字典数据之人员类别中间实体
 * @author Senghor<br>
 * @date 2017年12月13日 上午10:57:23
 */
@Entity
@Table(name = "T_COR_CONTROL_PERSON_TYPE")
public class ControlPersonType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 关联重点人员实体 */
	private ControlPerson controlPerson;
	/** 关联字典数据实体*/
	private Integer dictionaryId;
	
	/** 关联重点人员实体 */
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PERSON_ID")
	public ControlPerson getControlPerson() {
		return this.controlPerson;
	}

	/** 关联重点人员实体 */
	public void setControlPerson(ControlPerson controlPerson) {
		this.controlPerson = controlPerson;
	}

	/** 关联字典数据实体 */
    @Column(name="DICTIONARY_ID")
	public Integer getDictionaryId() {
		return this.dictionaryId;
	}

	/** 关联字典数据实体 */
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
}