package com.rizky.fauziah.aplikasicustomer.login;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.antrian.Antrian;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.register.Register;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

public class Login extends AppCompatActivity {
    //Note : kalau login berhasil, masuk ke layout antrian
    private SessionManager sessionManager;
    private EditText username, password;
    private TextView labelRegisterHere;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        getSupportActionBar().hide();

        sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.getBoolean(SessionManager.Key.IS_LOGGED_IN)) {
            Intent i = new Intent(this, Antrian.class);
            startActivity(i);
            finish();
        }

        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
        labelRegisterHere = findViewById(R.id.label_register_here);

        labelRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        Bundle b = getIntent().getExtras();
        if(b != null) {
            if(b.getBoolean("register_success", false)) {
                Snackbar.make(findViewById(android.R.id.content), "Register berhasil, silahkan login", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    public void login(View v) {
        username.setError(null);
        password.setError(null);

        String txt_username = username.getText().toString();
        String txt_password = password.getText().toString();
        View focusView = null;
        boolean cancel = false;

        if(TextUtils.isEmpty(txt_password)) {
            password.setError("Password wajib diisi");
            focusView = password;
            cancel = true;
        }

        if(TextUtils.isEmpty(txt_username)) {
            username.setError("Username wajib diisi");
            focusView = username;
            cancel = true;
        }

        if(cancel) {
            focusView.requestFocus();
        } else {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setTitle("Login...");
            dialog.show();

            ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
            Call<Customer> call = apiClient.login(txt_username, txt_password);

            call.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    dialog.dismiss();
                    Customer customer = response.body();
                    if(customer != null) {
                        sessionManager.setSession(SessionManager.Key.IS_LOGGED_IN, true);

                        Gson gson = new Gson();
                        String data = gson.toJson(customer);
                        sessionManager.setSession(SessionManager.Key.USER_DATA, data);

                        Intent i = new Intent(Login.this, Antrian.class);
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Gagal, Username atau Password salah", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                    dialog.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), "Gagal, Username atau Password salah", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }
}