package com.rizky.fauziah.aplikasicustomer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.CarpetQueue;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QueueCarpetAdapter extends RecyclerView.Adapter<QueueCarpetAdapter.QueueCarpetViewHolder> {

    private List<CarpetQueue> carpetQueues;

    public QueueCarpetAdapter(List<CarpetQueue> carpetQueues) {
        this.carpetQueues = carpetQueues;
    }

    @NonNull
    @Override
    public QueueCarpetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_antri_carpet, parent, false);

        return new QueueCarpetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueueCarpetViewHolder holder, int position) {
        CarpetQueue carpetQueue = carpetQueues.get(position);

        holder.name.setText(String.format("%s: Karpet warna %s", carpetQueue.getCustomer(), carpetQueue.getColorOfCarpet()));
        holder.queueNumber.setText(String.format("Nomor antrian : %d", carpetQueue.getId()));
        holder.estimate.setText(String.format("Estimasi waktu selesai %s", carpetQueue.getEstimationTime().substring(0, 5)));
        holder.status.setText(String.format("Status: %s", carpetQueue.getStatus()));
    }

    @Override
    public int getItemCount() {
        return carpetQueues.size();
    }

    public class QueueCarpetViewHolder extends RecyclerView.ViewHolder {
        public TextView queueNumber, name, estimate, status;

        public QueueCarpetViewHolder(@NonNull View itemView) {
            super(itemView);

            queueNumber = itemView.findViewById(R.id.tv_nomor_antrian);
            name = itemView.findViewById(R.id.tv_barang_antrian);
            estimate = itemView.findViewById(R.id.tv_estimasi_antrian);
            status = itemView.findViewById(R.id.tv_status_antrian);
        }
    }
}
