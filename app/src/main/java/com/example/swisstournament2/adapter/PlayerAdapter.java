package com.example.swisstournament2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerSwissDTO;
import com.example.swisstournament2.Model.PlayerTournois;
import com.example.swisstournament2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {
    private final List<PlayerSwissDTO> mapPlayer;
    private final ActionJoueurTournois actionParticipant;

    public PlayerAdapter(TreeSet<PlayerSwissDTO> mapPlayer, ActionJoueurTournois actionParticipant){
        this.mapPlayer=new ArrayList<>(mapPlayer);
        this.actionParticipant = actionParticipant;
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

        holder.getModifierJoueur().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionParticipant.modifierJoueurT(player);
            }
        });
        holder.getSupprimerJoueur().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionParticipant.supprimerPlayerT(player);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mapPlayer.size();
    }
}
