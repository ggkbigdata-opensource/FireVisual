package com.fire.app.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AppFireEvent {
    // Fields
    @Id
    @GeneratedValue
    private Long id;
    private Long streetId;
    private String streetName;
    private Long blockId;
    private String blockName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date occurTime;
    private String address;
    private String cityArea;
    private String caseNumber;
    private String buildingName;
    private String placeName;
    private String placeUseType;

    private String placePositionType;
    private String constructionType;
    private String fireType;
    private String description;
    private String sceneDesc;
    private String firePosition;
    private String fireObject;
    private String fireReasonType;
    private String fireReason;
    private Double loss;
    private Integer deadNum;
    private Integer hurtNum;
    private String hasProcedure;
    private String selfSave;
    private String enterpriseNature;
    private String punishCaseNumber;
    private String fireStation;
    private String handleDepart;
    private String dutyPart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modDate;
    private String remark;
    private String status;

    private String recycleBin;
    private String rescueEquipment;
    private String isArrive;
    private String isDispose;
    private String halfwayBack;
    private String rescueType;
    private String isArriveProspect;
    private String isPunish;

    private String quarter;
    private String month;
    private String day;
    private String time;
    private String timePeriod;
    private String handlingInformation;
    private String year;

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

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceUseType() {
        return placeUseType;
    }

    public void setPlaceUseType(String placeUseType) {
        this.placeUseType = placeUseType;
    }

    public String getPlacePositionType() {
        return placePositionType;
    }

    public void setPlacePositionType(String placePositionType) {
        this.placePositionType = placePositionType;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getFireType() {
        return fireType;
    }

    public void setFireType(String fireType) {
        this.fireType = fireType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSceneDesc() {
        return sceneDesc;
    }

    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc;
    }

    public String getFirePosition() {
        return firePosition;
    }

    public void setFirePosition(String firePosition) {
        this.firePosition = firePosition;
    }

    public String getFireObject() {
        return fireObject;
    }

    public void setFireObject(String fireObject) {
        this.fireObject = fireObject;
    }

    public String getFireReasonType() {
        return fireReasonType;
    }

    public void setFireReasonType(String fireReasonType) {
        this.fireReasonType = fireReasonType;
    }

    public String getFireReason() {
        return fireReason;
    }

    public void setFireReason(String fireReason) {
        this.fireReason = fireReason;
    }

    public Double getLoss() {
        return loss;
    }

    public void setLoss(Double loss) {
        this.loss = loss;
    }

    public Integer getDeadNum() {
        return deadNum;
    }

    public void setDeadNum(Integer deadNum) {
        this.deadNum = deadNum;
    }

    public Integer getHurtNum() {
        return hurtNum;
    }

    public void setHurtNum(Integer hurtNum) {
        this.hurtNum = hurtNum;
    }

    public String getHasProcedure() {
        return hasProcedure;
    }

    public void setHasProcedure(String hasProcedure) {
        this.hasProcedure = hasProcedure;
    }

    public String getSelfSave() {
        return selfSave;
    }

    public void setSelfSave(String selfSave) {
        this.selfSave = selfSave;
    }

    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public String getPunishCaseNumber() {
        return punishCaseNumber;
    }

    public void setPunishCaseNumber(String punishCaseNumber) {
        this.punishCaseNumber = punishCaseNumber;
    }

    public String getFireStation() {
        return fireStation;
    }

    public void setFireStation(String fireStation) {
        this.fireStation = fireStation;
    }

    public String getHandleDepart() {
        return handleDepart;
    }

    public void setHandleDepart(String handleDepart) {
        this.handleDepart = handleDepart;
    }

    public String getDutyPart() {
        return dutyPart;
    }

    public void setDutyPart(String dutyPart) {
        this.dutyPart = dutyPart;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecycleBin() {
        return recycleBin;
    }

    public void setRecycleBin(String recycleBin) {
        this.recycleBin = recycleBin;
    }

    public String getRescueEquipment() {
        return rescueEquipment;
    }

    public void setRescueEquipment(String rescueEquipment) {
        this.rescueEquipment = rescueEquipment;
    }

    public String getIsArrive() {
        return isArrive;
    }

    public void setIsArrive(String isArrive) {
        this.isArrive = isArrive;
    }

    public String getIsDispose() {
        return isDispose;
    }

    public void setIsDispose(String isDispose) {
        this.isDispose = isDispose;
    }

    public String getHalfwayBack() {
        return halfwayBack;
    }

    public void setHalfwayBack(String halfwayBack) {
        this.halfwayBack = halfwayBack;
    }

    public String getRescueType() {
        return rescueType;
    }

    public void setRescueType(String rescueType) {
        this.rescueType = rescueType;
    }

    public String getIsArriveProspect() {
        return isArriveProspect;
    }

    public void setIsArriveProspect(String isArriveProspect) {
        this.isArriveProspect = isArriveProspect;
    }

    public String getIsPunish() {
        return isPunish;
    }

    public void setIsPunish(String isPunish) {
        this.isPunish = isPunish;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getHandlingInformation() {
        return handlingInformation;
    }

    public void setHandlingInformation(String handlingInformation) {
        this.handlingInformation = handlingInformation;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
