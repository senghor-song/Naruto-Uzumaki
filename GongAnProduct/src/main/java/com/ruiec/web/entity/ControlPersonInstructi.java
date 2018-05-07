/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 重点人预警指令实体
 * @author Senghor<br>
 * @date 2017年11月28日 下午3:54:37
 */
@Entity
@Table(name="T_COR_CONTROL_PERSON_INSTRUCTI")
public class ControlPersonInstructi extends BaseEntity implements java.io.Serializable {
    /**  */
	private static final long serialVersionUID = -1309614512012810571L;

    /** 指令状态   0为未签收  1为已签收 2为待审核 3未审核通过 4未审核拒绝 */
    private Integer instructionsState;
    
    /** 下发人的民警ID */
    private Integer issuedPolicePrikey;
    
    /** 指令接受人的民警ID */
    private Integer instructionRecipientPrikey;
    
    /** 四色预警，1是红色2是橙色3是黄色4是橙色 */
    private Integer fourColourWarning;
    
    /** 动态信息 */
    private String dynamicsInformation;
    
    /** 下发人的单位Id */
    private Integer issuedPoliceUnitId;
    
    /** 指令接受人的单位Id */
    private Integer instructionRecipientUnitId;
    
    /** 关联预警ID */
    private ControlPersonAlarm controlPersonAlarm;
    
    /** 指令和反馈以JSON进行追加 */
    private String instructionFeedbackInformati;
    
    /** 操作日志 */
    private String operationLog;
    
    /** 重点人主键 */
    private ControlPerson controlPerson;

    /** 指令状态   0为未签收  1为已签收 2为待审核 3未审核通过 4未审核拒绝 */
    @Column(name="INSTRUCTIONS_STATE", nullable=false, precision=22, scale=0)
    public Integer getInstructionsState() {
        return this.instructionsState;
    }
    
    /** 指令状态   0为未签收  1为已签收 2为待审核 3未审核通过 4未审核拒绝 */
    public void setInstructionsState(Integer instructionsState) {
        this.instructionsState = instructionsState;
    }
    
    /** 下发人的民警ID */
    @Column(name="ISSUED_POLICE_PRIKEY", nullable=false, precision=22, scale=0)
    public Integer getIssuedPolicePrikey() {
        return this.issuedPolicePrikey;
    }
    
    /** 下发人的民警ID */
    public void setIssuedPolicePrikey(Integer issuedPolicePrikey) {
        this.issuedPolicePrikey = issuedPolicePrikey;
    }
    
    /** 指令接受人的民警ID */
    @Column(name="INSTRUCTION_RECIPIENT_PRIKEY", nullable=false, precision=22, scale=0)
    public Integer getInstructionRecipientPrikey() {
        return this.instructionRecipientPrikey;
    }
    
    /** 指令接受人的民警ID */
    public void setInstructionRecipientPrikey(Integer instructionRecipientPrikey) {
        this.instructionRecipientPrikey = instructionRecipientPrikey;
    }
    
    /** 四色预警，1是红色2是橙色3是黄色4是橙色 */
    @Column(name="FOUR_COLOUR_WARNING", nullable=false, precision=22, scale=0)
    public Integer getFourColourWarning() {
        return this.fourColourWarning;
    }
    
    /** 四色预警，1是红色2是橙色3是黄色4是橙色 */
    public void setFourColourWarning(Integer fourColourWarning) {
        this.fourColourWarning = fourColourWarning;
    }

    /** 动态信息 */
    @Column(name="DYNAMICS_INFORMATION", length=1020)
    public String getDynamicsInformation() {
        return this.dynamicsInformation;
    }

    /** 动态信息 */
    public void setDynamicsInformation(String dynamicsInformation) {
        this.dynamicsInformation = dynamicsInformation;
    }

    /** 下发人的单位ID */
    @Column(name="ISSUED_POLICE_UNIT_ID", nullable=false, length=1020)
    public Integer getIssuedPoliceUnitId() {
        return this.issuedPoliceUnitId;
    }

    /** 下发人的单位ID */
    public void setIssuedPoliceUnitId(Integer issuedPoliceUnitId) {
        this.issuedPoliceUnitId = issuedPoliceUnitId;
    }

    /** 指令接受人的单位ID */
    @Column(name="INSTRUCTION_RECIPIENT_UNIT_ID", nullable=false, length=1020)
    public Integer getInstructionRecipientUnitId() {
        return this.instructionRecipientUnitId;
    }

    /** 指令接受人的单位ID */
    public void setInstructionRecipientUnitId(Integer instructionRecipientUnitId) {
        this.instructionRecipientUnitId = instructionRecipientUnitId;
    }

    /** 关联预警ID */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MANAGEMENT_ASSOCIATION_SET")
    public ControlPersonAlarm getcontrolPersonAlarm() {
        return this.controlPersonAlarm;
    }

    /** 关联预警ID */
    public void setcontrolPersonAlarm(ControlPersonAlarm controlPersonAlarm) {
        this.controlPersonAlarm = controlPersonAlarm;
    }

    /** 指令和反馈以JSON进行追加 */
    @Column(name="INSTRUCTION_FEEDBACK_INFORMATI")
    public String getInstructionFeedbackInformati() {
        return this.instructionFeedbackInformati;
    }

    /** 指令和反馈以JSON进行追加 */
    public void setInstructionFeedbackInformati(String instructionFeedbackInformati) {
        this.instructionFeedbackInformati = instructionFeedbackInformati;
    }

    /** 操作日志 */
    @Column(name="OPERATION_LOG")
    public String getOperationLog() {
        return this.operationLog;
    }

    /** 操作日志 */
    public void setOperationLog(String operationLog) {
        this.operationLog = operationLog;
    }
    
    /** 重点人主键 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CONTROL_PERSON_PRIKEY", nullable=false)
	public ControlPerson getControlPerson() {
		return controlPerson;
	}

    /** 重点人主键 */
	public void setControlPerson(ControlPerson controlPerson) {
		this.controlPerson = controlPerson;
	}
}