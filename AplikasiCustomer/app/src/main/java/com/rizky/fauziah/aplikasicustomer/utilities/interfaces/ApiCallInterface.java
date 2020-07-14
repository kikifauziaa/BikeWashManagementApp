package com.rizky.fauziah.aplikasicustomer.utilities.interfaces;

import com.rizky.fauziah.aplikasicustomer.models.BikeHistory;
import com.rizky.fauziah.aplikasicustomer.models.BikeQueue;
import com.rizky.fauziah.aplikasicustomer.models.CarpetHistory;
import com.rizky.fauziah.aplikasicustomer.models.CarpetQueue;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.models.Discount;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBike;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBikeQueue;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpet;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpetQueue;
import com.rizky.fauziah.aplikasicustomer.models.request.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCallInterface {


    @GET("bikes/findbycustomer/{id}")
    Call<List<AddBike>> getBikeCust(@Path("id") int customerId);

    @GET("carpets/findbycustomer/{id}")
    Call<List<AddCarpet>> getCarpetCust(@Path("id") int customerId);

    @GET("bikehistories/findbycustomer/{id}")
    Call<List<BikeHistory>> getBikeHistories(@Path("id") int customerId);

    @GET("carpethistories/findbycustomer/{id}")
    Call<List<CarpetHistory>> getCarpetHistories(@Path("id") int customerId);

    @FormUrlEncoded
    @PUT("carpethistories/updaterating/{id}")
    Call<String> updateCarpetRating(@Path("id") String id, @Field("rating") int rating);

    @FormUrlEncoded
    @PUT("bikehistories/updaterating/{id}")
    Call<String> updateMotorRating(@Path("id") String id, @Field("rating") int rating);

    @FormUrlEncoded
    @POST("customers/login")
    Call<Customer> login(@Field("username") String username, @Field("password") String password);

    @GET("bikequeues")
    Call<List<BikeQueue>> getBikeQueues();

    @GET("carpetqueues")
    Call<List<CarpetQueue>> getCarpetQueues();

    @GET("discounts")
    Call<List<Discount>> getDiscounts();

    @POST("customers/register")
    Call<String> register(@Body Register register);

    @POST("bikes/create")
    Call<String> addBike(@Body AddBike addBike);

    @POST("bikequeues/create")
    Call<String> addBikeQueue(@Body AddBikeQueue queue);

    @POST("carpets/create")
    Call<String> addCarpet(@Body AddCarpet addCarpet);

    @POST("carpetqueues/create")
    Call<String> addCarpetQueue(@Body AddCarpetQueue queue);

    @GET("carpets/findbycustomer/{id}")
    Call<List<AddCarpet>> getCustomerCarpets(@Path("id") String id);

    @GET("bikes/findbycustomer/{id}")
    Call<List<AddBike>> getCustomerBikes(@Path("id") String id);
}
