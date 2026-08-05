package com.example.swisstournament2.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MatchSwissAdapter extends RecyclerView.Adapter<MatchSwissHolder> {
    private final ArrayList<MatchSwiss> matchSet;

    public MatchSwissAdapter(ArrayList<MatchSwiss> matchSet) {
        Log.d("on construi le match adapter ->",matchSet.get(0).getNom_black());
        this.matchSet= matchSet;
    }
    @NonNull
    @Override
    public MatchSwissHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matchlist, parent, false); //quel design pour chaque ligne
        return new MatchSwissHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MatchSwissHolder holder, int position) {
        MatchSwiss m = matchSet.get(position);
        holder.getPlayerW().setText(m.getNom_white());
        holder.getPlayerB().setText(m.getNom_black());
    }
    @Override
    public int getItemCount() {
        return matchSet.size();
    }
}
