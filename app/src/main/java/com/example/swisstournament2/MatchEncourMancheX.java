package com.example.swisstournament2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.adapter.MatchSwissAdapter;

import java.util.ArrayList;
import java.util.TreeSet;

public class MatchEncourMancheX extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_en_cours_manchex);
        loadMatches(getIntent().getExtras().getParcelable("MatchList"));
    }
    private void loadMatches(ArrayList<MatchSwiss> matchSet) {
        MatchSwissAdapter matchAdapter = new MatchSwissAdapter(matchSet);
        recyclerView.setAdapter(matchAdapter);
    }
}
