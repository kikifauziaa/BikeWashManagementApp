package com.rizky.fauziah.aplikasicustomer.profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.antrian.Antrian;
import com.rizky.fauziah.aplikasicustomer.historykarpet.HistoriKarpet;
import com.rizky.fauziah.aplikasicustomer.historymotor.HistoriMotor;
import com.rizky.fauziah.aplikasicustomer.login.Login;
import com.rizky.fauziah.aplikasicustomer.models.Customer;
import com.rizky.fauziah.aplikasicustomer.notifikasi.Notifikasi;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Profil extends AppCompatActivity {
    //Note : get data biasa, desesuaikan sama layout

    private BottomNavigationView bottom;
    private TextView labelName, labelAddress, labelEmail, labelSaldo;
    private SessionManager sessionManager;
    private Customer customer;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_profil);

        sessionManager = new SessionManager(getApplicationContext());
        if(!sessionManager.getBoolean(SessionManager.Key.IS_LOGGED_IN)) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        labelName = findViewById(R.id.lbl_customer_name);
        labelAddress = findViewById(R.id.lbl_customer_address);
        labelEmail = findViewById(R.id.lbl_customer_email);
        labelSaldo = findViewById(R.id.lbl_customer_deposit);

        Gson gson = new Gson();
        String data = sessionManager.getString(SessionManager.Key.USER_DATA);
        customer = gson.fromJson(data, Customer.class);

        labelName.setText(customer.getName());
        labelAddress.setText(customer.getAddress());
        labelEmail.setText(customer.getEmail());
        labelSaldo.setText(format_number(String.valueOf(customer.getDepositedMoney())));

        BottomNavigation ();
    }

    private void BottomNavigation(){
        bottom = (BottomNavigationView) findViewById (R.id.bottomnavigation);

        bottom.setSelectedItemId (R.id.action_profil);

        bottom.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                if(item.getItemId () == R.id.action_motor){
                    startActivity(new Intent (getApplicationContext (), HistoriMotor.class));
                    finish ();
                    overridePendingTransition (0,0);
                }else  if(item.getItemId () == R.id.action_karpet){
                    startActivity(new Intent (getApplicationContext (), HistoriKarpet.class));
                    finish ();
                    overridePendingTransition (0,0);
                }else if(item.getItemId () == R.id.action_profil){

                }else {
                    startActivity(new Intent (getApplicationContext (), Antrian.class));
                    finish ();
                    overridePendingTransition (0,0);

                }
                return false;
            }
        });
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
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.clearData();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish ();
        }
        return true;
        //return super.onOptionsItemSelected(item);

    }

    private String format_number(String angka){
        DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        mataUangIndonesia.setDecimalFormatSymbols(formatRp);
        int hargaRupiah = Integer.parseInt(String.valueOf(angka));
        return mataUangIndonesia.format(hargaRupiah);
    }
}