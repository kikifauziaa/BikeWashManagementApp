package com.rizky.fauziah.aplikasicustomer;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.rizky.fauziah.aplikasicustomer.historykarpet.HistoriKarpet;
import com.rizky.fauziah.aplikasicustomer.notifikasi.Notifikasi;
import com.rizky.fauziah.aplikasicustomer.utilities.ApiClient;
import com.rizky.fauziah.aplikasicustomer.utilities.Constant;
import com.rizky.fauziah.aplikasicustomer.utilities.interfaces.ApiCallInterface;

public class InputRating extends AppCompatActivity {
    //Note : Rating nanti diarahin ke workers '/workers/update/{workers}'

    private static final String TAG = InputRating.class.getSimpleName();
    ImageView rating1, rating2, rating3, rating4, rating5;
    private int total_rating;
    private int rating_type = Constant.RATING_MOTOR;
    private String id = null;
    Button btn_submit_rating;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_input_rating);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            rating_type = b.getInt("rating_type");
            id = b.getString("id");
        }

        rating1 = findViewById(R.id.rating_1);
        rating2 = findViewById(R.id.rating_2);
        rating3 = findViewById(R.id.rating_3);
        rating4 = findViewById(R.id.rating_4);
        rating5 = findViewById(R.id.rating_5);
        btn_submit_rating = findViewById(R.id.btn_submit_rating);

        btn_submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(total_rating <= 0) {
                    Snackbar s = Snackbar.make(findViewById(android.R.id.content), "Berikan rating terlebih dahulu", Snackbar.LENGTH_SHORT);
                    s.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            s.dismiss();
                        }
                    });
                    s.setActionTextColor(getResources().getColor(R.color.colorPrimary));
                    s.show();
                    return;
                }

                ApiCallInterface apiClient = ApiClient.getClient().create(ApiCallInterface.class);
                Call<String> call;
                if(rating_type == Constant.RATING_CARPET) {
                    call = apiClient.updateCarpetRating(id, total_rating);
                } else {
                    call = apiClient.updateMotorRating(id, total_rating);
                }

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String res = response.body();

                        if(res.toLowerCase().equalsIgnoreCase("success")) {
                            Snackbar.make(findViewById(android.R.id.content), "Terima kasih atas penilaian Anda", Snackbar.LENGTH_SHORT)
                                    .addCallback(new InputRating.SnackbarCallback())
                                    .show();
                        } else {
                            Toast.makeText(InputRating.this, res, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                        Toast.makeText(InputRating.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        ratingChangeInit();
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

    private void ratingChangeInit() {
        rating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating1.setImageResource(R.drawable.bintang_unyu);
                rating2.setImageResource(R.drawable.ic_bintangabu);
                rating3.setImageResource(R.drawable.ic_bintangabu);
                rating4.setImageResource(R.drawable.ic_bintangabu);
                rating5.setImageResource(R.drawable.ic_bintangabu);
                total_rating = 1;
            }
        });

        rating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating1.setImageResource(R.drawable.bintang_unyu);
                rating2.setImageResource(R.drawable.bintang_unyu);
                rating3.setImageResource(R.drawable.ic_bintangabu);
                rating4.setImageResource(R.drawable.ic_bintangabu);
                rating5.setImageResource(R.drawable.ic_bintangabu);
                total_rating = 2;
            }
        });

        rating3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating1.setImageResource(R.drawable.bintang_unyu);
                rating2.setImageResource(R.drawable.bintang_unyu);
                rating3.setImageResource(R.drawable.bintang_unyu);
                rating4.setImageResource(R.drawable.ic_bintangabu);
                rating5.setImageResource(R.drawable.ic_bintangabu);
                total_rating = 3;
            }
        });

        rating4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating1.setImageResource(R.drawable.bintang_unyu);
                rating2.setImageResource(R.drawable.bintang_unyu);
                rating3.setImageResource(R.drawable.bintang_unyu);
                rating4.setImageResource(R.drawable.bintang_unyu);
                rating5.setImageResource(R.drawable.ic_bintangabu);
                total_rating = 4;
            }
        });

        rating5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating1.setImageResource(R.drawable.bintang_unyu);
                rating2.setImageResource(R.drawable.bintang_unyu);
                rating3.setImageResource(R.drawable.bintang_unyu);
                rating4.setImageResource(R.drawable.bintang_unyu);
                rating5.setImageResource(R.drawable.bintang_unyu);
                total_rating = 5;
            }
        });
    }

    private class SnackbarCallback extends Snackbar.Callback {

        @Override
        public void onDismissed(Snackbar transientBottomBar, int event) {
            super.onDismissed(transientBottomBar, event);
            finish();
        }
    }
}