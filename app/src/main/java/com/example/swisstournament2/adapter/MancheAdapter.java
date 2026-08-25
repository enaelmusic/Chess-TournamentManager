package com.example.swisstournament2.adapter;

import static android.view.View.VISIBLE;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Activity.ManchesTournois;
import com.example.swisstournament2.Activity.MatchEncourMancheX;
import com.example.swisstournament2.Model.Enums.MancheStatus;
import com.example.swisstournament2.Model.MancheSwiss;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;
import com.example.swisstournament2.Retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.TreeSet;

import retrofit2.Retrofit;

public class MancheAdapter extends RecyclerView.Adapter<MancheHolder> {
    private final ArrayList<MancheSwiss> mancheTournois;
    private final MancheClickSurEcoute ecoute;

    public MancheAdapter(ArrayList<MancheSwiss> mancheTournois, MancheClickSurEcoute ecoute) {
        this.mancheTournois = mancheTournois;
        this.ecoute = ecoute;
    }

    @NonNull
    @Override
    public MancheHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manche_list, parent, false); //quel design pour chaque ligne
        return new MancheHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MancheHolder holder, int position) {
        MancheSwiss mancheSwiss = mancheTournois.get(position);
        String mancheName = "MANCHE "+mancheSwiss.getInt_manche();
        int mancheIntNum = mancheSwiss.getInt_manche();
        holder.getMancheNam().setText(mancheName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                if(mancheSwiss.getStatus()== MancheStatus.ENCOURS.getTyni() || mancheSwiss.getStatus()==MancheStatus.FINI.getTyni()){
                    ecoute.MancheOk(mancheSwiss);
                }
                else if(mancheSwiss.getStatus()==MancheStatus.ENATTENT.getTyni()){
                    ecoute.MancheNonDemarrer();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mancheTournois.size();
    }
}
