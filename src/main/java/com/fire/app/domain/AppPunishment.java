package com.fire.app.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AppPunishment {
    // Fields
    @Id
    @GeneratedValue
    private Long id;
    private Long streetId;
    private String streetName;
    private Long blockId;
    private String blockName;
    private String buildingName;
    private String punishmentUnitName;
    private String punishmentUnitAddr;
    private String punishmentDocNumber;
    private String punishmentReason;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date executeTime;
    private String punishmentEvidence;
    private String punishmentBasis;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date punishmentPublishTime;
    private String punishMethod;
    private String dutyPersonName;
    private String dutyPersonId;
    private String fineAmount;
    private String sealUpDocNumber;
    private String sealUpPosition;
    private String sealUpBasis;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sealUpTimeBegin;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sealUpTimeEnd;
    private String unsealDocNumber;
    private String unsealBasis;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date unsealTime;
    private String tempEntranceDocNumber;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tempEntranceTimeBegin;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tempEntranceTimeEnd;
    private String tempEntranceBasis;
    private String punishmentDescription;
    private String executorName;//执法人名称
    private String reconsiderationUnit;//复议单位i额
    private String lawsuitAcceptUnit;//提起诉讼单位
    private String punishUnitName;//主办单位
    private String urgeNote;
    private String forceExecute;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getPunishmentUnitName() {
        return punishmentUnitName;
    }

    public void setPunishmentUnitName(String punishmentUnitName) {
        this.punishmentUnitName = punishmentUnitName;
    }

    public String getPunishmentUnitAddr() {
        return punishmentUnitAddr;
    }

    public void setPunishmentUnitAddr(String punishmentUnitAddr) {
        this.punishmentUnitAddr = punishmentUnitAddr;
    }

    public String getPunishmentDocNumber() {
        return punishmentDocNumber;
    }

    public void setPunishmentDocNumber(String punishmentDocNumber) {
        this.punishmentDocNumber = punishmentDocNumber;
    }

    public String getPunishmentReason() {
        return punishmentReason;
    }

    public void setPunishmentReason(String punishmentReason) {
        this.punishmentReason = punishmentReason;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getPunishmentEvidence() {
        return punishmentEvidence;
    }

    public void setPunishmentEvidence(String punishmentEvidence) {
        this.punishmentEvidence = punishmentEvidence;
    }

    public String getPunishmentBasis() {
        return punishmentBasis;
    }

    public void setPunishmentBasis(String punishmentBasis) {
        this.punishmentBasis = punishmentBasis;
    }

 
    public String getPunishMethod() {
        return punishMethod;
    }

    public void setPunishMethod(String punishMethod) {
        this.punishMethod = punishMethod;
    }

    public String getDutyPersonName() {
        return dutyPersonName;
    }

    public void setDutyPersonName(String dutyPersonName) {
        this.dutyPersonName = dutyPersonName;
    }

    public String getDutyPersonId() {
        return dutyPersonId;
    }

    public void setDutyPersonId(String dutyPersonId) {
        this.dutyPersonId = dutyPersonId;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getSealUpDocNumber() {
        return sealUpDocNumber;
    }

    public void setSealUpDocNumber(String sealUpDocNumber) {
        this.sealUpDocNumber = sealUpDocNumber;
    }

    public String getSealUpPosition() {
        return sealUpPosition;
    }

    public void setSealUpPosition(String sealUpPosition) {
        this.sealUpPosition = sealUpPosition;
    }

    public String getSealUpBasis() {
        return sealUpBasis;
    }

    public void setSealUpBasis(String sealUpBasis) {
        this.sealUpBasis = sealUpBasis;
    }

    public Date getSealUpTimeBegin() {
        return sealUpTimeBegin;
    }

    public void setSealUpTimeBegin(Date sealUpTimeBegin) {
        this.sealUpTimeBegin = sealUpTimeBegin;
    }

    public String getUnsealDocNumber() {
        return unsealDocNumber;
    }

    public void setUnsealDocNumber(String unsealDocNumber) {
        this.unsealDocNumber = unsealDocNumber;
    }

    public String getUnsealBasis() {
        return unsealBasis;
    }

    public void setUnsealBasis(String unsealBasis) {
        this.unsealBasis = unsealBasis;
    }

    public String getTempEntranceDocNumber() {
        return tempEntranceDocNumber;
    }

    public void setTempEntranceDocNumber(String tempEntranceDocNumber) {
        this.tempEntranceDocNumber = tempEntranceDocNumber;
    }

    public String getTempEntranceBasis() {
        return tempEntranceBasis;
    }

    public void setTempEntranceBasis(String tempEntranceBasis) {
        this.tempEntranceBasis = tempEntranceBasis;
    }

    public String getPunishmentDescription() {
        return punishmentDescription;
    }

    public void setPunishmentDescription(String punishmentDescription) {
        this.punishmentDescription = punishmentDescription;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getReconsiderationUnit() {
        return reconsiderationUnit;
    }

    public void setReconsiderationUnit(String reconsiderationUnit) {
        this.reconsiderationUnit = reconsiderationUnit;
    }

    public String getLawsuitAcceptUnit() {
        return lawsuitAcceptUnit;
    }

    public void setLawsuitAcceptUnit(String lawsuitAcceptUnit) {
        this.lawsuitAcceptUnit = lawsuitAcceptUnit;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Date getPunishmentPublishTime() {
        return punishmentPublishTime;
    }

    public void setPunishmentPublishTime(Date punishmentPublishTime) {
        this.punishmentPublishTime = punishmentPublishTime;
    }

    public Date getSealUpTimeEnd() {
        return sealUpTimeEnd;
    }

    public void setSealUpTimeEnd(Date sealUpTimeEnd) {
        this.sealUpTimeEnd = sealUpTimeEnd;
    }

    public Date getUnsealTime() {
        return unsealTime;
    }

    public void setUnsealTime(Date unsealTime) {
        this.unsealTime = unsealTime;
    }

    public Date getTempEntranceTimeBegin() {
        return tempEntranceTimeBegin;
    }

    public void setTempEntranceTimeBegin(Date tempEntranceTimeBegin) {
        this.tempEntranceTimeBegin = tempEntranceTimeBegin;
    }

    public Date getTempEntranceTimeEnd() {
        return tempEntranceTimeEnd;
    }

    public void setTempEntranceTimeEnd(Date tempEntranceTimeEnd) {
        this.tempEntranceTimeEnd = tempEntranceTimeEnd;
    }

    public String getPunishUnitName() {
        return punishUnitName;
    }

    public void setPunishUnitName(String punishUnitName) {
        this.punishUnitName = punishUnitName;
    }

    public String getUrgeNote() {
        return urgeNote;
    }

    public void setUrgeNote(String urgeNote) {
        this.urgeNote = urgeNote;
    }

    public String getForceExecute() {
        return forceExecute;
    }

    public void setForceExecute(String forceExecute) {
        this.forceExecute = forceExecute;
    }
    
}
