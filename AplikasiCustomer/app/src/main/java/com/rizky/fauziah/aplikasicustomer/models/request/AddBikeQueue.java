package com.rizky.fauziah.aplikasicustomer.models.request;

import com.google.gson.annotations.SerializedName;

public class AddBikeQueue {
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("wash_type")
    private String washType;

    public AddBikeQueue() {
    }

    public AddBikeQueue(String licensePlate, String washType) {
        this.licensePlate = licensePlate;
        this.washType = washType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getWashType() {
        return washType;
    }

    public void setWashType(String washType) {
        this.washType = washType;
    }
}
