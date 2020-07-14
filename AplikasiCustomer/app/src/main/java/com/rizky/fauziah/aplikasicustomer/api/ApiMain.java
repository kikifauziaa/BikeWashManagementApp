package com.rizky.fauziah.aplikasicustomer.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMain {
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor ();
        httpLoggingInterceptor.setLevel (HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor (httpLoggingInterceptor).build ();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl ("http://www.cucimotorsalju.online/bismillahCuci/api/")
                .addConverterFactory (GsonConverterFactory.create ())
                .client (okHttpClient)
                .build ();

        return retrofit;
    }
}
