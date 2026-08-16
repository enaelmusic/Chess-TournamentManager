package com.example.swisstournament2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.R;

public class MancheHolder extends RecyclerView.ViewHolder {
    private TextView mancheNam;


    public MancheHolder(@NonNull View itemView) {
        super(itemView);
        mancheNam = itemView.findViewById(R.id.mancheName);
    }

    public TextView getMancheNam() {
        return mancheNam;
    }

    public void setMancheNam(TextView mancheNam) {
        this.mancheNam = mancheNam;
    }
}
