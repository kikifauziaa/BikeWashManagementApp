package com.rizky.fauziah.aplikasicustomer.models;

import com.google.gson.annotations.SerializedName;

public class CarpetQueue {

    @SerializedName("id")
    private int id;
    @SerializedName("status")
    private String status;
    @SerializedName("estimation_time")
    private String estimationTime;
    @SerializedName("customer")
    private String customer;
    @SerializedName("color_of_carpet")
    private String colorOfCarpet;
    @SerializedName("type_of_carpet")
    private String typeOfCarpet;
    @SerializedName("wash_type")
    private String washType;

    public CarpetQueue() {
    }

    public CarpetQueue(int id, String status, String estimationTime, String customer, String colorOfCarpet, String typeOfCarpet, String washType) {
        this.id = id;
        this.status = status;
        this.estimationTime = estimationTime;
        this.customer = customer;
        this.colorOfCarpet = colorOfCarpet;
        this.typeOfCarpet = typeOfCarpet;
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

    public String getColorOfCarpet() {
        return colorOfCarpet;
    }

    public void setColorOfCarpet(String colorOfCarpet) {
        this.colorOfCarpet = colorOfCarpet;
    }

    public String getTypeOfCarpet() {
        return typeOfCarpet;
    }

    public void setTypeOfCarpet(String typeOfCarpet) {
        this.typeOfCarpet = typeOfCarpet;
    }

    public String getWashType() {
        return washType;
    }

    public void setWashType(String washType) {
        this.washType = washType;
    }
}
