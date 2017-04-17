package com.fire.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String executeTime;
    private String punishmentEvidence;
    private String punishmentBasis;
    private String punishmentPublishTime;
    private String punishMethod;
    private String dutyPersonName;
    private String dutyPersonId;
    private String fineAmount;
    private String sealUpDocNumber;
    private String sealUpPosition;
    private String sealUpBasis;
    private String sealUpTimeBegin;
    private String sealUpTimeEnd;
    private String unsealDocNumber;
    private String unsealBasis;
    private String unsealTime;
    private String tempEntranceDocNumber;
    private String tempEntranceTimeBegin;
    private String tempEntranceTimeEnd;
    private String tempEntranceBasis;
    private String punishmentDescription;
    private String executorName;
    private String reconsiderationUnit;
    private String lawsuitAcceptUnit;

    public AppPunishment(){
        
    }

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

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
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

    public String getPunishmentPublishTime() {
        return punishmentPublishTime;
    }

    public void setPunishmentPublishTime(String punishmentPublishTime) {
        this.punishmentPublishTime = punishmentPublishTime;
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

    public String getSealUpTimeBegin() {
        return sealUpTimeBegin;
    }

    public void setSealUpTimeBegin(String sealUpTimeBegin) {
        this.sealUpTimeBegin = sealUpTimeBegin;
    }

    public String getSealUpTimeEnd() {
        return sealUpTimeEnd;
    }

    public void setSealUpTimeEnd(String sealUpTimeEnd) {
        this.sealUpTimeEnd = sealUpTimeEnd;
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

    public String getUnsealTime() {
        return unsealTime;
    }

    public void setUnsealTime(String unsealTime) {
        this.unsealTime = unsealTime;
    }

    public String getTempEntranceDocNumber() {
        return tempEntranceDocNumber;
    }

    public void setTempEntranceDocNumber(String tempEntranceDocNumber) {
        this.tempEntranceDocNumber = tempEntranceDocNumber;
    }

    public String getTempEntranceTimeBegin() {
        return tempEntranceTimeBegin;
    }

    public void setTempEntranceTimeBegin(String tempEntranceTimeBegin) {
        this.tempEntranceTimeBegin = tempEntranceTimeBegin;
    }

    public String getTempEntranceTimeEnd() {
        return tempEntranceTimeEnd;
    }

    public void setTempEntranceTimeEnd(String tempEntranceTimeEnd) {
        this.tempEntranceTimeEnd = tempEntranceTimeEnd;
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
    
    

}
