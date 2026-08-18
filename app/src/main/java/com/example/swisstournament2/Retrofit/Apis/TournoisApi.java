package com.example.swisstournament2.Retrofit.Apis;

import com.example.swisstournament2.Model.MancheSwiss;
import com.example.swisstournament2.Model.MatchSwiss;
import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerTournois;
import com.example.swisstournament2.Model.Tournois;

import java.util.ArrayList;
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
    @POST("/starttournois")
    Call<TreeSet<MatchSwiss>> startTournois(@Body Tournois tournois);

    @GET("/getManchesTournois/{idTournois}")
    Call<ArrayList<MancheSwiss>> getMancheTournois(@Path("idTournois") String idTournois);

    @GET("/getMatchByManche/{num_manche}")
    Call<TreeSet<MatchSwiss>> getMatchByManche(@Path("num_manche") String num_manche);
}
