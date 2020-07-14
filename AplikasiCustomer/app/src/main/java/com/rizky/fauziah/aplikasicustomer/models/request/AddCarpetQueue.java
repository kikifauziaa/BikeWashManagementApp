package com.rizky.fauziah.aplikasicustomer.models.request;

import com.google.gson.annotations.SerializedName;

public class AddCarpetQueue {

    @SerializedName("color_of_carpet")
    private String colorOfCarpet;
    @SerializedName("type_of_carpet")
    private String typeOfCarpet;
    @SerializedName("wash_type")
    private String washType;
    @SerializedName("customer_name")
    private String customerName;

    public AddCarpetQueue() {
    }

    public AddCarpetQueue(String colorOfCarpet, String typeOfCarpet, String washType, String customerName) {
        this.colorOfCarpet = colorOfCarpet;
        this.typeOfCarpet = typeOfCarpet;
        this.washType = washType;
        this.customerName = customerName;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
