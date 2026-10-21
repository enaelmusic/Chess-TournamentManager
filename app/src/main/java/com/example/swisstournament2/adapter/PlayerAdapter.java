package com.example.swisstournament2.adapter;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import static com.example.swisstournament2.R.color.gray_edit;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Activity.ConfirmationPlayerOut;
import com.example.swisstournament2.Activity.ParticipantsActivity;
import com.example.swisstournament2.Model.Enums.ParticipantEnum;
import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerSwissDTO;
import com.example.swisstournament2.Model.PlayerTournois;
import com.example.swisstournament2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {
    private final List<PlayerSwissDTO> mapPlayer;
    private final int modeView;
    private final ActionJoueurTournois actionParticipant;

    public PlayerAdapter(TreeSet<PlayerSwissDTO> mapPlayer, ActionJoueurTournois actionParticipant, int modeView){
        this.mapPlayer=new ArrayList<>(mapPlayer);
        this.actionParticipant = actionParticipant;
        this.modeView=modeView;
    }

    public void setItem(TreeSet<PlayerSwissDTO> mapPlayer){
        this.mapPlayer.clear();
        this.mapPlayer.addAll(mapPlayer);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.joueurtournois_item, parent, false); //quel design pour chaque ligne
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        PlayerSwissDTO player= mapPlayer.get(position);
        holder.getNamePlayer().setText(player.getNom());

        if(modeView==ModeView.TOURNOISENCOUR.getTyni()){
            if(player.getStatusTournois()==ParticipantEnum.PARTICIPEPLUS.getTyni()) {
                holder.itemView.setBackgroundResource(R.color.gray_edit);
                holder.getNamePlayer().setClickable(false);
                holder.getJoueurExitBtn().setVisibility(INVISIBLE);
                holder.getValiderModifName().setVisibility(VISIBLE);
            }
            else{
                holder.getSupprimerJoueur().setVisibility(INVISIBLE);
                holder.getModifierJoueur().setVisibility(INVISIBLE);
                holder.getJoueurExitBtn().setVisibility(VISIBLE);
            }
        }
        else{
            holder.getSupprimerJoueur().setVisibility(VISIBLE);
            holder.getModifierJoueur().setVisibility(VISIBLE);
            holder.getJoueurExitBtn().setVisibility(INVISIBLE);
        }
        holder.getModifierJoueur().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionParticipant.modifierJoueurT(player);
                holder.getSupprimerJoueur().setVisibility(INVISIBLE);
                holder.getModifierJoueur().setVisibility(INVISIBLE);
                holder.getValiderModifName().setVisibility(VISIBLE);
                holder.getNamePlayer().setVisibility(INVISIBLE);
                holder.getNamePlayerModifier().setVisibility(VISIBLE);
                holder.getNamePlayerModifier().requestFocus();
            }
        });
        holder.getValiderModifName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getSupprimerJoueur().setVisibility(VISIBLE);
                holder.getModifierJoueur().setVisibility(VISIBLE);
                holder.getValiderModifName().setVisibility(INVISIBLE);
                holder.getNamePlayer().setVisibility(VISIBLE);
                holder.getNamePlayerModifier().setVisibility(INVISIBLE);
                player.setNom(holder.getNamePlayerModifier().getText().toString());
                actionParticipant.validerModifPlayerT(player);
            }
        });
        holder.getSupprimerJoueur().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionParticipant.supprimerPlayerT(player);
            }
        });
        holder.getJoueurExitBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actionParticipant.joueurOut(player);

            }
        });
    }

    public void setButtonOut(@NonNull PlayerHolder holder){

    }
    @Override
    public int getItemCount() {
        return mapPlayer.size();
    }
}
