package com.rizky.fauziah.aplikasicustomer.antrian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.rizky.fauziah.aplikasicustomer.InputAntrian;
import com.rizky.fauziah.aplikasicustomer.InputAntrianKarpet;
import com.rizky.fauziah.aplikasicustomer.fragments.BikeQueueFragment;
import com.rizky.fauziah.aplikasicustomer.fragments.CarpetQueueFragment;
import com.rizky.fauziah.aplikasicustomer.login.Login;
import com.rizky.fauziah.aplikasicustomer.profil.Profil;
import com.rizky.fauziah.aplikasicustomer.R;
import com.rizky.fauziah.aplikasicustomer.historykarpet.HistoriKarpet;
import com.rizky.fauziah.aplikasicustomer.historymotor.HistoriMotor;
import com.rizky.fauziah.aplikasicustomer.notifikasi.Notifikasi;
import com.rizky.fauziah.aplikasicustomer.utilities.SessionManager;

public class Antrian extends AppCompatActivity {
    //Note : recyclerviewnya horizontal, antriannya ada 2 antrian cuci motor sama antrian karpet.
    //maunya bikin 1 recyclerview dengan 2 jenis antrian, tapi kalo gak bisa, dipisah aja
    //jadi ada 2 recyclerview atas buat motor, bawah buat karpet

    private BottomNavigationView bottom;
    private SessionManager sessionManager;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private String[] titles = {"Motor", "Karpet"};

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.antrian_activity_view_page);
        if(getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        sessionManager = new SessionManager(getApplicationContext());
        if(!sessionManager.getBoolean(SessionManager.Key.IS_LOGGED_IN)) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        tabLayout = findViewById(R.id.tab_antrian);
        viewPager2 = findViewById(R.id.view_pager_antria);
        viewPager2.setAdapter(new ViewPagerFragmentAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(titles[position])).attach();

        BottomNavigation ();
    }

//    private void floatbuton(){
//        FloatingActionButton floatingActionButton=findViewById(R.id.fab1);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(About.this, "Floating Action Button Berhasil dibuat", Toast.LENGTH_SHORT).show();
//                //here
//                startActivity(new Intent (Antrian.this, InputAntrian.class));
//            }
//        });

    private void BottomNavigation(){
        bottom = (BottomNavigationView) findViewById (R.id.bottomnavigation);

        bottom.setSelectedItemId (R.id.action_antri);

        bottom.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                if(item.getItemId () == R.id.action_motor){
                    //Intent myIntent = new Intent(getBaseContext(),  HistoriMotor.class);
                    startActivity(new Intent (getApplicationContext (), HistoriMotor.class));
                    finish ();
                    overridePendingTransition (0,0);
                }else  if(item.getItemId () == R.id.action_karpet){
                    startActivity(new Intent (getApplicationContext (), HistoriKarpet.class));
                    finish ();
                    overridePendingTransition (0,0);

                }else if(item.getItemId () == R.id.action_profil){
                    startActivity(new Intent (getApplicationContext (), Profil.class));
                    finish ();
                    overridePendingTransition (0,0);
                }else {

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

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new BikeQueueFragment();
                case 1:
                    return new CarpetQueueFragment();
            }

            return new BikeQueueFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}