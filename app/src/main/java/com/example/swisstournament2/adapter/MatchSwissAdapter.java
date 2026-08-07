package com.example.swisstournament2.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.MatchStatus;
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
        holder.setNumMatch(m.getNum_match());

        holder.getMatchGroupBtn().setOnCheckedChangeListener(null);
        if(m.getStatus()== MatchStatus.WHITE.getTyni()){
            holder.getPlayerW().setSelected(true);
        }
        else if(m.getStatus()== MatchStatus.BLACK.getTyni()){
            holder.getPlayerB().setSelected(true);
        }
        else if(m.getStatus()== MatchStatus.DRAW.getTyni()){
            holder.getNullBtn().setSelected(true);
        }
        else if(m.getStatus()==MatchStatus.DRAW.getTyni()){
            setActiveBtn(holder,false);
        }
        else{
            holder.getMatchGroupBtn().clearCheck();
        }

        holder.getMatchGroupBtn().setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                if(checkedId==R.id.PlayerWhiteWin){
                    Log.i("RadioButton -> ","Blanc gagne selectionner");
                    m.setStatus(MatchStatus.WHITE.getTyni());
                }
                else if(checkedId==R.id.PlayerBlackWin){
                    Log.i("RadioButton -> ","Black gagne selectionner");
                    m.setStatus(MatchStatus.BLACK.getTyni());
                }
                else if(checkedId==R.id.drawResult){
                    Log.i("RadioButton -> ","Null selectionner");
                    m.setStatus(MatchStatus.DRAW.getTyni());
                }
            }
        });
    }
    private void setActiveBtn(MatchSwissHolder holder, boolean active){
        holder.getPlayerB().setEnabled(active);
        holder.getPlayerW().setEnabled(active);
        holder.getNullBtn().setEnabled(active);
    }
    @Override
    public int getItemCount() {
        return matchSet.size();
    }

    public ArrayList<MatchSwiss> getUpdatedMatches(){
        return this.matchSet;
    }

    public void updateMatches(TreeSet<MatchSwiss> matches){
        this.matchSet.clear();
        this.matchSet.addAll(matches);
    }
}
