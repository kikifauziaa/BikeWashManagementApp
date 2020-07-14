package com.rizky.fauziah.aplikasicustomer.register;

public class RegisModel {
    //"id":1,"username":"bambang",
    // "password":"bambangpamungkas",
    // "name":"bambang pamungkas",
    // "address":"jl.ijen no.66",
    // "email":"pamungkas.bambang@gmail.com",
    // "phone_number":"085699358647",
    // "deposited_money":"10000",
    // "created_at":"2020-06-20T06:22:31.000000Z",
    // "updated_at":"2020-06-20T06:52:12.000000Z"

    int id;
    String username,
            password,
            name,
            address,
            email,
            phone_number,
            deposited_money,
            created_at,
            updated_at;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getUsername ( ) {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword ( ) {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getName ( ) {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getAddress ( ) {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPhone_number ( ) {
        return phone_number;
    }

    public void setPhone_number (String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDeposited_money ( ) {
        return deposited_money;
    }

    public void setDeposited_money (String deposited_money) {
        this.deposited_money = deposited_money;
    }

    public String getCreated_at ( ) {
        return created_at;
    }

    public void setCreated_at (String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at ( ) {
        return updated_at;
    }

    public void setUpdated_at (String updated_at) {
        this.updated_at = updated_at;
    }
}
