package com.rizky.fauziah.aplikasicustomer.models;

import com.google.gson.annotations.SerializedName;

public class Discount {
    @SerializedName("id")
    private int id;
    @SerializedName("discount_code")
    private String discountCode;
    @SerializedName("discount_desc")
    private String discountDescription;
    @SerializedName("status")
    private String status;
    @SerializedName("discount_value")
    private int discountValue;
    @SerializedName("min_wash_value")
    private int minWashValue;
    @SerializedName("start_at")
    private String startAt;
    @SerializedName("end_at")
    private String endAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public Discount() {
    }

    public Discount(int id, String discountCode, String discountDescription, String status, int discountValue, int minWashValue, String startAt, String endAt, String createdAt, String updatedAt) {
        this.id = id;
        this.discountCode = discountCode;
        this.discountDescription = discountDescription;
        this.status = status;
        this.discountValue = discountValue;
        this.minWashValue = minWashValue;
        this.startAt = startAt;
        this.endAt = endAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public int getMinWashValue() {
        return minWashValue;
    }

    public void setMinWashValue(int minWashValue) {
        this.minWashValue = minWashValue;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
