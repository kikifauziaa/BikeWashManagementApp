package com.rizky.fauziah.aplikasicustomer.models;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class CarpetHistory {

    @SerializedName("id")
    private int id;
    @SerializedName("color_of_carpet")
    private String colorOfCarper;
    @SerializedName("type_of_carpet")
    private String typeOfCarper;
    @SerializedName("customer")
    private String customer;
    @SerializedName("wash_type")
    private String washType;
    @SerializedName("worker1")
    private String worker1;
    @SerializedName("worker2")
    private String worker2;
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
    private String typeOfPayment;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("rating")
    @Nullable
    private int rating;

    public CarpetHistory(int id, String colorOfCarper, String typeOfCarper, String customer, String washType, String worker1, String worker2, String admin, int totalPay, int totalDisc, String payStatus, int paidAmount, int changes, String typeOfPayment, String createdAt, int rating) {
        this.id = id;
        this.colorOfCarper = colorOfCarper;
        this.typeOfCarper = typeOfCarper;
        this.customer = customer;
        this.washType = washType;
        this.worker1 = worker1;
        this.worker2 = worker2;
        this.admin = admin;
        this.totalPay = totalPay;
        this.totalDisc = totalDisc;
        this.payStatus = payStatus;
        this.paidAmount = paidAmount;
        this.changes = changes;
        this.typeOfPayment = typeOfPayment;
        this.createdAt = createdAt;
        this.rating = rating;
    }

    public CarpetHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColorOfCarper() {
        return colorOfCarper;
    }

    public void setColorOfCarper(String colorOfCarper) {
        this.colorOfCarper = colorOfCarper;
    }

    public String getTypeOfCarper() {
        return typeOfCarper;
    }

    public void setTypeOfCarper(String typeOfCarper) {
        this.typeOfCarper = typeOfCarper;
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

    public String getWorker1() {
        return worker1;
    }

    public void setWorker1(String worker1) {
        this.worker1 = worker1;
    }

    public String getWorker2() {
        return worker2;
    }

    public void setWorker2(String worker2) {
        this.worker2 = worker2;
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

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
