package com.example.swisstournament2.adapter;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.R;

public class PlayerHolder extends RecyclerView.ViewHolder {
    private TextView namePlayer ;
    private EditText namePlayerModifier;
    private ImageButton modifierJoueur, supprimerJoueur, validerModifName;

    public PlayerHolder(@NonNull View itemView) {
        super(itemView);
        namePlayer= itemView.findViewById(R.id.PlayerTournoisName);
        namePlayerModifier=itemView.findViewById(R.id.PlayerEditName);
        modifierJoueur= itemView.findViewById(R.id.modifierPlayerT);
        supprimerJoueur= itemView.findViewById(R.id.supprimerPlayerT);
        validerModifName= itemView.findViewById(R.id.validerModif);
    }

    public TextView getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(TextView namePlayer) {
        this.namePlayer = namePlayer;
    }

    public ImageButton getValiderModifName() {
        return validerModifName;
    }

    public void setValiderModifName(ImageButton validerModifName) {
        this.validerModifName = validerModifName;
    }

    public EditText getNamePlayerModifier() {
        return namePlayerModifier;
    }

    public void setNamePlayerModifier(EditText namePlayerModifier) {
        this.namePlayerModifier = namePlayerModifier;
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
