package com.rizky.fauziah.aplikasicustomer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.BikeQueue;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QueueMotorAdapter extends RecyclerView.Adapter<QueueMotorAdapter.BikeQueueViewHolder> {

    private List<BikeQueue> bikeQueues;

    public QueueMotorAdapter(List<BikeQueue> bikeQueues) {
        this.bikeQueues = bikeQueues;
    }

    @NonNull
    @Override
    public BikeQueueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_antri, parent, false);
        return new BikeQueueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeQueueViewHolder holder, int position) {
        BikeQueue bikeQueue = bikeQueues.get(position);

        holder.queueNumber.setText(String.format("Nomor antrian : %d", bikeQueue.getId()));
        holder.name.setText(bikeQueue.getLicensePlate());
        holder.estimate.setText(String.format("Estimasi waktu selesai %s", bikeQueue.getEstimationTime().substring(0, 5)));
        holder.status.setText(String.format("Status: %s", bikeQueue.getStatus()));
    }

    @Override
    public int getItemCount() {
        return bikeQueues.size();
    }

    public class BikeQueueViewHolder extends RecyclerView.ViewHolder {
        public TextView queueNumber, name, estimate, status;

        public BikeQueueViewHolder(@NonNull View itemView) {
            super(itemView);

            queueNumber = itemView.findViewById(R.id.tv_nomor_antrian);
            name = itemView.findViewById(R.id.tv_barang_antrian);
            estimate = itemView.findViewById(R.id.tv_estimasi_antrian);
            status = itemView.findViewById(R.id.tv_status_antrian);
        }
    }
}
