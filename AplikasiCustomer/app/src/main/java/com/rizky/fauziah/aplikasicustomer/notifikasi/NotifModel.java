package com.rizky.fauziah.aplikasicustomer.notifikasi;

public class NotifModel {
    //id":6,"discount_code":"jun4",
    // "discount_desc":"Diskon 4% khusus dibulan juni",
    // "status":"active",
    // "discount_value":"4",
    // "min_wash_value":"3",
    // "start_at":"2020-06-01",
    // "end_at":"2020-06-30"
    // ,"created_at":"2020-06-20T09:09:02.000000Z",
    // "updated_at":"2020-06-26T12:56:24.000000Z"

    int id;
    String discount_code,
            discount_desc,
            status,
            discount_value,
            min_wash_value,
            start_at,
            end_at,
            created_at,
            updated_at;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getDiscount_code ( ) {
        return discount_code;
    }

    public void setDiscount_code (String discount_code) {
        this.discount_code = discount_code;
    }

    public String getDiscount_desc ( ) {
        return discount_desc;
    }

    public void setDiscount_desc (String discount_desc) {
        this.discount_desc = discount_desc;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getDiscount_value ( ) {
        return discount_value;
    }

    public void setDiscount_value (String discount_value) {
        this.discount_value = discount_value;
    }

    public String getMin_wash_value ( ) {
        return min_wash_value;
    }

    public void setMin_wash_value (String min_wash_value) {
        this.min_wash_value = min_wash_value;
    }

    public String getStart_at ( ) {
        return start_at;
    }

    public void setStart_at (String start_at) {
        this.start_at = start_at;
    }

    public String getEnd_at ( ) {
        return end_at;
    }

    public void setEnd_at (String end_at) {
        this.end_at = end_at;
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
