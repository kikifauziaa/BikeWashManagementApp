package com.rizky.fauziah.aplikasicustomer;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.antrian.Antrian;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBikeQueue;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpet;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpetQueue;
import com.rizky.fauziah.aplikasicustomer.notifikasi.Notifikasi;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

public class InputAntrianKarpet extends AppCompatActivity {
    //setelah input antrian karpet, antrian muncul di layout antrian
    private EditText customerName;
    private Spinner carpetType, washType, carpetColor;
    private Button btnSubmit;
    private TextView cekkarpet;
    private List<AddCarpet> colorList;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_input_antrian_karpet);

        carpetColor = findViewById(R.id.spinner_carpet_color);
        carpetType = findViewById(R.id.spinner_type_carpet);
        washType = findViewById(R.id.spinner_wash_carpet);
        btnSubmit = findViewById(R.id.btn_submit_carpet_queue);
        carpetColor.setEnabled(false);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitQueue();
            }
        });


        cekkarpet = findViewById (R.id.lbl_cekkarpet);
        cekkarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext (), KarpetCustomer.class);
                startActivity(i);
            }
        });

        prepareData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atas, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //setMode(item.getItemId());
        //return super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_notif){
            Intent myIntent = new Intent(getBaseContext(),  Notifikasi.class);
            startActivity(myIntent);
        }else if(id==R.id.action_logout){
            finish ();
        }
        return true;
        //return super.onOptionsItemSelected(item);

    }

    private void submitQueue() {
        SessionManager s = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        Customer c = gson.fromJson(s.getString(SessionManager.Key.USER_DATA), Customer.class);

        String color = colorList.get(carpetColor.getSelectedItemPosition()).getColorOfCarpet();
        String type = carpetType.getSelectedItem().toString();
        String wash = washType.getSelectedItem().toString();

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Mohon tunggu...");
        dialog.show();

        AddCarpetQueue queue = new AddCarpetQueue(color, type, wash, c.getName());
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<String> call = apiClient.addCarpetQueue(queue);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                String res = response.body();
                if(res != null) {
                    if(res.equalsIgnoreCase("success")) {
                        Toast.makeText(InputAntrianKarpet.this, "Berhasil ambil antrian", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(InputAntrianKarpet.this, Antrian.class);
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Antrian gagal, pastikan data karpet terdaftar "+ res, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Antrian gagal, pastikan data karpet terdaftar", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Log.e(InputAntrian.class.getSimpleName(), t.getMessage());
                Snackbar.make(findViewById(android.R.id.content), "Antrian gagal, pastikan data karpet terdaftar "+ t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareData() {
        SessionManager session = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        Customer c = gson.fromJson(session.getString(SessionManager.Key.USER_DATA), Customer.class);
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<List<AddCarpet>> call = apiClient.getCustomerCarpets(String.valueOf(c.getId()));

        call.enqueue(new Callback<List<AddCarpet>>() {
            @Override
            public void onResponse(Call<List<AddCarpet>> call, Response<List<AddCarpet>> response) {
                colorList = response.body();
                String[] colors = new String[colorList.size()];

                for(int i = 0; i < colorList.size(); i++) {
                    colors[i] = String.format("%d. %s, %s x %s",
                            (i+1),
                            colorList.get(i).getColorOfCarpet(),
                            colorList.get(i).getLengthOfCarpet(),
                            colorList.get(i).getWidthOfCarpet());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        InputAntrianKarpet.this,
                        android.R.layout.simple_spinner_item,
                        colors);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carpetColor.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AddCarpet>> call, Throwable t) {

            }
        });
    }
}