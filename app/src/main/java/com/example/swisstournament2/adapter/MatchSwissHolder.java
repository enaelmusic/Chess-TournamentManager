package com.example.swisstournament2.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.R;

public class MatchSwissHolder extends RecyclerView.ViewHolder{
    private RadioGroup matchGroupBtn ;
    private RadioButton playerW,playerB, nullBtn;
    private String numMatch;
    public MatchSwissHolder(@NonNull View itemView) {
        super(itemView);
        matchGroupBtn = itemView.findViewById(R.id.matchGroupBtn);
        playerW = itemView.findViewById(R.id.PlayerWhiteWin);
        playerB = itemView.findViewById(R.id.PlayerBlackWin);
        nullBtn = itemView.findViewById(R.id.drawResult);
    }
    public RadioButton getPlayerW() {
        return playerW;
    }
    public void setPlayerW(RadioButton playerW) {
        this.playerW = playerW;
    }
    public RadioButton getPlayerB() {
        return playerB;
    }
    public void setPlayerB(RadioButton playerB) {
        this.playerB = playerB;
    }
    public RadioButton getNullBtn() {
        return nullBtn;
    }
    public void setNullBtn(RadioButton nullBtn) {
        this.nullBtn = nullBtn;
    }
    public RadioGroup getMatchGroupBtn() {
        return matchGroupBtn;
    }
    public void setMatchGroupBtn(RadioGroup matchGroupBtn) {
        this.matchGroupBtn = matchGroupBtn;
    }
    public String getNumMatch() {
        return numMatch;
    }
    public void setNumMatch(String numMatch) {
        this.numMatch = numMatch;
    }
}
