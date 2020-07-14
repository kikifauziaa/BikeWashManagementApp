package com.rizky.fauziah.aplikasicustomer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.CarpetHistory;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpet;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class KarpetCustAdapter extends RecyclerView.Adapter<KarpetCustAdapter.KarpetCustVH> {

    List<AddCarpet> carpetHistories;
    Context context;

    public KarpetCustAdapter (List<AddCarpet> carpetHistories, Context context) {
        this.carpetHistories = carpetHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public KarpetCustVH onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_karpetcustomer, parent, false);
        return new KarpetCustVH (view);
    }

    @Override
    public void onBindViewHolder (@NonNull KarpetCustVH holder, int position) {

        AddCarpet kt = carpetHistories.get(position);

        holder.warna.setText ("Karpet warna "+kt.getColorOfCarpet ());
        holder.tipe.setText (kt.getTypeOfCarpet ());
        holder.panjang.setText (kt.getLengthOfCarpet ()+"cm x "+kt.getWidthOfCarpet ()+ "cm");
        holder.jumlah.setText (kt.getAmountOfWash ()+"x cuci");

//        CarpetHistory history = carpetHistories.get(position);
//        String rating = String.valueOf(history.getRating());
//
//        holder.name.setText(String.format("Karpet %s warna %s", history.getTypeOfCarper(), history.getColorOfCarper()));
//        holder.washType.setText(history.getWashType());
//        holder.washDate.setText(history.getCreatedAt());
//        holder.worker.setText(history.getWorker1());
//        holder.worker2.setText(history.getWorker2());
//        holder.rating.setText(rating == null ? "0" : rating);

    }

    @Override
    public int getItemCount ( ) {
        return carpetHistories.size ();
    }

    public class KarpetCustVH extends RecyclerView.ViewHolder{
        CardView layout;
        TextView warna, tipe, panjang, lebar, jumlah;

        public KarpetCustVH (View view) {
            super (view);
            layout = (CardView) view.findViewById(R.id.card_karpetcust);
            warna = (TextView) view.findViewById(R.id.warna_cust);
            tipe = (TextView) view.findViewById (R.id.jnskarpet_cust);
            panjang = (TextView) view.findViewById (R.id.ukuran_cust);
            jumlah = (TextView) view.findViewById (R.id.jumlahkarpet_cust);
        }
    }
}
