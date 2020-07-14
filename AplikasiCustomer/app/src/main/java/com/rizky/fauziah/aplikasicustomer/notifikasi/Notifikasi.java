package com.rizky.fauziah.aplikasicustomer.notifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.adapters.DiscountAdapter;
import com.rizky.fauziah.aplikasicustomer.models.Discount;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

public class Notifikasi extends AppCompatActivity {
    //Note: get data, muncul dalam bentuk recyclerview vertical, layout sudah ada

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_notifikasi);

        recyclerView = findViewById(R.id.id_list_notifikasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        progressBar = findViewById(R.id.loading_bar);

        prepareData();
    }

    private void prepareData() {
        progressBar.setVisibility(View.VISIBLE);
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<List<Discount>> call = apiClient.getDiscounts();

        call.enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<Discount> discounts = response.body();
                recyclerView.setAdapter(new DiscountAdapter(discounts));
            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(Notifikasi.class.getSimpleName(), t.getMessage());
                Toast.makeText(Notifikasi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}