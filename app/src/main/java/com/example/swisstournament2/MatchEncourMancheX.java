package com.example.swisstournament2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.adapter.MatchSwissAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchEncourMancheX extends AppCompatActivity {
    private RecyclerView recyclerView;


    ArrayList<MatchSwiss> matchArray ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_en_cours_manchex);
        ArrayList<MatchSwiss> matchSwisses= (ArrayList<MatchSwiss>) getIntent().getSerializableExtra("MatchList");
        Log.d("Match pour MATCH ->",matchSwisses.get(0).getNom_black());
        recyclerView = findViewById(R.id.matchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadMatches(matchSwisses);

    }
    private void loadMatches(ArrayList<MatchSwiss> matchSet) {
        MatchSwissAdapter matchAdapter = new MatchSwissAdapter(matchSet);
        recyclerView.setAdapter(matchAdapter);
    }
}
