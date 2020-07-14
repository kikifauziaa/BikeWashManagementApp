package com.rizky.fauziah.aplikasicustomer.models.request;

import com.google.gson.annotations.SerializedName;

public class AddBike {

    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("type_of_bike")
    private String typeOfBike;
    @SerializedName("size_of_bike")
    private String sizeOfBike;
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("amount_of_wash")
    private int amountOfWash = 0;
    @SerializedName("note")
    private String note = "";

    public AddBike() {
    }

    public AddBike(String licensePlate, String typeOfBike, String sizeOfBike, String customerName) {
        this.licensePlate = licensePlate;
        this.typeOfBike = typeOfBike;
        this.sizeOfBike = sizeOfBike;
        this.customerName = customerName;
    }

    public int getAmountOfWash() {
        return amountOfWash;
    }

    public void setAmountOfWash(int amountOfWash) {
        this.amountOfWash = amountOfWash;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
