package com.fire.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BsEstateManager {

    @Id
    @GeneratedValue
    private Long id;
    
    private String itemNumber;
    private String managerUnitName;
    private String chargePerson;
    private String supChargeUnitName;
    private String publishTime;
    private String employeesNum;
    private String managerAddress;
    private String contactName;
    private String industrySupervisionDepart;
    private String qualificationGrade;
    private String fireWitnessNum;
    private String midFireWitnessNum;
    private String contactPhone;

    private String remark;
    private String creator;
    private String createTime;

    public BsEstateManager(){
        
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

    public String getManagerUnitName() {
        return managerUnitName;
    }

    public void setManagerUnitName(String managerUnitName) {
        this.managerUnitName = managerUnitName;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getSupChargeUnitName() {
        return supChargeUnitName;
    }

    public void setSupChargeUnitName(String supChargeUnitName) {
        this.supChargeUnitName = supChargeUnitName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getEmployeesNum() {
        return employeesNum;
    }

    public void setEmployeesNum(String employeesNum) {
        this.employeesNum = employeesNum;
    }

    public String getManagerAddress() {
        return managerAddress;
    }

    public void setManagerAddress(String managerAddress) {
        this.managerAddress = managerAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getIndustrySupervisionDepart() {
        return industrySupervisionDepart;
    }

    public void setIndustrySupervisionDepart(String industrySupervisionDepart) {
        this.industrySupervisionDepart = industrySupervisionDepart;
    }

    public String getQualificationGrade() {
        return qualificationGrade;
    }

    public void setQualificationGrade(String qualificationGrade) {
        this.qualificationGrade = qualificationGrade;
    }

    public String getFireWitnessNum() {
        return fireWitnessNum;
    }

    public void setFireWitnessNum(String fireWitnessNum) {
        this.fireWitnessNum = fireWitnessNum;
    }

    public String getMidFireWitnessNum() {
        return midFireWitnessNum;
    }

    public void setMidFireWitnessNum(String midFireWitnessNum) {
        this.midFireWitnessNum = midFireWitnessNum;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
