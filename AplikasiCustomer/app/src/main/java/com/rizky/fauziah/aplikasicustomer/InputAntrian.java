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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.antrian.Antrian;
import com.rizky.fauziah.aplikasicustomer.login.Login;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBike;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBikeQueue;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpet;
import com.rizky.fauziah.aplikasicustomer.notifikasi.Notifikasi;
import com.rizky.fauziah.aplikasicustomer.register.Register;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import java.util.List;

public class InputAntrian extends AppCompatActivity {
    //setelah input antrian motor, antrian muncul di layout antrian
    private Spinner washType, spinnerLicense;
    private Button btnSubmit;
    private TextView lihatlistmotorcust;
    private List<AddBike> licenseList;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_input_antrian);

        spinnerLicense = findViewById(R.id.spinner_license);
        washType = findViewById(R.id.txt_wash_type);
        btnSubmit = findViewById(R.id.btn_add_motor_queue);
        lihatlistmotorcust = findViewById (R.id.lbl_cekmotor);

        lihatlistmotorcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext (), MotorCustomer.class);
                startActivity(i);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitQueue();
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
        String txt_license = licenseList.get(spinnerLicense.getSelectedItemPosition()).getLicensePlate();
        String txt_wash = washType.getSelectedItem().toString();

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Mohon tunggu...");
        dialog.show();

        AddBikeQueue queue = new AddBikeQueue(txt_license, txt_wash);
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<String> call = apiClient.addBikeQueue(queue);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                String res = response.body();
                if(res != null) {
                    if(res.equalsIgnoreCase("success")) {
                        Toast.makeText(InputAntrian.this, "Berhasil ambil antrian", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(InputAntrian.this, Antrian.class);
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Antrian gagal, pastikan motor anda terdaftar "+ res, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Antrian gagal,  pastikan motor anda terdaftar", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Log.e(InputAntrian.class.getSimpleName(), t.getMessage());
                Snackbar.make(findViewById(android.R.id.content), "Antrian gagal: "+ t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void prepareData() {
        SessionManager session = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        Customer c = gson.fromJson(session.getString(SessionManager.Key.USER_DATA), Customer.class);
        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<List<AddBike>> call = apiClient.getCustomerBikes(String.valueOf(c.getId()));

        call.enqueue(new Callback<List<AddBike>>() {
            @Override
            public void onResponse(Call<List<AddBike>> call, Response<List<AddBike>> response) {
                licenseList = response.body();
                String[] licenses = new String[licenseList.size()];

                for(int i = 0; i < licenseList.size(); i++) {
                    licenses[i] = licenseList.get(i).getLicensePlate();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        InputAntrian.this,
                        android.R.layout.simple_spinner_item,
                        licenses);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerLicense.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AddBike>> call, Throwable t) {

            }
        });
    }

}