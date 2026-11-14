package com.example.swisstournament2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerSwissDTO;
import com.example.swisstournament2.Model.Tournois;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.PlayerApi;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;
import com.example.swisstournament2.Retrofit.RetrofitService;
import com.example.swisstournament2.adapter.ActionJoueurTournois;
import com.example.swisstournament2.adapter.ModeView;
import com.example.swisstournament2.adapter.PlayerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class CreerNouveauxTournois2 extends AppCompatActivity {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private RecyclerView recycleView;
    private final Retrofit retrofit = RetrofitService.getRetrofit();
    private final TournoisApi tournoisApi = retrofit.create(TournoisApi.class);
    private final PlayerApi playerApi = retrofit.create(PlayerApi.class);
    private final Tournois nvxTournois = new Tournois();
    private Tournois tournoisFromPOSTReturn = new Tournois();
    private Button startTournois ;
    private Button ajouterJoueur ;
    private EditText playerName ;
    private PlayerAdapter playerAdapter;
    private final TreeSet<PlayerSwissDTO> playerSwisses = new TreeSet<>();
    private int nbrMancheMaxInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.creer_nvx_tournois);
        initialiseNouveauxTournois();
    }
    private void initialiseNouveauxTournois() {
        EditText nameTournois = findViewById(R.id.nomTournoisInput);
        Spinner cadenceSpinner = findViewById(R.id.cadenceInput);
        EditText nbrManche = findViewById(R.id.inputNbrManche);
        configView(cadenceSpinner);
        ajouterJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipant();
            }
        });
        if(nameTournois.getText()!=null && nbrManche.getText()!=null ){
            startTournois.setEnabled(true);
        }
        startTournois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cadenceString = cadenceSpinner.getSelectedItem().toString();
                String nameString = nameTournois.getText().toString();
                String nbrMancheMaxString = nbrManche.getText().toString();
                if (peutCommencer(nameString, cadenceString, nbrMancheMaxString)) {
                    demarrerTournois(nameString, cadenceString, nbrMancheMaxInt);
                }
            }
        });
    }

    private void configView(Spinner cadenceSpinner) {
        playerName = findViewById(R.id.label4);
        startTournois = findViewById(R.id.startTournoisBtn);
        ajouterJoueur = findViewById(R.id.addPlayerBtn);
        startTournois.setEnabled(false);
        ajouterJoueur.setEnabled(true);
        recycleView = findViewById(R.id.PlayerList);
        String[] cadences = getResources().getStringArray(R.array.spinner_tournois_cadence);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cadences);
        cadenceSpinner.setAdapter(arrayAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void addParticipant() {
        String namePlyerString = playerName.getText().toString();
        PlayerSwissDTO playerRequest = new PlayerSwissDTO(namePlyerString);
        playerSwisses.add(playerRequest);
        playerName.setText("");
        loadJoueur();
    }
    private void demarrerTournois(String nameString, String cadenceString, int nbrMancheMaxInt) {

        startTournois.setEnabled(false);
        executor.execute(new Runnable(){
            @Override
            public void run() {
                int idTournois = postTournoisRec(nameString, cadenceString, nbrMancheMaxInt);
                postParticipants(idTournois);
                startTournois(idTournois);
            }
        });
    }
    private int postTournoisRec(String nameString, String cadenceString, int nbrMancheMaxInt) {
        int tournoisID = 0;
        nvxTournois.setName(nameString);
        nvxTournois.setCadence(cadenceString);
        nvxTournois.setStatusTournois(1);
        nvxTournois.setNbr_manche(nbrMancheMaxInt);
        try {
            Response<Tournois> response = tournoisApi.postTournois(nvxTournois).execute();
            if(response.isSuccessful()){
                tournoisFromPOSTReturn = response.body();
                Log.d("tournois sauvegardé","tournois a ecrasé : "+response.body().toString());
                tournoisID=tournoisFromPOSTReturn.getId();
            }
        } catch (IOException e) {
            Toast.makeText(CreerNouveauxTournois2.this, "ce nom de tournois existe deja", Toast.LENGTH_SHORT).show();
            Logger.getLogger(CreerNouveauxTournois2.class.getName()).log(Level.WARNING, "error creer tournois :", e);
        }
        return tournoisID;
    }
    private void postParticipants(int idTournois) {
            try {
                Response<Void> playResponse= playerApi.sauverJoueurDTO(playerSwisses, idTournois).execute();
                if(playResponse.isSuccessful()){
                    Log.i("sauvegarde joueurs","sauvegardes effectuees");
                }
                else{
                    Log.e("sauvegardes joueurs","sauvegardes echouese");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    private void startTournois(int idTournois) {
        tournoisApi.startTournois(tournoisFromPOSTReturn)
                .enqueue(new Callback<TreeSet<MatchSwiss>>() {
                    @Override
                    public void onResponse(Call<TreeSet<MatchSwiss>> call, Response<TreeSet<MatchSwiss>> response) {
                        Intent intent = new Intent(CreerNouveauxTournois2.this, MatchEncourMancheX.class);
                        Log.i("Response RECU->", "body =" + response.body().toString());
                        ArrayList<MatchSwiss> matchList = new ArrayList<>(response.body());
                        Log.i("First Array RECU->", "First =" + matchList.get(0).getNom_black());
                        intent.putExtra("MatchList", matchList);
                        intent.putExtra("IDTOURNOIS", idTournois);
                        intent.putExtra("numManche", response.headers().get("numManche"));
                        intent.putExtra("mancheMax", response.headers().get("mancheMax"));
                        startActivity(intent);
                        finish();

                    }
                    @Override
                    public void onFailure(Call<TreeSet<MatchSwiss>> call, Throwable t) {
                        Logger.getLogger(CreerNouveauxTournois2.class.getName()).log(Level.WARNING, "error creer tournois :", t);
                    }
                });
    }
    private boolean peutCommencer(String nameString, String cadenceString, String nbrMancheMaxString) {
        if(nameString.isEmpty()){
            Toast.makeText(CreerNouveauxTournois2.this, "Saisir un nom de tournois", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cadenceString.isEmpty()){
            Toast.makeText(CreerNouveauxTournois2.this, "Saisir une cadence", Toast.LENGTH_LONG).show();
            return false;
        }
        if(nbrMancheMaxString.isEmpty()){
            Toast.makeText(CreerNouveauxTournois2.this, "Le nombre de manche doit etre entre 2 et 20", Toast.LENGTH_SHORT).show();
            return false;
        }
        try{
            nbrMancheMaxInt = Integer.parseInt(nbrMancheMaxString);

        } catch (NumberFormatException e) {
            Toast.makeText(CreerNouveauxTournois2.this, "Le nombre est vide", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nbrMancheMaxInt <2 || nbrMancheMaxInt >20){
            Toast.makeText(CreerNouveauxTournois2.this, "Le nombre de manche doit etre entre 2 et 20", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(playerAdapter==null || playerAdapter.getItemCount()<3){
            Toast.makeText(CreerNouveauxTournois2.this, "Saisir au moins 3 participants", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void loadJoueur() {
        remplirListJoueurTournois(playerSwisses);
    }
    private void remplirListJoueurTournois(TreeSet<PlayerSwissDTO> body) {
        if(playerAdapter==null){
            playerAdapter = new PlayerAdapter(body, new ActionJoueurTournois() {
                /**
                 * permettre la modification des informations du participant
                 * @param player
                 */
                @Override
                public void modifierJoueurT(PlayerSwissDTO player) {
                    Log.i("BTN MODIFER", "Je commence la modifiaction");
                }

                /**
                 * valider la modification d'edition
                 * @param player
                 */
                @Override
                public void validerModifPlayerT(PlayerSwissDTO player){
                    Log.i("PlayerUpdatedName", "name: " + player.getNom());
                    loadJoueur();
                    playerName.requestFocus();
                }
                @Override
                public void supprimerPlayerT(PlayerSwissDTO player) {
                    Log.i("BTN SUPP CLICK","je commence la suppression");
                    playerSwisses.remove(player);
                    Log.i("SupprimerCLIcl","supprimer participant, fait");
                    loadJoueur();
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