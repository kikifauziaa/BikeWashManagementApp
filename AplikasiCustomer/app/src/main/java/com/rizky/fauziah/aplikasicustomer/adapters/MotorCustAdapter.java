package com.rizky.fauziah.aplikasicustomer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.BikeHistory;
import com.rizky.fauziah.aplikasicustomer.models.CarpetHistory;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBike;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MotorCustAdapter extends RecyclerView.Adapter<MotorCustAdapter.motorcustVH> {

    private List<AddBike> bikeHistories;
    private Context context;

    public MotorCustAdapter (List<AddBike> bikeHistories, Context context) {
        this.bikeHistories = bikeHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public motorcustVH onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_motorcustomer, parent, false);
        return new motorcustVH (view);
    }

    @Override
    public void onBindViewHolder (@NonNull motorcustVH holder, int position) {
        AddBike bike = bikeHistories.get (position);
        String jumlah = String.valueOf (bike.getAmountOfWash ());

        holder.plat.setText (bike.getLicensePlate ());
        holder.jnsmotor.setText (bike.getTypeOfBike ());
        holder.jumlah.setText (jumlah+ "x cuci");

//        CarpetHistory history = carpetHistories.get(position);
//        String rating = String.valueOf(history.getRating());

//        holder.name.setText(String.format("Karpet %s warna %s", history.getTypeOfCarper(), history.getColorOfCarper()));
//        holder.washType.setText(history.getWashType());
//        holder.washDate.setText(history.getCreatedAt());
//        holder.worker.setText(history.getWorker1());
//        holder.worker2.setText(history.getWorker2());
//        holder.rating.setText(rating == null ? "0" : rating);

    }

    @Override
    public int getItemCount ( ) {
        return bikeHistories.size ();
    }

    public class motorcustVH extends RecyclerView.ViewHolder{
        TextView plat, jnsmotor, jumlah;
        CardView layout;

        public motorcustVH (@NonNull View itemView) {
            super (itemView);
            layout = (CardView) itemView.findViewById(R.id.card_motorcust);
            plat = (TextView) itemView.findViewById(R.id.plat_cust);
            jnsmotor = (TextView) itemView.findViewById(R.id.jnsmotor_cust);
            jumlah = (TextView) itemView.findViewById(R.id.jumlahmotor_cust);
        }
    }
}
