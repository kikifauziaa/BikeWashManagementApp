package com.rizky.fauziah.aplikasicustomer.models;

import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("email")
    private String email;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("deposited_money")
    private int depositedMoney;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public Customer() {
    }

    public Customer(int id, String username, String password, String name, String address, String email, String phoneNumber, int depositedMoney, String createdAt, String updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.depositedMoney = depositedMoney;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDepositedMoney() {
        return depositedMoney;
    }

    public void setDepositedMoney(int depositedMoney) {
        this.depositedMoney = depositedMoney;
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
