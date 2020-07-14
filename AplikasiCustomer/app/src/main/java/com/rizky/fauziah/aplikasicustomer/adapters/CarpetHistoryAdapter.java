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
import com.rizky.fauziah.aplikasicustomer.models.CarpetHistory;
import com.rizky.fauziah.aplikasicustomer.utilities.Constant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CarpetHistoryAdapter extends RecyclerView.Adapter<CarpetHistoryAdapter.CarperViewHolder> {

    List<CarpetHistory> carpetHistories;
    Context context;

    public CarpetHistoryAdapter(List<CarpetHistory> carpetHistories, Context context) {
        this.carpetHistories = carpetHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public CarperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_karpet, parent, false);
        return new CarperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarperViewHolder holder, int position) {
        CarpetHistory history = carpetHistories.get(position);
        String rating = String.valueOf(history.getRating());

        holder.name.setText(String.format("Karpet %s warna %s", history.getTypeOfCarper(), history.getColorOfCarper()));
        holder.washType.setText(history.getWashType());
        holder.washDate.setText(history.getCreatedAt());
        holder.worker.setText(history.getWorker1());
        holder.worker2.setText(history.getWorker2());
        holder.rating.setText(rating == null ? "0" : rating);

        holder.btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, InputRating.class);
                Bundle b = new Bundle();
                b.putInt("rating_type", Constant.RATING_CARPET);
                b.putString("id", String.valueOf(history.getId()));
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carpetHistories.size();
    }

    public static class CarperViewHolder extends RecyclerView.ViewHolder {
        CardView historyLayout;
        TextView name, washType, washDate, worker, rating, worker2;
        Button btnRating;

        public CarperViewHolder(@NonNull View itemView) {
            super(itemView);

            historyLayout = itemView.findViewById(R.id.card_view);
            name = itemView.findViewById(R.id.tv_karpet_namabarang);
            washType = itemView.findViewById(R.id.tv_karpet_jeniscuci);
            washDate = itemView.findViewById(R.id.tv_karpet_tglcuci);
            worker = itemView.findViewById(R.id.tv_karpet_namapegawai);
            worker2 = itemView.findViewById(R.id.tv_karpet_namapegawai2);
            rating = itemView.findViewById(R.id.tv_karpet_rating);

            btnRating = itemView.findViewById(R.id.btn_carpet_addrating);
        }
    }
}
