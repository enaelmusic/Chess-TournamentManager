package com.example.swisstournament2.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.Enums.ParticipantEnum;
import com.example.swisstournament2.Model.PlayerSwissDTO;
import com.example.swisstournament2.Model.PlayerTournois;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.adapter.ActionJoueurTournois;
import com.example.swisstournament2.adapter.ModeView;
import com.example.swisstournament2.adapter.PlayerAdapter;

import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ParticipantsActivity extends Activity {
    private RecyclerView recyclerView;
    private Retrofit retrofit = RetrofitService.getRetrofit();
    private TournoisApi tournoisApi= retrofit.create(TournoisApi.class);
    private String idTournoisString;
    int idTournois;
    private PlayerAdapter playerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant_activity);
        init();
    }
    private void init(){
        idTournoisString = this.getIntent().getExtras().getString("IDTOURNOIS");
        if (idTournoisString != null) {
            idTournois= Integer.parseInt(idTournoisString);
        }
        initRecycleView();
        loadJoueur();
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.PlayerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void loadJoueur() {
        tournoisApi.getPlayerTournois(idTournois)
                .enqueue(new Callback<TreeSet<PlayerSwissDTO>>() {
                    @Override
                    public void onResponse(Call<TreeSet<PlayerSwissDTO>> call, Response<TreeSet<PlayerSwissDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            remplirListJoueurTournois(response.body());
                        } else {
                            Log.e("LISTERROR", "code: " + response.code());
                            try {
                                Log.e("LISTERROR", "code: " + response.errorBody().string());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TreeSet<PlayerSwissDTO>> call, Throwable t) {
                        Toast.makeText(ParticipantsActivity.this, "Failed to load Player", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(ParticipantsActivity.class.getName()).log(Level.WARNING, "error chargement player :", t);
                    }
                });
    }
    private void remplirListJoueurTournois(TreeSet<PlayerSwissDTO> body) {
        if(playerAdapter==null){
            playerAdapter = new PlayerAdapter(body, new ActionJoueurTournois() {
                @Override
                public void modifierJoueurT(PlayerSwissDTO player) {
                }
                @Override
                public void validerModifPlayerT(PlayerSwissDTO player){
                }
                @Override
                public void supprimerPlayerT(PlayerSwissDTO player) {

                }

                @Override
                public void joueurOut(PlayerSwissDTO player) {
                    Log.i("playerDTO","le status de participant est ; "+player.getStatusTournois());
                    new AlertDialog.Builder(ParticipantsActivity.this)
                            .setMessage(getString(R.string.confirmationOut))
                            .setPositiveButton(getString(R.string.ok),(dialog, which) -> {
                                PlayerTournois p = new PlayerTournois();
                                p.setIdTournois(idTournois);
                                p.setIdPlayer(player.getId());
                                tournoisApi.postJoueurOut(p)
                                        .enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if(response.isSuccessful()){
                                                    Log.i("Player OUT","PlayerOut envoyé "+response.code());
                                                    loadJoueur();

                                                }
                                                else{
                                                    Log.i("Player OUT","PlayerOut non envoyé "+response.code());

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {

                                            }
                                        });
                            })
                            .setNegativeButton("Annuler",null)
                            .show();

                }
            }, ModeView.TOURNOISENCOUR.getTyni());
            recyclerView.setAdapter(playerAdapter);

            Log.i("player ADapter","recycleViewSetAdptater fait");

        }else{
            playerAdapter.setItem(body);
        }
    }

}
