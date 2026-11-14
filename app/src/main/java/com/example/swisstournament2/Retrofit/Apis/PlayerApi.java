package com.example.swisstournament2.Retrofit.Apis;

import com.example.swisstournament2.Model.PlayerSwiss;
import com.example.swisstournament2.Model.PlayerSwissDTO;

import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlayerApi {
    @POST("/sauverPlayerSwiss")
    Call<PlayerSwiss> sauverJoueur(@Body PlayerSwiss playerSwiss);
    @POST("/sauverPlayerSwissDTO/{id}")
    Call<Void> sauverJoueurDTO(@Body TreeSet<PlayerSwissDTO> playerSwiss, @Path("id") int id);

    @GET("/getNamePlayer/{id}")
    Call<String> getNamePlayer(@Path("id") int id);
}
