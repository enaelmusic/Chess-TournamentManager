package com.example.swisstournament2.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.R;

public class PlayerHolder extends RecyclerView.ViewHolder {
    private TextView namePlayer ;
    private ImageButton modifierJoueur, supprimerJoueur;

    public PlayerHolder(@NonNull View itemView) {
        super(itemView);
        namePlayer= itemView.findViewById(R.id.PlayerTournoisName);
        modifierJoueur= itemView.findViewById(R.id.modifierPlayerT);
        supprimerJoueur= itemView.findViewById(R.id.supprimerPlayerT);
    }

    public TextView getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(TextView namePlayer) {
        this.namePlayer = namePlayer;
    }

    public ImageButton getModifierJoueur() {
        return modifierJoueur;
    }

    public void setModifierJoueur(ImageButton ratingPlayer) {
        this.modifierJoueur = ratingPlayer;
    }

    public ImageButton getSupprimerJoueur() {
        return supprimerJoueur;
    }

    public void setSupprimerJoueur(ImageButton supprimerJoueur) {
        this.supprimerJoueur = supprimerJoueur;
    }
}
