package com.example.swisstournament2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.R;

public class PlayerHolder extends RecyclerView.ViewHolder {
    private TextView namePlayer , ratingPlayer;

    public PlayerHolder(@NonNull View itemView) {
        super(itemView);
        namePlayer= itemView.findViewById(R.id.PlayerTournoisName);
        ratingPlayer= itemView.findViewById(R.id.PlayerTournoisRating);
    }

    public TextView getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(TextView namePlayer) {
        this.namePlayer = namePlayer;
    }

    public TextView getRatingPlayer() {
        return ratingPlayer;
    }

    public void setRatingPlayer(TextView ratingPlayer) {
        this.ratingPlayer = ratingPlayer;
    }
}
