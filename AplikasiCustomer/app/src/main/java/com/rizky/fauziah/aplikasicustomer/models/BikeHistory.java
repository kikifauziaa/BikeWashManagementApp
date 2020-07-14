package com.rizky.fauziah.aplikasicustomer.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BikeHistory {

    @SerializedName("id")
    private int id;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("customer")
    private String customer;
    @SerializedName("wash_type")
    private String washType;
    @SerializedName("worker")
    private String worker;
    @SerializedName("admin")
    private String admin;
    @SerializedName("total_pay")
    private int totalPay;
    @SerializedName("total_disc")
    private int totalDisc;
    @SerializedName("pay_status")
    private String payStatus;
    @SerializedName("paid_amount")
    private int paidAmount;
    @SerializedName("changes")
    private int changes;
    @SerializedName("type_of_payment")
    private String typeOfAmount;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("rating")
    private int rating;

    public BikeHistory(int id, String licensePlate, String customer, String washType, String worker, String admin, int totalPay, int totalDisc, String payStatus, int paidAmount, int changes, String typeOfAmount, String createdAt, int rating) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.customer = customer;
        this.washType = washType;
        this.worker = worker;
        this.admin = admin;
        this.totalPay = totalPay;
        this.totalDisc = totalDisc;
        this.payStatus = payStatus;
        this.paidAmount = paidAmount;
        this.changes = changes;
        this.typeOfAmount = typeOfAmount;
        this.createdAt = createdAt;
        this.rating = rating;
    }

    public BikeHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getWashType() {
        return washType;
    }

    public void setWashType(String washType) {
        this.washType = washType;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    public int getTotalDisc() {
        return totalDisc;
    }

    public void setTotalDisc(int totalDisc) {
        this.totalDisc = totalDisc;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    public int getChanges() {
        return changes;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    public String getTypeOfAmount() {
        return typeOfAmount;
    }

    public void setTypeOfAmount(String typeOfAmount) {
        this.typeOfAmount = typeOfAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
