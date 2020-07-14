package com.rizky.fauziah.aplikasicustomer.historykarpet;

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
import com.rizky.fauziah.aplikasicustomer.adapters.CarpetHistoryAdapter;
import com.rizky.fauziah.aplikasicustomer.login.Login;
import com.rizky.fauziah.aplikasicustomer.models.CarpetHistory;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.profil.Profil;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.antrian.Antrian;
import com.rizky.fauziah.aplikasicustomer.historymotor.HistoriMotor;
import com.rizky.fauziah.aplikasicustomer.notifikasi.Notifikasi;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

public class HistoriKarpet extends AppCompatActivity {
    //Note: get data, muncul dalam bentuk recyclerview vertical, layout sudah ada

    private static final String TAG = HistoriKarpet.class.getSimpleName();
    private BottomNavigationView bottom;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Customer customer;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_histori_karpet);

        progressBar = findViewById(R.id.loading_bar);
        recyclerView = findViewById(R.id.id_list_history_karpet);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SessionManager s = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        customer = gson.fromJson(s.getString(SessionManager.Key.USER_DATA), Customer.class);

        BottomNavigation ();
        prepareData();
    }

    private void BottomNavigation(){
        bottom = (BottomNavigationView) findViewById (R.id.bottomnavigation);

        bottom.setSelectedItemId (R.id.action_karpet);

        bottom.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                if(item.getItemId () == R.id.action_motor){
                    //Intent myIntent = new Intent(getBaseContext(),  HistoriMotor.class);
                    startActivity(new Intent (getApplicationContext (), HistoriMotor.class));
                    finish ();
                    overridePendingTransition (0,0);
                }else  if(item.getItemId () == R.id.action_karpet){

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

        Call<List<CarpetHistory>> call = apiClient.getCarpetHistories(customer.getId());
        call.enqueue(new Callback<List<CarpetHistory>>() {
            @Override
            public void onResponse(Call<List<CarpetHistory>> call, Response<List<CarpetHistory>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<CarpetHistory> carpetHistories = response.body();
                recyclerView.setAdapter(new CarpetHistoryAdapter(carpetHistories, HistoriKarpet.this));
            }

            @Override
            public void onFailure(Call<List<CarpetHistory>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(TAG, t.getMessage());
                Toast.makeText(HistoriKarpet.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}