package com.example.swisstournament2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.R;

public class ClassementHolder extends RecyclerView.ViewHolder {
    private TextView numeroClassement, playerName, pointPlayer;
    public ClassementHolder(@NonNull View itemView) {
        super(itemView);
        playerName = itemView.findViewById(R.id.nomJoueurClassement);
        pointPlayer = itemView.findViewById(R.id.pointJoueurClassement);
        numeroClassement=itemView.findViewById(R.id.numeroClassement);

    }

    public TextView getNumeroClassement() {
        return numeroClassement;
    }

    public void setNumeroClassement(TextView numeroClassement) {
        this.numeroClassement = numeroClassement;
    }

    public TextView getPointPlayer() {
        return pointPlayer;
    }

    public void setPointPlayer(TextView pointPlayer) {
        this.pointPlayer = pointPlayer;
    }

    public TextView getPlayerName() {
        return playerName;
    }

    public void setPlayerName(TextView playerName) {
        this.playerName = playerName;
    }
}
