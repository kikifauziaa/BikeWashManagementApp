package com.rizky.fauziah.aplikasicustomer.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rizky.fauziah.aplikasicustomer.InputRating;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.BikeHistory;
import com.rizky.fauziah.aplikasicustomer.utilities.Constant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BikeHistoryAdapter extends RecyclerView.Adapter<BikeHistoryAdapter.BikeHistoryViewHolder> {

    private List<BikeHistory> bikeHistories;
    private Context context;

    public BikeHistoryAdapter(List<BikeHistory> bikeHistories, Context context) {
        this.bikeHistories = bikeHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public BikeHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_motor, parent, false);
        return new BikeHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeHistoryViewHolder holder, int position) {
        holder.worker.setText(bikeHistories.get(position).getWorker());
        holder.washDate.setText(bikeHistories.get(position).getCreatedAt());
        holder.washType.setText(bikeHistories.get(position).getWashType());
        holder.bikeName.setText(bikeHistories.get(position).getLicensePlate());
        holder.rating.setText(String.valueOf(bikeHistories.get(position).getRating()));

        holder.btn_submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, InputRating.class);
                Bundle b = new Bundle();
                b.putInt("rating_type", Constant.RATING_MOTOR);
                b.putString("id", String.valueOf(bikeHistories.get(position).getId()));
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bikeHistories.size();
    }

    public static class BikeHistoryViewHolder extends RecyclerView.ViewHolder {
        CardView historyLayout;
        TextView bikeName;
        TextView washType;
        TextView washDate;
        TextView worker;
        TextView rating;
        Button btn_submit_rating;

        public BikeHistoryViewHolder(View v) {
            super(v);
            historyLayout = (CardView) v.findViewById(R.id.card_view);
            bikeName = (TextView) v.findViewById(R.id.tv_motor_namabarang);
            washType = (TextView) v.findViewById(R.id.tv_motor_jeniscuci);
            washDate = (TextView) v.findViewById(R.id.tv_motor_tglcuci);
            worker = (TextView) v.findViewById(R.id.tv_motor_namapegawai);
            rating = (TextView) v.findViewById(R.id.tv_motor_rating);
            btn_submit_rating = v.findViewById(R.id.btn_submit_rating);
        }
    }
}
