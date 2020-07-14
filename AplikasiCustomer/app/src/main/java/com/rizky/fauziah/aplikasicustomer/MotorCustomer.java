package com.rizky.fauziah.aplikasicustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.activity.AddBikeActivity;
import com.rizky.fauziah.aplikasicustomer.adapters.BikeHistoryAdapter;
import com.rizky.fauziah.aplikasicustomer.adapters.MotorCustAdapter;
import com.rizky.fauziah.aplikasicustomer.historymotor.HistoriMotor;
import com.rizky.fauziah.aplikasicustomer.models.BikeHistory;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBike;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

public class MotorCustomer extends AppCompatActivity {

    private static final String TAG = MotorCustomer.class.getSimpleName();
    //private BottomNavigationView bottom;
    private RecyclerView historyRecyler;
    private ProgressBar progressBar;
    private Customer customer;
    private Button tambahdata;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_motor_customer);

        progressBar = findViewById(R.id.loading_bar);

        historyRecyler = (RecyclerView) findViewById(R.id.list_motorcustomer);
        historyRecyler.setLayoutManager(new LinearLayoutManager (this));
        historyRecyler.setItemAnimator(new DefaultItemAnimator ());

        SessionManager s = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        customer = gson.fromJson(s.getString(SessionManager.Key.USER_DATA), Customer.class);

        tambahdata = findViewById (R.id.button_addbike);
        tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext (), AddBikeActivity.class);
                startActivity(i);
            }
        });

        prepareData ();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        prepareData();
    }

    private void prepareData() {
        progressBar.setVisibility(View.VISIBLE);
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);

        Call<List<AddBike>> call = apiClient.getBikeCust(customer.getId());
        call.enqueue(new Callback<List<AddBike>> () {
            @Override
            public void onResponse(Call<List<AddBike>> call, Response<List<AddBike>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<AddBike> bikeHistories = response.body();
                historyRecyler.setAdapter(new MotorCustAdapter (bikeHistories, MotorCustomer.this));
            }

            @Override
            public void onFailure(Call<List<AddBike>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(TAG, t.getMessage());
                Toast.makeText(MotorCustomer.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void tambahdata(){

    }
}