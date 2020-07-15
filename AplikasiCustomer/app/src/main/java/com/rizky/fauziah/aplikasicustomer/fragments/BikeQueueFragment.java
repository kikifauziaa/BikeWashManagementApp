package com.rizky.fauziah.aplikasicustomer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizky.fauziah.aplikasicustomer.InputAntrian;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.activity.AddBikeActivity;
import com.rizky.fauziah.aplikasicustomer.adapters.QueueMotorAdapter;
import com.rizky.fauziah.aplikasicustomer.models.BikeQueue;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BikeQueueFragment extends Fragment {

    private RecyclerView recyclerView;
    private QueueMotorAdapter queueMotorAdapter;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;
    private View view;
    private List<BikeQueue> bikeQueues;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        recyclerView = view.findViewById(R.id.list_antrian_motor);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        fab = view.findViewById(R.id.fab);
        refreshLayout = view.findViewById(R.id.bike_swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        refreshLayout.setOnRefreshListener(() -> prepareData(true));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), AddBikeActivity.class);
//                startActivity(i);

                Intent i = new Intent(view.getContext(), InputAntrian.class);
                startActivity(i);
            }
        });

        prepareData(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.antrian_motor_fragment, container, false);
    }

    private void prepareData(boolean refresh) {
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<List<BikeQueue>> call = apiClient.getBikeQueues();
        if(refresh) {
            bikeQueues.clear();
            queueMotorAdapter.notifyDataSetChanged();
        }

        call.enqueue(new Callback<List<BikeQueue>>() {
            @Override
            public void onResponse(Call<List<BikeQueue>> call, Response<List<BikeQueue>> response) {
                bikeQueues = response.body();
                queueMotorAdapter = new QueueMotorAdapter(bikeQueues);
                recyclerView.setAdapter(queueMotorAdapter);
                queueMotorAdapter.notifyDataSetChanged();
                if(refresh) {
                    refreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<BikeQueue>> call, Throwable t) {
                Log.e(BikeQueueFragment.class.getSimpleName(), t.getMessage());
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
