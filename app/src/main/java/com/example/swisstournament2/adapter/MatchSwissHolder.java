package com.example.swisstournament2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.R;

public class MatchSwissHolder extends RecyclerView.ViewHolder{
    private TextView playerW,playerB;
    public MatchSwissHolder(@NonNull View itemView) {
        super(itemView);
        playerW = itemView.findViewById(R.id.PlayerWhiteWin);
        playerB = itemView.findViewById(R.id.PlayerBlackWin);
    }
    public TextView getPlayerW() {
        return playerW;
    }
    public void setPlayerW(TextView playerW) {
        this.playerW = playerW;
    }
    public TextView getPlayerB() {
        return playerB;
    }
    public void setPlayerB(TextView playerB) {
        this.playerB = playerB;
    }
}
