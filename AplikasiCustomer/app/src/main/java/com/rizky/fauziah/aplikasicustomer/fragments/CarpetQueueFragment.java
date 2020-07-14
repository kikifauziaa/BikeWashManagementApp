package com.rizky.fauziah.aplikasicustomer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizky.fauziah.aplikasicustomer.InputAntrianKarpet;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.activity.AddCarpetActivity;
import com.rizky.fauziah.aplikasicustomer.adapters.QueueCarpetAdapter;
import com.rizky.fauziah.aplikasicustomer.models.CarpetQueue;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarpetQueueFragment extends Fragment {

    private RecyclerView recyclerView;
    private QueueCarpetAdapter queueCarpetAdapter;
    private FloatingActionButton fab;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.list_antrian_karpet);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InputAntrianKarpet.class);
                startActivity(i);
            }
        });

        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<List<CarpetQueue>> call = apiClient.getCarpetQueues();

        call.enqueue(new Callback<List<CarpetQueue>>() {
            @Override
            public void onResponse(Call<List<CarpetQueue>> call, Response<List<CarpetQueue>> response) {
                List<CarpetQueue> carpetQueues = response.body();
                queueCarpetAdapter = new QueueCarpetAdapter(carpetQueues);
                recyclerView.setAdapter(queueCarpetAdapter);
            }

            @Override
            public void onFailure(Call<List<CarpetQueue>> call, Throwable t) {
                Log.e(CarpetQueueFragment.class.getSimpleName(), t.getMessage());
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.antrian_karpet_fragment, container, false);
    }
}
