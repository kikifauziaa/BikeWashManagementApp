package com.rizky.fauziah.aplikasicustomer.historymotor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.adapters.BikeHistoryAdapter;
import com.rizky.fauziah.aplikasicustomer.historykarpet.HistoriKarpet;
import com.rizky.fauziah.aplikasicustomer.login.Login;
import com.rizky.fauziah.aplikasicustomer.models.BikeHistory;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.profil.Profil;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.antrian.Antrian;
import com.rizky.fauziah.aplikasicustomer.notifikasi.Notifikasi;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

public class HistoriMotor extends AppCompatActivity {
    //Note: get data, muncul dalam bentuk recyclerview vertical, layout sudah ada
    private static final String TAG = HistoriMotor.class.getSimpleName();
    private BottomNavigationView bottom;
    private RecyclerView historyRecyler;
    private ProgressBar progressBar;
    private Customer customer;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_histori_motor);

        progressBar = findViewById(R.id.loading_bar);

        historyRecyler = (RecyclerView) findViewById(R.id.id_list_history_motor);
        historyRecyler.setLayoutManager(new LinearLayoutManager(this));
        historyRecyler.setItemAnimator(new DefaultItemAnimator());

        SessionManager s = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        customer = gson.fromJson(s.getString(SessionManager.Key.USER_DATA), Customer.class);

        BottomNavigation ();
        prepareData();
    }

    private void BottomNavigation(){
        bottom = (BottomNavigationView) findViewById (R.id.bottomnavigation);

        bottom.setSelectedItemId (R.id.action_motor);

        bottom.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                if(item.getItemId () == R.id.action_motor){

                }else  if(item.getItemId () == R.id.action_karpet){
                    startActivity(new Intent (getApplicationContext (), HistoriKarpet.class));
                    finish ();
                    overridePendingTransition (0,0);
                }else if(item.getItemId () == R.id.action_profil){
                    startActivity(new Intent (getApplicationContext (), Profil.class));
                    finish ();
                    overridePendingTransition (0,0);
                }else {
                    startActivity(new Intent (getApplicationContext (), Antrian.class));
                    finish ();
                    overridePendingTransition (0,0);

                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atas, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //setMode(item.getItemId());
        //return super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_notif){
            Intent myIntent = new Intent(getBaseContext(),  Notifikasi.class);
            startActivity(myIntent);
        }else if(id==R.id.action_logout){
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.clearData();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish ();
        }
        return true;
        //return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        prepareData();
    }

    private void prepareData() {
        progressBar.setVisibility(View.VISIBLE);
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);

        Call<List<BikeHistory>> call = apiClient.getBikeHistories(customer.getId());
        call.enqueue(new Callback<List<BikeHistory>>() {
            @Override
            public void onResponse(Call<List<BikeHistory>> call, Response<List<BikeHistory>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<BikeHistory> bikeHistories = response.body();
                historyRecyler.setAdapter(new BikeHistoryAdapter(bikeHistories, HistoriMotor.this));
            }

            @Override
            public void onFailure(Call<List<BikeHistory>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(TAG, t.getMessage());
                Toast.makeText(HistoriMotor.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}