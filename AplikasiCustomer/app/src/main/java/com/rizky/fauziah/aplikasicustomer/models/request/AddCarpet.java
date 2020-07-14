package com.rizky.fauziah.aplikasicustomer.models.request;

import com.google.gson.annotations.SerializedName;

public class AddCarpet {
    @SerializedName("color_of_carpet")
    private String colorOfCarpet;
    @SerializedName("type_of_carpet")
    private String typeOfCarpet;
    @SerializedName("length_of_carpets")
    private String lengthOfCarpet;
    @SerializedName("width_of_carpets")
    private String widthOfCarpet;
    @SerializedName("amount_of_wash")
    private int amountOfWash = 0;
    @SerializedName("note")
    private String note = "";
    @SerializedName("customer_name")
    private String customerName;

    public AddCarpet() {
    }

    public AddCarpet(String colorOfCarpet, String typeOfCarpet, String lengthOfCarpet, String widthOfCarpet, String customerName) {
        this.colorOfCarpet = colorOfCarpet;
        this.typeOfCarpet = typeOfCarpet;
        this.lengthOfCarpet = lengthOfCarpet;
        this.widthOfCarpet = widthOfCarpet;
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

    public String getLengthOfCarpet() {
        return lengthOfCarpet;
    }

    public void setLengthOfCarpet(String lengthOfCarpet) {
        this.lengthOfCarpet = lengthOfCarpet;
    }

    public String getWidthOfCarpet() {
        return widthOfCarpet;
    }

    public void setWidthOfCarpet(String widthOfCarpet) {
        this.widthOfCarpet = widthOfCarpet;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
