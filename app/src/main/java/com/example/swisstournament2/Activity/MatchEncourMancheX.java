package com.example.swisstournament2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.ManchesApi;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.adapter.MatchSwissAdapter;

import java.util.ArrayList;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MatchEncourMancheX extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Retrofit retrofit =  RetrofitService.getRetrofit();
    private ManchesApi manchesApi= retrofit.create(ManchesApi.class);
    private MatchSwissAdapter matchAdapter;
    private TournoisApi tournoisApi = retrofit.create(TournoisApi.class);
    private ArrayList<MatchSwiss> matchSwisses;
    private String idTournois, numMancheInt, mancheMaxInt;
    ArrayList<MatchSwiss> matchArray ;
    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        return super.getOnBackInvokedDispatcher();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_en_cours_manchex);
        initRecycleView();
        if(getIntent().hasExtra("MatchList")){
            matchSwisses= (ArrayList<MatchSwiss>) getIntent().getSerializableExtra("MatchList");
            idTournois = getIntent().getExtras().getInt("IDTOURNOIS")+"";
            numMancheInt = getIntent().getExtras().getString("numManche");
            mancheMaxInt = getIntent().getExtras().getString("mancheMax");
            Log.d("Match pour MATCH ->",matchSwisses.get(0).getNom_black());
            loadMatches(matchSwisses);
            initialiserVue();
        }
        else{
            tournoisApi.getMatchByManche(getIntent().getExtras().getString("numMancheUnique")).
                    enqueue(new Callback<TreeSet<MatchSwiss>>() {
                        @Override
                        public void onResponse(Call<TreeSet<MatchSwiss>> call, Response<TreeSet<MatchSwiss>> response) {
                            if(response.body() != null){
                                idTournois   = response.headers().get("IDTOURNOIS");
                                numMancheInt = response.headers().get("numManche");
                                mancheMaxInt = response.headers().get("mancheMax");
                                Log.d("info GetMancheByNumManche -> ","idTournois : "+idTournois+"num: " + numMancheInt+"Max:"+mancheMaxInt);
                                loadMatchesSet(response.body());
                                initialiserVue();
                            }
                            else{
                                Log.d("nullBody","le body est null");
                            }
                        }

                        @Override
                        public void onFailure(Call<TreeSet<MatchSwiss>> call, Throwable t) {
                            Log.d("Erreur chargement match","la recuperation des match a echouer");
                        }
                    });
        }
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(MatchEncourMancheX.this,ManchesTournois.class);
                intent.putExtra("IDTOURNOIS", idTournois);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initialiserVue() {
        Button nextManche = findViewById(R.id.NextRoundBTN);
        TextView numManche = findViewById(R.id.numManche);
        String textSetter = numMancheInt+"/"+mancheMaxInt;
        numManche.setText(textSetter);
        Log.d("info initialView -> ","idTournois : "+idTournois+"num: " + numMancheInt+"Max:"+mancheMaxInt);
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
                                        Intent intent2 = new Intent(MatchEncourMancheX.this, ClassementActivity.class);
                                        intent2.putExtra("IDTOURNOIS",idTournois);
                                        startActivity(intent2);
                                    }
                                    else {
                                        loadMatches(new ArrayList<MatchSwiss>(response.body()));
                                        numMancheInt =response.headers().get("numManche");
                                        mancheMaxInt = response.headers().get("mancheMax");
                                        numManche.setText(numMancheInt+"/"+mancheMaxInt);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<TreeSet<MatchSwiss>> call, Throwable t) {
                                Log.e("erreur clickNextMance ->",t.toString());

                            }
                        });
            }
        });
    }
    private void initRecycleView() {
        recyclerView = findViewById(R.id.matchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void loadMatches(ArrayList<MatchSwiss> matchSet) {
        matchAdapter = new MatchSwissAdapter(matchSet);
        recyclerView.setAdapter(matchAdapter);
    }
    private void loadMatchesSet(TreeSet<MatchSwiss> matchSet) {
        matchAdapter = new MatchSwissAdapter(matchSet);
        recyclerView.setAdapter(matchAdapter);
    }
}
