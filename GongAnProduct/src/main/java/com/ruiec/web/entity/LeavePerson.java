package com.ruiec.web.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ruiec.framework.server.support.entity.BaseEntity;


/**
 * 离市人员实体
 * @author yk<br>
 * @date 2017年12月1日 上午11:00:41
 */

@Entity
@Table(name="T_COR_LEAVE_PERSON", uniqueConstraints = @UniqueConstraint(columnNames={"ID_CARD", "CREATE_TIME"}))
public class LeavePerson  extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
     /** 姓名 */
     private String name;
     /** 性别 */
     private String sex;
     /** 身份证号 */
     private String idCard;
     /** 轨迹类型编码 */
     private Integer dynamicInfoId;
     /** 出行时间 */
     private String departureTime;
     /** 目的地 */
     private String endPlace;
     /** 户籍地址*/
     private String nativePlace;
     /** 户籍地址派出所编码(原来用于存储预警地派出所编码) */
     private Integer nativePlacePoliceId;
     /** 户籍地址公安机关 */
     private String nativePlacePoliceStationCo;
     /**人员类别  */
     private String personType;
     /** 触发时间 */
     private String triggerTime;
     /** 唯一索引 */
     private String theIndex;
     /** 是否签收:0=未签收,1=已签收*/
     private String isSign;
     /** 签收人姓名  */
     private Integer signPersonId;
     /** 签收单位编码 */
     private Integer signUnitId;
     /** 出发地*/
     private String startPlace;
     /** 户籍地责任派出所编码 */
     private Integer nativePlaceResponsibilityId;
     /** 重点人关系 */
     private List<ControlPersonRelation> controlPersonRelations;


     /** 姓名 */
    @Column(name="NAME", nullable=false)
    public String getName() {
        return this.name;
    }

    /** 姓名 */
    public void setName(String name) {
        this.name = name;
    }
    
    /** 性别 */
    @Column(name="SEX", nullable=false)
    public String getSex() {
        return this.sex;
    }

    /** 性别 */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /** 身份证号 */
    @Column(name="ID_CARD", nullable=false)
    public String getIdCard() {
        return this.idCard;
    }

    /** 身份证号 */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    
    /** 轨迹类型编码 */
    @Column(name="DYNAMIC_INFO_ID")
    public Integer getDynamicInfoId() {
        return this.dynamicInfoId;
    }

    /** 轨迹类型编码 */
    public void setDynamicInfoId(Integer dynamicInfoId) {
        this.dynamicInfoId = dynamicInfoId;
    }
    
    /** 出行时间 */
    @Column(name="DEPARTURE_TIME")
    public String getDepartureTime() {
        return this.departureTime;
    }

    /** 出行时间 */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    /** 目的地 */
    @Column(name="END_PLACE")
    public String getEndPlace() {
        return this.endPlace;
    }

    /** 目的地 */ 
    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }
    
    /** 户籍地址*/
    @Column(name="NATIVE_PLACE")
    public String getNativePlace() {
        return this.nativePlace;
    }

    /** 户籍地址*/
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    
    /** 户籍地址派出所编码(原来用于存储预警地派出所编码) */
    @Column(name="NATIVE_PLACE_POLICE_ID")
    public Integer getNativePlacePoliceId() {
        return this.nativePlacePoliceId;
    }

    /** 户籍地址派出所编码(原来用于存储预警地派出所编码) */
    public void setNativePlacePoliceId(Integer nativePlacePoliceId) {
        this.nativePlacePoliceId = nativePlacePoliceId;
    }
    
    /** 户籍地址公安机关 */
    @Column(name="NATIVE_PLACE_POLICE_STATION_CO")
    public String getNativePlacePoliceStationCo() {
        return this.nativePlacePoliceStationCo;
    }

    /** 户籍地址公安机关 */
    public void setNativePlacePoliceStationCo(String nativePlacePoliceStationCo) {
        this.nativePlacePoliceStationCo = nativePlacePoliceStationCo;
    }
    
    /**人员类别  */
    @Column(name="PERSON_TYPE")
    public String getPersonType() {
        return this.personType;
    }

    /**人员类别  */
    public void setPersonType(String personType) {
        this.personType = personType;
    }
    
    /** 触发时间 */
    @Column(name="TRIGGER_TIME")
    public String getTriggerTime() {
        return this.triggerTime;
    }

    /** 触发时间 */
    public void setTriggerTime(String triggerTime) {
        this.triggerTime = triggerTime;
    }
    
    /** 唯一索引 */
    @Column(name="THE_INDEX")
    public String getTheIndex() {
        return this.theIndex;
    }

    /** 唯一索引 */
    public void setTheIndex(String theIndex) {
        this.theIndex = theIndex;
    }
    
    /** 是否签收:0=未签收,1=已签收*/
    @Column(name="IS_SIGN", nullable=false, length=20)
    public String getIsSign() {
        return this.isSign;
    }

    /** 是否签收:0=未签收,1=已签收*/
    public void setIsSign(String isSign) {
        this.isSign = isSign;
    }
    
    /** 签收人姓名  */
    @Column(name="SIGN_PERSON_ID")
    public Integer getSignPersonId() {
        return this.signPersonId;
    }

    /** 签收人姓名  */
    public void setSignPersonId(Integer signPersonId) {
        this.signPersonId = signPersonId;
    }
    
    /** 签收单位编码 */
    @Column(name="SIGN_UNIT_ID")
    public Integer getSignUnitId() {
        return this.signUnitId;
    }

    /** 签收单位编码 */
    public void setSignUnitId(Integer signUnitId) {
        this.signUnitId = signUnitId;
    }
    
    /** 出发地*/
    @Column(name="START_PLACE")
    public String getStartPlace() {
        return this.startPlace;
    }

    /** 出发地*/
    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }
    
    /** 户籍地责任派出所编码 */
    @Column(name="NATIVE_PLACE_RESPONSIBILITY_ID")
    public Integer getNativePlaceResponsibilityId() {
        return this.nativePlaceResponsibilityId;
    }

    /** 户籍地责任派出所编码 */
    public void setNativePlaceResponsibilityId(Integer nativePlaceResponsibilityId) {
        this.nativePlaceResponsibilityId = nativePlaceResponsibilityId;
    }

    /** 重点人关系 */
	@Transient
	public List<ControlPersonRelation> getControlPersonRelations() {
		return controlPersonRelations;
	}
    
	/** 重点人关系 */
	public void setControlPersonRelations(List<ControlPersonRelation> controlPersonRelations) {
		this.controlPersonRelations = controlPersonRelations;
	}

	/** 重点人关系 */
	public void setControlPersonRelation(ControlPersonRelation controlPersonRelation) {
		if (this.controlPersonRelations == null) {
			this.controlPersonRelations = new ArrayList<ControlPersonRelation>();
		}
		this.controlPersonRelations.add(controlPersonRelation);
	}
}