package com.example.swisstournament2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.Retrofit.ManchesApi;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.Retrofit.TournoisApi;
import com.example.swisstournament2.adapter.MatchSwissAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MatchEncourMancheX extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Retrofit retrofit =  RetrofitService.getRetrofit();
    private ManchesApi manchesApi= retrofit.create(ManchesApi.class);
    private MatchSwissAdapter matchAdapter;
    private ArrayList<MatchSwiss> matchSwisses;


    ArrayList<MatchSwiss> matchArray ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_en_cours_manchex);
        matchSwisses= (ArrayList<MatchSwiss>) getIntent().getSerializableExtra("MatchList");
        Log.d("Match pour MATCH ->",matchSwisses.get(0).getNom_black());
        initRecycleView();
        loadMatches(matchSwisses);
        initialiserVue();
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.matchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initialiserVue() {
        Button nextManche = findViewById(R.id.NextRoundBTN);
        TextView numManche = findViewById(R.id.numManche);
        nextManche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manchesApi.nextManche(new TreeSet<MatchSwiss>(matchAdapter.getUpdatedMatches()))
                        .enqueue(new Callback<TreeSet<MatchSwiss>>() {
                            @Override
                            public void onResponse(Call<TreeSet<MatchSwiss>> call, Response<TreeSet<MatchSwiss>> response) {
                                if(response.isSuccessful()){
                                    if(response.code()==204){
                                        // classement final
                                    }
                                    else {
                                       // matchAdapter.updateMatches(response.body());
                                        loadMatches(new ArrayList<MatchSwiss>(response.body()));
                                        String stringView = response.headers().
                                                get("numManche")+"/"+response.headers().get("mancheMax");
                                        numManche.setText(stringView);
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<TreeSet<MatchSwiss>> call, Throwable t) {

                            }
                        });

            }
        });
    }

    private void loadMatches(ArrayList<MatchSwiss> matchSet) {
        matchAdapter = new MatchSwissAdapter(matchSet);
        recyclerView.setAdapter(matchAdapter);
    }
}
