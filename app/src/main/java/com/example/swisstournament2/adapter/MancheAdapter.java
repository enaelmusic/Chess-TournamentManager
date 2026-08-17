package com.example.swisstournament2.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Activity.MatchEncourMancheX;
import com.example.swisstournament2.Model.MancheSwiss;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.Apis.TournoisApi;
import com.example.swisstournament2.Retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.TreeSet;

import retrofit2.Retrofit;

public class MancheAdapter extends RecyclerView.Adapter<MancheHolder> {
    private final ArrayList<MancheSwiss> mancheTournois;

    public MancheAdapter(ArrayList<MancheSwiss> mancheTournois) {
        this.mancheTournois = mancheTournois;
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
        holder.getMancheNam().setText(mancheName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mancheSwiss.getStatus()==1 || mancheSwiss.getStatus()==2){
                    Context context = v.getContext();
                    Intent intent= new Intent(context, MatchEncourMancheX.class);
                    intent.putExtra("numMancheUnique",mancheSwiss.getNum_manche());
                    context.startActivity(intent);
                }
                else{
                    Log.d("manche click error","manche n'ayant pas le statut en cours ou fini");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mancheTournois.size();
    }
}
