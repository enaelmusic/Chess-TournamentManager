package com.example.swisstournament2.Activity;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerSwissDTO;
import com.example.swisstournament2.Model.PlayerTournois;
import com.example.swisstournament2.Model.Tournois;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.PlayerApi;
import com.example.swisstournament2.Retrofit.Apis.PlayerTournoisApi;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;
import com.example.swisstournament2.adapter.ActionJoueurTournois;
import com.example.swisstournament2.adapter.ModeView;
import com.example.swisstournament2.adapter.PlayerAdapter;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreerNouveauxTournois extends AppCompatActivity {
    private RecyclerView recycleView;
    private Logger log = Logger.getLogger("CREATION TOURNOIS");
    private Retrofit retrofit = RetrofitService.getRetrofit();
    private TournoisApi tournoisApi = retrofit.create(TournoisApi.class);
    private PlayerApi playerApi = retrofit.create(PlayerApi.class);
    private Tournois nvxTournois = new Tournois();
    private PlayerSwiss playerHandler;
    private PlayerTournoisApi playerTournoisApi = retrofit.create(PlayerTournoisApi.class);
    private int idTournois;
    private Button startTournois ;
    private Button ajouterJoueur ;
    private EditText playerName ;
    private PlayerAdapter playerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        creerTournois();
        setContentView(R.layout.creer_nvx_tournois);
        initialiseNouveauxTournois();
    }

    public void creerTournois() {
        tournoisApi.postTournois(nvxTournois)
                .enqueue(new Callback<Tournois>() {
                    @Override
                    public void onResponse(Call<Tournois> call, Response<Tournois> response) {
                        if(response.isSuccessful() & response.body() !=null){
                            Toast.makeText(CreerNouveauxTournois.this, "Tournois Creer", Toast.LENGTH_SHORT).show();
                            nvxTournois = response.body();
                            idTournois = nvxTournois.getId();
                            if(ajouterJoueur != null){ajouterJoueur.setEnabled(true);}
                            if(startTournois != null){startTournois.setEnabled(true);}
                        }
                        else{
                            Log.e("creerTournoisError","response = "+response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Tournois> call, Throwable t) {
                        Toast.makeText(CreerNouveauxTournois.this, "ce nom de tournois existe deja", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(CreerNouveauxTournois.class.getName()).log(Level.WARNING, "error creer tournois :", t);
                    }
                });
    }

    private void initialiseNouveauxTournois() {
        EditText nameTournois = findViewById(R.id.nomTournoisInput);
        Spinner cadenceSpinner = findViewById(R.id.cadenceInput);
        EditText nbrManche = findViewById(R.id.inputNbrManche);
        playerName = findViewById(R.id.label4);
        startTournois = findViewById(R.id.startTournoisBtn);
        ajouterJoueur = findViewById(R.id.addPlayerBtn);
        startTournois.setEnabled(false);
        ajouterJoueur.setEnabled(false);
        recycleView = findViewById(R.id.PlayerList);

        String[] cadences = getResources().getStringArray(R.array.spinner_tournois_cadence);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cadences);
        cadenceSpinner.setAdapter(arrayAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        ajouterJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePlyerString = playerName.getText().toString();
                PlayerSwiss playerRequest = new PlayerSwiss();
                playerRequest.setNom(namePlyerString);
                playerApi.sauverJoueur(playerRequest)
                        .enqueue(new Callback<PlayerSwiss>() {
                            @Override
                            public void onResponse(Call<PlayerSwiss> call, Response<PlayerSwiss> response) {
                                responseSavePlayer(response);
                                playerName.setText("");
                            }

                            @Override
                            public void onFailure(Call<PlayerSwiss> call, Throwable t) {
                                Logger.getLogger(CreerNouveauxTournois.class.getName()).log(Level.WARNING, "error creer tournois :", t);
                            }
                        });
                loadJoueur();
            }
        });
        startTournois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cadenceString = cadenceSpinner.getSelectedItem().toString();
                String nameString = nameTournois.getText().toString();
                String nbrMancheMaxString = nbrManche.getText().toString();
                if(nameString.isEmpty()){
                    Toast.makeText(CreerNouveauxTournois.this, "Saisir un nom de tournois", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cadenceString.isEmpty()){
                    Toast.makeText(CreerNouveauxTournois.this, "Saisir une cadence", Toast.LENGTH_LONG).show();
                    return;
                }
                if(nbrMancheMaxString.isEmpty()){
                    Toast.makeText(CreerNouveauxTournois.this, "Le nombre de manche doit etre entre 2 et 20", Toast.LENGTH_SHORT).show();
                    return;
                }
                int nbrMancheMaxInt = Integer.parseInt(nbrMancheMaxString);
                if(nbrMancheMaxInt<2 || nbrMancheMaxInt>20){
                    Toast.makeText(CreerNouveauxTournois.this, "Le nombre de manche doit etre entre 2 et 20", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(playerAdapter==null || playerAdapter.getItemCount()<3){
                    Toast.makeText(CreerNouveauxTournois.this, "Saisir au moins 3 participants", Toast.LENGTH_SHORT).show();
                    return;
                }
                nvxTournois.setName(nameString);
                nvxTournois.setCadence(cadenceString);
                nvxTournois.setStatusTournois(1);
                nvxTournois.setNbr_manche(nbrMancheMaxInt);
                tournoisApi.postTournois(nvxTournois)
                        .enqueue(new Callback<Tournois>() {
                            @Override
                            public void onResponse(Call<Tournois> call, Response<Tournois> response) {
                                Tournois tournoisCreer= response.body();
                                tournoisApi.startTournois(tournoisCreer)
                                        .enqueue(new Callback<TreeSet<MatchSwiss>>() {
                                            @Override
                                            public void onResponse(Call<TreeSet<MatchSwiss>> call, Response<TreeSet<MatchSwiss>> response) {
                                                Intent intent = new Intent(CreerNouveauxTournois.this, MatchEncourMancheX.class);

                                                Log.i("Response RECU->", "body =" + response.body().toString());
                                                ArrayList<MatchSwiss> matchList = new ArrayList<>(response.body());
                                                Log.i("First Array RECU->", "First =" + matchList.get(0).getNom_black());
                                                intent.putExtra("MatchList",matchList);
                                                intent.putExtra("IDTOURNOIS",tournoisCreer.getId());
                                                intent.putExtra("numManche",response.headers().get("numManche"));
                                                intent.putExtra("mancheMax",response.headers().get("mancheMax"));
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void onFailure(Call<TreeSet<MatchSwiss>> call, Throwable t) {

                                            }
                                        });
                                ;
                            }

                            @Override
                            public void onFailure(Call<Tournois> call, Throwable t) {
                                Toast.makeText(CreerNouveauxTournois.this, "ce nom de tournois existe deja", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(CreerNouveauxTournois.class.getName()).log(Level.WARNING, "error creer tournois :", t);
                            }
                        });
                ;
            }
        });
    }

    private void responseSavePlayer(Response<PlayerSwiss> response) {
        if (response.isSuccessful() && response.body() != null) {
            playerHandler = response.body();
            Log.i("JOUEURSAUVER->", "player id =" + playerHandler.getId());
            PlayerTournois playerTournoisRequest = new PlayerTournois();
            playerTournoisRequest.setIdPlayer(playerHandler.getId());
            playerTournoisRequest.setIdTournois(idTournois);
            tournoisApi.postJoueurTournois(playerTournoisRequest)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call2, Response<Void> response2) {
                            buildLoadJoueur(response2);
                        }

                        @Override
                        public void onFailure(Call<Void> call2, Throwable t2) {
                            Toast.makeText(CreerNouveauxTournois.this, "Sauvegarde Joueur Echouer", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(CreerNouveauxTournois.class.getName()).log(Level.WARNING, "error creer tournois :", t2);
                        }
                    });
        } else {
            Log.e("APIERROR", "code: " + response.code());
            try {
                Log.e("APIERROR", "code: " + response.errorBody().string());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void buildLoadJoueur(Response<Void> response2) {
        if (response2.isSuccessful()) {
            loadJoueur();
        } else {
            Log.e("APIpostJoueurTournoisERROR", "code: " + response2.code());
            try {
                Log.e("APIpostJoueurTournoisERROR", "code: " + response2.errorBody().string());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void loadJoueur() {
        tournoisApi.getPlayerTournois(nvxTournois.getId())
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
                        Toast.makeText(CreerNouveauxTournois.this, "Failed to load Player", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(CreerNouveauxTournois.class.getName()).log(Level.WARNING, "error chargement player :", t);
                    }
                });
    }
    private void remplirListJoueurTournois(TreeSet<PlayerSwissDTO> body) {
        if(playerAdapter==null){
            playerAdapter = new PlayerAdapter(body, new ActionJoueurTournois() {
                @Override
                public void modifierJoueurT(PlayerSwissDTO player) {
                    Log.i("BTN MODIFER", "Je commence la modifiaction");
                }
                @Override
                public void validerModifPlayerT(PlayerSwissDTO player){
                    Log.i("PlayerUpdatedName", "name: " + player.getNom());
                    playerTournoisApi.modifierParticipant( player)
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.isSuccessful()) {
                                        Log.i("modifDone", "code: " + response.code());
                                        loadJoueur();
                                        playerName.requestFocus();
                                    }
                                    else{
                                        Log.e("modifError", "code: " + response.code());
                                        try {
                                            Log.e("modifErroString", "code: " + response.errorBody().string());
                                        }
                                        catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                }
                @Override
                public void supprimerPlayerT(PlayerSwissDTO player) {
                    Log.i("BTN SUPP CLICK","je commence la suppression");
                    playerTournoisApi.supprimerParticipant(player.getId())
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    loadJoueur();
                                    Log.i("SupprimerCLIcl","supprimer participant, fait");
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    t.printStackTrace();
                                    log.warning("probleme click supprimer"+t.getMessage());
                                }
                            });
                    Log.i("BTN SUPP CLICK","je prepare le loading");
                }

                @Override
                public void joueurOut(PlayerSwissDTO player) {

                }
            }, ModeView.INSCRIPTION.getTyni());
            recycleView.setAdapter(playerAdapter);

        }else{
            playerAdapter.setItem(body);
        }
    }
}
