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
import com.rizky.fauziah.aplikasicustomer.InputAntrianKarpet;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.models.request.AddCarpet;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCarpetActivity extends AppCompatActivity {

    private EditText colorCarpet, lengthCarpet, widthCarpet, note;
    private Spinner typeCarpet;
    private Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_carpet);

        colorCarpet = findViewById(R.id.txt_color_carpet);
        lengthCarpet = findViewById(R.id.txt_length_carpet);
        widthCarpet = findViewById(R.id.txt_width_carpet);
        note = findViewById(R.id.txt_note_carpet);
        typeCarpet = findViewById(R.id.spinner_type_carpet);
        btnSubmit = findViewById(R.id.button_submit_carpet);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitCarpet();
            }
        });
    }

    private void submitCarpet() {
        View reqFocus = null;
        boolean fail = false;

        SessionManager s = new SessionManager(getApplicationContext());
        Gson gson = new Gson();
        Customer c = gson.fromJson(s.getString(SessionManager.Key.USER_DATA), Customer.class);

        String color = colorCarpet.getText().toString();
        String length = lengthCarpet.getText().toString();
        String width = widthCarpet.getText().toString();
        String note = this.note.getText().toString();
        String customer = c.getName();
        String type = typeCarpet.getSelectedItem().toString();

        if(TextUtils.isEmpty(color)) {
            colorCarpet.setError("Warna karpet wajib diisi");
            reqFocus = colorCarpet;
            fail = true;
        }

        if(TextUtils.isEmpty(length)) {
            lengthCarpet.setError("Warna karpet wajib diisi");
            reqFocus = lengthCarpet;
            fail = true;
        }

        if(TextUtils.isEmpty(width)) {
            widthCarpet.setError("Warna karpet wajib diisi");
            reqFocus = widthCarpet;
            fail = true;
        }

        if(TextUtils.isEmpty(note)) {
            this.note.setError("Warna karpet wajib diisi");
            reqFocus = this.note;
            fail = true;
        }

        if(fail) {
            reqFocus.requestFocus();
            return;
        }

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Mohon tunggu...");
        dialog.setCancelable(false);
        dialog.show();

        AddCarpet addCarpet = new AddCarpet(color, type, length, width, customer);
        addCarpet.setNote(note);

        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<String> call = apiClient.addCarpet(addCarpet);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                String res = response.body();
                if(res != null) {
                    if(res.equalsIgnoreCase("success")) {
                        Intent i = new Intent(AddCarpetActivity.this, InputAntrianKarpet.class);
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Input karpet gagal: "+ res, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Input karpet gagal", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Log.e(InputAntrian.class.getSimpleName(), t.getMessage());
                Snackbar.make(findViewById(android.R.id.content), "Input karpet gagal: "+ t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
