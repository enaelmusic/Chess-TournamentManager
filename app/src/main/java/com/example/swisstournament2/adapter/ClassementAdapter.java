package com.example.swisstournament2.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swisstournament2.Model.ClassementDTO;
import com.example.swisstournament2.Model.ClassementTournois;
import com.example.swisstournament2.R;
import com.example.swisstournament2.Retrofit.PlayerApi;
import com.example.swisstournament2.Retrofit.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClassementAdapter extends RecyclerView.Adapter<ClassementHolder> {
    private final ArrayList<ClassementDTO> classementTournois;
    private final Retrofit retrofit = RetrofitService.getRetrofit();
    private final PlayerApi playerApi = retrofit.create(PlayerApi.class);

    public ClassementAdapter(ArrayList<ClassementDTO> c) {
        this.classementTournois = c;
    }

    @NonNull
    @Override
    public ClassementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_classement, parent, false); //quel design pour chaque ligne
        return new ClassementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassementHolder holder, int position) {
        Log.i("nom joueur", classementTournois.get(position).getIdPlayer()+"");
        holder.getPlayerName().setText(classementTournois.get(position).getNom());
        holder.getPointPlayer().setText(String.valueOf(classementTournois.get(position).getPoint()));
        holder.getNumeroClassement().setText(String.valueOf(position + 1));
//        int id = classementTournois.get(position).getIdPlayer();
//        playerApi.getNamePlayer(id).
//                enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        holder.getPlayerName().setText(response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        Log.d("playerApi", "playerApi a echouer",t);
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return classementTournois.size();
    }
}
