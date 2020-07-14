package com.rizky.fauziah.aplikasicustomer.antrian;

public class AntriModel {
    //[{"id":"1",
    // "status":"proses",
    // "estimation_time":"11:00:00",
    // "license_plate":"N123GO",
    // "type_of_bike":"matic",
    // "size_of_bike":"sedang",
    // "wash_type":"standar"}

    private int id;
    private String license_plate,status,estimation_time,type_of_bike,size_of_bike,wash_type;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getLicense_plate ( ) {
        return license_plate;
    }

    public void setLicense_plate (String license_plate) {
        this.license_plate = license_plate;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getEstimation_time ( ) {
        return estimation_time;
    }

    public void setEstimation_time (String estimation_time) {
        this.estimation_time = estimation_time;
    }

    public String getType_of_bike ( ) {
        return type_of_bike;
    }

    public void setType_of_bike (String type_of_bike) {
        this.type_of_bike = type_of_bike;
    }

    public String getSize_of_bike ( ) {
        return size_of_bike;
    }

    public void setSize_of_bike (String size_of_bike) {
        this.size_of_bike = size_of_bike;
    }

    public String getWash_type ( ) {
        return wash_type;
    }

    public void setWash_type (String wash_type) {
        this.wash_type = wash_type;
    }
}
