package com.example.swisstournament2.Activity;

import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MancheSwiss;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.adapter.MancheAdapter;
import com.example.swisstournament2.adapter.MancheClickSurEcoute;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ManchesTournois extends Activity {
    private Retrofit retrofit = RetrofitService.getRetrofit();
    private TournoisApi tournoisApi= retrofit.create(TournoisApi.class);
    private MancheAdapter mancheAdapter;
    private RecyclerView recyclerView ;
    private String idTournois ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manche_tournois);
        initializeView();
        idTournois = getIntent().getExtras().getString("IDTOURNOIS");
        initRecycleView();
        tournoisApi.getMancheTournois(idTournois)
                .enqueue(new Callback<ArrayList<MancheSwiss>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MancheSwiss>> call, Response<ArrayList<MancheSwiss>> response) {
                        if(response.body()!=null){
                            ArrayList<MancheSwiss> m = response.body();
                            loadManche(m);
                        }
                        else{
                            Log.d("Erreur chargment Manche tournois",response.code()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MancheSwiss>> call, Throwable t) {

                    }
                });
    }

    private void initializeView() {
        Button homebtn = findViewById(R.id.HomeButton);
        Button participantsListButton = findViewById(R.id.participantsList);
        participantsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManchesTournois.this, ParticipantsActivity.class);
                intent.putExtra("IDTOURNOIS",idTournois);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManchesTournois.this,MainActivity.class);
                intent.putExtra("IDTOURNOIS",idTournois);
                startActivity(intent);
            }
        });
    }
    private void initRecycleView() {
        recyclerView = findViewById(R.id.MancheTournoisList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void loadManche(ArrayList<MancheSwiss> mancheSwisses){
        mancheAdapter = new MancheAdapter(mancheSwisses, new MancheClickSurEcoute() {
            @Override
            public void MancheOk(MancheSwiss m) {
                Intent intent= new Intent(ManchesTournois.this,MatchEncourMancheX.class);
                intent.putExtra("numMancheUnique",m.getNum_manche());
                intent.putExtra("numManche",m.getInt_manche());
                intent.putExtra("mancheStatus",m.getStatus()+"");
                startActivity(intent);
            }

            @Override
            public void MancheNonDemarrer() {
                TextView tv = findViewById(R.id.errueurManche);
                tv.setVisibility(VISIBLE);
                Log.d("manche click error","manche n'ayant pas le statut en cours ou fini");

            }
        });
        recyclerView.setAdapter(mancheAdapter);
    }
}
