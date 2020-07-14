package com.rizky.fauziah.aplikasicustomer.register;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.login.Login;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

public class Register extends AppCompatActivity {
    //Note : post data, nanti bida buat login
    private EditText name, address, phone, email, username, password;
    private Button btnSubmit;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        getSupportActionBar().hide();

        name = findViewById(R.id.txt_name);
        address = findViewById(R.id.txt_address);
        phone = findViewById(R.id.txt_phone);
        email = findViewById(R.id.txt_email);
        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                submitRegister();
                return false;
            }
        });

        btnSubmit = findViewById(R.id.btn_register);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRegister();
            }
        });
    }

    public void submitRegister() {
        View reqFocus = null;
        boolean fail = false;

        String txt_name = name.getText().toString();
        String txt_address = address.getText().toString();
        String txt_phone = phone.getText().toString();
        String txt_email = email.getText().toString();
        String txt_username = username.getText().toString();
        String txt_password = password.getText().toString();

        if(TextUtils.isEmpty(txt_name)) {
            name.setError("Nama wajib diisi");
            reqFocus = name;
            fail = true;
        }
        if(TextUtils.isEmpty(txt_address)) {
            address.setError("Alamat wajib diisi");
            reqFocus = address;
            fail = true;
        }
        if(TextUtils.isEmpty(txt_email)) {
            email.setError("Email wajib diisi");
            reqFocus = email;
            fail = true;
        }
        if(TextUtils.isEmpty(txt_phone)) {
            phone.setError("Nomor Telepon wajib diisi");
            reqFocus = phone;
            fail = true;
        }
        if(TextUtils.isEmpty(txt_username)) {
            username.setError("Username wajib diisi");
            reqFocus = username;
            fail = true;
        }
        if(TextUtils.isEmpty(txt_password)) {
            password.setError("Password wajib diisi");
            reqFocus = password;
            fail = true;
        }

        if(fail) {
            reqFocus.requestFocus();
        }

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Mohon tunggu...");
        dialog.setCancelable(false);
        dialog.show();

        com.rizky.fauziah.aplikasicustomer.models.request.Register register;
        register = new com.rizky.fauziah.aplikasicustomer.models.request.Register(txt_username, txt_password, txt_name, txt_address, txt_email, txt_phone);

        ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
        Call<String> call = apiClient.register(register);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                String res = response.body();
                if(res != null) {
                    if (res.equalsIgnoreCase("success")) {
                        Intent i = new Intent(Register.this, Login.class);
                        Bundle b = new Bundle();
                        b.putBoolean("register_success", true);
                        i.putExtras(b);
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Register gagal: " + res, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Register gagal: " + res, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Log.e(Register.class.getSimpleName(), t.getMessage());
                Snackbar.make(findViewById(android.R.id.content), t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}