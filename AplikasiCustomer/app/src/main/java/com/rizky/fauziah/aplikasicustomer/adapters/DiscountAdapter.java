package com.rizky.fauziah.aplikasicustomer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.Discount;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountAdapterViewHolder> {

    private List<Discount> discounts;

    public DiscountAdapter(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @NonNull
    @Override
    public DiscountAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notif, parent, false);
        return new DiscountAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountAdapterViewHolder holder, int position) {
        Discount d = discounts.get(position);

        holder.discAmount.setText(d.getDiscountValue() +"%");
        holder.discCode.setText(d.getDiscountCode().toUpperCase());
        holder.discEnd.setText(d.getEndAt());
    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    public class DiscountAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView discAmount, discCode, discEnd;

        public DiscountAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            discAmount = itemView.findViewById(R.id.tv_notif_diskon);
            discCode = itemView.findViewById(R.id.tv_notif_kode2);
            discEnd = itemView.findViewById(R.id.tv_notifikasi_tanggal2);
        }
    }
}
