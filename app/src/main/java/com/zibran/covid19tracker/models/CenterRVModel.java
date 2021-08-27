package com.zibran.covid19tracker.models;

public class CenterRVModel {
    public String centerName;
    public String centerAddress;
    public String centerFromTime;
    public String centerToTime;
    public String feeType;
    public int ageLimit;
    public String vaccineName;
    public int availabilityCapacity;

    public CenterRVModel(String centerName, String centerAddress, String centerFromTime, String centerToTime, String feeType, int ageLimit, String vaccineName, int availabilityCapacity) {
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.centerFromTime = centerFromTime;
        this.centerToTime = centerToTime;
        this.feeType = feeType;
        this.ageLimit = ageLimit;
        this.vaccineName = vaccineName;
        this.availabilityCapacity = availabilityCapacity;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public String getCenterFromTime() {
        return centerFromTime;
    }

    public String getCenterToTime() {
        return centerToTime;
    }

    public String getFeeType() {
        return feeType;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getAvailabilityCapacity() {
        return availabilityCapacity;
    }
}
