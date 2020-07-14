package com.rizky.fauziah.aplikasicustomer.models;

import com.google.gson.annotations.SerializedName;

public class BikeQueue {

    @SerializedName("id")
    private int id;
    @SerializedName("status")
    private String status;
    @SerializedName("estimation_time")
    private String estimationTime;
    @SerializedName("customer")
    private String customer;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("type_of_bike")
    private String typeOfBike;
    @SerializedName("size_of_bike")
    private String sizeOfBike;
    @SerializedName("wash_type")
    private String washType;

    public BikeQueue() {
    }

    public BikeQueue(int id, String status, String estimationTime, String customer, String licensePlate, String typeOfBike, String sizeOfBike, String washType) {
        this.id = id;
        this.status = status;
        this.estimationTime = estimationTime;
        this.customer = customer;
        this.licensePlate = licensePlate;
        this.typeOfBike = typeOfBike;
        this.sizeOfBike = sizeOfBike;
        this.washType = washType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimationTime() {
        return estimationTime;
    }

    public void setEstimationTime(String estimationTime) {
        this.estimationTime = estimationTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getTypeOfBike() {
        return typeOfBike;
    }

    public void setTypeOfBike(String typeOfBike) {
        this.typeOfBike = typeOfBike;
    }

    public String getSizeOfBike() {
        return sizeOfBike;
    }

    public void setSizeOfBike(String sizeOfBike) {
        this.sizeOfBike = sizeOfBike;
    }

    public String getWashType() {
        return washType;
    }

    public void setWashType(String washType) {
        this.washType = washType;
    }
}
