package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 布控人类型
 * 
 * @author 陈靖原<br>
 * @date 2018年1月8日 下午1:45:41
 */
@Entity
@Table(name = "T_COR_MONITOR_TYPE")
public class MonitorType extends BaseEntity {

	private static final long serialVersionUID = -2572079535179469861L;

	/** 关联布控人员实体 */
	private Monitor monitor;
	/** 关联字典数据实体 */
	private Integer dictionaryId;

	/** 关联布控人员实体 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MONITOR_ID")
	public Monitor getMonitor() {
		return this.monitor;
	}

	/** 关联布控人员实体 */
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	/** 关联字典数据实体 */
	@Column(name = "DICTIONARY_ID")
	public Integer getDictionaryId() {
		return this.dictionaryId;
	}

	/** 关联字典数据实体 */
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
}
