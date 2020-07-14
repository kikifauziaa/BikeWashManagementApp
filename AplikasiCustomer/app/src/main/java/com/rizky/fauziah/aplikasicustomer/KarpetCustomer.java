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

import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.activity.AddBikeActivity;
import com.rizky.fauziah.aplikasicustomer.activity.AddCarpetActivity;
import com.rizky.fauziah.aplikasicustomer.adapters.KarpetCustAdapter;
import com.rizky.fauziah.aplikasicustomer.adapters.MotorCustAdapter;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBike;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpet;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

public class KarpetCustomer extends AppCompatActivity {

    private static final String TAG = KarpetCustomer.class.getSimpleName();
    //private BottomNavigationView bottom;
    private RecyclerView historyRecyler;
    private ProgressBar progressBar;
    private Customer customer;
    private Button tambahdata;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_karpet_customer);

        progressBar = findViewById(R.id.loading_bar);

        historyRecyler = (RecyclerView) findViewById(R.id.list_karpetcustomer);
        historyRecyler.setLayoutManager(new LinearLayoutManager (this));
        historyRecyler.setItemAnimator(new DefaultItemAnimator ());

        SessionManager s = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        customer = gson.fromJson(s.getString(SessionManager.Key.USER_DATA), Customer.class);

        tambahdata = findViewById (R.id.button_addcarpet);
        tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext (), AddCarpetActivity.class);
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

        Call<List<AddCarpet>> call = apiClient.getCarpetCust(customer.getId());
        call.enqueue(new Callback<List<AddCarpet>> () {
            @Override
            public void onResponse(Call<List<AddCarpet>> call, Response<List<AddCarpet>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<AddCarpet> bikeHistories = response.body();
                historyRecyler.setAdapter(new KarpetCustAdapter (bikeHistories, KarpetCustomer.this));
            }

            @Override
            public void onFailure(Call<List<AddCarpet>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(TAG, t.getMessage());
                Toast.makeText(KarpetCustomer.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}