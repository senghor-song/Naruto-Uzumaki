package com.ruiec.web.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 重点人员实体
 * 
 * @author Senghor<br>
 * @date 2017年12月1日 上午11:00:22
 */
@Entity
@Table(name = "T_COR_CONTROL_PERSON")
@DynamicUpdate(true)
public class ControlPerson extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /** 姓名 */
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    /** 性别 */
    @NotBlank
    @Size(min = 1, max = 1)
    private String sex;
    /** 身份证号 */
    @NotBlank
    @Size(min = 15, max = 18)
    private String idCard;
    /** 电话号码 */
    @Size(min = 8, max = 11)
    private String phone;
    /** 是否在控 */
    @NotNull
    private Integer isControl;
    /** 危险级别 */
    @NotNull
    private Integer dangerousLevel;
    /** 列管状态0撤管1列管 */
    private String columnTubeState;
    /** 列管时间 */
    private Date columnDate;
    /** 登记状态 */
    private String registerState;
    /** 人员级别 */
    private Integer personnelLevel;
    /** 列控级别 */
    @NotNull
    private Integer columnTubeLevel;
    /** 人员类型 */
    @NotNull
    private Integer personnelType;
    /** 责任民警主键 */
    private Integer userId;
    /** 重点人员列管是否审核1是未审核2是审核3是拒绝 */
    private String isAuditKeyPersonColumnTub;
    /** 列控方式 */
    private String columnTubeMode;
    /** 关联单位id */
    private Integer unitId;
    /** 重点人扩展表 */
    private ControlPersonExtend controlPersonExtend;
    /** 重点人类别集合 */
    private List<ControlPersonType> controlPersonTypes;
    /** 是否删除 */
    private Integer isDelete = 0;

    /** 姓名 */
    @Column(name = "NAME", nullable = false)
    public String getName() {
        return this.name;
    }

    /** 姓名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 性别 */
    @Column(name = "SEX")
    public String getSex() {
        return this.sex;
    }

    /** 性别 */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /** 身份证号 */
    @Column(name = "ID_CARD", nullable = false)
    public String getIdCard() {
        return this.idCard;
    }

    /** 身份证号 */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /** 是否删除 */
    @Column(name = "IS_DELETE", nullable = false, precision = 11, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    /** 是否删除 */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /** 电话号码 */
    @Column(name = "PHONE")
    public String getPhone() {
        return this.phone;
    }

    /** 电话号码 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 是否在控 */
    @Column(name = "IS_CONTROL")
    public Integer getIsControl() {
        return this.isControl;
    }

    /** 是否在控 */
    public void setIsControl(Integer isControl) {
        this.isControl = isControl;
    }

    /** 危险级别 */
    @Column(name = "DANGEROUS_LEVEL", precision = 11, scale = 0)
    public Integer getDangerousLevel() {
        return this.dangerousLevel;
    }

    /** 危险级别 */
    public void setDangerousLevel(Integer dangerousLevel) {
        this.dangerousLevel = dangerousLevel;
    }

    /** 列管状态0撤管1列管 */
    @Column(name = "COLUMN_TUBE_STATE")
    public String getColumnTubeState() {
        return this.columnTubeState;
    }

    /** 列管状态0撤管1列管 */
    public void setColumnTubeState(String columnTubeState) {
        this.columnTubeState = columnTubeState;
    }

    /** 列管时间 */
    @Column(name = "COLUMN_DATE")
    public Date getColumnDate() {
        return columnDate;
    }

    /** 列管时间 */
    public void setColumnDate(Date columnDate) {
        this.columnDate = columnDate;
    }

    /** 登记状态 */
    @Column(name = "REGISTER_STATE")
    public String getRegisterState() {
        return this.registerState;
    }

    /** 登记状态 */
    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }

    /** 人员级别 */
    @Column(name = "PERSONNEL_LEVEL", precision = 11, scale = 0)
    public Integer getPersonnelLevel() {
        return this.personnelLevel;
    }

    /** 人员级别 */
    public void setPersonnelLevel(Integer personnelLevel) {
        this.personnelLevel = personnelLevel;
    }

    /** 列控级别 */
    @Column(name = "COLUMN_TUBE_LEVEL", precision = 11, scale = 0)
    public Integer getColumnTubeLevel() {
        return this.columnTubeLevel;
    }

    /** 列控级别 */
    public void setColumnTubeLevel(Integer columnTubeLevel) {
        this.columnTubeLevel = columnTubeLevel;
    }

    /** 人员类型 */
    @Column(name = "PERSONNEL_TYPE", precision = 11, scale = 0)
    public Integer getPersonnelType() {
        return this.personnelType;
    }

    /** 人员类型 */
    public void setPersonnelType(Integer personnelType) {
        this.personnelType = personnelType;
    }

    /** 责任民警主键 */
    @Column(name = "RESPONSIBILITY_POLICE_ID", precision = 11, scale = 0)
    public Integer getUserId() {
        return this.userId;
    }

    /** 责任民警主键 */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 重点人员列管是否审核1是未审核2是审核3是拒绝 */
    @Column(name = "IS_AUDIT_KEY_PERSON_COLUMN_TUB", length = 20)
    public String getIsAuditKeyPersonColumnTub() {
        return this.isAuditKeyPersonColumnTub;
    }

    /** 重点人员列管是否审核1是未审核2是审核3是拒绝 */
    public void setIsAuditKeyPersonColumnTub(String isAuditKeyPersonColumnTub) {
        this.isAuditKeyPersonColumnTub = isAuditKeyPersonColumnTub;
    }

    /** 列控方式 */
    @Column(name = "COLUMN_TUBE_MODE")
    public String getColumnTubeMode() {
        return this.columnTubeMode;
    }

    /** 列控方式 */
    public void setColumnTubeMode(String columnTubeMode) {
        this.columnTubeMode = columnTubeMode;
    }

    /** 关联单位id */
    @Column(name = "UNIT_ID", precision = 11, scale = 0, nullable = false)
    public Integer getUnitId() {
        return this.unitId;
    }

    /** 关联单位id */
    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    /** 重点人扩展表 */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    // 配置共享主键，否则会额外生成外键关联列
    public ControlPersonExtend getControlPersonExtend() {
        return controlPersonExtend;
    }

    /** 重点人扩展表 */
    public void setControlPersonExtend(ControlPersonExtend controlPersonExtend) {
        this.controlPersonExtend = controlPersonExtend;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "controlPerson")
    public List<ControlPersonType> getControlPersonTypes() {
        return controlPersonTypes;
    }

    public void setControlPersonTypes(List<ControlPersonType> controlPersonTypes) {
        this.controlPersonTypes = controlPersonTypes;
    }

    /**
     * 是否完善信息的判断
     * 
     * @author Senghor<br>
     * @date 2017年12月26日 上午10:13:21
     */
    public boolean emiptyControlPerson() {
        if (this.getName() == null) {
            return false;
        }// 姓名
        if (this.getIdCard() == null) {
            return false;
        }// 身份证号
        if (this.getPhone() == null) {
            return false;
        }// 手机号码
        if (this.getIsControl() == null) {
            return false;
        }// 是否在控
        if (this.getColumnTubeLevel() == null) {
            return false;
        }// 列控级别
        if (this.getDangerousLevel() == null) {
            return false;
        }// 危险级别
        /*
         * if(this.getDataitemId()==null){return false;}//人员类别
         */if (this.controlPersonExtend.getHabitualResidence() == null) {
            return false;
        }// 经常居住地
        if (this.controlPersonExtend.getHouseholdRegisterPlace() == null) {
            return false;
        }// 户籍地址
        if (this.controlPersonExtend.getPhoto() == null) {
            return false;
        }// 照片
        if (this.controlPersonExtend.getReason() == null) {
            return false;
        }// 事由
        return true;
    }

    /**
     * 必填的信息判断
     * 
     * @author Senghor<br>
     * @date 2017年12月26日 上午10:13:54
     */
    public boolean emiptyRequiredControlPerson() {
        if (this.getName() == null) {
            return false;
        }// 姓名
        if (this.getIdCard() == null) {
            return false;
        }// 身份证号
        if (this.getSex() == null) {
            return false;
        }// 性别
        if (this.getPersonnelType() == null) {
            return false;
        }// 人员类型
        if (this.getColumnTubeMode() == null) {
            return false;
        }// 列控方式
        if (this.getIsControl() == null) {
            return false;
        }// 是否在控（在控状态）
        if (this.getColumnTubeLevel() == null) {
            return false;
        }// 列控级别
        if (this.getDangerousLevel() == null) {
            return false;
        }// 危险级别
        if (this.getUnitId() == null) {
            return false;
        }// 单位id
        if (this.controlPersonExtend.getPhoto() == null) {
            return false;
        }// 照片
        if (this.controlPersonExtend.getNation() == null) {
            return false;
        }// 民族
        if (this.controlPersonExtend.getReason() == null) {
            return false;
        }// 列控理由
        return true;
    }
}