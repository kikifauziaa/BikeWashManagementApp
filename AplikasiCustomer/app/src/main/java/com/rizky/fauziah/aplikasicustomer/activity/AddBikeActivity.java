package com.rizky.fauziah.aplikasicustomer.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.InputAntrian;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.models.request.AddBike;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBikeActivity extends AppCompatActivity {

    private EditText licensePlate, note;
    private Spinner motorSize, motorType;
    private Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_motor);

        licensePlate = findViewById(R.id.txt_license_plate);
        note = findViewById(R.id.txt_note);
        motorSize = findViewById(R.id.spinner_size_motor);
        motorType = findViewById(R.id.spinner_type_motor);

        btnSubmit = findViewById(R.id.btn_submit_motor);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitBike();
            }
        });
    }

    private void submitBike() {
        View reFocus = null;
        boolean fail = false;

        SessionManager s = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        Customer c = gson.fromJson(s.getString(SessionManager.Key.USER_DATA), Customer.class);

        String license = licensePlate.getText().toString();
        String customer = c.getName();
        String note = this.note.getText().toString();
        String size = motorSize.getSelectedItem().toString();
        String type = motorType.getSelectedItem().toString();

        if(TextUtils.isEmpty(license)) {
            licensePlate.setError("Nomor plat motor wajib diisi");
            reFocus = licensePlate;
            fail = true;
        }

        if(TextUtils.isEmpty(note)) {
            this.note.setError("Catatan wajib diisi");
            reFocus = this.note;
            fail = true;
        }

        if(fail) {
            reFocus.requestFocus();
            return;
        }

        AddBike addBike = new AddBike(license, type, size, customer);
        addBike.setNote(note);
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Mohon tunggu...");
        dialog.setCancelable(false);
        dialog.show();

        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<String> call = apiClient.addBike(addBike);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                String res = response.body();
                if(res != null) {
                    if(res.equalsIgnoreCase("success")) {
                        Intent i = new Intent(AddBikeActivity.this, InputAntrian.class);
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Input motor gagal: "+ res, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Input motor gagal", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Log.e(InputAntrian.class.getSimpleName(), t.getMessage());
                Snackbar.make(findViewById(android.R.id.content), "Input motor gagal: "+ t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
