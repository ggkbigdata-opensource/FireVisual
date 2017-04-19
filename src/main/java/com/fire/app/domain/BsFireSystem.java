package com.fire.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BsFireSystem {

    @Id
    @GeneratedValue
    private Long id;
    private String itemNumber;
    private String module;
    private String systemConstituentName;
    private String modelSize;
    private String amount;
    private String manufacturer;

    private String position;
    private String useTime;
    private String useSituation;
    private String remark;
    private String firePoolVolume;
    private String fireTankVolume;
    private String firePoolNum;
    private String fireTankNum;

    private String declared;
    private String creator;
    private String createTime;

    public BsFireSystem(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSystemConstituentName() {
        return systemConstituentName;
    }

    public void setSystemConstituentName(String systemConstituentName) {
        this.systemConstituentName = systemConstituentName;
    }

    public String getModelSize() {
        return modelSize;
    }

    public void setModelSize(String modelSize) {
        this.modelSize = modelSize;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getUseSituation() {
        return useSituation;
    }

    public void setUseSituation(String useSituation) {
        this.useSituation = useSituation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFirePoolVolume() {
        return firePoolVolume;
    }

    public void setFirePoolVolume(String firePoolVolume) {
        this.firePoolVolume = firePoolVolume;
    }

    public String getFireTankVolume() {
        return fireTankVolume;
    }

    public void setFireTankVolume(String fireTankVolume) {
        this.fireTankVolume = fireTankVolume;
    }

    public String getFirePoolNum() {
        return firePoolNum;
    }

    public void setFirePoolNum(String firePoolNum) {
        this.firePoolNum = firePoolNum;
    }

    public String getFireTankNum() {
        return fireTankNum;
    }

    public void setFireTankNum(String fireTankNum) {
        this.fireTankNum = fireTankNum;
    }

    public String getDeclared() {
        return declared;
    }

    public void setDeclared(String declared) {
        this.declared = declared;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    
}
