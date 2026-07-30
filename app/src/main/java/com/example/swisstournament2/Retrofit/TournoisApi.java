package com.example.swisstournament2.Retrofit;

import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerTournois;
import com.example.swisstournament2.Model.Tournois;

import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TournoisApi {
    @GET("/getTournois")
    Call<TreeSet<Tournois>> getTournois();
    @GET("/playerTournoisList/{id}")
    Call<TreeSet<PlayerSwiss>> getPlayerTournois(@Path("id") int id);

    @POST("/sauverTournois")
    Call<Tournois> postTournois(@Body Tournois tounrois);

    @POST("/sauverJoueurTournois")
    Call<Void> postJoueurTournois(@Body PlayerTournois playerTournois);
}
