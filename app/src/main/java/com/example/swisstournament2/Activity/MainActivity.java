package com.example.swisstournament2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;

import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button creerTournois = findViewById(R.id.creer_nvx_tournois);
        Retrofit retrofit = RetrofitService.getRetrofit();
        TournoisApi tournoisApi = retrofit.create(TournoisApi.class);
        creerTournois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNvxTournois = new Intent(MainActivity.this, CreerNouveauxTournois.class);
                startActivity(intentNvxTournois);
            }
        });
    }
}